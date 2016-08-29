package com.zoomba.Services.Manager.Gesture;

import com.zoomba.UI.Modes.HighScore;

/**
 * Created by colinpuri on 29/08/2016.
 */
public class Helper {
    public static int getAspectWidth(int height)  {
        return (int) (height / HighScore.aspectRatio);
    }
}
