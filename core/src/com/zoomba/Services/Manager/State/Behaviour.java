package com.zoomba.Services.Manager.State;

import com.badlogic.gdx.Gdx;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.GameObjects.ObjectFactory.Objects.GameObject;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Manager.Types.Direction;
import com.zoomba.UI.Modes.HighScore;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ed on 22/08/2016.
 */
public class Behaviour {
    public static void generateStateTransition(Direction direction, GameObject gameObject) {
        /**
         * 75% chance of a reflection on colliding with a wall
         * 25% chance of a teleportation through the wall
         *
         * Currently defaulting to collide wall on collision
         */
        if (new Random().nextInt(10) < 10) collideWall(direction, gameObject);
        else passThroughWall(direction, gameObject);
    }

    public static void passThroughWall(Direction direction, GameObject gameObject) {
        if (direction.equals(Direction.Left)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "LEFT PASS THROUGH");
            gameObject.setX(HighScore.width - 2 * gameObject.getRadius() - gameObject.getVelocity());
            gameObject.setY(HighScore.height - gameObject.getY() - gameObject.getVelocity());
        } else if (direction.equals(Direction.Top)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "TOP PASS THROUGH");
            gameObject.setX(HighScore.width - gameObject.getX());
            gameObject.setY(gameObject.getRadius() + gameObject.getVelocity());
        } else if (direction.equals(Direction.Right)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "RIGHT PASS THROUGH");
            gameObject.setX(2 * gameObject.getRadius() + gameObject.getVelocity());
            gameObject.setY(HighScore.height - gameObject.getY() - gameObject.getVelocity());
        } else if (direction.equals(Direction.Bottom)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "BOTTOM PASS THROUGH");
            gameObject.setX(HighScore.width - gameObject.getX() - gameObject.getVelocity());
            gameObject.setY(HighScore.height - gameObject.getRadius() - gameObject.getVelocity());
        }
    }

    public static void collideWall(Direction direction, GameObject gameObject) {
        if (direction.equals(Direction.Left)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "LEFT COLLISION");
            gameObject.setX(gameObject.getX() + gameObject.getVelocity());
            gameObject.setOrientation(getVerticalBounce(gameObject.getOrientation()));
        } else if (direction.equals(Direction.Right)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "RIGHT COLLISION");
            gameObject.setX(gameObject.getX() - gameObject.getVelocity());
            gameObject.setOrientation(getVerticalBounce(gameObject.getOrientation()));
        } else if (direction.equals(Direction.Top)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "TOP COLLISION");
            gameObject.setY(gameObject.getY() - gameObject.getVelocity());
            gameObject.setOrientation(getHorizontalBounce(gameObject.getOrientation()));
        } else if (direction.equals(Direction.Bottom)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "BOTTOM COLLISION");
            gameObject.setY(gameObject.getY() + gameObject.getVelocity());
            gameObject.setOrientation(getHorizontalBounce(gameObject.getOrientation()));
        }
        Gdx.app.log(Constants.PHYSICS_ATTRIBUTE_DEBUG, gameObject.getOrientation() + " degrees");
    }

    public static float getVerticalBounce(float angle) {
        return 180 - angle;
    }

    public static float getHorizontalBounce(float angle) {
        return 360 - angle;
    }

    public static void circleCollision(ArrayList<Circle> circles) {
        for (int i = 0; i < circles.size(); i++) {
            for (int j = 0; j < circles.size(); j++) {
                if (!circles.get(i).equals(circles.get(j))) {
                    double dx = (circles.get(i).getX() + circles.get(i).getRadius()) -
                            (circles.get(j).getX() + circles.get(j).getRadius());
                    double dy = circles.get(i).getY() - circles.get(j).getY();
                    double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
                    if (distance < circles.get(i).getRadius() + circles.get(j).getRadius()) {
                        determineCollision(circles.get(i), circles.get(j));
                    }
                }
            }
        }
    }

    public static void determineCollision(Circle circle1, Circle circle2) {
        if (circle1.getX() < circle2.getX()) {
            //1o2o
            if (circle1.getY() < circle2.getY()) {
                //2o
                //1o
                circle1.setOrientation(getHorizontalBounce(circle1.getOrientation()));
                circle2.setOrientation(getHorizontalBounce(circle2.getOrientation()));
            } else {
                //1o
                //2o
                circle1.setOrientation(getHorizontalBounce(circle1.getOrientation()));
                circle2.setOrientation(getHorizontalBounce(circle2.getOrientation()));
            }
        } else {
            //2o1o
            if (circle1.getY() < circle2.getY()) {
                //2o
                //1o
                circle1.setOrientation(getVerticalBounce(circle1.getOrientation()));
                circle2.setOrientation(getVerticalBounce(circle2.getOrientation()));
            } else {
                //1o
                //2o
                circle1.setOrientation(getVerticalBounce(circle1.getOrientation()));
                circle2.setOrientation(getVerticalBounce(circle2.getOrientation()));
            }
        }
    }
}
