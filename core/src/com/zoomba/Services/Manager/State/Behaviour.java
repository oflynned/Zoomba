package com.zoomba.Services.Manager.State;

import com.badlogic.gdx.Gdx;
import com.zoomba.GameObjects.ObjectFactory.Circle;
import com.zoomba.Services.Constants;
import com.zoomba.UI.Screens.GameScreen;

import java.util.Random;

/**
 * Created by ed on 22/08/2016.
 */
public class Behaviour {
    public static void generateStateTransition(Direction direction, Circle circle) {
        collideWall(direction, circle);

        /*switch(new Random().nextInt(1)) {
            case 0:
                collideWall(direction, circle);
            case 1:
                passThroughWall(direction, circle);
        }*/
    }

    public static void passThroughWall(Direction direction, Circle circle) {
        if(direction.equals(Direction.Left)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "LEFT PASS THROUGH");
            circle.setX(GameScreen.width - 2*circle.getRadius() - 10);
            circle.setY(GameScreen.height - circle.getY());
        } else if (direction.equals(Direction.Top)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "TOP PASS THROUGH");
            circle.setX(GameScreen.width - circle.getX());
            circle.setY(circle.getRadius() + 10);
        } else if(direction.equals(Direction.Right)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "RIGHT PASS THROUGH");
            circle.setX(10);
            circle.setY(GameScreen.height - circle.getY());
        } else if(direction.equals(Direction.Bottom)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "BOTTOM PASS THROUGH");
            circle.setX(GameScreen.width - circle.getX());
            circle.setY(GameScreen.height - circle.getRadius() - 10);
        }
    }

    public static void collideWall(Direction direction, Circle circle) {
        if(direction.equals(Direction.Left)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "LEFT COLLISION");
            circle.setX(circle.getX() + 10);
            circle.setOrientation(getVerticalBounce(circle.getOrientation()));
        } else if(direction.equals(Direction.Right)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "RIGHT COLLISION");
            circle.setX(circle.getX() - 10);
            circle.setOrientation(getVerticalBounce(circle.getOrientation()));
        } else if (direction.equals(Direction.Top)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "TOP COLLISION");
            circle.setY(circle.getY() - 10);
            circle.setOrientation(getHorizontalBounce(circle.getOrientation()));
        } else if (direction.equals(Direction.Bottom)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "BOTTOM COLLISION");
            circle.setY(circle.getY() + 10);
            circle.setOrientation(getHorizontalBounce(circle.getOrientation()));
        }
        Gdx.app.log(Constants.PHYSICS_DEBUG, circle.getOrientation() + " degrees");
    }

    public static float getVerticalBounce(float angle) {
        return 180 - angle;
    }

    public static float getHorizontalBounce(float angle) {
        return 360 - angle;
    }

    public static void circleCollision(Circle circle1, Circle circle2) {
        double dx = circle1.getX() - circle1.getX();
        double dy = circle1.getY() - circle2.getY();
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        if(distance < circle1.getRadius() + circle2.getRadius()) {
            //is collision

        }
    }
}
