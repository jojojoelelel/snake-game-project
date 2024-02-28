package main;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends JFrame implements KeyListener{

	// Objects of the game
	private Snake player;
	private Food food;
	private Graphics graphics;
	private Sound sound = new Sound(); 
	private JFrame window;
	
	/* Dimensions of the game */
	public static final int width = 30; //600
	public static final int height = 30; //600
	public static final int dimension = 20;
	public static final int bodyParts = 6;
	public static final int Rcolor = 50; // Choose a value from 0 to 255
	public static final int Gcolor = 50; // Choose a value from 0 to 255
	public static final int Bcolor = 50; // Choose a value from 0 to 255
	public static int delay;
	
	
	public Game() {
		
		// Create window
		window = new JFrame();
	 
		// Call constructors
		player = new Snake();
		food = new Food(player);
		graphics = new Graphics(this);
		
		// Set Window options
		window.setIconImage(new ImageIcon(getClass().getResource("/images/snake-icon.png")).getImage());
		window.add(graphics);
		window.setTitle("Snake Game(Ignatius & Joel");
		window.setSize(width*dimension +2, height*dimension +4);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		// Start the misc. of the game (music and graphics timer)
		musicControl();
		graphics.t.start();
		
	}
	
	// To start the game
	
	public void startGame() {
		graphics.state = "RUNNING"; // SETS STATE TO RUNNING
		player.move = "RIGHT"; // INITIAL DIRECTION OF MOVEMENT
	}
	
	// To restart the game
	public void restartGame() {
		stopMusic();
		window.setVisible(false);b
		new Game();
	}
	
	// To quit the game
	public void quitGame() {
		window.dispose();
		System.exit(1);
		graphics.state = "QUIT"; // SETS STATE TO QUIT
	}
	
	// To update the game
	public void updateGame() {
		if (graphics.state == "RUNNING") {
			if (check_food_collision()) { // APPLE IS EATEN
				playSE(0); // APPLE EATEN SOUND EFFECT
				player.grow(); // GROWS BY 1 RECTANGLE
				food.newApple(player); // SPAWNS A NEW APPLE
			}
			else if (check_wall_collision() || check_self_collision() || check_bomb_collision()) {
				stopMusic();
				graphics.state = "END"; // SETS STATE TO GAMEOVER
				musicControl(); // PLAYS MUSIC
			}
			else {
				player.move(); // KEEPS MOVING THE PLAYER
			}
		} 
	}
	
	// Controls the music. Which to play 
	public void musicControl() {
		if(graphics.state == "START") {
			playMusic(4);
		} else if (graphics.state == "RUNNING") {
			playMusic(1);
		} else if (graphics.state == "END") {
			playSE(2);
			playMusic(5);
		} else {
			stopMusic();
		}
	}
	
	private boolean check_wall_collision() { // CHECK BORDER COLLISIONS
		if(player.getX() <= 0 || player.getX() >= width * dimension || player.getY() <= 0 || player.getY() >= height * dimension) {
			stopMusic(); 
			return true;
		} 
		return false;
	}

	private boolean check_food_collision() { // CHECK IF APPLE EATEN
		if(player.getX() == food.getFoodX() * dimension && player.getY() == food.getFoodY() * dimension) {
			return true;
		} 
		return false;
	}
	
	private boolean check_bomb_collision() { // CHECK IF BOMB COLLIDED
		if(player.getX() == food.getBombX() * dimension && player.getY() == food.getBombY() * dimension) {
			return true;
		} 
		return false;
	}
	
	private boolean check_self_collision() { // CHECK BODY COLLISIONS
		for(int i = 1; i < player.getBody().size(); i++) {
			if(player.getX() == player.getBody().get(i).x && player.getY() == player.getBody().get(i).y ) {
				stopMusic(); 
				return true;
			} 
		}
		return false;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// NOT NEEDED
	}

	// Keyboard inputs based on different states
	@Override
	public void keyPressed(KeyEvent e) {
		// INPUT
		int keyCode = e.getKeyCode();
		
		if (graphics.state == "START"){ // IF GAME IS IN START MENU SCREEN
			if(keyCode == KeyEvent.VK_H) {
				//H TO HELP
				graphics.state = "HELP"; // SETS STATE TO HELP
				playSE(6); // COUGH
			}
			
			else if(keyCode == KeyEvent.VK_S) {
				//S TO START GAME
				stopMusic(); // STOPS MUSIC
				this.startGame();
				playSE(6); // COUGH
				musicControl();
			}
			else if(keyCode == KeyEvent.VK_Q) {
				//Q TO QUIT GAME
				quitGame(); // EXITS SYSTEM
			}
			}
		else if(graphics.state == "HELP") { //IF GAME IS IN HELP MENU 
			if(keyCode == KeyEvent.VK_H) {
				//H TO RETURN
				graphics.state = "START"; // SETS STATE BACK TO START (START MENU)
				playSE(6); // COUGH
			} 
			else if(keyCode == KeyEvent.VK_C) {
				//C TO CHOOSE COLORS
				graphics.state = "COLORSELECTION"; // SETS STATE TO COLORSELECTION (COLOR SELECTION)
				playSE(6); // COUGH
			}
			else if(keyCode == KeyEvent.VK_D) {
				//D TO CHOOSE DIFFICULTY
				graphics.state = "DIFFICULTY"; // SETS STATE TO DIFFICULTY (DIFFICULTY SELECTION)
				playSE(6); // COUGH
			}
		}
		else if(graphics.state == "DIFFICULTY") { //IF GAME IS IN DIFFICULTY SELECTION
			if(keyCode == KeyEvent.VK_D) {
				//D TO RETURN
				graphics.state = "HELP"; // SETS STATE BACK TO HELP
				restartGame();
				playSE(6); // COUGH
			} else if(keyCode == KeyEvent.VK_1) {
				//1 FOR EASY
				graphics.state = "EASY"; // SETS STATE TO EASY
				delay = 150; // SETS DELAY
				playSE(6); // COUGH
			} else if(keyCode == KeyEvent.VK_2) {
				//2 FOR MEDIUM
				graphics.state = "MEDIUM"; // SETS STATE TO MEDIUM
				delay = 100; // SETS DELAY
				playSE(6); // COUGH
			} else if(keyCode == KeyEvent.VK_3) {
				//3 FOR HARD
				graphics.state = "HARD"; // SETS STATE TO HARD
				delay = 75; // SETS DELAY
				playSE(6); // COUGH
			}
		}
		else if(graphics.state == "EASY") { //IF GAME IS EASY SELECTED. CANNOT SELECT EASY AGAIN
			if(keyCode == KeyEvent.VK_D) {
				//D TO RETURN
				graphics.state = "HELP";
				restartGame();
				playSE(6); // COUGH
			} else if(keyCode == KeyEvent.VK_2) {
				//2 FOR MEDIUM
				graphics.state = "MEDIUM";
				delay = 100;
				playSE(6); // COUGH
			} else if(keyCode == KeyEvent.VK_3) {
				//3 FOR HARD
				graphics.state = "HARD";
				delay = 75;
				playSE(6); // COUGH
			}
		}
		else if(graphics.state == "MEDIUM") { //IF GAME IS MEDIUM SELECTED. CANNOT SELECT MEDIUM AGAIN
			if(keyCode == KeyEvent.VK_D) {
				//D TO RETURN
				graphics.state = "HELP"; // SETS STATE BACK TO HELP
				playSE(6); // COUGH
				restartGame();
				
			} else if(keyCode == KeyEvent.VK_1) {
				//1 FOR EASY
				graphics.state = "EASY"; // SETS STATE TO EASY
				delay = 150; // SETS DELAY
				playSE(6); // COUGH
			} else if(keyCode == KeyEvent.VK_3) {
				//3 FOR HARD
				graphics.state = "HARD"; // SETS STATE TO HARD
				delay = 75; // SETS DELAY
				playSE(6); // COUGH
			}
		}
		else if(graphics.state == "HARD") { //IF GAME IS HARD SELECTED. CANNOT SELECT HARD AGAIN
			if(keyCode == KeyEvent.VK_D) {
				//D TO RETURN
				graphics.state = "HELP"; // SETS STATE BACK TO HELP
				playSE(6); // COUGH
				restartGame();
			} else if(keyCode == KeyEvent.VK_1) {
				//1 FOR EASY
				graphics.state = "EASY"; // SETS STATE TO EASY
				delay = 150; // SETS DELAY
				playSE(6); // COUGH
			} else if(keyCode == KeyEvent.VK_2) {
				//2 FOR MEDIUM
				graphics.state = "MEDIUM"; // SETS STATE TO MEDIUM
				delay = 100; // SETS DELAY
				playSE(6); // COUGH
			} 
		}
		else if(graphics.state == "COLORSELECTION") { //IF GAME IS IN COLOR SELECTION
			if(keyCode == KeyEvent.VK_F) {
				//FLAT COLOR SELECTED
				graphics.state = "FLAT"; // SETS STATE TO FLAT
				graphics.colorscheme = "FLAT"; // SETS COLOR SHCEME TO FLAT
				playSE(6); // COUGH
			} else if(keyCode == KeyEvent.VK_R) {
				//RANDOM COLOR SELECTED
				graphics.state = "RANDOM"; // SETS STATE TO RANDOM
				graphics.colorscheme = "RANDOM"; // SETS COLOR SCHEME TO RANDOM
				playSE(6); // COUGH
			} else if(keyCode == KeyEvent.VK_C) {
				//C TO RETURN
				graphics.state = "HELP"; // SETS STATE TO HELP
				playSE(6); // COUGH
			}
		}
		else if(graphics.state == "RANDOM") { //IF GAME IS IN RANDOM COLOR SELECTED. CANNOT SELECT RANDOM COLOR AGAIN
			if(keyCode == KeyEvent.VK_F) {
				//FLAT COLOR SELECTED
				graphics.state = "FLAT"; // SETS STATE TO FLAT
				graphics.colorscheme = "FLAT"; // SETS COLOR SCHEME TO FLAT
				playSE(6); // COUGH
			} else if(keyCode == KeyEvent.VK_C) {
				//C TO RETURN
				graphics.state = "HELP"; // SETS STATE BACK TO HELP
				playSE(6); // COUGH
			}
		}
		else if(graphics.state == "FLAT") { //IF GAME IS IN FLAT COLOR SELECTED. CANNOT SELECT FLAT COLOR AGAIN
			if(keyCode == KeyEvent.VK_R) {
				//RANDOM COLOR SELECTED
				graphics.state = "RANDOM"; // SETS STATE TO RANDOM
				graphics.colorscheme = "RANDOM"; // SETS COLOR SCHEME TO RANDOM
				playSE(6); // COUGH
			} else if(keyCode == KeyEvent.VK_C) {
				//C TO RETURN
				graphics.state = "HELP"; // SETS STATE BACK TO HELP
				playSE(6); // COUGH
			}
		}
		else if(graphics.state == "RUNNING") { //IF GAME IS RUNNING. CONTROLS FOR MOVEMENT
			if(keyCode == KeyEvent.VK_UP) {
				//MOVE UP	
				player.up(); // CALL FUNCTION FROM SNAKE
			}
			
			else if(keyCode == KeyEvent.VK_DOWN) {
				//MOVE DOWN	
				player.down(); // CALL FUNCTION FROM SNAKE
			}
			
			else if(keyCode == KeyEvent.VK_LEFT) {
				//MOVE LEFT
				player.left(); // CALL FUNCTION FROM SNAKE
			}
			
			else if(keyCode == KeyEvent.VK_RIGHT) {
				//MOVE RIGHT
				player.right(); // CALL FUNCTION FROM SNAKE
			}		
			else if(keyCode == KeyEvent.VK_SPACE) {
				//SPACE TO PAUSE
				if(graphics.state != "PAUSE") { //CAN ONLY PRESS PAUSE IF GAME IS NOT IN PAUSE
				player.move = "NOTHING"; // PAUSES MOVEMENT
				graphics.state = "PAUSE"; // SETS STACK TO PAUSE
				}
			}
		}
		else if(graphics.state == "PAUSE") { //IF GAME IS PAUSED
			//PRESS R TO RESUME
			if(keyCode == KeyEvent.VK_R) {
				graphics.state = "RUNNING"; // SETS STATE BACK TO RUNNING
				playSE(6); // COUGH
			}
			if(keyCode == KeyEvent.VK_Q) {
			//PRESS Q TO QUIT
				quitGame(); // EXITS THE SYSTEM
				playSE(6); // COUGH
				System.exit(1);
			}
		}
		else if(graphics.state == "END") { //IF GAME HAS ENDED
			if(keyCode == KeyEvent.VK_R) {
				//PRESS R TO RESTART
				restartGame();
				graphics.state = "START"; // SETS GAME BACK TO START MENU
				playSE(6); // COUGH
			}
			else if(keyCode == KeyEvent.VK_Q) {
				//PRESS Q TO QUIT
				quitGame(); // EXITS THE SYSTEM
				playSE(6); // COUGH
				System.exit(1);
			}
		}
	}
	
	/* Sounds */
	//For starting Background Music that ARE looped
	public void playMusic(int i) {
		sound.setFile(i); // CHOOSES SOUND
		sound.play(); // PLAYS THE SOUND
		sound.loop(); // LOOPS THE SOUND 
	}
	
	//For Stopping Background Music
	public void stopMusic() {
		sound.stop(); // STOPS THE SOUND
	}
	
	//For playing sound effects that are NOT looped
	public void playSE(int i) { 
		sound.setFile(i); // CHOOSES SOUND
		sound.play(); // PLAYS THE SOUND
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// NOT NEEDED
	}

	public Snake getPlayer() {
		return player;
	}

	public void setPlayer(Snake player) {
		this.player = player;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}
	
}
