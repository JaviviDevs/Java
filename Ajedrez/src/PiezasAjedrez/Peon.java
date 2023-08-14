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
    public static final int TAMdesFil = 2;
    public static final int TAMdesCol = 2;
     
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
     * realizarDesplazamiento()
     * Comprueba si se puede mover la figura a la casilla que queremos, en caso afirmativo actualiza las
     * coordenadas.
     * @param filaAnt: fila actual en la que se encuentra la pieza a mover
     * @param col: columna en la que se encuentra la pieza a mover
     * @param indxDes: indice del vector desplazamiento que indica el numero de casillas que se desplaza 
     * @return moverse: booleano, true si la pieza puede moverse
    */
    public boolean realizarDesplazamiento(int filaAnt,int col,int indxDes){
        boolean moverse=false;
        boolean rAccion=true;
        
        boolean hayPiezaPriCas=false;
        boolean hayPiezaSegCas=false;
            
        int desp=this.desplazamiento[blanco][indxDes];
        int fila=filaAnt+desp;
        
        rAccion=this.puedeRealizarAccion(false,true,0,desp);
        
        if(rAccion){
            hayPiezaPriCas=super.comprobarPieza(filaAnt+this.desplazamiento[blanco][0],col);
            hayPiezaSegCas=super.comprobarPieza(fila,col);
        }
        
        if(hayPiezaPriCas || hayPiezaSegCas){
            moverse=false;
        }else{
            this.coordenadas[0]=fila;
            moverse=true;
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
        int filaAnt=this.coordenadas[0];
        int colAnt=this.coordenadas[1];
        boolean moverFigura=true;

        if(this.inicio){
            indxDes=super.menuOpciones("Nº casillas a desplazar(1,2):",1,2);
        }
        moverFigura=this.realizarDesplazamiento(filaAnt,colAnt,indxDes-1); // el indice es 0,1.
        if(moverFigura){
            actualizarTablero(filaAnt,colAnt);
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
        int des=this.desplazamiento[blanco][0];
        int colIzq=-1;
        int colDcha=1;
        
        boolean[] comer=new boolean[Piezas.TAM_COORDENADAS];
        comer[0]=comer[1]=false;
        boolean pComerHIzq=true;
        boolean pComerDcha=true;
        
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int fila=filaAct+des;
        
        pComerHIzq=this.puedeRealizarAccion(true, true, colIzq,des);
        pComerDcha=this.puedeRealizarAccion(true, true, colDcha,des);
      
        if(pComerHIzq && super.comprobarPieza(fila,colAct+colIzq)){
            comer[0]=true;   
        }
        
        if(pComerDcha && super.comprobarPieza(fila,colAct+colDcha)){
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
