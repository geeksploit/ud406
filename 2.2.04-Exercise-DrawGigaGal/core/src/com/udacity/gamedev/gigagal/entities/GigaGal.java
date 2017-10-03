package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;

public class GigaGal {

    public final static String TAG = GigaGal.class.getName();

    private Vector2 position;

    public GigaGal() {
        position = new Vector2(20, Constants.GIGAGAL_EYE_HEIGHT);
    }

    public void update(float delta) {

    }

    public void render(SpriteBatch batch) {
        batch.draw(Assets.instance.gigaGalAssets.standingRight.getTexture(),
                Constants.GIGAGAL_EYE_POSITION.x, Constants.GIGAGAL_EYE_POSITION.y);
    }
}
