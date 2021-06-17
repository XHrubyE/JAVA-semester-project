/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Measurable;
import cz.muni.fi.pb162.project.geometry.Triangle;

/**
 *
 * @author Erik Hruby
 */
public class Gauger {
    /**
     * 
     * @param object of information
     */
    public static void printMeasurement(Measurable object) {
        System.out.printf("Width: %s%sHeight: %s%s", object.getWidth(), 
                                                     System.lineSeparator(), 
                                                     object.getHeight(),
                                                     System.lineSeparator());
    }
    
    /**
     * 
     * @param triangle object
     */
    public static void printMeasurement(Triangle triangle) {
        System.out.println(triangle);
        printMeasurement((Measurable) triangle);
    }
}
