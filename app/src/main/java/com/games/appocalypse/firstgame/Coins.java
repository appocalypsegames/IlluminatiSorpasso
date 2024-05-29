package com.games.appocalypse.firstgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;



public class Coins implements GameObject{
    private Rect rectangle;
    private int color;
    private Animation idle;
    private AnimationManager animationManager;
    public Rect getRectangle(){
        return rectangle;
    }
    public void incrementY(float y){
        rectangle.top+=y;
        rectangle.bottom+=y;

    }
    public Coins(int color, int startX,int startY){
        rectangle=new Rect(startX,startY+200,startX+45,startY+250);
        BitmapFactory bf=new BitmapFactory();
        Bitmap coin0,coin1,coin2,coin3,coin4;
        coin0 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.coin0);
        coin1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.coin1);
        coin2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.coin2);
        coin3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.coin3);
        coin4 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.coin4);
        idle=new Animation(new Bitmap[]{coin0,coin1,coin2,coin3,coin4,coin3,coin2,coin1},0.6f);
        animationManager=new AnimationManager(new Animation[]{idle});
        this.color=color;
    }
    public boolean playerCollide(RectPlayer player){
        if(Rect.intersects(rectangle, player.getRectangle()))return true;
        else return false;
    }
    @Override
    public void update() {
        animationManager.update();
    }
    @Override
    public void draw(Canvas canvas){
        //Paint paint=new Paint();
        //paint.setColor(color);
        //canvas.drawRect(rectangle,paint);
        animationManager.draw(canvas,rectangle);
        animationManager.playAnim(0);
        animationManager.update();
    }

}
