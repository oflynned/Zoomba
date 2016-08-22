package com.zoomba.Services.Manager.State;

import com.badlogic.gdx.Gdx;
import com.zoomba.GameObjects.ObjectFactory.Circle;

import java.util.Random;

/**
 * Created by ed on 22/08/2016.
 */
public class Behaviour {
    private static float width = Gdx.graphics.getWidth();
    private static float height = Gdx.graphics.getHeight();

    public static PhysicsState generateStateTransition() {
        switch(new Random().nextInt(2)) {
            case 0:
                return PhysicsState.WallCollision;
            case 1:
                return PhysicsState.Teleport;
            default:
                return null;
        }
    }

    public static void passThroughWall(Circle circle) {
        if(circle.getY() - circle.getRadius() <= 0) {
            circle.setY(height - circle.getRadius());
        } else if (circle.getY() + circle.getRadius() >= height) {
            circle.setY(circle.getRadius());
        } else if(circle.getX() - circle.getRadius() <= 0) {
            circle.setX(width - circle.getRadius());
        } else if(circle.getX() + circle.getRadius() >= width) {
            circle.setX(circle.getRadius());
        }
    }

    public static void collideWall(Circle circle) {
        circle.setOrientation(circle.getOrientation() - 360);

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
