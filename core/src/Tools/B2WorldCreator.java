package Tools;

import Scenes.Hud;
import Sprites.Brick;
import Sprites.Coin;
import Sprites.EndFlag;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.RedMan2D;

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map, Hud hud){

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        //create ground
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / RedMan2D.PPM, (rect.getY() + rect.getHeight() / 2)/ RedMan2D.PPM) ;

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / RedMan2D.PPM, rect.getHeight() / 2 / RedMan2D.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //create coins
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(world, map, rect, hud);
        }
        //create bricks
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(world, map, rect, hud);

        }
        //create endFlag
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new EndFlag(world, map, rect, hud);
        }
    }
}
