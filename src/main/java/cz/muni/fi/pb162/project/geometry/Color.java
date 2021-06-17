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
public enum Color {
    BLACK, WHITE, RED, GREEN, BLUE, YELLOW, ORANGE;
    
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
