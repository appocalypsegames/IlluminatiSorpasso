package com.games.appocalypse.firstgame;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private String character="0";
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToGame(View view){
        if(mp!=null)mp.release();
        Intent intent = new Intent(getBaseContext(), GameActivity.class);
        intent.putExtra("character", character);
        startActivity(intent);
    }
    public void goToCharacter(View view){
        if(mp!=null)mp.release();
        startActivity(new Intent(this,CharacterActivity.class));
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        mp = MediaPlayer.create(this, R.raw.atlantis);
        if(!mp.isPlaying()) {
            mp.start();
        }
        Typeface face=Typeface.createFromAsset(getAssets(), "fonts/blowbrush.ttf");
        TextView textViewScore=findViewById(R.id.score);
        textViewScore.setTypeface(face);
        TextView textViewHighscore=findViewById(R.id.highscore);
        textViewHighscore.setTypeface(face);
        prefs = this.getSharedPreferences("com.games.appocalypse.firstgame", Context.MODE_PRIVATE);
        character=prefs.getString("character","0");
        ImageView imageView=(ImageView)findViewById(R.id.character);
        if(character.equals("0")){
            imageView.setImageResource(R.drawable.character);
        }else if(character.equals("1")){
            imageView.setImageResource(R.drawable.characterb);
        }else if(character.equals("2")){
            imageView.setImageResource(R.drawable.characterc);
        }else if(character.equals("3")){
            imageView.setImageResource(R.drawable.characterd);
        }else if(character.equals("4")){
            imageView.setImageResource(R.drawable.charactere);
        }else if(character.equals("5")){
            imageView.setImageResource(R.drawable.characterf);
        }else if(character.equals("6")){
            imageView.setImageResource(R.drawable.characterg);
        }else if(character.equals("7")){
            imageView.setImageResource(R.drawable.characterh);
        }else if(character.equals("8")){
            imageView.setImageResource(R.drawable.characteri);
        }
        AdView mAdView=(AdView)findViewById(R.id.av_bottom_banner);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        TextView highscore = findViewById(R.id.score);
        highscore.setText(String.valueOf(prefs.getInt("highscore", 0)));

    }

}
