package Clases;

public class Jugador extends Objeto2DDinamico {
   private SistemaControl botones;
    private double impulso = 5;
    private int HPmax =    20;//100;
    private int HPactual = HPmax;


    private boolean HitState;
    private boolean InicioTimer;

    private long timerStar;
    private long currentTimer;

    private final SoundPlayer ReproductorSonidos = SoundPlayer.GetInstancia();
    private Sonido sonidoColision;


    public Jugador(String imagen,double posx,double posy){
        super(imagen,posx,posy);
        this.HitState=false;
        this.InicioTimer=false;
        sonidoColision = new Sonido("/Sonidos/Splash.wav");
    }

    public void AddControl(SistemaControl controller){
        this.botones = controller;
    }

    public SistemaControl GetCotrol(){
        return this.botones;
    }

    @Override
    public void Mover(Vector2D vel){
        vel = vel.Por(new Vector2D(this.impulso));
        super.Mover(vel);
    }

    public int GetHPactual() {
        return HPactual;
    }

    public void SetHPactual(int valor) {
        this.HPactual = valor;
    }


    public void Danio(int dmg){
        if(!HitState)
        {
            this.HPactual-= dmg;
            ReproductorSonidos.play(sonidoColision);
            if (this.HPactual <= 0 )
            {
                this.HPactual = 0;
            }
            this.HitState = true;
        }
    }

    /*calculo de daño solo 1 vez*/
    public void Update(){

        if(HitState && !InicioTimer)
        {
            timerStar = System.currentTimeMillis();
            InicioTimer = true;
        }
        currentTimer = System.currentTimeMillis() - timerStar;

        if((currentTimer / 1000) == 2)
        {
            HitState = false;
            InicioTimer = false;
        }
    }

    public int getHPmax(){
        return  HPmax;
    }
    public void setHitState( boolean hitState){
        this.HitState = HitState;
    }



}
