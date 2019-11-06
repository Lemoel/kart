package com.carros.exemplo.kart;

import android.os.Bundle;

import com.carros.exemplo.kart.AndGraph.AGActivityGame;

public class TelaPrincipal extends AGActivityGame {

    //Variavel de referencia
    private CenaAbertura abertura = null;
    private CenaMenu menu = null;
    private CenaSobre sobre = null;
    private CenaJogo jogo = null;
    private CenaReiniciar restart = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //PREPARA O MOTOR GRAFICO PARA O USO
        super.init(this, true);

        // valida o objeto da cena
        abertura = new CenaAbertura(getGameManager());
        menu = new CenaMenu(getGameManager());
        sobre = new CenaSobre(getGameManager());
        jogo = new CenaJogo(getGameManager());
        restart = new CenaReiniciar(getGameManager());

        getGameManager().addScene(abertura);//0
        getGameManager().addScene(menu);//1
        getGameManager().addScene(sobre);//2
        getGameManager().addScene(jogo);//3
        getGameManager().addScene(restart);//4


    }

}
