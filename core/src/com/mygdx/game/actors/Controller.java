package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.extra.AssetMan;

/**
 * nuestra clase Controller, esta tiene dos actores botones(Button es una clase
 * que ya nos da libgdx, y que hereda de varias clases(una de ellas es actor, por lo
 * que aprovechamos para añadirlos a nuestro stage)) y ademas un input procesor que añadiremos
 * a nuestro InputMultiplexer
 */
public class Controller {
    //Botones e inputProcessor
    private Button rightButton;
    private Button leftButton;
    private InputProcessor inputProcessor;

    //main character para hacer las acciones que nos interese con el
    private MainCharacter mc;

    public Controller(){
        //configuracion del boton izquierdo
        leftButton = new Button(new TextureRegionDrawable(AssetMan.getAssetMan().getFlechaIzquierda()));
        leftButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mc.setDireccion("izquierda");
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                mc.setDireccion("soltar_izquierda");
            }
        });
        leftButton.setHeight(0.8f);
        leftButton.setWidth(0.8f);


        //configuracion del boton derecho
        rightButton = new Button(new TextureRegionDrawable(AssetMan.getAssetMan().getFlechaDerecha()));
        rightButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mc.setDireccion("derecha");
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                mc.setDireccion("soltar_derecha");
            }

        });
        rightButton.setHeight(0.8f);
        rightButton.setWidth(0.8f);


        //configuracion del toque en la pantalla
        inputProcessor = new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                //como screen x va por pixeles, si pulso, en la mitad de la pantalla hacia la derecha
                if(screenX >= Gdx.graphics.getWidth()/2){
                    mc.saltar();
                }
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                return false;
            }
        };
    }

    //Getters de los controller, para poder añadirlos al stage, y este al multiplexer, o en el caso del toque directamente al multiplexer
    public Button getLeftButton() {
        return this.leftButton;
    }
    public Button getRightButton(){
        return this.rightButton;
    }
    public InputProcessor getTouch(){
        return this.inputProcessor;
    }

    //setter del personaje principal, para que depende de lo que pulse, hacer unas cosas u otras
    public void setMc(MainCharacter mc) {
        this.mc = mc;
    }

    //para ir moviendo los botones con la camara
    public void cambiarPosicion(float x){
        this.rightButton.setPosition(x-2.95f, 0.14f);
        this.leftButton.setPosition(x-3.8f, 0.14f);
    }
}
