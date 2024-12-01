package io.goxjanskloon.v3d;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import org.apache.log4j.*;
import io.goxjanskloon.graphics.*;
import io.goxjanskloon.utils.*;
public class Camera{
    public static final Interval HIT_RANGE=new Interval(1e-5,Double.POSITIVE_INFINITY);
    public static final Vector Y_POSITIVE=new Vector(0,1,0);
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
    public Color backgroundLight;
    private final ExecutorService threadPool;
    public double getUpAngle(){
        return upAngle;
    }
    public void setUpAngle(double upAngle){
        this.upAngle=upAngle;
        upDir=ray.dir.cross(Y_POSITIVE).unit().rotate(ray.dir.unit(),upAngle);
        rightDir=upDir.rotate(ray.dir.unit(),-Math.PI/2);
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
    public Camera(Hittable world,Ray ray,double upAngle,int width,int height,int maxDepth,int samplesPerPixel,Color backgroundLight,int dWidth){
        this.world=world;
        this.ray=ray;
        setUpAngle(upAngle);
        setWidth(width);
        setHeight(height);
        this.maxDepth=maxDepth;
        this.samplesPerPixel=samplesPerPixel;
        this.backgroundLight=backgroundLight;
        this.dWidth=dWidth;
        final int threadNumber=width/dWidth+5;
        threadPool=new ThreadPoolExecutor(threadNumber,threadNumber,Long.MAX_VALUE,TimeUnit.DAYS,new ArrayBlockingQueue<>(threadNumber));
    }
    public Color render(Ray ray,int depth){
        Hittable.HitRecord record=world.hit(ray,HIT_RANGE);
        if(record==null){
            if(depth==1) return Color.BLACK;
            return backgroundLight;
        }
        if(depth==maxDepth) return record.color.scale(record.brightness);
        Vector reflectDir=ray.dir.sub(record.normal.mul(ray.dir.dot(record.normal)*2)).unit();
        Vector fuzzedReflectDir=record.material.generate(record.normal,reflectDir);
        return render(new Ray(record.point,fuzzedReflectDir),depth+1).div(record.material.getPossibility(reflectDir,fuzzedReflectDir)).scale(record.color).mix(record.color.scale(record.brightness));
    }
    public Color render(int x,int y){
        Color s=Color.BLACK;
        for(int i=0;i<samplesPerPixel;++i){
            Color sample=render(new Ray(ray.orig,(ray.dir.add(upDir.mul((halfHeight-y+Interval.UNIT_RANGE.random()))).add(rightDir.mul(x-halfWidth+Interval.UNIT_RANGE.random()))).unit()),1);
            if(sample.isValid())
                s=s.mix(sample);
        }
        return s.div(samplesPerPixel);
    }
    private class renderRunnable implements Runnable{
        private final int l,r;
        private final Rgb[][] p;
        private final AtomicInteger total;
        public renderRunnable(int i,Rgb[][] pixels,AtomicInteger total){
            r=Math.min((l=i)+dWidth,width);
            p=pixels;
            this.total=total;
        }
        @Override public void run(){
            for(int i=0;i<height;++i)
                for(int j=l;j<r;++j)
                    p[i][j]=render(j,i).toRgb();
            int remain=total.decrementAndGet();
            logger.log(Level.INFO,"Thread "+l/dWidth+" finished,"+remain+" thread"+(remain>1?"s":"")+" left.");
        }
    }
    public Image render(){
        Image image=new Image(new Rgb[height][width]);
        AtomicInteger total=new AtomicInteger();
        for(int i=0;i<width;i+=dWidth){
            total.incrementAndGet();
            threadPool.execute(new renderRunnable(i,image.pixels,total));
            logger.log(Level.INFO,"Thread "+i/dWidth+" submitted.");
        }
        try{
            threadPool.shutdown();
            if(threadPool.awaitTermination(Integer.MAX_VALUE,TimeUnit.DAYS)){
                logger.log(Level.INFO,"Rendering finished.");
                return image;
            }
        }catch(InterruptedException e){
            logger.log(Level.ERROR,"Rendering interrupted.",e);
        }
        return null;
    }
}