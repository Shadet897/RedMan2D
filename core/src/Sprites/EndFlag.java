package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.RedMan2D;

public class EndFlag extends InteractiveTileObject{
    public EndFlag(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);

    }
}
