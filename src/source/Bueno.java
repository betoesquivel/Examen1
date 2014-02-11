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
public class Bueno extends Base {

    public Bueno() {
    }

    public Bueno(int posX, int posY, Animacion animacionBasica) {
        super(posX, posY, animacionBasica);
    }

    public Bueno(int posX, int posY, Animacion animacionCaminarIzquierda, Animacion animacionCaminarDerecha) {
        super(posX, posY, animacionCaminarIzquierda, animacionCaminarDerecha);
    }

    
}
