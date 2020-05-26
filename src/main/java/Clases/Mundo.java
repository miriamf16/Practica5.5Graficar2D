package Clases;

import java.util.ArrayList;
import java.util.Random;

public class Mundo {
    private Jugador personaje;
    private Pilares PilarUpper;
    private Pilares PilarBelow;
    private Objeto2D bg;

    private boolean fin = false;
    private double xlim,ylim;
    public ArrayList<Objeto> ObjetosMundo = new ArrayList<>();
    private boolean pausa;
    private final SoundPlayer ReproductorSonidos = SoundPlayer.GetInstancia();

    //agregar sonidos
    private Sonido sonidoBg;
    private Sonido sonidoPerdio;

    public void init(SistemaControl evento, int widht,int height,boolean pausa){
        xlim = widht;
        ylim = height;
        this.pausa=pausa;

        bg= new Objeto2D("/bg2.png");
        bg.SetEscalar(3,3); // 3 ambos

        personaje = new Jugador("/wormvolador.png",0,ylim/2);
        personaje.AddControl(evento);
        personaje.SetVelocidad(new Vector2D(0,1));
        personaje.FlipHorizontal();
        personaje.SetColisionVisble(false);

        PilarUpper = new Pilares("/muro1.png",xlim/2,0)  ;
        PilarUpper.RotarImagen(90);
        PilarUpper.SetVelocidad(new Vector2D(-3,0));
        PilarUpper.SetEscalar(0.5,1);
        PilarUpper.SetColisionVisble(false);

        PilarBelow = new Pilares("/muro2.png",xlim/1.25,ylim-20);
        PilarBelow.SetEscalar(0.5,1);
        PilarBelow.SetVelocidad(new Vector2D(-3,0));
        PilarBelow.SetColisionVisble(true);

        ObjetosMundo.add(bg);
        ObjetosMundo.add(personaje);
        ObjetosMundo.add(PilarUpper);
        ObjetosMundo.add(PilarBelow);
        //Reproducir sonido
        sonidoPerdio = new Sonido("/Sonidos/lose.mid");

        sonidoBg = new Sonido("/Sonidos/main.mid");
        ReproductorSonidos.playloop(sonidoBg,5);
    }

    public  void Reglas(){
        /*update posicion de la colision*/
        for(Objeto actores: ObjetosMundo)
        {
            if(actores instanceof  Objeto2DDinamico)
            {
                //Actualiza posicion de las colisiones
                Objeto2DDinamico actoresDynamicos = (Objeto2DDinamico)actores;
                double x1 = actoresDynamicos.GetPosicion().x;
                double y1 = actoresDynamicos.GetPosicion().y;
                double x2 = x1 + actoresDynamicos.GetSuperficie().GetImagen().getWidth();
                double y2 = y1 +  actoresDynamicos.GetSuperficie().GetImagen().getHeight();
                actoresDynamicos.GetColision().UpdatePosicion(x1,y1,x2,y2);
            }
        }

        /*verificar si colisiona pilares con el personaje*/
        if(personaje.GetPosicion().y >= ylim || PilarUpper.GetColision().EnColision(personaje.GetColision()))
        { //|| PilarBelow.GetColision().EnColision(personaje.GetColision())
            SetFin(true);
            personaje.Danio(PilarUpper.GetDanio());
            System.out.println(PilarUpper.GetDanio()+ " - Vida:" +personaje.GetHPactual());

        }

        if(PilarBelow.GetColision().EnColision(personaje.GetColision()) )
        {
            SetFin(true);
            personaje.Danio((PilarBelow.GetDanio()));
            System.out.println(PilarBelow.GetDanio()+"- Vida:"+personaje.GetHPactual());
        }

        /*update objetos*/
        personaje.Update();
        if(personaje.GetHPactual() <=0)
        {
            ReproductorSonidos.stop(sonidoBg);
            ReproductorSonidos.play(sonidoPerdio);
            SetFin(true);
        }

        int escalaminima = 1;
        int escalamaxima = 3;

        if(PilarUpper.GetPosicion().x <= 0 )
        {
            PilarUpper.ResetDimensiones();
            PilarUpper.RotarImagen(90);

            Random random = new Random();
            int r = random.nextInt((escalamaxima-escalaminima) + escalaminima)+1;
            PilarUpper.SetEscalar(0.5,r);
            PilarUpper.SetPosicion(new Vector2D(xlim+100,0));

        }
        if(PilarBelow.GetPosicion().x <= 0)
        {
            PilarBelow.ResetDimensiones();
            Random random = new Random();
            int r = random.nextInt((escalamaxima-escalaminima)+escalaminima)+1;
            PilarBelow.SetEscalar(0.5,r);
            PilarBelow.SetPosicion(new Vector2D(xlim+100,ylim-PilarBelow.GetSuperficie().GetImagen().getHeight()+10));
        }

    }

    public void Reset()
    {
        //personaje.SetHPactual(personaje.getHPmax());
        //personaje.setHitState(false);
        //personaje.SetPosicion(new Vector2D(0,ylim/2));
        //PilarUpper.SetPosicion(new Vector2D(xlim/2,0));
        //PilarBelow.SetPosicion(new Vector2D(xlim/1.25,ylim-20));
        //setFin(false);
        ReproductorSonidos.stop(sonidoBg);
        //ReproductorSonidos.resume(sonidoBg);
        System.out.println("Reset lvl");
    }


    public Jugador GetPersonaje() {
        return personaje;
    }

    public void update(){
        Reglas();
    }

    public void SetFin(boolean valor){
        fin = valor;
    }

    public boolean IsOver(){
        return fin;
    }

    public Objeto2D getBg() {
        return bg;
    }

    public void setBg(Objeto2D bg) {
        this.bg = bg;
    }



}
