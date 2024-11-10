package az.edu.turing;

import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println(UUID.randomUUID().toString().substring(0, 8));
    }
}
