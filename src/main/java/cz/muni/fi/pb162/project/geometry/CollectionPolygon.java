/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.project.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Erik Hruby
 */
public class CollectionPolygon extends SimplePolygon {
    private final List<Vertex2D> vertices;
    
    /**
     * 
     * @param array vertices
     */
    public CollectionPolygon(Vertex2D[] array) {
        super(array);        
        vertices = new ArrayList<>(Arrays.asList(array));
    }
    
    /**
     * 
     * @param vertices vertices
     */
    public CollectionPolygon(List<Vertex2D> vertices) {
        this(vertices.toArray(new Vertex2D[0]));        
    }
    
    /**
     * 
     * @return new polygon
     */
    public CollectionPolygon withoutLeftmostVertices() {
        if (vertices.isEmpty()) {
            throw new IllegalArgumentException("empty list");
        }
        
        Vertex2D min = Collections.min(vertices, 
                (Vertex2D a, Vertex2D b)->(Double.compare(a.getX(), b.getX())));
        List<Vertex2D> newVertices = new ArrayList<>(vertices);
        while(newVertices.contains(min)){
            newVertices.remove(min);
        }
        return new CollectionPolygon(newVertices);
    }

    @Override
    public Vertex2D getVertex(int index) {
         if (index < 0) {
            throw new IllegalArgumentException("negative index");
        }        
        return vertices.get(index % getNumVertices());
    }

    @Override
    public int getNumVertices() {
        return vertices.size();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        
        if (!this.getClass().equals(o.getClass())) {
            return false;
        }
        
        CollectionPolygon other =(CollectionPolygon) o;
        if (this.getNumVertices() != other.getNumVertices()) {
            return false;
        }
        
        for (int i = 0; i < getNumVertices(); i++) {
            if (!(getVertex(i).equals(other.getVertex(i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return vertices.hashCode();
    }

}
