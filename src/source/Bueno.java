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
public class Bueno extends Base {

    private final String DESAPARECE = "DESAPARECE";
    private final String PAUSADO = "PAUSADO";
    private final int DEFAULT_SPEED = 10;

    private int speed;

    //Cuadros
    private URL[] stoppedURLs = {
        this.getClass().getResource("/images/ninjaStopped01_01.png"),
        this.getClass().getResource("/images/ninjaStopped02_02.png")
    };
    private URL[] derechaURLs = {
        this.getClass().getResource("/images/ninjaDerecha01_01.png"),
        this.getClass().getResource("/images/ninjaDerecha02_02.png"),
        this.getClass().getResource("/images/ninjaDerecha03_03.png"),
        this.getClass().getResource("/images/ninjaDerecha04_04.png"),
        this.getClass().getResource("/images/ninjaDerecha05_05.png"),
        this.getClass().getResource("/images/ninjaDerecha06_06.png")
    };
    private URL[] izquierdaURLs = {
        this.getClass().getResource("/images/ninjaIzquierda01_01.png"),
        this.getClass().getResource("/images/ninjaIzquierda02_02.png"),
        this.getClass().getResource("/images/ninjaIzquierda03_03.png"),
        this.getClass().getResource("/images/ninjaIzquierda04_04.png"),
        this.getClass().getResource("/images/ninjaIzquierda05_05.png"),
        this.getClass().getResource("/images/ninjaIzquierda06_06.png")
    };

    /**
     * Default Constructor that loads images in the arrays mentioned above...
     */
    public Bueno() {
        speed = DEFAULT_SPEED;
        Animacion parado = new Animacion();
        Animacion derecha = new Animacion();
        Animacion izquierda = new Animacion();

        /*
         Agrega todos los cuadros a la animacion main con 100ms de duracion
         */
        for (URL url : stoppedURLs) {
            parado.sumaCuadro(Toolkit.getDefaultToolkit().getImage(url), 100);
        }
        /*
         Agrega todos los cuadros a la animacion main con 100ms de duracion
         */
        for (URL url : derechaURLs) {
            derecha.sumaCuadro(Toolkit.getDefaultToolkit().getImage(url), 100);
        }
        /*
         Agrega todos los cuadros a la animacion collision con 100ms de duracion
         */
        for (URL url : izquierdaURLs) {
            izquierda.sumaCuadro(Toolkit.getDefaultToolkit().getImage(url), 100);
        }

        //Agrega las animaciones creadas al personaje en su clase Base. 
        setAnimacionBasica(parado);
        setAnimacionCaminarDerecha(derecha);
        setAnimacionCaminarIzquierda(izquierda);

        //direccion inicial del ninja
        setCorriendoAnimacionBasica(true);
    }

    public Bueno(int posX, int posY, Animacion animacionBasica) {
        super(posX, posY, animacionBasica);
    }

    public Bueno(int posX, int posY, Animacion animacionCaminarIzquierda, Animacion animacionCaminarDerecha) {
        super(posX, posY, animacionCaminarIzquierda, animacionCaminarDerecha);
    }

    /* GETTERS y SETTERS */
    public int getSpeed() {
        return speed;

    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    /* GETTERS y SETTERS */

    /* COMPORTAMIENTOS */
    public void moveRight() {
        setHaciaLaIzquierda(false);
        setHaciaLaDerecha(true);
        setCorriendoAnimacionBasica(false);
    }

    public void moveLeft() {
        setHaciaLaIzquierda(true);
        setHaciaLaDerecha(false);
        setCorriendoAnimacionBasica(false);
    }

    public void stop() {
        setHaciaLaIzquierda(false);
        setHaciaLaDerecha(false);
        setCorriendoAnimacionBasica(true);
    }

    public void move() {
        if (isHaciaLaDerecha()) {
            setPosX(getPosX() + speed);
        } else if (isHaciaLaIzquierda()) {
            setPosX(getPosX() - speed);
        } else {
            //no hacer nada o poner animacion de detenido
        }
    }

}//Fin de la clase Bueno
