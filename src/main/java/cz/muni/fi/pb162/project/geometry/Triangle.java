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
public class Triangle extends ArrayPolygon{
    private static final int A = 0;
    private static final int B = 1;
    private static final int C = 2;
    private static final double EPSILON = 0.001;
        
    private final Triangle[] subTriangles = new Triangle[3];
    
    /**
     * 
     * @param point0 Vertex2D of point 0
     * @param point1 Vertex2D of point 1 
     * @param point2 Vertex2D of point 2
     */
    public Triangle(Vertex2D point0, Vertex2D point1, Vertex2D point2) {
        super(new Vertex2D[] {point0, point1, point2});              
    }
    /**
     * 
     * @param point0 point 0 of triangle
     * @param point1 point 1 of triangle
     * @param point2 point 2 of trinagle
     * @param depth depth of recursion
     */
    public Triangle(Vertex2D point0, Vertex2D point1, Vertex2D point2,int depth) {
        this(point0, point1, point2);
        divide(depth);
    }
    
    @Override
    public String toString() {
        return String.format("Triangle: vertices=%s %s %s", getVertex(0),
                getVertex(1), getVertex(2));
    }
    
    /**
     * 
     * @param index vertices index
     * @return if in Range return True else False
     */
    private boolean isInRange(int index) {
        return !((index < 0) || (index > 2));
    }    
        
    /**
     * 
     * @return if already divided returns False else True
     */
    public boolean isDivided() {
        return  subTriangles[0] != null
                && subTriangles[1] != null
                && subTriangles[2] != null;
    }
    
    /**
     * 
     * @param index Triangle index
     * @return Triangle on corresponding index
     */
    public Triangle getSubTriangle(int index) {
        if(index < 0 || index > 2 || !isDivided()) {
            return null;
        }
        return subTriangles[index];
    }
    /**
     * 
     * @return if already divided returns False else True
     */
    public boolean divide() {
        if(isDivided()) {
            return false;
        }
        
        Vertex2D midA = getVertex(B).createMiddle(getVertex(C));
        Vertex2D midB = getVertex(A).createMiddle(getVertex(C));
        Vertex2D midC = getVertex(A).createMiddle(getVertex(B));                
        
        subTriangles[A] = new Triangle(getVertex(A), midC, midB);
        subTriangles[B] = new Triangle(getVertex(B), midC, midA);
        subTriangles[C] = new Triangle(getVertex(C), midA, midB);
        return true;
                
    }
    /**
     * 
     * @param depth of recursion
     */
    public void divide(int depth) {
        if (depth > 0) {
            divide();
            for(Triangle triangle:subTriangles) {
                triangle.divide(depth - 1);
            }
        }
    }    
    /**
     * 
     * @return True if all sides equal False otherwise
     */
    public boolean isEquilateral() {
       double sideA = getVertex(A).distance(getVertex(B));
       double sideB = getVertex(B).distance(getVertex(C));
       double sideC = getVertex(C).distance(getVertex(A));
       
       return (Math.abs(sideC - sideA) < EPSILON) &&
              (Math.abs(sideA - sideB) < EPSILON) &&
              (Math.abs(sideB - sideC) < EPSILON);
    }
}
