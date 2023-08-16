/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PiezasAjedrez;
import javax.swing.ImageIcon;

/**
 *
 * @author javie
 * La clase Figuras define los métodos comúnes de todas las piezas del ajedrez.
 */
public interface Figuras {
    public int TAM_COORDENADAS = 2;
    
    /**
    * calcCasMover()
    * Calcula en cada direccion el numero de casillas máximas disponibles para moverse
    * @return mover: vector int que contiene las casillas disponibles a las que moverse
    */
    public abstract int[] calcCasMover();
    
    /**
    * MoverFigura()
    * Define el movimiento de cada figura
    */
    public abstract void moverFigura();    
    
    /**
    * calcCasComer()
    * Calcula en cada direccion el numero de casillas necesarias para comer la figura (si la encuentra)
    * @return comer: vector int que contiene las casillas necesarias para comer las figuras
    */
    public abstract int[] calcCasComer();
    
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
    public abstract void setColor(int blanco);
}
