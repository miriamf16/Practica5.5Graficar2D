package Clases;

public class Pilares extends Objeto2DDinamico {

    private  int mi_dano;

    public Pilares(String imagen){
        super(imagen);
    }

    public Pilares (String imagen,double posx,double posy){

        super(imagen,posx,posy);
        this.mi_dano = (int) ( 5 + Math.random() * 5) ;
    }

    public int GetDanio(){
        return mi_dano;
    }

}
