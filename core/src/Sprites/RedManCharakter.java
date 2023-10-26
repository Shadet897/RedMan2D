package Sprites;

import Screens.PlayScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.RedMan2D;

public class RedManCharakter extends Sprite {
    public World world;
    public Body b2body;
    public  Texture redManStanding;
    //private TextureRegion redmanStand;

    public RedManCharakter(World world, PlayScreen screen){
        //super(screen.getAtlas().findRegion("little_mario"));
        redManStanding = new Texture("RedManStanding.png");
        this.world = world;
        defineMario();
        //redmanStand = new TextureRegion(getTexture(), 0, 0, 16, 16);
        //setBounds(0, 0, 16 / RedMan2D.PPM, 16 / RedMan2D.PPM);
        //setRegion(redmanStand);
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
        CircleShape shape = new CircleShape();
        shape.setRadius(7 / RedMan2D.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
