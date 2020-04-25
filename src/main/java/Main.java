
import Clases.Canvas;
import Clases.Mundo;
import Clases.SistemaControl;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        SistemaControl Micontrol = new SistemaControl();

        int WIDTH = 300;
        int HEIGHT = 400; //400



        System.out.println("MAIN");
        Canvas mi_canvas = new Canvas(Micontrol);
        mi_canvas.setSize(WIDTH,HEIGHT);
        mi_canvas.init(); // INICIALIZA toodo


        JFrame marco = new JFrame("Canvas");
        marco.getContentPane().add(mi_canvas);
        marco.setSize(WIDTH+50,HEIGHT+50); // Se agrega 50 para barra de cerrar ventana

        marco.addKeyListener(Micontrol);
        marco.getContentPane().add(BorderLayout.CENTER,mi_canvas);
        marco.setVisible(true);
        marco.setResizable(false);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
