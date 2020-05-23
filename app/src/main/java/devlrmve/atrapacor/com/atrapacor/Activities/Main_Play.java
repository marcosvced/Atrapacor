package devlrmve.atrapacor.com.atrapacor.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.File;
import java.util.ArrayList;

import devlrmve.atrapacor.com.atrapacor.Database.Senteces;
import devlrmve.atrapacor.com.atrapacor.R;
import devlrmve.atrapacor.com.atrapacor.Utils.CircleTransform;
import devlrmve.atrapacor.com.atrapacor.Utils.DownloadPicture;
import devlrmve.atrapacor.com.atrapacor.Utils.RecordsName;


public class Main_Play extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private TextView scoreView;
    private TextView bestScoreView;
    private TextView levelUserView;
    private TextView totalScore;
    private TextView maxScore;
    private String email, username;
    ProgressBar progressBar;
    protected View header;
    String typeUser;
    private GoogleApiClient mGoogleApiClient;
    int levelUser;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main_play);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //INSET THE DATA OF TABLE RECORDS_NAME
        if (Senteces.readRecordsName(getBaseContext()).size() == 0) {
            String[] names_record = new String[]{
                    getString(R.string.mole),
                    getString(R.string.fox),
                    getString(R.string.predator),
                    getString(R.string.feline),
                    getString(R.string.lynx),
                    getString(R.string.prey),
                    getString(R.string.eagle),
                    getString(R.string.tenPoints),
                    getString(R.string.unLevel2),
                    getString(R.string.unLevel3)
            };
            String[] description_records = new String[]{
                    getString(R.string.des_mole),
                    getString(R.string.des_fox),
                    getString(R.string.des_predator),
                    getString(R.string.des_feline),
                    getString(R.string.des_lynx),
                    getString(R.string.des_prey),
                    getString(R.string.des_eagle),
                    getString(R.string.des_tenPoints),
                    getString(R.string.des_unLevel2),
                    getString(R.string.des_unLevel3)
            };

            for (int i = 0; i < 10; i++) {
                RecordsName recordsName = new RecordsName(i + 1, names_record[i], description_records[i]);
                Senteces.insertIntoRecordsName(getBaseContext(), recordsName);
            }

        }  //END INSERT INTO RECORDS_NAME

        //views
        scoreView = (TextView) findViewById(R.id.puntuacion);
        bestScoreView = (TextView) findViewById(R.id.mejorPuntuacion);
        levelUserView = (TextView) findViewById(R.id.levelPlay);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        totalScore = (TextView) findViewById(R.id.totalScore);
        maxScore = (TextView) findViewById(R.id.finishScore);
        //end view

        //get info of intent
        Intent intent = getIntent();
        email = intent.getExtras().getString("email");
        final String photo = intent.getExtras().getString("photo");
        //end get info intent

        //get user info
        ArrayList<String> dataUser = Senteces.readUser(getBaseContext(), email);
        email = dataUser.get(0);
        username = dataUser.get(1);
        //end user info


        // [START configure_signin]
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(AppIndex.API).build();
        // [END build_client]

        //VIEW NAVIGATION DRAWER
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        header = navigationView.getHeaderView(0);

        //this part to check the connection and the profile photo
        if (intent.getExtras().getString("typeUser").equalsIgnoreCase("google")) {
            typeUser = "google";
            if (DownloadPicture.checkNetwork((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))) {
                if ((pictureThread == null) || (pictureThread.getStatus() == AsyncTask.Status.FINISHED)) {
                    if (!photo.equals("null")) {
                        pictureThread = new PictureThread(photo, email);
                        pictureThread.execute();
                    } else {
                        ImageView photoView = (ImageView) header.findViewById(R.id.photoPlay);
                        BitmapDrawable drawable = (BitmapDrawable) photoView.getDrawable();
                        Bitmap bm = drawable.getBitmap();
                        photoView.setImageBitmap(CircleTransform.transform(bm));
                    }
                }
            } else {
                Snackbar.make(findViewById(R.id.layoutMainPlayActivity), R.string.whitoutnet, Snackbar.LENGTH_LONG).show();
                DownloadPicture.mCurrentPhotoPath = "" + (new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), email + ".jpg"));
                ImageView photoView = (ImageView) header.findViewById(R.id.photoPlay);
                Bitmap bm = BitmapFactory.decodeFile(DownloadPicture.mCurrentPhotoPath);
                if (bm == null) {
                    BitmapDrawable drawable = (BitmapDrawable) photoView.getDrawable();
                    bm = drawable.getBitmap();
                }
                photoView.setImageBitmap(CircleTransform.transform(bm));
            }
        } else {
            typeUser = "default";
            ImageView photoView = (ImageView) header.findViewById(R.id.photoPlay);
            BitmapDrawable drawable = (BitmapDrawable) photoView.getDrawable();
            Bitmap bm = drawable.getBitmap();
            photoView.setImageBitmap(CircleTransform.transform(bm));
        }
        //end profile photo

        TextView emailView = (TextView) header.findViewById(R.id.email_navigation);
        emailView.setText(email);

        TextView nameView = (TextView) header.findViewById(R.id.username_navigation);
        nameView.setText(username);
        //END VIEW NAVIGATION DRAWER
        levelUser = Senteces.selectLevel(getBaseContext(), email);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


    }

    private void startGame(boolean b) {
        if (b) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent mainGame = new Intent(getApplicationContext(), Main_Game.class);
                    mainGame.putExtra("email", email);
                    mainGame.putExtra("username", username);
                    mainGame.putExtra("bstpuntuation", Senteces.readBestScore(getBaseContext(), email, levelUser));
                    mainGame.putExtra("typeUser", typeUser);
                    mainGame.putExtra("levelUser", levelUser);
                    startActivity(mainGame);
                }
            });
        } else {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(findViewById(R.id.layoutMainPlayActivity), R.string.msg_unlock_level, Snackbar.LENGTH_LONG).show();
                }
            });

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        levelUser = Senteces.selectLevel(getBaseContext(), email);
        itemSelect(getBaseContext(), email, levelUser);

        if (Senteces.success) {
            Snackbar.make(findViewById(R.id.layoutMainPlayActivity), "Unloock a new achievement", Snackbar.LENGTH_INDEFINITE).setAction("View", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent recordsIntent = new Intent(getApplicationContext(), Main_Records.class);
                    recordsIntent.putExtra("email", email);
                    recordsIntent.putExtra("name", username);
                    startActivity(recordsIntent);
                }
            }).setActionTextColor(getBaseContext().getResources().getColor(R.color.colorAccent)).show();

            Senteces.success = false;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            case R.id.action_level1:
                levelUser = 1;
                itemSelect(getBaseContext(), email, levelUser);
                break;
            case R.id.action_level2:
                levelUser = 2;
                itemSelect(getBaseContext(), email, levelUser);
                break;
            case R.id.action_level3:
                levelUser = 3;
                itemSelect(getBaseContext(), email, levelUser);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void itemSelect(Context baseContext, String email, int levelUser) {
        scoreView.setText(Senteces.readLastScore(getBaseContext(), email, levelUser));
        bestScoreView.setText(Senteces.readBestScore(getBaseContext(), email, levelUser));
        levelUserView.setText(getString(R.string.levelTwoDot) + " " + levelUser);
        boolean bool = (Senteces.existsScore(baseContext, email, levelUser));

        switch (levelUser) {
            case 1:
                bool = true;
                maxScore.setText(String.format("/%d", 2500));
                progressBar.setMax(2500);
                break;
            case 2:
                maxScore.setText(String.format("/%d", 1500));
                progressBar.setMax(1500);
                break;
            case 3:
                maxScore.setText(String.format("/%d", 500));
                progressBar.setMax(500);
                break;
        }
        int scores = Integer.parseInt(Senteces.readTotalScores(baseContext, email, levelUser));
        totalScore.setText(scores + "");
        progressBar.setProgress(scores);
        startGame(bool);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_scores) {
            Intent scoresIntent = new Intent(getApplicationContext(), Main_Scores.class);
            scoresIntent.putExtra("email", email);
            scoresIntent.putExtra("name", username);
            startActivity(scoresIntent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(getApplicationContext(), Main_Tools.class);
            intent.putExtra("email", email);
            startActivity(intent);
        } else if (id == R.id.nav_exit) {
            signOut();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "MarcosVE@coreo.balidea.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Find The Color");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } else if (id == R.id.nav_records) {
            Intent recordsIntent = new Intent(getApplicationContext(), Main_Records.class);
            recordsIntent.putExtra("email", email);
            recordsIntent.putExtra("name", username);
            startActivity(recordsIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_play, menu);
        return true;
    }

    // [START signOut]
    private void signOut() {
        if (typeUser.equalsIgnoreCase("google")) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            // [START_EXCLUDE]
                            Intent login = new Intent(getApplicationContext(), Login_Activity.class);
                            startActivity(login);
                            finish();
                            // [END_EXCLUDE]
                        }
                    });
        } else {
            SharedPreferences settings = getApplicationContext().getSharedPreferences("Login_Activity", Context.MODE_PRIVATE);
            settings.edit().clear().apply();
            Intent login = new Intent(getApplicationContext(), Login_Activity.class);
            startActivity(login);
            finish();

        }
    }
    // [END signOut]


    //-----DONWLOAD PICTURE----------
    /**
     * FIO
     */
    private PictureThread pictureThread;

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main_Play Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://devlrmve.atrapacor.com.atrapacor/http/host/path")
        );
        AppIndex.AppIndexApi.start(mGoogleApiClient, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main_Play Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://devlrmve.atrapacor.com.atrapacor/http/host/path")
        );
        AppIndex.AppIndexApi.end(mGoogleApiClient, viewAction);
        mGoogleApiClient.disconnect();
    }

    private class PictureThread extends AsyncTask<Void, Integer, Boolean> {

        String IMAXE_DESCARGAR;
        String tarefaEmail;

        PictureThread(String url, String email) {
            IMAXE_DESCARGAR = url;
            tarefaEmail = email;
        }

        @Override
        protected void onPreExecute() {
            ImageView photoView = (ImageView) header.findViewById(R.id.photoPlay);
            BitmapDrawable drawable = (BitmapDrawable) photoView.getDrawable();
            Bitmap bm = drawable.getBitmap();
            photoView.setImageBitmap(CircleTransform.transform(bm));
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            DownloadPicture.descargarArquivo(IMAXE_DESCARGAR, tarefaEmail, getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                ImageView photoView = (ImageView) header.findViewById(R.id.photoPlay);
                Bitmap bm = BitmapFactory.decodeFile(DownloadPicture.mCurrentPhotoPath);
                photoView.setImageBitmap(CircleTransform.transform(bm));

            }
        }
    }

}
