package Client.main;// Java program to play an Audio
// file using Clip Object 
import java.io.File; 
import java.io.IOException; 


import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public class SimpleAudioPlayer 
{

	private static String filePath;
	// to store current position
	Long currentFrame; 
	Clip clip; 
	
	// current status of clip 
	String status; 
	
	AudioInputStream audioInputStream;

	// constructor to initialize streams and clip 
	public SimpleAudioPlayer(String filePath)
		throws UnsupportedAudioFileException, 
		IOException, LineUnavailableException 
	{
		// create AudioInputStream object 
		audioInputStream = 
				AudioSystem.getAudioInputStream(new File( filePath).getAbsoluteFile());
		
		// create clip reference 
		clip = AudioSystem.getClip(); 
		
		// open audioInputStream to the clip 
		clip.open(audioInputStream); 
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	} 

	public void play() 
	{ 
		//start the clip 
		clip.start(); 
		
		status = "play"; 
	} 

	public void restart() throws IOException, LineUnavailableException, 
											UnsupportedAudioFileException 
	{ 
		clip.stop(); 
		clip.close(); 
		resetAudioStream(); 
		currentFrame = 0L; 
		clip.setMicrosecondPosition(0); 
		this.play(); 
	}

	public void stop() throws IOException, LineUnavailableException
	{
		currentFrame = 0L;
		clip.stop();
		clip.close();
	}

	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
											LineUnavailableException 
	{ 
		audioInputStream = AudioSystem.getAudioInputStream( 
		new File(filePath).getAbsoluteFile()); 
		clip.open(audioInputStream); 
		clip.loop(Clip.LOOP_CONTINUOUSLY); 
	} 

} 
