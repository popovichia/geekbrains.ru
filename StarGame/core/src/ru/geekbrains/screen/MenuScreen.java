package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Vector2 posCurrent;
    private Vector2 posEnd;
    private Vector2 posDelta;
    private Vector2 v;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        v = new Vector2(1.0f, 1.0f);
        posCurrent = new Vector2();
        posEnd = new Vector2();
        posDelta = new Vector2();
    }

    @Override
    public void render(float delta) {
       update(delta);
       draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        posEnd.set(screenX, Gdx.graphics.getHeight() - screenY);
        posDelta = posEnd.cpy().sub(posCurrent);
        v.set(posDelta.x/posDelta.len(), posDelta.y/posDelta.len());
        return false;
    }

    private void update(float delta) {
        if (posEnd.cpy().sub(posCurrent).len() >= 0.5) {
            posCurrent.add(v);
        } else {
            posCurrent.x = posEnd.x;
            posCurrent.y = posEnd.y;
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0.5f, 0.7f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, posCurrent.x, posCurrent.y);
        batch.end();
    }

}
