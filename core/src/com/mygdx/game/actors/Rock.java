package com.mygdx.game.actors;

import static com.mygdx.game.extra.Utils.USER_ROCK;

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

/**
 * actor roca
 */
public class Rock extends Actor {

    private static final float SPEED = 0.4f;

    private Body bodyRock;
    private Fixture fixtureRock;

    private Animation<TextureRegion> rockAnimation;
    private float stateTime;

    private World world;
    private Vector2 position;

    public Rock(World world, Vector2 position) {
        this.world = world;
        this.position = position;

        this.stateTime = 0f;
        this.rockAnimation = AssetMan.getAssetMan().getRockAnimation();

        createBody();
        createFixture();
    }

    public Vector2 getBodyPositionRock() {
        return this.bodyRock.getPosition();
    }

    private void createBody() {
        BodyDef def = new BodyDef();
        def.position.set(this.position);
        def.type = BodyDef.BodyType.KinematicBody;
        this.bodyRock = this.world.createBody(def);
        this.bodyRock.setLinearVelocity(new Vector2(0, SPEED));
    }
    private void createFixture() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.2f, 0.2f);
        this.fixtureRock = bodyRock.createFixture(shape, 3);
        this.fixtureRock.setUserData(USER_ROCK);
        shape.dispose();
    }

    public void act(float delta) {

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.bodyRock.getPosition().x-0.2f, this.bodyRock.getPosition().y-0.2f);
        batch.draw(this.rockAnimation.getKeyFrame(stateTime, true), getX(), getY(), 0.4f,0.4f );

        //calculo de fps
        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void detach(){
        this.bodyRock.destroyFixture(this.fixtureRock);
        world.destroyBody(this.bodyRock);
    }

    /**
     * metodos para cambiar la direccion de la roca
     */
    public void moverAbajo(){
        this.bodyRock.setLinearVelocity(new Vector2(0, -(SPEED)));
    }
    public void moverArriba(){
        this.bodyRock.setLinearVelocity(new Vector2(0, SPEED));
    }
}
