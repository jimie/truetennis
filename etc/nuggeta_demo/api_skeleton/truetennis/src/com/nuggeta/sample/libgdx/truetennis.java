
package com.nuggeta.sample.libgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nuggeta.NuggetaPlug;
import com.nuggeta.ngdl.StartResponseHandler;
import com.nuggeta.ngdl.nobjects.StartResponse;
import com.nuggeta.ngdl.nobjects.StartStatus;

public class truetennis implements ApplicationListener {

    protected Stage m_Stage;

    protected Skin m_Skin;

    protected Table m_Table;

    private Texture tOn;

    private Texture tOff;

    private TextureRegion textureRegion;

    private NuggetaPlug nuggetaPlug;

    private Label label;

    @Override
    public void create() {

        // init graphics
        initGraphics();

        // create NuggetaPlug
        nuggetaPlug = new NuggetaPlug("nuggeta://true_pong_e1a7c11d-bbeb-401d-a7cd-0dd5c50a07b5");

        // listen to StartResponse
        nuggetaPlug.addStartResponseHandler(new StartResponseHandler() {

            @Override
            public void onStartResponse(StartResponse response) {

                if (StartStatus.READY == response.getStartStatus()) {
                    textureRegion.setTexture(tOn);
                    label.setText("Connection Ready with Nuggeta ;)");

                } else if (StartStatus.FAILED == response.getStartStatus()) {
                    label.setText("Connection Failed to Nuggeta ;(");
                }
            }
        });

        // start the plug
        nuggetaPlug.start();

    }

    private void initGraphics() {

        // load skin
        m_Skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        // create the stage
        m_Stage = new Stage();

        // create the table
        m_Table = createTable(true);
        m_Stage.addActor(m_Table);

        // load texture
        Texture.setEnforcePotImages(false);
        tOff = new Texture(Gdx.files.internal("ui/nuggeta_off.png"));
        tOff.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        tOn = new Texture(Gdx.files.internal("ui/nuggeta_on.png"));
        tOn.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        textureRegion = new TextureRegion(tOff, 0, 0, tOff.getWidth(), tOff.getHeight());

        // add image
        Image image = new Image(textureRegion);
        float scale = Gdx.graphics.getWidth() / image.getWidth();
        image.setSize(scale * image.getWidth(), scale * image.getHeight());

        m_Table.add(image).width(scale * image.getWidth()).height(scale * image.getHeight()).spaceBottom(10);
        m_Table.row();

        // add label
        label = createLabel("Connecting to Nuggeta...", Align.center);
        m_Table.add(label).width(100).height(100).spaceBottom(10);
        m_Table.row();
    }
    
    public Label createLabel(String str, int align) {
        Label label = new Label(str, m_Skin);
        label.setAlignment(align);
        return label;
    }

    public Table createTable(boolean fillParent) {
        Table t = new Table();
        t.setFillParent(fillParent);
        t.center();
        return t;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        m_Stage.act(Gdx.graphics.getDeltaTime());
        m_Stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}
