package devlrmve.atrapacor.com.atrapacor.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import devlrmve.atrapacor.com.atrapacor.DialogsFragment.Restart_DialogFragment;
import devlrmve.atrapacor.com.atrapacor.R;


public class Main_Tools extends AppCompatActivity {
    String email_tools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tools);

        setupActionBar();

        Intent intent = getIntent();
        email_tools = intent.getExtras().getString("email");

        LinearLayout reboot = (LinearLayout) findViewById(R.id.reiniciar);
        reboot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("title", "Restart");
                args.putString("emailuser", email_tools);
                Restart_DialogFragment dialogFragment = Restart_DialogFragment.newInstance(args);
                dialogFragment.show(getFragmentManager(), "dialog");
            }
        });

        LinearLayout valorar = (LinearLayout) findViewById(R.id.valorar);
        valorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        LinearLayout acercade = (LinearLayout) findViewById(R.id.acercade);
        acercade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        LinearLayout howplay=(LinearLayout)findViewById(R.id.howplay);
        howplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondLayoutIntro.class);
                startActivity(intent);
            }
        });

    }


    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
