package Clases;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Objeto2D extends  Objeto {

    /*SUPERFICIE*/
    private Superficie DisplayImage;
    private BufferedImage imagen_inicial;

    //public int aceleracion;

    public Objeto2D(){
        super();
        this.DisplayImage = new Superficie();

    }

    public Objeto2D(String imagen){
        super();
        this.DisplayImage = new Superficie(imagen);
        this.imagen_inicial=this.GetSuperficie().GetImagen();
    }

    public Objeto2D(String imagen,double posx,double posy){
        super(posx,posy);
        this.DisplayImage = new Superficie(imagen);
        this.imagen_inicial=this.GetSuperficie().GetImagen();
    }

    public void LoadImagen(String archivo){
        this.DisplayImage.CargarImagen(archivo);
    }

    public Superficie GetSuperficie(){
        return this.DisplayImage;
    }

    public void SetEscalar(double factorX,double factorY){
        BufferedImage antes = this.GetSuperficie().GetImagen();
        int w =antes.getWidth();
        int h = antes.getHeight();
        BufferedImage despues = new BufferedImage((int)(w*factorX),(int)(h*factorY),BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(factorX, factorY);
        AffineTransformOp scaleOp = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
        despues = scaleOp.filter(antes,despues);

        this.GetSuperficie().SetImagen(despues);

    }

    public void ResetDimensiones(){
        this.GetSuperficie().SetImagen(this.imagen_inicial);
    }

    public void FlipVertical(){
        BufferedImage antes = this.GetSuperficie().GetImagen();
        int w =antes.getWidth();
        int h = antes.getHeight();
        BufferedImage despues = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(1,-1));
        at.concatenate(AffineTransform.getTranslateInstance(0,-h));
        AffineTransformOp scaleOp = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
        despues = scaleOp.filter(antes,despues);

        this.GetSuperficie().SetImagen(despues);
    }

    public void FlipHorizontal(){
        BufferedImage antes = this.GetSuperficie().GetImagen();
        int w =antes.getWidth();
        int h = antes.getHeight();
        BufferedImage despues = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(-1,1));
        at.concatenate(AffineTransform.getTranslateInstance(-w,0));
        AffineTransformOp scaleOp = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
        despues = scaleOp.filter(antes,despues);

        this.GetSuperficie().SetImagen(despues);
    }

    public void RotarImagen(double angulo){
        double rads = Math.toRadians(angulo);
        double sin =  Math.abs(Math.sin(rads)),cos = Math.abs(Math.cos(rads));

        BufferedImage before = this.GetSuperficie().GetImagen();
        int w = before.getWidth();
        int h = before.getHeight();

        int newWidth = (int) Math.floor(w*cos+h*sin);
        int newHeight = (int) Math.floor(h*cos+w*sin);

        BufferedImage despues = new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.translate((float)(newWidth-w)/2,(float)(newHeight-h)/2);
        int x = w / 2;
        int y = h / 2;
        at.rotate(rads,x,y);

        AffineTransformOp scaleOp = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
        despues = scaleOp.filter(before,despues);
        this.GetSuperficie().SetImagen(despues);
    }
}
