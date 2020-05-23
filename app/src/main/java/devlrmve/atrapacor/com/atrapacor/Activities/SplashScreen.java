package devlrmve.atrapacor.com.atrapacor.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import devlrmve.atrapacor.com.atrapacor.R;

public class SplashScreen extends AppCompatActivity {
    Timer timer;
    SharedPreferences sharedPref;
    boolean exist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        readPreferences();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                if (exist) {
                    String defaultValue = "";
                    String preferenceEmail = sharedPref.getString("Email", defaultValue);
                    String preferencePass = sharedPref.getString("Password", defaultValue);
                    String preferenceName = sharedPref.getString("Name", defaultValue);
                    Intent mainPlay = new Intent(getApplicationContext(), Main_Play.class);
                    mainPlay.putExtra("email", preferenceEmail);
                    mainPlay.putExtra("username", preferenceName);
                    mainPlay.putExtra("typeUser", "default");
                    startActivity(mainPlay);
                } else {
                    // Start the next activity
                    Intent mainIntent = new Intent().setClass(getBaseContext(), Login_Activity.class);
                    startActivity(mainIntent);

                    // Close the activity so the user won't able to go back this
                    // activity pressing Back button
                }
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        timer = new Timer();
        timer.schedule(task, 900);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
        finish();
    }

    private boolean readPreferences() {
        sharedPref = getApplicationContext().getSharedPreferences("Login_Activity", Context.MODE_PRIVATE);
        boolean exist = (sharedPref.getAll().isEmpty()) ? false : true;
        return exist;
    }
}
