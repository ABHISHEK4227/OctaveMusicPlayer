package sayantan.mediaplayback;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;


public class song_two extends Activity {
    SeekBar seek_bar;
    ImageButton play2, prev2,next2;
    MediaPlayer player;

    boolean isPlaying = false;

    Handler seekHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        getInit();
        seekUpdation();


    }

    public void getInit() {
        seek_bar = (SeekBar) findViewById(R.id.seek_bar);
        play2= (ImageButton) findViewById(R.id.play2);
        prev2 = (ImageButton) findViewById(R.id.prev2);
        next2=(ImageButton)findViewById(R.id.next2);


        player = MediaPlayer.create(this, R.raw.animals);
        seek_bar.setMax(player.getDuration());


    }


    Runnable run = new Runnable() {

        @Override
        public void run() {
            seekUpdation();
        }

    };

    public void seekUpdation() {

        seek_bar.setProgress(player.getCurrentPosition());
        seekHandler.postDelayed(run, 1000);


    }


    public void buttonOnClick1(View view) {
        switch (view.getId()) {
            case R.id.play2: {
                if (isPlaying) {
                    play2.setImageResource(R.drawable.play1);
                    player.pause();
                } else {
                    play2.setImageResource(R.drawable.pause1);

                    player.start();
                }
                isPlaying = !isPlaying;

                break;
            }
            case R.id.prev2:

                Intent activityChangeIntent = new Intent(song_two.this, MainActivity.class);

                // currentContext.startActivity(activityChangeIntent);

                song_two.this.startActivity(activityChangeIntent);
                finish();
                player.reset();
                break;
            case R.id.next2:
                Intent activityChangeIntent1 = new Intent(song_two.this, song_three.class);

                // currentContext.startActivity(activityChangeIntent);

                song_two.this.startActivity(activityChangeIntent1);
                finish();
                player.reset();
                break;




        }


    }
}






