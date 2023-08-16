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
    
    public Torre(){
        super();
    }
    
    /**
     * calcCasDisp()
     * Calcula el numero de casillas disponibles para moverse en las distintas
     * direcciones teniendo en cuenta las piezas de un solo color.
     */
    @Override
    public int[] calcCasDisp(int color){
        int[] nCasillas=new int[ORIENTACIONES];
        nCasillas[0]=nCasillas[1]=nCasillas[2]=nCasillas[3]=7;
        int nFilsCols=8;
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        boolean salirBucle=false;
        
        for(int orientacion=0;orientacion<ORIENTACIONES;orientacion++){
            salirBucle=false;
            for(int pos=1;pos<nFilsCols && !salirBucle;pos++){
                //HACIA ARRIBA
                if(orientacion==0){
                    if(!super.salirseTablero(0, -pos)){
                        if(super.comprobarPiezaColor(filaAct-pos,colAct,color)){
                            nCasillas[orientacion]=pos-1; //Almacenamos la posicion de la pieza
                            salirBucle=true;
                        }
                    }else{
                        nCasillas[orientacion]=pos-1; //Almacenamos la posicion de la pieza
                        salirBucle=true;
                    }
                //HACIA LA DERECHA
                }else if(orientacion==1){
                    if(!super.salirseTablero(pos, 0)){
                        if(super.comprobarPiezaColor(filaAct,colAct+pos,color)){
                            nCasillas[orientacion]=pos-1; //Almacenamos la posicion de la pieza
                            salirBucle=true;
                        }
                    }else{
                        nCasillas[orientacion]=pos-1; //Almacenamos la posicion de la pieza
                        salirBucle=true;
                    }
                    
                //HACIA ABAJO
                }else if(orientacion==2){
                    if(!super.salirseTablero(0, pos)){
                        if(super.comprobarPiezaColor(filaAct+pos,colAct,color)){
                            nCasillas[orientacion]=pos-1; //Almacenamos la posicion de la pieza
                            salirBucle=true;
                        }
                    }else{
                        nCasillas[orientacion]=pos-1; //Almacenamos la posicion de la pieza
                        salirBucle=true;
                    }
                //HACIA LA IZQUIERDA
                }else{
                    if(!super.salirseTablero(-pos, 0)){
                        if(super.comprobarPiezaColor(filaAct,colAct-pos,color)){
                            nCasillas[orientacion]=pos-1; //Almacenamos la posicion de la pieza
                            salirBucle=true;
                        }
                    }else{
                        nCasillas[orientacion]=pos-1; //Almacenamos la posicion de la pieza
                        salirBucle=true;
                    }
                }
            } 
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
        
        for(int orientacion=0;orientacion<ORIENTACIONES;orientacion++){
            if(distFigBlancas[orientacion]<distFigNegras[orientacion]){
                this.mover[orientacion]=distFigBlancas[orientacion];
            }else{
                this.mover[orientacion]=distFigNegras[orientacion];
            } 
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
        int dist=0;
        if(this.blanco==0){
            color=1;
        }
        this.mover=calcCasMover();
        for(int orientacion=0;orientacion<ORIENTACIONES;orientacion++){
            dist=this.mover[orientacion]+1;
            if(orientacion==0 && !super.salirseTablero(0, -dist) && super.comprobarPiezaColor(filaAct-dist,colAct,color)){
                this.comer[orientacion]=dist;
            }else if(orientacion==1 && !super.salirseTablero(dist, 0) && super.comprobarPiezaColor(filaAct,colAct+dist,color)){
                this.comer[orientacion]=dist;
            }else if(orientacion==2 && !super.salirseTablero(0, dist) && super.comprobarPiezaColor(filaAct+dist,colAct,color)){
                this.comer[orientacion]=dist;
            }else if(orientacion==3 && !super.salirseTablero(-dist, 0) && super.comprobarPiezaColor(filaAct,colAct-dist,color)){
                this.comer[orientacion]=dist;
            }
        }
        
        return this.comer;
    }
    
     /**
    * MoverFigura()
    * Define el movimiento de la figura: Peon
    */
    @Override
    public void moverFigura() {
        int filaAct=this.coordenadas[0];
        int nFil=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nCol=this.coordenadas[1];
        int dist=0;
        String msgOrientacion=msgOrientacion="Seleccione la orientacion de desplazamiento\n"
                            + "0: Arriba\n"
                            + "1: Derecha\n"
                            +"2: Abajo\n"
                            +"3: Izquierda: \n"
                            + "Opcion: ";
        int orientacion=0;
        orientacion=super.menuOpciones(msgOrientacion,0,3);
        //this.mover=this.calcCasMover();
        if(this.mover[orientacion]>0){
            dist=super.menuOpciones("Nº casillas a desplazar(1,..,"+this.mover[orientacion]+"):",1,this.mover[orientacion]);
        }else{
            System.out.println("La pieza no se puede mover en esa direccion");
        }
        
        
        if(dist>0){
            if(orientacion==0){
                nFil=filaAct-dist;
            }else if(orientacion==1){
                nCol=colAct+dist;
            }else if(orientacion==2){
                nFil=filaAct+dist;
            }else{
                nCol=colAct-dist;
            }
            
            this.coordenadas[0]=nFil;
            this.coordenadas[1]=nCol;
            super.actualizarTablero(filaAct,colAct);
           
        }
    }
    
    /**
    * comerFigura()
    * Define la acción de comer una figura, en este caso define como come la figura: Peon
    */
    @Override
    public void comerFigura() {
        int filaAct=this.coordenadas[0];
        int nFil=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nCol=this.coordenadas[1];
        int dist=0;
        String msgOrientacion=msgOrientacion="Seleccione la orientacion para comer\n"
                            + "0: Arriba\n"
                            + "1: Derecha\n"
                            +"2: Abajo\n"
                            +"3: Izquierda: \n"
                            + "Opcion: ";
        int orientacion=0;
        orientacion=super.menuOpciones(msgOrientacion,0,3);
        this.comer=this.calcCasComer();
        if(this.comer[orientacion]>0){
            dist=this.comer[orientacion];
            if(orientacion==0){
                nFil=filaAct-dist;
            }else if(orientacion==1){
                nCol=colAct+dist;
            }else if(orientacion==2){
                nFil=filaAct+dist;
            }else{
                nCol=colAct-dist;
            }
            
            Component piezaComida=this.tablero[nFil][nCol].getComponent(0);
            tablero[nFil][nCol].remove(piezaComida);
            tablero[nFil][nCol].repaint();
            
            this.coordenadas[0]=nFil;
            this.coordenadas[1]=nCol;
            super.actualizarTablero(filaAct,colAct);
            
        }else{
            System.out.println("La pieza no puede en esa direccion");
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
