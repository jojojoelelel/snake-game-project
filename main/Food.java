package main;

import java.awt.Rectangle;

public class Food {
	private int Foodx; // X COORDINATE OF FOOD
	private int Foody; // Y COORDINATE OF FOOD
	private int Bombx; // X COORDINATE OF BOMB
	private int Bomby; // Y COORDINATE OF BOMB

	public Food(Snake player) {
		this.newApple(player);
		this.newBomb(player);
		
	}
	
	public void newApple(Snake player) { // SPAWNS A NEW APPLE
		
		boolean onSnake = true; // ASSUME APPLE SPAWNS ON SNAKE
		
		while(onSnake) { // WHILE APPLE IS ON SNAKE. GO INTO WHILE LOOP
			
			onSnake = false; // SET APPLE IS ON SNAKE TO FALSE
			
			Foodx = (int)(Math.random() * Game.width-1); // GENERATE APPLEX COORDINATE
			Foody = (int)(Math.random() * Game.height-1); // GENERATE APPLEY COORDINATE
			
			for (Rectangle r : player.getBody()) { // CHECK IF APPLE POSITION IS ON THE SNAKE
				if(r.x == Foodx && r.y == Foody) { 
					onSnake = true; // SET TO TRUE -> APPLE IS ON SNAKE, TO RUN THE WHILE LOOP AND GENERATE ANOTHER COORDINATE. UNTIL NOT ON SNAKE
				}
			}
		}
	}

	public void newBomb(Snake player) { // SPAWNS A NEW BOMB
		boolean onSnake = true; // ASSUME BOMB SPAWNS ON SNAKE
		
		while(onSnake) { // WHILE BOMB IS ON SNAKE. GO INTO WHILE LOOP
			
			onSnake = false; // SET BOMB IS ON SNAKE TO FALSE
			
			Bombx = (int)(Math.random() * Game.width-1); // GENERATE BOMBX COORDINATE
			Bomby = (int)(Math.random() * Game.height-1); // GENERATE BOMBY COORDINATE
			
			for (Rectangle r : player.getBody()) { // CHECK IF BOMB POSITION IS ON THE SNAKE
				if(r.x == Bombx && r.y == Bomby) { 
					onSnake = true; // SET TO TRUE -> BOMB IS ON SNAKE, TO RUN THE WHILE LOOP AND GENERATE ANOTHER COORDINATE. UNTIL NOT ON SNAKE
				}
			}
		}
	}
	public int getFoodX() { // GETTER FOR X COORDINATE OF APPLE
		return Foodx;
	}

	public int getFoodY() { // GETTER FOR Y COORDINATE OF APPLE
		return Foody;
	}
	
	public int getBombX() { // GETTER FOR X COORDINATE OF BOMB
		return Bombx;
	}
	
	public int getBombY() { // GETTER FOR Y COORDINATE OF BOMB
		return Bomby;
	}
	
}
