/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.project.geometry;

import java.util.Objects;

/**
 *
 * @author Erik Hruby
 */
public class ColoredPolygon {
    private final Polygon polygon;
    private final Color color;
    
    /**
     * 
     * @param polygon polyogn
     * @param color of polygon
     */
    public ColoredPolygon(Polygon polygon, Color color) {
        this.polygon = polygon;
        this.color = color;
    }
    
    public Polygon getPolygon() {
        return polygon;
    }
    
    public Color getColor() {
        return color;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.polygon);
        hash = 67 * hash + Objects.hashCode(this.color);
        return hash;
    }
    
    @Override
    public boolean equals(Object o) {
       if (o == null) {
            return false;
        }
        
        if (!this.getClass().equals(o.getClass())) {
            return false;
        }
        
        ColoredPolygon other = (ColoredPolygon) o;
        return getPolygon().equals(other.getPolygon()) &&
                getColor().equals(other.getColor());
    }
    
    
}
