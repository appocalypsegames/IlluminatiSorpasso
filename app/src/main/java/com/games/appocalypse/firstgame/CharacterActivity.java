package com.games.appocalypse.firstgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by eduar on 09/02/2018.
 */

public class CharacterActivity  extends AppCompatActivity {
    private SharedPreferences prefs;
    private MediaPlayer mp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character);
        mp = MediaPlayer.create(this, R.raw.atlantis);
        if(!mp.isPlaying()) {
            mp.start();
        }
        AdView mAdView=(AdView)findViewById(R.id.av_bottom_banner);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        prefs = this.getSharedPreferences("com.games.appocalypse.firstgame", Context.MODE_PRIVATE);
        TextView coinsText = findViewById(R.id.coins);
        Typeface face=Typeface.createFromAsset(getAssets(), "fonts/blowbrush.ttf");
        coinsText.setTypeface(face);
        TextView nocoinsText = findViewById(R.id.nocoins);
        Typeface face2=Typeface.createFromAsset(getAssets(), "fonts/blowbrush.ttf");
        nocoinsText.setTypeface(face2);
        coinsText.setText("COINS: "+prefs.getInt("coins",0));
        if(prefs.getBoolean("characterd",false)){
            ImageView characterdView=(ImageView)findViewById(R.id.characterd);
            characterdView.setImageResource(R.drawable.characterd);
        }
        if(prefs.getBoolean("charactere",false)){
            ImageView characterdView=(ImageView)findViewById(R.id.charactere);
            characterdView.setImageResource(R.drawable.charactere);
        }
        if(prefs.getBoolean("characterf",false)){
            ImageView characterdView=(ImageView)findViewById(R.id.characterf);
            characterdView.setImageResource(R.drawable.characterf);
        }
        if(prefs.getBoolean("characterg",false)){
            ImageView characterdView=(ImageView)findViewById(R.id.characterg);
            characterdView.setImageResource(R.drawable.characterg);
        }
        if(prefs.getBoolean("characterh",false)){
            ImageView characterdView=(ImageView)findViewById(R.id.characterh);
            characterdView.setImageResource(R.drawable.player0h);
        }
        if(prefs.getBoolean("characteri",false)){
            ImageView characterdView=(ImageView)findViewById(R.id.characteri);
            characterdView.setImageResource(R.drawable.player0i);
        }
    }
    public void goToMain(View view){
        stopMusic();
        startActivity(new Intent(this,MainActivity.class));
    }
    public void goToMain0(View view){
        stopMusic();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("character","0");
        editor.commit();
        startActivity(new Intent(this,MainActivity.class));
    }
    public void goToMain1(View view){
        stopMusic();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("character","1");
        editor.commit();
        startActivity(new Intent(this,MainActivity.class));
    }
    public void goToMain2(View view){
        stopMusic();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("character","2");
        editor.commit();
        startActivity(new Intent(this,MainActivity.class));
    }
    public void goToMain3(View view){

        if(!prefs.getBoolean("characterd",false)){
            if(prefs.getInt("coins",0)>=100){
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("coins",prefs.getInt("coins",0)-100);
                editor.putBoolean("characterd",true);
                editor.commit();
                TextView coinsText = findViewById(R.id.coins);
                coinsText.setText("COINS: "+prefs.getInt("coins",0));
                ImageView characterdView=(ImageView)findViewById(R.id.characterd);
                characterdView.setImageResource(R.drawable.characterd);
                TextView noCoins = findViewById(R.id.nocoins);
                noCoins.setText("RUN!");
            }else{
                TextView noCoins = findViewById(R.id.nocoins);
                noCoins.setText("100 COINS!");
            }
        }else {
            stopMusic();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("character", "3");
            editor.commit();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    public void goToMain4(View view){

        if(!prefs.getBoolean("charactere",false)){
            if(prefs.getInt("coins",0)>=100){
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("coins",prefs.getInt("coins",0)-100);
                editor.putBoolean("charactere",true);
                editor.commit();
                TextView coinsText = findViewById(R.id.coins);
                coinsText.setText("COINS: "+prefs.getInt("coins",0));
                ImageView characterdView=(ImageView)findViewById(R.id.charactere);
                characterdView.setImageResource(R.drawable.charactere);
                TextView noCoins = findViewById(R.id.nocoins);
                noCoins.setText("RUN!");
            }else{
                TextView noCoins = findViewById(R.id.nocoins);
                noCoins.setText("100 COINS!");
            }
        }else {
            stopMusic();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("character", "4");
            editor.commit();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    public void goToMain5(View view){

        if(!prefs.getBoolean("characterf",false)){
            if(prefs.getInt("coins",0)>=200){
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("coins",prefs.getInt("coins",0)-200);
                editor.putBoolean("characterf",true);
                editor.commit();
                TextView coinsText = findViewById(R.id.coins);
                coinsText.setText("COINS: "+prefs.getInt("coins",0));
                ImageView characterdView=(ImageView)findViewById(R.id.characterf);
                characterdView.setImageResource(R.drawable.characterf);
                TextView noCoins = findViewById(R.id.nocoins);
                noCoins.setText("RUN!");
            }else{
                TextView noCoins = findViewById(R.id.nocoins);
                noCoins.setText("200 COINS!");
            }
        }else {
            stopMusic();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("character", "5");
            editor.commit();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    public void goToMain6(View view){

        if(!prefs.getBoolean("characterg",false)){
            if(prefs.getInt("coins",0)>=200){
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("coins",prefs.getInt("coins",0)-200);
                editor.putBoolean("characterg",true);
                editor.commit();
                TextView coinsText = findViewById(R.id.coins);
                coinsText.setText("COINS: "+prefs.getInt("coins",0));
                ImageView characterdView=(ImageView)findViewById(R.id.characterg);
                characterdView.setImageResource(R.drawable.characterg);
                TextView noCoins = findViewById(R.id.nocoins);
                noCoins.setText("RUN!");
            }else{
                TextView noCoins = findViewById(R.id.nocoins);
                noCoins.setText("200 COINS!");
            }
        }else {
            stopMusic();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("character", "6");
            editor.commit();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    public void goToMain7(View view){

        if(!prefs.getBoolean("characterh",false)){
            if(prefs.getInt("coins",0)>=300){
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("coins",prefs.getInt("coins",0)-300);
                editor.putBoolean("characterh",true);
                editor.commit();
                TextView coinsText = findViewById(R.id.coins);
                coinsText.setText("COINS: "+prefs.getInt("coins",0));
                ImageView characterdView=(ImageView)findViewById(R.id.characterh);
                characterdView.setImageResource(R.drawable.player0h);
                TextView noCoins = findViewById(R.id.nocoins);
                noCoins.setText("RUN!");
            }else{
                TextView noCoins = findViewById(R.id.nocoins);
                noCoins.setText("300 COINS!");
            }
        }else {
            stopMusic();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("character", "7");
            editor.commit();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    public void goToMain8(View view){

        if(!prefs.getBoolean("characteri",false)){
            if(prefs.getInt("coins",0)>=300){
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("coins",prefs.getInt("coins",0)-300);
                editor.putBoolean("characteri",true);
                editor.commit();
                TextView coinsText = findViewById(R.id.coins);
                coinsText.setText("COINS: "+prefs.getInt("coins",0));
                ImageView characterdView=(ImageView)findViewById(R.id.characteri);
                characterdView.setImageResource(R.drawable.player0i);
                TextView noCoins = findViewById(R.id.nocoins);
                noCoins.setText("RUN!");
            }else{
                TextView noCoins = findViewById(R.id.nocoins);
                noCoins.setText("300 COINS!");
            }
        }else {
            stopMusic();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("character", "8");
            editor.commit();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    public void stopMusic(){
        if(mp!=null)mp.release();
    }
    @Override
    public void onBackPressed ()
    {
        if (mp != null)
            mp.release();
        super.onBackPressed();
    }
}
