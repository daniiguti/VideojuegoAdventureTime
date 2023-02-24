package com.mygdx.game.screens;

import static com.mygdx.game.extra.Utils.WORLD_HEIGHT;
import static com.mygdx.game.extra.Utils.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.actors.Controller;
import com.mygdx.game.actors.Enemy;
import com.mygdx.game.actors.Fruit;
import com.mygdx.game.actors.MainCharacter;
import com.mygdx.game.actors.Obstacles;
import com.mygdx.game.actors.Pared;
import com.mygdx.game.actors.Rock;
import com.mygdx.game.actors.Suelo;
import com.mygdx.game.actors.Techo;
import com.mygdx.game.actors.Trofeo;
import com.mygdx.game.extra.AssetMan;

public class GameScreen extends BaseScreen{
    //Controladores
    private Stage stage;
    private InputMultiplexer im;
    private Controller controller;
    private OrthographicCamera ortCamera;

    //Actores
    private Image background;
    private Suelo suelo;
    private Techo techo;

    private MainCharacter mc;
    private Pared pared1;
    private Pared pared2;

    private Obstacles obstacles;

    private Rock rock;
    private Rock rock2;

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

    private Trofeo trofeo;

    private int numFrutasCogidas;

    //variable que controla las fisicas del juego
    private World world;

    //Listener de colisiones
    private ColisionListener cl;

    //Musica
    private Music musicgamescreen;

    /**
     * Constructor de GameScreen
     * @param maingame
     */
    public GameScreen(MainGame maingame){
        super(maingame);
        //Inicializacion del mundo, (doSleep = true) para que cuando toque el suelo se quede parado ahí y el mundo no siga ejerciendo presion
        this.world = new World(new Vector2(0,-10f), true);

        //PARAMETROS:                                      minimo que se tiene que ver -- maximo que se puede ver
        //es decir, la pantalla cogera el maximo que se pueda ver del mundo, pero el minimo sera ese,
        //esto se hace para evitar que haya bordes negros
        ExtendViewport extendViewport = new ExtendViewport(8f, WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
        //Director o escenario, encargado de añadir los actores
        this.stage = new Stage(extendViewport);

        //Listener de colision
        cl = new ColisionListener(this.mainGame, this.stage);
        this.world.setContactListener(cl);

        this.numFrutasCogidas = 0;
        //Camara
        this.ortCamera = (OrthographicCamera) this.stage.getCamera();

        //multiplexer para capturar varios eventos
        this.im = new InputMultiplexer();
        //creacion de botones y toque
        this.controller = new Controller();

        //Inicializacion de sonidos
        this.musicgamescreen = AssetMan.getAssetMan().getMusicGameScreen();
        this.cl.setMusicGameScreen(this.musicgamescreen);
    }

    /**
     * añadir el fondo
     */
    public void addBackground(){
        //nos creamos el fondo con la clase AssetMan y el metodo que cargaba el fondo y le damos otros
        //atributos
        this.background = new Image(AssetMan.getAssetMan().getBackground());
        this.background.setPosition(0,0);
        this.background.setSize(WORLD_WIDTH,WORLD_HEIGHT);

        this.stage.addActor(this.background);
    }

    /**
     * añadir suelo
     */
    public void addSuelo(){
        this.suelo = new Suelo(this.world, new Vector2(10f,0f));
        this.stage.addActor(this.suelo);
    }

    /**
     * añadir techo
     */
    public void addTecho(){
        this.techo = new Techo(this.world, new Vector2(10f, WORLD_HEIGHT));
        this.stage.addActor(techo);
    }

    /**
     * añadir paredes
     */
    public void addParedes(){
        this.pared1 = new Pared(this.world, new Vector2(0f, 2.4f));
        this.pared2 = new Pared(this.world, new Vector2(20f, 2.4f));

        this.stage.addActor(pared1);
        this.stage.addActor(pared2);
    }

    /**
     * metodo para añadir todos los obstaculos, esto estaria mejor hecho con TiledMap, de esta forma
     * lo haces tod a "pelo"
     */
    public void addObstacles(){
        this.obstacles = new Obstacles(this.world);
        this.stage.addActor(this.obstacles);
    }

    /**
     * metodo para añadir la roca
     */
    public void addRock(){
        this.rock = new Rock(this.world, new Vector2(5.1f, 1f));
        this.rock2 = new Rock(this.world, new Vector2(15.5f, 2.2f));

        this.stage.addActor(this.rock);
        this.stage.addActor(this.rock2);
    }

    /**
     * metodo para añadir los enemigos, y una vez creados se los pasamos al CollisionListener
     */
    public void addEnemies(){
        this.enemy1 = new Enemy(this.world, new Vector2(1f, 3.18f), 1);
        this.enemy2 = new Enemy(this.world, new Vector2(7f, 1.53f), 2);
        this.enemy3 = new Enemy(this.world, new Vector2(3.7f, 3.12f), 3);
        this.enemy4 = new Enemy(this.world, new Vector2(10f, 0.73f), 4);
        this.enemy5 = new Enemy(this.world, new Vector2(13f, 3.03f), 5);

        this.stage.addActor(this.enemy1);
        this.stage.addActor(this.enemy2);
        this.stage.addActor(this.enemy3);
        this.stage.addActor(this.enemy4);
        this.stage.addActor(this.enemy5);

        this.cl.setEnemy1(this.enemy1);
        this.cl.setEnemy2(this.enemy2);
        this.cl.setEnemy3(this.enemy3);
        this.cl.setEnemy4(this.enemy4);
        this.cl.setEnemy5(this.enemy5);
    }

    /**
     * metodo para añadir al main character y pasarselo al CollisionListener
     */
    public void addMainCharacter(){
        this.mc = new MainCharacter(world, new Vector2(1f,0.7f));
        this.stage.addActor(this.mc);
        this.cl.setMc(this.mc);
        this.controller.setMc(this.mc);
    }

    /**
     * metodo para añadir las frutas
     */
    public void addFruits(){
        this.fruit1 = new Fruit(world, new Vector2(0.6f, 3.2f), 1);
        this.fruit2 = new Fruit(world, new Vector2(3.3f, 3.95f), 2);
        this.fruit3 = new Fruit(world, new Vector2(6f, 3.05f), 3);
        this.fruit4 = new Fruit(world, new Vector2(9.8f, 1.85f), 4);
        this.fruit5 = new Fruit(world, new Vector2(13.4f, 3.05f), 5);

        this.stage.addActor(this.fruit1);
        this.stage.addActor(this.fruit2);
        this.stage.addActor(this.fruit3);
        this.stage.addActor(this.fruit4);
        this.stage.addActor(this.fruit5);

        this.cl.setFruit1(this.fruit1);
        this.cl.setFruit2(this.fruit2);
        this.cl.setFruit3(this.fruit3);
        this.cl.setFruit4(this.fruit4);
        this.cl.setFruit5(this.fruit5);
    }

    /**
     * metodo para añadir el trofeo final
     */
    public void addTrofeo(){
        this.trofeo = new Trofeo(world);
        this.stage.addActor(trofeo);
    }

    /**
     * metodo para añadir los botones
     */
    public void addButtons(){
        // Añadir botones a la escena
        this.stage.addActor(this.controller.getRightButton());
        this.stage.addActor(this.controller.getLeftButton());

        //como los botones estan en el stage, añadimos el stage como un processor
        im.addProcessor(this.stage);
        //addemas añadimos un proceso para cuando se toque la pantalla
        im.addProcessor(this.controller.getTouch());

        //para asignarle un input procesor, en este caso como tenemos varias entradas hemos usado un multiplexer,
        //el cual ya hemos configurado antes, dandole todas las entradas que necesita
        Gdx.input.setInputProcessor(this.im);
    }


    /**
     * metodo para dibujar los objetos de juego en la pantalla y actualizar la pantalla con los últimos
     * cambios en el juego.
     * @param delta -> parametro automatico, cuanto dura un fps 0,016 seg (en el mejor de los casos)
     */
    private int numVecesGenerada = 0;
    @Override
    public void render(float delta) {
        //para limpiar la grafica
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Actualizamos para que las cosas para que se dibujen donde se tienen que dibujar
        this.ortCamera.update();
        this.stage.getBatch().setProjectionMatrix(this.ortCamera.combined);

        //cambiamos la posicion de la camara para que siga al personaje
        this.ortCamera.position.set(this.mc.getX(), WORLD_HEIGHT/2, 0);

        //si las frutas fueron cogidas, mostramos el trofeo final
        if(this.numFrutasCogidas == 5 && numVecesGenerada == 0){
            addTrofeo();
            this.numVecesGenerada++;
        }
        //metodos para comprobar si se pueden eliminar o no actores, lo hacemos antes de que se dibuje nada
        this.comprobarVidaEnemigos();
        this.comprobarFrutasCogidas();

        //el stage(escenario) va a coger todos los metodos act, de los actores que se le hayan añadido
        this.stage.act();

        //comprobaciones
        this.colision_obstaculo_roca_suelo();
        this.colision_suelo_roca2_techo();
        this.enemigo1_plataforma();
        this.enemigo2_plataforma();
        this.enemigo3_cielo();
        this.enemigo4_plataforma();
        this.enemigo5_plataforma();

        this.world.step(delta,6,2);
        //el stage(escenario) va a coger todos los metodos draw, de los actores que se le hayan añadido
        this.stage.draw();

        this.ortCamera.update();
        //cambiamos la posicion de los botones, para que estos se muevan junto a la camara
        this.controller.cambiarPosicion(this.ortCamera.position.x);
    }

    /**
     * metodo para mostrar los actores(como el onResume de las activity)
     */
    @Override
    public void show() {
        addBackground();
        addSuelo();
        addTecho();
        addParedes();
        addObstacles();
        addFruits();
        addRock();
        addEnemies();
        addButtons();
        addMainCharacter();

        this.musicgamescreen.setLooping(true);
        this.musicgamescreen.play();
    }

    /**
     * metodo para ocultar temporalmente una ventana o pantalla específica(como el onPause de las activity), pero esta se puede volver a mostrar
     */
    @Override
    public void hide() {
        this.mc.detach();
        this.obstacles.detach();
        this.musicgamescreen.stop();
    }

    /**
     * metodo para liberar los recursos utilizados por esa pantalla
     * una vez que se llama al método dispose(), no se puede volver a mostrar la pantalla y se debe crear una nueva instancia si se desea volver a utilizar.
     */
    @Override
    public void dispose() {
        this.stage.dispose();
        this.world.dispose();
    }

    //METODOS AUXILIARES
    //metodo para comprobar cuando la roca choca con el suelo o con la plataforma
    public void colision_obstaculo_roca_suelo(){
        double posicionRoca = this.rock.getBodyPositionRock().y + 0.1f;
        double posicionObstaculo = this.obstacles.getBodyPositionPlataforma2().y - 0.2f;
        if(posicionRoca >= posicionObstaculo){
            this.rock.moverAbajo();
        }else{
            double posicionSuelo = this.suelo.getBodyPositionSuelo().y + 0.3f;
            posicionRoca = this.rock.getBodyPositionRock().y - 0.48f;
            if(posicionRoca <= posicionSuelo){
                this.rock.moverArriba();
            }
        }
    }

    //metodo para comprobar cuando el enemigo choca con la pared o con el extremo
    private void enemigo1_plataforma(){
        if(enemigo1PuedeMorir == true) {
            double posicionEnemigo1 = this.enemy1.getBodyPositionEnemy().x - 0.2f;
            double posicionParedIzquierda = this.pared1.getBodyPositionPared().x + 0.075f;
            if (posicionEnemigo1 <= posicionParedIzquierda) {
                this.enemy1.moverDerecha();
            } else {
                double posExtremoDerecho = 1.85f;
                posicionEnemigo1 = this.enemy1.getBodyPositionEnemy().x + 0.2f;
                if (posicionEnemigo1 >= posExtremoDerecho) {
                    this.enemy1.moverIzquierda();
                }
            }
        }
    }

    //metodo para comprobar cuando el enemigo choca con la pared o con el extremo
    private void enemigo2_plataforma(){
        if(enemigo2PuedeMorir == true) {
            double posicionEnemigo2 = this.enemy2.getBodyPositionEnemy().x + 0.2f;
            double posExtremoDerecho = 7.9f;
            if (posicionEnemigo2 >= posExtremoDerecho) {
                this.enemy2.moverIzquierda();
            } else {
                double posExtremoIzquierdo = 5.7f;
                posicionEnemigo2 = this.enemy2.getBodyPositionEnemy().x - 0.2f;
                if (posicionEnemigo2 <= posExtremoIzquierdo) {
                    this.enemy2.moverDerecha();
                }
            }
        }
    }

    //metodo para comprobar cuando el enemigo choca con cualquiera dde los dos extremos
    private void enemigo3_cielo(){
        if(enemigo3PuedeMorir == true) {
            double posicionEnemigo = this.enemy3.getBodyPositionEnemy().x + 0.05f;
            double extremoDerecho = 6.1f;
            if (posicionEnemigo >= extremoDerecho) {
                this.enemy3.moverIzquierda();
            } else {
                posicionEnemigo = this.enemy3.getBodyPositionEnemy().x - 0.05f;
                double extremoIzquierdo = 2.53f;
                if (posicionEnemigo <= extremoIzquierdo) {
                    this.enemy3.moverDerecha();
                }
            }
        }
    }

    //metodo para controlar que el enemigo 4 no se vaya
    private void enemigo4_plataforma(){
        if(enemigo4PuedeMorir == true){
            double posicionEnemigo4 = this.enemy4.getBodyPositionEnemy().x + 0.2f;
            double posExtremoDerecho = this.obstacles.getBodyPositionMuro2().x - 0.2f;
            if (posicionEnemigo4 >= posExtremoDerecho) {
                this.enemy4.moverIzquierda();
            } else {
                double posExtremoIzquierdo = 9.2f;
                posicionEnemigo4 = this.enemy4.getBodyPositionEnemy().x - 0.4f;
                if (posicionEnemigo4 <= posExtremoIzquierdo) {
                    this.enemy4.moverDerecha();
                }
            }
        }
    }

    //metodo para controlar que el enemigo 5 no se vaya
    private void enemigo5_plataforma(){
        //aprovechamos la variable candado, para que si ya ha muerto no pierda el tiempo haciendo todos estos calculos
        if(enemigo5PuedeMorir == true){
            double posicionEnemigo5 = this.enemy5.getBodyPositionEnemy().x + 0.2f;
            double posExtremoDerecho = this.obstacles.getBodyPositionMuro2().x - 0.2f;
            if (posicionEnemigo5 >= posExtremoDerecho) {
                this.enemy5.moverIzquierda();
            } else {
                double posExtremoIzquierdo = 9.9f;
                posicionEnemigo5 = this.enemy5.getBodyPositionEnemy().x - 0.4f;
                if (posicionEnemigo5 <= posExtremoIzquierdo) {
                    this.enemy5.moverDerecha();
                }
            }
        }
    }

    //metodo para comprobar cuando la roca choca con el suelo o con el techo
    public void colision_suelo_roca2_techo(){
        double posicionRoca = this.rock2.getBodyPositionRock().y + 0.1f;
        double posicionTecho = this.techo.getBodyPositionTecho().y - 0.2f;
        if(posicionRoca >= posicionTecho){
            this.rock2.moverAbajo();
        }else{
            double posicionSuelo = this.suelo.getBodyPositionSuelo().y + 0.3f;
            posicionRoca = this.rock2.getBodyPositionRock().y - 0.48f;
            if(posicionRoca <= posicionSuelo){
                this.rock2.moverArriba();
            }
        }
    }

    //metodo para comprobar si los enemigos estan ya muertos o no
    //(realmente no son necesarias, pero las usamos para asi cuando un enemigo muera evitar tener que hacer
    // las comprobaciones)
    private boolean enemigo1PuedeMorir = true;
    private boolean enemigo2PuedeMorir = true;
    private boolean enemigo3PuedeMorir = true;
    private boolean enemigo4PuedeMorir = true;
    private boolean enemigo5PuedeMorir = true;
    private void comprobarVidaEnemigos(){
        //Para comprobar si estos actores ya fueron eliminados o para que se eliminen UNA SOLA VEZ, ya que si estos se eliminaron
        //e intentamos hacer esto otra vez, da fallo, puesto que va a eliminar algo vacio
        if(this.enemy1.isDead() == true && enemigo1PuedeMorir==true){
            this.enemy1.detach();
            this.enemy1.remove();
            enemigo1PuedeMorir = false;
        }
        if(this.enemy2.isDead() == true && enemigo2PuedeMorir==true){
            this.enemy2.detach();
            this.enemy2.remove();
            enemigo2PuedeMorir = false;
        }
        if(this.enemy3.isDead() == true && enemigo3PuedeMorir==true){
            this.enemy3.detach();
            this.enemy3.remove();
            enemigo3PuedeMorir = false;
        }
        if(this.enemy4.isDead() == true && enemigo4PuedeMorir==true){
            this.enemy4.detach();
            this.enemy4.remove();
            enemigo4PuedeMorir = false;
        }
        if(this.enemy5.isDead() == true && enemigo5PuedeMorir==true){
            this.enemy5.detach();
            this.enemy5.remove();
            enemigo5PuedeMorir = false;
        }
    }

    //metodo para comprobar si las frutas estan ya cogidas o no
    private boolean fruta1PuedeSerCogida = true;
    private boolean fruta2PuedeSerCogida = true;
    private boolean fruta3PuedeSerCogida = true;
    private boolean fruta4PuedeSerCogida = true;
    private boolean fruta5PuedeSerCogida = true;
    private void comprobarFrutasCogidas(){
        if(this.fruit1.isCogida() == true && fruta1PuedeSerCogida == true){
            this.fruit1.detach();
            this.fruit1.remove();
            fruta1PuedeSerCogida = false;
            this.numFrutasCogidas++;
        }
        if(this.fruit2.isCogida() == true && fruta2PuedeSerCogida == true){
            this.fruit2.detach();
            this.fruit2.remove();
            fruta2PuedeSerCogida = false;
            this.numFrutasCogidas++;
        }
        if(this.fruit3.isCogida() == true && fruta3PuedeSerCogida == true){
            this.fruit3.detach();
            this.fruit3.remove();
            fruta3PuedeSerCogida = false;
            this.numFrutasCogidas++;
        }
        if(this.fruit4.isCogida() == true && fruta4PuedeSerCogida == true){
            this.fruit4.detach();
            this.fruit4.remove();
            fruta4PuedeSerCogida = false;
            this.numFrutasCogidas++;
        }
        if(this.fruit5.isCogida() == true && fruta5PuedeSerCogida == true){
            this.fruit5.detach();
            this.fruit5.remove();
            fruta5PuedeSerCogida = false;
            this.numFrutasCogidas++;
        }
    }
}
