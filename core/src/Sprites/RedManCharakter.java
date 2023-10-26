package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.RedMan2D;

public class RedManCharakter extends Sprite {
    public World world;
    public Body b2body;

    public RedManCharakter(World world){
        this.world = world;
        defineMario();
    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / RedMan2D.PPM, 64 / RedMan2D.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / RedMan2D.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
