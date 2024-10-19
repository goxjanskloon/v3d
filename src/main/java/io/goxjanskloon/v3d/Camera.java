package io.goxjanskloon.v3d;
public class Camera{
    public static final Interval HIT_RANGE=new Interval(1e-5,Double.POSITIVE_INFINITY);
    public static final Vector Y_POSITIVE=new Vector(0.0,1.0,0.0);
    public Hittable world;
    public Ray ray;
    private Vector upDir,rightDir;
    private double upAngle;
    private int width,height,halfWidth,halfHeight;
    public int maxDepth,samplesPerPixel;
    public Color bgColor;
    public double getUpAngle(){
        return upAngle;
    }
    public void setUpAngle(double upAngle){
        this.upAngle=upAngle;
        upDir=ray.dir.cross(Y_POSITIVE).unit().rotate(ray.dir,upAngle);
        rightDir=upDir.rotate(ray.dir,Math.PI/2.0);
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
    public Camera(Hittable world,Ray ray,double upAngle,int width,int height,int maxDepth,int samplesPerPixel,Color bgColor){
        this.world=world;
        this.ray=ray;
        setUpAngle(upAngle);
        setWidth(width);
        setHeight(height);
        this.maxDepth=maxDepth;
        this.samplesPerPixel=samplesPerPixel;
        this.bgColor=bgColor;
    }
    public Color render(Ray ray,int depth){
        if(depth>maxDepth) return bgColor;
        Hittable.HitRecord record=world.hit(ray,HIT_RANGE);
        if(record==null) return bgColor;
        return render(new Ray(record.point,((ray.dir.sub(record.normal.mul(ray.dir.dot(record.normal)*2.0))).unit().mul(2.0).add(Vector.randomUnit().mul(record.roughness))).unit()),depth+1).scale(ray.dir.neg().dot(record.normal)).scale(record.color).mix(record.color.scale(record.brightness));
    }
    public Color render(int x,int y){
        Color s=Color.BLACK;
        for(int i=0;i<samplesPerPixel;++i)
            s=s.mix(render(new Ray(ray.orig,(ray.dir.add(upDir.mul((halfHeight-y+Math.random()-0.5))).add(rightDir.mul(x-halfWidth+Math.random()-0.5))).unit()),1));
        return s.scale(1.0/samplesPerPixel);
    }
    public Image render(){
        Image image=new Image(new Rgb[height][width]);
        for(int i=0;i<height;++i)
            for(int j=0;j<width;++j) image.pixels[i][j]=render(i,j).toRgb();
        return image;
    }
}