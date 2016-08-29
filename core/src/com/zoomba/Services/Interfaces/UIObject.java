package com.zoomba.Services.Interfaces;

/**
 * Created by ed on 29/08/2016.
 */
public interface UIObject {
    void onDraw();
    void onUpdate(int width, int height, int instances);
    void onDispose();
}
