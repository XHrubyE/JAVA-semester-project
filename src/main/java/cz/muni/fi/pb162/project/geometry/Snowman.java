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
public class Snowman {
    private static final int BALLS_NUMBER = 3;
    private static final double DEFAULT_SHRINK_FACTOR = 0.8;
    private RegularPolygon[] balls = new RegularPolygon[BALLS_NUMBER];
    
    /**
     * 
     * @param bottom Circle
     * @param shrinkFactor for smaller Circles
     */
    public Snowman(RegularPolygon bottom, double shrinkFactor) {
        balls[0] = bottom;
        if ((shrinkFactor <= 0) ||(shrinkFactor > 1)) {
            shrinkFactor = DEFAULT_SHRINK_FACTOR;
        }
        for (int i = 1; i < BALLS_NUMBER; i++){
            balls[i] = createBall(balls[i - 1], shrinkFactor);
        }
    }
    
    /**
     * 
     * @return array of Snowman balls
     */
    public RegularPolygon[] getBalls() {
        RegularPolygon[] result = new RegularPolygon[BALLS_NUMBER];
        System.arraycopy(balls, 0, result, 0, BALLS_NUMBER);
        return result;
    }
    
    /**
     * 
     * @param ball under current ball
     * @param shrinkFactor 
     * @return smaller ball over ball
     */
    private RegularPolygon createBall(RegularPolygon ball, double shrinkFactor) {
        RegularPolygon newBall;
        double newRadius = ball.getRadius() * shrinkFactor;
        Vertex2D newCenter = new Vertex2D(ball.getCenter().getX(),
                                          ball.getCenter().getY() + 
                                                 ball.getRadius() +
                                                 newRadius);
        newBall = new GeneralRegularPolygon(newCenter, ball.getNumEdges(), newRadius);
        return newBall;
    }
}
