package com.udacity.gamedev.animations;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Animations extends ApplicationAdapter {


    private static final float EXPLOSION_SPAWN_RATE = 20;
    private static final float EXPLOSION_FRAME_DURATION = 0.1f;

    private static final float WALK_LOOP_FRAME_DURATION = 0.1f;

    private SpriteBatch batch;
    private ExtendViewport viewport;

    private Animation walkLoop;
    private long startTime;

    private Animation explosion;
    private DelayedRemovalArray<OneShotAnimation> explosions;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new ExtendViewport(100, 100);

        startTime = TimeUtils.nanoTime();

        Array<TextureRegion> walkLoopTextures = new Array<TextureRegion>();

        walkLoopTextures.add(new TextureRegion(new Texture("walk-1-right.png")));
        walkLoopTextures.add(new TextureRegion(new Texture("walk-2-right.png")));
        walkLoopTextures.add(new TextureRegion(new Texture("walk-3-right.png")));

        walkLoop = new Animation(WALK_LOOP_FRAME_DURATION, walkLoopTextures, PlayMode.LOOP_PINGPONG);

        Array<TextureRegion> explosionTextures = new Array<TextureRegion>();
        explosionTextures.add(new TextureRegion(new Texture("explosion-large.png")));
        explosionTextures.add(new TextureRegion(new Texture("explosion-medium.png")));
        explosionTextures.add(new TextureRegion(new Texture("explosion-small.png")));
        explosion = new Animation(EXPLOSION_FRAME_DURATION, explosionTextures, PlayMode.NORMAL);
        explosions = new DelayedRemovalArray<OneShotAnimation>();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        updateExplosions();
        viewport.apply();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        float elapsedTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - startTime);

        TextureRegion walkLoopTexture = walkLoop.getKeyFrame(elapsedTime);

        drawRegionCentered(
                batch,
                walkLoopTexture,
                viewport.getWorldWidth() / 2,
                viewport.getWorldHeight() / 2
        );

        for (OneShotAnimation explosion : explosions) {
            drawRegionCentered(batch, explosion.getFrame(), explosion.position.x, explosion.position.y);
        }

        batch.end();
    }

    private void drawRegionCentered(SpriteBatch batch, TextureRegion region, float x, float y) {
        batch.draw(
                region.getTexture(),
                x - region.getRegionWidth() / 2,
                y - region.getRegionHeight() / 2,
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

    private void updateExplosions() {

        // Remove explosions that are done
        explosions.begin();
        for (int i = 0; i < explosions.size; i++) {
            if (explosions.get(i).isAnimationFinished()) {
                explosions.removeIndex(i);
            }
        }
        explosions.end();

        // Randomly spawn a new explosion
        if (MathUtils.random() < Gdx.graphics.getDeltaTime() * EXPLOSION_SPAWN_RATE) {
            Vector2 position = new Vector2(
                    MathUtils.random(viewport.getWorldWidth()),
                    MathUtils.random(viewport.getWorldWidth())
            );
            explosions.add(new OneShotAnimation(explosion, position, TimeUtils.nanoTime()));
        }
    }


    private class OneShotAnimation {
        final Vector2 position;
        private final Animation animation;
        private final long startTimeNanos;

        OneShotAnimation(Animation animation, Vector2 position, long startTimeNanos) {
            this.animation = animation;
            this.position = position;
            this.startTimeNanos = startTimeNanos;
        }

        private float elapsedTime() {
            return MathUtils.nanoToSec * (TimeUtils.nanoTime() - startTimeNanos);
        }

        TextureRegion getFrame() {
            return animation.getKeyFrame(elapsedTime());
        }

        boolean isAnimationFinished() {
            return animation.isAnimationFinished(elapsedTime());
        }


    }
}
