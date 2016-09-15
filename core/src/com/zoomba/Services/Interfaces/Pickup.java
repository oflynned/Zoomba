package com.zoomba.Services.Interfaces;

import com.zoomba.GameObjects.ObjectFactory.Objects.GameObject;

import java.util.ArrayList;

/**
 * Created by ed on 29/08/2016.
 */
public interface Pickup {
    void onPickup(ArrayList<GameObject> gameObjects);
    void onDestroy(ArrayList<GameObject> gameObjects);
}
