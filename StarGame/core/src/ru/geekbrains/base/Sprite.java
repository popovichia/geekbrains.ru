package ru.geekbrains.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;
import sun.awt.X11.XSystemTrayPeer;

public class Sprite extends Rect {

    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame;
    protected Vector2 currentPos;
    protected Vector2 endPos;
    protected Vector2 v;

    public Sprite(TextureRegion region) throws GameException {
        if (region == null) {
            throw new GameException("Region is null");
        }
        regions = new TextureRegion[1];
        regions[0] = region;
        currentPos = new Vector2(getLeft(), getBottom());
        endPos = new Vector2(getLeft(), getBottom());
        v = new Vector2();
    }

    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }

    public void update(float delta) {
        if (endPos.cpy().sub(currentPos).len() > v.len()) {
            currentPos.add(v);
            System.out.println("currentPos: " + currentPos.x + "; " + currentPos.y + "; " + currentPos.len());
            System.out.println("endPos: " + endPos.x + "; " + endPos.y + "; " + endPos.len());
            System.out.println("v: " + v.x + "; " + v.y + "; " + v.len());
            System.out.println("endPos.cpy().sub(currentPos).len(): " + endPos.cpy().sub(currentPos).len());
            System.out.println();
            this.setLeft(currentPos.x);
            this.setBottom(currentPos.y);
        } else {
            this.setLeft(endPos.x);
            this.setBottom(endPos.y);
            currentPos.set(endPos);
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );
    }

    public void resize(Rect worldBounds) {

    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        System.out.println("TOUCH--------------------------------------");
        endPos.set(touch);
        v = touch.sub(currentPos).scl(0.01f);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        return false;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
