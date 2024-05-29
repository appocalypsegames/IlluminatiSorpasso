package com.games.appocalypse.firstgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by eduar on 23/01/2018.
 */

public class Obstacle implements GameObject {
    private Rect rectangle;
    private Rect rectangle2;
    private int color;
    public Rect getRectangle(){
        return rectangle;
    }
    public void incrementY(float y){
        rectangle.top+=y;
        rectangle.bottom+=y;
        rectangle2.top+=y;
        rectangle2.bottom+=y;

    }
    public Obstacle(int rectHeight,int color, int startX,int startY, int playerGap){
        rectangle=new Rect(0,startY,startX,startY+rectHeight);
        rectangle2=new Rect(startX+playerGap,startY,Constants.SCREEN_WIDTH,startY+rectHeight);
        this.color=color;
    }
    public boolean playerCollide(RectPlayer player){
       if(Rect.intersects(rectangle, player.getRectangle())||Rect.intersects(rectangle2, player.getRectangle()))return true;
       else return false;
    }
    @Override

    public void update(){}
    @Override
    public void draw(Canvas canvas){
        Paint paint=new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);
        canvas.drawRect(rectangle2,paint);
    }
}
