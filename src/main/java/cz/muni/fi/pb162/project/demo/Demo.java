package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.geometry.LabeledPolygon;
import cz.muni.fi.pb162.project.geometry.Vertex2D;
import java.io.File;
import java.io.IOException;


/**
 * Demo class for second seminar
 *
 * @author Erik Hruby
 */
public class Demo {

    /**
     * 
     * @param args arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {                   
        LabeledPolygon polygon = new LabeledPolygon.Builder()
                .read(new File("polygon-ok.txt"))
                .addVertex("vrchol x", new Vertex2D(123, 456))
                .build();
        polygon.writeJson(System.out);
        System.out.println("Hello World!");
    }
}
