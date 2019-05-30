package Others;

import java.io.File;
import java.util.Random;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class BasicFunctions {
	static Random random = new Random();
	public BasicFunctions() {}
	
	/*//metodo para obtener del property el dato guardado con ese key*/
	public static String getPropertyKey(String key) {
		return ReadPropertyFile.main(key);
	}
	
	//STRING TO INTEGER
	public static int ParseInt(String key) {
		return Integer.parseInt(key);	
	}
	
	public static long ParseLong(String key) {
		return Long.parseLong(key);
	}

    //MUSICA
    public static void music() {
    	String songname = getPropertyKey("song2");
    	Media hit = new Media(new File("src/Media/"+songname+".mp3").toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(hit);
    	mediaPlayer.play();
    }
    
    
    //RANDOM NUM
    public static int getRandomNum(double d) {
    	int num = random.nextInt((int) d)+1;
    	return num;
    }
    
    //SLEEP A THREAD
    public static void sleep(double sleep) {
    	try {
			Thread.sleep((long) (sleep*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("No se duerme el Thread. Error");
		}
    }
	
}
