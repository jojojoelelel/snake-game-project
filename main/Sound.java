package main;

import java.net.URL;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;

public class Sound {

	//INSTANTIATE 
	Clip clip;
	URL soundURL[] = new URL[10]; //ARRAY WITH SOUNDS USED 
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sounds/Applecrunchsoundfx.wav"); // APPLE EATEN (0)
		soundURL[1] = getClass().getResource("/sounds/Easycheesybgmusic.wav"); // BACKGROUND MUSIC WHEN GAME IS RUNNING (1)
		soundURL[2] = getClass().getResource("/sounds/Gameoversoundfx.wav"); // GAMEOVER SOUND EFFECT (2)
		soundURL[3] = getClass().getResource("/sounds/clickfx.wav"); // CLICK SOUND EFFECT (3)
		soundURL[4] = getClass().getResource("/sounds/Menubgm.wav"); // MENU BACKGROUND MUSIC (4). BEACH BOYS - I GET AROUND, 1963
		soundURL[5] = getClass().getResource("/sounds/Gameoverbgm.wav"); // GAMEOVER MUSIC (5). MY HEART WILL GO ON - RECORDER BY CANDLELIGHT BY MATT MULHOLLAND
		soundURL[6] = getClass().getResource("/sounds/CoughSoundFX.wav"); // SOUND EFFECT FOR COUGH (6)
				
	}
	
	public void setFile(int i) {
		try {
			
			// TO SET AUDIO CLIP TO BE SELECTED CLIP, BASED ON ARRAY SOUNDURL[] INDEX
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
		}
	}
	public void play() { // TO START PLAYING THE AUDIO CLIP
		
		clip.start();
		
	}
	public void loop() { // TO LOOP THE AUDIO CLIP
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	public void stop() { // TO STOP THE AUDIO CLIP
		
		clip.stop();
	} 
}

