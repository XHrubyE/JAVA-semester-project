/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.project.comparator;

import cz.muni.fi.pb162.project.geometry.Vertex2D;
import java.util.Comparator;

/**
 *
 * @author Erik Hruby
 */
public class VertexInverseComparator implements Comparator<Vertex2D> {

    @Override
    public int compare(Vertex2D vertex1, Vertex2D vertex2) {
        return vertex2.compareTo(vertex1);
    }
    
}
