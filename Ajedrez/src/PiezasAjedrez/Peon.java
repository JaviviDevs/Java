/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PiezasAjedrez;

import static PiezasAjedrez.Figuras.TAM_COORDENADAS;
import javax.swing.ImageIcon;

/**
 *
 * @author javie
 * Clase que representa la Pieza Peon
 */
public class Peon extends Piezas{
    private boolean inicio;

    /**
    * Creates new form Peon
    * Constructor de la clase Peon
    */
    
    public Peon() {
        this.inicio=true;
    }
    
    /**
    * MoverFigura()
    * Define el movimiento de la figura: Peon
    */
    @Override
    public void moverFigura() {
       int desplazamiento=1;
       int filaant=coordenadas[0];
       int col=coordenadas[1];
       if(inicio){
           desplazamiento=2;
       }
       
       if(blanco){
           coordenadas[0]=filaant-desplazamiento;
       }else{
           coordenadas[0]=filaant+desplazamiento;
       }
       
       int fila=coordenadas[0];
       tablero[filaant][col].remove(this);
       tablero[filaant][col].repaint();
       tablero[fila][col].add(this);
       inicio=false;
    }
    
    /**
    * comerFigura()
    * Define la acción de comer una figura, en este caso define como come la figura: Peon
    */
    @Override
    public void comerFigura() {

    }
    
    /**
    * setCoordenadas()
    * Establece las coordenadas de las piezas: Peon
    * @param fila: fila del tablero en la que se encuentra la pieza
    * @param columna: columna del tablero en la que se encuentra la pieza
    */
    @Override
    public void setCoordenadas(int fila,int columna){
        this.coordenadas[0]=fila;
        this.coordenadas[1]=columna;
    }
    
    /**
    * setIcono()
    * Establece el icono de cada pieza: Peon
    * @param icono: imagen del paquete ImagenesFiguras que se corresponde con el icono
    */
    @Override
    public void setIcono(ImageIcon icon){
        iconoFigura.setIcon(icon);
    }
    
     /**
    * setColor()
    * Establece el color de la pieza: Peon 
    * @param blanco: booleano; true = blanco, false = negro
    */
    @Override
    public void setColor(boolean blanco){
        this.blanco=blanco;
    }
    
    
    
    
    
}
