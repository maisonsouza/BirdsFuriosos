package br.com.maison.birdsfuriosos.elementos;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Maison on 08/04/2016.
 */
public class GameOver {

    private final Tela tela;
    private static final Paint VERMELHO = Cores.getCorDoGameOver();
    private Som som;

    public GameOver(Tela tela,Som som) {
        this.tela = tela;
        this.som = som;
        som.play(Som.MORTE);
    }


    public void desenhaNo(Canvas canvas) {
        String gameOver = "Game Over";
        int centroHorizontal = centralizaTexto(gameOver);
        canvas.drawText(gameOver, centroHorizontal,
                tela.getAltura()/2, VERMELHO);
    }

    private int centralizaTexto(String texto) {
        Rect limiteDoTexto = new Rect();
        VERMELHO.getTextBounds(texto, 0, texto.length(),
                limiteDoTexto);
        int centroHorizontal = tela.getLargura()/2 -
                (limiteDoTexto.right - limiteDoTexto.left)/2;
        return centroHorizontal;
    }
}
