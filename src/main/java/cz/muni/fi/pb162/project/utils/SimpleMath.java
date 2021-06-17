/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Polygon;

/**
 *
 * @author Erik Hruby
 */
public class SimpleMath {
    
    /**
     * 
     * @param polygon object
     * @return smallest X cor of polygon
     */
    public static double minX(Polygon polygon) {        
        double minX = polygon.getVertex(0).getX();
        for (int i = 0; i < polygon.getNumVertices(); i++) {
            minX = Math.min(minX, polygon.getVertex(i).getX());
    }
        return minX;
    }
    
    /**
     * 
     * @param polygon object
     * @return smallest Y cor of triangle
     */
    public static double minY(Polygon polygon) {
        double minY = polygon.getVertex(0).getY();
        for (int i = 0; i < polygon.getNumVertices(); i++) {
            minY = Math.min(minY, polygon.getVertex(i).getY());
    }
        return minY;
    }
    
    /**
     * 
     * @param polygon object
     * @return biggest X cor of triangle
     */    
    public static double maxX(Polygon polygon) {
        double maxX = polygon.getVertex(0).getX();
        for (int i = 0; i < polygon.getNumVertices(); i++) {
            maxX = Math.max(maxX, polygon.getVertex(i).getX());
    }
        return maxX;
    }
    
    /**
     * 
     * @param polygon object
     * @return biggest Y cor of triangle
     */    
    public static  double maxY(Polygon polygon) {
        double maxY = polygon.getVertex(0).getY();
        for (int i = 0; i < polygon.getNumVertices(); i++) {
            maxY = Math.max(maxY, polygon.getVertex(i).getY());
    }
        return maxY;    
    }
}
