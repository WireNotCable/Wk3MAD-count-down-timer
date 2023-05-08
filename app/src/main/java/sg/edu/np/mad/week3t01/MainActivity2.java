package sg.edu.np.mad.week3t01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    String title = "Main Activity 2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.v(title,"Create");
        Intent myRecvIntent = getIntent();
        String recvUserName;
        String recvPassword;
        recvUserName = myRecvIntent.getStringExtra("UserName");
        recvPassword = myRecvIntent.getStringExtra("UserPassword");

        Log.v(title, "Recved Username: "+recvUserName+" Password: "+recvPassword);
        myCountDownTimer();
    }

    private int getRandomNumber(){
        Random ran = new Random();
        int myRandomValue = ran.nextInt(999999);
        return myRandomValue;
    }

    CountDownTimer myCountDown;

    private void myCountDownTimer(){
        TextView myOTPNumber = findViewById(R.id.textView5);
        myOTPNumber.setText("Your OTP is " + getRandomNumber());

        myCountDown = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                Log.v(title, "Countdown "+ l/1000);
                Toast.makeText(getApplicationContext(), "Your OTP will expire in "+ l/1000 + "secs.",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Log.v(title, "Finish Countdown");
                myCountDown.cancel();
                queryOTP();
            }
        };
        myCountDown.start();
    }

    private void queryOTP(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New OTP?");
        builder.setMessage("Your OTP just Expired!! Do you want a new OTP?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.v(title, "User accepts");
                myCountDownTimer();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.v(title,"User declines");
                myCountDown.cancel();
                finish();
            }
        });
        builder.setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();
    }
}