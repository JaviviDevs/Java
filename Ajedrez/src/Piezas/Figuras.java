/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Piezas;

import javax.swing.JPanel;

/**
 *
 * @author javie
 */
public interface Figuras {
    public abstract int[] moverFigura();    
    public abstract int[] comer();     
    public abstract void setCoordenadas(int fila,int columna);
    public abstract void setTableros(Figuras[][] tablero,JPanel[][] tableroInterfaz);
}
