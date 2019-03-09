import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Jake Wise Farah Aljishi
/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
public class View extends JPanel{
	final int frameCount = 10;
    int picNum = 0;
    int imageNum = 0;
    static int xloc;
    static int yloc;
    static Direction direct;
    BufferedImage[] pics;
    BufferedImage[][] images;
	final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    static JFrame frame = new JFrame();
    
    
    
    
    //Builds the JFrame and starts the animation.
    public static void main(String[] args) {
    	Controller control = new Controller();
    	frame.getContentPane().add(new View());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	
    	
    	control.start();
    }
    
    //Draw image based on the current x,y location and image, 
    //which is determined by the direction
    public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	
    	if(direct == Direction.SOUTHEAST) {
    		imageNum = 3;
    	}
    	if(direct == Direction.NORTHEAST) {
    		imageNum = 1;
    	}
    	if(direct == Direction.SOUTHWEST) {
    		imageNum = 5;
    	}
    	if(direct == Direction.NORTHWEST) {
    		imageNum = 7;
    	}

    	g.drawImage(images[imageNum][picNum], xloc, yloc, Color.gray, this);
    }
	
    //Constructor loads all the images into the array.
	public View() {
		pics = new BufferedImage[8];
    	pics[0] = createImage("images/orc/orc_forward_north.png");
    	pics[1] = createImage("images/orc/orc_forward_northeast.png");
    	pics[2] = createImage("images/orc/orc_forward_east.png");
    	pics[3] = createImage("images/orc/orc_forward_southeast.png");
    	pics[4] = createImage("images/orc/orc_forward_south.png");
    	pics[5] = createImage("images/orc/orc_forward_southwest.png");
    	pics[6] = createImage("images/orc/orc_forward_west.png");
    	pics[7] = createImage("images/orc/orc_forward_northwest.png");
    	images = new BufferedImage[8][10];
    	for(int j = 0; j < 8; j++) {
    		for(int i = 0; i < frameCount; i++)
    			images[j][i] = pics[j].getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	}
	}
	
	//Obtains the x,y location and direction and sets it to the local variables 
	//in View. Repaints the current image.
	public void update(int x, int y, Direction d) {
		xloc = x;
		yloc = y;
		direct = d;
		frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getWidth() {
		return frameWidth;
	}
	
	public int getHeight() {
		return frameHeight;
	}
	
	public int getImageWidth() {
		return imgWidth;
	}
	
	public int getImageHeight() {
		return imgHeight;
	}
	
	//Read image from file and return
    private BufferedImage createImage(String name){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(name));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    }
}
