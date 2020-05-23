package devlrmve.atrapacor.com.atrapacor.Activities;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;

import devlrmve.atrapacor.com.atrapacor.Database.Senteces;
import devlrmve.atrapacor.com.atrapacor.R;
import devlrmve.atrapacor.com.atrapacor.Utils.CurrentDate;
import devlrmve.atrapacor.com.atrapacor.Utils.Scores;
import devlrmve.atrapacor.com.atrapacor.Utils.ScoresAdapter;


public class Main_All_Scores extends AppCompatActivity {
    private static String email_all_score;
    private static int level_all_score;
    public static String today,yesterday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_all_scores);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        today= getString(R.string.today);
        yesterday= getString(R.string.yesterday);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.comingsoon, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent intent = getIntent();
        email_all_score = intent.getExtras().getString("email");
        level_all_score = intent.getExtras().getInt("level");

        this.setTitle(getString(R.string.Level_title) + level_all_score + getString(R.string.scores_title));

        ArrayList<Scores> scores = Senteces.readScores(getBaseContext(), email_all_score, level_all_score);
        ArrayList<Date> dates = Senteces.readDates(getBaseContext());
        ArrayList<String> type_view = new ArrayList<>();

        String date_final = "";
        for (Scores score : scores) {
            if (!CurrentDate.dateFormatString(score.getDate())
                    .equalsIgnoreCase(date_final)) {
                type_view.add("TYPE_VIEW");
                date_final = CurrentDate.dateFormatString(score.getDate());
            } else {
                type_view.add("TYPE_HIDEN");
            }

        }


        RecyclerView rvScores_level_One = (RecyclerView) findViewById(R.id.my_recycler_view);
        ScoresAdapter adapter = new ScoresAdapter(Scores.createScoreList(scores), type_view, getFragmentManager(), email_all_score, level_all_score);
        rvScores_level_One.setAdapter(adapter);
        rvScores_level_One.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
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
