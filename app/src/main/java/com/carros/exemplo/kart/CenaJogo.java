package com.carros.exemplo.kart;

import com.carros.exemplo.kart.AndGraph.AGGameManager;
import com.carros.exemplo.kart.AndGraph.AGInputManager;
import com.carros.exemplo.kart.AndGraph.AGScene;
import com.carros.exemplo.kart.AndGraph.AGScreenManager;
import com.carros.exemplo.kart.AndGraph.AGSprite;
import com.carros.exemplo.kart.AndGraph.AGTimer;

import java.util.Random;

public class CenaJogo extends AGScene {

    int ACELERACAO_BORDA = 10;
    int ACELERACAO_KARTS = 14;
    int ACELERACAO_FAIXA = 11;
    AGSprite[] vetKart = new AGSprite[4];

    final int LARGURA_KART = 20;
    final int COMPRIMENTO_KART = 25;
    Lado lado = Lado.DIREITO;

    private AGSprite fundo = null;
    private AGSprite kartVerdeWood = null;
    private AGSprite kartVermelho = null;
    private AGSprite barraSuperior = null;
    private AGSprite bordaEsquerda1 = null;
    private AGSprite bordaEsquerda2 = null;
    private AGSprite bordaDireita1 = null;
    private AGSprite bordaDireita2 = null;
    private AGSprite faixaCentral1 = null;
    private AGSprite faixaCentral2 = null;
    private AGSprite roda1 = null;
    private AGSprite roda2 = null;
    private AGSprite roda3 = null;

    private boolean atingido;
    private AGSprite kartAmarelo = null;
    private AGSprite kartAzul = null;
    private AGSprite kartMarron = null;
    private AGTimer tempoAtingido;
    private AGTimer tempoDaVoltaCompleta;
    private int cont;
    AGSprite[] placar = null;
    int valorPlacar = 0;
    int numeroDeVoltas = 0;


    CenaJogo(AGGameManager gameManager) {
        super(gameManager);
    }

    private void atualizaPlacar() {

        if (numeroDeVoltas > 0) {
            numeroDeVoltas--;
            valorPlacar++;
        }

        placar[7].setCurrentAnimation(valorPlacar % 10);
        placar[6].setCurrentAnimation((valorPlacar % 100) / 10);
        placar[5].setCurrentAnimation((valorPlacar % 1000) / 100);
        placar[4].setCurrentAnimation((valorPlacar % 10000) / 1000);
        placar[3].setCurrentAnimation((valorPlacar % 100000) / 10000);
        placar[2].setCurrentAnimation((valorPlacar % 1000000) / 100000);
        placar[1].setCurrentAnimation((valorPlacar % 10000000) / 1000000);
    }

    public void render() {
        super.render();

        for (AGSprite digito : placar) {
            digito.render();
        }
    }

    @Override
    public void init() {

        atingido = false;
        tempoAtingido = new AGTimer(2000);
        tempoDaVoltaCompleta = new AGTimer(10000);
        ACELERACAO_BORDA = 10;
        ACELERACAO_KARTS = 14;
        ACELERACAO_FAIXA = 11;
        cont = 0;
        numeroDeVoltas = 0;
        valorPlacar = 0;

        initFundo();
        initBordaEsquerda();
        initBordaDireita();
        initFaixaCentral();
        initPlacar();
        initKarts();
        initBarraSuperior();
        initRodas();

    }

    private void initRodas() {

        roda1 = createSprite(R.mipmap.roda, 1, 1);
        roda1.setScreenPercent(8,6);
        roda1.vrPosition.setY(AGScreenManager.iScreenHeight - roda1.getSpriteHeight()/2 - roda1.getSpriteHeight()/6);
        roda1.vrPosition.setX(AGScreenManager.iScreenWidth - roda1.getSpriteWidth()/2 );

        roda2 = createSprite(R.mipmap.roda, 1, 1);
        roda2.setScreenPercent(8,6);
        roda2.vrPosition.setY(AGScreenManager.iScreenHeight - roda2.getSpriteHeight()/2 - roda2.getSpriteHeight()/6);
        roda2.vrPosition.setX(AGScreenManager.iScreenWidth - roda1.getSpriteWidth() - roda1.getSpriteWidth()/2);

        roda3 = createSprite(R.mipmap.roda, 1, 1);
        roda3.setScreenPercent(8,6);
        roda3.vrPosition.setY(AGScreenManager.iScreenHeight - roda3.getSpriteHeight()/2 - roda3.getSpriteHeight()/6);
        roda3.vrPosition.setX(AGScreenManager.iScreenWidth  - roda2.getSpriteWidth() * 2 - roda2.getSpriteWidth()/2 );

    }

    private void initPlacar() {

        placar = new AGSprite[8];
        for (int iIndex = 0; iIndex < 8; iIndex++) {
            placar[iIndex] = createSprite(R.mipmap.fonte, 4, 4);
            placar[iIndex].setScreenPercent(8, 8);
            placar[iIndex].bAutoRender = false;
            placar[iIndex].vrPosition.setY(AGScreenManager.iScreenHeight -
                    placar[iIndex].getSpriteHeight() / 2);
            placar[iIndex].vrPosition.setX(placar[iIndex].getSpriteWidth() / 2 +
                    iIndex * placar[iIndex].getSpriteWidth());

            for (int jIndex = 0; jIndex < 10; jIndex++) {
                placar[iIndex].addAnimation(1, false, jIndex);
            }
        }

    }

    private void initFundo() {
        fundo = createSprite(R.mipmap.pista, 1, 1);
        fundo.setScreenPercent(100, 100);
        fundo.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                AGScreenManager.iScreenHeight / 2);

    }


    /**
     * Inicializa Barra Superior
     */
    private void initBarraSuperior() {
        barraSuperior = createSprite(R.drawable.barra_superior, 1, 1);
        barraSuperior.setScreenPercent(100, 8);
        barraSuperior.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight - barraSuperior.getSpriteHeight() / 2);
    }

    /**
     * Inicializa os carros
     */
    private void initKarts() {


        //Sr Wood Verde
        kartVerdeWood = createSprite(R.mipmap.wood, 1, 1);
        kartVerdeWood.setScreenPercent(LARGURA_KART, COMPRIMENTO_KART);
        kartVerdeWood.vrPosition.setXY(getPosicaoDireita(), kartVerdeWood.iFrameHeight / 3);

        kartVermelho = createSprite(R.mipmap.kart_vermelho, 1, 1);
        kartVermelho.setScreenPercent(LARGURA_KART, COMPRIMENTO_KART);
        kartVermelho.vrPosition.setXY(getPosicaoEsquerda(), AGScreenManager.iScreenHeight + kartVermelho.getSpriteHeight() / 2);
        vetKart[0] = kartVermelho;

        kartAmarelo = createSprite(R.mipmap.kart_amarelo, 1, 1);
        kartAmarelo.setScreenPercent(LARGURA_KART, COMPRIMENTO_KART);
        kartAmarelo.vrPosition.setXY(getPosicaoDireita(), AGScreenManager.iScreenHeight + kartAmarelo.getSpriteHeight() / 2);
        vetKart[1] = kartAmarelo;

        kartAzul = createSprite(R.mipmap.kart_azul, 1, 1);
        kartAzul.setScreenPercent(LARGURA_KART, COMPRIMENTO_KART);
        kartAzul.vrPosition.setXY(getPosicaoEsquerda(), AGScreenManager.iScreenHeight + kartAzul.getSpriteHeight() / 2);
        vetKart[2] = kartAzul;

        kartMarron = createSprite(R.mipmap.kart_marron, 1, 1);
        kartMarron.setScreenPercent(LARGURA_KART, COMPRIMENTO_KART);
        kartMarron.vrPosition.setXY(getPosicaoEsquerda(), AGScreenManager.iScreenHeight + kartMarron.getSpriteHeight() / 2);
        vetKart[3] = kartMarron;

    }

    private void initFaixaCentral() {

        faixaCentral1 = createSprite(R.drawable.faixa, 1, 1);
        faixaCentral1.setScreenPercent(10, 100);
        faixaCentral1.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 2);

        faixaCentral2 = createSprite(R.drawable.faixa, 1, 1);
        faixaCentral2.setScreenPercent(10, 100);
        faixaCentral2.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, faixaCentral1.vrPosition.getY() + faixaCentral2.getSpriteHeight());
    }

    private void initBordaDireita() {
        bordaDireita1 = createSprite(R.drawable.borda, 1, 1);
        bordaDireita1.setScreenPercent(10, 100);
        bordaDireita1.vrPosition.setXY(AGScreenManager.iScreenWidth - bordaDireita1.getSpriteWidth() / 2, AGScreenManager.iScreenHeight / 2);

        bordaDireita2 = createSprite(R.drawable.borda, 1, 1);
        bordaDireita2.setScreenPercent(10, 100);
        bordaDireita2.vrPosition.setXY(AGScreenManager.iScreenWidth - bordaDireita2.getSpriteWidth() / 2, bordaDireita1.vrPosition.getY() + bordaEsquerda2.getSpriteHeight());
    }

    private void initBordaEsquerda() {

        bordaEsquerda1 = createSprite(R.drawable.borda, 1, 1);
        bordaEsquerda1.setScreenPercent(10, 100);
        bordaEsquerda1.vrPosition.setXY(bordaEsquerda1.getSpriteWidth() / 2, AGScreenManager.iScreenHeight / 2);

        bordaEsquerda2 = createSprite(R.drawable.borda, 1, 1);
        bordaEsquerda2.setScreenPercent(10, 100);
        bordaEsquerda2.vrPosition.setXY(bordaEsquerda2.getSpriteWidth() / 2, bordaEsquerda1.vrPosition.getY() + bordaEsquerda2.getSpriteHeight());
    }


    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {

        moveBordaEsquerda();
        moveBordaDireita();
        moveFaixaCentral();
        moveKart();
        gerenciaColisao();
        trocarDePista();
        atualizaPlacar();


        if (cont == 1) {
            roda3.bVisible = false;
        } else if (cont == 2) {
            roda2.bVisible = false;
        } else if (cont == 3) {
            roda1.bVisible = false;
            vrGameManager.setCurrentScene(4);
            return;
        }

        tempoDaVoltaCompleta.update();
        if (tempoDaVoltaCompleta.isTimeEnded()) {
            numeroDeVoltas += 1;
            ACELERACAO_BORDA = ACELERACAO_BORDA + 5;
            ACELERACAO_KARTS = ACELERACAO_KARTS + 5;
            ACELERACAO_FAIXA = ACELERACAO_FAIXA + 5;
            tempoDaVoltaCompleta.restart();
        }

    }

    /**
     * Trocar o carro de lado na tela, gerenciando o toque na tela
     */
    public void trocarDePista() {
        if (AGInputManager.vrTouchEvents.screenClicked()) {
            if (kartVerdeWood.moveEnded()) {
                if (lado.equals(Lado.DIREITO)) {
                    kartVerdeWood.moveTo(100, getPosicaoEsquerda(), kartVerdeWood.iFrameHeight / 3);
                    lado = Lado.ESQUERDO;
                } else {
                    kartVerdeWood.moveTo(100, getPosicaoDireita(), kartVerdeWood.iFrameHeight / 3);
                    lado = Lado.DIREITO;
                }
            }
        }
    }

    private void moveKart() {

        //movendo o vermelho
        vetKart[0].vrPosition.setY(vetKart[0].vrPosition.getY() - ACELERACAO_KARTS);

        //se vermelho na metade da tela
        if (vetKart[0].vrPosition.getY() < AGScreenManager.iScreenHeight / 2) {

            //Libera o proximo amarelo
            vetKart[1].vrPosition.setY(vetKart[1].vrPosition.getY() - ACELERACAO_KARTS);

            //Se amarelo na metade da tela
            if (vetKart[1].vrPosition.getY() < AGScreenManager.iScreenHeight / 2) {

                //Movimenta azul
                vetKart[2].vrPosition.setY(vetKart[2].vrPosition.getY() - ACELERACAO_KARTS);

                //Se azul na metade da tela liberar o marron
                if (vetKart[2].vrPosition.getY() < AGScreenManager.iScreenHeight / 2) {

                    //Marron movimenta
                    vetKart[3].vrPosition.setY(vetKart[3].vrPosition.getY() - ACELERACAO_KARTS);

                    //se tiver fora da tela
                    if (vetKart[3].vrPosition.getY() < -vetKart[3].getSpriteHeight()) {
                        vetKart[0].vrPosition.setXY(getLadoRandomicamente(), AGScreenManager.iScreenHeight + vetKart[0].getSpriteHeight() / 2);
                        vetKart[1].vrPosition.setXY(getLadoRandomicamente(), AGScreenManager.iScreenHeight + vetKart[1].getSpriteHeight() / 2);
                        vetKart[2].vrPosition.setXY(getLadoRandomicamente(), AGScreenManager.iScreenHeight + vetKart[2].getSpriteHeight() / 2);
                        vetKart[3].vrPosition.setXY(getLadoRandomicamente(), AGScreenManager.iScreenHeight + vetKart[3].getSpriteHeight() / 2);
                    }

                }

            }

        }

    }

    private void gerenciaColisao() {

        if (kartVerdeWood.collide(kartAmarelo) ||
                kartVerdeWood.collide(kartAzul) ||
                kartVerdeWood.collide(kartMarron) ||
                kartVerdeWood.collide(kartVermelho)) {
            atingido = true;
        }

        if (atingido) {
            tempoAtingido.update();
            if (tempoAtingido.isTimeEnded()) {
                tempoAtingido.restart();
                atingido = false;
                cont++;
                kartVerdeWood.bVisible = true;
                return;
            }

            kartVerdeWood.bVisible = !kartVerdeWood.bVisible;
        }

    }


    private void moveFaixaCentral() {


        if (faixaCentral1.vrPosition.getY() + faixaCentral1.getSpriteHeight() / 2 <= 0) {
            faixaCentral1.vrPosition.setY(faixaCentral2.vrPosition.getY() + faixaCentral1.getSpriteHeight());
        }

        if (faixaCentral2.vrPosition.getY() + faixaCentral2.getSpriteHeight() / 2 <= 0) {
            faixaCentral2.vrPosition.setY(faixaCentral1.vrPosition.getY() + faixaCentral2.getSpriteHeight());
        }

        faixaCentral1.vrPosition.setY(faixaCentral1.vrPosition.getY() - ACELERACAO_FAIXA);
        faixaCentral2.vrPosition.setY(faixaCentral2.vrPosition.getY() - ACELERACAO_FAIXA);


    }

    private void moveBordaDireita() {

        if (bordaDireita1.vrPosition.getY() + bordaDireita1.getSpriteHeight() / 2 <= 0) {
            bordaDireita1.vrPosition.setY(bordaDireita2.vrPosition.getY() + bordaDireita1.getSpriteHeight());
        }

        if (bordaDireita2.vrPosition.getY() + bordaDireita2.getSpriteHeight() / 2 <= 0) {
            bordaDireita2.vrPosition.setY(bordaDireita1.vrPosition.getY() + bordaDireita2.getSpriteHeight());
        }

        bordaDireita1.vrPosition.setY(bordaDireita1.vrPosition.getY() - ACELERACAO_BORDA);
        bordaDireita2.vrPosition.setY(bordaDireita2.vrPosition.getY() - ACELERACAO_BORDA);
    }

    private void moveBordaEsquerda() {

        if (bordaEsquerda1.vrPosition.getY() + bordaEsquerda1.getSpriteHeight() / 2 <= 0) {
            bordaEsquerda1.vrPosition.setY(bordaEsquerda2.vrPosition.getY() + bordaEsquerda1.getSpriteHeight());
        }

        if (bordaEsquerda2.vrPosition.getY() + bordaEsquerda2.getSpriteHeight() / 2 <= 0) {
            bordaEsquerda2.vrPosition.setY(bordaEsquerda1.vrPosition.getY() + bordaEsquerda2.getSpriteHeight());
        }

        bordaEsquerda1.vrPosition.setY(bordaEsquerda1.vrPosition.getY() - ACELERACAO_BORDA);
        bordaEsquerda2.vrPosition.setY(bordaEsquerda2.vrPosition.getY() - ACELERACAO_BORDA);

    }


    private Integer getPosicaoEsquerda() {
        return AGScreenManager.iScreenWidth / 2 - kartVerdeWood.iFrameWidth / 2;
    }

    private Integer getPosicaoDireita() {
        return AGScreenManager.iScreenWidth / 2 + kartVerdeWood.iFrameWidth - kartVerdeWood.iFrameWidth / 3;
    }

    private Integer getLadoRandomicamente() {

        //instância um objeto da classe Random usando o construtor básico
        Random gerador = new Random();
        if (gerador.nextInt(2) == 1) {
            return getPosicaoDireita();
        } else {
            return getPosicaoEsquerda();
        }

    }

}
