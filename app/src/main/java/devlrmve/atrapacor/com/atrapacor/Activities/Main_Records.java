package devlrmve.atrapacor.com.atrapacor.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import devlrmve.atrapacor.com.atrapacor.Database.Senteces;
import devlrmve.atrapacor.com.atrapacor.R;
import devlrmve.atrapacor.com.atrapacor.Utils.Records;
import devlrmve.atrapacor.com.atrapacor.Utils.RecordsAdapter;
import devlrmve.atrapacor.com.atrapacor.Utils.RecordsName;


public class Main_Records extends AppCompatActivity {
    private static String email_all_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_records);

        Intent intent = getIntent();
        email_all_score = intent.getExtras().getString("email");
        ArrayList<RecordsName> records = Senteces.readRecordsName(getBaseContext());
        ArrayList<Records> records_user = Senteces.readRecords(getBaseContext(), email_all_score);

        ArrayList<String> type_view = new ArrayList<>();
        ArrayList<Records> records_user_send = new ArrayList<>();

        //edit the actionbar
        setupActionBar(records_user.size(),records.size());

        boolean flag = false;
        for (RecordsName recordName : records) {
            for (Records record : records_user) {
                if (recordName.getId_record() == record.getId_Record()) {
                    type_view.add("TYPE_VIEW");
                    records_user_send.add(record);
                    flag = true;
                }
            }
            if (!flag) {
                type_view.add("TYPE_HIDEN");
                records_user_send.add(null);
            } else {
                flag = false;
            }

        }


        RecyclerView rvScores_level_One = (RecyclerView) findViewById(R.id.my_recycler_view);
        RecordsAdapter adapter = new RecordsAdapter(RecordsName.createRecordList(records), type_view, records_user_send, getFragmentManager());
        rvScores_level_One.setAdapter(adapter);
        rvScores_level_One.setLayoutManager(new LinearLayoutManager(this));
        //}
    }
    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     * @param sizeOne
     * @param sizeTow
     */
    private void setupActionBar(int sizeOne, int sizeTow) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |
                    ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_USE_LOGO);
            actionBar.setLogo(R.drawable.ic_action_games_achievements_white);
            actionBar.setTitle(getString(R.string.records));
            actionBar.setSubtitle(sizeOne + "/" + sizeTow);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_scores, menu);
        return true;
    }

}
