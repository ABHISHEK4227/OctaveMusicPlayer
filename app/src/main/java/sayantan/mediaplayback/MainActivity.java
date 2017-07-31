package sayantan.mediaplayback;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends Activity  {

    //Initializing variables and objects
    SeekBar seek_bar;
    ImageButton playpause,next;
    static MediaPlayer player; //Variable is static to allow only one copy in the memory
    TextView Title;
    ArrayList<File> songList;
    int position;
    boolean isPlaying = true;
    Handler seekHandler=new Handler();
    //--------------------------------------------------

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
    Title=(TextView)findViewById(R.id.songTitle);

    //Check if a Song is Already Playing
    if(player!=null)
    {
        player.stop();
        player.release();
        player.release();
    }

    //Getting intents
    Intent i=getIntent();
    Bundle b=i.getExtras();
    songList=(ArrayList)b.getParcelableArrayList("songlist"); //Getting The Entire ArrayList
    position =b.getInt("pos");

    Title.setText(songList.get(position).getName().toString());


    //Creating URI object
    playMusic();
    playpause.setImageResource(R.drawable.pause1);
    seek_bar.setMax(player.getDuration());

    //Add Listener to seekbar
    seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(player!=null && fromUser){
                player.seekTo(progress);
            }}
    });
    //------------------------------------------------------


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


//Registering Button Click Events

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
    {   position=(position+1)%songList.size();
        Uri urk=Uri.parse(songList.get(position).toString());
        player.stop();
        player.release();

        playMusic();
        Title.setText(songList.get(position).getName().toString());
        break;
    }

    case R.id.prev: {
        if (position - 1 < 0)
            position = songList.size() - 1;
        else
            position = position - 1;

        player.stop();
        player.release();

        playMusic();
        Title.setText(songList.get(position).getName().toString());
        break;
    }
}
}



public void playMusic(){
    Uri ur=Uri.parse(songList.get(position).toString());
    player=MediaPlayer.create(getApplicationContext(),ur);
    player.start();
    seek_bar.setMax(player.getDuration());
    seekUpdation();
    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            position=(position+1)%songList.size();
            playMusic();
            Title.setText(songList.get(position).getName().toString());
        }
    });


}


}








