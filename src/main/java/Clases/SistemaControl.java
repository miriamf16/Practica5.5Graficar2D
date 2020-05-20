package Clases;

import javafx.application.Application;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SistemaControl  {
    public double vx,vy;
    private double rate=1;


    public SistemaControl(){
        this.vx=rate;
        this.vy=rate;

    }
    public void Manejador(InputEvent evento, Canvas cvs){
        if(evento.getEventType() == KeyEvent.KEY_PRESSED)
        {
            KeyEvent presiono = (KeyEvent)evento;
            if(presiono.getCode().isWhitespaceKey())
            {
                this.vy = -rate;
            }
            if(presiono.getCode() == KeyCode.P)
            {
               cvs.setPausa(true);
            }
            if(presiono.getCode() == KeyCode.P && cvs.getPausa() == true)
            {
                cvs.setFlag(true);
            }
            if(presiono.getCode() == KeyCode.M)
            {
                cvs.init();
            }
            if(presiono.getCode() == KeyCode.O)
            {
                cvs.setPausa(false);
            }



        }
        if (evento.getEventType() == KeyEvent.KEY_RELEASED) {
            KeyEvent presiono = (KeyEvent) evento;
            if (presiono.getCode().isWhitespaceKey()) {
                this.vy = rate;
            }
        }



    }
}
