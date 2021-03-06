package com.example.mycalendar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//알람종료
public class AlarmRingActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    Button btnMaingo;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmclose);

        //알림음 재생
        this.mediaPlayer= MediaPlayer.create(this, R.raw.alarm);
        this.mediaPlayer.start();

        findViewById(R.id.btnClose).setOnClickListener(mClickListener);
        btnMaingo=(Button) findViewById(R.id.btnMainGO);

        btnMaingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AlarmRingActivity.this,MainActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }


    protected void onDestroy(){
        super.onDestroy();


        if(this.mediaPlayer != null){
            this.mediaPlayer.release();
            this.mediaPlayer=null;
        }
    }


    //알람종료
    private  void close(){
        if(this.mediaPlayer.isPlaying()){
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer=null;

        }

       // finish();
    }



    View.OnClickListener mClickListener=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnClose:
                    //알람종료
                    close();
                    break;


            }
        }
    };

}
