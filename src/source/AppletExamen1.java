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
import java.util.LinkedList;

/**
 * El applet AppletAnimacion muestra una animación en pantalla.
 */
public class AppletExamen1 extends Applet implements Runnable, KeyListener {

    private Image dbImage;    // Imagen a proyectar
    private Graphics dbg;	// Objeto grafico

    //Personajes en el juego
    private Bueno ninja;    //objeto bueno, controlable con el teclado
    private LinkedList<Malo> malos; //lista de malos

    //marcador y pausa
    private int score;
    private boolean pausado; 

    //Variables de control de tiempo de la animación
    private long tiempoActual;
    private long tiempoInicial;
    int posX, posY;

    /**
     * El método init() crea la animación que se mostrará en pantalla.
     */
    public void init() {
        ninja = new Bueno();

        //genera lista de malos de tamanio aleatorio
        //puede ser 6, 10 o 12
        malos = generateRandomMaloList(6, 10, 12);
        ninja.setPosX(getWidth() / 2 - ninja.getAncho() / 2);
        ninja.setPosY(getHeight() / 2 - ninja.getAlto() / 2);

        //inicializo el marcador en 0
        score = 0;
        
        //el juego no esta pausado
        pausado = false; 

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

            //Manda a llamar checa colision
            checaColision();

            //Manda a llamar al método paint() para mostrar en pantalla la animación
            repaint();

            //Hace una pausa de 50 milisegundos
            try {
                Thread.sleep(100);
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

        for (Malo paraguas : malos) {
            paraguas.fall();
            paraguas.updateAnimation(tiempoTranscurrido);
        }
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
        }

    }

    /**
     * Metodo usado para checar las colisiones del objeto elefante y asteroid
     * con las orillas del <code>Applet</code>.
     */
    public void checaColision() {
        for (Malo paraguas : malos) {
            if (paraguas.intersecta(ninja)) {
                paraguas.collide(getWidth());
                score += 10;
            }

            /*
             //Checa colision con el applet
             if (paraguas.getPosY() > (getHeight() - paraguas.getAlto())) {
             paraguas.collide(getWidth());
             }
             */
        }

        int bounceoff = ninja.getSpeed();
        //checks ninja collision with applet
        if (ninja.getPosX() < 0) {
            ninja.moveRight();
            ninja.move();
        } else if (ninja.getPosX() > getWidth() - ninja.getAncho()) {
            ninja.moveLeft();
            ninja.move();
        }

    }

    /**
     * Metodo <I>update</I> sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void update(Graphics g) {
        // Inicializan el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza el Foreground.
        dbg.setColor(getForeground());
        paint(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);
    }

    /**
     * El método paint() muestra en pantalla la animación
     */
    public void paint(Graphics g) {
        // Muestra en pantalla el cuadro actual de la animación
        if (ninja != null && malos != null) {
            g.drawImage(ninja.getImagen(), ninja.getPosX(), ninja.getPosY(), this);
            for (Malo paraguas : malos) {
                g.drawImage(paraguas.getImagen(), paraguas.getPosX(), paraguas.getPosY(), this);
            }
            g.drawString("Score: " + score, 25, 25);
        } else {
            g.drawString("Cargando...", getWidth() / 2, getHeight() / 2);
        }

    }

    /**
     * Metodo crearMalo que crea un malo en una posicion aleatoria fuera del
     * applet en la parte de arriba.
     *
     * @return objeto de clase <code>Malo</code>
     */
    public Malo crearMalo() {
        Malo nuevoParaguas = new Malo();

        //Posiciona al nuevo paraguas aleatoriamente en la parte superior del applet
        nuevoParaguas.randomReset(getWidth());

        //establece la velocidad del objeto de manera aleatoria entre 3 y 6 px
        nuevoParaguas.setRandomSpeed(3, 6);

        return nuevoParaguas;
    }

    /**
     * Metodo generateRandomMaloList que genera una lista de malos, escogiendo
     * entre 3 tamanios enviados como parametro.
     *
     * @param easy minima cantidad de malos a crear tipo <code>int</code>
     * @param medium mediana cantidad de malos a crear tipo <code>int</code>
     * @param hard maxima cantidad de malos a crear tipo <code>int</code>
     * @return objeto de tipo <code>LinkedList</code> de la clase Malo con los
     * malos del juego ya con su speed y posiciones aleatorias.
     */
    public LinkedList<Malo> generateRandomMaloList(int easy, int medium, int hard) {
        int R = (int) (Math.random() * (3 - 1)) + 1;
        switch (R) {
            case 1:
                R = easy;
                break;
            case 2:
                R = medium;
                break;
            case 3:
                R = hard;
                break;
        }
        LinkedList<Malo> malos = new LinkedList<Malo>();
        for (int i = 0; i < R; i++) {
            malos.add(crearMalo());
        }
        return malos;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //presiono p
        if(e.getKeyCode() == KeyEvent.VK_P){
            pausado = true; 
        }
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
