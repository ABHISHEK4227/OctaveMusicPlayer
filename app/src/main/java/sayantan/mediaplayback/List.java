package sayantan.mediaplayback;

import android.Manifest;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class List extends Activity {
     ListView lv;
    private String items[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lv=(ListView) findViewById(R.id.list);

        //Getting Permissions to Read Files
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            }
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            return;
        }




        final ArrayList<File> songList=songs(Environment.getExternalStorageDirectory());
        items=new String[songList.size()];
        for(int i=0;i<songList.size();i++){
            items[i]=songList.get(i).getName().toString().replace(".mp3","");
        }
        ArrayAdapter<String> adp=new ArrayAdapter<String>(getApplicationContext(),R.layout.listitem,R.id.title,items);
        lv.setAdapter(adp);



        //Defining click Events on ListView lv
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("pos",position);   //passing the position and the arraylist to next activity
                i.putExtra("songlist",songList);
                startActivity(i);
            }
        });



    }

    public ArrayList<File> songs(File root){
        File files[]=root.listFiles();

        ArrayList<File> arr=new ArrayList<File>();

        for(File f: files){
            if(f.isDirectory() && !f.isHidden()){

                arr.addAll(songs(f));
            }else{

                if(f.getName().endsWith(".mp3") || f.getName().endsWith(".wav")){
                    arr.add(f);

                }else{


                }

            }
        }
        return arr;

    }


}
