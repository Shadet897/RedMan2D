package Menus;

import PlayerClasses.RedManCharakter;
import Screens.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RedMan2D;

public class DeathSceen implements Screen {
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Skin skin;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private RedManCharakter player;
    private RedMan2D game;

    public DeathSceen(RedMan2D game, RedManCharakter player){
        this.game = game;
        this.player = player;
        atlas = new TextureAtlas("flat-earth-ui.atlas");
        skin = new Skin(Gdx.files.internal("flat-earth-ui.json"), atlas);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(300, 300, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        Label gameOverLabel = new Label("Game Over!", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        TextButton mainMenuButton = new TextButton("Main Menu", skin);
        TextButton restartLevelButton = new TextButton("Restart?", skin);

        mainTable.add(gameOverLabel).colspan(2).padTop(viewport.getWorldHeight() / 8).row();
        mainTable.add(mainMenuButton).padTop(viewport.getWorldHeight() / 4).expandX();
        mainTable.add(restartLevelButton).padTop(viewport.getWorldHeight() / 4).expandX();

        mainMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                ((RedMan2D)Gdx.app.getApplicationListener()).setScreen(new MainMenu(game));
            }
        });
        restartLevelButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                ((RedMan2D)Gdx.app.getApplicationListener()).setScreen(getLevel(player.atLevel));
            }
        });

        stage.addActor(mainTable);
    }

    public Screen getLevel(int level){
        switch (level){
            case 1: {
                return new Level1(game);
            }
            case 2: {
                return new Level2(game);
            }
            case 3: {
                return new Level3(game);
            }
            case 4: {
                return new Level4(game);
            }
            case 5: {
                return new Level5(game);
            }
            case 6: {
                return new Level6(game);
            }
        }
        return null;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
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
        atlas.dispose();
        skin.dispose();
        batch.dispose();
    }
}
