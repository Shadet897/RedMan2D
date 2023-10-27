package Screens;

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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RedMan2D;

public class LevelMenu implements Screen {

   private SpriteBatch batch;
   private TextureAtlas atlas;
   private Skin skin;
   private OrthographicCamera camera;
   private Stage stage;
   private Viewport viewport;

   private RedMan2D game;

   public LevelMenu(RedMan2D game){
       this.game = game;
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

        Table levelTable = new Table();
        levelTable.setFillParent(true);
        levelTable.top();

        //Buttons
        TextButton level1Button = new TextButton("Level1", skin);
        TextButton level2Button = new TextButton("Level2", skin);
        TextButton level3Button = new TextButton("Level3", skin);
        TextButton closeButton = new TextButton("close", skin);

        //label
        Label levelstext = new Label("Levels",new Label.LabelStyle(new BitmapFont(), Color.WHITE) );

        level1Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((RedMan2D)Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
            }
        });
        closeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((RedMan2D)Gdx.app.getApplicationListener()).setScreen(new MainMenu(game));
            }
        });

        //add elements to table
        levelTable.pad(viewport.getWorldHeight() / 8);
        levelTable.add(level1Button);
        levelTable.row();
        levelTable.row().padTop(10);
        levelTable.add(level2Button);
        levelTable.row();
        levelTable.row().padTop(10);
        levelTable.add(level3Button);
        levelTable.row();
        levelTable.row().padTop(viewport.getWorldHeight() / 8);
        levelTable.add(closeButton);

        stage.addActor(levelTable);
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

    }
}
