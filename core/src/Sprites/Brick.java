package Sprites;

import Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.RedMan2D;

public class Brick extends InteractiveTileObject{
    private Hud hud;

    public Brick(World world, TiledMap map, Rectangle bounds, Hud hud){
        super(world, map, bounds, hud);
        this.hud = hud;
        fixture.setUserData(this);
        setCategoryFilter(RedMan2D.BRICK_BIT);
    }

    @Override
    public void interactionWithObject() {
        Gdx.app.log("hitting brick", "");
        setCategoryFilter(RedMan2D.DESTROYED_BIT);
        getCell().setTile(null);
        hud.addScore(10);
    }

}
