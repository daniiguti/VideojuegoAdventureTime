package com.mygdx.game.actors;

import static com.mygdx.game.extra.Utils.USER_PARED;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.extra.AssetMan;

/**
 * para este actor no seria necesario crearnos una clase, pero a mi me gusta
 * independizar las cosas, por lo que me he creado esta clase
 */
public class Pared extends Actor {

    private Vector2 position;

    private World world;

    private Body bodyPared;
    private Fixture fixturePared;


    public Pared(World world, Vector2 position){
        this.position = position;

        this.world = world;

        createBody();
        createFixture();
    }

    public Vector2 getBodyPositionPared(){
        return this.bodyPared.getPosition();
    }


    private void createBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(this.position);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyPared = this.world.createBody(bodyDef);
    }


    private void createFixture(){
        PolygonShape rectangle = new PolygonShape();
        //para darle el tamaño, tiene que ser la mitad de lo que mide la x y la mitad de lo que mide la y
        rectangle.setAsBox(0.15f, 2.4f);

        this.fixturePared = this.bodyPared.createFixture(rectangle, 8);
        //hay que especificarle un id de este objeto, ya que tendremos varios que hacen de fisica
        this.fixturePared.setUserData(USER_PARED);

        //eliminar el rectangulo de la memoria
        rectangle.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.bodyPared.getPosition().x - 0.15f, this.bodyPared.getPosition().y - 2.4f);
        batch.draw(AssetMan.getAssetMan().getPared(), getX(),getY(), 0.3f,4.8f);
    }

    public void detach(){
        this.bodyPared.destroyFixture(this.fixturePared);
        this.world.destroyBody(this.bodyPared);
    }
}

