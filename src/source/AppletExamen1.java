/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author ppesq
 */
import javax.swing.ImageIcon;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * El applet AppletAnimacion muestra una animación en pantalla.
 */
public class AppletExamen1 extends Applet implements Runnable, KeyListener {

    //Personajes en el juego
    Bueno ninja;
    Malo paraguas;

//Objeto de la clase Animacion para el manejo de la animación
    private Animacion anim;

    //Variables de control de tiempo de la animación
    private long tiempoActual;
    private long tiempoInicial;
    int posX, posY;

    /**
     * El método init() crea la animación que se mostrará en pantalla.
     */
    public void init() {
        ninja = new Bueno();
        paraguas = new Malo();
        ninja.setPosX(15);
        ninja.setPosY(15);

        //Pinta el fondo del Applet de color amarillo		
        setBackground(Color.yellow);
        addKeyListener(this);
    }

    //El método start() inicializa el thread que utiliza el Applet
    public void start() {

        //Crea el thread
        Thread hilo = new Thread(this);
        //Inicializa el thread
        hilo.start();
    }

    /**
     * Metodo stop sobrescrito de la clase Applet. En este metodo se pueden
     * tomar acciones para cuando se termina de usar el Applet. Usualmente
     * cuando el usuario sale de la pagina en donde esta este Applet.
     */
    public void stop() {

    }

    /**
     * Metodo destroy sobrescrito de la clase Applet. En este metodo se toman
     * las acciones necesarias para cuando el Applet ya no va a ser usado.
     * Usualmente cuando el usuario cierra el navegador.
     */
    public void destroy() {

    }

    /**
     * El método run() manda a llamar los métodos atualiza() y repaint(),
     * nesecarios para actualizar y mostrar la animación en pantalla.
     */
    public void run() {

        //Guarda el tiempo actual del sistema
        tiempoActual = System.currentTimeMillis();

        //Ciclo principal del Applet. Actualiza y despliega en pantalla la animación hasta que el Applet sea cerrado
        while (true) {

            //Actualiza la animación
            actualiza();
            //Manda a llamar al método paint() para mostrar en pantalla la animación
            repaint();

            //Hace una pausa de 200 milisegundos
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
            }
        }

    }

    /**
     * El método actualiza() actualiza la animación
     */
    public void actualiza() {

        //Determina el tiempo que ha transcurrido desde que el Applet inicio su ejecución
        long tiempoTranscurrido
                = System.currentTimeMillis() - tiempoActual;

        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;

        ninja.move();
        ninja.updateAnimation(tiempoTranscurrido);

        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
        }

    }

    /**
     * El método paint() muestra en pantalla la animación
     */
    public void paint(Graphics g) {
        // Muestra en pantalla el cuadro actual de la animación
        if (ninja != null) {
            g.drawImage(ninja.getImagen(), ninja.getPosX(), ninja.getPosY(), this);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //presiono flecha izquierda
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ninja.moveLeft();
            //Presiono flecha derecha
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ninja.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ninja.stop();
    }
}
