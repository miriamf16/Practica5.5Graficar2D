package Clases;

public class CuadroColision {
    private Vector2D[] Vertices = new Vector2D[4];
    private Vector2D posicion;
    public boolean mostrar = false;

    public CuadroColision(double x1,double y1,double x2,double y2){
        this.posicion = new Vector2D((x1+x2)/2,(y1+y2)/2);//centro pivote cuadro
        this.Vertices[0] = new Vector2D(x1,y1);
        this.Vertices[1] = new Vector2D(x1,y2);
        this.Vertices[2] = new Vector2D(x2,y2);
        this.Vertices[3] = new Vector2D(x2,y1);
    }


    public Vector2D[] GetVertices(){
        return Vertices;
    }

    public void UpdatePosicion(double x1,double y1,double x2,double y2){
        NuevosVertices(x1,y1,x2,y2);
    }

    public void NuevosVertices(double x1,double y1, double x2,double y2){
        this.Vertices[0] = new Vector2D(x1,y1);
        this.Vertices[1] = new Vector2D(x1,y2);
        this.Vertices[2] = new Vector2D(x2,y2);
        this.Vertices[3] = new Vector2D(x2,y1);
        this.posicion = new Vector2D((x1+x2)/2,(y1+y2)/2);

    }

    public boolean EnColision(CuadroColision otroCuadro){

        double x1 = this.Vertices[0].x;
        double y1 = this.Vertices[0].y;
        double x2 = this.Vertices[2].x;
        double y2 = this.Vertices[2].y;

        /*se ocupa x1,y1,x2,y2*/
        double ox1 = otroCuadro.GetVertices()[0].x;
        double oy1 = otroCuadro.GetVertices()[0].y;
        double ox2 = otroCuadro.GetVertices()[2].x;
        double oy2 = otroCuadro.GetVertices()[2].y;

        if((ox1 >= x1 && ox1 <= x2) || (ox2 >= x1 && ox2 <= x2))
        {
            if((oy1 >= y1 && oy1 <= y2) || (oy2>=y1 && oy2<= y2))
            {
                System.out.println("COLISION");
                return true;
            }
        }
        return false;

    }

}
