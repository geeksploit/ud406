package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;


public class GameplayScreen extends ScreenAdapter {

    public static final String TAG = GameplayScreen.class.getName();

    private SpriteBatch spriteBatch;
    private ExtendViewport extendViewport;

    @Override
    public void show() {

        Assets.instance.init();
        spriteBatch = new SpriteBatch();
        extendViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
    }

    @Override
    public void resize(int width, int height) {
        extendViewport.update(width, height);
    }

    @Override
    public void dispose() {
        // TODO: Dispose of the Assets instance


        // TODO: Dispose of the SpriteBatch

    }

    @Override
    public void render(float delta) {

        // TODO: Apply the viewport


        // TODO: Clear the screen to the BACKGROUND_COLOR


        // TODO: Set the SpriteBatch's projection matrix


        // TODO: Begin the SpriteBatch


        // TODO: Draw the standing right AtlasRegion


        // TODO: End the SpriteBatch

    }
}
