package Clases;

import java.awt.*;

public class Objeto2DDinamico extends  Objeto2D {
    /*OBJETOS DINAMICOS TIENEN COLISION*/
    private CuadroColision mi_cuadro_colision;

    public Objeto2DDinamico(){
        super();
        this.es_movible=true;
    }

    public Objeto2DDinamico(String imagen){
        super(imagen);
        this.es_movible=true;

    }

    public Objeto2DDinamico(String imagen, double posx, double posy){
        super(imagen,posx,posy);
        this.es_movible=true;

        double x1= this.GetPosicion().x;
        double y1 = this.GetPosicion().y;
        double x2 = x1 + this.GetSuperficie().GetImagen().getWidth();
        double y2 = y1 + this.GetSuperficie().GetImagen().getHeight();
        mi_cuadro_colision = new CuadroColision(x1,y1,x2,y2);

    }
    /*get y set cuadrocolision*/
    public CuadroColision GetColision() {
        return mi_cuadro_colision;
    }

    public void SetColisionVisble(boolean visible) {
        this.mi_cuadro_colision.mostrar = visible;
    }

    @Override
    public void MostrarColision(Graphics2D render){
        if(this.mi_cuadro_colision!= null)
        {
            if(this.mi_cuadro_colision.mostrar)
            {
               for(int i=0;i<this.mi_cuadro_colision.GetVertices().length-1;i++)
               {
                   int x1 = (int) mi_cuadro_colision.GetVertices()[i].x;
                   int x2 = (int) mi_cuadro_colision.GetVertices()[i+1].x;
                   int y1 = (int) mi_cuadro_colision.GetVertices()[i].y;
                   int y2 = (int) mi_cuadro_colision.GetVertices()[i+1].y;
                    render.drawLine(x1,y1,x2,y2);
               }
               render.drawLine((int)mi_cuadro_colision.GetVertices()[3].x,(int)mi_cuadro_colision.GetVertices()[3].y,(int)mi_cuadro_colision.GetVertices()[0].x,(int)mi_cuadro_colision.GetVertices()[0].y);

            }
        }
    }
}
