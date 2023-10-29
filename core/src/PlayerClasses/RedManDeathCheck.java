package PlayerClasses;

import Menus.DeathSceen;
import Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.RedMan2D;

public class RedManDeathCheck implements Disposable {
    private RedMan2D game;
    private RedManCharakter player;
    private Hud hud;

    public RedManDeathCheck(RedMan2D game, RedManCharakter player, Hud hud){
        this.game = game;
        this.player = player;
        this.hud = hud;
    }

    public void checkDeathByFalling(){
        if (player.b2body.getPosition().y < 0){
            dispose();
            ((RedMan2D) Gdx.app.getApplicationListener()).setScreen(new DeathSceen(game, player));
        }
    }
    public void checkDeathByTimer(){
        if (hud.getWorldTimer() < 0) {
            dispose();
            ((RedMan2D) Gdx.app.getApplicationListener()).setScreen(new DeathSceen(game, player));
        }
    }

    @Override
    public void dispose() {
        hud.dispose();
        game.dispose();
    }
}
