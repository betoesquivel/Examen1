/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.awt.Toolkit;
import java.net.URL;

/**
 *
 * @author ppesq
 */
public class Malo extends Base {

    private URL[] umbrellaURLs = {
        this.getClass().getResource("/images/umbrella01_01.png"),
        this.getClass().getResource("/images/umbrella02_02.png"),
        this.getClass().getResource("/images/umbrella03_03.png"),
        this.getClass().getResource("/images/umbrella04_04.png"),
        this.getClass().getResource("/images/umbrella05_05.png"),
        this.getClass().getResource("/images/umbrella06_06.png")
    };
    private URL[] umbrellaCollisionURLs = {
        this.getClass().getResource("/images/umbrellaCollision01_01.png"),
        this.getClass().getResource("/images/umbrellaCollision02_02.png")
    };

    /**
     * Default constructor that loads images from the arrays mentioned above.
     */
    public Malo() {
        Animacion main = new Animacion();
        Animacion collision = new Animacion();
        
        /*
        Agrega todos los cuadros a la animacion main con 100ms de duracion
        */
        for (URL umbrella : umbrellaURLs) {
            main.sumaCuadro(Toolkit.getDefaultToolkit().getImage(umbrella), 100);
        }
        /*
        Agrega todos los cuadros a la animacion collision con 100ms de duracion
        */
        for (URL coll : umbrellaCollisionURLs) {
            collision.sumaCuadro(Toolkit.getDefaultToolkit().getImage(coll), 100);
        }
        
        //Agrega las animaciones creadas al personaje en su clase Base. 
        setAnimacionBasica(main);
        setAnimacionColision(collision);
        
        //direccion inicial del malo
        setCorriendoAnimacionBasica(true);
    }

    public Malo(int posX, int posY, Animacion animacionBasica) {
        super(posX, posY, animacionBasica);
    }

    public Malo(int posX, int posY, Animacion animacionCaminarIzquierda, Animacion animacionCaminarDerecha) {
        super(posX, posY, animacionCaminarIzquierda, animacionCaminarDerecha);
    }

}
