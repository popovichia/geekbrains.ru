package ru.geekbrains.sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.ScaledButton;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;

public class ButtonNewGame extends ScaledButton {
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
    }
}
