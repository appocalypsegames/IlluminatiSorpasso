package com.games.appocalypse.firstgame;

        import android.app.Activity;
        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.util.DisplayMetrics;
        import android.view.Window;
        import android.view.WindowManager;

/**
 * Created by eduar on 06/02/2018.
 */

public class GameActivity extends Activity {
        private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH=dm.widthPixels;
        Constants.SCREEN_HEIGHT=dm.heightPixels;
        int character =Integer.parseInt(getIntent().getStringExtra("character"));

        mp = MediaPlayer.create(this,R.raw.fightscene);
        if(!mp.isPlaying()) {
            mp.start();
        }
        setContentView(new GamePanel(this,character,mp));


    }
    @Override
    public void onBackPressed ()
    {
        if (mp != null)
            mp.release();
        super.onBackPressed();
    }

}
