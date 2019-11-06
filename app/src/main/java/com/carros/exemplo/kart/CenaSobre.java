package com.carros.exemplo.kart;

import com.carros.exemplo.kart.AndGraph.AGGameManager;
import com.carros.exemplo.kart.AndGraph.AGInputManager;
import com.carros.exemplo.kart.AndGraph.AGScene;
import com.carros.exemplo.kart.AndGraph.AGScreenManager;
import com.carros.exemplo.kart.AndGraph.AGSprite;

public class CenaSobre extends AGScene {
    AGSprite sobre = null;

    CenaSobre(AGGameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void init() {
        this.setSceneBackgroundColor(1, 0, 0);

        sobre = createSprite(R.mipmap.fundosobre, 1, 1);
        sobre.setScreenPercent(80, 80);
        sobre.vrPosition.setXY(AGScreenManager.iScreenWidth / 2,
                -sobre.getSpriteHeight() / 2);
        sobre.moveTo(10000, sobre.vrPosition.getX(),
                AGScreenManager.iScreenHeight + sobre.getSpriteHeight() / 2);
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {

        // ao final do movimento da imagem do sobre, faz com que volte para a cena 1
        // que a tela de abertura...
        if (AGInputManager.vrTouchEvents.screenClicked() || sobre.moveEnded() ||
                AGInputManager.vrTouchEvents.backButtonClicked()) {
            vrGameManager.setCurrentScene(1);
        }
    }

}
