/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PiezasAjedrez;

import javax.swing.ImageIcon;

/**
 *
 * @author javie
 */
public class Torre extends Piezas {

    public Torre(){
        super();
    }
    
    @Override
    public boolean[] puedeComer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moverFigura() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void comerFigura() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean realizarDesplazamiento(int filaAnt, int col, int desplazamiento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        this.iconoFigura.setIcon(icon);
    }
    
     /**
    * setColor()
    * Establece el color de la pieza: Peon 
    * @param blanco: booleano; true = blanco, false = negro
    */
    @Override
    public void setColor(int blanco){
        this.blanco=blanco;
    }
}
