package br.com.maison.birdsfuriosos.elementos;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Maison on 08/04/2016.
 */
public class Pontuacao {

    private int pontos = 0;
    private static final Paint BRANCO =
            Cores.getCorDaPontuacao();


    public Pontuacao(){

    }

    public void aumenta() {
        pontos++;

    }

    public void desenhaNo(Canvas canvas) {
        canvas.drawText(String.valueOf(pontos), 100, 100,
                BRANCO);
    }
}
