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
public class RegularOctagon extends GeneralRegularPolygon {
    /**
     * 
     * @param center of octagon
     * @param radius of octagon
     */
    public RegularOctagon(Vertex2D center, double radius) {
        super(center, 8, radius);
    }
}
