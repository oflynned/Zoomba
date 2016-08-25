package com.zoomba.GameObjects.UI;

/**
 * Created by ed on 09/08/16.
 */
public abstract class UIObject {
    public abstract void onDraw();
    public abstract void onUpdate(int time, int score);
    public abstract void onDispose();
}
