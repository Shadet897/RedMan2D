package Sprites;

import Sprites.RedManCharakter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.RedMan2D;

public class RedManMovement {
    private RedMan2D game;
    private RedManCharakter player;

    public RedManMovement(RedMan2D game, RedManCharakter player){
        this.game = game;
        this.player = player;
    }

    public void handleInput(float dt){
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y == 0|| Gdx.input.isKeyJustPressed(Input.Keys.W)
                && player.b2body.getLinearVelocity().y == 0){
            player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2 || Gdx.input.isKeyPressed(Input.Keys.D)
                && player.b2body.getLinearVelocity().x <= 2){
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2 || Gdx.input.isKeyPressed(Input.Keys.A) &&
                player.b2body.getLinearVelocity().x >= -2) {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        checkOutOfBounds();
    }

    public void checkOutOfBounds(){
        if (player.b2body.getPosition().x < 1 / RedMan2D.PPM){
            player.b2body.setTransform(1 / RedMan2D.PPM, player.b2body.getPosition().y, player.b2body.getAngle());
        }
    }
}
