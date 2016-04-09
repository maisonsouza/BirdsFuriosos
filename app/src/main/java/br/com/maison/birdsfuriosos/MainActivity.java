package br.com.maison.birdsfuriosos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import br.com.maison.birdsfuriosos.engine.Jogo;

public class MainActivity extends Activity {
    private Jogo jogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FrameLayout único e principal onde o jogo será desenhado
        FrameLayout container = (FrameLayout) findViewById(R.id.container);
        jogo = new Jogo (this);
        //Adicionando o jogo ao Frame
        container.addView(jogo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        jogo.inicia();
        new Thread(jogo).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jogo.cancela();
    }


}
