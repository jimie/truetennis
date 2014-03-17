package org.zapylaev.game.truetennis.core.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.zapylaev.game.truetennis.core.Assets;
import org.zapylaev.game.truetennis.core.Constants;
import org.zapylaev.game.truetennis.core.Debug;
import org.zapylaev.game.truetennis.core.domain.Field;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
class HUD {
    private static final float SCORE_RIGHT_MARGIN = 520;
    private static final float SCORE_TOP_MARGIN = 15;

    private final OrthographicCamera mHUDCamera;
    private FpsCounter mFpsCounter;
    private final BitmapFont mBitmapFont;

    public HUD(OrthographicCamera hudCamera) {
        mBitmapFont = Assets.getInstance().fontDroidSans17;
        mHUDCamera = hudCamera;
        if (Debug.DEBUG) {
            mFpsCounter = new FpsCounter();
        }
    }

    public void render(SpriteBatch batch, Field field) {
        batch.setProjectionMatrix(mHUDCamera.combined);
        batch.begin();
        int leftTeamScore = field.getLeftPlayerScore();
        int rightTeamScore = field.getRightPlayerScore();
        mBitmapFont.draw(batch, "Score " + leftTeamScore + " : " + rightTeamScore,
                Constants.HUD_SCREEN_WIDTH / 2 - SCORE_RIGHT_MARGIN, Constants.HUD_SCREEN_HEIGHT / 2 - SCORE_TOP_MARGIN);
        if (mFpsCounter != null) {
            mFpsCounter.render(batch);
        }

        Color original = mBitmapFont.getColor();
        mBitmapFont.setColor(0.05f, 0.35f, 0.65f, 1f);
        mBitmapFont.setScale(1.2f);
        batch.end();
        mBitmapFont.setColor(original);
        mBitmapFont.setScale(1);
    }

    private static final class FpsCounter {

        private static final float MARGIN_LEFT = 30;
        private static final float MARGIN_TOP = 15;

        private final BitmapFont mBitmapFont;

        private FpsCounter() {
            mBitmapFont = Assets.getInstance().fontDroidSans17;
        }

        private void render(SpriteBatch batch) {
            if (Debug.SHOW_FPS) {
                int framesPerSecond = Gdx.graphics.getFramesPerSecond();
                Color oldColor = mBitmapFont.getColor();
                if (framesPerSecond >= 55) {
                    mBitmapFont.setColor(Color.GREEN);
                } else if (framesPerSecond < 55 && framesPerSecond >= 30 ) {
                    mBitmapFont.setColor(Color.YELLOW);
                } else if (framesPerSecond < 30) {
                    mBitmapFont.setColor(Color.RED);
                }
                mBitmapFont.draw(batch, "FPS: " + framesPerSecond, -Constants.HUD_SCREEN_WIDTH / 2 + MARGIN_LEFT, Constants.HUD_SCREEN_HEIGHT / 2 - MARGIN_TOP);
                mBitmapFont.setColor(oldColor);
            }
        }
    }
}
