/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PiezasAjedrez;

import static PiezasAjedrez.Piezas.tablero;
import java.awt.Component;

/**
 *
 * @author javie
 */
public class Alfil extends Piezas{
    
    public Alfil(){
        super();
    }
    /**
     * calcCasDisp()
     * Calcula el numero de casillas disponibles para moverse en las distintas
     * direcciones teniendo en cuenta las piezas de un solo color.
     * @param color: color de las figuras que intenta buscar
     * @return nCasillas: numero de casillas que puede desplazarse en cada direccion(solo las 4 últimas posiciones)
     */
    @Override
    public int[] calcCasDisp(int color) {
        int[] nCasillas=new int[ORIENTACIONES];
        int filaAct=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nFilsCols=8;
        boolean salirBucle=false;
        for(int orientacion=0;orientacion<ORIENTACIONES;orientacion++){
            nCasillas[orientacion]=7;
            salirBucle=false;
            for(int pos=1;pos<nFilsCols && !salirBucle;pos++){
                //HACIA ARRIBA DERECHA
                if(orientacion==4){
                    salirBucle=super.setNCasillas(nCasillas,orientacion,pos,-pos,filaAct-pos,colAct+pos,color,pos);   
                //HACIA  ABAJO DERECHA
                }else if(orientacion==5){
                    salirBucle=super.setNCasillas(nCasillas,orientacion,pos,pos,filaAct+pos,colAct+pos,color,pos);
                //HACIA ABAJO IZQUIERDA
                }else if(orientacion==6){
                     salirBucle=super.setNCasillas(nCasillas,orientacion,-pos,pos,filaAct+pos,colAct-pos,color,pos);
                //HACIA ARRIBA IZQUIERDA
                }else if(orientacion==7){
                    salirBucle=super.setNCasillas(nCasillas,orientacion,-pos,-pos,filaAct-pos,colAct-pos,color,pos);
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

     /**
    * calcCasComer()
    * Calcula en cada direccion el numero de casillas necesarias para comer la figura (si la encuentra)
    * @return comer: vector int que contiene las casillas necesarias para comer las figuras
    */
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
            dist=this.mover[orientacion]+1;
            if(orientacion==4 && !super.salirseTablero(dist, -dist) && super.comprobarPiezaColor(filaAct-dist,colAct+dist,color)){
                this.comer[orientacion]=dist;
            }else if(orientacion==5 && !super.salirseTablero(dist, dist) && super.comprobarPiezaColor(filaAct+dist,colAct+dist,color)){
                this.comer[orientacion]=dist;
            }else if(orientacion==6 && !super.salirseTablero(-dist, dist) && super.comprobarPiezaColor(filaAct+dist,colAct-dist,color)){
                this.comer[orientacion]=dist;
            }else if(orientacion==7 && !super.salirseTablero(-dist, -dist) && super.comprobarPiezaColor(filaAct-dist,colAct-dist,color)){
                this.comer[orientacion]=dist;
            }
        }
        
        return this.comer;
    }
    /**
    * MoverFigura()
    * Define el movimiento de la figura: Reina
    */
    @Override
    public void moverFigura() {
       int filaAct=this.coordenadas[0];
        int nFil=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nCol=this.coordenadas[1];
        int dist=0;
        String msgOrientacion="Seleccione la orientacion de desplazamiento\n"
                            + "4: Arriba Dcha\n"
                            + "5: Abajo Dcha\n"
                            + "6: Abajo Izq\n"
                            + "7: Arriba Izq \n"
                            + "Opcion: ";
        int orientacion=0;
        orientacion=super.menuOpciones(msgOrientacion,4,7);
        //this.mover=this.calcCasMover();
        if(this.mover[orientacion]>0){
            dist=super.menuOpciones("Nº casillas a desplazar(1,..,"+this.mover[orientacion]+"):",1,this.mover[orientacion]);
            nFil=actCoordenadasTrasAccion(orientacion,dist)[0];
            nCol=actCoordenadasTrasAccion(orientacion,dist)[1];
            
            this.coordenadas[0]=nFil;
            this.coordenadas[1]=nCol;
            super.actualizarTablero(filaAct,colAct);
        }else{
            System.out.println("La pieza no se puede mover en esa direccion");
        }
    }
    
    /**
    * comerFigura()
    * Define la acción de comer una figura, en este caso define como come la figura: Reina
    */
    @Override
    public void comerFigura() {
        int filaAct=this.coordenadas[0];
        int nFil=this.coordenadas[0];
        int colAct=this.coordenadas[1];
        int nCol=this.coordenadas[1];
        int dist=0;
        String msgOrientacion="Seleccione la orientacion de desplazamiento\n"
                            + "4: Arriba Dcha\n"
                            + "5: Abajo Dcha\n"
                            + "6: Abajo Izq\n"
                            + "7: Arriba Izq \n"
                            + "Opcion: ";
        int orientacion=0;
        orientacion=super.menuOpciones(msgOrientacion,4,7);
        //this.comer=this.calcCasComer();
        
        if(this.comer[orientacion]>0){
            dist=this.comer[orientacion];
            nFil=actCoordenadasTrasAccion(orientacion,dist)[0];
            nCol=actCoordenadasTrasAccion(orientacion,dist)[1];
            
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
    
}
