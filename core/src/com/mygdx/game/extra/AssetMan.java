package com.mygdx.game.extra;

import static com.mygdx.game.extra.Utils.*;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetMan {
    //instancia de la propia clase (patron singleton)
    private static AssetMan assetMan;

    private AssetManager assetManager;
    private TextureAtlas textureAtlas;

    /**
     * Constructor para cargar los recursos necesarios (imagenes, sonidos, etc)
     * es privada puesto que usamos el patron singleton
     */
    private AssetMan(){
        this.assetManager = new AssetManager();

        assetManager.load(ATLAS_MAP, TextureAtlas.class);

        assetManager.load(TAKE_FRUIT, Sound.class);
        assetManager.load(TAKE_TROFEO, Sound.class);
        assetManager.load(DEAD, Sound.class);
        assetManager.load(JUMP, Sound.class);
        assetManager.load(ENEMY_DEAD, Sound.class);

        assetManager.load(TEMA_GAMEOVER_GAMEWIN, Music.class);
        assetManager.load(TEMA_GETREADY, Music.class);
        assetManager.load(TEMA_GAMESCREEN, Music.class);

        assetManager.finishLoading();

        this.textureAtlas = assetManager.get(ATLAS_MAP);
    }

    /**
     * metodo del patron singleton para obtener el mismo objeto(y que NO se cree uno nuevo) alli donde nos interese
     * @return -> instancia de la propia clase, ya que si el objeto estaba creado devuelve ese, y sino lo crea y lo devuelve
     */
    public static AssetMan getAssetMan() {
        if (assetMan == null) {
            assetMan = new AssetMan();
        }
        return assetMan;
    }

    /**
     * @return -> el fondo de la app una vez ya cargado
     */
    public TextureRegion getBackground(){
        return this.textureAtlas.findRegion(BACKGROUND_IMAGE);
    }

    /**
     * @return -> los obstaculos
     */
    public TextureRegion getPlataforma1(){
        return this.textureAtlas.findRegion(PLATAFORMA1);
    }
    public TextureRegion getPlataforma2(){
        return this.textureAtlas.findRegion(PLATAFORMA2);
    }
    public TextureRegion getPlataforma3(){
        return this.textureAtlas.findRegion(PLATAFORMA3);
    }
    public TextureRegion getPlataforma4(){
        return this.textureAtlas.findRegion(PLATAFORMA4);
    }
    public TextureRegion getPlataforma5(){
        return this.textureAtlas.findRegion(PLATAFORMA5);
    }
    public TextureRegion getTierra1(){
        return this.textureAtlas.findRegion(TIERRA1);
    }
    public TextureRegion getTierra2(){
        return this.textureAtlas.findRegion(TIERRA2);
    }
    public TextureRegion getLadrillos(){
        return this.textureAtlas.findRegion(LADRILLOS);
    }
    public TextureRegion getPinchos(){
        return this.textureAtlas.findRegion(PINCHOS);
    }
    public TextureRegion getMuro1(){
        return this.textureAtlas.findRegion(MURO1);
    }
    public TextureRegion getMuro2(){
        return this.textureAtlas.findRegion(MURO2);
    }
    public TextureRegion getSuelo1(){
        return this.textureAtlas.findRegion(SUELO1);
    }
    public TextureRegion getSuelo2(){
        return this.textureAtlas.findRegion(SUELO2);
    }
    public TextureRegion getEscalon1(){
        return this.textureAtlas.findRegion(ESCALON1);
    }
    public TextureRegion getEscalon2(){
        return this.textureAtlas.findRegion(ESCALON2);
    }
    public TextureRegion getEscalon3(){
        return this.textureAtlas.findRegion(ESCALON3);
    }

    /**
     * @return -> la animacion de los enemigos
     */
    public Animation<TextureRegion> getEnemyMushroomAnimation(){
        return new Animation<TextureRegion>(0.125f,
                textureAtlas.findRegion(ENEMIGO1),
                textureAtlas.findRegion(ENEMIGO2),
                textureAtlas.findRegion(ENEMIGO3),
                textureAtlas.findRegion(ENEMIGO4),
                textureAtlas.findRegion(ENEMIGO5),
                textureAtlas.findRegion(ENEMIGO6),
                textureAtlas.findRegion(ENEMIGO7),
                textureAtlas.findRegion(ENEMIGO8)
        );
    }
    public Animation<TextureRegion> getEnemyBatAnimation(){
        return new Animation<TextureRegion>(0.143f,
                textureAtlas.findRegion(BAT1),
                textureAtlas.findRegion(BAT2),
                textureAtlas.findRegion(BAT3),
                textureAtlas.findRegion(BAT4),
                textureAtlas.findRegion(BAT5),
                textureAtlas.findRegion(BAT6),
                textureAtlas.findRegion(BAT7)
        );
    }
    public Animation<TextureRegion> getEnemyRightBatAnimation(){
        return new Animation<TextureRegion>(0.143f,
                textureAtlas.findRegion(BAT_DERECHA1),
                textureAtlas.findRegion(BAT_DERECHA2),
                textureAtlas.findRegion(BAT_DERECHA3),
                textureAtlas.findRegion(BAT_DERECHA4),
                textureAtlas.findRegion(BAT_DERECHA5),
                textureAtlas.findRegion(BAT_DERECHA6),
                textureAtlas.findRegion(BAT_DERECHA7)
        );
    }

    /**
     * @return -> la animacion de la roca
     */
    public Animation<TextureRegion> getRockAnimation(){
        return new Animation<TextureRegion>(0.25f,
                textureAtlas.findRegion(ROCA1),
                textureAtlas.findRegion(ROCA2),
                textureAtlas.findRegion(ROCA3),
                textureAtlas.findRegion(ROCA4)
        );
    }

    /**
     * @return -> los botones
     */
    public TextureRegion getFlechaIzquierda(){
        return this.textureAtlas.findRegion(IZQUIERDA);
    }
    public TextureRegion getFlechaDerecha(){
        return this.textureAtlas.findRegion(DERECHA);
    }

    /**
     * @return -> techo
     */
    public TextureRegion getTecho(){
        return this.textureAtlas.findRegion(TECHO);
    }

    /**
     * @return -> pared
     */
    public TextureRegion getPared(){
        return this.textureAtlas.findRegion(PARED);
    }

    /**
     * @return -> Trofeo
     */
    public TextureRegion getTrofeo(){
        return this.textureAtlas.findRegion(TROFEO);
    }

    /**
     * @return -> suelo
     */
    public TextureRegion getSuelo(){
        return this.textureAtlas.findRegion(SUELO);
    }


    /**
     * @return -> fruits
     */
    public Animation<TextureRegion> getAppleAnimation(){
        return new Animation<TextureRegion>(0.166f,
                textureAtlas.findRegion(MANZANA1),
                textureAtlas.findRegion(MANZANA2),
                textureAtlas.findRegion(MANZANA3),
                textureAtlas.findRegion(MANZANA4),
                textureAtlas.findRegion(MANZANA5),
                textureAtlas.findRegion(MANZANA6)
        );
    }
    public Animation<TextureRegion> getBananaAnimation(){
        return new Animation<TextureRegion>(0.166f,
                textureAtlas.findRegion(BANANA1),
                textureAtlas.findRegion(BANANA2),
                textureAtlas.findRegion(BANANA3),
                textureAtlas.findRegion(BANANA4),
                textureAtlas.findRegion(BANANA5),
                textureAtlas.findRegion(BANANA6)
        );
    }
    public Animation<TextureRegion> getOrangeAnimation(){
        return new Animation<TextureRegion>(0.166f,
                textureAtlas.findRegion(ORANGE1),
                textureAtlas.findRegion(ORANGE2),
                textureAtlas.findRegion(ORANGE3),
                textureAtlas.findRegion(ORANGE4),
                textureAtlas.findRegion(ORANGE5),
                textureAtlas.findRegion(ORANGE6)
        );
    }


    /**
     * @return -> main character
     */
    public Animation<TextureRegion> getQuietAnimation(){
        return new Animation<TextureRegion>(0.09f,
                textureAtlas.findRegion(QUIETO1),
                textureAtlas.findRegion(QUIETO2),
                textureAtlas.findRegion(QUIETO3),
                textureAtlas.findRegion(QUIETO4),
                textureAtlas.findRegion(QUIETO5),
                textureAtlas.findRegion(QUIETO6),
                textureAtlas.findRegion(QUIETO7),
                textureAtlas.findRegion(QUIETO8),
                textureAtlas.findRegion(QUIETO9),
                textureAtlas.findRegion(QUIETO10),
                textureAtlas.findRegion(QUIETO11)
        );
    }
    public Animation<TextureRegion> getLeftQuietAnimation(){
        return new Animation<TextureRegion>(0.09f,
                textureAtlas.findRegion(QUIETO_IZQUIERDA1),
                textureAtlas.findRegion(QUIETO_IZQUIERDA2),
                textureAtlas.findRegion(QUIETO_IZQUIERDA3),
                textureAtlas.findRegion(QUIETO_IZQUIERDA4),
                textureAtlas.findRegion(QUIETO_IZQUIERDA5),
                textureAtlas.findRegion(QUIETO_IZQUIERDA6),
                textureAtlas.findRegion(QUIETO_IZQUIERDA7),
                textureAtlas.findRegion(QUIETO_IZQUIERDA8),
                textureAtlas.findRegion(QUIETO_IZQUIERDA9),
                textureAtlas.findRegion(QUIETO_IZQUIERDA10),
                textureAtlas.findRegion(QUIETO_IZQUIERDA11)
        );
    }
    public Animation<TextureRegion> getJumpAnimation(){
        return new Animation<TextureRegion>(0.16f,
                textureAtlas.findRegion(SALTO1),
                textureAtlas.findRegion(SALTO2),
                textureAtlas.findRegion(SALTO3),
                textureAtlas.findRegion(SALTO4),
                textureAtlas.findRegion(SALTO5),
                textureAtlas.findRegion(SALTO6)
        );
    }
    public Animation<TextureRegion> getLeftJumpAnimation(){
        return new Animation<TextureRegion>(0.16f,
                textureAtlas.findRegion(SALTO_IZQUIERDA1),
                textureAtlas.findRegion(SALTO_IZQUIERDA2),
                textureAtlas.findRegion(SALTO_IZQUIERDA3),
                textureAtlas.findRegion(SALTO_IZQUIERDA4),
                textureAtlas.findRegion(SALTO_IZQUIERDA5),
                textureAtlas.findRegion(SALTO_IZQUIERDA6)
        );
    }
    public Animation<TextureRegion> getRunAnimation(){
        return new Animation<TextureRegion>(0.083f,
                textureAtlas.findRegion(RUN1),
                textureAtlas.findRegion(RUN2),
                textureAtlas.findRegion(RUN3),
                textureAtlas.findRegion(RUN4),
                textureAtlas.findRegion(RUN5),
                textureAtlas.findRegion(RUN6),
                textureAtlas.findRegion(RUN7),
                textureAtlas.findRegion(RUN8),
                textureAtlas.findRegion(RUN9),
                textureAtlas.findRegion(RUN10),
                textureAtlas.findRegion(RUN11),
                textureAtlas.findRegion(RUN12)
        );
    }
    public Animation<TextureRegion> getLeftRunAnimation(){
        return new Animation<TextureRegion>(0.083f,
                textureAtlas.findRegion(RUN_IZQUIERDA1),
                textureAtlas.findRegion(RUN_IZQUIERDA2),
                textureAtlas.findRegion(RUN_IZQUIERDA3),
                textureAtlas.findRegion(RUN_IZQUIERDA4),
                textureAtlas.findRegion(RUN_IZQUIERDA5),
                textureAtlas.findRegion(RUN_IZQUIERDA6),
                textureAtlas.findRegion(RUN_IZQUIERDA7),
                textureAtlas.findRegion(RUN_IZQUIERDA8),
                textureAtlas.findRegion(RUN_IZQUIERDA9),
                textureAtlas.findRegion(RUN_IZQUIERDA10),
                textureAtlas.findRegion(RUN_IZQUIERDA11),
                textureAtlas.findRegion(RUN_IZQUIERDA12)
        );
    }

    /**
     * @return -> los fondos de las perspectivas pantallas
     */
    public TextureRegion getFondoGameOver(){
        return this.textureAtlas.findRegion(FONDO_GAME_OVER);
    }
    public TextureRegion getFondoGameWin(){
        return this.textureAtlas.findRegion(FONDO_GAME_WIN);
    }
    public TextureRegion getFondoGameReady(){
        return this.textureAtlas.findRegion(FONDO_GAME_READY);
    }

    /**
     * @return -> sonidos
     */
    public Sound getJumpSound(){
        return this.assetManager.get(JUMP);
    }
    public Sound getDeadSound(){
        return this.assetManager.get(DEAD);
    }
    public Sound getTakeFruitSound(){
        return this.assetManager.get(TAKE_FRUIT);
    }
    public Sound getTakeTrofeoSound(){
        return this.assetManager.get(TAKE_TROFEO);
    }
    public Sound getEnemyDeadSound(){
        return this.assetManager.get(ENEMY_DEAD);
    }

    /**
     * @return -> musica
     */
    public Music getMusicGameScreen(){
        return this.assetManager.get(TEMA_GAMESCREEN);
    }
    public Music getMusicGameOverGameWinScreen(){
        return this.assetManager.get(TEMA_GAMEOVER_GAMEWIN);
    }
    public Music getMusicGetReadyScreen(){
        return this.assetManager.get(TEMA_GETREADY);
    }

}
