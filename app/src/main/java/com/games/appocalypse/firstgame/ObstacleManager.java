package com.games.appocalypse.firstgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.ArrayList;

/**
 * Created by eduar on 23/01/2018.
 */

public class ObstacleManager {
    //higher index = lower on screen = higher y value
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Coins> coins;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;

    private long startTime;
    private long initTime;
    private int coinss;
    private int score = 0;

    private Context context;
    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int color, Context context) {
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;

        startTime = initTime = System.currentTimeMillis();

        obstacles = new ArrayList<>();
        coins = new ArrayList<>();

        this.context=context;
        populateObstacles();
    }

    public int playerCollide(RectPlayer player) {
        for(Obstacle ob : obstacles) {
            if(ob.playerCollide(player))
                return 1;
        }
        for(Coins co : coins) {
            if(co.playerCollide(player)){
                coins.remove(co);
                coinss++;
                return 2;
            }
        }
        return 3;
    }

    private void populateObstacles() {
        int currY = -5*Constants.SCREEN_HEIGHT/4;
        while(currY < 0) {
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            int xStartCoin = (int)(Math.random()*(Constants.SCREEN_WIDTH - 50));
            obstacles.add(new Obstacle(obstacleHeight, color, xStart, currY, playerGap));
            coins.add(new Coins(color,xStartCoin,currY));
            currY += obstacleHeight + obstacleGap;
        }
    }

    public void update() {
        if(startTime < Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;
        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = (float)(Math.sqrt(1 + (startTime - initTime)/2000.0))*Constants.SCREEN_HEIGHT/(10000.0f);
        for(Obstacle ob : obstacles) {
            ob.incrementY(speed * elapsedTime);
        }
        for(Coins co : coins) {
            co.incrementY(speed * elapsedTime);
        }
        if(obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new Obstacle(obstacleHeight, color, xStart, obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap, playerGap));
            obstacles.remove(obstacles.size() - 1);
            score++;
        }
        if(coins.size()>0){
            if(coins.get(coins.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT){
                int xStartCoin = (int) (Math.random() * (Constants.SCREEN_WIDTH -50));
                coins.add(0,new Coins(color, xStartCoin, obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap));
                coins.remove(coins.size() - 1);
            }
        }else{
            int xStartCoin = (int) (Math.random() * (Constants.SCREEN_WIDTH - 50));
            coins.add(0,new Coins(color, xStartCoin, obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap));
        }
    }

    public void draw(Canvas canvas) {
        for(Obstacle ob : obstacles)
            ob.draw(canvas);
        for(Coins co : coins)
            co.draw(canvas);
        Paint paint = new Paint();
        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/blowbrush.ttf");
        paint.setTypeface(face);
        paint.setTextSize(100);
        paint.setColor(Color.WHITE);
        canvas.drawText("" + score, 50, 50 + paint.descent() - paint.ascent(), paint);
    }
    public int getScore(){
        return score;
    }
    public int getCoins(){
        return coinss;
    }
}