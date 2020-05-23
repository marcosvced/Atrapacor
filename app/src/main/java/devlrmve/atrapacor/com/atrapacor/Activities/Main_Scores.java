package devlrmve.atrapacor.com.atrapacor.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.io.File;
import java.util.ArrayList;

import devlrmve.atrapacor.com.atrapacor.Database.Senteces;
import devlrmve.atrapacor.com.atrapacor.R;
import devlrmve.atrapacor.com.atrapacor.Utils.CurrentDate;
import devlrmve.atrapacor.com.atrapacor.Utils.DownloadPicture;
import devlrmve.atrapacor.com.atrapacor.Utils.Scores;
import devlrmve.atrapacor.com.atrapacor.Utils.UserType;


public class Main_Scores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scores);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String email = intent.getExtras().getString("email");
        String name = intent.getExtras().getString("name");

        DownloadPicture.mCurrentPhotoPath = "" + (new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), email + ".jpg"));
        ImageView photoView = (ImageView) findViewById(R.id.backgroundScore);
        Bitmap bm = BitmapFactory.decodeFile(DownloadPicture.mCurrentPhotoPath);
        photoView.setImageBitmap(bm);

        //set grey color to email icon
        ImageView imageView = (ImageView) findViewById(R.id.imageView_email);
        imageView.setColorFilter(Color.rgb(115, 115, 115)); // grey Tint




        TextView name_socres = (TextView) findViewById(R.id.name_scores);
        name_socres.setText(name);
        TextView email_scores = (TextView) findViewById(R.id.email_scores);
        email_scores.setText(email);
        TextView type_user_score = (TextView) findViewById(R.id.typeUser_score);
        type_user_score.setText(UserType.getUserType(email));

        scoresLevel1(email);
        scoresLevel2(email);
        sccoresLevel3(email);


        /* */


    }

    private void sccoresLevel3(final String email) {
        //level 3
        ArrayList<Scores> scores_levelThree = Senteces.readScores(getBaseContext(), email, 3);
        if (scores_levelThree.size() != 0) {
            CardView card_level3 = (CardView) findViewById(R.id.card_view_scores_level3);
            card_level3.setVisibility(View.VISIBLE);
            card_level3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent scores_intent = new Intent(getApplicationContext(), Main_All_Scores.class);
                    scores_intent.putExtra("email", email);
                    scores_intent.putExtra("level", 3);
                    startActivity(scores_intent);
                }
            });
            if (scores_levelThree.size() >= 1) {
                LinearLayout linearLayout_level3 = (LinearLayout) findViewById(R.id.layout_score_one_level3);
                linearLayout_level3.setVisibility(View.VISIBLE);

                TextView score_one = (TextView) findViewById(R.id.score_one_level3);
                score_one.setText(scores_levelThree.get(0).getScore() + "");

                TextView date_one = (TextView) findViewById(R.id.dateScore_one_level3);
                date_one.setText(CurrentDate.dateFormatString(scores_levelThree.get(0).getDate()));
            }
            if (scores_levelThree.size() >= 2) {
                LinearLayout linearLayout_level3_socre2 = (LinearLayout) findViewById(R.id.layout_score_two_level3);
                linearLayout_level3_socre2.setVisibility(View.VISIBLE);

                TextView score_two = (TextView) findViewById(R.id.score_two_level3);
                score_two.setText(scores_levelThree.get(1).getScore() + "");

                TextView date_two = (TextView) findViewById(R.id.dateScore_tow_level3);
                date_two.setText(CurrentDate.dateFormatString(scores_levelThree.get(1).getDate()));
            }
            if (scores_levelThree.size() >= 3) {
                LinearLayout linearLayout_level3_socre3 = (LinearLayout) findViewById(R.id.layout_score_three_level3);
                linearLayout_level3_socre3.setVisibility(View.VISIBLE);

                TextView score_three = (TextView) findViewById(R.id.score_three_level3);
                score_three.setText(scores_levelThree.get(2).getScore() + "");

                TextView date_three = (TextView) findViewById(R.id.dateScore_three_level3);
                date_three.setText(CurrentDate.dateFormatString(scores_levelThree.get(2).getDate()));
            }
            if (scores_levelThree.size() >= 4) {
                LinearLayout linearLayout_level3_score4 = (LinearLayout) findViewById(R.id.layout_score_two_level3);
                linearLayout_level3_score4.setVisibility(View.VISIBLE);

                TextView score_for = (TextView) findViewById(R.id.score_for_level3);
                score_for.setText(scores_levelThree.get(3).getScore() + "");

                TextView date_for = (TextView) findViewById(R.id.dateScore_for_level3);
                date_for.setText(CurrentDate.dateFormatString(scores_levelThree.get(3).getDate()));
            }
        }
    }

    private void scoresLevel2(final String email) {
        //level 2
        ArrayList<Scores> scores_levelTwo = Senteces.readScores(getBaseContext(), email, 2);
        if (scores_levelTwo.size() != 0) {
            CardView card_level2 = (CardView) findViewById(R.id.card_view_scores_level2);
            card_level2.setVisibility(View.VISIBLE);
            card_level2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent scores_intent = new Intent(getApplicationContext(), Main_All_Scores.class);
                    scores_intent.putExtra("email", email);
                    scores_intent.putExtra("level", 2);
                    startActivity(scores_intent);
                }
            });
            if (scores_levelTwo.size() >= 1) {
                LinearLayout linearLayout_level2 = (LinearLayout) findViewById(R.id.layout_score_one_level2);
                linearLayout_level2.setVisibility(View.VISIBLE);

                TextView score_one = (TextView) findViewById(R.id.score_one_level2);
                score_one.setText(scores_levelTwo.get(0).getScore() + "");

                TextView date_one = (TextView) findViewById(R.id.dateScore_one_level2);
                date_one.setText(CurrentDate.dateFormatString(scores_levelTwo.get(0).getDate()));
            }
            if (scores_levelTwo.size() >= 2) {
                LinearLayout linearLayout_level2_socre2 = (LinearLayout) findViewById(R.id.layout_score_two_level2);
                linearLayout_level2_socre2.setVisibility(View.VISIBLE);

                TextView score_two = (TextView) findViewById(R.id.score_two_level2);
                score_two.setText(scores_levelTwo.get(1).getScore() + "");

                TextView date_two = (TextView) findViewById(R.id.dateScore_tow_level2);
                date_two.setText(CurrentDate.dateFormatString(scores_levelTwo.get(1).getDate()));
            }
            if (scores_levelTwo.size() >= 3) {
                LinearLayout linearLayout_level2_socre3 = (LinearLayout) findViewById(R.id.layout_score_three_level2);
                linearLayout_level2_socre3.setVisibility(View.VISIBLE);

                TextView score_three = (TextView) findViewById(R.id.score_three_level2);
                score_three.setText(scores_levelTwo.get(2).getScore() + "");

                TextView date_three = (TextView) findViewById(R.id.dateScore_three_level2);
                date_three.setText(CurrentDate.dateFormatString(scores_levelTwo.get(2).getDate()));
            }
            if (scores_levelTwo.size() >= 4) {
                LinearLayout linearLayout_level2_score4 = (LinearLayout) findViewById(R.id.layout_score_for_level2);
                linearLayout_level2_score4.setVisibility(View.VISIBLE);

                TextView score_for = (TextView) findViewById(R.id.score_for_level2);
                score_for.setText(scores_levelTwo.get(3).getScore() + "");

                TextView date_for = (TextView) findViewById(R.id.dateScore_for_level2);
                date_for.setText(CurrentDate.dateFormatString(scores_levelTwo.get(3).getDate()));
            }
        }
    }

    private void scoresLevel1(final String email) {
        //level 1
        final ArrayList<Scores> scores_levelOne = Senteces.readScores(getBaseContext(), email, 1);
        if (scores_levelOne.size() != 0) {
            CardView card_level1 = (CardView) findViewById(R.id.card_view_scores_level1);
            card_level1.setVisibility(View.VISIBLE);
            card_level1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent scores_intent = new Intent(getApplicationContext(), Main_All_Scores.class);
                    scores_intent.putExtra("email", email);
                    scores_intent.putExtra("level", 1);
                    startActivity(scores_intent);

                }
            });
            if (scores_levelOne.size() >= 1) {
                LinearLayout linearLayout_level1 = (LinearLayout) findViewById(R.id.layout_score_one_level1);
                linearLayout_level1.setVisibility(View.VISIBLE);

                TextView score_one = (TextView) findViewById(R.id.score_one_level1);
                score_one.setText(scores_levelOne.get(0).getScore() + "");

                TextView date_one = (TextView) findViewById(R.id.dateScore_one_level1);
                date_one.setText(CurrentDate.dateFormatString(scores_levelOne.get(0).getDate()));
            }
            if (scores_levelOne.size() >= 2) {
                LinearLayout linearLayout_level1_socre2 = (LinearLayout) findViewById(R.id.layout_score_two_level1);
                linearLayout_level1_socre2.setVisibility(View.VISIBLE);

                TextView score_two = (TextView) findViewById(R.id.score_two_level1);
                score_two.setText(scores_levelOne.get(1).getScore() + "");

                TextView date_two = (TextView) findViewById(R.id.dateScore_tow_level1);
                date_two.setText(CurrentDate.dateFormatString(scores_levelOne.get(1).getDate()));
            }
            if (scores_levelOne.size() >= 3) {
                LinearLayout linearLayout_level1_socre3 = (LinearLayout) findViewById(R.id.layout_score_three_level1);
                linearLayout_level1_socre3.setVisibility(View.VISIBLE);

                TextView score_three = (TextView) findViewById(R.id.score_three_level1);
                score_three.setText(scores_levelOne.get(2).getScore() + "");

                TextView date_three = (TextView) findViewById(R.id.dateScore_three_level1);
                date_three.setText(CurrentDate.dateFormatString(scores_levelOne.get(2).getDate()));
            }
            if (scores_levelOne.size() >= 4) {
                LinearLayout linearLayout_level1_score4 = (LinearLayout) findViewById(R.id.layout_score_for_level1);
                linearLayout_level1_score4.setVisibility(View.VISIBLE);

                TextView score_for = (TextView) findViewById(R.id.score_for_level1);
                score_for.setText(scores_levelOne.get(3).getScore() + "");

                TextView date_for = (TextView) findViewById(R.id.dateScore_for_level1);
                date_for.setText(CurrentDate.dateFormatString(scores_levelOne.get(3).getDate()));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_scores, menu);
        return true;
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
}
