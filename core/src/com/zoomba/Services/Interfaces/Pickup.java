package com.zoomba.Services.Interfaces;

import com.zoomba.GameObjects.ObjectFactory.Objects.Circle;

import java.util.ArrayList;

/**
 * Created by ed on 29/08/2016.
 */
public interface Pickup {
    void onUpdate();
    void onPickup(ArrayList<Circle> circles);
    void onDestroy(ArrayList<Circle> circles);
}
