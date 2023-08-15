/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PiezasAjedrez;

import static PiezasAjedrez.Piezas.tablero;
import java.awt.Component;
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
            moverse[0]=!(super.comprobarPieza(filaAct-desp,colAct)); //Figura de arriba
        }
        if(rAccion[1]){
            moverse[1]=!(super.comprobarPieza(filaAct,colAct+desp)); //Figura derecha
        }
        if(rAccion[2]){
            moverse[2]=!(super.comprobarPieza(filaAct+desp,colAct)); //Figura abajo
        } 
        if(rAccion[3]){
            moverse[3]=!(super.comprobarPieza(filaAct,colAct-desp)); //Figura izquierda
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
            if(orientacion==0 && this.puedeRealizarAccion(false,true,0,-pos)){
                if(super.comprobarPiezaColor(filaAct-pos,colAct,color)){
                    //Devuelve la posicion de la figura. Cuando se desplaza, se le resta 1 pues no puedes 
                    //atravesar la figura
                    nCasillas=pos; 
                    enc=true;
                }else{
                    //Porque esta devolviendo el numero de casillas libres sin salirse del tablero. 
                    //Como luego se resta -1, le añadimos +1 ahora.
                    nCasillas=pos+1; 
                }
            }
            
            if(orientacion==1 && this.puedeRealizarAccion(true,false,pos,0)){
                if(super.comprobarPiezaColor(filaAct,colAct+pos,color)){
                    nCasillas=pos;
                    enc=true;
                }else{
                    nCasillas=pos+1;
                }
            }
            
            if(orientacion==2 && this.puedeRealizarAccion(false,true,0,pos)){
                if(super.comprobarPiezaColor(filaAct+pos,colAct,color)){
                    nCasillas=pos;
                    enc=true;
                }else{
                    nCasillas=pos+1;
                }
            }
            
            if(orientacion==3 && this.puedeRealizarAccion(true,false,-pos,0)){
                if(super.comprobarPiezaColor(filaAct,colAct-pos,color)){
                    nCasillas=pos;
                    enc=true;
                }else{
                    nCasillas=pos+1;
                }
            }
        }
        
        return nCasillas;
    }
    
    @Override
    public void moverFigura() {
        int indxDes=-1;
        int filaAct=this.coordenadas[0];
        int nFil=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nCol=this.coordenadas[1];
        int desp=0;
        int nCasillasMax=0;
        String msgOrientacion=msgOrientacion="Seleccione la orientacion de desplazamiento\n"
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
                //Devuelve la casilla donde se halla una figura que interfiera,  por ello nCasillasMax-1
                //Si la figura se mueve, cualquier figura de cualquier color interfiere(nos quedamos con
                //el menor numero de casillas)
                if(this.calcularCasillasMax(orientacion,0)<this.calcularCasillasMax(orientacion,1)){
                     nCasillasMax=this.calcularCasillasMax(orientacion,0); 
                }else{
                    nCasillasMax=this.calcularCasillasMax(orientacion,1); 
                }
                
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
                nFil=filaAct-desp;
            }else if(orientacion==1){
                nCol=colAct+desp;
            }else if(orientacion==2){
                nFil=filaAct+desp;
            }else{
                nCol=colAct-desp;
            }
            
            this.coordenadas[0]=nFil;
            this.coordenadas[1]=nCol;
            super.actualizarTablero(filaAct,colAct);
           
        }
    }

    
    @Override
    public boolean[] puedeComer(){
        int color=0;
        if(this.blanco==0){
            color=1;
        }
        boolean[] comer=new boolean[4];
        int[] figAComer=new int[4];
        int[] figAmigo=new int[4];
        comer[0]=comer[1]=comer[2]=comer[3]=false; 
        for(int pos=0;pos<4;pos++){
            figAComer[pos]=this.calcularCasillasMax(pos,color);
            figAmigo[pos]=this.calcularCasillasMax(pos,this.blanco);
            if(figAComer[pos]<8 && figAComer[pos]<figAmigo[pos]){
                comer[pos]=true;  
            }
        }
        
        return comer;
    }
    
    @Override
    public void comerFigura() {
        int filaAct=this.coordenadas[0];
        int nFil=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nCol=this.coordenadas[1];
        int desp=0;
        int casFigAComer=-1;
        int color=0;
        if(this.blanco==0){
            color=1;
        }
        String msgOrientacion=msgOrientacion="Seleccione la orientacion en la que desea comer\n"
                            + "0: Arriba\n"
                            + "1: Derecha\n"
                            +"2: Abajo\n"
                            +"3: Izquierda: \n"
                            + "Opcion: ";
        int orientacion=0;
                
        boolean[] comerFigura=this.puedeComer();
        
        if(comerFigura[0] || comerFigura[1] || comerFigura[2] || comerFigura[3]){
            orientacion=super.menuOpciones(msgOrientacion,0,3);
            if(comerFigura[orientacion]){
                //Devuelve la casilla donde se halla la figura,  por ello nCasillasMax-1
                casFigAComer=this.calcularCasillasMax(orientacion,color);
            }else{
               System.out.println("La pieza no puede comer en la direccion que desea"); 
            }
        }else{
            System.out.println("La pieza no se puede mover");
        }
        
        
        if(casFigAComer>-1){
            desp=this.desplazamiento[0][casFigAComer-1];
            if(orientacion==0){
                nFil=filaAct-desp;
            }else if(orientacion==1){
                nCol=colAct+desp;
            }else if(orientacion==2){
                nFil=filaAct+desp;
            }else{
                nCol=colAct-desp;
            }
            
            Component piezaComida=this.tablero[nFil][nCol].getComponent(0);
            tablero[nFil][nCol].remove(piezaComida);
            tablero[nFil][nCol].repaint();
            
            this.coordenadas[0]=nFil;
            this.coordenadas[1]=nCol;
            super.actualizarTablero(filaAct,colAct);
        }
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
