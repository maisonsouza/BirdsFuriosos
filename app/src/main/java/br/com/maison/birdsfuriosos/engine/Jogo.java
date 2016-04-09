package br.com.maison.birdsfuriosos.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import br.com.maison.birdsfuriosos.R;
import br.com.maison.birdsfuriosos.elementos.Cano;
import br.com.maison.birdsfuriosos.elementos.Canos;
import br.com.maison.birdsfuriosos.elementos.GameOver;
import br.com.maison.birdsfuriosos.elementos.Passaro;
import br.com.maison.birdsfuriosos.elementos.Pontuacao;
import br.com.maison.birdsfuriosos.elementos.Som;
import br.com.maison.birdsfuriosos.elementos.Tela;

/**
 * Created by Maison on 06/04/2016.
 */

/* Classe principal do Jogo herda de Surface View para criar atráves de threads cada elemento do jogo
    Interface Runnable */
public class Jogo extends SurfaceView implements Runnable,View.OnTouchListener {
    // variável que verifica se o jogo está rodando
    private boolean estaRodando = true;
    //Classe que desenha em cada pixel da tela
    private final SurfaceHolder holder = getHolder();
    private Passaro passaro;
    private Bitmap background;
    private Tela tela;
    private Cano cano;
    private Context context;
    private Canos canos;
    private Pontuacao pontuacao;
    private Som som;



    //Contrutor da Classe Jogo
    public Jogo(Context context) {
        super(context);
        this.context=context;
        this.tela= new Tela(context);
        this.som = new Som(context);

        inicializaElementos();
        //rodamos a interface Touch
        setOnTouchListener(this);

    }
    // Método da interface Runnable
    //Onde o jogo roda
    @Override
    public void run() {
        while(estaRodando) {
            Canvas canvas = holder.lockCanvas();
            if(!holder.getSurface().isValid()) continue;
            canvas.drawBitmap(background, 0, 0, null);
            passaro.desenhaNo(canvas);
            passaro.cai();
            canos.desenhaNo(canvas);
            canos.move();
            pontuacao.desenhaNo(canvas);
            if(new VerificadorDeColisao(passaro, canos).temColisao()) {

                new GameOver(tela,som).desenhaNo(canvas);
                this.estaRodando = false;

            }
            //Fecha o canvas e exibe
            holder.unlockCanvasAndPost(canvas);
        }
    }

    //Método onde cancelamos o jogo
    public void cancela() {
        this.estaRodando = false;
    }
    //Método que inicia o jogo
    public void inicia() {
        this.estaRodando = true;
    }

    private void inicializaElementos() {

        this.background = BitmapFactory.decodeResource(getResources(),
                R.drawable.background);
        this.passaro = new Passaro(tela,context,som);
        this.cano = new Cano(tela, 200, context);
        this.pontuacao = new Pontuacao();
        this.canos = new Canos(tela, pontuacao, context,som);

    }

    //Ao tocar na tela disparamos o metodo pula
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        passaro.pula();

        return false;
    }
}
