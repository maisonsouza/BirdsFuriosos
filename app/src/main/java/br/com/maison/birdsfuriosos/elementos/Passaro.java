package br.com.maison.birdsfuriosos.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.maison.birdsfuriosos.R;

/**
 * Created by Maison on 06/04/2016.
 */

public class Passaro {

//variáveis para construir o drawCircle
    protected static final int X = 100;
    protected static final int RAIO = 50;
    private int altura;
    private static final Paint vermelho =
            Cores.getCorDoPassaro();
    private Tela tela;
    private Context context;
    private Bitmap passaro;
    private Som som;

    //Construtor - altura inicial
    public Passaro(Tela tela, Context context, Som som) {

        this.tela = tela;
        this.altura = 100;
        this.context=context;
        this.som = som;
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.passaro);
        this.passaro = Bitmap.createScaledBitmap(bp, RAIO*2, RAIO*2 ,
                false);
    }

    // desenha o "pássaro"
    public void desenhaNo(Canvas canvas){
        canvas.drawCircle(X, altura, RAIO, vermelho);
        canvas.drawBitmap(passaro, X-RAIO, altura-RAIO, null);
    }

// queda do passáro
    public void cai () {
        //tratando colisão com o chão
        boolean chegouNoChao = altura + RAIO > tela.getAltura();
        if(!chegouNoChao) {
            altura += 5;
        }
    }
 //pulo
    public void pula() {
        //tratando colisão com o teto
        if(altura > RAIO) {
            this.altura -= 70;
            som.play(Som.PULO);
        }
    }

    public int getX(){
        return X;
    }

    public int getRaio(){
        return RAIO;
    }

    public int getAltura(){
        return altura;
    }



}
