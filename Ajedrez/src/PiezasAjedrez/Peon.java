/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PiezasAjedrez;

import java.util.Scanner;
import javax.swing.ImageIcon;

/**
 *
 * @author javie
 * Clase que representa la Pieza Peon
 */
public class Peon extends Piezas{
    private boolean inicio;
    Scanner entradaDatos;

    /**
    * Creates new form Peon
    * Constructor de la clase Peon
    */
    
    public Peon() {
        this.inicio=true;
        entradaDatos = new Scanner(System.in);
    }
    
    /**
    * escogerDesplazamiento()
    * Permite escoger el desplazamiento inicial de los peones
    * Al inicio los peones pueden avanzar una o dos casillas.
    * @return desplazamiento: desplazamiento escogido
    */
    @Override
    public int escogerDesplazamiento(){
        int desplazamiento=0;
        while(desplazamiento<1 || desplazamiento>2){
            System.out.print("Ingresa el desplazamiento(1 o 2): ");
            desplazamiento = entradaDatos.nextInt();
            System.out.println("Desplazamiento escogido: " + desplazamiento);
        }
        
        return desplazamiento;
    }
    
    /**
     * realizarDesplazamiento()
     * Comprueba si se puede mover la figura a la casilla que queremos, en caso afirmativo actualiza las
     * coordenadas.
     * @param filaAnt: fila actual en la que se encuentra la pieza a mover
     * @param col: columna en la que se encuentra la pieza a mover
     * @param desplazamiento: cantidad de casillas que la figura se desplaza en caso de poderse 
     * @return moverse: booleano, true si la pieza puede moverse
    */
    public boolean realizarDesplazamiento(int filaAnt,int col,int desplazamiento){
        int fila=filaAnt;
        
        boolean moverse=true;
        boolean hayPiezaPrimeraCasilla=false;
        boolean hayPiezaSegundaCasilla=false;
        if(this.blanco){
           fila=filaAnt-desplazamiento;
        }else{
           fila=filaAnt+desplazamiento;
        }
        
        if(desplazamiento==2){
            hayPiezaSegundaCasilla=comprobarPieza(fila,col);
            if(this.blanco){
                hayPiezaPrimeraCasilla=this.comprobarPieza(filaAnt-1,col);
            }else{
                hayPiezaPrimeraCasilla=this.comprobarPieza(filaAnt+1,col);
            }
        }else{
            hayPiezaPrimeraCasilla=this.comprobarPieza(fila,col);
        }
        
        if(hayPiezaPrimeraCasilla || hayPiezaSegundaCasilla){
            moverse=false;
        }else{
          this.coordenadas[0]=fila;
        }
        
        return moverse;
    }
    
    /**
     * actualizartablero()
     * Actualiza el tablero tras mover o comer una figura/pieza.
     * @param filaAnt: fila actual en la que se encuentra la pieza a mover
     * @param col: columna en la que se encuentra la pieza a mover 
     */
    @Override
    public void actualizarTablero(int filaAnt,int col){
        int fila=this.coordenadas[0];
        tablero[filaAnt][col].remove(this);
        tablero[filaAnt][col].repaint();
        tablero[fila][col].add(this);
    }
    /**
    * MoverFigura()
    * Define el movimiento de la figura: Peon
    */
    @Override
    public void moverFigura() {
       int desplazamiento=1;
       int filaAnt=this.coordenadas[0];
       int col=this.coordenadas[1];
       boolean moverFigura=true;
       
       if(this.inicio){
           desplazamiento=this.escogerDesplazamiento();
       }
       moverFigura=this.realizarDesplazamiento(filaAnt,col,desplazamiento);
       
       if(moverFigura){
            actualizarTablero(filaAnt,col);
            inicio=false;
       }
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
