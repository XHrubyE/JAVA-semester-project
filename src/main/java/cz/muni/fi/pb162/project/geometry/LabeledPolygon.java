/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.project.geometry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * @author Erik Hruby
 */
public final class LabeledPolygon extends SimplePolygon implements Labelable, Sortable, PolygonWritable {
    private final SortedMap<String, Vertex2D> vertices;  
    private static final Gson GSON_WRITER = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();
    /**
     * @author Erik Hruby
     */
    public static class Builder implements Buildable<LabeledPolygon>, PolygonReadable {

        private final SortedMap<String, Vertex2D> vertices = new TreeMap();
        
        /**
         * 
         * @param label of vertex
         * @param vertex value
         * @return Map
         */
        public Builder addVertex(String label, Vertex2D vertex) {
            if (label == null || vertex == null) {
                throw new IllegalArgumentException("label or vertex is null");
            }
            vertices.put(label, vertex);
            return this;
        }
        
        @Override
        public LabeledPolygon build() {
            return new LabeledPolygon(vertices);
        }

        @Override
        public Builder read(InputStream is) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Map<String, Vertex2D> tmpVertices = new HashMap();
            
            while(reader.ready()) {
                String line = reader.readLine();
                lineToVertex(line, tmpVertices);
            }            
            vertices.putAll(tmpVertices);
            return this;
        }
        
        private void lineToVertex(String line, Map<String, Vertex2D> map) throws IOException {
            if (line != null) {
                    String[] lineSplit = line.split(" ", 3);
                    if (lineSplit.length != 3) {
                        throw new IOException("Invalid line format");
                    }
                    
                    Vertex2D vertex = null;
                    try{
                        double x = Double.parseDouble(lineSplit[0]);
                        double y = Double.parseDouble(lineSplit[1]);
                        vertex = new Vertex2D(x, y);
                    }catch(NumberFormatException nfe) {
                        throw new IOException("Invalid value", nfe);
                    }
                    map.put(lineSplit[2], vertex);
            }
        }

        @Override
        public Builder read(File file) throws IOException {
            /*InputStream is = null;
            try {
                is = new FileInputStream(file);
                read(is);
                is.close();
            }finally{
                if(is != null) {
                    is.close();
                }
                
            }*/
            try(InputStream is = new FileInputStream(file)) {
                return read(is);
            }
            
            
        }
        
    }
       
    @Override
    public Vertex2D getVertex(String label) {
        if (!(vertices.keySet().contains(label))) {
            throw new IllegalArgumentException("label not contain");
        }
        return vertices.get(label);
    }

    @Override
    public Collection<String> getLabels() {
        return new TreeSet<>(this.vertices.keySet());
    }

    @Override
    public Collection<String> getLabels(Vertex2D vertex) {
        return vertices.entrySet().stream()
                .filter(a->a.getValue().equals(vertex))
                .map(a->a.getKey())
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<Vertex2D> getSortedVertices() {
        return new TreeSet<>(vertices.values());
    }

    @Override
    public Collection<Vertex2D> getSortedVertices(Comparator<Vertex2D> comparator) {
       SortedSet<Vertex2D> sorted = new TreeSet<>(comparator);
       sorted.addAll(vertices.values());
       return sorted;
    }        
    
    private LabeledPolygon(Map<String, Vertex2D> vertices) {
        super(vertices.values().toArray(new Vertex2D[0]));
        this.vertices = new TreeMap<>(vertices);
    }

    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("incorrect index");
        }
        return vertices.values().toArray(new Vertex2D[0])[index % getNumVertices()];
    }

    @Override
    public int getNumVertices() {
        return vertices.keySet().size();
    }
    
    /**
     * 
     * @return duplicate vertices 
     */
    public Collection<Vertex2D> duplicateVertices() {
        return vertices.values().stream()
                .filter(a->Collections.frequency(vertices.values(), a)>1)
                .collect(Collectors.toSet());
    }
    
    @Override
    public void write(OutputStream os) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        try{                    
            for(Map.Entry<String, Vertex2D>vertexEntry : vertices.entrySet()) {
                Vertex2D vertex = vertexEntry.getValue();
                writer.write(String.format("%s %s %s", vertex.getX(), 
                                                       vertex.getY(), 
                                                       vertexEntry.getKey()));
                writer.write(System.lineSeparator());
            }
        }finally{
            writer.flush();
        }
        
    }

    @Override
    public void write(File file) throws IOException {
        try(OutputStream os = new FileOutputStream(file)) {
            write(os);
        }
    }
    
    /**
     * 
     * @param os out
     */
    public void writeJson(OutputStream os) {
        String json = GSON_WRITER.toJson(vertices);
        new PrintStream(os).print(json);
    }
}
