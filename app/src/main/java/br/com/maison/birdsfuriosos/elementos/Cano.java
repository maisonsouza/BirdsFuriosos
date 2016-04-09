package br.com.maison.birdsfuriosos.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.maison.birdsfuriosos.R;

/**
 * Created by Maison on 07/04/2016.
 */
public class Cano {
    private static final int TAMANHO_DO_CANO = 140;
    private static final int LARGURA_DO_CANO = 100;
    private Tela tela;
    private int alturaDoCanoInferior;
    private int alturaDoCanoSuperior;
    private int posicao;
    private final Paint verde = Cores.getCorDoCano();
    private Bitmap canoInferior;
    private Bitmap canoSuperior;


    //Contrutor Cano
    public Cano(Tela tela, int posicao, Context context){
        this.tela=tela;
        this.posicao = posicao;
        this.alturaDoCanoInferior =
                tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
        this.alturaDoCanoSuperior =
                 TAMANHO_DO_CANO + valorAleatorio();
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.cano);
        this.canoInferior = Bitmap.createScaledBitmap(bp,
                LARGURA_DO_CANO, this.alturaDoCanoInferior, false);
        this.canoSuperior = Bitmap.createScaledBitmap(bp,
                LARGURA_DO_CANO, this.alturaDoCanoSuperior, false);
    }

    //método que desenha os canos
    public void desenhaNo(Canvas canvas){
        desenhaCanoSuperiorNo(canvas);
        desenhaCanoInferiorNo(canvas);

    }
    //Desenhando o cano inferior
    public void desenhaCanoInferiorNo(Canvas canvas) {
        //canvas.drawRect(posicao, alturaDoCanoInferior, posicao + LARGURA_DO_CANO, tela.getAltura(), verde);
        canvas.drawBitmap(canoInferior, posicao, alturaDoCanoInferior,
                null);
    }

    public void desenhaCanoSuperiorNo(Canvas canvas) {
        canvas.drawBitmap(canoSuperior, posicao, 0, null);
    }

    //cano se movendo
    public void move() {
        posicao -= 5;
    }

    private int valorAleatorio() {
        return (int) (Math.random() * 140);
    }

    public boolean saiuDaTela() {
        return posicao + LARGURA_DO_CANO < 0;
    }

     public int getPosicao(){
         return this.posicao;
     }
//verifica colisão vertical entre o cano e o passaro
    public boolean temColisaoVerticalCom(Passaro passaro) {
        return passaro.getAltura() -
                passaro.RAIO < this.alturaDoCanoSuperior
                || passaro.getAltura() + passaro.RAIO >
                this.alturaDoCanoInferior;
    }

    public boolean temColisaoHorizontalCom(Passaro passaro) {
        return this.posicao - passaro.X < passaro.RAIO;
    }

}
