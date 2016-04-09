package br.com.maison.birdsfuriosos.elementos;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import br.com.maison.birdsfuriosos.R;

/**
 * Created by Maison on 08/04/2016.
 */
public class Som {

    private SoundPool soundPool;
    public static int PULO;
    public static int MORTE,PONTOS;

    private Context context;

    public Som(Context context) {
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        PULO = soundPool.load(context, R.raw.pulo, 1);
        MORTE = soundPool.load(context, R.raw.morte,2);
        PONTOS = soundPool.load(context,R.raw.pontos,2);
    }

    public void play(int som) {
        soundPool.play(som, 1, 1, 1, 0, 1);

    }

    public void stop(int som) {
        soundPool.play(som, 1, 1, 1, 0, 1);

    }
}