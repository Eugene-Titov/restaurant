package restaurant.restaurant;

import java.awt.EventQueue;

import view.Authentification;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException
    {
    	Class.forName("org.postgresql.Driver");
        EventQueue.invokeLater(() -> {
        	Authentification authentification = new Authentification();
        });
    }
}
