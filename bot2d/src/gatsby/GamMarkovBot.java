package gatsby;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.GammaDistributionImpl;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * 
 */
public class GamMarkovBot extends AdvancedRobot {

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
	public int rademacher(double p){
		// generate a Rademacher random variable with probability p of being  1.
		if(rng.nextDouble() < p){
			return 1;
		}else{
			return -1;
		}
	}
	
	public State move1(State xt, GammaDistributionImpl aNoise, GammaDistributionImpl dNoise){
		// Return x_{t+1}
		double at = xt.angle;
		double dt = xt.dist;
		
		double A=0.8, B=180, C=8; 
		double P = 0.7;
		double D=200; 
		
		double at1=0;
		double dt1=0;
		try {
			at1 = -A*at + Math.PI*Math.exp(-B/dt)*Math.pow(aNoise.sample(), P);
			dt1 = C*Math.exp(1+Math.abs(at) ) + D/Math.sqrt(1+dt) + dNoise.sample();
		} catch (MathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new State(at1, dt1);
	}

	/**
	 * run: SitCorner's default behavior
	 */
	public void run() {
		setAllColors(Color.PINK);
		double a0 = 0;
		double d0 = 100;
		State xt = new State(a0, d0);

		double aShape = 4;
		double aScale = 0.3;
		
		double dShape = 7;
		double dScale = 5;
		
		GammaDistributionImpl aNoise = new GammaDistributionImpl(aShape, aScale);
		GammaDistributionImpl dNoise = new GammaDistributionImpl(dShape, dScale);
			
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
			xt = move1(xt, aNoise, dNoise);
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
