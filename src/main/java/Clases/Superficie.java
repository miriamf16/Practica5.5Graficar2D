package Clases;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Superficie{

    private BufferedImage imagen;

    public Superficie(){
        this.imagen=null;

    }

    public Superficie(String archivo) {
        try {
            this.imagen = ImageIO.read(getClass().getResource(archivo));
        } catch (IOException e) {
            System.out.println("Error al cargar imagen");
        }
    }

    public void CargarImagen(String archivo){
        try{
            this.imagen= ImageIO.read(getClass().getResource(archivo));
        }catch(IOException e){
            System.out.println("Error al cargar imagen");
        }
    }

    public BufferedImage GetImagen(){
        return this.imagen;
    }



}
