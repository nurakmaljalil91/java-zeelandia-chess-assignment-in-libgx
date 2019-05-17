package com.assignment.game.gameobjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.InputProcessor;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Piece {
    protected Texture texture;
    public Sprite sprite;
    protected final int SIZE = 96;
    protected boolean select;
    protected Vector2 position;
    protected int team;
    protected ShapeRenderer shapeRenderer;
    public ArrayList<Rectangle> rectangles;


    public Piece(int team, String name, Vector2 position) {
        texture = new Texture(name);
        sprite = new Sprite(texture);
        this.position = position;
        this.team = team;
        sprite.setPosition(position.x * SIZE, position.y * SIZE);
        sprite.setSize(SIZE, SIZE);
        shapeRenderer = new ShapeRenderer();
        rectangles = new ArrayList<Rectangle>();
    }


    public abstract void dispose();

    public abstract void render(Batch batch);

    public abstract void drawRectangles();

    public abstract Sprite getSprite();

    public abstract Vector2 getPosition();

    public abstract int getTeam();

    public abstract void selected();

    public abstract boolean getSelect();

    public abstract void deselect();

    public abstract void move(int mouseX, int mouseY);


}
