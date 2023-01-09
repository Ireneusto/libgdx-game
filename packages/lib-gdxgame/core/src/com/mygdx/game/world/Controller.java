package com.mygdx.game.world;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class Controller implements InputProcessor {

    public float deltaX, deltaY;
    public boolean isTouched;
    private float x, y;



    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.x = screenX;
        this.y = screenY;
        isTouched = true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        this.x = 0;
        this.y = 0;
        isTouched = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        this.deltaX = -1*(x - screenX)/100;
        this.deltaY = (y - screenY)/100;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
