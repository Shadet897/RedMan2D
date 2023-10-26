package Screens;

import Scenes.Hud;
import Sprites.RedManCharakter;
import Tools.B2WorldCreator;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.RedMan2D;

import javax.swing.*;

public class PlayScreen implements Screen {
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
    private Box2DDebugRenderer b2dr;

    //Player
    private RedManCharakter player;

    public PlayScreen(RedMan2D game){

        atlas = new TextureAtlas("RedMan2DPrototype.pack");
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(RedMan2D.V_WIDTH / RedMan2D.PPM, RedMan2D.V_HEIGHT / RedMan2D.PPM, gamecam);
        this.hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();

        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1 / RedMan2D.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);

        player = new RedManCharakter(world, this);
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2){
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2){
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }
    }

    public void update (float dt){
        handleInput(dt);

        world.step(1/60f, 6, 2);

        player.update(dt);

        gamecam.position.x = player.b2body.getPosition().x;

        gamecam.update();


        renderer.setView(gamecam);
    }

    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        // Render box2d debug lines
        b2dr.render(world, gamecam.combined);

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
        b2dr.dispose();
        hud.dispose();
    }
}
