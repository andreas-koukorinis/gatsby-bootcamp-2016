package gatsby;

import java.awt.Graphics2D;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * SitCorner - a robot by (your name here)
 */
public class SitCorner extends AdvancedRobot {

	// get bearing in radians to a target(x,y) from the current position/heading
	public double getBearing(double x, double y) {

		// can rotate the coordinate system to avoid the addition of pi/2 if you
		// like
		double centerAngle = Math.atan2(x - getX(), y - getY());
		return Utils.normalRelativeAngle(centerAngle- getHeadingRadians());
		
	}

	/**
	 * run: SitCorner's default behavior
	 */
	public void run() {

		while (true) {
			// Go the the upper left wall and sit
			double corx = 0;
			double cory = this.getBattleFieldHeight();
			double x = this.getX();
			double y = this.getY();
			double toCorAngle = getBearing(corx, cory);
			final double margin = 30;
			if (Math.abs(x - corx) > margin || Math.abs(y - cory) > margin) {
				// not at the corner yet
				// this.setTurnLeftRadians(toCorAngle);
				this.setTurnRightRadians(toCorAngle);

				// avoid the wall

				final double turn = 10;
				if (x < margin) {
					setTurnRight(turn);
				}
				if (y > getBattleFieldHeight() - margin) {
					setTurnLeft(turn);
				}
			}

			this.setAhead(100);
			this.execute();
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
