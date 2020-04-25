package Clases;

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
    private  int fps = 60;
    private JLabel nlabel;
    private Mundo primernivel;


    /*SINGLETON*/
    private SistemaMovimiento Movimiento = SistemaMovimiento.GetInstancia();
    private  SistemaControl controles;

    public Canvas(SistemaControl evento ){
        super();
        this.delay = 1000/fps; //1s = 1000 ms
        miTimer = new Timer(this.delay,gameTimer);
        miTimer.start();
        controles = evento;
        nlabel = new JLabel("VIDAS");

    }

    private void DibujarImagenes(Objeto entidad ,Graphics2D g ){
        Objeto2D  miEntidad = (Objeto2D) entidad;

        AffineTransform tx = AffineTransform.getScaleInstance(1,1);

        BufferedImageOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage template = op.filter(miEntidad.GetSuperficie().GetImagen(),null);
        g.drawImage(template,op,(int)miEntidad.GetPosicion().x,(int)miEntidad.GetPosicion().y);

    }

    public void UpdateFrames(){
        this.repaint();
        //System.out.println("repitandondo frames");
    }

    /*Actualizar la pantalla*/
    public ActionListener gameTimer = actionEvent -> UpdateFrames();



    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        nlabel.setText("VIDA:"+primernivel.GetPersonaje().GetHPactual());
        primernivel.update();
        for(Objeto objs: primernivel.ObjetosMundo)
        {
            Movimiento.Update(objs);
            objs.MostrarColision((Graphics2D) g);

            DibujarImagenes(objs,(Graphics2D) g );
        }
    }

    public void init(){
        Movimiento.SetCanvasLimit(this.getWidth(),this.getHeight());
        primernivel = new Mundo();
        primernivel.init(controles,this.getWidth(),this.getHeight());
        this.add(nlabel);
    }

    public Mundo getPrimernivel() {
        return primernivel;
    }
}
