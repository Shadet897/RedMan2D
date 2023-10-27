package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.RedMan2D;

public class RedManCharakter extends Sprite {
    public World world;
    public Body b2body;
    public  Texture redManStanding;

    public RedManCharakter(World world){
        redManStanding = new Texture("RedManStanding.png");
        this.world = world;
        defineMario();
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / RedMan2D.PPM, 64 / RedMan2D.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(6 /  RedMan2D.PPM, 6 / RedMan2D.PPM);
        fdef.filter.categoryBits = RedMan2D.REDMAN_BIT;
        fdef.filter.maskBits = RedMan2D.DEFAULT_BIT | RedMan2D.COIN_BIT | RedMan2D.BRICK_BIT | RedMan2D.ENDFLAG_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-4 / RedMan2D.PPM, 6 / RedMan2D.PPM), new Vector2(4 / RedMan2D.PPM, 6 / RedMan2D.PPM));
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("head");
    }
}
