package Engine;

import GameObject.Rectangle;
import SpriteFont.SpriteFont;
import Utils.Colors;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/*
 * This is where the game loop process and render back buffer is setup
 */
public class GamePanel extends JPanel implements MouseListener{
	// loads Screens on to the JPanel
	// each screen has its own update and draw methods defined to handle a "section" of the game.
	private ScreenManager screenManager;

	// used to draw graphics to the panel
	private GraphicsHandler graphicsHandler;

	private boolean isGamePaused = false;
	private SpriteFont pauseLabel;
	private KeyLocker keyLocker = new KeyLocker();
	private final Key pauseKey = Key.P;
	private Thread gameLoopProcess;

	private Key showFPSKey = Key.G;
	private SpriteFont fpsDisplayLabel;
	private boolean showFPS = false;
	private int currentFPS;
	private boolean doPaint;

	public static boolean theSwitch = false;
	public static Point lastMousePosition;

	// The JPanel and various important class instances are setup here
	public GamePanel() {
		super();
		this.setDoubleBuffered(true);

		// attaches Keyboard class's keyListener to this JPanel
		this.addKeyListener(Keyboard.getKeyListener());
		addMouseListener(this);

		graphicsHandler = new GraphicsHandler();

		screenManager = new ScreenManager();

		pauseLabel = new SpriteFont("PAUSE", 365, 280, "Arial", 24, Color.white);
		pauseLabel.setOutlineColor(Color.black);
		pauseLabel.setOutlineThickness(2.0f);

		fpsDisplayLabel = new SpriteFont("FPS", 4, 3, "Arial", 12, Color.black);

		currentFPS = Config.TARGET_FPS;

		// this game loop code will run in a separate thread from the rest of the program
		// will continually update the game's logic and repaint the game's graphics
		GameLoop gameLoop = new GameLoop(this);
		gameLoopProcess = new Thread(gameLoop.getGameLoopProcess());
	}

	// this is called later after instantiation, and will initialize screenManager
	public void setupGame() {
		setBackground(Colors.CORNFLOWER_BLUE);
		screenManager.initialize(new Rectangle(getX(), getY(), getWidth(), getHeight()));
	}

	// this starts the timer (the game loop is started here)
	public void startGame() {
		gameLoopProcess.start();
	}

	public ScreenManager getScreenManager() {
		return screenManager;
	}

	public void setCurrentFPS(int currentFPS) {
		this.currentFPS = currentFPS;
	}

	public void setDoPaint(boolean doPaint) {
		this.doPaint = doPaint;
	}

	public void update() {
		updatePauseState();
		updateShowFPSState();

		if (!isGamePaused) {
			screenManager.update();
		}
	}

	private void updatePauseState() {
		if (Keyboard.isKeyDown(pauseKey) && !keyLocker.isKeyLocked(pauseKey)) {
			isGamePaused = !isGamePaused;
			keyLocker.lockKey(pauseKey);
		}

		if (Keyboard.isKeyUp(pauseKey)) {
			keyLocker.unlockKey(pauseKey);
		}
	}

	private void updateShowFPSState() {
		if (Keyboard.isKeyDown(showFPSKey) && !keyLocker.isKeyLocked(showFPSKey)) {
			showFPS = !showFPS;
			keyLocker.lockKey(showFPSKey);
		}

		if (Keyboard.isKeyUp(showFPSKey)) {
			keyLocker.unlockKey(showFPSKey);
		}

		fpsDisplayLabel.setText("FPS: " + currentFPS);
	}

	public void draw() {			
		// draw current game state
		screenManager.draw(graphicsHandler);

		// if game is paused, draw pause gfx over Screen gfx
		if (isGamePaused) {
			pauseLabel.draw(graphicsHandler);
			graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(0, 0, 0, 100));
		}

		if (showFPS) {
			fpsDisplayLabel.draw(graphicsHandler);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (doPaint) {
			// every repaint call will schedule this method to be called
			// when called, it will setup the graphics handler and then call this class's draw method
			graphicsHandler.setGraphics((Graphics2D) g);
			draw();
		}
	}



	public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed "+ e.getX() + ", " + e.getY() + " (# of clicks: " + e.getClickCount() + ")");
		lastMousePosition = e.getPoint();
		theSwitch = !theSwitch;
    }
    
    public void mouseReleased(MouseEvent e) {
        //System.out.println("Mouse released (# of clicks: " + e.getClickCount() + ")");
		theSwitch = !theSwitch;
    }
    
    public void mouseEntered(MouseEvent e) {
        //System.out.println("Mouse entered");
    }
    
    public void mouseExited(MouseEvent e) {
        //System.out.println("Mouse exited");
    }
    
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Mouse clicked (# of clicks: " + e.getClickCount() + ")");
    }

	public static boolean isMouseClicked() {
		return theSwitch;
	}

	public static Point getMousePositionPoint() {
		return lastMousePosition;
	}
}
