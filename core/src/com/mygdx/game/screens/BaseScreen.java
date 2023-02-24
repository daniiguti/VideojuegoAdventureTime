package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MainGame;

public abstract class BaseScreen implements Screen {
    //Instancia de main game para que cada ventana pueda acceder a las otras y
    //por abstracción está aquí
    protected MainGame mainGame;

    public BaseScreen(MainGame maingame){
        this.mainGame = maingame;
    }

    //Metodos de la interfaz, se implementan aqui, para no tener que implementaros en cada una de las pantallas,
    //puesto que alomejor en GameScreen necesitamos el hide, pero en el GameOver no, para eso se definen aqui,
    //luego extendemos de esta e implementamos solo los metodos que necesitemos
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

