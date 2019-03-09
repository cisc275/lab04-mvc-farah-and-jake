//Jake Wise, Farah Aljishi
/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model {
	private int frameWidth;
	private int frameHeight;
	private int imageWidth;
	private int imageHeight;
	private int xLoc = 0;
	private int yLoc = 0;
	private final int xIncr = 8;
	private final int yIncr = 2;
	private int xFactor = 1;
	private int yFactor = 1;
	private Direction direct;
	
	public Model(int fW, int fH, int iW, int iH) {
		frameWidth = fW;
		frameHeight = fH;
		imageWidth = iW;
		imageHeight = iH;
	}
	
	public int getX() {
		return this.xLoc;
	}
	
	public int getY() {
		return this.yLoc;
	}
	
	public Direction getDirect() {
		return this.direct;
	}
	
	//Checks for boundary collision to determine whether to increment/decrement
	//the x and y positions. Also sets the direction which is based on the 
	//values used to make x and y increment or decrement.
	public void updateLocationAndDirection() {
		if(xLoc > frameWidth-imageWidth) {
    		xFactor = -1;
    	}
    	if(xLoc < 0) {
    		xFactor = 1;
    	}
    	
    	if(yLoc > frameHeight-imageHeight) {
    		yFactor = -1;
    	}
    	if(yLoc < 0) {
    		yFactor = 1;
    	}
    	
    	if(xFactor == 1 && yFactor == 1) {
    		direct = Direction.SOUTHEAST;
    	}
    	if(xFactor == 1 && yFactor == -1) {
    		direct = Direction.NORTHEAST;
    	}
    	if(xFactor == -1 && yFactor == 1) {
    		direct = Direction.SOUTHWEST;
    	}
    	if(xFactor == -1 && yFactor == -1) {
    		direct = Direction.NORTHWEST;
    	}
    	
    	xLoc+=(xIncr*xFactor);
    	
    	yLoc+=(yIncr*yFactor);
	}
	
	
	
	
}

