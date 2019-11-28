package com.carros.exemplo.kart;

import com.carros.exemplo.kart.AndGraph.AGGameManager;
import com.carros.exemplo.kart.AndGraph.AGInputManager;
import com.carros.exemplo.kart.AndGraph.AGScene;
import com.carros.exemplo.kart.AndGraph.AGScreenManager;
import com.carros.exemplo.kart.AndGraph.AGSoundManager;
import com.carros.exemplo.kart.AndGraph.AGSprite;

public class CenaReiniciar extends AGScene {

    AGSprite telaFundo = null;
    AGSprite restart = null;
    AGSprite memu = null;
    AGSprite exit = null;
    AGSprite gambia = null;
    AGSprite gambia2 = null;
    AGSprite gambia3 = null;
    int codStart = 0;
    int codClique = 0;

   CenaReiniciar(AGGameManager gameManager){
        super(gameManager);
    }
    @Override
    public void init() {

        codStart  = AGSoundManager.vrSoundEffects.loadSoundEffect("ligar.mp3");
        codClique = AGSoundManager.vrSoundEffects.loadSoundEffect("toc.wav");

        telaFundo = createSprite(R.mipmap.gameover, 1,1);
        telaFundo.setScreenPercent(100,100);
        telaFundo.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                AGScreenManager.iScreenHeight / 2);

        gambia = createSprite(R.mipmap.limpo, 1,4);
        gambia.setScreenPercent(80,20);
        gambia.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                AGScreenManager.iScreenHeight - gambia.getSpriteHeight() / 2);

        gambia2 = createSprite(R.mipmap.limpo,1,1);
        gambia2.setScreenPercent(50,15);
        gambia2.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                gambia.vrPosition.fY - gambia.getSpriteHeight() / 2 -
                        gambia.getSpriteHeight()/2);

        gambia3 = createSprite(R.mipmap.limpo,1,1);
        gambia3.setScreenPercent(50,15);
        gambia3.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                gambia2.vrPosition.fY - gambia2.getSpriteHeight() / 4 -
                        gambia.getSpriteHeight()/4);

        restart = createSprite(R.mipmap.restart,1,1);
        restart.setScreenPercent(50,15);
        restart.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                gambia3.vrPosition.fY - gambia3.getSpriteHeight() / 2 -
                        gambia3.getSpriteHeight()/2);

        memu = createSprite(R.mipmap.menu,1,1);
        memu.setScreenPercent(50,15);
        memu.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                restart.vrPosition.fY - restart.getSpriteHeight() / 2 -
                        restart.getSpriteHeight()/2);

        exit = createSprite(R.mipmap.exit,1,1);
        exit.setScreenPercent(50,15);
        exit.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                memu.vrPosition.fY - memu.getSpriteHeight() / 2 -
                        memu.getSpriteHeight()/2);

    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {

        if(AGInputManager.vrTouchEvents.screenClicked())
        {
            if (exit.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                AGSoundManager.vrSoundEffects.play(codClique);
                vrGameManager.vrActivity.finish();
            }
            if (memu.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                AGSoundManager.vrSoundEffects.play(codClique);
                vrGameManager.setCurrentScene(1);
                return;
            }
            if (restart.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                AGSoundManager.vrSoundEffects.play(codStart);
                vrGameManager.setCurrentScene(3);
                return;
            }
        }

        if (
                AGInputManager.vrTouchEvents.backButtonClicked())
        {
            vrGameManager.vrActivity.finish();
        }
    }
}
