package br.com.maison.birdsfuriosos.engine;

import br.com.maison.birdsfuriosos.elementos.Canos;
import br.com.maison.birdsfuriosos.elementos.Passaro;
import br.com.maison.birdsfuriosos.elementos.Som;

/**
 * Created by Maison on 08/04/2016.
 */
public class VerificadorDeColisao {
    private final Passaro passaro;
    private final Canos canos;


    public VerificadorDeColisao(Passaro passaro, Canos canos) {
        this.passaro = passaro;
        this.canos = canos;
    }
    public boolean temColisao() {
        return canos.temColisaoCom(passaro);
    }

}
