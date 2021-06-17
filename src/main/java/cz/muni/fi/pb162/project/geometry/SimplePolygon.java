/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.utils.SimpleMath;
import java.util.Arrays;

/**
 *
 * @author Erik Hruby
 */
public abstract class SimplePolygon implements Polygon {
    
    /**
     * 
     * @param array of polygons
     */
    public SimplePolygon(Vertex2D[] array) {
        if (array == null || Arrays.asList(array).contains(null)) {
            throw new IllegalArgumentException("Array in null or contains null");
        }
        
        if (array.length < 3) {
            throw new MissingVerticesException("Less than 3 vertices");
        }
    }
    
    @Override
    public double getWidth() {
        return SimpleMath.maxX(this) - SimpleMath.minX(this);
    }
    
    @Override
    public double getHeight() {
        return SimpleMath.maxY(this) - SimpleMath.minY(this);
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Polygon: vertices =");
        for (int i = 0; i < getNumVertices(); i++) {
            result.append(" ").append(getVertex(i));
        }
        return result.toString();
    }
    
}
