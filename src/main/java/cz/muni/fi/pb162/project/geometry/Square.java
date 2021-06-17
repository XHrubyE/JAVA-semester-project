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
public class Square extends GeneralRegularPolygon implements Circular{      
    /**
     * 
     * @param center of Square
     * @param diameter of Circle used to describe Square
     */
    public Square(Vertex2D center, double diameter) {
        super(center, 4, diameter / 2);
    }    
    /**
     * 
     * @param object Circle used to describe Square
     */
    public Square(Circular object) {
        this(object.getCenter(), object.getRadius() * 2);
    }    
    /*
    public Vertex2D getVertex(int index) {
        switch(index){
            case 0:return new Vertex2D(center.getX() - radius, center.getY());
            case 1:return new Vertex2D(center.getX(), center.getY() - radius);
            case 2:return new Vertex2D(center.getX() + radius, center.getY());
            case 3:return new Vertex2D(center.getX(), center.getY() + radius);
            default:return null;
        }        
    }*/           
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Square: vertices=");
        for (int i = 0; i < 4; i++) {
            result.append(getVertex(i));
            if (i < 3) {
                result.append(" ");
            }
        }        
        return result.toString();
    }
}
