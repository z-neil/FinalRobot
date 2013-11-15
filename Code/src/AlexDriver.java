import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.LCD;

import bluetooth.*;


/* this is a driver that instantaties a Robot and makes it do stuff */

class AlexDriver {

	private static final SensorPort  sonicPort = SensorPort.S4;
	private static final SensorPort colourPort = SensorPort.S3;
   private static final SensorPort lightPort = SensorPort.S1;
   private static Swagbot robot = new Swagbot(sonicPort,colourPort,lightPort);
   private static Display display;

	@SuppressWarnings("unused")
	private static final boolean doLoco = true;
	
	public static void main(String args[]) {
      
		/*
		float destination_x, destination_y;

		BluetoothConnection conn = new BluetoothConnection();
		// as of this point the bluetooth connection is closed again, and you can pair to another NXT (or PC) if you wish
		
		// example usage of Tranmission class
		Transmission t = conn.getTransmission();
		if (t == null) {
			LCD.drawString("Failed to read transmission", 0, 5);
			destination_x = 0;
			destination_y = 0;
		} else {
			StartCorner corner = t.startingCorner;
			PlayerRole role = t.role;
			// green zone is defined by these (bottom-left and top-right) corners:
			int[] greenZone = t.greenZone;
			// red zone is defined by these (bottom-left and top-right) corners:
			int[] redZone = t.redZone;
			
			destination_x = (float)(greenZone[0] + greenZone[2])/2 - 1;
			destination_y = (float)(greenZone[1] + greenZone[3])/2 - 1;

			if (corner == StartCorner.BOTTOM_LEFT) {
			}
			else if (corner == StartCorner.BOTTOM_RIGHT) {
				destination_x = 8 - destination_x;
			}
			else if (corner == StartCorner.TOP_LEFT) {
				destination_y = 8 - destination_y;
			}
			else if (corner == StartCorner.TOP_RIGHT) {
				destination_x = 8 - destination_x;
				destination_y = 8 - destination_y;
			}
			destination_x = destination_x * 30.48f;
			destination_y = destination_y * 30.48f;

			// print out the transmission information to the LCD
			conn.printTransmission();
		}
		// stall until user decides to end program
		Button.waitForAnyPress();
		 /\ what is this shit?
		 */

      display = new Display(robot);

		monitorForExit();

		/* Neil: loco, travel to the 2nd square, and turn to 90 */
		//robot.localise();
		//waitForIdle();
		//robot.travelTo(60.96f, 60.96f/*30.48f,30.48f*/);
		//waitForIdle();
		//robot.turnTo(90f);
		//waitForIdle();

//      runTests();
//      runAbridgedTests();
//      robot.scanLeft(90f);
//      robot.localise();
//      waitForIdle();
		if(doLoco) {
			robot.localise();
			waitForIdle();
		} else {
			robot.setPosition(new Position(30.48f,30.48f,0f));
		}
		robot.findBlocks();
		waitForIdle();

//		int press;
//		Colour colour = new Colour(colourPort);
//		for( ; ; ) {
//			System.out.print("press: ");
//            press = Button.waitForAnyPress();
//			if((press & Button.ID_ESCAPE) != 0) break;
//			System.out.println((int)(colour.getStyrofoamProbability() * 100f) + "% styrofoam");
//         System.out.println(colour.getColourValue() == Colour.Value.STYROFOAM);
//		}
	}


   private static void monitorForExit() {
      //spawn thread for exit
      Thread exitThread = new Thread() {
         public void run() {
            if (Button.waitForAnyPress() == Button.ID_ESCAPE)
   	         System.exit(0);
         } 
      };
      exitThread.start();
   }



   public static void waitForIdle() {
         while(robot.getStatus() != Robot.Status.IDLE || robot.getFindStatus() != Robot.FindStatus.IDLE) {
		}
   }

   /** unit tests for robot code, when you complete a function write a test method
   say what it's supposed to do and run it here, then test to make sure everything
   functions properly.
   localise: rotates but values are off
      test: rotates counterclockwise and updates odometer (update not implemented yet)
   turnTo/travelTo: works
      test:
         turn left, turn right
         travel to the first cross of vertical and horizontal lines
         face 90 (straight)
   findBlocks: not working -- in progress
   */
   public static void runTests() {
      robot.localise(); 
      waitForIdle();
      robot.turnTo(0f,250);
      waitForIdle();
      robot.turnTo(90f,250);
      waitForIdle();
      robot.turnTo(0f,250);
      waitForIdle();
      robot.travelTo(60.96f,60.96f,250,250);
      waitForIdle();
      robot.turnTo(90f,250);
      waitForIdle();
		robot.travelTo(30.48f, 30.48f,250,250);
//    robot.findBlocks();
//      waitForIdle();
      robot.stop();
      return;
   }
   /** quick test -- turns left 30 degrees and back, spin/travels to -5,-5 and returns */
   public static void runAbridgedTests() {
      robot.turnTo(30f,250);
      waitForIdle();
      robot.turnTo(0f,250);
      waitForIdle();
		robot.travelTo(-5f, -5f,250,250);
      waitForIdle();
      robot.travelTo(0f,0f,250,250);
      waitForIdle();
      robot.stop();
      return;
   }

}
