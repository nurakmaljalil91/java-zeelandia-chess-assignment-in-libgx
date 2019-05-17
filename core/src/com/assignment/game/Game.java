package com.assignment.game;

import com.assignment.game.gameobjects.Plus;
import com.assignment.game.gameobjects.Piece;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


public class Game extends ApplicationAdapter implements Screen, InputProcessor {
    SpriteBatch batch;
    Texture img;
    Sprite sprite;
    ShapeRenderer shapeRenderer;
    private int TILESIZE = 96;
    private int WIDTH = 672;
    private int HEIGHT = 576;
    private int turn = 1;

    public ArrayList<Piece> pieces;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(this);
        pieces = new ArrayList<Piece>();
        pieces.add(new Plus(2, "bluePlus.png", new Vector2(0, 5)));
        pieces.add(new Plus(2, "bluePlus.png", new Vector2(6, 5)));
        pieces.add(new Plus(1, "redPlus.png", new Vector2(0, 0)));
        pieces.add(new Plus(1, "redPlus.png", new Vector2(6, 0)));
        for (Piece p : pieces) {
            System.out.println(p.getPosition());
        }

    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawGrid();

        batch.begin();

        for (Piece p : pieces) {
            p.render(batch);
        }

        batch.end();

        for (Piece p : pieces) {
            p.drawRectangles();
        }

    }


    @Override
    public void dispose() {

        batch.dispose();
        for (Piece p : pieces) {
            p.dispose();
        }

        shapeRenderer.dispose();
    }

    public void drawGrid() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLUE);
        for (int x = 0; x < WIDTH; x += TILESIZE) {
            shapeRenderer.line(x, 0, x, HEIGHT);
        }
        for (int y = 0; y < HEIGHT; y += TILESIZE) {
            shapeRenderer.line(0, y, WIDTH, y);
        }
        shapeRenderer.end();
    }

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
        if (button == Input.Buttons.LEFT) {
            float pointerX = Gdx.input.getX() / TILESIZE;
            float pointerY = (Gdx.graphics.getHeight() - Gdx.input.getY()) / TILESIZE;
            System.out.println(pointerX + "," + pointerY);
            for (Piece p : pieces) {

                if (p.getTeam() == turn) {
                    if (!p.getSelect()) {
                        if (p.getPosition().x == pointerX && p.getPosition().y == pointerY) {
                            for (Piece op : pieces) {
                                op.deselect();
                            }
                            p.selected();
                            if(p.getSelect()){
                                p.move((int) pointerX, (int) pointerY);
                            }

                            System.out.println("select");
                        }
                    } else {
                        if (p.getPosition().x == pointerX && p.getPosition().y == pointerY) {
                            p.deselect();
                            System.out.println("deselect");
                        }
                    }
                }


            }
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void hide() {

    }


}

class InputTransform {
    private static int appWidth = 480;
    private static int appHeight = 320;

    public static float getCursorToModelX(int screenX, int cursorX) {
        return (((float) cursorX) * appWidth) / ((float) screenX);
    }

    public static float getCursorToModelY(int screenY, int cursorY) {
        return ((float) (screenY - cursorY)) * appHeight / ((float) screenY);
    }
}
