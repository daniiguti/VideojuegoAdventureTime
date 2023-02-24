package com.mygdx.game.actors;

import static com.mygdx.game.extra.Utils.USER_ENEMY;
import static com.mygdx.game.extra.Utils.USER_ENEMY1;
import static com.mygdx.game.extra.Utils.USER_ENEMY2;
import static com.mygdx.game.extra.Utils.USER_ENEMY3;
import static com.mygdx.game.extra.Utils.USER_ENEMY4;
import static com.mygdx.game.extra.Utils.USER_ENEMY5;

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

public class Enemy extends Actor {
    private static final float SPEED = 0.3f;

    private Vector2 position;

    private World world;

    private Body bodyEnemy;
    private Fixture fixtureEnemy;

    private Animation<TextureRegion> enemyAnimation;
    private float stateTime;

    private boolean dead;
    private Object userDataBody;
    private int tipoEnemigo;

    //constructor
    public Enemy(World world, Vector2 position, int tipoEnemigo){
        this.dead = false;
        this.position = position;

        this.world = world;

        this.stateTime = 0f;

        this.tipoEnemigo = tipoEnemigo;
        //le damos un user body distinto a cada enemigo
        switch (tipoEnemigo){
            case 1:
                this.enemyAnimation = AssetMan.getAssetMan().getEnemyMushroomAnimation();
                this.userDataBody = USER_ENEMY1;
                break;
            case 2:
                this.enemyAnimation = AssetMan.getAssetMan().getEnemyMushroomAnimation();
                this.userDataBody = USER_ENEMY2;
                break;
            case 3:
                this.enemyAnimation = AssetMan.getAssetMan().getEnemyBatAnimation();
                this.userDataBody = USER_ENEMY3;
                break;
            case 4:
                this.enemyAnimation = AssetMan.getAssetMan().getEnemyMushroomAnimation();
                this.userDataBody = USER_ENEMY4;
                break;
            case 5:
                this.enemyAnimation = AssetMan.getAssetMan().getEnemyMushroomAnimation();
                this.userDataBody = USER_ENEMY5;
                break;
        }

        createBody();
        createFixture();
    }

    /**
     * getter que necesito para comprobar la posicion del enemigo y que este no se caiga
     * o traspase las plataformas/paredes
     * @return -> body del actor Enemy
     */
    public Vector2 getBodyPositionEnemy() {
        return this.bodyEnemy.getPosition();
    }

    //tipo kinematic puesto que nos interesa que se mueva pero que no se le apliquen fisicas
    private void createBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(this.position);
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        this.bodyEnemy = this.world.createBody(bodyDef);
        //velocidad negativa
        this.bodyEnemy.setLinearVelocity(new Vector2(-(SPEED),0));
        this.bodyEnemy.setUserData(userDataBody);
    }

    private void createFixture(){
        PolygonShape rectangle = new PolygonShape();
        //para darle el tamaño, tiene que ser la mitad de lo que mide la x y la mitad de lo que mide la y
        rectangle.setAsBox(0.13f, 0.13f);

        this.fixtureEnemy = this.bodyEnemy.createFixture(rectangle, 8);
        //hay que especificarle un id de este objeto, ya que tendremos varios que hacen de fisica
        //el fixture y el body van a tener userData distintos porque hay que identificar 2 cosas:
        //primero que choco con un enemigo(nos da igual el que sea)
        //segundo con que enemigo choco, para "matar a ese", esto ultimo lo averiguamos con el userdata del body
        this.fixtureEnemy.setUserData(USER_ENEMY);
        this.fixtureEnemy.setSensor(true);
        //eliminar el rectangulo de la memoria
        rectangle.dispose();
    }

    @Override
    public void act(float delta) {

    }

    /**
     * metodo que dibuja al personaje
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.bodyEnemy.getPosition().x-0.13f, this.bodyEnemy.getPosition().y-0.13f);
        batch.draw(this.enemyAnimation.getKeyFrame(stateTime, true), getX(), getY(),0.26f,0.26f);
        //calculo de fps
        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void detach(){
        this.bodyEnemy.destroyFixture(this.fixtureEnemy);
        this.world.destroyBody(this.bodyEnemy);
    }

    /**
     * al ser un tipo kinematic este actor(Enemy) no tiene fisicas y hay que estar comprobando
     * con que se choca y cambiandolo de direccion con calculos matematicos, con estos
     * metodos lo movemos para la derecha o para la izquierda cuando nos interese
     */
    public void moverDerecha(){
        this.bodyEnemy.setLinearVelocity(new Vector2(SPEED,0));
        //si el enemigo es el 3, hay que cambiarle la animaion (ya que es el muercielago y queda raro)
        //con las setas no hay que hacerlo pq la animacion nos sirve para los dos lados
        if(this.tipoEnemigo == 3){
            this.enemyAnimation = AssetMan.getAssetMan().getEnemyRightBatAnimation();
        }
    }
    public void moverIzquierda(){
        this.bodyEnemy.setLinearVelocity(new Vector2(-(SPEED),0));
        if(this.tipoEnemigo == 3){
            this.enemyAnimation = AssetMan.getAssetMan().getEnemyBatAnimation();
        }
    }

    /**
     * getter y setter para saber si el enemigo esta muerto o no
     */
    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean dead) {
        if(dead == true){
            AssetMan.getAssetMan().getEnemyDeadSound().play();
        }
        this.dead = dead;
    }
}
