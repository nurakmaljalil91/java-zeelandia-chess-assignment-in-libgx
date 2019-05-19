package com.assignment.game;

import com.assignment.game.gameobjects.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * The game class for the game
 * @author
 * @version 1.0
 * @since 2019-5-19
 */
public class Game extends ApplicationAdapter implements Screen, InputProcessor {
    SpriteBatch batch;
    Texture img;
    Sprite sprite;
    ShapeRenderer shapeRenderer;
    private int TILESIZE = 96; //tilesize for the piece
    private int WIDTH = 672; //board width
    private int HEIGHT = 576; //board height
    private int turn = 1; //check the turn for piece
    private BitmapFont font; //for render text
    private int turnToSwap = 1;
    public ArrayList<Piece> pieces; //pieces manager

    /**
     * act as constructor of the game
     * Initialize all the game
     */
    public void create() {
        batch = new SpriteBatch(); //init sprite batch
        shapeRenderer = new ShapeRenderer(); //init shaperenderer
        Gdx.input.setInputProcessor(this); //for mouse click
        font = new BitmapFont();
        pieces = new ArrayList<Piece>(); //list for pieces collection
        //Init all the pieces
        pieces.add(new Plus(2, "bluePlus.png", new Vector2(0, 5)));
        pieces.add(new Plus(2, "bluePlus.png", new Vector2(6, 5)));
        pieces.add(new Plus(1, "redPlus.png", new Vector2(0, 0)));
        pieces.add(new Plus(1, "redPlus.png", new Vector2(6, 0)));
        pieces.add(new Triangle(2, "blueTriangle.png", new Vector2(1, 5)));
        pieces.add(new Triangle(2, "blueTriangle.png", new Vector2(5, 5)));
        pieces.add(new Triangle(1, "redTriangle.png", new Vector2(1, 0)));
        pieces.add(new Triangle(1, "redTriangle.png", new Vector2(5, 0)));
        pieces.add(new Chevron(2, "blueChevron.png", new Vector2(2, 5)));
        pieces.add(new Chevron(2, "blueChevron.png", new Vector2(4, 5)));
        pieces.add(new Chevron(1, "redChevron.png", new Vector2(2, 0)));
        pieces.add(new Chevron(1, "redChevron.png", new Vector2(4, 0)));
        pieces.add(new Sun(2, "blueSun.png", new Vector2(3, 5)));
        pieces.add(new Sun(1, "redSun.png", new Vector2(3, 0)));
    }

    /**
     * render all the necessary gameobjects inside the game
     */
    @Override
    public void render() {
        //clear all the color to white
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawGrid(); //draw the grids

        batch.begin(); //start the batch

        //render all the pieces in pieces manager
        for (Piece p : pieces) {
            p.render(batch);
        }
        updatePiece(batch); //update the pieces
        batch.end(); //end the batch

        //create and render the rectangle
        for (Piece p : pieces) {
            p.drawRectangles();
        }
        swap();
        //System.out.println("turn: "+ turn);
    }

    /**
     * dispose or clear all gameobjects inside the game
     */
    @Override
    public void dispose() {

        batch.dispose(); //dispose all the batch
        //dispose all the pieces
        for (Piece p : pieces) {
            p.dispose();
        }
        //dispose all the shape renderer
        shapeRenderer.dispose();
    }

    /**
     * draw of the grids
     */
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

    /**
     * key down
     * @param keycode
     * @return
     */
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

    /**
     * mouse click the function - use this for click once
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //check if mouse is click
        if (button == Input.Buttons.LEFT) {
            //get the mouse coordinates
            float pointerX = Gdx.input.getX() / TILESIZE;
            float pointerY = (Gdx.graphics.getHeight() - Gdx.input.getY()) / TILESIZE;
            System.out.println(pointerX + "," + pointerY);
            //check for piece in pieces
            for (Piece p : pieces) {
                //check if piece team and turn is equal
                if (p.getTeam() == turn) {
                    //if piece is not selected
                    if (!p.getSelect()) {
                        //if the pieces position equal coordinates position
                        if (p.getPosition().x == pointerX && p.getPosition().y == pointerY) {
                            //check the other piece team
                            for (Piece op : pieces) {
                                //if piece team equal turn
                                if (op.getTeam() == turn) {
                                    //deselect the piece
                                    op.deselect();
                                }
                            }
                            //select the piece
                            p.selected();
                            System.out.println("select");
                        }
                    }

                }


            }
            //check piece in pieces
            for (Piece p : pieces) {
                //if piece select is true
                if (p.getSelect()) {
                    //move the piece to the mouse click
                    p.move((int) pointerX, (int) pointerY);
                    //end the turn when finish move
                    turn = p.endTurn();
                    turnToSwap++;
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

    /**
     * update the piece - check if the piece
     * position is same as other team piece position
     * hence, delete the other piece
     *,this is basically a eat piece method
     */
    public void updatePiece(Batch batch){
        //loop all in the pieces manager
        for(int i = 0; i < pieces.size(); i++){
            //if piece team equal turn
            if(pieces.get(i).getTeam() == turn){
                //loop again with different team
                for(int j = 0; j < pieces.size(); j++){
                    //if the team for the pieces is not equal turn
                    if(pieces.get(j).getTeam() != turn){
                        //if piece position is the same as other piece position
                        if(pieces.get(i).getPosition().x == pieces.get(j).getPosition().x && pieces.get(i).getPosition().y == pieces.get(j).getPosition().y){
                            System.out.println("delete"+ pieces.get(i).getPosition());

                            if(pieces.get(i).getId() == 1){
                                font.draw(batch, "Team "+turn+" win!", 100, 100);
                                System.out.println("win");
                            }
                            pieces.remove(i); //remove the piece
                        }
                    }

                }
            }
        }
    }

    public void swap(){
        if(turnToSwap == 3){
            System.out.println("Swap");
            for(Piece p : pieces){
                if(p.getTeam() == 1){
                    if(p.getId() == 4){
                        for(Piece op:pieces){
                            if(op.getTeam() == 1){
                                if(op.getId() == 3){
                                    p.move((int)op.getPosition().x,(int) op.getPosition().y);
                                    op.move((int)p.getPosition().x,(int)p.getPosition().y);
                                }
                            }
                        }
                    }
                }
            }
        }
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

