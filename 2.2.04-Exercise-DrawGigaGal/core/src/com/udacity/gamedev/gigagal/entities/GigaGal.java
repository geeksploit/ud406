package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
        TextureAtlas.AtlasRegion atlasRegion = Assets.instance.gigaGalAssets.standingRight;
        batch.draw(
                atlasRegion.getTexture(),
                position.x - Constants.GIGAGAL_EYE_POSITION.x,
                position.y - Constants.GIGAGAL_EYE_POSITION.y,
                0, 0,
                atlasRegion.getRegionWidth(),
                atlasRegion.getRegionHeight(),
                1, 1, 0,
                atlasRegion.getRegionX(),
                atlasRegion.getRegionY(),
                atlasRegion.getRegionWidth(),
                atlasRegion.getRegionHeight(),
                false, false
        );
    }
}
