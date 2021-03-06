import lejos.nxt.Sound;
import lejos.nxt.NXTRegulatedMotor;
import lejos.util.Delay;

/** The stacker class allows the robot to operate the stacking mechanism.
 The greenZone method is called by Swagbot when the robot is in the
green zone and allows the robot to stack the blocks.
@author Xavier,Neil */
class Stacker {

	/* this is untested; Alex can you test it Tomorrow in the morning? -Neil
	 I've got it. -Alex */
	private static final float STACKING_SPEED = 100f;

	private static final int openAngle  = -15;
	private static final int closeAngle = 60;

	private int    collected;
	private final Colour colour;
	private final Swagbot swag;

	public Stacker(final Colour colour, final Swagbot swag) {
		this.colour = colour;
		this.swag   = swag;
		collected = 0;
	}
	
	public void resetMotor() {
		Hardware.stackerMotor.resetTachoCount();
	}

	public void openStacker() {
		resetMotor();
		Hardware.stackerMotor.rotateTo(openAngle);
	}

	public void closeStacker() {
		resetMotor();
		Hardware.stackerMotor.rotateTo(closeAngle);
	}

	/** non-blocking to get the Swagbots downward colour sensor
	 @author Neil */
	public boolean hasBlock() {
		return colour.getStyrofoamProbability() > 0.77f;
	}

	/** only called on hasBlock true (this needs to be re-written) */
	public boolean hasTwoBricks() {
		collected++;
		Sound.beep();
		swag.setSpeeds(STACKING_SPEED, STACKING_SPEED);
		/* forward ? cm */
		Delay.msDelay(3000); /* check this! */
		/* this may or may not be neccessary -Neil */
		swag.stop();
		/* return */
		if(collected==2){
			Sound.buzz();
			return true;
		}
		else{
			return false;
		}
	}
   /** Stacks the blocks the robot is holding. 
   Print statments are included for debugging as well as unexpected behavior with the rotateTo method turning past the given angle.
   There was believed that the print statments might stop the problem. Whether this work or not is unknown. We added them last minute.
   @author Xavier,Neil,Alex*/	
	public void greenZone() {
		Display.setText("Victory!");
		swag.stop();
		openStacker();
      System.out.print("hello");
		swag.setSpeeds(STACKING_SPEED, STACKING_SPEED); /* such a hack -Neil */
		//forward 6 cm (fixme: check)
		Delay.msDelay(1500);
		swag.stop();
		closeStacker();
      System.out.print("hello");
		//forward 11 cm (fixme: check)
		swag.setSpeeds(STACKING_SPEED, STACKING_SPEED); /* such a hack -Neil */
		Delay.msDelay(2000);
		swag.stop();
		openStacker();
      System.out.print("hello");
      Hardware.stackerMotor.stop();
		swag.stop();
		Delay.msDelay(1000);
		swag.setSpeeds(300, 300); /* go somewhere... out of green zone -Alex */
		Delay.msDelay(2000);
      swag.stop();
      System.exit(0);
	}
	
}
