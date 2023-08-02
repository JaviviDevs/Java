/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PiezasAjedrez;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author javie
 * La clase Figuras define los métodos comúnes de todas las piezas del ajedrez.
 */
public interface Figuras {
    public int TAM_COORDENADAS = 2;
    
    /**
    * MoverFigura()
    * Define el movimiento de cada figura
    */
    public abstract void moverFigura();    
    
    /**
    * comerFigura()
    * Define la acción de comer una figura
    */
    public abstract void comerFigura();  
    
    /**
    * setCoordenadas()
    * Establece las coordenadas de las piezas
    * @param fila: fila del tablero en la que se encuentra la pieza
    * @param columna: columna del tablero en la que se encuentra la pieza
    */
    public abstract void setCoordenadas(int fila,int columna);
    
    /**
    * setIcono()
    * Establece el icono de cada pieza
    * @param icono: imagen del paquete ImagenesFiguras que se corresponde con el icono
    */
    
    public abstract void setIcono(ImageIcon icono);
    
    /**
    * setColor()
    * Establece el color de la pieza (true = blanco, false = negro)
    * @param blanco: booleano; true = blanco, false = negro
    */
    
    public abstract void setColor(boolean blanco);
}
