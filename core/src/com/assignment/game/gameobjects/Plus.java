package com.assignment.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Plus extends Piece {

    public Plus(int team, String name, Vector2 position) {
        super(team, name, position);

    }


    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch);
        sprite.setPosition(position.x * SIZE, position.y * SIZE);

    }

    public void drawRectangles() {
        if (select) {
            rectangles.add(new Rectangle(new Rectangle((int) position.x, (int) position.y + 1, SIZE, SIZE)));
            rectangles.add(new Rectangle(new Rectangle((int) position.x, (int) position.y + 2, SIZE, SIZE)));
            rectangles.add(new Rectangle(new Rectangle((int) position.x, (int) position.y - 1, SIZE, SIZE)));
            rectangles.add(new Rectangle(new Rectangle((int) position.x, (int) position.y - 2, SIZE, SIZE)));
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.RED);
            for (Rectangle r : rectangles) {
                shapeRenderer.rect(r.x * SIZE, r.y * SIZE, SIZE, SIZE);
            }
            shapeRenderer.end();
        }
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    public int getTeam() {
        return team;
    }

    public void selected() {
        select = true;
    }

    public boolean getSelect() {
        return select;
    }

    public void deselect() {
        select = false;
    }

    public void move(int mouseX, int mouseY) {

        System.out.println(mouseX + ":" + mouseY);


    }
}

