package sg.edu.np.mad.week3t01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String title = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(title, "Create");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v(title, "Start!");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.v(title, "Resume!");

        EditText etUserName = findViewById(R.id.editTextText);
        EditText etPassword = findViewById(R.id.editTextText2);
        Button loginButton = findViewById(R.id.button);

        loginButton.setOnClickListener(new View.OnClickListener(){
            String usernameStr;
            String userPasswordStr;
            @Override
            public void onClick(View view){
                Log.v(title, "Login Button Pressed!");
                usernameStr = etUserName.getText().toString();
                userPasswordStr = etPassword.getText().toString();

                Log.v(title, "Username: "+usernameStr+"Password:"+userPasswordStr);
                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                myIntent.putExtra("UserName", usernameStr);
                myIntent.putExtra("UserPassword", userPasswordStr);
                startActivity(myIntent);
            }
        });
    }
}