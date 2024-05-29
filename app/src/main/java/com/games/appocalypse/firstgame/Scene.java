package com.games.appocalypse.firstgame;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by eduar on 05/02/2018.
 */

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent event);
}
