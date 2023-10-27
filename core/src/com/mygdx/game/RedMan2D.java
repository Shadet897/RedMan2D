package com.mygdx.game;

import Screens.MainMenu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;

public class RedMan2D extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final int MENU_WIDTH = 600;
	public static final int MENU_HEIGHT = 308;
	public static final float PPM = 100; //pixels per meter
	public SpriteBatch batch;

	public static final short DEFAULT_BIT = 1;
	public static final short REDMAN_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short ENDFLAG_BIT = 32;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}
}