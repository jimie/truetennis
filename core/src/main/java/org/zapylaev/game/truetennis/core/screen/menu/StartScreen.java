/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Zapylaiev Kyrylo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.zapylaev.game.truetennis.core.screen.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.zapylaev.game.truetennis.core.Assets;
import org.zapylaev.game.truetennis.core.TrueTennisMain;

public class StartScreen extends MenuScreen {

    private final Image mBg;

    public StartScreen(TrueTennisMain game) {
        super(game);
        mBg = new Image(Assets.getInstance().splash);
    }

    @Override
    public void show() {
        super.show();
        Table table = new Table(mSkin);
        table.setBackground(mBg.getDrawable());
        table.setFillParent(true);

        mStage.addActor(table);

        TextButton createServerButton = new TextButton("Start", mSkin);
        TextButton joinGameBlueButton = new TextButton("Options", mSkin);

        table.add(createServerButton).size(350, 65).uniform().spaceBottom(10).padTop(150);
        table.row();
        table.add(joinGameBlueButton).uniform().fill().spaceBottom(10);
        table.row();

        createServerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mGame.createGame();
            }
        });
        joinGameBlueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mGame.showOptions();
            }

        });
    }

}
