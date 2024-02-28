package main;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {

	public ArrayList<Rectangle> body;

	public String move; // DIRECTIONS
	
	public Snake() { // SNAKE CONSTRUCTOR
		body = new ArrayList<>();
		
		// CREATES THE SNAKE USING AN ARRAYLIST, WITH BODYPARTS AS THE NUMBER OF INITIAL BODYPARTS
		
		for(int i=0; i<Game.bodyParts; i++) {
			Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
			temp.setLocation(Game.width / 2* Game.dimension - i, Game.height / 2* Game.dimension - i); // STARTING POSITION OF THE SNAKE
			body.add(temp);
		}
		
		/* HARD CODED FOR SNAKE INITIAL BODY TO BE 3 
		Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
		temp.setLocation(Game.width / 2 *Game.dimension, Game.height / 2* Game.dimension);
		body.add(temp);
		
		temp = new Rectangle(Game.dimension, Game.dimension);
		temp.setLocation((Game.width / 2 - 1)*Game.dimension, (Game.height / 2)*Game.dimension);
		body.add(temp);
		
		temp = new Rectangle(Game.dimension, Game.dimension);
		temp.setLocation((Game.width / 2 - 2)*Game.dimension, (Game.height / 2)*Game.dimension);
		body.add(temp);
		*/
		
		move = "NOTHING"; // INITIAL MOVE
	}
	
	public void move() { // MOVE FUNCTION
		
		if(move != "NOTHING") { // IF MOVE IS NOT NOTHING
			Rectangle first = body.get(0);
			Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
			
			if(move == "UP") { //UP
				temp.setLocation(first.x, first.y - Game.dimension); //MOVE BY 1 UNIT UP
			}
			else if(move == "DOWN") { //DOWN
				temp.setLocation(first.x, first.y + Game.dimension); //MOVE BY 1 UNIT DOWN
			}
			else if(move == "LEFT") { //LEFT
				temp.setLocation(first.x - Game.dimension, first.y); //MOVE BY 1 UNIT LEFT
			}
			else { //RIGHT
				temp.setLocation(first.x + Game.dimension, first.y); //MOVE BY 1 UNIT RIGHT
			}
			body.add(0, temp); // ADD A BODY PART AT THE FRONT
			body.remove(body.size()-1); // REMOVE THE LAST BODY PART
		} 
	}
	
	public void grow() { // TO INCREASE THE BODY PARTS AFTER EATING AN APPLE
		
		Rectangle first = body.get(0); // GET INITIAL BODY PART
		Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
		
		if(move == "UP") { //IF MOVING UP
			temp.setLocation(first.x, first.y - Game.dimension);
		}
		else if(move == "DOWN") { //IF MOVING DOWN
			temp.setLocation(first.x, first.y + Game.dimension);
		}
		else if(move == "LEFT") { //IF MOVING LEFT
			temp.setLocation(first.x - Game.dimension, first.y);
		}
		else { //IF MOVING RIGHT
			temp.setLocation(first.x + Game.dimension, first.y);
		}
		body.add(0, temp); //ADD PARTS
	}
	
	public ArrayList<Rectangle> getBody() {
		return body;
	}
	
	public void setBody(ArrayList<Rectangle> body) {
		this.body = body;
	}
	
	public int getX() { // get HeadX of Snake
		return body.get(0).x;
	}
	
	public int getY() { // get HeadY of Snake
		return body.get(0).y;
	}
	
	public int getX(int i) { // get BodyX of Snake
		return body.get(i).x;
	}
	
	public int getY(int i) { // get BodyY of Snake
		return body.get(i).y;
	}
	
	public void up() { // TO MOVE IT UP. CALLED IN GAME
		if(move != "DOWN") { // PREVENT IT FROM MOVING IN THE OPPOSITE DIRECTION
		move = "UP";
		}
	}
	public void down() { // TO MOVE IT DOWN. CALLED IN GAME
		if(move != "UP") { // PREVENT IT FROM MOVING IN THE OPPOSITE DIRECTION
		move = "DOWN";
		}
	}
	public void left() { // TO MOVE IT LEFT. CALLED IN GAME
		if(move != "RIGHT") { // PREVENT IT FROM MOVING IN THE OPPOSITE DIRECTION
		move = "LEFT";
		}
	}
	public void right() { // TO MOVE IT RIGHT. CALLED IN GAME
		if(move != "LEFT") { // PREVENT IT FROM MOVING IN THE OPPOSITE DIRECTION
		move = "RIGHT";
		}
	}
	
}
