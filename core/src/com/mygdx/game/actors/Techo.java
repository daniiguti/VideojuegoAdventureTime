package com.mygdx.game.actors;

import static com.mygdx.game.extra.Utils.USER_TECHO;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.extra.AssetMan;

public class Techo extends Actor {

    private Vector2 position;

    private World world;

    private Body body;
    private Fixture fixture;

    /**
     * constructor del techo, dependiendo del vector que le pasemos sera el cielo o el suelo
     * ademas creamos el body y el Fixture
     *
     * @param world -> se le pasa el mundo para poder darle las fisicas, etc
     */
    public Techo(World world, Vector2 position) {
        this.position = position;

        this.world = world;

        createBody();
        createFixture();
    }

    private void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(this.position);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.body = this.world.createBody(bodyDef);
    }

    private void createFixture() {
        PolygonShape rectangle = new PolygonShape();
        //para darle el tamaño, tiene que ser la mitad de lo que mide la x y la mitad de lo que mide la y
        rectangle.setAsBox(10f, 0.15f);

        this.fixture = this.body.createFixture(rectangle, 8);
        //hay que especificarle un id de este objeto, ya que tendremos varios que hacen de fisica
        this.fixture.setUserData(USER_TECHO);

        //eliminar el rectangulo de la memoria
        rectangle.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.body.getPosition().x - 10f, this.body.getPosition().y - 0.15f);
        batch.draw(AssetMan.getAssetMan().getTecho(), getX(),getY(), 20f,0.3f);
    }


    /**
     * metodo para limpiar la grafica
     */
    public void detach() {
        this.body.destroyFixture(this.fixture);
        this.world.destroyBody(this.body);
    }

    /**
     * metodo para obtener la posicion del body del techo
     * @return -> posicion del techo
     */
    public Vector2 getBodyPositionTecho(){
        return this.body.getPosition();
    }
}
