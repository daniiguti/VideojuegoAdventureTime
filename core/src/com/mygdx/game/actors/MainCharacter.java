package com.mygdx.game.actors;

import static com.mygdx.game.extra.Utils.USER_PERSONAJE;

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


public class MainCharacter extends Actor {
    //direccion para saber hacia donde vamos a mover nuestro personaje
    //esta se cambia cuando se pulsen los botones o las teclas
    private String direccion;
    //Numero de saltos que lleva, para que no pueda hacer mas de 3
    private int numSaltos;
    private boolean salto;
    private Vector2 position;

    private float stateTime;

    private World world;

    public Body bodyMainCharacter;
    private Fixture fixtureMainCharacter;

    private boolean dead;

    private Animation<TextureRegion> mcAnimation;


    /**
     * Constructor para crearnos a nuestro main character, su body(tipo de elemento) y su fixture(fisica)
     * @param world -> variable para controlar las fisicas del juego
     * @param position -> posicion donde queramos que aparezca nuestro main character
     */
    public MainCharacter(World world, Vector2 position){
        this.position = position;
        this.world = world;

        this.stateTime = 0f;

        this.direccion = "";
        this.salto = false;
        this.dead = false;
        this.numSaltos = 0;

        this.mcAnimation = AssetMan.getAssetMan().getQuietAnimation();

        createBody();
        createFixture();
    }

    /**
     * metodo para crear el tipo de elemento que es nuestro actor
     */
    private void createBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(this.position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        this.bodyMainCharacter = this.world.createBody(bodyDef);
    }

    /**
     * metodo para crear de la forma (hitbox) del mc, en esta caso cuadrada
     */
    private void createFixture(){
        PolygonShape rectangle = new PolygonShape();
        //para darle el tamaño, tiene que ser la mitad de lo que mide la x y la mitad de lo que mide la y
        rectangle.setAsBox(0.1f, 0.1f);

        this.fixtureMainCharacter = this.bodyMainCharacter.createFixture(rectangle, 8);
        //hay que especificarle un id de este objeto, ya que tendremos varios que hacen de fisica
        this.fixtureMainCharacter.setUserData(USER_PERSONAJE);

        //eliminar el circulo de la memoria
        rectangle.dispose();
    }

    /**
     * act, en este act lo que vamos a hacer es comprobar que posicion tiene el personaje
     * para moverlo hacia esa, y si saltó o no
     * @param delta
     */
    @Override
    public void act(float delta) {
        if(this.dead == false) {
            if (salto) {
                this.bodyMainCharacter.setLinearVelocity(0f, 3f);
                this.numSaltos++;
                this.salto = false;
            }

            //controlamos las direcciones
            switch (direccion) {
                case "derecha":
                    this.bodyMainCharacter.setLinearVelocity(new Vector2(1, this.bodyMainCharacter.getLinearVelocity().y));
                    break;
                case "izquierda":
                    this.bodyMainCharacter.setLinearVelocity(new Vector2(-1, this.bodyMainCharacter.getLinearVelocity().y));
                    break;
                case "soltar_derecha":
                case "soltar_izquierda":
                    this.bodyMainCharacter.setLinearVelocity(new Vector2(0, this.bodyMainCharacter.getLinearVelocity().y));
                    break;
            }
        }
    }

    //getter y setter de la direccion, para que dependiendo de el boton que se pulse,
    //este se mueva a la derecha o a la izquierda
    /**
    * @return -> direccion actual
    */
    public String getDireccion() {
        return direccion;
    }
    /**
     * @param direccion -> direccion nueva
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
        //hacemos esto aqui para aprovechar este metodo y cambiar la animacion, y no lo hacemos en el act
        //para evitar que en cada vuelta del render, este cambiando la animacion, sino que lo haga cuando se produzca
        //el evento de cambiar la direccion
        switch(direccion) {
            case "derecha":
                this.mcAnimation = AssetMan.getAssetMan().getRunAnimation();
                break;
            case "izquierda":
                this.mcAnimation = AssetMan.getAssetMan().getLeftRunAnimation();
                break;
            case "soltar_derecha":
                this.mcAnimation = AssetMan.getAssetMan().getQuietAnimation();
                break;
            case "soltar_izquierda":
                this.mcAnimation = AssetMan.getAssetMan().getLeftQuietAnimation();
                break;
        }
    }

    /**
     * metodo para dibujar al mc
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.bodyMainCharacter.getPosition().x - 0.1f, this.bodyMainCharacter.getPosition().y - 0.1f);
        batch.draw(this.mcAnimation.getKeyFrame(stateTime, true), getX(), getY(),0.2f,0.2f);
        //calculo de fps
        stateTime += Gdx.graphics.getDeltaTime();
    }

    /**
     * metodo para limpiar la grafica
     */
    public void detach(){
        this.bodyMainCharacter.destroyFixture(this.fixtureMainCharacter);
        this.world.destroyBody(this.bodyMainCharacter);
    }

    //metodos necesarios para controlar los saltos que puede dar el usuario
    public void setNumSaltos(int numSaltos) {
        this.numSaltos = numSaltos;
    }

    //aux para saltar
    public void saltar(){
        if(this.dead == false) {
            //vamos a hacer la animacion en caso de que haga doble salto
            if (this.numSaltos >= 1) {
                switch (direccion) {
                    case "derecha":
                    case "soltar_derecha":
                        this.mcAnimation = AssetMan.getAssetMan().getJumpAnimation();
                        break;
                    case "izquierda":
                    case "soltar_izquierda":
                        this.mcAnimation = AssetMan.getAssetMan().getLeftJumpAnimation();
                        break;
                }
            }
            if (this.numSaltos <= 2) {
                AssetMan.getAssetMan().getJumpSound().play();
                this.salto = true;
            }
        }
    }

    //para que cuando toque cualquier obstaculo, se le cambie la animacion a la que le toque
    public void cambiarAnimacion(){
        switch (this.direccion){
            case "derecha":
                this.mcAnimation = AssetMan.getAssetMan().getRunAnimation();
                break;
            case "soltar_derecha":
                this.mcAnimation = AssetMan.getAssetMan().getQuietAnimation();
                break;
            case "izquierda":
                this.mcAnimation = AssetMan.getAssetMan().getLeftRunAnimation();
                break;
            case "soltar_izquierda":
                this.mcAnimation = AssetMan.getAssetMan().getLeftQuietAnimation();
                break;
        }
    }

    //para controlar si el personaje murio, no hacer cosas como saltar
    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
