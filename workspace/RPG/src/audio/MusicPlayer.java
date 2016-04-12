package audio;

import java.util.ArrayList;

public class MusicPlayer implements Runnable {
	
	private ArrayList<AudioFile> musicFiles;
	private int currentSongIndex;
	private boolean running;
	
	public MusicPlayer(String... files) {
		
		musicFiles = new ArrayList<AudioFile>();
		for(String file : files)
			
			musicFiles.add(new AudioFile("./resources/audio/" + file + ".wav"));
	}

	@Override
	public void run() {
		
		running = true;
		AudioFile song = musicFiles.get(currentSongIndex);
		song.play();
		
		while(running){
			
			if(!song.isPlaying()) {
				
				currentSongIndex ++;
				
				if(currentSongIndex >= musicFiles.size())
					currentSongIndex = 0;
				song = musicFiles.get(currentSongIndex);
				song.play();
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}