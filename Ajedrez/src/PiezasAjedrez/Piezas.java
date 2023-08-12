/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PiezasAjedrez;

import static ajedrez.Tablero.COLUMNAS;
import static ajedrez.Tablero.FILAS;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


/**
 *
 * @author javie
 * La clase piezas surge como resultado de la necesidad de usar herencia múltiple debido
 * a la herencia por parte de JPanel y de Figuras. Es necesario la creación de esta clase, ya que las
 * variables de una clase interface son static, final y public por defecto. Al ser final, el tablero no se
 * puede modificar una vez declarado, pero yo necesito un atributo tablero que compartan todas las piezas del
 * ajedrez, y que puedan modificar este.
 */
public abstract class Piezas extends javax.swing.JPanel implements Figuras{
    
    static JPanel[][] tablero; // La interfaz que muestra el tablero
    int[] coordenadas; // Coordenadas da cada pieza
    boolean blanco; // Si sonde color blanco o negro
    Scanner entradaDatos; //Entrada de datos, para el menu acciones
    /**
     * Creates new form Piezas
     * Constructor de la clase piezas
     */
    public Piezas() {
        initComponents();
        this.tablero= new JPanel[FILAS][COLUMNAS];
        this.blanco=true;
        this.coordenadas = new int[Piezas.TAM_COORDENADAS];
        this.entradaDatos = new Scanner(System.in);
    }
    
    /**
    * SetTablero
    * Establece el tablero al que acceden todas las piezas. 
    * Se llama una vez todas las piezas han sido colocadas
    * @param tablero: tablero de ajedrez tras haber añadido todas las piezas
    */
    
    public static void setTablero(JPanel[][] tablero) {
        Piezas.tablero=tablero;
    }
    
    /**
     * actualizartablero()
     * Actualiza el tablero tras mover o comer una figura/pieza.
     * @param filaAnt: fila actual en la que se encuentra la pieza a mover
     * @param col: columna en la que se encuentra la pieza a mover 
     */
    public abstract void actualizarTablero(int filaAnt,int col);
    
    /**
    * comprobarPieza()
    * Comprueba si hay una pieza en las coordenadas dadas
    * @param fila: fila donde hay que comprobar si se halla una pieza
    * @param columna: columna donde hay que comprobar si se halla una pieza
    * @return pieza: booleano, true si hay una pieza en la casilla a comprobar, false si no.
    */
    boolean comprobarPieza(int fila, int col){
        boolean pieza=false;
        if(this.tablero[fila][col].getComponents().length>0){
            /*System.out.println("NO SE PUEDE MOVER");*/
            pieza=true;
        }
        return pieza;
    }
    
    /**
    * puedeRealizarAccion()
    * Comprueba si la pieza pieza se sale del tablero al realizar una accion
    * @param desH: True si la accion desencadena un movimiento horizontal
    * @param desV: True si la accion desencadena un movimiento vertical
    * @param disH: numero de casillas que se desplaza en horizontal
    * @param disV: numero de casillas que se desplaza en vertical
    * @param realizarAccion: True si se puede mover la figura,False si no.
    * @return pieza: booleano, true si hay una pieza en la casilla a comprobar, false si no.
    */
    public boolean puedeRealizarAccion(boolean desH,boolean desV,int disH,int disV){
        boolean realizarAccion=true;
        if(desH){
            if(coordenadas[1]+disH>(COLUMNAS-1) || coordenadas[1]+disH<0){
                realizarAccion=false;
            }
        }
        
        if(desV){
            if(coordenadas[0]+disV>(FILAS-1) || coordenadas[0]+disV<0){
                realizarAccion=false;
            }
        }
        return realizarAccion;
    }
    /**
    * puedeComer()
    * Comprueba si la figura puede comerse a otra
    * @return comer: booleano, true si se puede comer una pieza, false si no.
    */
    public abstract boolean[] puedeComer();
       
    /**
    * escogerDesplazamiento()
    * Permite escoger el desplazamiento de las piezas
    * Cuantas casillas avanza
    */
    public abstract int escogerDesplazamiento();
    
    /**
     * realizarDesplazamiento()
     * Comprueba si se puede mover la figura a la casilla que queremos, en caso afirmativo actualiza las
     * coordenadas.
     * @param filaAnt: fila actual en la que se encuentra la pieza a mover
     * @param col: columna en la que se encuentra la pieza a mover
     * @param desplazamiento: cantidad de casillas que la figura se desplaza en caso de poderse 
     * @return moverse: booleano, true si la pieza puede moverse
    */
    public abstract boolean realizarDesplazamiento(int filaAnt,int col,int desplazamiento);
    
    /**
    * MoverFigura()
    * Define el movimiento de cada figura
    */
    @Override
    public abstract void moverFigura();
       
    /**
    * comerFigura()
    * Define la acción de comer una figura
    */
    @Override
    public abstract void comerFigura();

    /**
    * setCoordenadas()
    * Establece las coordenadas de las piezas
    * @param fila: fila del tablero en la que se encuentra la pieza
    * @param columna: columna del tablero en la que se encuentra la pieza
    */
    
    
    
    
    @Override
    public abstract void setCoordenadas(int fila,int columna);
    
    /**
    * setIcono()
    * Establece el icono de cada pieza
    * @param icono: imagen del paquete ImagenesFiguras que se corresponde con el icono
    */
    
    @Override
    public abstract void setIcono(ImageIcon icono);
    
    /**
    * setColor()
    * Establece el color de la pieza (true = blanco, false = negro)
    * @param blanco: booleano; true = blanco, false = negro
    */
    
    @Override
    public abstract void setColor(boolean blanco);
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iconoFigura = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(40, 40));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(40, 40));
        setLayout(new java.awt.GridLayout(1, 0));

        iconoFigura.setBackground(new java.awt.Color(255, 255, 255));
        iconoFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesFiguras/peonNegro.png"))); // NOI18N
        iconoFigura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iconoFiguraActionPerformed(evt);
            }
        });
        add(iconoFigura);
    }// </editor-fold>//GEN-END:initComponents

    /**
    * iconoFiguraActionPerformed()
    * Cuando pulsamos en la figura, esta se mueve o despliega un menu acerca de como moverse
    * @param evt: evento que surge de clicar el boton de la pieza.
    */
    private void iconoFiguraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iconoFiguraActionPerformed
        boolean[] pcomer=this.puedeComer();
        if(pcomer[0] || pcomer[1]){
            int accion=menuAcciones();
            if(accion==1){
                this.comerFigura();
            }else{
                this.moverFigura();
            }
        }else{
            this.moverFigura();
        }
        
    }//GEN-LAST:event_iconoFiguraActionPerformed
    
    /**
    * menuAcciones()()
    * Cuando pulsamos en la figura, esta se mueve o despliega un menu acerca de como moverse
    * @param accion: accion que puede hacer la figura : 1 comer, 2 moverse 
    */
    Integer menuAcciones(){
        int accion=0;
        while(accion<1 || accion>2){
            System.out.print("Ingresa la accion(1 comer o 2 moverse): ");
            accion = this.entradaDatos.nextInt();
            System.out.println("Accion escogida: " + accion);
        }
        
        return accion;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton iconoFigura;
    // End of variables declaration//GEN-END:variables

    
}
