package Clases;

import java.awt.*;

public class Objeto {

    /*coordenadas*/
    protected Vector2D posicion;
    protected Vector2D velocidad;
    protected Vector2D velocidad_rotacion;
    protected boolean es_movible = false;

    public Objeto(double posx,double posy){
        posicion = new Vector2D(posx,posy); //inicia posicion
        this.velocidad = new Vector2D(0,0);
        this.velocidad_rotacion = new Vector2D(0.,0);

    }

    public Objeto(){
        posicion= new Vector2D(); //inicia en cero
        this.velocidad = new Vector2D(0,0);
        this.velocidad_rotacion = new Vector2D(0.,0);

    }

    /*Sistema Movimiento*/

    public Vector2D GetPosicion() {
        return posicion;
    }

    public void SetPosicion(Vector2D nuevaposicion) {
        this.posicion = nuevaposicion;
    }

    public void Mover(Vector2D velocidad){
        posicion=posicion.Suma(velocidad);
    }

    public Vector2D GetVelocidad() {
        return velocidad;
    }

    public void SetVelocidad(Vector2D nueva) {
        velocidad.x = nueva.x;
        velocidad.y=nueva.y;
    }

    public Vector2D GetVelocidadRotacion(){
        return velocidad_rotacion;
    }

    public boolean Esmovible(){
        return es_movible;
    }

    public void MostrarColision(Graphics2D render){
        //sobreescribir en la clase que se vaya a utilizar
        //para render
    }

}
