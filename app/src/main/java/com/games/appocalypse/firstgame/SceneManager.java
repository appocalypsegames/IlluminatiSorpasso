package com.games.appocalypse.firstgame;

import android.content.Context;
import android.graphics.Canvas;
import android.icu.util.Measure;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by eduar on 05/02/2018.
 */

public class SceneManager {
    private ArrayList<Scene> scene=new ArrayList<>();
    public static int ACTIVE_SCENE;
    public SceneManager(Context context, int character, MediaPlayer mp){
        ACTIVE_SCENE=0;
        scene.add(new GameplayScene(context,character,mp));
    }
    public void receiveTouch(MotionEvent event){
        scene.get(ACTIVE_SCENE).receiveTouch(event);
    }
    public void update(){
        scene.get(ACTIVE_SCENE).update();
    }
    public void draw(Canvas canvas){
        scene.get(ACTIVE_SCENE).draw(canvas);
    }
}
