package Clases;

public class SistemaMovimiento {
    private static  SistemaMovimiento instancia = null;

    private int maxx,maxy;

    public static SistemaMovimiento GetInstancia(){
        if(instancia == null){
            instancia = new SistemaMovimiento();
        }
        return instancia;
    }

    public void SetCanvasLimit(int width,int height){
        this.maxx=width;
        this.maxy=height;
        System.out.println("w;"+maxx+"h:"+maxy);
    }

    public void Update(Objeto entidad){
        if(entidad.Esmovible())
        {
            if(entidad.getClass() == (Jugador.class)){
                Jugador player = (Jugador) entidad;

                Vector2D velocidad = new Vector2D(player.GetVelocidad());
                velocidad = velocidad.Por(new Vector2D(player.GetCotrol().vx,player.GetCotrol().vy));
                entidad.Mover(velocidad);
            }
            else
            {
                entidad.Mover(entidad.GetVelocidad());
            }
        }

        /*Revisa limites del canvas*/
        if(entidad.GetPosicion().y > maxy){
            entidad.GetPosicion().y = maxy;
           Vector2D velocidad = new Vector2D(entidad.GetVelocidad());
            velocidad = velocidad.Por(new Vector2D(0));
            entidad.Mover(velocidad);
        }
        if(entidad.GetPosicion().y <= 0)
        {
            entidad.GetPosicion().y = 0;
            Vector2D velocidad = new Vector2D(entidad.GetVelocidad());
            velocidad = velocidad.Por(new Vector2D(0));
            entidad.Mover(velocidad);
        }
        if(entidad.GetPosicion().x > maxx + 100)
        {
            entidad.GetPosicion().x=maxx;
            Vector2D velocidad = new Vector2D(entidad.GetVelocidad());
            velocidad = velocidad.Por(new Vector2D(0));
            entidad.Mover(velocidad);
        }
        if(entidad.GetPosicion().x <= 0)
        {
            entidad.GetPosicion().x=0;
            Vector2D velocidad = new Vector2D(entidad.GetVelocidad());
            velocidad = velocidad.Por(new Vector2D(0));
            entidad.Mover(velocidad);
        }
        entidad.SetPosicion(entidad.GetPosicion());
    }
}
