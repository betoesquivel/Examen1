package source;

/**
 * Clase Base
 *
 * source: Antonio Mejorado
 *
 * @author José Alberto Esquivel Patiño
 * @version 1.00 2008/6/13
 */
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Esta clase es una base para personajes dentro de un applet de java. La clase
 * debe de ser inicializada con al menos una animación no nula.
 *
 * @author ppesq
 */
public class Base {

    //control de movimiento
    private int posX;    //posicion en x.       
    private int posY;	//posicion en y.

    //control de animaciones
    private Animacion animacionBasica; //animacion principal del objeto
    private Animacion animacionCaminarIzquierda; //para caminar hacia la izquierda
    private Animacion animacionCaminarDerecha;  //para caminar hacia la derecha
    private Animacion animacionColision; //para colisionar
    private boolean corriendoAnimacionBasica; //true cuando está corriendo la animacion basica
    private boolean haciaLaDerecha; //true cuando se está moviendo hacia la derecha
    private boolean haciaLaIzquierda; //true cuando se está moviendo hacia la izquierda
    private boolean colisionando; //true cuando se está reproduciendo animacion de colision
    
    /* CONSTRUCTORES */
    /**
     * Metodo constructor usado para crear el objeto con valores default
     *
     */
    public Base() {
        this.posX = 0;
        this.posY = 0;
        this.animacionBasica = animacionCaminarDerecha = animacionCaminarIzquierda = animacionColision = null;
        
    }
    
    /**
     * Metodo constructor usado para crear el objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param image es la <code>imagen</code> del objeto.
     */
    public Base(int posX, int posY, Animacion animacionBasica) {
        this.posX = posX;
        this.posY = posY;
        this.animacionBasica = animacionBasica;
    }

    /**
     * Metodo constructor usado para crear el objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param image es la <code>imagen</code> del objeto.
     */
    public Base(int posX, int posY, Animacion animacionCaminarIzquierda, Animacion animacionCaminarDerecha) {
        this.posX = posX;
        this.posY = posY;
        this.animacionCaminarIzquierda = animacionCaminarIzquierda;
        this.animacionCaminarDerecha = animacionCaminarDerecha;
    }
    /* CONSTRUCTORES */

    /* COMPORTAMIENTOS */
    /**
     * Metodo de acceso que regresa un nuevo rectangulo
     *
     * @return un objeto de la clase <code>Rectangle</code> que es el perimetro
     * del rectangulo
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getPosX(), getPosY(), getAncho(), getAlto());
    }
    
    public void updateAnimation(long tiempoTranscurrido){
        Animacion anim = new Animacion(); 
        if(isCorriendoAnimacionBasica()){
            anim = animacionBasica; 
        }else if(isHaciaLaDerecha()){
            anim = animacionCaminarDerecha;
        }else if(isHaciaLaIzquierda()){
            anim = animacionCaminarIzquierda;
        }else if(isColisionando()){
            anim = animacionColision;
        }
        //Actualiza la animación en base al tiempo transcurrido
        anim.actualiza(tiempoTranscurrido);
    }
    /* COMPORTAMIENTOS */

    /* GETTERS Y SETTERS */
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Regresa la imagen del cuadro actual animándose.
     *
     * @return
     */
    public Image getImagen() {
        if (corriendoAnimacionBasica) {
            return animacionBasica.getImagen();
        } else if (haciaLaDerecha) {
            return animacionCaminarDerecha.getImagen();
        } else if (haciaLaIzquierda) {
            return animacionCaminarIzquierda.getImagen();
        } else {
            return null;
        }
    }

    /**
     * Metodo de acceso que regresa el ancho del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del
     * icono.
     */
    public int getAncho() {
        return (new ImageIcon(getImagen())).getIconWidth();
    }

    /**
     * Metodo de acceso que regresa el alto del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el alto del
     * icono.
     */
    public int getAlto() {
        return (new ImageIcon(getImagen())).getIconHeight();
    }

    public Animacion getAnimacionBasica() {
        return animacionBasica;
    }

    public void setAnimacionBasica(Animacion animacionBasica) {
        this.animacionBasica = animacionBasica;
    }

    public Animacion getAnimacionCaminarIzquierda() {
        return animacionCaminarIzquierda;
    }

    public void setAnimacionCaminarIzquierda(Animacion animacionCaminarIzquierda) {
        this.animacionCaminarIzquierda = animacionCaminarIzquierda;
    }

    public Animacion getAnimacionCaminarDerecha() {
        return animacionCaminarDerecha;
    }

    public void setAnimacionCaminarDerecha(Animacion animacionCaminarDerecha) {
        this.animacionCaminarDerecha = animacionCaminarDerecha;
    }

    public Animacion getAnimacionColision() {
        return animacionColision;
    }

    public void setAnimacionColision(Animacion animacionColision) {
        this.animacionColision = animacionColision;
    }
    
    public boolean isCorriendoAnimacionBasica() {
        return corriendoAnimacionBasica;
    }

    public void setCorriendoAnimacionBasica(boolean corriendoAnimacionBasica) {
        this.corriendoAnimacionBasica = corriendoAnimacionBasica;
    }

    public boolean isHaciaLaDerecha() {
        return haciaLaDerecha;
    }

    public void setHaciaLaDerecha(boolean haciaLaDerecha) {
        this.haciaLaDerecha = haciaLaDerecha;
    }

    public boolean isHaciaLaIzquierda() {
        return haciaLaIzquierda;
    }

    public void setHaciaLaIzquierda(boolean haciaLaIzquierda) {
        this.haciaLaIzquierda = haciaLaIzquierda;
    }

    public boolean isColisionando() {
        return colisionando;
    }

    public void setColisionando(boolean colisionando) {
        this.colisionando = colisionando;
    }    
    /* FIN DE GETTERS Y SETTERS */

}// Fin de la clase Base
