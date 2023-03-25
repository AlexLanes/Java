import java.util.concurrent.ThreadLocalRandom;

public class Random {
    public static int range( int start, int end ){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt( start, ++end );
    }   

    public static void main( String[] args ){
        System.out.println( Random.range(0, 2) );
    }
}
