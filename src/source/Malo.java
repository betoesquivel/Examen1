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

    //Variable de clase contador
    private static int cont = -1;

    //Control de movimiento
    private int speed;
    private final int DEFAULT_SPEED = 10;

    //URLs de cuadros de las animaciones
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
        if (cont < 0) {
            cont = 0;
        } else {
            //modifico el contador? 
        }
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

        speed = DEFAULT_SPEED;

    }

    public Malo(int posX, int posY, Animacion animacionBasica) {
        super(posX, posY, animacionBasica);
    }

    public Malo(int posX, int posY, Animacion animacionCaminarIzquierda, Animacion animacionCaminarDerecha) {
        super(posX, posY, animacionCaminarIzquierda, animacionCaminarDerecha);
    }

    /* COMPORTAMIENTOS */
    /**
     * Metodo collide que actualiza la posicion del paraguas y 
     * 
     */
    public void collide(int appletWidth){
        randomReset(appletWidth);
    }
    /**
     * Método fall
     *
     * Modifica la posición del objeto malo, aumentando su posición en Y para
     * que caiga.
     */
    public void fall() {
        setPosY(getPosY() + 10);
    }

    public void randomReset(int appletWidth){
        //formula random
        //Math.random() * (upper - lower)) + lower
        setPosX((int) (Math.random() * appletWidth));
        //corrige la posicion si se paso
        if (getPosX() > appletWidth - getAncho()) {
            //correct displacement out of screen
            setPosX(appletWidth - getAncho());
        }
        setPosY((int) (Math.random() * -200));
    }
    /* COMPORTAMIENTOS */
    /* SETTERS Y GETTERS */
    /**
     * Metodo getCont regresa el contador que es una variable de clase
     *
     * @return cont contador de tipo <code>int</code>
     */
    public static int getCont() {
        return cont;
    }

    public static void setCont(int cont) {
        Malo.cont = cont;
    }

    /**
     * Metodo getSpeed regresa la velocidad del objeto
     *
     * @return speed de tipo <code>int</code>
     */
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Metodo getUmbrellaURLS regresa URLs de imágenes animación básica de
     * umbrella.
     *
     * @return umbrellaURLs <code>URL[]</code>
     */
    public URL[] getUmbrellaURLs() {
        return umbrellaURLs;
    }

    public void setUmbrellaURLs(URL[] umbrellaURLs) {
        this.umbrellaURLs = umbrellaURLs;
    }

    /**
     * Metodo getUmbrellaCOllisionURLs Regresa el arrgeglo con URLs de imagenes
     * de colisión
     *
     * @return umbrellaCollisionURLs tipo <code>URL[]</code>
     */
    public URL[] getUmbrellaCollisionURLs() {
        return umbrellaCollisionURLs;
    }

    public void setUmbrellaCollisionURLs(URL[] umbrellaCollisionURLs) {
        this.umbrellaCollisionURLs = umbrellaCollisionURLs;
    }
    /* SETTERS Y GETTERS */

}
