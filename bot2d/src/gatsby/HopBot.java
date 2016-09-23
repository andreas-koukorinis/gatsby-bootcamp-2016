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
 * HopBot. This robot moves in a discrete step. It moves for a certain distance
 * in a straight line, stop, turn itself and repeat. The distance is drawn from
 * a distribution.
 */
public class HopBot extends AdvancedRobot {

	private Random rng = new Random();

	// get bearing in radians to a target(x,y) from the current position/heading
	public double getBearing(double x, double y) {

		// can rotate the coordinate system to avoid the addition of pi/2 if you
		// like
		double centerAngle =  Math.atan2(x - getX(), y - getY());
		return Utils.normalRelativeAngle(centerAngle - getHeadingRadians());
	}

	public double drawDistance() {
		// N(220, 50)
		double mean = 150;
		double sd = 60;
		double dist = rng.nextGaussian() * sd + mean;
		return dist;
	}

	/**
	 * run: SitCorner's default behavior
	 */
	public void run() {
		setAllColors(Color.WHITE);
		while (true) {
			double moveDist = drawDistance();
			final double bound = 100;
			// bearing to the center
			double bearingCen = getBearing(getBattleFieldWidth()/2, getBattleFieldHeight()/2);
			// add some noise to the radians angle
			double noiseBearing = bearingCen + rng.nextFloat()*Math.PI/12;
			// -bound to bound
			//double angle = rng.nextFloat()*bound*2 - bound;
			ahead(moveDist);

			// stop for a while
			try {
				Thread.sleep((long)(1000 * 0.2));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// turn around
			turnRightRadians(noiseBearing);
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
