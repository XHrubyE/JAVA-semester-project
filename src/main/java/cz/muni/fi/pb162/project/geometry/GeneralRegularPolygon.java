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
public class GeneralRegularPolygon implements Colored, RegularPolygon {
    private Vertex2D  center;
    private double radius;
    private int edges;
    private Color color = Color.BLACK;
    
    /**
     * 
     * @param center of polygon
     * @param edges of polygon
     * @param radius of polygon
     */
    public GeneralRegularPolygon(Vertex2D center, int edges, double radius) {
        this.center = center;
        this.radius = radius;
        this.edges = edges;       
    }
    
    @Override
    public void setColor(Color color) {
        this.color = color;
    }
    
    @Override
    public Color getColor() {
        return color;
    }
    
    @Override
    public int getNumEdges() {
        return edges;
    }
    
    @Override
    public double getEdgeLength() {
        return 2 * radius * Math.sin(Math.PI / edges);
    }
    
    @Override
    public Vertex2D getVertex(int index) {
        double newX = center.getX() - radius * Math.cos(index * 2 * Math.PI / edges);
        double newY = center.getY() - radius * Math.sin(index * 2 * Math.PI / edges);
        return new Vertex2D(newX, newY);        
    }
    
    @Override
    public  double getWidth() {
        return radius * 2;
    }
    
    @Override
    public double getHeight() {
        return this.getWidth();
    }
    
    @Override
    public  Vertex2D getCenter() {
        return center;
    }
    
    @Override
    public double getRadius() {
        return radius;
    }
    
    @Override
    public String toString() {
        return String.format("%s-gon: center=%s, radius=%s, color=%s",
                            getNumEdges(), getCenter(), getRadius(), getColor());
    }
}
