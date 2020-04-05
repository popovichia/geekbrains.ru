package ru.geekbrains.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;

public class Ship extends Sprite {
    private float ship_sensitivity = 0f;
    private float leftborder = 0;
    private float rightborder = 0;

    public Ship(TextureAtlas textureAtlas) throws GameException {
        super(new TextureRegion(textureAtlas.findRegion("main_ship"),
                0, 0,
                textureAtlas.findRegion("main_ship").getRegionWidth()/2,
                textureAtlas.findRegion("main_ship").getRegionHeight()));
    }
    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
        setBottom(worldBounds.getBottom() + 0.01f);
        leftborder = worldBounds.getLeft();
        rightborder = worldBounds.getRight();
    }
    @Override
    public void update(float delta) {
        if ((this.getLeft() > leftborder && this.ship_sensitivity < 0)
                || (this.getRight() < rightborder && this.ship_sensitivity > 0)) {
            this.setLeft(this.getLeft() + this.ship_sensitivity);
        }
    }

    public boolean keyDown (int keycode) {
        if (keycode == 21) {
            this.ship_sensitivity = -0.007f;
        }
        if (keycode == 22) {
            this.ship_sensitivity = 0.007f;
        }
        return false;
    }
    public boolean keyUp (int keycode) {
        if (keycode == 21 && this.ship_sensitivity < 0) {
            this.ship_sensitivity = 0f;
        }
        if (keycode == 22 && this.ship_sensitivity > 0) {
            this.ship_sensitivity = 0f;
        }
        return false;
    }
}
