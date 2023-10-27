package Sprites;

import Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.RedMan2D;

public class Coin extends InteractiveTileObject{
    private Hud hud;

    public Coin(World world, TiledMap map, Rectangle bounds, Hud hud){
        super(world, map, bounds, hud);
        this.hud = hud;
        fixture.setUserData(this);
        setCategoryFilter(RedMan2D.COIN_BIT);
    }

    @Override
    public void interactionWithObject() {
        Gdx.app.log("hitting coin", "");
        setCategoryFilter(RedMan2D.DESTROYED_BIT);
        getCell().setTile(null);
        hud.addScore(200);
    }
}
