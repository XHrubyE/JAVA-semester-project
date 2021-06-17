/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.EmptyDrawableException;
import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.exception.TransparentColorException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Erik Hruby
 */
public class Paper implements Drawable, PolygonFactory {
    private final Set<ColoredPolygon> polygons;
    private Color currentColor;
    
    /**
     * 
     */
    public Paper() {
        polygons = new HashSet<>();
        currentColor = Color.BLACK;
    }
    
    /**
     * 
     * @param other drawable
     */
    public Paper(Drawable other) {
        polygons = new HashSet<>(other.getAllDrawnPolygons());
        currentColor = Color.BLACK;
    }

    @Override
    public void changeColor(Color color) {
        currentColor = color;
    }

    @Override
    public void drawPolygon(Polygon polygon) throws TransparentColorException {
        
        if (currentColor == Color.WHITE) {            
            throw new TransparentColorException(String.format("drawing using color %s", currentColor)); 
        }
         polygons.add(new ColoredPolygon(polygon, currentColor));
    }

    @Override
    public void erasePolygon(ColoredPolygon polygon) {
        polygons.remove(polygon);
    }

    @Override
    public void eraseAll() throws EmptyDrawableException {
        if (polygons.isEmpty()) {
            throw new EmptyDrawableException("Paper is empty");
        }
        polygons.clear();
    }

    @Override
    public Collection<ColoredPolygon> getAllDrawnPolygons() {
        return Collections.unmodifiableSet(polygons);
    }

    @Override
    public int uniqueVerticesAmount() {
        Set<Vertex2D> vertices = new HashSet<>();
        for (ColoredPolygon temp: polygons) {
            Polygon polygon = temp.getPolygon();
            for (int i = 0; i < polygon.getNumVertices(); i++) {
                vertices.add(polygon.getVertex(i));
            }
        }
        return vertices.size();
    }
    
    @Override
    public Polygon tryToCreatePolygon(List<Vertex2D> vertices) throws MissingVerticesException {
        if (vertices == null) {
            throw new NullPointerException("vertices is null");            
        }
        
        List<Vertex2D> verticesCopy = new ArrayList<>(vertices);
               
        try {
            return new CollectionPolygon(verticesCopy);
        } catch(IllegalArgumentException ex) {
            verticesCopy = verticesCopy.stream()
                    .filter(v->(v!=null))
                    .collect(Collectors.toList());
            
            return new CollectionPolygon(verticesCopy);
        }
        
    }
    
    @Override
    public void tryToDrawPolygons(List<List<Vertex2D>> collectionPolygons) throws EmptyDrawableException {        
        Throwable cause = null;
        int drawn = 0;
        for(List<Vertex2D> vertices : collectionPolygons) {
            try {
                Polygon poly = tryToCreatePolygon(vertices);
                drawPolygon(poly);
                drawn++;
            } catch (MissingVerticesException | NullPointerException ex) {
                cause = ex;
            } catch (TransparentColorException ex) {
                changeColor(Color.BLACK);
                cause = ex;
            }            
        }
        if (drawn == 0) {
            throw new EmptyDrawableException("No polygons drawed", cause);
        }
    }
    
    /**
     * 
     * @param color polygon
     * @return new polygon collection
     */
    public  Collection<Polygon> getPolygonsWithColor(Color color) {
        return polygons.stream()
                .filter((p)->p.getColor()==color)
                .map(p->p.getPolygon())
                .collect(Collectors.toSet());
    }
}
