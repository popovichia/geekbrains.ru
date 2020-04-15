package ru.geekbrains.sprites;

<<<<<<< HEAD
=======
import com.badlogic.gdx.Game;
>>>>>>> stargame_lesson7
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.ScaledButton;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;

public class ButtonNewGame extends ScaledButton {
<<<<<<< HEAD

    private static final float MAX_SCALE = 1.05f;
    private static final float MIN_SCALE = 1f;
    private static final float ANIMATE_INTERVAL = 0.05f;

    private GameScreen gameScreen;
    private boolean isGrow;

    private float animateTimer;

    public ButtonNewGame(TextureAtlas atlas, GameScreen gameScreen) throws GameException {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
        this.isGrow = true;
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer < ANIMATE_INTERVAL) {
            return;
        }
        animateTimer = 0f;
        if (isGrow) {
            scale += delta;
            if (scale >= MAX_SCALE) {
                scale = MAX_SCALE;
                isGrow = false;
            }
        } else {
            scale -= delta;
            if (scale <= MIN_SCALE) {
                scale = MIN_SCALE;
                isGrow = true;
            }
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.05f);
        setBottom(-0.05f);
    }

    @Override
    public void action() {
        gameScreen.startNewGame();
=======
    private Game game;
    public ButtonNewGame(TextureAtlas textureAtlas, Game game) throws GameException {
        super(textureAtlas.findRegion("button_new_game"));
        this.game = game;
    }
    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.05f);
        setBottom(-0.2f);
    }
    @Override
    public void action() {
        game.setScreen(new GameScreen(this.game));
>>>>>>> stargame_lesson7
    }
}
