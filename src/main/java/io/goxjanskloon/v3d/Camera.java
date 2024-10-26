package io.goxjanskloon.v3d;
import java.util.concurrent.*;
import org.apache.log4j.*;
public class Camera{
    public static final Interval HIT_RANGE=new Interval(1e-5,Double.POSITIVE_INFINITY);
    public static final Vector Y_POSITIVE=new Vector(0.0,1.0,0.0);
    private static final Logger logger=Logger.getLogger(Camera.class);
    static{
        PropertyConfigurator.configure(Camera.class.getClassLoader().getResourceAsStream("log4j.properties"));
    }
    public Hittable world;
    public Ray ray;
    private Vector upDir,rightDir;
    private double upAngle;
    private int width,height,halfWidth,halfHeight,dWidth;
    public int maxDepth,samplesPerPixel;
    public Color bgColor;
    private final ExecutorService threadPool;
    public double getUpAngle(){
        return upAngle;
    }
    public void setUpAngle(double upAngle){
        this.upAngle=upAngle;
        upDir=ray.dir.cross(Y_POSITIVE).unit().rotate(ray.dir.unit(),upAngle);
        rightDir=upDir.rotate(ray.dir.unit(),-Math.PI/2.0);
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setWidth(int width){
        halfWidth=(this.width=width)>>1;
    }
    public void setHeight(int height){
        halfHeight=(this.height=height)>>1;
    }
    public Camera(Hittable world,Ray ray,double upAngle,int width,int height,int maxDepth,int samplesPerPixel,Color bgColor,int dWidth){
        this.world=world;
        this.ray=ray;
        setUpAngle(upAngle);
        setWidth(width);
        setHeight(height);
        this.maxDepth=maxDepth;
        this.samplesPerPixel=samplesPerPixel;
        this.bgColor=bgColor;
        this.dWidth=dWidth;
        final int threadNumber=width/dWidth+5;
        threadPool=new ThreadPoolExecutor(threadNumber,threadNumber,Long.MAX_VALUE,TimeUnit.DAYS,new ArrayBlockingQueue<>(threadNumber));
    }
    public Color render(Ray ray,int depth){
        if(depth>maxDepth) return bgColor;
        Hittable.HitRecord record=world.hit(ray,HIT_RANGE);
        if(record==null){
            if(depth==1) return Color.BLACK;
            return bgColor;
        }
        Vector fuzzedNormal=Vector.randomUnitOnHemisphere(record.normal,record.roughness);
        Vector reflectDir=ray.dir.sub(fuzzedNormal.mul(ray.dir.dot(fuzzedNormal)*2.0)).unit();
        Color reflectColor=render(new Ray(record.point,reflectDir),depth+1).scale(ray.dir.neg().dot(record.normal));
        return reflectColor.scale(record.color).mix(record.color.scale(record.brightness));
    }
    public Color render(int x,int y){
        Color s=Color.BLACK;
        for(int i=0;i<samplesPerPixel;++i){
            Color sample=render(new Ray(ray.orig,(ray.dir.add(upDir.mul((halfHeight-y+ThreadLocalRandom.current().nextDouble(-0.5,0.5)))).add(rightDir.mul(x-halfWidth+ThreadLocalRandom.current().nextDouble(-0.5,0.5)))).unit()),1);
            if(sample.isValid())
                s=s.mix(sample);
        }
        return s.scale(1.0/samplesPerPixel);
    }
    private class renderRunnable implements Runnable{
        private final int l,r;
        private final Rgb[][] p;
        public renderRunnable(int i,Rgb[][] pixels){
            r=Math.min((l=i)+dWidth,width);
            p=pixels;
        }
        @Override public void run(){
            for(int i=0;i<height;++i)
                for(int j=l;j<r;++j)
                    p[i][j]=render(j,i).toRgb();
            logger.log(Level.INFO,"Thread "+l/dWidth+" finished.");
        }
    }
    public Image render(){
        Image image=new Image(new Rgb[height][width]);
        for(int i=0;i<width;i+=dWidth){
            threadPool.execute(new renderRunnable(i,image.pixels));
            logger.log(Level.INFO,"Thread "+i/dWidth+" submitted.");
        }
        try{
            threadPool.shutdown();
            if(threadPool.awaitTermination(Integer.MAX_VALUE,TimeUnit.DAYS))
                return image;
        }catch(InterruptedException e){
            logger.log(Level.ERROR,e);
        }
        return null;
    }
}