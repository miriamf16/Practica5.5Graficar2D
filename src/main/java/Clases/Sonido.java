package Clases;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido {

    Clip miSonido;
    public boolean PlayState = false;
    public boolean loop = false;
    int sonidoFrame;

    public Sonido (String Arch)
    {
        try
        {
            AudioInputStream sonido = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(Arch));
            miSonido = AudioSystem.getClip();
            miSonido .open(sonido);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public Clip getClip()
    {
        return  miSonido;
    }

    void setCurrentFrame(int valor)
    {
        sonidoFrame = valor;
    }
    public int getCurrentFrame()
    {
        return sonidoFrame;
    }

}
