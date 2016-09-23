package gatsby;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.Timer;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * AngleDist1stMarkovBot. This robot moves in hops: move then stop, move then stop,...
 * Its states is defined by (orientation a_t, distance to move_t) = (a_t, d_t) = x_t.
 * x_t defines a 1st-order Markov chain. x_t = g(x_t-1).
 */
public class AngleDist1stMarkovBot extends AdvancedRobot {

	private Random rng = new Random();
	
	public static class State{
		// a = angle relative to the current heading angle
		public double angle=0;
		// d = distance to travel
		public double dist=0;
		
		public State(double angle, double dist){
			this.angle = angle;
			this.dist = dist;
		}
	}
	
	// get bearing in radians to a target(x,y) from the current position/heading
	public double getBearing(double x, double y) {

		// can rotate the coordinate system to avoid the addition of pi/2 if you
		// like
		double centerAngle =  Math.atan2(x - getX(), y - getY());
		return Utils.normalRelativeAngle(centerAngle - getHeadingRadians());
	}
	
	public double rndGauss(double mean, double sd){
		return rng.nextGaussian()*sd + mean;
	}
	
	public int toInt(boolean b){
		return b ? 1:0;
	}
	public State move1(State xt){
		// Return x_{t+1}
		double at = xt.angle;
		double dt = xt.dist;
		
		double A=0.8, B=140, C=30; 
		double ep=Math.PI/6;
		double D=200; 
		
		double at1Noise = rndGauss(0, Math.PI/10); 
		double at1 = -A*at + Math.PI*Math.exp(-B/dt) + at1Noise ;
		double dt1Noise = rng.nextDouble()*50;
		
		double dt1 = C/(1+Math.abs(at)) + D/(1+dt) + 60 + dt1Noise;
		return new State(at1, dt1);
	}

	/**
	 * run: SitCorner's default behavior
	 */
	public void run() {
		setAllColors(Color.MAGENTA);
		double a0 = 0;
		double d0 = 100;
		State xt = new State(a0, d0);
		
		while (true) {
			// turn around
			turnRightRadians(xt.angle);
			// move straight
			ahead(xt.dist);
			// stop for a while
			try {
				Thread.sleep((long)(1000 * 0.2));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// state transition
			xt = move1(xt);
		}
	}

	@Override
	public void onPaint(Graphics2D g) {
		// TODO Auto-generated method stub
		super.onPaint(g);
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		// fire(1);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like

	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
	}

}
