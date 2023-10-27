package Sprites;

import Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.RedMan2D;

public class Coin extends InteractiveTileObject{
    private Hud hud;
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 6;

    public Coin(World world, TiledMap map, Rectangle bounds, Hud hud){
        super(world, map, bounds, hud);
        this.hud = hud;
        tileSet = map.getTileSets().getTileSet("Tileset1");
        fixture.setUserData(this);
        setCategoryFilter(RedMan2D.COIN_BIT);
    }

    @Override
    public void interactionWithObject() {
        Gdx.app.log("hitting coin", "");
        getCell().setTile(tileSet.getTile(BLANK_COIN));
        hud.addScore(200);
    }
}
