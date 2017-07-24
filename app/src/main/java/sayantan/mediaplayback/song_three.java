package sayantan.mediaplayback;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;


public class song_three extends Activity {
    SeekBar seek_bar;
    ImageButton play3, prev3,next3;
    MediaPlayer player;

    boolean isPlaying = false;

    Handler seekHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        getInit();
        seekUpdation();


    }

    public void getInit() {
        seek_bar = (SeekBar) findViewById(R.id.seek_bar);
        play3= (ImageButton) findViewById(R.id.play3);
        prev3 = (ImageButton) findViewById(R.id.prev3);
        next3=(ImageButton)findViewById(R.id.next3);


        player = MediaPlayer.create(this, R.raw.reminder);
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


    public void buttonOnClick2(View view) {
        switch (view.getId()) {
            case R.id.play3: {
                if (isPlaying) {
                    play3.setImageResource(R.drawable.play1);
                    player.pause();
                } else {
                    play3.setImageResource(R.drawable.pause1);

                    player.start();
                }
                isPlaying = !isPlaying;

                break;
            }
            case R.id.prev3: {

                Intent activityChangeIntent1 = new Intent(song_three.this, song_two.class);

                // currentContext.startActivity(activityChangeIntent);

                song_three.this.startActivity(activityChangeIntent1);
                finish();
                player.reset();
                break;

            }
        }


    }
}






