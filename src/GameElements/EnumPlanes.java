package GameElements;

import java.util.Random;

public enum EnumPlanes {
    A,B,C,D,E;
   
    private static final Random RANDOM = new Random();
    public static String getRandomAvion()  {
    	EnumPlanes avion = values()[RANDOM.nextInt(values().length)];
      return String.valueOf(avion);
    }
    
}
