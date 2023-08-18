/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ajedrez;

import PiezasAjedrez.Alfil;
import PiezasAjedrez.Caballo;
import PiezasAjedrez.Peon;
import PiezasAjedrez.Piezas;
import PiezasAjedrez.Reina;
import PiezasAjedrez.Rey;
import PiezasAjedrez.Torre;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author javie
 */
public class Tablero extends javax.swing.JPanel {

   public static final int FILAS = 8;
   public static final int COLUMNAS = 8;
   private static JPanel[][] tablero;
   
   
    /**
     * Creates new form Tablero
     * Contructor de la clase tablero
     */
    public Tablero() {
        initComponents();
        tablero = new JPanel[FILAS][COLUMNAS];
        crearTablero();
        inicializarTablero();
    }
    
    /**
     * crearTablero()
     * Crea el tablero, es decir todas las casillas blancas y negras
     */
    private void crearTablero(){
        //Color de las casillas del tablero;
        Color casillaBlanca=Color.WHITE;
        Color casillaNegra=Color.BLACK;
        
        for(int fila=0;fila<FILAS;fila++){
            for(int col=0;col<COLUMNAS;col++){
                JPanel casilla=new JPanel();
                casilla.setBackground((fila + col) % 2 == 0 ? casillaBlanca : casillaNegra);
                this.add(casilla);
                tablero[fila][col]=casilla;
            }
        }
    }
    
    /**
     * inicializarTablero()
     * Inicializa el tablero, es decir introduce todas las figuras en él.
     */
    private void inicializarTablero(){
        ImageIcon iconPeonBlanco=new ImageIcon(getClass().getResource("/ImagenesFiguras/peonBlanco.png"));
        ImageIcon iconPeonNegro=new ImageIcon(getClass().getResource("/ImagenesFiguras/peonNegro.png"));
        
        InicializarFilaPeon(1,iconPeonNegro);
        InicializarFilaPeon(6,iconPeonBlanco);
        
        ImageIcon iconTorreBlanca=new ImageIcon(getClass().getResource("/ImagenesFiguras/torreBlanca.png"));
        ImageIcon iconTorreNegra=new ImageIcon(getClass().getResource("/ImagenesFiguras/torreNegra.png"));
        
        InicializarTorres(0,iconTorreNegra);
        InicializarTorres(7,iconTorreBlanca);
        
        ImageIcon iconCaballoBlanco=new ImageIcon(getClass().getResource("/ImagenesFiguras/caballoBlanco.png"));
        ImageIcon iconCaballoNegro=new ImageIcon(getClass().getResource("/ImagenesFiguras/caballoNegro.png"));
        
        InicializarCaballos(0,iconCaballoNegro);
        InicializarCaballos(7,iconCaballoBlanco);
        
        
        
        ImageIcon iconAlfilBlanco=new ImageIcon(getClass().getResource("/ImagenesFiguras/alfilBlanco.png"));
        ImageIcon iconAlfilNegro=new ImageIcon(getClass().getResource("/ImagenesFiguras/alfilNegro.png"));
        
        InicializarAlfiles(0,iconAlfilNegro);
        InicializarAlfiles(7,iconAlfilBlanco);
        
        ImageIcon iconReyBlanco=new ImageIcon(getClass().getResource("/ImagenesFiguras/reyBlanco.png"));
        ImageIcon iconReyNegro=new ImageIcon(getClass().getResource("/ImagenesFiguras/reyNegro.png"));
        
        InicializarRey(0,iconReyNegro);
        InicializarRey(7,iconReyBlanco);
        
        ImageIcon iconReinaBlanca=new ImageIcon(getClass().getResource("/ImagenesFiguras/reinaBlanca.png"));
        ImageIcon iconReinaNegra=new ImageIcon(getClass().getResource("/ImagenesFiguras/reinaNegra.png"));
        
        InicializarReina(0,iconReinaNegra);
        InicializarReina(7,iconReinaBlanca);
        
        
        Piezas.setTablero(Tablero.tablero);
    }
    
    /**
     * InicializarFilaPeon()
     * Inicializa las filas de los peones
     * @param fila: fila de los peones (1 peones negros, 6 peones blancos)
     * @param icon: icono de los peones(1 peones negros, 6 peones blancos)
     */
    private void InicializarFilaPeon(int fila, ImageIcon icon){
        for(int col=0;col<COLUMNAS;col++){
            Piezas peon=new Peon();
            peon.setIcono(icon);
            peon.setCoordenadas(fila,col);
            if(fila==1){
                peon.setColor(0);
            }
            tablero[fila][col].add(peon);
        }
    }
    
    /**
     * InicializarTorres
     * Inicializa las torres
     * @param fila: fila de las torres(0 torres negras, 7 torres blancas)
     * @param icon: icono de las torres(0 torres negras, 7 torres blancas)
     */
    private void InicializarTorres(int fila,ImageIcon icon){
        Piezas torreIzq=new Torre();
        Piezas torreDcha=new Torre();
        
        torreIzq.setIcono(icon);
        torreDcha.setIcono(icon);
        
        if(fila==0){
            torreIzq.setColor(0);
            torreDcha.setColor(0);
        }
        
        torreIzq.setCoordenadas(fila, 0);
        torreDcha.setCoordenadas(fila,COLUMNAS-1);
        tablero[fila][0].add(torreIzq);
        tablero[fila][COLUMNAS-1].add(torreDcha);    
    }
    
    /**
     * InicializarCaballos
     * Inicializa los caballos
     * @param fila: fila de los caballos
     * @param icon: icono de los caballos
     */
    
    private void InicializarCaballos(int fila,ImageIcon icon){
        Piezas caballoIzq=new Caballo();
        Piezas caballoDcha=new Caballo();
        
        caballoIzq.setIcono(icon);
        caballoDcha.setIcono(icon);
        
        if(fila==0){
            caballoIzq.setColor(0);
            caballoDcha.setColor(0);
        }
        
        caballoIzq.setCoordenadas(fila, 1);
        caballoDcha.setCoordenadas(fila,COLUMNAS-2);
        tablero[fila][1].add(caballoIzq);
        tablero[fila][COLUMNAS-2].add(caballoDcha);    
    }
    
    /**
     * InicializarAlfiles
     * Inicializa los alfiles
     * @param fila: fila de los alfiles
     * @param icon: icono de los alfiles
     */
    
    private void InicializarAlfiles(int fila,ImageIcon icon){
        Piezas alfilIzq=new Alfil();
        Piezas alfilDcha=new Alfil();
        
        alfilIzq.setIcono(icon);
        alfilDcha.setIcono(icon);
        
        if(fila==0){
            alfilIzq.setColor(0);
            alfilDcha.setColor(0);
        }
        
        alfilIzq.setCoordenadas(fila, 2);
        alfilDcha.setCoordenadas(fila,COLUMNAS-3);
        tablero[fila][2].add(alfilIzq);
        tablero[fila][COLUMNAS-3].add(alfilDcha);    
    }
    
    /**
     * InicializarRey
     * Inicializa los reyes
     * @param fila: fila de los reyes
     * @param icon: icono de los reyes
     */
    private void InicializarRey(int fila,ImageIcon icon){
        Piezas rey=new Rey();
        
        rey.setIcono(icon);
       
        if(fila==0){
            rey.setColor(0);
        }
        
        rey.setCoordenadas(fila, 4);
        tablero[fila][4].add(rey);
        
    }
    
    /**
     * InicializarReina
     * Inicializa los reyes
     * @param fila: fila de las reinas
     * @param icon: icono de las reinas
     */
    private void InicializarReina(int fila,ImageIcon icon){
        Piezas reina=new Reina();
        
        reina.setIcono(icon);
       
        if(fila==0){
            reina.setColor(0);
        }
        
        reina.setCoordenadas(fila, 3);
        tablero[fila][3].add(reina);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridLayout(8, 8));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
