package com.zoomba.Services.Manager.State;

/**
 * Created by ed on 09/08/16.
 */
public class Manager {
    private static Manager manager = null;
    public static Manager getInstance() {
        if (manager == null)
            manager = new Manager();
        return manager;
    }
}
