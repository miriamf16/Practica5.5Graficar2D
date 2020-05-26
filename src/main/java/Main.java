
import Clases.Canvas;
import Clases.SistemaControl;
import com.sun.prism.paint.Color;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sun.misc.JavaLangAccess;

import javax.swing.*;
import java.awt.*;

public class Main extends Application {
    Stage MIVentana;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
            public void start(Stage Ventana){
        System.out.println("MAIN");
        SistemaControl Micontrol = new SistemaControl();
        int WIDTH = 300;
        int HEIGHT = 400;

        //JAVAFx
        MIVentana = Ventana;
        MIVentana.setTitle("Mini Juego con MiniEngine 2D 1.0");

        JLabel vida = new JLabel("Vida");
        JLabel vidavalor = new JLabel("Loading");
        JLabel continua = new JLabel();
        //vida.setFont(new java.awt.Font("Arial",1,20));
        //vidavalor.setFont(new java.awt.Font("Arial",1,20));


        //Conversion nodos JavaFx de los elemntos de swing
        SwingNode nodoVidaTexto = new SwingNode();
        nodoVidaTexto.setContent(vida);
        SwingNode nodoVidaValor = new SwingNode();
        nodoVidaValor.setContent(vidavalor);
        SwingNode nodoContinua = new SwingNode();
        nodoContinua.setContent(continua);


        Rectangle hpbarra = new Rectangle();
        Label vidat = new Label("Vida");
        Canvas mi_canvas = new Canvas(Micontrol,hpbarra,vidavalor);
        mi_canvas.setSize(WIDTH,HEIGHT);
        mi_canvas.init();

        SwingNode nodoCanvas = new SwingNode();
        nodoCanvas.setContent(mi_canvas);

        Separator linea = new Separator(Orientation.VERTICAL);
        Separator linea2 = new Separator(Orientation.VERTICAL);

        HBox toppanel = new HBox(vidat,linea,hpbarra,linea2,nodoVidaValor,nodoContinua);

        toppanel.setPrefWidth(WIDTH);
        toppanel.setPrefHeight(25);

        BorderPane layout = new BorderPane();
        layout.setTop(toppanel);
        layout.setCenter(nodoCanvas);

        /*Crear el evento de presionar una tecla*/

        EventHandler<InputEvent> teclas = inputEvent -> {
            Micontrol.Manejador(inputEvent,mi_canvas);
            inputEvent.consume();
        };

        /*se agrega evento a ventana*/
        MIVentana.addEventHandler(KeyEvent.KEY_PRESSED,teclas);
        MIVentana.addEventHandler(KeyEvent.KEY_RELEASED,teclas);

        Scene Area = new Scene(layout,WIDTH+10,HEIGHT+10);
        MIVentana.setScene(Area);
        MIVentana.show();
        MIVentana.setWidth(WIDTH);
        MIVentana.setHeight(HEIGHT);
    }

     @Override
    public void stop() throws  Exception{
        super.stop();
        System.exit(1);
     }


}

