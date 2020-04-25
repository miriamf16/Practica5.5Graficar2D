package Clases;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SistemaControl implements KeyListener {
    public double vx,vy;
    private double rate=1;

    public SistemaControl(){
        this.vx=rate;
        this.vy=rate;
    }

    @Override
    public void keyTyped(KeyEvent k){
        k.consume();
    }

    @Override
    public void keyPressed(KeyEvent k ){
        if(k.getKeyCode() == KeyEvent.VK_SPACE)
        {
            this.vy = -rate;
        }
    }

    @Override
    public void keyReleased(KeyEvent k ){
        switch (k.getKeyCode())
        {
            case KeyEvent.VK_SPACE:
                this.vy = rate;
                break;
        }
    }
}
