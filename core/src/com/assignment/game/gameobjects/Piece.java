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

/**
 * abstract class piece - act as parent class for all other pieces
 *
 * @author
 * @version 1.0
 * @since 2019-05-18
 */
public abstract class Piece {
    protected int id;
    protected Texture texture; //texture or image of the piece
    public Sprite sprite; //sprite of the piece - easy to manipulate
    protected final int SIZE = 96; //size of the piece width * height
    protected boolean select; //check if the piece is select
    protected Vector2 position; //position of the piece
    protected int team; //decide the team for the piece
    protected ShapeRenderer shapeRenderer; //shaperenderer to draw triangle
    public ArrayList<Rectangle> rectangles; //list of possible move

    /**
     * Consructor for the piece class
     * @param team
     * @param name
     * @param position
     */
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

    /**
     * dispose all the gameobject
     */
    public abstract void dispose();

    /**
     * render the images in the game
     * @param batch
     */
    public abstract void render(Batch batch);

    /**
     * create rectangle - possible move
     * @param others
     */
    public abstract void createRectangles(ArrayList<Rectangle> others);

    /**
     * draw the rectangle
     */
    public abstract void drawRectangles();

    /**
     * get the piece sprite
     * @return
     */
    public abstract Sprite getSprite();

    /**
     * get the position of the piece
     * @return
     */
    public abstract Vector2 getPosition();

    /**
     * get the piece team
     * @return
     */
    public abstract int getTeam();

    /**
     * make the piece to be select
     */
    public abstract void selected();

    /**
     * return the if the piece is selec
     * @return
     */
    public abstract boolean getSelect();

    /**
     * deselect the piece
     */
    public abstract void deselect();

    /**
     * move the piece to the mouse position
     * @param mouseX
     * @param mouseY
     */
    public abstract void move(int mouseX, int mouseY);

    /**
     * end the turn
     * @return
     */
    public abstract int endTurn();

    /**
     * get the piece id
     * @return
     */
    public abstract int getId();


}
