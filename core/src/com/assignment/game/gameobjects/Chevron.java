package com.assignment.game.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.util.ArrayList;


public class Chevron extends Piece {
    public Chevron(int team, String name, Vector2 position) {
        super(team, name, position);
        id = 2;

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

    public void createRectangles(ArrayList<Rectangle> others) {
        // check left


    }

    public void drawRectangles() {
        if (select) {

            //check left

            rectangles.add(new Rectangle(new Rectangle((int) position.x - 1, (int) position.y + 2, SIZE, SIZE)));
            rectangles.add(new Rectangle(new Rectangle((int) position.x + 1, (int) position.y + 2, SIZE, SIZE)));
            rectangles.add(new Rectangle(new Rectangle((int) position.x - 1, (int) position.y - 2, SIZE, SIZE)));
            rectangles.add(new Rectangle(new Rectangle((int) position.x + 1, (int) position.y - 2, SIZE, SIZE)));

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
        System.out.println("deselect");
    }

    public void move(int mouseX, int mouseY) {

        for (Rectangle r : rectangles) {
            if (r.x == mouseX && r.y == mouseY) {
                position.x = r.x;
                position.y = r.y;
                select = false;
            }
        }
        rectangles.clear();


    }

    public int endTurn() {
        if (team == 1) {
            return 2;
        } else {
            return 1;
        }

    }

    @Override
    public int getId() {
        return id;
    }
}
