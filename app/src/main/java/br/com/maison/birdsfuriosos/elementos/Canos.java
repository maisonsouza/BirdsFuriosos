package br.com.maison.birdsfuriosos.elementos;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Maison on 07/04/2016.
 */
public class Canos {
    private static final int QUANTIDADE_DE_CANOS = 5;
    private static final int DISTANCIA_ENTRE_CANOS = 250;
    private List<Cano> canos = new ArrayList<Cano>();
    private Tela tela;
    private final Pontuacao pontuacao;
    private Context context;
    private Som som;
//Contrutor de Canos
    public Canos(Tela tela, Pontuacao pontuacao,Context context, Som som) {
        this.tela=tela;
        this.pontuacao=pontuacao;
        this.context=context;
        this.som = som;
        int posicaoInicial = 200;
        for(int i=0; i < QUANTIDADE_DE_CANOS; i++) {
            posicaoInicial += DISTANCIA_ENTRE_CANOS;
            canos.add(new Cano(tela, posicaoInicial,context));
        }
    }
//Desenha os canos com foreach
    public void desenhaNo(Canvas canvas) {
        for(Cano cano : canos)
            cano.desenhaNo(canvas);
    }

    public void move() {
        ListIterator<Cano> iterator = canos.listIterator();
        while (iterator.hasNext()) {
            Cano cano = (Cano) iterator.next();
            cano.move();
            if (cano.saiuDaTela()) {
                this.pontuacao.aumenta();
                this.som.play(Som.PONTOS);

                //removendo os canos usados
                iterator.remove();
                Cano outroCano =
                        new Cano(tela, getMaximo() + DISTANCIA_ENTRE_CANOS, context);
                iterator.add(outroCano);
            }
        }
    }

    private int getMaximo() {
        int maximo = 0;
        for(Cano cano : canos) {
            maximo = Math.max(cano.getPosicao(), maximo);
        }
        return maximo;
    }

    public boolean temColisaoCom(Passaro passaro) {
        //Percorrer os canos
        for (Cano cano : canos) {
            if (cano.temColisaoHorizontalCom(passaro)
                    && cano.temColisaoVerticalCom(passaro)) {
                return true;
            }
        }
        return false;
    }

}
