package com.mygdx.game.actors;

import static com.mygdx.game.extra.Utils.USER_OBSTACULO;
import static com.mygdx.game.extra.Utils.USER_PINCHOS;

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
 * nuestra clase Obstaculos, esto debería de ser mejor una TiledMap, ya que aqui
 * se esta haciendo "a pelo", también habria que independizar los tamaños, ya que
 * el codigo seria complejo de cambiar, pero al ser tantos, he preferido dejarlo asi
 */
public class Obstacles extends Actor {

    private World world;

    private Body bodyTierra1;
    private Fixture fixtureTierra1;

    private Body bodyTierra2;
    private Fixture fixtureTierra2;

    private Body bodyPinchos;
    private Fixture fixturePinchos;

    private Body bodyPlataforma1;
    private Fixture fixturePlataforma1;

    private Body bodyLadrillos;
    private Fixture fixtureLadrillos;

    private Body bodyPlataforma2;
    private Fixture fixturePlataforma2;

    private Body bodyPlataforma3;
    private Fixture fixturePlataforma3;

    private Body bodyPlataforma4;
    private Fixture fixturePlataforma4;

    private Body bodyPlataforma5;
    private Fixture fixturePlataforma5;

    private Body bodyMuro1;
    private Fixture fixtureMuro1;

    private Body bodyMuro2;
    private Fixture fixtureMuro2;

    private Body bodySuelo1;
    private Fixture fixtureSuelo1;

    private Body bodySuelo2;
    private Fixture fixtureSuelo2;

    private Body bodyEscalon1;
    private Fixture fixtureEscalon1;

    private Body bodyEscalon2;
    private Fixture fixtureEscalon2;

    private Body bodyEscalon3;
    private Fixture fixtureEscalon3;

    private Body bodyPinchos2;
    private Fixture fixturePinchos2;

    /**
     * constructor, nos creamos todos los elementos del mundo
     * @param world -> se le pasa el mundo para poder darle las fisicas, etc
     */
    public Obstacles(World world){
        this.world = world;

        createBodyTierra1();
        createFixtureTierra1();

        createBodyTierra2();
        createFixtureTierra2();

        createBodyPinchos();
        createFixturePinchos();

        createBodyPlataforma1();
        createFixturePlataforma1();

        createBodyLadrillos();
        createFixtureLadrillos();

        createBodyPlataforma2();
        createFixturePlataforma2();

        createBodyPlataforma3();
        createFixturePlataforma3();

        createBodyPlataforma4();
        createFixturePlataforma4();

        createBodyPlataforma5();
        createFixturePlataforma5();

        createBodyMuro1();
        createFixtureMuro1();

        createBodyMuro2();
        createFixtureMuro2();

        createBodySuelo1();
        createFixtureSuelo1();

        createBodySuelo2();
        createFixtureSuelo2();

        createBodyEscalon1();
        createFixtureEscalon1();

        createBodyEscalon2();
        createFixtureEscalon2();

        createBodyEscalon3();
        createFixtureEscalon3();

        createBodyPinchos2();
        createFixturePinchos2();
    }

    //-------------------------------------------------------------
    private void createBodyTierra1(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(2f, 1f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyTierra1 = this.world.createBody(bodyDef);
    }
    private void createFixtureTierra1(){
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(0.35f, 0.4f);

        this.fixtureTierra1 = this.bodyTierra1.createFixture(rectangle, 8);
        this.fixtureTierra1.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyTierra2(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(2.67f, 1.4f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyTierra2 = this.world.createBody(bodyDef);
    }
    private void createFixtureTierra2(){
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(0.3f, 0.8f);

        this.fixtureTierra2 = this.bodyTierra2.createFixture(rectangle, 8);
        this.fixtureTierra2.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyPinchos(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(3.7f, 0.8f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyPinchos = this.world.createBody(bodyDef);
    }
    private void createFixturePinchos(){
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(0.7f, 0.2f);

        this.fixturePinchos = this.bodyPinchos.createFixture(rectangle, 8);
        //Para el colision listener, cuando se "pinche" morira
        this.fixturePinchos.setUserData(USER_PINCHOS);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyPlataforma1(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(0.95f, 2.95f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyPlataforma1 = this.world.createBody(bodyDef);
    }
    private void createFixturePlataforma1(){
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(0.8f, 0.1f);

        this.fixturePlataforma1 = this.bodyPlataforma1.createFixture(rectangle, 8);
        this.fixturePlataforma1.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyLadrillos(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(6.75f, 1f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyLadrillos = this.world.createBody(bodyDef);
    }
    private void createFixtureLadrillos(){
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(1.1f, 0.4f);

        this.fixtureLadrillos = this.bodyLadrillos.createFixture(rectangle, 8);
        this.fixtureLadrillos.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyPlataforma2(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(5.1f, 2.1f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyPlataforma2 = this.world.createBody(bodyDef);
    }
    private void createFixturePlataforma2(){
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(0.65f, 0.1f);

        this.fixturePlataforma2 = this.bodyPlataforma2.createFixture(rectangle, 8);
        this.fixturePlataforma2.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    public Vector2 getBodyPositionPlataforma2(){
        return this.bodyPlataforma2.getPosition();
    }
    //-------------------------------------------------------------
    private void createBodyPlataforma3(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(3.3f, 3.7f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyPlataforma3 = this.world.createBody(bodyDef);
    }
    private void createFixturePlataforma3(){
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(1f, 0.1f);

        this.fixturePlataforma3 = this.bodyPlataforma3.createFixture(rectangle, 8);
        this.fixturePlataforma3.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyPlataforma4(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(6.85f, 4f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyPlataforma4 = this.world.createBody(bodyDef);
    }
    private void createFixturePlataforma4(){
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(1f, 0.1f);

        this.fixturePlataforma4 = this.bodyPlataforma4.createFixture(rectangle, 8);
        this.fixturePlataforma4.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyPlataforma5(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(6f, 2.8f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyPlataforma5 = this.world.createBody(bodyDef);
    }
    private void createFixturePlataforma5(){
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(0.5f, 0.1f);

        this.fixturePlataforma5 = this.bodyPlataforma5.createFixture(rectangle, 8);
        this.fixturePlataforma5.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyMuro1(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(9.2f, 3f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyMuro1 = this.world.createBody(bodyDef);
    }
    private void createFixtureMuro1(){
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(0.3f, 1.7f);

        this.fixtureMuro1 = this.bodyMuro1.createFixture(rectangle, 8);
        this.fixtureMuro1.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyMuro2(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(14f, 2.2f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyMuro2 = this.world.createBody(bodyDef);
    }
    private void createFixtureMuro2(){
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(0.3f, 1.7f);

        this.fixtureMuro2 = this.bodyMuro2.createFixture(rectangle, 8);
        this.fixtureMuro2.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    public Vector2 getBodyPositionMuro2(){
        return this.bodyMuro2.getPosition();
    }
    //-------------------------------------------------------------
    private void createBodySuelo1(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(11.1f, 1.5f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodySuelo1 = this.world.createBody(bodyDef);
    }
    private void createFixtureSuelo1(){
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(2f, 0.2f);

        this.fixtureSuelo1 = this.bodySuelo1.createFixture(rectangle, 8);
        this.fixtureSuelo1.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodySuelo2(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(12.2f, 2.7f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodySuelo2 = this.world.createBody(bodyDef);
    }
    private void createFixtureSuelo2(){
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(2f, 0.2f);

        this.fixtureSuelo2 = this.bodySuelo2.createFixture(rectangle, 8);
        this.fixtureSuelo2.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyEscalon1(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(16.7f, 1f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyEscalon1 = this.world.createBody(bodyDef);
    }
    private void createFixtureEscalon1(){
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(0.3f, 0.4f);

        this.fixtureEscalon1 = this.bodyEscalon1.createFixture(rectangle, 8);
        this.fixtureEscalon1.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyEscalon2(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(17.3f, 1.4f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyEscalon2 = this.world.createBody(bodyDef);
    }
    private void createFixtureEscalon2(){
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(0.3f, 0.8f);

        this.fixtureEscalon2 = this.bodyEscalon2.createFixture(rectangle, 8);
        this.fixtureEscalon2.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyEscalon3(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(17.9f, 1.8f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyEscalon3 = this.world.createBody(bodyDef);
    }
    private void createFixtureEscalon3(){
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(0.3f, 1.2f);

        this.fixtureEscalon3 = this.bodyEscalon3.createFixture(rectangle, 8);
        this.fixtureEscalon3.setUserData(USER_OBSTACULO);

        rectangle.dispose();
    }
    //-------------------------------------------------------------
    private void createBodyPinchos2(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(19f, 0.8f));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.bodyPinchos2 = this.world.createBody(bodyDef);
    }
    private void createFixturePinchos2(){
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(0.8f, 0.2f);

        this.fixturePinchos2 = this.bodyPinchos2.createFixture(rectangle, 8);
        //Para el colision listener, cuando se "pinche" morira
        this.fixturePinchos2.setUserData(USER_PINCHOS);

        rectangle.dispose();
    }

    /**
     * metodo para dibujar todos los obstaculos
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.bodyTierra1.getPosition().x - 0.35f, this.bodyTierra1.getPosition().y - 0.4f);
        batch.draw(AssetMan.getAssetMan().getTierra1(), getX(),getY(), 0.7f,0.8f);

        setPosition(this.bodyTierra2.getPosition().x - 0.3f, this.bodyTierra2.getPosition().y - 0.8f);
        batch.draw(AssetMan.getAssetMan().getTierra2(), getX(),getY(), 0.6f,1.6f);

        setPosition(this.bodyPinchos.getPosition().x - 0.7f, this.bodyPinchos.getPosition().y - 0.2f);
        batch.draw(AssetMan.getAssetMan().getPinchos(), getX(),getY(), 1.4f,0.4f);

        setPosition(this.bodyPlataforma1.getPosition().x - 0.8f, this.bodyPlataforma1.getPosition().y - 0.1f);
        batch.draw(AssetMan.getAssetMan().getPlataforma1(), getX(),getY(), 1.6f,0.2f);

        setPosition(this.bodyLadrillos.getPosition().x - 1.1f, this.bodyLadrillos.getPosition().y - 0.4f);
        batch.draw(AssetMan.getAssetMan().getLadrillos(), getX(),getY(), 2.2f,0.8f);

        setPosition(this.bodyPlataforma2.getPosition().x - 0.65f, this.bodyPlataforma2.getPosition().y - 0.1f);
        batch.draw(AssetMan.getAssetMan().getPlataforma2(), getX(),getY(), 1.3f,0.2f);

        setPosition(this.bodyPlataforma3.getPosition().x - 1f, this.bodyPlataforma3.getPosition().y - 0.1f);
        batch.draw(AssetMan.getAssetMan().getPlataforma3(), getX(),getY(), 2f,0.2f);

        setPosition(this.bodyPlataforma4.getPosition().x - 1f, this.bodyPlataforma4.getPosition().y - 0.1f);
        batch.draw(AssetMan.getAssetMan().getPlataforma4(), getX(),getY(), 2f,0.2f);

        setPosition(this.bodyPlataforma5.getPosition().x - 0.5f, this.bodyPlataforma5.getPosition().y - 0.1f);
        batch.draw(AssetMan.getAssetMan().getPlataforma5(), getX(),getY(), 1f,0.2f);

        setPosition(this.bodyMuro1.getPosition().x - 0.3f, this.bodyMuro1.getPosition().y - 1.7f);
        batch.draw(AssetMan.getAssetMan().getMuro1(), getX(),getY(), 0.6f,3.4f);

        setPosition(this.bodyMuro2.getPosition().x - 0.3f, this.bodyMuro2.getPosition().y - 1.7f);
        batch.draw(AssetMan.getAssetMan().getMuro2(), getX(),getY(), 0.6f,3.4f);

        setPosition(this.bodySuelo1.getPosition().x - 2f, this.bodySuelo1.getPosition().y - 0.2f);
        batch.draw(AssetMan.getAssetMan().getSuelo1(), getX(),getY(), 4f,0.4f);

        setPosition(this.bodySuelo2.getPosition().x - 2f, this.bodySuelo2.getPosition().y - 0.2f);
        batch.draw(AssetMan.getAssetMan().getSuelo2(), getX(),getY(), 4f,0.4f);

        setPosition(this.bodyEscalon1.getPosition().x - 0.3f, this.bodyEscalon1.getPosition().y - 0.4f);
        batch.draw(AssetMan.getAssetMan().getEscalon1(), getX(),getY(), 0.6f,0.8f);

        setPosition(this.bodyEscalon2.getPosition().x - 0.3f, this.bodyEscalon2.getPosition().y - 0.8f);
        batch.draw(AssetMan.getAssetMan().getEscalon2(), getX(),getY(), 0.6f,1.6f);

        setPosition(this.bodyEscalon3.getPosition().x - 0.3f, this.bodyEscalon3.getPosition().y - 1.2f);
        batch.draw(AssetMan.getAssetMan().getEscalon3(), getX(),getY(), 0.6f,2.4f);

        setPosition(this.bodyPinchos2.getPosition().x - 0.8f, this.bodyPinchos2.getPosition().y - 0.2f);
        batch.draw(AssetMan.getAssetMan().getPinchos(), getX(),getY(), 1.6f,0.4f);
    }

    /**
     * metodo para limpiar la grafica
     */
    public void detach(){
        this.bodyTierra1.destroyFixture(this.fixtureTierra1);
        this.world.destroyBody(this.bodyTierra1);
        this.bodyTierra2.destroyFixture(this.fixtureTierra2);
        this.world.destroyBody(this.bodyTierra2);

        this.bodyPlataforma1.destroyFixture(this.fixturePlataforma1);
        this.world.destroyBody(this.bodyPlataforma1);
        this.bodyPlataforma2.destroyFixture(this.fixturePlataforma2);
        this.world.destroyBody(this.bodyPlataforma2);
        this.bodyPlataforma3.destroyFixture(this.fixturePlataforma3);
        this.world.destroyBody(this.bodyPlataforma1);
        this.bodyPlataforma3.destroyFixture(this.fixturePlataforma3);
        this.world.destroyBody(this.bodyPlataforma3);
        this.bodyPlataforma4.destroyFixture(this.fixturePlataforma4);
        this.world.destroyBody(this.bodyPlataforma4);
        this.bodyPlataforma5.destroyFixture(this.fixturePlataforma5);
        this.world.destroyBody(this.bodyPlataforma5);

        this.bodyLadrillos.destroyFixture(this.fixtureLadrillos);
        this.world.destroyBody(this.bodyLadrillos);

        this.bodyPinchos.destroyFixture(this.fixturePinchos);
        this.world.destroyBody(this.bodyPinchos);

        this.bodyMuro1.destroyFixture(this.fixtureMuro1);
        this.world.destroyBody(this.bodyMuro1);
        this.bodyMuro2.destroyFixture(this.fixtureMuro2);
        this.world.destroyBody(this.bodyMuro2);

        this.bodySuelo1.destroyFixture(this.fixtureSuelo1);
        this.world.destroyBody(this.bodySuelo1);
        this.bodySuelo2.destroyFixture(this.fixtureSuelo2);
        this.world.destroyBody(this.bodySuelo2);

        this.bodyEscalon1.destroyFixture(this.fixtureEscalon1);
        this.world.destroyBody(this.bodyEscalon1);
        this.bodyEscalon2.destroyFixture(this.fixtureEscalon2);
        this.world.destroyBody(this.bodyEscalon2);
        this.bodyEscalon3.destroyFixture(this.fixtureEscalon3);
        this.world.destroyBody(this.bodyEscalon3);

        this.bodyPinchos2.destroyFixture(this.fixturePinchos2);
        this.world.destroyBody(this.bodyPinchos2);
    }
}