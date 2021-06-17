/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.project.geometry;

/**
 *
 * @author Erik Hruby
 */
public class Circle extends GeneralRegularPolygon implements Measurable, Circular{                
    /**
     * 
     * @param center 
     * @param radius 
     */
    public Circle(Vertex2D center, double radius) {
       super(center, Integer.MAX_VALUE, radius);
       this.setColor(Color.RED);
    }
    /**
     * 
     */
    public Circle() {
        this(new Vertex2D(0.0, 0.0), 1.0);
    }
       
    @Override
    public String toString() {
        return String.format("Circle: center=%s, radius=%s", 
                            this.getCenter(),
                            this.getRadius());
    }
    
    @Override
    public double getEdgeLength() {
        return 0;
    }
}
