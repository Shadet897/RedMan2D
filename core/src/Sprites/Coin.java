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
    private final int COIN_BLOCK = 31;

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
        if (getCell().getTile().equals(tileSet.getTile(COIN_BLOCK))){
            hud.addScore(200);
            getCell().setTile(tileSet.getTile(BLANK_COIN));
        }
    }
}
