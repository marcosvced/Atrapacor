package devlrmve.atrapacor.com.atrapacor.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import devlrmve.atrapacor.com.atrapacor.Database.Senteces;
import devlrmve.atrapacor.com.atrapacor.DialogsFragment.EndGame_DialogFragment;
import devlrmve.atrapacor.com.atrapacor.DialogsFragment.ReplayGame_DialogFragment;
import devlrmve.atrapacor.com.atrapacor.R;
import devlrmve.atrapacor.com.atrapacor.Utils.ImageAdapter;
import devlrmve.atrapacor.com.atrapacor.Utils.ResourceLevels;


public class Main_Game extends AppCompatActivity {
    public static int level = 1;
    public static GridView gridview;
    public static ImageAdapter adapter;
    public static int fewDifferents;
    public static TextView puntuationTextView;
    public static TextView bestTextView;
    public Temporizador timerTask;
    public static TextView time;
    public static ArrayList colors = new ArrayList<>();
    public static ArrayList colorsRandom = new ArrayList<>();
    public static int width;
    int levelUser;
    String emailGame;
    String userNameGame;
    String typeUser;
    String mCurrentPhotoPath;

    public static TextView bestScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        emailGame = intent.getExtras().getString("email");
        userNameGame = intent.getExtras().getString("username");
        typeUser = intent.getExtras().getString("typeUser");
        mCurrentPhotoPath = intent.getExtras().getString("photoGame");

        levelUser = intent.getExtras().getInt("levelUser");
        setupActionBar(levelUser);
        bestScore = (TextView) findViewById(R.id.mejorPuntuacionHidden);
        bestScore.setText(Senteces.readBestScore(getBaseContext(), emailGame, levelUser));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("title", getString(R.string.restart_game));
                args.putString("emailuser", emailGame);
                args.putString("userName", userNameGame);
                args.putString("photoGame", mCurrentPhotoPath);
                args.putString("typeUser", typeUser);
                args.putInt("levelUser", levelUser);
                ReplayGame_DialogFragment dialogFragment = ReplayGame_DialogFragment.newInstance(args);
                dialogFragment.show(getFragmentManager(), "dialog");
            }
        });

        //Views
        puntuationTextView = (TextView) findViewById(R.id.puntuacionGame);
        bestTextView = (TextView) findViewById(R.id.mejorPuntuacionGame);
        bestTextView.setText(Senteces.readBestScore(getBaseContext(), emailGame, levelUser));

        startGame(levelUser);

        if ((timerTask == null) || (timerTask.getStatus() == AsyncTask.Status.FINISHED)) {
            timerTask = new Temporizador();
            timerTask.execute();
        } else if (timerTask.getStatus() == AsyncTask.Status.RUNNING) {
            timerTask.cancel(true);
        }
    }

    private void setupActionBar(int level) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            String subtitle = getString(R.string.levelTwoDot) + level;
            actionBar.setSubtitle(subtitle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
        timerTask.cancel(true);
        colors.clear();
        colorsRandom.clear();
        ResourceLevels.coloresAleatorios.clear();
        ResourceLevels.colores.clear();
        arrayOfTags.clear();

    }

    int press = 0;

    @Override
    public void onBackPressed() {
        press++;
        Timer timer = null;
        if (press == 2) {
            if (timer != null) {
                timer.cancel();
            }
            super.onBackPressed();
        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                press = 0;
            }
        };
        // Simulate a long loading process on application startup.
        timer = new Timer();
        timer.schedule(task, 1000);
        Snackbar.make(findViewById(R.id.Main_Game_layout), R.string.clickagain, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Start the code of the game
     */
//--------------------------------CODIGO----------------------------------
    public void startGame(int levelUser) {
        //establecese o nivel un o iniciar
        level = 1;

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Log.i("metrics", metrics + "");
        //obetmos o tamaño da pantalla para poñer un tamaño a cada boton
       /* Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size.x, getResources().getDisplayMetrics());
        width = (width / 2) - 180;*/
        width = (int) (270 * metrics.density);
        //aqui cargamos todos os botons nun array
        colors = ResourceLevels.coloresArray();
        colorsRandom = ResourceLevels.coloresArrayRandomLevels(levelUser);

        //creamos a variable time para traballar sobre o textview do time
        time = (TextView) findViewById(R.id.tempo);
        //o mesmo para a puntuationTextView

        //creamos o grid
        gridview = (GridView) findViewById(R.id.gridview);
        //o adapter
        adapter = new ImageAdapter(this);


        //comezamos o xogo marcando o nivel.
        niveles(level);
    }


    public void niveles(int level) {
        int tamaño;
        switch (level) {
            case 1:
                tamaño = 3;
                break;
            case 2:
            case 3:
                tamaño = 4;
                break;
            case 4:
            case 5:
            case 6:
                tamaño = 5;
                break;
            case 7:
            case 8:
            case 9:
                tamaño = 6;
                break;
            default:
                tamaño = 7;
                break;
        }
        establecerAleatorios(tamaño, level);
    }

    public static Random r = new Random();
    public static Random color = new Random();
    public static int posicionUn;
    public static int posicionDous;
    public static int posicionTres;

    private void establecerAleatorios(int tamaño, int level) {
        ArrayList<Integer> posicions = new ArrayList<>();
        gridview.setNumColumns(tamaño);
        int colorPoñer = color.nextInt(colors.size());
        while (posicions.size() == 0) {
            switch (level) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 10:
                    posicionUn = r.nextInt(tamaño * tamaño);
                    posicions.add(posicionUn);
                    break;
                case 3:
                case 5:
                case 8:
                case 11:
                    posicionUn = r.nextInt(tamaño * tamaño);
                    posicionDous = r.nextInt(tamaño * tamaño);
                    posicions.add(posicionUn);
                    posicions.add(posicionDous);
                    if (posicionUn == posicionDous) {
                        posicions.clear();
                    }
                    break;
                case 6:
                case 9:
                case 12:
                    posicionUn = r.nextInt(tamaño * tamaño);
                    posicionDous = r.nextInt(tamaño * tamaño);
                    posicionTres = r.nextInt(tamaño * tamaño);
                    posicions.add(posicionUn);
                    posicions.add(posicionDous);
                    posicions.add(posicionTres);
                    if (posicionUn == posicionDous || posicionUn == posicionTres || posicionTres == posicionDous) {
                        posicions.clear();
                    }
                    break;

            }
            level = r.nextInt(12);
        }
        adapter.completar(tamaño, posicions, colorPoñer, width);
        fewDifferents = posicions.size();
        gridview.setAdapter(adapter);
    }

    public static ArrayList<String> arrayOfTags = new ArrayList<>();

    //this method to check the position de de player press
    public void comprobar(View v) {

        //check the positions of the player press
        if (posicionUn == (int) v.getTag()) {
            arrayOfTags.add("posicion:" + v.getTag());
            if (arrayOfTags.size() == fewDifferents) checkTheArray(arrayOfTags);
        } else if (posicionDous == (int) v.getTag()) {
            arrayOfTags.add("posicion:" + v.getTag());
            if (arrayOfTags.size() == fewDifferents) checkTheArray(arrayOfTags);
        } else if (posicionTres == (int) v.getTag()) {
            arrayOfTags.add("posicion:" + v.getTag());
            if (arrayOfTags.size() == fewDifferents) checkTheArray(arrayOfTags);
        } else {
            if (!puntuationTextView.getText().equals("0")) {
                puntos = puntos - 1;
                puntuationTextView.setText(puntos + "");
            }
            arrayOfTags.clear();
            niveles(level);
        }

        if (Integer.parseInt(bestScore.getText().toString()) < Integer.parseInt(puntuationTextView.getText().toString())) {
            bestTextView.setText(puntuationTextView.getText().toString());

        } else {
            bestTextView.setText(bestScore.getText().toString());
        }
    }

    public int puntos;

    //this method to cheking the array
    private void checkTheArray(ArrayList<String> arrayOfTags) {
        boolean correct = true;
        if (!time.getText().equals("00:00")) {
            // if exist a tag more than one time on the array,
            for (int i = 0; i < arrayOfTags.size(); i++) {
                for (int z = 0; z < arrayOfTags.size(); z++) {
                    if (arrayOfTags.get(i).equals(arrayOfTags.get(z)) && z != i) {
                        correct = false;//the value of the variable 'correct' change to false
                    }
                }
            }

            //if the value of the variable correct is true
            if (correct) {
                puntos += fewDifferents;
                puntuationTextView.setText(puntos + "");
                arrayOfTags.clear();
                if (level < 13) level++;
                niveles(level);
            } else {
                //but if the value of variable correct is false and the value of the variable texto isn´t 0 decrement one point
                if (!puntuationTextView.getText().equals("0")) {
                    puntuationTextView.setText(--puntos + "");
                }
                arrayOfTags.clear();
                niveles(level);
            }
        }
    }

    /**
     * Created by Marcos Vicente on 29/11/2015.
     */
    public class Temporizador extends AsyncTask<Void, Integer, Boolean> {
        private final int TEMPO_FINAL = 60;


        @Override
        protected Boolean doInBackground(Void... params) {
            for (int i = 1; i <= TEMPO_FINAL; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                publishProgress(i);

                if (isCancelled())
                    break;
            }
            return true;
        }

        int progreso = 60;
        boolean controlTempo = false;

        @Override
        protected void onProgressUpdate(Integer... values) {
            progreso = progreso - 1;
            if (time.getText().equals("00:10"))
                controlTempo = true;
            if (!controlTempo)
                time.setText("00:" + (progreso));
            else time.setText("00:0" + (progreso));
        }

        @Override
        protected void onPostExecute(Boolean result) {

            if (result) {
                Bundle args = new Bundle();
                args.putString("title", getString(R.string.gameOver));
                args.putString("puntuation", puntuationTextView.getText() + "");
                args.putString("bestPuntuation", bestTextView.getText() + "");
                args.putString("emailuser", emailGame);
                args.putString("nameuser", userNameGame);
                args.putString("photoGame", mCurrentPhotoPath);
                args.putString("typeUser", typeUser);
                args.putInt("levelUser", levelUser);

                double average = ((Double.parseDouble(puntuationTextView.getText() + "")) / 60);
                DecimalFormat df = new DecimalFormat("#.###");
                args.putString("average", df.format(average));

                EndGame_DialogFragment dialogFragment = EndGame_DialogFragment.newInstance(args);
                dialogFragment.setCancelable(false);
                dialogFragment.show(getFragmentManager(), "dialog");


            }
        }
    }
    // the end of temporizador

}
