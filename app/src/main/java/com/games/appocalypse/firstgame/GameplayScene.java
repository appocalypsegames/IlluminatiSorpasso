package com.games.appocalypse.firstgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.MotionEvent;

/**
 * Created by eduar on 05/02/2018.
 */

public class GameplayScene implements Scene {

    private Rect r = new Rect();

    private RectPlayer player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;

    private boolean movingPlayer = false;

    private boolean gameOver = false;
    private boolean primeraentrada =true;
    private long gameOverTime;

    private int coins=0;
    private long frameTime;
    private Context context;
    private SharedPreferences prefs;
    private MediaPlayer mp;
    public GameplayScene(Context context,int character,MediaPlayer mp) {
        player = new RectPlayer(new Rect(100, 100, 200, 200), Color.rgb(255, 0, 0),character);
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);

        this.mp=mp;
        obstacleManager = new ObstacleManager(250, 350, 50, Color.BLACK,context);

        frameTime = System.currentTimeMillis();
        this.context=context;

        primeraentrada =true;
    }

    public void reset() {
        if(mp.isPlaying()){
            mp.seekTo(0);
        }
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(250, 350, 50, Color.BLACK,context);
        movingPlayer = false;
        primeraentrada =true;
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!gameOver && player.getRectangle().contains((int)event.getX(), (int)event.getY()))
                    movingPlayer = true;
                if(gameOver && System.currentTimeMillis() - gameOverTime >= 2000) {
                    reset();
                    gameOver = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!gameOver && movingPlayer)
                    playerPoint.set((int)event.getX(), (int)event.getY());
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.RED);//background color

        player.draw(canvas);
        obstacleManager.draw(canvas);

        if(gameOver) {

            prefs = context.getSharedPreferences("com.games.appocalypse.firstgame", Context.MODE_PRIVATE);
            int highscore=prefs.getInt("highscore", 0);
            Paint paint = new Paint();
            if(highscore<=obstacleManager.getScore()){
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("highscore",obstacleManager.getScore());
                editor.commit();
                paint.setColor(Color.YELLOW);
                paint.setTextSize(100);
                drawCenterText(canvas, paint, "HIGHSCORE!",-100);
            }
            paint.setTextSize(100);
            paint.setColor(Color.WHITE);
            drawCenterText(canvas, paint, "GAME OVER",0);
            paint.setTextSize(20);
            drawCenterText(canvas, paint, "TAP TO RESTART",75);
            coins=obstacleManager.getCoins();
            if(coins>0) {
                if(primeraentrada) {
                    primeraentrada = false;
                    int coinss = prefs.getInt("coins", 0);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("coins", coins + coinss);
                    editor.commit();
                }
                paint.setColor(Color.YELLOW);
                drawCenterText(canvas, paint, "+" + coins + " coins", 120);
            }
        }
    }

    @Override
    public void update() {
        if(!gameOver) {
            if(frameTime < Constants.INIT_TIME)
                frameTime = Constants.INIT_TIME;
            frameTime = System.currentTimeMillis();


            if(playerPoint.x < 0)
                playerPoint.x = 0;
            else if(playerPoint.x > Constants.SCREEN_WIDTH)
                playerPoint.x = Constants.SCREEN_WIDTH;
            if(playerPoint.y < 0)
                playerPoint.y = 0;
            else if(playerPoint.y > Constants.SCREEN_HEIGHT)
                playerPoint.y = Constants.SCREEN_HEIGHT;

            player.update(playerPoint);
            obstacleManager.update();

            if(obstacleManager.playerCollide(player)==1) {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
        }
    }

    private void drawCenterText(Canvas canvas, Paint paint, String text,int sorpasso) {
        paint.setTextAlign(Paint.Align.LEFT);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/blowbrush.ttf");
        paint.setTypeface(face);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y+sorpasso, paint);
    }

}