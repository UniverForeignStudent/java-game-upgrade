package main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Audio {
	private Clip clip;
	private File audioFile;
	private AudioInputStream audioInputStream;
	private boolean isLoop;
	float volume;
	int volumeScale = 3;
	FloatControl floatControl;
	
	public Audio(String pathName, boolean isLoop) {
		this.isLoop = isLoop;
		try{
			clip = AudioSystem.getClip();
			audioFile = new File(pathName);
			audioInputStream = AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioInputStream);
			floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
		}catch(LineUnavailableException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	public void start() {
		clip.setFramePosition(0);
		clip.start();
		checkVolume();
		if(isLoop)clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}

	public void checkVolume(){
		switch (volumeScale){
			case 0:
				volume = -80f;
				break;
			case 1:
				volume = -20f;
				break;
			case 2:
				volume = -12f;
				break;
			case 3:
				volume = -5f;
				break;
			case 4:
				volume = 1f;
				break;
			case 5:
				volume = 6f;
				break;
		}

		floatControl.setValue(volume);
	}
}
