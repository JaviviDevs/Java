/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PiezasAjedrez;

import java.awt.Component;
import javax.swing.ImageIcon;

/**
 *
 * @author javie
 * Clase que representa la Pieza Peon
 */
public class Peon extends Piezas{
    private boolean inicio; //Indica si ese peon se ha movido o no
    private int[][] desplazamiento; //Controla el desplazamiento de los peones
    private final int TAMdesFil = 2;
    private final int TAMdesCol = 2;
     
    /**
    * Creates new form Peon
    * Constructor de la clase Peon
    */
    
    public Peon() {
        super(); //Inicializacion de los tributos de la clase padre
        this.inicio=true;
        this.desplazamiento=new int[TAMdesFil][TAMdesCol];
        this.desplazamiento[0][0]=1; //Si la figura es negra y se desplaza una casilla
        this.desplazamiento[0][1]=2; //Si la figura es negra y se desplaza dos casillas
        this.desplazamiento[1][0]=-1; //Si la figura es blanca y se desplaza una casilla
        this.desplazamiento[1][1]=-2; //Si la figura es blanca y se desplaza dos casillas
    }
    
    /**
    * puedeMover()
    * @return moverse: vector boleano que indica si puede moverse en las diversas direccions,aunque en el caso
    * del peon solo indicara si se puede una o dos casillas.
    */
    @Override
    public boolean[] puedeMover(){
        boolean[] moverse=new boolean[2];
        moverse[0]=false;
        boolean rAccion=true;
        
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int desp=this.desplazamiento[blanco][1]; //Comprobar para las dos casillas delanteras
        int fila=filaAct+desp;
        
        rAccion=this.puedeRealizarAccion(false,true,0,desp);
        
        if(rAccion){
            moverse[0]=!(super.comprobarPieza(filaAct+this.desplazamiento[blanco][0],colAct));
            moverse[1]=!(super.comprobarPieza(fila,colAct));
        }
        
        return moverse;
    }
    
    /**
    * MoverFigura()
    * Define el movimiento de la figura: Peon
    */
    @Override
    public void moverFigura() {
        int indxDes=1;
        int filaAct=this.coordenadas[0];
        int nFila=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int desp=0;
        boolean[] moverFigura=this.puedeMover();

        if(this.inicio){
            indxDes=super.menuOpciones("Nº casillas a desplazar(1,2):",1,2);
        }
        
        if(moverFigura[0] || moverFigura[1]){
            desp=this.desplazamiento[blanco][indxDes-1];
            nFila=filaAct+desp;
            this.coordenadas[0]=nFila;
            
            super.actualizarTablero(filaAct,colAct);
            inicio=false;
        }else{
            System.out.println("La pieza no se puede mover");
        }
    }
    
    /**
    * puedeComer()
    * Comprueba si la figura puede comerse a otra
    * @return comer: booleano, true si se puede comer una pieza, false si no.
    */
    
    @Override
    public boolean[] puedeComer() {
        int des=this.desplazamiento[blanco][0];
        int colIzq=-1;
        int colDcha=1;
        
        boolean[] comer=new boolean[4];
        //En este caso la posicion 2 y 3 seran false, pues solo necesito saber si se puede
        //comer hacia la izq o dcha, pero para otras figuras necesito que tenga ese tamaño.
        // porque se usa en la clase padre
        comer[0]=comer[1]=comer[2]=comer[3]=false; 
        boolean pComerHIzq=true;
        boolean pComerDcha=true;
        
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int fila=filaAct+des;
        
        pComerHIzq=this.puedeRealizarAccion(true, true, colIzq,des);
        pComerDcha=this.puedeRealizarAccion(true, true, colDcha,des);
      
        int color=0;
        if(this.blanco==0){
            color=1;
        }
        
        if(pComerHIzq && super.comprobarPiezaColor(fila,colAct+colIzq,color)){
            comer[0]=true;   
        }
        
        if(pComerDcha && super.comprobarPiezaColor(fila,colAct+colDcha,color)){
            comer[1]=true;
        } 
       
       return comer;
    }
    
    /**
    * comerFigura()
    * Define la acción de comer una figura, en este caso define como come la figura: Peon
    */
    @Override
    public void comerFigura() {
        int des=this.desplazamiento[blanco][0];
        int colDcha=1;
        int colIzq=-1;
        int accion=0;
        boolean[] pcomer=this.puedeComer();
        
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nFil=filaAct+des;
        int nCol=colAct;
        
        if(pcomer[0] && pcomer[1]){
            accion=super.menuOpciones("Elija figura a comer: izq(1),dcha(2):", 1, 2);
            if(accion==1){
                nCol=colAct+colIzq;
            }else{
                nCol=colAct+colDcha;
            }  
        }else if(pcomer[0]){
            nCol=colAct+colIzq;
        }else if(pcomer[1]){
            nCol=colAct+colDcha;
        }
        
        Component piezaComida=this.tablero[nFil][nCol].getComponent(0);
        tablero[nFil][nCol].remove(piezaComida);
        tablero[nFil][nCol].repaint();

        this.coordenadas[0]=nFil;
        this.coordenadas[1]=nCol;
        super.actualizarTablero(filaAct, colAct);
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
