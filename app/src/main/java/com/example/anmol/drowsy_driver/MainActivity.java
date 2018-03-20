package com.example.anmol.drowsy_driver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button b1;
    private ImageView i;
    private static final int VIDEO_REQUEST_CODE= 100;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.bt);
        i=(ImageView)findViewById(R.id.img);
         Log.d("log","in on first");
    }


    public void startRecording(View view) {
         Log.d("log","in on first");

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        File video_file=getFilepath();
//         Log.d("log","in on tcreate"+video_file.toString());
//
//
//        Uri video_uri=FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".my.package.name.provider",video_file);
//        Log.d("log","in on fcreate");
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, video_uri);
//        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
         Log.d("log","in on create"); 
        startActivityForResult(intent,VIDEO_REQUEST_CODE);
        Log.d("log","in on create");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("log","in onactivity ");
        if(requestCode==VIDEO_REQUEST_CODE){
            Log.d("log","in onactivity ");
            if(resultCode==RESULT_OK){
                Uri videoUri = data.getData();
                Toast.makeText(getApplicationContext(),videoUri.toString(),Toast.LENGTH_LONG).show();

               Intent tostart = new Intent(Intent.ACTION_VIEW);
                tostart.setDataAndType(videoUri, "video/*");
                startActivity(tostart);
                Toast.makeText(getApplicationContext(),"VIDEO SUCCESSFULLY RECORDED",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"VIDEO RECORDING FAILED",Toast.LENGTH_LONG).show();

            }
        }
    }
    public class GenericFileProvider extends FileProvider {}

    public File getFilepath(){
        File folder= new File("documents/video_app");
        if(folder.exists()){
            folder.mkdir();
        }
        File video_file=new File(folder,"sample_video.mp4");
        return video_file;
    }
}