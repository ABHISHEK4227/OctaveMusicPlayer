package sayantan.mediaplayback;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;


public class MainActivity extends Activity  {
    SeekBar seek_bar;
    ImageButton playpause,next;
    MediaPlayer player;
    //Chal Betichod isbar achha company lana

    boolean isPlaying = false;

    Handler seekHandler=new Handler();

@Override
    public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getInit();
    seekUpdation();


}

public void getInit()
{
    seek_bar =(SeekBar)findViewById(R.id.seek_bar);
    playpause=(ImageButton)findViewById(R.id.play);
    next=(ImageButton)findViewById(R.id.next);




    player=MediaPlayer.create(this,R.raw.idfc);
    seek_bar.setMax(player.getDuration());


}


Runnable run=new Runnable()
{

    @Override
    public void run(){
        seekUpdation();
    }

};

public void seekUpdation(){

    seek_bar.setProgress(player.getCurrentPosition());
    seekHandler.postDelayed(run,1000);


}


    public void buttonOnClick(View view){
switch (view.getId()){
    case R.id.play:
    {if (isPlaying) {
        playpause.setImageResource(R.drawable.play1);
        player.pause();
    } else {
        playpause.setImageResource(R.drawable.pause1);

        player.start();
    }
        isPlaying = !isPlaying;

    break;
    }
    case R.id.next:

        Intent activityChangeIntent = new Intent(MainActivity.this, song_two.class);

        // currentContext.startActivity(activityChangeIntent);

        MainActivity.this.startActivity(activityChangeIntent);
        finish();
        player.reset();
        break;







}








}


}








