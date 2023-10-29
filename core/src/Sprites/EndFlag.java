package Sprites;

import Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.RedMan2D;

public class EndFlag extends InteractiveTileObject{
    public EndFlag(World world, TiledMap map, Rectangle bounds, Hud hud) {
        super(world, map, bounds, hud);
        fixture.setUserData(this);
        setCategoryFilter(RedMan2D.ENDFLAG_BIT);
    }

    @Override
    public void interactionWithObject() {
        Gdx.app.log("hitting endflag", "");
    }
}
