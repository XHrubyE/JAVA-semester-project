/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.project.exception;

/**
 *
 * @author Erik Hruby
 */
public class MissingVerticesException extends RuntimeException{

    /**
     * 
     * @param message def
     */
    public MissingVerticesException(String message) {
        super(message);
    }

    /**
     * 
     * @param message def
     * @param cause throwable
     */
    public MissingVerticesException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
