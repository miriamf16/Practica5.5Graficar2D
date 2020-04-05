package Clases;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x,double y){
        this.x=x;
        this.y=y;
    }

    public Vector2D(Vector2D copiar){
        this.x= copiar.x;
        this.y=copiar.y;
    }

    public Vector2D(){
        this.x=0;
        this.y=0;
    }

    public Vector2D Por(Vector2D B){
        Vector2D C = new Vector2D();
        C.x= this.x * B.x;
        C.y= this.y * B.y;
        return C;
    }

    public Vector2D Suma(Vector2D B){
        Vector2D C = new Vector2D();
        C.x= this.x + B.x;
        C.y= this.y + B.y;
        return C;
    }

    public Vector2D Resta(Vector2D B){
        Vector2D C = new Vector2D();
        C.x= this.x - B.x;
        C.y= this.y - B.y;
        return C;
    }

}
