package Clases;

public class Objeto2D extends  Objeto {

    private Superficie DisplayImage;
    public int aceleracion;

    public Objeto2D(){
        this.DisplayImage = new Superficie();
        this.coordenadas = new Vector2D();
    }

    public Objeto2D(String imagen){
        this.DisplayImage = new Superficie(imagen);
        this.coordenadas= new Vector2D();
    }

    public void LoadImagen(String archivo){
        this.DisplayImage.CargarImagen(archivo);
    }

    public Superficie GetDisplayImage(){
        return this.DisplayImage;
    }
}
