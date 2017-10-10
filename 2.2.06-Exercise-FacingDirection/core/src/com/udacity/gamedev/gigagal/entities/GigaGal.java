package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;

public class GigaGal {

    public final static String TAG = GigaGal.class.getName();

    Vector2 position;
    Facing facing;

    public GigaGal() {
        position = new Vector2(20, Constants.GIGAGAL_EYE_HEIGHT);
        // TODO: Initialize facing, probably with Facing.RIGHT

    }

    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            moveLeft(delta);
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            moveRight(delta);
        }
    }

    private void moveLeft(float delta) {
        // TODO: Update facing direction

        position.x -= delta * Constants.GIGAGAL_MOVE_SPEED;
    }

    private void moveRight(float delta) {
        // TODO: Update facing direction

        position.x += delta * Constants.GIGAGAL_MOVE_SPEED;
    }

    public void render(SpriteBatch batch) {

        TextureRegion region = Assets.instance.gigaGalAssets.standingRight;

        // TODO: Set region to the correct sprite for the current facing direction


        batch.draw(
                region.getTexture(),
                position.x - Constants.GIGAGAL_EYE_POSITION.x,
                position.y - Constants.GIGAGAL_EYE_POSITION.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }

    enum Facing {
        LEFT, RIGHT
    }
}
