package com.carros.exemplo.kart;

import com.carros.exemplo.kart.AndGraph.AGGameManager;
import com.carros.exemplo.kart.AndGraph.AGInputManager;
import com.carros.exemplo.kart.AndGraph.AGScene;
import com.carros.exemplo.kart.AndGraph.AGScreenManager;
import com.carros.exemplo.kart.AndGraph.AGSoundManager;
import com.carros.exemplo.kart.AndGraph.AGSprite;


public class CenaMenu extends AGScene {
    AGSprite telaFundo = null;
    AGSprite titulo = null;
    AGSprite play = null;
    AGSprite about = null;
    AGSprite exit = null;
    AGSprite gambia = null;
    int codClique = 0;
    int codStart = 0;

    CenaMenu(AGGameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void init() {
        this.setSceneBackgroundColor(1, 1, 0);

        codClique = AGSoundManager.vrSoundEffects.loadSoundEffect("toc.wav");
        codStart = AGSoundManager.vrSoundEffects.loadSoundEffect("ligar.mp3");

        telaFundo = createSprite(R.mipmap.telainicio, 1, 1);
        telaFundo.setScreenPercent(100, 100);
        telaFundo.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                AGScreenManager.iScreenHeight / 2);

        titulo = createSprite(R.mipmap.kartwood, 1, 4);
        titulo.setScreenPercent(80, 20);
        titulo.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                AGScreenManager.iScreenHeight - titulo.getSpriteHeight() / 2);
        //quantos quadros por segundo e se repete quando acabar
        titulo.addAnimation(5, true, 0, 1, 2, 3);

        gambia = createSprite(R.mipmap.limpo, 1, 1);
        gambia.setScreenPercent(50, 15);
        gambia.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                titulo.vrPosition.fY - titulo.getSpriteHeight() / 2 -
                        titulo.getSpriteHeight() / 2);

        play = createSprite(R.mipmap.start, 1, 1);
        play.setScreenPercent(50, 15);
        play.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                gambia.vrPosition.fY - gambia.getSpriteHeight() / 2 -
                        gambia.getSpriteHeight() / 2);

        about = createSprite(R.mipmap.about, 1, 1);
        about.setScreenPercent(50, 15);
        about.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                play.vrPosition.fY -
                        about.getSpriteHeight() - 20);

        exit = createSprite(R.mipmap.exit, 1, 1);
        exit.setScreenPercent(50, 15);
        exit.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                about.vrPosition.fY -
                        exit.getSpriteHeight() - 20);

    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if (AGInputManager.vrTouchEvents.screenClicked()) {
            if (exit.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                AGSoundManager.vrSoundEffects.play(codClique);
                vrGameManager.vrActivity.finish();
            }
            if (about.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                AGSoundManager.vrSoundEffects.play(codClique);
                vrGameManager.setCurrentScene(2);
                return;
            }
            if (play.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                AGSoundManager.vrSoundEffects.play(codStart);
                vrGameManager.setCurrentScene(3);
                return;
            }
        }

        if (
                AGInputManager.vrTouchEvents.backButtonClicked()) {
            vrGameManager.vrActivity.finish();
        }
    }
}
