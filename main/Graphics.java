package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Graphics extends JPanel implements ActionListener {
	
	//Variables used
	public String state; 
	public String colorscheme;
	public BufferedImage MenuBG, GameOverBG, Title, Grass, Meta;
	public Timer t = new Timer(Game.delay, this);
	private Snake s;
	private Food f;
	private Game game;
	public String difficulty = "EASY"; //DEFAULT DIFFICULTY IS EASY

	public Graphics(Game g) {
		
		state = "START"; // WHEN GRAPHICS IS CALLED, THE GAME IS SET TO START
		colorscheme = "FLAT"; // DEFAULT COLORSCHEME IS FLAT
		
		game = g;
		s = g.getPlayer();
		f = g.getFood();
		
		//add a KeyListener
		this.addKeyListener(g);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}
	
	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		 
		//DRAW START MENU BACKGROUND  
				try {
					MenuBG = ImageIO.read(getClass().getResourceAsStream("/images/Menu_imagebg.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
				g.drawImage(MenuBG,0,0,Game.width*Game.dimension, Game.height*Game.dimension, null);
				//SNAKE GAME TITLE TEXT
				try {
					Title = ImageIO.read(getClass().getResourceAsStream("/images/snakegame-title.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
				g.drawImage(Title,380,20,180,130, null);
		
		if(state == "START") { //IF GAME IS IN START MENU
				
			g2d.setColor(new Color(34,139,34));
			Font fnt3 = new Font("arcade", Font.BOLD, 20);
			g2d.setFont(fnt3);
			
			//INSTRUCTIONS
			g2d.drawString("Press S To Start Game", Game.width/4 * Game.dimension - 120, Game.height/2 * Game.dimension - 20);
			g2d.drawString("Press H for Help", Game.width/4 * Game.dimension - 120, Game.height/2 * Game.dimension + 20);
			g2d.drawString("Press Q to Quit", Game.width/4 * Game.dimension - 120, Game.height/2 * Game.dimension + 60);
			
		}
		else if (state == "HELP") { //IF GAME IS IN HELP
			
		// DRAW HELP BACKGROUND
			try {
				Meta = ImageIO.read(getClass().getResourceAsStream("/images/metabg.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g.drawImage(Meta,0,0,Game.width*Game.dimension, Game.height*Game.dimension, null);
			
			g2d.setColor(new Color(0,255,0));
			Font fnt4 = new Font("arcade", Font.BOLD, 20);
			g2d.setFont(fnt4);
			
		//DRAW INSTRUCTIONS
			g2d.drawString("Press H to return", Game.width, Game.height + 10);
			g2d.drawString("Press C to choose colors", Game.width, Game.height + 40);
			g2d.drawString("Press D to choose difficulty", Game.width, Game.height + 70);
			g2d.drawString("Instructions", Game.width, Game.height + 150);
			g2d.drawString("Press UP arrow key to move up", Game.width, Game.height + 200);
			g2d.drawString("Press DOWN arrow key to move down", Game.width, Game.height + 250);
			g2d.drawString("Press LEFT arrow key to move left", Game.width, Game.height + 300);
			g2d.drawString("Press RIGHT arrow key to move right", Game.width, Game.height + 350);
			g2d.drawString("Eat as many apples as you can,", Game.width, Game.height + 400);
			g2d.drawString("Snake dies when colliding with wall.", Game.width, Game.height + 450);
			
			
		}
		else if(state == "DIFFICULTY") { //IF GAME IS IN DIFFICULTY SELECTION
			//DRAW DIFFICULTY SELECTION BACKGROUND
			try {
				Meta = ImageIO.read(getClass().getResourceAsStream("/images/metabg.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g.drawImage(Meta,0,0,Game.width*Game.dimension, Game.height*Game.dimension, null);
			
			g2d.setColor(new Color(0,255,0));
			Font fnt5 = new Font("arcade", Font.BOLD, 20);
			g2d.setFont(fnt5);
			
			//DRAW DIFFICULTY INSTRUCTIONS
			g2d.drawString("Press D to return", Game.width, Game.height/2 * Game.dimension-240);
			g2d.drawString("Press 1 for EASY", Game.width, Game.height + 150);
			g2d.drawString("Press 2 for MEDIUM", Game.width, Game.height + 200);
			g2d.drawString("Press 3 for HARD", Game.width, Game.height + 250);
			
			//DRAW SELECTION BUTTONS, NOTHING IS SELECTED YET
			g2d.setColor(Color.white);
			g2d.fillOval(350, 150, 40, 40);
			g2d.setColor(Color.white);
			g2d.fillOval(350, 200, 40, 40);	
			g2d.setColor(Color.white);
			g2d.fillOval(350, 250, 40, 40);
		}
		else if(state == "EASY") { //IF GAME IS IN EASY SELECTED
			//DRAW DIFFICULTY SELECTION BACKGROUND
			try {
				Meta = ImageIO.read(getClass().getResourceAsStream("/images/metabg.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g.drawImage(Meta,0,0,Game.width*Game.dimension, Game.height*Game.dimension, null);
			
			g2d.setColor(new Color(0,255,0));
			Font fnt5 = new Font("arcade", Font.BOLD, 20);
			g2d.setFont(fnt5);
			
			//DRAW DIFFICULTY INSTRUCTIONS
			g2d.drawString("Press D to return", Game.width, Game.height/2 * Game.dimension-240);
			g2d.drawString("Press 1 for EASY", Game.width, Game.height + 150);
			g2d.drawString("Press 2 for MEDIUM", Game.width, Game.height + 200);
			g2d.drawString("Press 3 for HARD", Game.width, Game.height + 250);
			
			//DRAW SELECTION BUTTONS, EASY SELECTED
			g2d.setColor(Color.green); // -> EASY IS SELECTED
			g2d.fillOval(350, 150, 40, 40);
			g2d.setColor(Color.white);
			g2d.fillOval(350, 200, 40, 40);	
			g2d.setColor(Color.white);
			g2d.fillOval(350, 250, 40, 40);	
		}
		else if(state == "MEDIUM") { //IF GAME IS IN MEDIUM SELECTED
			//DRAW DIFFICULTY SELECTION BACKGROUND
			try {
				Meta = ImageIO.read(getClass().getResourceAsStream("/images/metabg.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g.drawImage(Meta,0,0,Game.width*Game.dimension, Game.height*Game.dimension, null);
			
			g2d.setColor(new Color(0,255,0));
			Font fnt5 = new Font("arcade", Font.BOLD, 20);
			g2d.setFont(fnt5);
			
			//DRAW DIFFICULTY INSTRUCTIONS
			g2d.drawString("Press D to return", Game.width, Game.height/2 * Game.dimension-240);
			g2d.drawString("Press 1 for EASY", Game.width, Game.height + 150);
			g2d.drawString("Press 2 for MEDIUM", Game.width, Game.height + 200);
			g2d.drawString("Press 3 for HARD", Game.width, Game.height + 250);
			
			//DRAW SELECTION BUTTONS, MEDIUM SELECTED
			g2d.setColor(Color.white);
			g2d.fillOval(350, 150, 40, 40);
			g2d.setColor(Color.green); // -> MEDIUM IS SELECTED
			g2d.fillOval(350, 200, 40, 40);	
			g2d.setColor(Color.white);
			g2d.fillOval(350, 250, 40, 40);	
		}
		else if(state == "HARD") { //IF GAME IS IN HARD SELECTED
			//DRAW DIFFICULTY SELECTION BACKGROUND
			try {
				Meta = ImageIO.read(getClass().getResourceAsStream("/images/metabg.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g.drawImage(Meta,0,0,Game.width*Game.dimension, Game.height*Game.dimension, null);
			
			
			g2d.setColor(new Color(0,255,0));
			Font fnt5 = new Font("arcade", Font.BOLD, 20);
			g2d.setFont(fnt5);
			
			//DRAW DIFFICULTY INSTRUCTIONS
			g2d.drawString("Press D to return", Game.width, Game.height/2 * Game.dimension-240);
			g2d.drawString("Press 1 for EASY", Game.width, Game.height + 150);
			g2d.drawString("Press 2 for MEDIUM", Game.width, Game.height + 200);
			g2d.drawString("Press 3 for HARD", Game.width, Game.height + 250);
			
			//DRAW SELECTION BUTTONS, HARD SELECTED
			g2d.setColor(Color.white);
			g2d.fillOval(350, 150, 40, 40);
			g2d.setColor(Color.white);
			g2d.fillOval(350, 200, 40, 40);	
			g2d.setColor(Color.green); // -> HARD IS SELECTED
			g2d.fillOval(350, 250, 40, 40);	
		}
		
		else if(state == "COLORSELECTION") { //IF GAME IS IN COLOR SELECTION
			//DRAW COLOR SELECT BACKGROUND
			try {
				Meta = ImageIO.read(getClass().getResourceAsStream("/images/metabg.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g.drawImage(Meta,0,0,Game.width*Game.dimension, Game.height*Game.dimension, null);
			
			//DRAW COLOR CHOICE INSTRUCTIONS
			g2d.setColor(new Color(0,255,0));
			Font fnt5 = new Font("arcade", Font.BOLD, 20);
			g2d.setFont(fnt5);
			g2d.drawString("Press R to randomise colors", Game.width, Game.height/2 * Game.dimension - 100);
			g2d.drawString("Press F to have flat colors", Game.width, Game.height/2 * Game.dimension - 60);
			g2d.drawString("Press C to return", Game.width, Game.height/2 * Game.dimension-240);
			
			//DRAW COLOR SELECTION BUTTONS, NOTHING IS SELECTED
			g2d.setColor(Color.white);
			g2d.fillOval(350, 170, 40, 40);
			g2d.setColor(Color.white);
			g2d.fillOval(350, 220, 40, 40);
		}
		
		else if(state == "RANDOM") { //IF GAME IS IN RANDOM COLOR SELECTED
			//DRAW COLOR SELECT BACKGROUND
			try {
				Meta = ImageIO.read(getClass().getResourceAsStream("/images/metabg.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g.drawImage(Meta,0,0,Game.width*Game.dimension, Game.height*Game.dimension, null);
			
			//DRAW COLOR CHOICE INSTRUCTIONS
			g2d.setColor(new Color(0,255,0));
			Font fnt5 = new Font("arcade", Font.BOLD, 20);
			g2d.setFont(fnt5);
			g2d.drawString("Press R to randomise colors", Game.width, Game.height/2 * Game.dimension - 100);
			g2d.drawString("Press F to have flat colors", Game.width, Game.height/2 * Game.dimension - 60);
			g2d.drawString("Press C to return", Game.width, Game.height/2 * Game.dimension-240);
			
			//DRAW COLOR SELECTION BUTTONS, RANDOM COLOR SELECTED
			g2d.setColor(Color.green); // -> RANDOM COLOR SELECTED
			g2d.fillOval(350, 170, 40, 40);
			g2d.setColor(Color.white);
			g2d.fillOval(350, 220, 40, 40);
		}
		
		else if(state == "FLAT") { //IF GAME IS IN FLAT COLOR SELECTED
			//DRAW COLOR SELECT BACKGROUND
			try {
				Meta = ImageIO.read(getClass().getResourceAsStream("/images/metabg.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g.drawImage(Meta,0,0,Game.width*Game.dimension, Game.height*Game.dimension, null);
			
			//DRAW COLOR CHOICE INSTRUCTIONS
			g2d.setColor(new Color(0,255,0));
			Font fnt5 = new Font("arcade", Font.BOLD, 20);
			g2d.setFont(fnt5);
			g2d.drawString("Press R to randomise colors", Game.width, Game.height/2 * Game.dimension - 100);
			g2d.drawString("Press F to have flat colors", Game.width, Game.height/2 * Game.dimension - 60);
			g2d.drawString("Press C to return", Game.width, Game.height/2 * Game.dimension-240);
			
			//DRAW COLOR SELECTION BUTTONS, FLAT COLOR SELECTED
			g2d.setColor(Color.white);
			g2d.fillOval(350, 170, 40, 40);
			g2d.setColor(Color.green); // -> FLAT COLOR SELECTED
			g2d.fillOval(350, 220, 40, 40);	
		}
		
		else if(state == "RUNNING") { //IF GAME IS RUNNING
		
		//DRAW GAME RUNNING BACKGROUND
			try {
				Grass = ImageIO.read(getClass().getResourceAsStream("/images/grassbg.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g.drawImage(Grass,0,0,Game.width*Game.dimension, Game.height*Game.dimension, null);
		
		//DRAW SCORE
			g2d.setColor(Color.red);
			Font fnt0 = new Font("arcade", Font.BOLD, 25);
			g2d.setFont(fnt0);
			g2d.drawString("SCORE: " + (s.getBody().size() - Game.bodyParts), Game.width/2*Game.dimension + 120, Game.height/2*Game.dimension - 250);
			
		//DRAW DIFFICULTY
			if(Game.delay==150) {
				difficulty = "EASY";
			}else if(Game.delay==100) {
				difficulty = "MEDIUM";
			}else if(Game.delay==75) {
				difficulty = "HARD";
			}
			g2d.drawString("Difficulty :" + difficulty, Game.width/2*Game.dimension + 70, Game.height/2 * Game.dimension - 200);
			
		//DRAW APPLE
			g2d.setColor(Color.red);
			g2d.fillOval(f.getFoodX() * Game.dimension, f.getFoodY() * Game.dimension, Game.dimension, Game.dimension); // CALLS FOOD METHODS TO GET LOCATION
			
		//DRAW SNAKE RECTANGLES FLAT COLOR
			if(colorscheme == "FLAT") {
				
			g2d.setColor(new Color(Game.Rcolor, Game.Gcolor, Game.Bcolor));
			for(Rectangle r : s.getBody()) {
				g2d.fill(r);
			} 
			} else { // COLORSCHEME == "RANDOM"
			
		//DRAW SNAKE ROUND RECTANGLES RANDOM COLOR
			Random random = new Random();
			for(int i = 0; i < Game.bodyParts; i++) { // For loop to iterate each body part of snake
				if(i%2 == 0) {
					g2d.setColor(new Color(Game.Rcolor, Game.Gcolor, Game.Bcolor));
					g2d.fillRoundRect(s.getX(i), s.getY(i), Game.dimension, Game.dimension, 5, 20);
				}
				else {
			     //g.setColor(new Color(0,100,0)); // Alternating skin colours of snake
				 //g2d.setColor(new Color(Game.Rcolor, Game.Gcolor, Game.Bcolor));
					g2d.setColor(new Color( random.nextInt(255),random.nextInt(255),random.nextInt(255))); // Randomly changing skin colours of snake
					g2d.fillRoundRect(s.getX(i), s.getY(i), Game.dimension, Game.dimension, 5, 20);
				    }
				 }
			}
			
			//DRAW PAUSE INSTRUCTIONS
			g2d.setColor(Color.white);
			Font fnt5 = new Font("arcade", Font.BOLD, 15);
			g2d.setFont(fnt5);
			g2d.drawString("Press SPACE to pause", Game.width/2*Game.dimension + 100, Game.height/2 * Game.dimension - 180);
			
			//DRAW BOMB
			if (difficulty == "HARD") {
				g2d.setColor(Color.black);
				g2d.fillOval(f.getBombX() * Game.dimension, f.getBombY() * Game.dimension, Game.dimension, Game.dimension); // CALLS FOOD METHODS TO GET LOCATION
			} else {
				
			}
			
		} else if (state == "PAUSE") { //GAME IS PAUSED
			
			//DRAW PAUSED GAME BACKGROUND. SAME AS RUNNING GAME BACKGROUND
			try {
				Grass = ImageIO.read(getClass().getResourceAsStream("/images/grassbg.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g.drawImage(Grass,0,0,Game.width*Game.dimension, Game.height*Game.dimension, null);
		
			//DRAW SCORE. SAME AS RUNNING GAME
			g2d.setColor(Color.red);
			Font fnt0 = new Font("arcade", Font.BOLD, 40);
			g2d.setFont(fnt0);
			g2d.drawString("SCORE: " + (s.getBody().size() - Game.bodyParts), Game.width/2*Game.dimension + 90, Game.height/2*Game.dimension - 250);
					
			//DRAW APPLE. SAME LOCATION AS RUNNING GAME
			g2d.setColor(Color.red);
			g2d.fillOval(f.getFoodX() * Game.dimension, f.getFoodY() * Game.dimension, Game.dimension, Game.dimension);
			
			//DRAW BOMB. SAME LOCATION AS RUNNING GAME
			if (difficulty == "HARD") {
				g2d.setColor(Color.black);
				g2d.fillOval(f.getBombX() * Game.dimension, f.getBombY() * Game.dimension, Game.dimension, Game.dimension); // CALLS FOOD METHODS TO GET LOCATION
			} else {
				
			}
		
			//DRAW SNAKE RECTANGLES FLAT COLOR. SAME AS RUNNING GAME 
			if(colorscheme == "FLAT") {
				
			g2d.setColor(new Color(Game.Rcolor, Game.Gcolor, Game.Bcolor));
			for(Rectangle r : s.getBody()) {
				g2d.fill(r);
			} 
			} else {
			
			//DRAW SNAKE ROUND RECTANGLES RANDOM COLOR. SAME AS RUNNING GAME
			Random random = new Random();
				for(int i = 0; i < Game.bodyParts; i++) { // For loop to iterate each body part of snake
					if(i%2 == 0) {
						g2d.setColor(new Color(Game.Rcolor, Game.Gcolor, Game.Bcolor));
						g2d.fillRoundRect(s.getX(i), s.getY(i), Game.dimension, Game.dimension, 5, 20);
				}
				else {
					//g.setColor(new Color(0,100,0)); // ALTERNATING SKIN COLORS OF SNAKE
					g2d.setColor(new Color( random.nextInt(255),random.nextInt(255),random.nextInt(255))); // RANDOM COLOR CHANGE
					g2d.fillRoundRect(s.getX(i), s.getY(i), Game.dimension, Game.dimension, 5, 20);
				    }
				 }
			}
			
			//DRAW PAUSE TEXT
			g2d.setColor(Color.white);
			Font fnt1 = new Font("arcade", Font.BOLD, 40);
			g2d.setFont(fnt1);
			g2d.drawString("GAME IS PAUSED", Game.width/2 * Game.dimension - 90, Game.height/2 * Game.dimension);
				
			//DRAW INSTRUCTIONS TO RESUME
			g2d.setColor(Color.white);
			Font fnt2 = new Font("arcade", Font.BOLD, 30);
			g2d.setFont(fnt2);
			g2d.drawString("PRESS R TO RESUME", Game.width/2 * Game.dimension - 200, Game.height/2 * Game.dimension +60);
			g2d.drawString("PRESS Q TO QUIT", Game.width/2 * Game.dimension - 200, Game.height/2 * Game.dimension +100);
				
		} else if (state == "END") { //GAME IS IN GAMEOVER STATE
			//DRAW GAMEOVER BACKGROUND
			try {
				GameOverBG = ImageIO.read(getClass().getResourceAsStream("/Images/GameOver_imagebg.jpeg"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			g.drawImage(GameOverBG,0,0,Game.width*Game.dimension, Game.height*Game.dimension, null);
			
			//DRAW REPLAY INSTRUCTIONS 
			g2d.setColor(Color.red);
			g2d.setFont(new Font("Monospaced", Font.BOLD, 30));
			FontMetrics metrics1 = getFontMetrics(g.getFont());
			
			//DRAW SCORE
			g2d.drawString("Score: " + (s.getBody().size() - Game.bodyParts),330,450);
			g2d.drawString("Press 'R' to replay game",150,480);
			g2d.drawString("Press 'Q' to quit game",150,510);
			
			//DRAW GAMEOVER TEXT
			g.setColor(Color.red);
			g.setFont(new Font("Arcade", Font.BOLD, 75));
			FontMetrics metrics2 = getFontMetrics(g.getFont());
			g.drawString("GAME OVER",50,130);	// Centre of screen
			
			repaint();
			
		} else if(state == "QUIT") {
			// CHOSEN TO QUIT
			g2d.dispose();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		repaint();
		
		game.updateGame();
		
	}

}
