/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.project.geometry;

import java.util.Arrays;

/**
 *
 * @author Erik Hruby
 */
public class ArrayPolygon extends SimplePolygon {
    private final Vertex2D[] vertices;
    
    /**
     * 
     * @param array of vertexes
     */
    public ArrayPolygon(Vertex2D[] array) {
        super(array);        
        vertices = Arrays.copyOf(array, array.length);
    }
    
    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("negative index");
        }        
        return vertices[index % getNumVertices()];
    }
    
    @Override
    public int getNumVertices() {
        return vertices.length;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        
        if (!this.getClass().equals(o.getClass())) {
            return false;
        }
        
        ArrayPolygon other = (ArrayPolygon) o;
        if (this.getNumVertices() != other.getNumVertices()) {
            return false;
        }
        
        for (int i = 0; i < getNumVertices(); i++) {
            if (!this.getVertex(i).equals(other.getVertex(i))) {
                return false;
            }
        }
        return true;
        
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        for (Vertex2D vertex : vertices) {
            hash = hash * 7 + vertex.hashCode();
        }
        return hash;
    }
}
