package com.games.appocalypse.firstgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Matrix;

/**
 * Created by eduar on 23/01/2018.
 */

public class RectPlayer implements GameObject {
    private Rect rectangle;
    private int color;

    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;
    private AnimationManager animationManager;

    public Rect getRectangle(){
        return rectangle;
    }
    public RectPlayer(Rect rectangle,int color,int character){
        this.rectangle=rectangle;
        this.color=color;

        BitmapFactory bf=new BitmapFactory();
        Bitmap idleImg,walk1,walk2;
           idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player0);
           walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player1);
            walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player2);
        if(character==1){
            idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player0b);
            walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player1b);
             walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player2b);
        }else if(character==2){
            idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player0c);
            walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player1c);
            walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player2c);
        }else if(character==3){
            idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player0d);
            walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player1d);
            walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player2d);
        }else if(character==4){
            idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player0e);
            walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player1e);
            walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player2e);
        }else if(character==5){
            idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player0f);
            walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player1f);
            walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player2f);
        }else if(character==6){
            idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player0g);
            walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player1g);
            walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player2g);
        }else if(character==7){
            idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player0h);
            walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player1h);
            walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player2h);
        }else if(character==8){
            idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player0i);
            walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player1i);
            walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.player2i);
        }
        idle=new Animation(new Bitmap[]{idleImg},2);
        walkRight=new Animation(new Bitmap[]{walk1,walk2},0.5f);

        Matrix m=new Matrix();
        m.setScale( -1 , 1 );
        walk1=Bitmap.createBitmap(walk1,0,0,walk1.getWidth(),walk1.getHeight(),m,false);
        walk2=Bitmap.createBitmap(walk2,0,0,walk2.getWidth(),walk2.getHeight(),m,false);



        walkLeft=new Animation(new Bitmap[]{walk1,walk2},0.5f);

        animationManager=new AnimationManager(new Animation[]{idle,walkRight,walkLeft});
    }
    @Override
    public void draw(Canvas canvas) {
        //Paint paint=new Paint();
        //paint.setColor(color);
        //canvas.drawRect(rectangle,paint);
        animationManager.draw(canvas,rectangle);
    }

    @Override
    public void update() {
        animationManager.update();
    }
    public void update(Point point){
        float oldLeft=rectangle.left;
       //l,t,r,b
        rectangle.set(point.x-rectangle.width()/2,point.y-rectangle.height()/2,point.x+rectangle.width()/2,point.y+rectangle.height()/2);
        int state=0;
        if(rectangle.left-oldLeft>5){
            state=1;
        }else if(rectangle.left-oldLeft<-5){
            state=2;
        }
        animationManager.playAnim(state);
        animationManager.update();
    }
}
