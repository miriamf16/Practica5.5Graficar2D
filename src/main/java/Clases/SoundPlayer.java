package Clases;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundPlayer {
    public static SoundPlayer instancia = null;

    public static SoundPlayer GetInstancia()
    {
        if(instancia == null)
        {
            instancia = new SoundPlayer();
        }
        return instancia;
    }
    public void play(Sonido sonido)
    {

        if(!sonido.PlayState)
        {
            sonido.getClip().setFramePosition(0);
            sonido.getClip().start();
            sonido.loop = false;
            sonido.PlayState = true;
        }
        //resetea el estado cuando termine de reproducior el audio de manera natural
        if(!sonido.getClip().isActive())
        {
            sonido.PlayState=false;
        }
    }
    public void playloop (Sonido sonido,int volumen)
    {
        if(!sonido.PlayState) {
            sonido.getClip().setFramePosition(0);
            FloatControl vol = (FloatControl) sonido.getClip().getControl(FloatControl.Type.MASTER_GAIN);
            vol.setValue(-volumen);
            sonido.getClip().start();
            sonido.getClip().loop(Clip.LOOP_CONTINUOUSLY);
            sonido.loop = true;
            sonido.PlayState = true;
        }
    }
    public void resume(Sonido sonido)
    {
        sonido.getClip().setFramePosition(sonido.getCurrentFrame());
        sonido.getClip().start();
        if(sonido.loop)
            sonido.getClip().loop(Clip.LOOP_CONTINUOUSLY);
        sonido.PlayState = true;
    }

    public void stop(Sonido sonido)
    {
        sonido.setCurrentFrame(sonido.getClip().getFramePosition());
        sonido.getClip().stop();
        sonido.PlayState = false;
    }
}
