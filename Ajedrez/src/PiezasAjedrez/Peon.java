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
     * calcCasDisp()
     * Calcula el numero de casillas disponibles para moverse en las distintas
     * direcciones teniendo en cuenta las piezas de un solo color.
     */
    @Override
    public int[] calcCasDisp(int color){
        int[] nCasillas=new int[1];
        nCasillas[0]=2;
        int nFigs=3;
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        boolean salirBucle=false;
        
        for(int pos=1;pos<nFigs && !salirBucle;pos++){
            salirBucle=super.setNCasillas(nCasillas,0,0,this.desplazamiento[this.blanco][pos-1],
                    filaAct+this.desplazamiento[this.blanco][pos-1],colAct,color,pos);
        } 
         
        return nCasillas;
    }
    
    /**
    * calcCasMover()
    * Calcula en cada direccion el numero de casillas máximas disponibles para moverse
    * @return mover: vector int que contiene las casillas disponibles a las que moverse
    */
    @Override
    public int[] calcCasMover(){
        int[] distFigBlancas=this.calcCasDisp(1);
        int[] distFigNegras=this.calcCasDisp(0);
        
        if(distFigBlancas[0]<distFigNegras[0]){
            this.mover[0]=distFigBlancas[0];
        }else{
            this.mover[0]=distFigNegras[0];
        } 
        return this.mover;
    }
    
    /**
    * calcCasComer()
    * Calcula en cada direccion el numero de casillas necesarias para comer la figura (si la encuentra)
    * @return comer: vector int que contiene las casillas necesarias para comer las figuras
    */
    @Override
    public int[] calcCasComer(){
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int color=0;
        if(this.blanco==0){
            color=1;
        }
        int dist=this.desplazamiento[this.blanco][0];
        
        if(!super.salirseTablero(-1, dist) && super.comprobarPiezaColor(filaAct+dist,colAct-1,color)){
            this.comer[0]=1;
        }else{
            this.comer[0]=0;
        }
        if(!super.salirseTablero(1, dist) && super.comprobarPiezaColor(filaAct+dist,colAct+1,color)){
            this.comer[1]=1;
        }else{
            this.comer[1]=0;
        }
        return this.comer;
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

        if(this.inicio){
            indxDes=super.menuOpciones("Nº casillas a desplazar(1,2):",1,2);
        }
        this.mover=this.calcCasMover();
        if(this.mover[0]>=indxDes){
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
    * comerFigura()
    * Define la acción de comer una figura, en este caso define como come la figura: Peon
    */
    @Override
    public void comerFigura() {
        int des=this.desplazamiento[blanco][0];
        int colDcha=1;
        int colIzq=-1;
        int accion=0;
        
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nFil=filaAct+des;
        int nCol=colAct;
        this.comer=this.calcCasComer();
        if(this.comer[0]>0 && this.comer[1]>0){
            accion=super.menuOpciones("Elija figura a comer: izq(1),dcha(2):", 1, 2);
            if(accion==1){
                nCol=colAct+colIzq;
            }else{
                nCol=colAct+colDcha;
            }  
        }else if(this.comer[0]>0){
            nCol=colAct+colIzq;
        }else if(this.comer[1]>0){
            nCol=colAct+colDcha;
        }
        
        Component piezaComida=this.tablero[nFil][nCol].getComponent(0);
        tablero[nFil][nCol].remove(piezaComida);
        tablero[nFil][nCol].repaint();

        this.coordenadas[0]=nFil;
        this.coordenadas[1]=nCol;
        super.actualizarTablero(filaAct, colAct);
    }
    
    
}
