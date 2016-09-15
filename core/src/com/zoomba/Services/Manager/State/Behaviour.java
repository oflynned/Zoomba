package com.zoomba.Services.Manager.State;

import com.badlogic.gdx.Gdx;
import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;
import com.zoomba.Services.Constants;
import com.zoomba.Services.Manager.Types.Direction;
import com.zoomba.UI.Modes.HighScore;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ed on 22/08/2016.
 */
public class Behaviour {
    public static void generateStateTransition(Direction direction, Circle circle) {
        /**
         * 75% chance of a reflection on colliding with a wall
         * 25% chance of a teleportation through the wall
         *
         * Currently defaulting to collide wall on collision
         */
        if (new Random().nextInt(10) < 10) collideWall(direction, circle);
        else passThroughWall(direction, circle);
    }

    public static void passThroughWall(Direction direction, Circle circle) {
        if (direction.equals(Direction.Left)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "LEFT PASS THROUGH");
            circle.setX(HighScore.width - 2 * circle.getRadius() - circle.getVelocity());
            circle.setY(HighScore.height - circle.getY() - circle.getVelocity());
        } else if (direction.equals(Direction.Top)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "TOP PASS THROUGH");
            circle.setX(HighScore.width - circle.getX());
            circle.setY(circle.getRadius() + circle.getVelocity());
        } else if (direction.equals(Direction.Right)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "RIGHT PASS THROUGH");
            circle.setX(2 * circle.getRadius() + circle.getVelocity());
            circle.setY(HighScore.height - circle.getY() - circle.getVelocity());
        } else if (direction.equals(Direction.Bottom)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "BOTTOM PASS THROUGH");
            circle.setX(HighScore.width - circle.getX() - circle.getVelocity());
            circle.setY(HighScore.height - circle.getRadius() - circle.getVelocity());
        }
    }

    public static void collideWall(Direction direction, Circle circle) {
        if (direction.equals(Direction.Left)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "LEFT COLLISION");
            circle.setX(circle.getX() + circle.getVelocity());
            circle.setOrientation(getVerticalBounce(circle.getOrientation()));
        } else if (direction.equals(Direction.Right)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "RIGHT COLLISION");
            circle.setX(circle.getX() - circle.getVelocity());
            circle.setOrientation(getVerticalBounce(circle.getOrientation()));
        } else if (direction.equals(Direction.Top)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "TOP COLLISION");
            circle.setY(circle.getY() - circle.getVelocity());
            circle.setOrientation(getHorizontalBounce(circle.getOrientation()));
        } else if (direction.equals(Direction.Bottom)) {
            Gdx.app.log(Constants.PHYSICS_DEBUG, "BOTTOM COLLISION");
            circle.setY(circle.getY() + circle.getVelocity());
            circle.setOrientation(getHorizontalBounce(circle.getOrientation()));
        }
        Gdx.app.log(Constants.PHYSICS_ATTRIBUTE_DEBUG, circle.getOrientation() + " degrees");
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
