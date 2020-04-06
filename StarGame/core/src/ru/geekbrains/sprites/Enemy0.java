package ru.geekbrains.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;

public class Enemy0 extends Sprite {
    private Vector2 v0;
    private Vector2 v;
    private Rect worldBounds;
    private static final float SHIP_HEIGHT = 0.15f;

    public Enemy0(TextureAtlas textureAtlas) throws GameException {
        super(textureAtlas.findRegion("enemy0"), 1, 2, 2);
        v = new Vector2(0f, -0.003f);
    }
    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(SHIP_HEIGHT);
        setStartPos();
    }
    @Override
    public void update(float delta) {
        if (this.getTop() >= worldBounds.getBottom()) {
            this.pos.add(v);
        } else {
            setStartPos();
        }
    }
    private void setStartPos() {
        this.setBottom(worldBounds.getTop());
        this.setLeft(Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight() - this.getWidth()));
    }
}
