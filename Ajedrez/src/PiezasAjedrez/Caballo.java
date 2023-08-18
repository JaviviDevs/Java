/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PiezasAjedrez;

import java.awt.Component;

/**
 *
 * @author javie
 */
public class Caballo extends Piezas{

    /**
     * calcCasDisp()
     * Calcula el numero de casillas disponibles para moverse en las distintas
     * direcciones teniendo en cuenta las piezas de un solo color.
     * @param color: color de las figuras que intenta buscar
     * @return nCasillas: numero simbolico de casillas. (5 indica casilla libre, 
     * 4 indica que se sale del tablero o que hay una figura).
     */
    @Override
    public int[] calcCasDisp(int color) {
        int[] nCasillas=new int[ORIENTACIONES];
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int valor=5;
        for(int orientacion=0;orientacion<ORIENTACIONES;orientacion++){
            nCasillas[orientacion]=6;
            //L HORIZONTAL ARRIBA DERECHA
            if(orientacion==0){
                super.setNCasillas(nCasillas,orientacion,2,-1,filaAct-1,colAct+2,color,valor);
            //L HORIZONTAL ABAJO DERECHA
            }else if(orientacion==1){
                super.setNCasillas(nCasillas,orientacion,2,1,filaAct+1,colAct+2,color,valor);
            //L HORIZONTAL ABAJO IZQUIERDA
            }else if(orientacion==2){
                super.setNCasillas(nCasillas,orientacion,-2,1,filaAct+1,colAct-2,color,valor);
            //L HORIZONTAL ARRIBA IZQUIERDA
            }else if(orientacion==3){
                super.setNCasillas(nCasillas,orientacion,-2,-1,filaAct-1,colAct-2,color,valor); 
            //L VERTICAL ARRIBA DERECHA
            }else if(orientacion==4){
                super.setNCasillas(nCasillas,orientacion,1,-2,filaAct-2,colAct+1,color,valor);
            //L VERTICAL ABAJO DERECHA
            }else if(orientacion==5){
                super.setNCasillas(nCasillas,orientacion,1,2,filaAct+2,colAct+1,color,valor);
            //L VERTICAL ABAJO IZQUIERDA
            }else if(orientacion==6){
                super.setNCasillas(nCasillas,orientacion,-1,2,filaAct+2,colAct-1,color,valor);
            //L VERTICAL ARRIBA IZQUIERDA
            }else if(orientacion==7){
                super.setNCasillas(nCasillas,orientacion,-1,-2,filaAct-2,colAct-1,color,valor);
            }
        }
         
        return nCasillas;
    }

    @Override
    public int[] calcCasMover() {
        int[] distFigBlancas=this.calcCasDisp(1);
        int[] distFigNegras=this.calcCasDisp(0);
        
        for(int orientacion=0;orientacion<ORIENTACIONES;orientacion++){
            if(distFigBlancas[orientacion]<distFigNegras[orientacion]){
                this.mover[orientacion]=distFigBlancas[orientacion];
            }else{
                this.mover[orientacion]=distFigNegras[orientacion];
            } 
        }
        
        /*for(int orientacion=0;orientacion<ORIENTACIONES;orientacion++){
            System.out.println(this.mover[orientacion]);
        }*/
        return this.mover;
    }

    @Override
    public int[] calcCasComer() {
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int color=0;
        int dist=0;
        if(this.blanco==0){
            color=1;
        }
        
        this.mover=calcCasMover();
        for(int orientacion=0;orientacion<ORIENTACIONES;orientacion++){
            dist=this.mover[orientacion];
            if(dist<5){
                if(orientacion==0 && !super.salirseTablero(2, -1) && super.comprobarPiezaColor(filaAct-1,colAct+2,color)){
                    this.comer[orientacion]=dist;
                }else if(orientacion==1 && !super.salirseTablero(2, 1) && super.comprobarPiezaColor(filaAct+1,colAct+2,color)){
                    this.comer[orientacion]=dist;
                }else if(orientacion==2 && !super.salirseTablero(-2, 1) && super.comprobarPiezaColor(filaAct+1,colAct-2,color)){
                    this.comer[orientacion]=dist;
                }else if(orientacion==3 && !super.salirseTablero(-2, -1) && super.comprobarPiezaColor(filaAct-1,colAct-2,color)){
                    this.comer[orientacion]=dist;
                }else if(orientacion==4 && !super.salirseTablero(1, -2) && super.comprobarPiezaColor(filaAct-2,colAct+1,color)){
                    this.comer[orientacion]=dist;
                }else if(orientacion==5 && !super.salirseTablero(1, 2) && super.comprobarPiezaColor(filaAct+2,colAct+1,color)){
                    this.comer[orientacion]=dist;
                }else if(orientacion==6 && !super.salirseTablero(-1, 2) && super.comprobarPiezaColor(filaAct+2,colAct-1,color)){
                    this.comer[orientacion]=dist;
                }else if(orientacion==7 && !super.salirseTablero(-1, -2) && super.comprobarPiezaColor(filaAct-2,colAct-1,color)){
                    this.comer[orientacion]=dist;
                }
            }
           
        }
        
        return this.comer;
    }

    @Override
    public void moverFigura() {
        int filaAct=this.coordenadas[0];
        int nFil=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nCol=this.coordenadas[1];
        
        String msgOrientacion="Seleccione la orientacion de desplazamiento\n"
                            + "0: L horizontal arriba dcha\n"
                            + "1: L horizontal abajo dcha\n"
                            + "2: L horizontal abajo izq\n"
                            + "3: L horizontal arriba  izq \n"
                            + "4: L vertical arriba dcha\n"
                            + "5: L vertical abajo dcha\n"
                            + "6: L vertical abajo izq\n"
                            + "7: L vertical arriba Izq \n"
                            + "Opcion: ";
        int orientacion=0;
        orientacion=super.menuOpciones(msgOrientacion,0,7);
        //this.mover=this.calcCasMover();
        if(this.mover[orientacion]>4){
            nFil=actCoordenadasTrasAccionCaballo(orientacion)[0];
            nCol=actCoordenadasTrasAccionCaballo(orientacion)[1];
            
            this.coordenadas[0]=nFil;
            this.coordenadas[1]=nCol;
            super.actualizarTablero(filaAct,colAct);
        }else{
            System.out.println("La pieza no se puede mover en esa direccion");
        }
    }

    @Override
    public void comerFigura() {
        int filaAct=this.coordenadas[0];
        int nFil=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nCol=this.coordenadas[1];
        String msgOrientacion="Seleccione la orientacion de desplazamiento\n"
                            + "0: L horizontal arriba dcha\n"
                            + "1: L horizontal abajo dcha\n"
                            + "2: L horizontal abajo izq\n"
                            + "3: L horizontal arriba  izq \n"
                            + "4: L vertical arriba dcha\n"
                            + "5: L vertical abajo dcha\n"
                            + "6: L vertical abajo izq\n"
                            + "7: L vertical arriba Izq \n"
                            + "Opcion: ";
        int orientacion=0;
        orientacion=super.menuOpciones(msgOrientacion,0,7);
        //this.comer=this.calcCasComer();
        
        if(this.comer[orientacion]>0){
            nFil=actCoordenadasTrasAccionCaballo(orientacion)[0];
            nCol=actCoordenadasTrasAccionCaballo(orientacion)[1];
            
            Component piezaComida=this.tablero[nFil][nCol].getComponent(0);
            tablero[nFil][nCol].remove(piezaComida);
            tablero[nFil][nCol].repaint();
            
            this.coordenadas[0]=nFil;
            this.coordenadas[1]=nCol;
            super.actualizarTablero(filaAct,colAct);
            
        }else{
            System.out.println("La pieza no puede comer en esa direccion");
        }
    
    }
    
    /**
     * actCoordenadasTrasAccionCaballo()
     * Dependiendo de la orientacion actualiza las coordenadas del caballo tras una accion (mover o comer)
     * @param orientacion: orientacion del desplazamiento 
     * @return nCoordenas: coordenadas nuevas de la figura
     */
    public int[] actCoordenadasTrasAccionCaballo(int orientacion){
        int[] nCoordenadas=new int[Piezas.TAM_COORDENADAS];
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        
        if(orientacion==0){
                nCoordenadas[0]=filaAct-1;
                nCoordenadas[1]=colAct+2;
            }else if(orientacion==1){
                nCoordenadas[0]=filaAct+1;
                nCoordenadas[1]=colAct+2;
            }else if(orientacion==2){
                nCoordenadas[0]=filaAct+1;
                nCoordenadas[1]=colAct-2;
            }else if(orientacion==3){
                nCoordenadas[0]=filaAct-1;
                nCoordenadas[1]=colAct-2;
            }else if(orientacion==4){
                nCoordenadas[0]=filaAct-2;
                nCoordenadas[1]=colAct+1;
            }else if(orientacion==5){
                nCoordenadas[0]=filaAct+2;
                nCoordenadas[1]=colAct+1;
            }else if(orientacion==6){
                nCoordenadas[0]=filaAct+2;
                nCoordenadas[1]=colAct-1;
            }else if(orientacion==7){
                nCoordenadas[0]=filaAct-2;
                nCoordenadas[1]=colAct-1;
            }
        return nCoordenadas;
    }
}
