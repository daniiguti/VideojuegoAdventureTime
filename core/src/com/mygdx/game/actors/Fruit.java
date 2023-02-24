package com.mygdx.game.actors;


import static com.mygdx.game.extra.Utils.USER_FRUIT;
import static com.mygdx.game.extra.Utils.USER_FRUIT1;
import static com.mygdx.game.extra.Utils.USER_FRUIT2;
import static com.mygdx.game.extra.Utils.USER_FRUIT3;
import static com.mygdx.game.extra.Utils.USER_FRUIT4;
import static com.mygdx.game.extra.Utils.USER_FRUIT5;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.extra.AssetMan;

public class Fruit extends Actor {

    private Vector2 position;

    private World world;

    private Body bodyFruit;
    private Fixture fixtureFruit;

    private Animation<TextureRegion> fruitAnimation;
    private float stateTime;

    private boolean cogida;
    private Object userDataBody;

    //constructor
    public Fruit(World world, Vector2 position, int tipoFruta){
        this.cogida = false;
        this.position = position;

        this.world = world;

        this.stateTime = 0f;

        //misma logica que enemy, necesimatos identificar dos cosas:
        //fixture -> para saber que colisiono una fruta
        //body -> para saber cual de las 5 frutas colisiono
        switch (tipoFruta){
            case 1:
                this.fruitAnimation = AssetMan.getAssetMan().getAppleAnimation();
                this.userDataBody = USER_FRUIT1;
                break;
            case 2:
                this.fruitAnimation = AssetMan.getAssetMan().getBananaAnimation();
                this.userDataBody = USER_FRUIT2;
                break;
            case 3:
                this.fruitAnimation = AssetMan.getAssetMan().getOrangeAnimation();
                this.userDataBody = USER_FRUIT3;
                break;
            case 4:
                this.fruitAnimation = AssetMan.getAssetMan().getAppleAnimation();
                this.userDataBody = USER_FRUIT4;
                break;
            case 5:
                this.fruitAnimation = AssetMan.getAssetMan().getOrangeAnimation();
                this.userDataBody = USER_FRUIT5;
                break;
        }

        createBody();
        createFixture();
    }

    private void createBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(this.position);
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        this.bodyFruit = this.world.createBody(bodyDef);
        this.bodyFruit.setUserData(userDataBody);
    }

    private void createFixture(){
        PolygonShape rectangle = new PolygonShape();
        //para darle el tamaño, tiene que ser la mitad de lo que mide la x y la mitad de lo que mide la y
        rectangle.setAsBox(0.1f, 0.1f);

        this.fixtureFruit = this.bodyFruit.createFixture(rectangle, 8);
        //hay que especificarle un id de este objeto, ya que tendremos varios que hacen de fisica
        this.fixtureFruit.setUserData(USER_FRUIT);
        this.fixtureFruit.setSensor(true);
        //eliminar el rectangulo de la memoria
        rectangle.dispose();
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.bodyFruit.getPosition().x - 0.1f, this.bodyFruit.getPosition().y - 0.1f);
        //batch.draw(AssetMan.getAssetMan().getFruit(), getX(),getY(), 0.2f,0.2f);
        //Le restamos mas o menos la mitad para centrar la imagen del dragon al circulo
        //setPosition(this.bodyFruit.getPosition().x-0.1f, this.bodyFruit.getPosition().y-0.1f);
        //                                          tiempo,       bucle,        x,     y,         ancho,    alto
        batch.draw(this.fruitAnimation.getKeyFrame(stateTime, true), getX(), getY(), 0.2f,0.2f );

        //calculo de fps
        stateTime += Gdx.graphics.getDeltaTime();
    }


    public void detach(){
        this.bodyFruit.destroyFixture(this.fixtureFruit);
        this.world.destroyBody(this.bodyFruit);
    }


    public boolean isCogida() {
        return cogida;
    }
    public void setCogida(boolean cogida) {
        this.cogida = cogida;
    }
}
