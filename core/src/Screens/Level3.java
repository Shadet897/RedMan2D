package Screens;

import PlayerClasses.RedManDeathCheck;
import Scenes.Hud;
import PlayerClasses.RedManCharakter;
import PlayerClasses.RedManMovement;
import Tools.B2WorldCreator;
import Tools.WorldContactListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.RedMan2D;

public class Level3 implements Screen {
    private RedMan2D game;
    private TextureAtlas atlas;


    private OrthographicCamera gamecam;
    private FitViewport gamePort;
    private Hud hud;
    //Tiled map
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    //Box 2d
    private World world;
    //Player
    private RedManCharakter player;
    //movement
    private RedManMovement movement;
    //Death
    private RedManDeathCheck checkDeath;

    public Level3(RedMan2D game){

        atlas = new TextureAtlas("RedMan2DPrototype.pack");
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(RedMan2D.V_WIDTH / RedMan2D.PPM, RedMan2D.V_HEIGHT / RedMan2D.PPM, gamecam);
        this.hud = new Hud(game.batch, 1, 3);

        mapLoader = new TmxMapLoader();
        //cam,map,renderer
        map = mapLoader.load("level3.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1 / RedMan2D.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        //world debug tool
        world = new World(new Vector2(0, -10), true);
        //create map
        new B2WorldCreator(world, map, hud);
        //character and movement
        player = new RedManCharakter(world);
        player.atLevel = 2;
        this.movement = new RedManMovement(game, player);
        this.checkDeath = new RedManDeathCheck(game, player, hud);

        world.setContactListener(new WorldContactListener());
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    public void update (float dt){
        movement.handleInput(dt);
        checkDeath.checkDeathByFalling();
        checkDeath.checkDeathByTimer();

        world.step(1/60f, 6, 2);

        player.update(dt);
        hud.update(dt);

        if(player.b2body.getPosition().x < 201 / game.PPM){
            gamecam.position.x = 201 / game.PPM;
        }
        else {
            gamecam.position.x = player.b2body.getPosition().x;
            gamecam.update();
        }

        renderer.setView(gamecam);
    }

    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        game.batch.setProjectionMatrix(gamecam.combined); // Set the projection matrix here

        game.batch.begin();
        float playerWidth = player.redManStanding.getWidth() / game.PPM;
        float playerHeight = player.redManStanding.getHeight() / game.PPM;
        float playerX = player.b2body.getPosition().x - (playerWidth / 2);
        float playerY = player.b2body.getPosition().y - (playerHeight / 2);
        game.batch.draw(player.redManStanding, playerX, playerY, playerWidth, playerHeight);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        hud.dispose();
    }
}