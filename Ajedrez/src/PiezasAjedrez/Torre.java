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
    
    private int[][] desplazamiento; //Controla el desplazamiento de los peones
    private  final int TAMdesFil = 1;
    private final int TAMdesCol = 7;
    
    public Torre(){
        super();
        this.desplazamiento=new int[TAMdesFil][TAMdesCol];
        this.crearDesplazamiento();
    }
    
    private void crearDesplazamiento(){
        for(int indx=0;indx<TAMdesCol;indx++){
            this.desplazamiento[0][indx]=indx+1;
        }
    }
    
   /**
    * puedeMover()
    * @return moverse: vector boleano que indica si puede moverse en las diversas direccions
    */
    @Override
    public boolean[] puedeMover(){
        boolean[] moverse=new boolean[4];
        moverse[0]=moverse[1]=moverse[2]=moverse[3]=false;
        boolean[] rAccion=new boolean[4];
        
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int desp=1; //Comprobar que se puede desplazar minimo una casilla
        
        rAccion[0]=this.puedeRealizarAccion(false,true,0,-desp);
        rAccion[1]=this.puedeRealizarAccion(true,false,desp,0);
        rAccion[2]=this.puedeRealizarAccion(false,true,0,desp); 
        rAccion[3]=this.puedeRealizarAccion(true,false,-desp,0);
         
        if(rAccion[0]){
            moverse[0]=!(super.comprobarPiezaColor(filaAct-desp,colAct,this.blanco)); //Figura de arriba
        }
        if(rAccion[1]){
            moverse[1]=!(super.comprobarPiezaColor(filaAct,colAct+desp,this.blanco)); //Figura derecha
        }
        if(rAccion[2]){
            moverse[2]=!(super.comprobarPiezaColor(filaAct+desp,colAct,this.blanco)); //Figura abajo
        } 
        if(rAccion[3]){
            moverse[3]=!(super.comprobarPiezaColor(filaAct,colAct-desp,this.blanco)); //Figura izquierda
        }            
        return moverse;
    }
    
    int calcularCasillasMax(int orientacion,int color){
        int nCasillas=0;
        int nFilsCols=8;
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        
        boolean enc=false;
        for(int pos=1;pos<nFilsCols && !enc;pos++){ 
            
            if((orientacion==0 && this.puedeRealizarAccion(false,true,0,-pos) && super.comprobarPiezaColor(filaAct-pos,colAct,color))||
               (orientacion==1 && this.puedeRealizarAccion(true,false,pos,0)  && super.comprobarPiezaColor(filaAct,colAct+pos,color))||
               (orientacion==2 && this.puedeRealizarAccion(false,true,0,pos)  && super.comprobarPiezaColor(filaAct+pos,colAct,color))||
               (orientacion==3 && this.puedeRealizarAccion(true,false,-pos,0) && super.comprobarPiezaColor(filaAct,colAct-pos,color))
              ){
                nCasillas=pos;
                enc=true;
            }
        }
        
        if(!enc){
            nCasillas=8; //Luego se usa nCasillas-1=7 por eso es 8 y no 7
        }
        return nCasillas;
    }
    
    @Override
    public void moverFigura() {
        int indxDes=-1;
        int filaAct=this.coordenadas[0];
        int nFila=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nCol=this.coordenadas[1];
        int desp=0;
        int nCasillasMax=0;
        String msgOrientacion=msgOrientacion="Seleccione una orientacion de desplazamiento\n"
                            + "0: Arriba\n"
                            + "1: Derecha\n"
                            +"2: Abajo\n"
                            +"3: Izquierda: \n"
                            + "Opcion: ";
        int orientacion=0;
                
        boolean[] moverFigura=this.puedeMover();
        
        if(moverFigura[0] || moverFigura[1] || moverFigura[2] || moverFigura[3]){
            orientacion=super.menuOpciones(msgOrientacion,0,3);
            if(moverFigura[orientacion]){
                //Devuelve la casilla donde se halla la figura,  por ello nCasillasMax-1
                nCasillasMax=this.calcularCasillasMax(orientacion,this.blanco); 
                indxDes=super.menuOpciones("Nº casillas a desplazar(1,..,"+(nCasillasMax-1)+"):",1,nCasillasMax-1);
            }else{
               System.out.println("La pieza no se puede mover en la direccion que desea"); 
            }
        }else{
            System.out.println("La pieza no se puede mover");
        }
        
        
        if(indxDes>-1){
            desp=this.desplazamiento[0][indxDes-1];
            if(orientacion==0){
                nFila=filaAct-desp;
            }else if(orientacion==1){
                nCol=colAct+desp;
            }else if(orientacion==2){
                nFila=filaAct+desp;
            }else{
                nCol=colAct-desp;
            }
            
            this.coordenadas[0]=nFila;
            this.coordenadas[1]=nCol;
            super.actualizarTablero(filaAct,colAct);
           
        }
    }

    
    @Override
    public boolean[] puedeComer() {
        boolean[] comer=new boolean[4];
        comer[0]=comer[1]=comer[2]=comer[3]=false; 
        return comer;
    }
    
    @Override
    public void comerFigura() {
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
