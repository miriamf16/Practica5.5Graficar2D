package Clases;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

public class Canvas extends JPanel {
    private int delay;
    private final Timer miTimer;
    private  int fps = 80;//80;//60
    private Mundo primernivel;


    /*SINGLETON*/
    private SistemaMovimiento Movimiento = SistemaMovimiento.GetInstancia();
    private  SistemaControl controles;
    //added
    private final JLabel textoVida;
    private final Rectangle barrahp;
    //private final Rectangle fondo;
    private boolean pausa;
    private boolean flag;
    private JLabel textoContinua;

    public Canvas(SistemaControl evento, javafx.scene.shape.Rectangle vida,JLabel vidatexto ){
        super();
        this.delay = 1000/fps; //1s = 1000 ms
        miTimer = new Timer(this.delay,gameTimer);
        miTimer.start();
        controles = evento;
        textoVida=vidatexto;
        barrahp = vida;
        pausa = false;
        flag = false;
        textoContinua = new JLabel("Presiona M para continuar ");
        textoContinua.setForeground(java.awt.Color.white);
        textoContinua.setFont(new java.awt.Font("Arial", 3, 20));
        this.add(textoContinua);
        textoContinua.setVisible(false);



    }

    public void init(){

            Movimiento.SetCanvasLimit(this.getWidth(), this.getHeight());
                 primernivel = new Mundo();
            primernivel.init(controles, this.getWidth(), this.getHeight(), pausa);


    }

    @Override
    public void paintComponent(Graphics g) {

        if (pausa == false) {

            super.paintComponent(g);

            if (primernivel.GetPersonaje().GetHPactual() != 0) {
                primernivel.update();

                for (Objeto objs : primernivel.ObjetosMundo) {
                    Movimiento.Update(objs);
                    objs.MostrarColision((Graphics2D) g);
                    DibujarImagenes(objs, (Graphics2D) g);

                }

                if (barrahp != null) {
                    barrahp.setHeight(20);
                    barrahp.setWidth(this.primernivel.GetPersonaje().GetHPactual() * 1.5);
                    textoVida.setText(String.valueOf(this.primernivel.GetPersonaje().GetHPactual()));

                    if (primernivel.GetPersonaje().GetHPactual() >= 100 || primernivel.GetPersonaje().GetHPactual() > 75) {
                        barrahp.setFill(Color.SEAGREEN);
                    } else if (primernivel.GetPersonaje().GetHPactual() <= 75 && primernivel.GetPersonaje().GetHPactual() >= 60) {
                        barrahp.setFill(Color.ORANGE);
                    } else if (primernivel.GetPersonaje().GetHPactual() <= 59 && primernivel.GetPersonaje().GetHPactual() > 30) {

                        barrahp.setFill(Color.DARKRED);
                    } else
                        barrahp.setFill(Color.DARKVIOLET);

                }

            }

        if (primernivel.GetPersonaje().GetHPactual() == 0)
        {
                this.setBackground(java.awt.Color.black);
                textoContinua.setVisible(true);
                primernivel.Reset();
        }
        }
    }

    private void DibujarImagenes(Objeto entidad ,Graphics2D g ){
            Objeto2D miEntidad = (Objeto2D) entidad;

            AffineTransform tx = AffineTransform.getScaleInstance(1, 1);

            BufferedImageOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            BufferedImage template = op.filter(miEntidad.GetSuperficie().GetImagen(), null);
            g.drawImage(template, op, (int) miEntidad.GetPosicion().x, (int) miEntidad.GetPosicion().y);

    }

    public void UpdateFrames(){
        textoContinua.setVisible(false);
        this.repaint();
        //System.out.println("repitandondo frames");

    }

    /*Actualizar la pantalla*/
    public ActionListener gameTimer = actionEvent -> UpdateFrames();


    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    public boolean getPausa() {
        return pausa;
    }

    public Mundo getPrimernivel() {
        return primernivel;
    }
}
