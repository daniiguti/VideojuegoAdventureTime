package com.mygdx.game.screens;

import static com.mygdx.game.extra.Utils.USER_ENEMY;
import static com.mygdx.game.extra.Utils.USER_ENEMY1;
import static com.mygdx.game.extra.Utils.USER_ENEMY2;
import static com.mygdx.game.extra.Utils.USER_ENEMY3;
import static com.mygdx.game.extra.Utils.USER_ENEMY4;
import static com.mygdx.game.extra.Utils.USER_ENEMY5;
import static com.mygdx.game.extra.Utils.USER_FRUIT;
import static com.mygdx.game.extra.Utils.USER_FRUIT1;
import static com.mygdx.game.extra.Utils.USER_FRUIT2;
import static com.mygdx.game.extra.Utils.USER_FRUIT3;
import static com.mygdx.game.extra.Utils.USER_FRUIT4;
import static com.mygdx.game.extra.Utils.USER_FRUIT5;
import static com.mygdx.game.extra.Utils.USER_OBSTACULO;
import static com.mygdx.game.extra.Utils.USER_PERSONAJE;
import static com.mygdx.game.extra.Utils.USER_PINCHOS;
import static com.mygdx.game.extra.Utils.USER_ROCK;
import static com.mygdx.game.extra.Utils.USER_SUELO;
import static com.mygdx.game.extra.Utils.USER_TROFEO;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MainGame;
import com.mygdx.game.actors.Enemy;
import com.mygdx.game.actors.Fruit;
import com.mygdx.game.actors.MainCharacter;
import com.mygdx.game.extra.AssetMan;

/**
 * Listener para controlar las colisiones entre el personaje y muchas cosas
 */
public class ColisionListener implements ContactListener {
    private MainGame maingame;
    private Stage stage;

    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private Enemy enemy5;

    private Fruit fruit1;
    private Fruit fruit2;
    private Fruit fruit3;
    private Fruit fruit4;
    private Fruit fruit5;

    private MainCharacter mc;

    private Sound sonidoDead;
    private Sound sonidoTakeFruit;
    private Sound sonidoCogerTrofeo;
    private Music musicGameScreen;

    public ColisionListener(MainGame maingame, Stage stage){
        this.maingame = maingame;
        this.stage = stage;

        this.sonidoDead = AssetMan.getAssetMan().getDeadSound();
        this.sonidoTakeFruit = AssetMan.getAssetMan().getTakeFruitSound();
        this.sonidoCogerTrofeo = AssetMan.getAssetMan().getTakeTrofeoSound();
    }

    @Override
    public void beginContact(Contact contact) {
        //obtenemos los dos fixture que colisionaron
        Fixture contacto1 = contact.getFixtureA();
        Fixture contacto2 = contact.getFixtureB();

        //COLISION PERSONAJE Y TROFEO (lo mandamos a la pantalla de GameWin)
        if((contacto1.getUserData() == USER_PERSONAJE && contacto2.getUserData() == USER_TROFEO) ||
                (contacto2.getUserData() == USER_PERSONAJE && contacto1.getUserData() == USER_TROFEO)) {
            this.sonidoCogerTrofeo.play();
            this.musicGameScreen.stop();
            this.mc.setDead(true);
            this.maingame.setScreen(this.maingame.getGameWinScreen());
        }else {
            //COLISION PERSONAJE Y PINCHOS (lo mandamos a la pantalla de GameOver)
            if ((contacto1.getUserData() == USER_PERSONAJE && contacto2.getUserData() == USER_PINCHOS) ||
                    (contacto2.getUserData() == USER_PERSONAJE && contacto1.getUserData() == USER_PINCHOS)) {
                this.sonidoDead.play();
                this.musicGameScreen.stop();
                this.mc.setDead(true);
                this.maingame.setScreen(this.maingame.getGameOverScreen());
            } else {
                //COLISION PERSONAJE Y ROCA (lo mandamos a la pantalla de GameOver)
                if ((contacto1.getUserData() == USER_PERSONAJE && contacto2.getUserData() == USER_ROCK) ||
                        (contacto2.getUserData() == USER_PERSONAJE && contacto1.getUserData() == USER_ROCK)) {
                    this.sonidoDead.play();
                    this.musicGameScreen.stop();
                    this.mc.setDead(true);
                    maingame.setScreen(maingame.getGameOverScreen());
                } else {
                    //COLISION PERSONAJE Y OBSTACULOS Y SUELO (para que cuando salte en un obstaculo y toque la parte de arriba de ese obstaculo
                    // , que se le reinicien los saltos a 0)
                    if ((contacto1.getUserData() == USER_PERSONAJE && contacto2.getUserData() == USER_OBSTACULO) ||
                            (contacto2.getUserData() == USER_PERSONAJE && contacto1.getUserData() == USER_OBSTACULO) ||
                            (contacto1.getUserData() == USER_PERSONAJE && contacto2.getUserData() == USER_SUELO) ||
                            (contacto2.getUserData() == USER_PERSONAJE && contacto1.getUserData() == USER_SUELO)) {
                        if ((contacto1.getUserData() == USER_PERSONAJE && contacto2.getUserData() == USER_OBSTACULO)) {
                            if (contacto1.getBody().getPosition().y > contacto2.getBody().getPosition().y) {
                                this.mc.setNumSaltos(0);
                            }
                        } else {
                            //para que reinicie los saltos en caso de que se salte por encima del obstaculo, y no por debajo
                            if ((contacto2.getUserData() == USER_PERSONAJE && contacto1.getUserData() == USER_OBSTACULO)) {
                                if (contacto2.getBody().getPosition().y > contacto1.getBody().getPosition().y) {
                                    this.mc.setNumSaltos(0);
                                }
                            }
                            //si toca el suelo que los reinicie tambien
                            else {
                                this.mc.setNumSaltos(0);
                            }
                        }
                        //cambiamos la animacion para que no se quede la animacion de saltando
                        this.mc.cambiarAnimacion();
                    } else {
                        //COLISION PERSONAJE Y FRUTAS (eliminamos la fruta del mundo)
                        if ((contacto1.getUserData() == USER_PERSONAJE && contacto2.getUserData() == USER_FRUIT) ||
                                (contacto2.getUserData() == USER_PERSONAJE && contacto1.getUserData() == USER_FRUIT)) {
                            this.sonidoTakeFruit.play();
                            if (contacto1.getUserData() == USER_FRUIT) {
                                String data_contacto1 = contacto1.getBody().getUserData().toString();
                                switch (data_contacto1) {
                                    case USER_FRUIT1:
                                        this.fruit1.setCogida(true);
                                        break;
                                    case USER_FRUIT2:
                                        this.fruit2.setCogida(true);
                                        break;
                                    case USER_FRUIT3:
                                        this.fruit3.setCogida(true);
                                        break;
                                    case USER_FRUIT4:
                                        this.fruit4.setCogida(true);
                                        break;
                                    case USER_FRUIT5:
                                        this.fruit5.setCogida(true);
                                        break;
                                }
                            } else {
                                if (contacto2.getUserData() == USER_FRUIT) {
                                    String data_contacto2 = contacto2.getBody().getUserData().toString();
                                    switch (data_contacto2) {
                                        case USER_FRUIT1:
                                            this.fruit1.setCogida(true);
                                            break;
                                        case USER_FRUIT2:
                                            this.fruit2.setCogida(true);
                                            break;
                                        case USER_FRUIT3:
                                            this.fruit3.setCogida(true);
                                            break;
                                        case USER_FRUIT4:
                                            this.fruit4.setCogida(true);
                                            break;
                                        case USER_FRUIT5:
                                            this.fruit5.setCogida(true);
                                            break;
                                    }
                                }
                            }
                        } else {
                            //COLISION PERSONAJE Y ENEMIGOS
                            //aprovechamos que todos los enemigos tienen el mismo fixture para detectar la colision con cualquier enemigo
                            //y luego usamos el body user data(que es distinto para cada uno), para detectar cual fue con el que se colisiono
                            // ypersonaje > yenemigo -> muere enemigo
                            // yenemigo <= ypersonaje -> muere personaje
                            if ((contacto1.getUserData() == USER_PERSONAJE && contacto2.getUserData() == USER_ENEMY) ||
                                    (contacto2.getUserData() == USER_PERSONAJE && contacto1.getUserData() == USER_ENEMY)) {
                                if ((contacto1.getBody().getPosition().y > contacto2.getBody().getPosition().y + 0.13f) && (contacto1.getUserData() == USER_PERSONAJE)) {
                                    String data_contacto2 = contacto2.getBody().getUserData().toString();
                                    switch (data_contacto2) {
                                        case USER_ENEMY1:
                                            this.enemy1.setDead(true);
                                            break;
                                        case USER_ENEMY2:
                                            this.enemy2.setDead(true);
                                            break;
                                        case USER_ENEMY3:
                                            this.enemy3.setDead(true);
                                            break;
                                        case USER_ENEMY4:
                                            this.enemy4.setDead(true);
                                            break;
                                        case USER_ENEMY5:
                                            this.enemy5.setDead(true);
                                            break;
                                    }
                                } else {
                                    if ((contacto2.getBody().getPosition().y > contacto1.getBody().getPosition().y + 0.13f) && (contacto2.getUserData() == USER_PERSONAJE)) {
                                        String data_contacto1 = contacto1.getBody().getUserData().toString();
                                        switch (data_contacto1) {
                                            case USER_ENEMY1:
                                                this.enemy1.setDead(true);
                                                break;
                                            case USER_ENEMY2:
                                                this.enemy2.setDead(true);
                                                break;
                                            case USER_ENEMY3:
                                                this.enemy3.setDead(true);
                                                break;
                                            case USER_ENEMY4:
                                                this.enemy4.setDead(true);
                                                break;
                                            case USER_ENEMY5:
                                                this.enemy5.setDead(true);
                                                break;
                                        }
                                    } else {
                                        if ((contacto2.getBody().getPosition().y >= contacto1.getBody().getPosition().y) && (contacto1.getUserData() == USER_PERSONAJE)) {
                                            this.sonidoDead.play();
                                            this.musicGameScreen.stop();
                                            this.mc.setDead(true);
                                            this.maingame.setScreen(this.maingame.getGameOverScreen());
                                        } else {
                                            if ((contacto1.getBody().getPosition().y >= contacto2.getBody().getPosition().y) && (contacto2.getUserData() == USER_PERSONAJE)) {
                                                this.sonidoDead.play();
                                                this.musicGameScreen.stop();
                                                this.mc.setDead(true);
                                                this.maingame.setScreen(this.maingame.getGameOverScreen());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    //setters necesarios para hacer con los objetos lo que nos interese
    public void setMc(MainCharacter mc) {
        this.mc = mc;
    }

    public void setEnemy1(Enemy enemy1) {
        this.enemy1 = enemy1;
    }
    public void setEnemy2(Enemy enemy2) {
        this.enemy2 = enemy2;
    }
    public void setEnemy3(Enemy enemy3) {
        this.enemy3 = enemy3;
    }
    public void setEnemy4(Enemy enemy4) {
        this.enemy4 = enemy4;
    }
    public void setEnemy5(Enemy enemy5) {
        this.enemy5 = enemy5;
    }

    public void setFruit1(Fruit fruit) {
        this.fruit1 = fruit;
    }
    public void setFruit2(Fruit fruit) {
        this.fruit2 = fruit;
    }
    public void setFruit3(Fruit fruit) {
        this.fruit3 = fruit;
    }
    public void setFruit4(Fruit fruit) {
        this.fruit4 = fruit;
    }
    public void setFruit5(Fruit fruit) {
        this.fruit5 = fruit;
    }

    public void setMusicGameScreen(Music musicGameScreen) {
        this.musicGameScreen = musicGameScreen;
    }
}