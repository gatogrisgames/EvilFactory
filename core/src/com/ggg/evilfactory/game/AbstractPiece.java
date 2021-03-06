package com.ggg.evilfactory.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.ggg.evilfactory.interfaces.Displayable;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-8-27.
 */
public abstract class AbstractPiece implements Displayable
{
    protected Sprite sprite;
    private Vector2 position;
    private Vector2 direction;
    private float speed;
    private Vector2 friction;
    private int addPoints;
    private int takePoints;
    private String name;

    public AbstractPiece()
    {

        position = new Vector2(Constants.VIEWPORT_WIDTH, 100);
        direction = new Vector2(0, 0);
        friction = new Vector2(0, 0);
        speed = 0;
        addPoints = 0;
        takePoints = 0;


    }

    public Vector2 getPosition()
    {
        return position;
    }

    public float getPositionX()
    {
        return position.x;
    }

    public float getPositionY()
    {
        return position.y;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public void setPositionX(float speed, float deltaTime)
    {
        position.x -=  speed * deltaTime;
        sprite.setX(position.x);
    }

    public Vector2 getDirection()
    {
        return direction;
    }

    public void setDirection(Vector2 direction)
    {
        this.direction = direction;
    }

    public float getSpeed()
    {
        return speed;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public Vector2 getFriction()
    {
        return friction;
    }

    public void setFriction(Vector2 friction)
    {
        this.friction = friction;
    }

    public int getAddPoints()
    {
        return addPoints;
    }

    public void setAddPoints(int addPoints)
    {
        this.addPoints = addPoints;
    }

    public int getTakePoints()
    {
        return takePoints;
    }

    public void setTakePoints(int takePoints)
    {
        this.takePoints = takePoints;
    }

    public Sprite getSprite()
    {
        return sprite;
    }

    @Override
    abstract public void display();

    public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }


    /**
     * Method returns true if the screen touch  happens inside the area of the sprite position.
     * @param game is the main Application
     * @return true when input touch happens inside the sprite area limited but its position and
     * width, height
     */
    public boolean isClicked(Application game)
    {
        if (Gdx.input.justTouched())
        {
           game.getTouchPosition().set(Gdx.input.getX(), Gdx.input.getY(), 0);
           game.getCamera().unproject(game.getTouchPosition());

            if (game.getTouchPosition().x >= getPositionX() &&
                    game.getTouchPosition().y >= getPositionY() &&
                    game.getTouchPosition().x <= (getPositionX() + getSprite().getWidth()) &&
                    game.getTouchPosition().y <= (getPositionY() + getSprite().getHeight()))
            {
                return true;

            }
        }

        return false;


    }

    public void dispose()
    {
        getSprite().getTexture().dispose();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


}
