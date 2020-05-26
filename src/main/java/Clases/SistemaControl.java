package Clases;

import javafx.application.Application;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SistemaControl  {
    public double vx,vy;
    private double rate=1;
    private boolean pause;

    public SistemaControl(){
        this.vx=rate;
        this.vy=rate;
        pause = false;

    }
    public void Manejador(InputEvent evento, Canvas cvs){
        if(evento.getEventType() == KeyEvent.KEY_PRESSED)
        {
            KeyEvent presiono = (KeyEvent)evento;
            if(presiono.getCode().isWhitespaceKey())
            {
                this.vy = -rate;
            }

            if(presiono.getCode() == KeyCode.M && cvs.getPrimernivel().GetPersonaje().GetHPactual() == 0)
            {
                //cvs.getPrimernivel().Reset();
                cvs.getPrimernivel().GetPersonaje().SetHPactual(100);
                cvs.init();

            }
            if(presiono.getCode() == KeyCode.P)
            {
               cvs.setPausa(true);
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
