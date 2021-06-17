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
public class Vertex2D implements Comparable<Vertex2D> {
    private final double x;
    private final double y;
    /**
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public Vertex2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }    
    
    @Override    
    public String toString() {        
        //return("[" + x + ", " + y + "]");     
        return String.format("[%s, %s]", x, y);
    }    
    /**
     * 
     * @param otherVertex second vertex
     * @return middle Vertex
     */
    public Vertex2D createMiddle(Vertex2D otherVertex) {
        double midX = (x + otherVertex.x) / 2;
        double midY = (y + otherVertex.y) / 2;
        return new Vertex2D(midX, midY);
    }
    /**
     * 
     * @param vertex second point
     * @return distance of 2 points
     */
    public double distance(Vertex2D vertex) {
        if (vertex == null) {
            return -1.0;
        }
        
        double difX = Math.abs(x - vertex.x);
        double difY = Math.abs(y - vertex.y);
        
        return Math.sqrt(difX * difX + difY * difY);
    }
    
    @Override
    public boolean equals(Object o) {        
        if (!(o instanceof Vertex2D)) {
            return false;
        }       
        
        Vertex2D that = (Vertex2D) o;
        
        return Double.compare(this.x, that.getX()) == 0 &&
               Double.compare(this.y, that.getY()) == 0; 
    }
    
    @Override
    public int hashCode() { 
        return Double.hashCode(x) & Double.hashCode(y)>>4;
    }

    @Override
    public int compareTo(Vertex2D that) {
        int cmp = Double.compare(this.x, that.getX());
        if (cmp == 0) {
            return Double.compare(this.y , that.getY());
        }
        return cmp;
    }
    
}
