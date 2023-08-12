/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PiezasAjedrez;

import java.awt.Component;
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
        this.entradaDatos = new Scanner(System.in);
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
            desplazamiento = this.entradaDatos.nextInt();
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
        boolean moverse=false;
        boolean realizarAccion=true;
       
        int fila=filaAnt;
        if(this.blanco){
           fila=filaAnt-desplazamiento;
           realizarAccion=this.puedeRealizarAccion(false,true,0,-desplazamiento);
        }else{
           fila=filaAnt+desplazamiento;
           realizarAccion=this.puedeRealizarAccion(false,true,0,desplazamiento);
        }

        if(realizarAccion){ 
            boolean hayPiezaPrimeraCasilla=false;
            boolean hayPiezaSegundaCasilla=false;

            if(desplazamiento==2){
                hayPiezaSegundaCasilla=super.comprobarPieza(fila,col);
                if(this.blanco){
                    hayPiezaPrimeraCasilla=super.comprobarPieza(filaAnt-1,col);
                }else{
                    hayPiezaPrimeraCasilla=super.comprobarPieza(filaAnt+1,col);
                }
            }else{
                hayPiezaPrimeraCasilla=super.comprobarPieza(fila,col);
            }

            if(hayPiezaPrimeraCasilla || hayPiezaSegundaCasilla){
                moverse=false;
            }else{
              this.coordenadas[0]=fila;
              moverse=true;
            }
        }
        
        return moverse;
    }
    
    /**
     * actualizartablero()
     * Actualiza el tablero tras mover o comer una figura/pieza.
     * @param filaAnt: fila en la que se encontraba la figura
     * @param colAnt: columna en la que se encontraba la figura
     */
    @Override
    public void actualizarTablero(int filaAnt,int colAnt){
        int fila=this.coordenadas[0];
        int col=this.coordenadas[1];
        tablero[filaAnt][colAnt].remove(this);
        tablero[filaAnt][colAnt].repaint();
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
    * puedeComer()
    * Comprueba si la figura puede comerse a otra
    * @return comer: booleano, true si se puede comer una pieza, false si no.
    */
    
    @Override
    public boolean[] puedeComer() {
        int desplazamiento=1;
        boolean[] comer=new boolean[Piezas.TAM_COORDENADAS];
        comer[0]=comer[1]=false;
        boolean pComerHIzq=true;
        boolean pComerDcha=true;
        
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int fila=filaAct;
        
        if(this.blanco){
           fila=filaAct-desplazamiento;
           pComerHIzq=this.puedeRealizarAccion(true, true, -desplazamiento,-desplazamiento);
           pComerDcha=this.puedeRealizarAccion(true, true, desplazamiento,-desplazamiento);
        }else{
           fila=filaAct+desplazamiento;
           pComerHIzq=this.puedeRealizarAccion(true, true, -desplazamiento,desplazamiento);
           pComerDcha=this.puedeRealizarAccion(true, true, desplazamiento,desplazamiento);
        }
       
        if(pComerHIzq){
            if(super.comprobarPieza(fila,colAct-desplazamiento)){
               comer[0]=true;
            }
        } 
        
        if(pComerDcha){
            if(super.comprobarPieza(fila,colAct+desplazamiento)){
               comer[1]=true;
            }
        } 
       
       return comer;
    }
    
    Integer eligeFiguraComer(){
        int accion=0;
        while(accion<1 || accion>2){
            System.out.print("Ingresa la accion(1 comer izq o 2 comer dcha): ");
            accion = this.entradaDatos.nextInt();
            System.out.println("Accion escogida: " + accion);
        }
        
        return accion;
    }
    /**
    * comerFigura()
    * Define la acción de comer una figura, en este caso define como come la figura: Peon
    */
    @Override
    public void comerFigura() {
       
        int desplazamiento=1;
        boolean[] pcomer=this.puedeComer();
        
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nFil=filaAct;
        int nCol=colAct;
        
        if(this.blanco){
            nFil=filaAct-desplazamiento;
        }else{
            nFil=filaAct+desplazamiento;
        }
       
        if(pcomer[0] && pcomer[1]){
            int accion=eligeFiguraComer();
            if(accion==1){
                nCol=colAct-desplazamiento;
            }else{
                nCol=colAct+desplazamiento;
            }
        }else if(pcomer[0]){
            nCol=colAct-desplazamiento;
        }else if(pcomer[1]){
            nCol=colAct+desplazamiento;
        }
        
        
       
        Component piezaComida=this.tablero[nFil][nCol].getComponent(0);
        tablero[nFil][nCol].remove(piezaComida);
        tablero[nFil][nCol].repaint();

        this.coordenadas[0]=nFil;
        this.coordenadas[1]=nCol;
        this.actualizarTablero(filaAct, colAct);
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
