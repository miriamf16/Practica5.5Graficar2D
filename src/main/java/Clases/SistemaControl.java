package Clases;

import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;

public class SistemaControl  {
    public double vx,vy;
    private double rate=1;

    public SistemaControl(){
        this.vx=rate;
        this.vy=rate;
    }
    public void Manejador(InputEvent evento){
        if(evento.getEventType() == KeyEvent.KEY_PRESSED)
        {
            KeyEvent presiono = (KeyEvent)evento;
            if(presiono.getCode().isWhitespaceKey() )
            {
                this.vy = -rate;
            }
        }
        if (evento.getEventType() == KeyEvent.KEY_RELEASED)
        {
            KeyEvent presiono = (KeyEvent)evento;
            if(presiono.getCode().isWhitespaceKey() )
            {
                this.vy = rate ;
            }
        }

    }
}
