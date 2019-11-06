package com.carros.exemplo.kart;


import com.carros.exemplo.kart.AndGraph.AGGameManager;
import com.carros.exemplo.kart.AndGraph.AGScene;
import com.carros.exemplo.kart.AndGraph.AGScreenManager;
import com.carros.exemplo.kart.AndGraph.AGSprite;
import com.carros.exemplo.kart.AndGraph.AGTimer;

public class CenaAbertura extends AGScene {
    AGTimer tempo = null;
    AGSprite logofadep = null;
    int estado = 0;

    CenaAbertura(AGGameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void init() {
        //chamdo quando está cena for carregada

        this.setSceneBackgroundColor(0, 0, 0);
        tempo = new AGTimer(3000);
        // informa a qtd de quadros que tem a imagem que vai ser usada.
        logofadep = createSprite(R.mipmap.abertura, 1, 1);
        //percentual da tela que a imagem vai utilizar
        logofadep.setScreenPercent(60, 40);
        //posicao da imagem na tela
        logofadep.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                AGScreenManager.iScreenHeight / 2);
        //tem que vai iniciar
        logofadep.fadeIn(4000);

        //AGSoundManager.vrMusic.loadMusic("musica.mp3", true);
        // AGSoundManager.vrMusic.play();

    }

    @Override
    public void restart() {
        // chamado quando cena volta de interrupção
    }

    @Override
    public void stop() {
        //chamdo quando acena sofre interrupção

    }

    @Override
    public void loop() {
        //metodo que roda n vezes por segundo, onde será programado a lógica da sena

        //atualiza o objeto de tempo
        // tempo.update();
        // verifica se o tempo acabou
        // if (tempo.isTimeEnded())
        // {
        //troca a cena
        //  vrGameManager.setCurrentScene(1);
        //}

        if (estado == 0 && logofadep.fadeEnded()) {
            estado = 1;
            logofadep.fadeOut(3000);
        } else {
            if (logofadep.fadeEnded()) {
                vrGameManager.setCurrentScene(1);
            }
        }
    }

}
