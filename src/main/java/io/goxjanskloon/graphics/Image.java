package io.goxjanskloon.graphics;
import java.io.*;
import org.apache.log4j.*;
public class Image{
    public static final Logger logger=Logger.getLogger(Image.class);
    static{
        PropertyConfigurator.configure(Image.class.getClassLoader().getResourceAsStream("log4j.properties"));
    }
    public final Rgb[][] pixels;
    public Image(Rgb[][] pixels){
        this.pixels=pixels;
    }
    public boolean output(Writer writer){
        try{
            writer.write("P3\n"+pixels[0].length+" "+pixels.length+"\n255\n");
            for(Rgb[] line:pixels)
                for(Rgb pixel:line)
                    writer.write(pixel+" ");
            return true;
        }catch(IOException e){
            logger.log(Level.ERROR,e.toString(),e);
            return false;
        }
    }
}