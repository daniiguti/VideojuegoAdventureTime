package com.mygdx.game.actors;

import static com.mygdx.game.extra.Utils.USER_TROFEO;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.extra.AssetMan;

public class Trofeo extends Actor {
    private Vector2 position;

    private World world;

    private Body bodyTrofeo;
    private Fixture fixtureTrofeo;


    public Trofeo(World world){
        this.position = position;

        this.world = world;

        createBody();
        createFixture();
    }

    private void createBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(18f,4f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyTrofeo = this.world.createBody(bodyDef);
    }


    private void createFixture(){
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(0.3f, 0.3f);

        this.fixtureTrofeo = this.bodyTrofeo.createFixture(rectangle, 8);
        //hay que especificarle un id de este objeto, ya que tendremos varios que hacen de fisica
        this.fixtureTrofeo.setUserData(USER_TROFEO);

        //eliminar el rectangulo de la memoria
        rectangle.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.bodyTrofeo.getPosition().x - 0.3f, this.bodyTrofeo.getPosition().y - 0.3f);
        batch.draw(AssetMan.getAssetMan().getTrofeo(), getX(),getY(), 0.6f,0.6f);
    }

    public void detach(){
        this.bodyTrofeo.destroyFixture(this.fixtureTrofeo);
        this.world.destroyBody(this.bodyTrofeo);
    }
}
