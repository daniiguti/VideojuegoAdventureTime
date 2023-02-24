package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MainGame;
import com.mygdx.game.extra.AssetMan;


public class GameOverScreen extends BaseScreen{
    private SpriteBatch batch;
    private int screenWidth;
    private int screenHeight;
    private Music musicGameOverScreen;

    public GameOverScreen(final MainGame maingame){
        super(maingame);
        batch = new SpriteBatch();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    private void addBackground(){
        batch.begin();
        //Esto lo que hace es coger la coordenada 0, 0 (la esquina) y dibujar a partir de ahi, hasta el tamaño de la pantalla
        batch.draw(AssetMan.getAssetMan().getFondoGameOver(), 0, 0,  screenWidth, screenHeight);
        batch.end();
    }

    private void addMusic(){
        this.musicGameOverScreen = AssetMan.getAssetMan().getMusicGameOverGameWinScreen();
        this.musicGameOverScreen.setLooping(true);
        this.musicGameOverScreen.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.justTouched()){
            //Paramos la musica
            this.musicGameOverScreen.stop();
            //ponemos la ventana
            this.mainGame.setScreen(this.mainGame.getGameReadyScreen());
        }
        addBackground();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    //añadimos la musica en el show para que esta se añada cada vez que se muestra la pantalla
    @Override
    public void show(){
        addMusic();
    }

}
