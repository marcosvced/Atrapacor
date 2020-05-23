package devlrmve.atrapacor.com.atrapacor.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro2;

import devlrmve.atrapacor.com.atrapacor.R;
import devlrmve.atrapacor.com.atrapacor.Utils.SampleSlide;


public class SecondLayoutIntro extends AppIntro2 {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.intro_2));
        addSlide(SampleSlide.newInstance(R.layout.intro2_2));
        addSlide(SampleSlide.newInstance(R.layout.intro3_2));
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, Main_Tools.class);
        startActivity(intent);
    }

    @Override
    public void onDonePressed() {
        //loadMainActivity();
        finish();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onSlideChanged() {

    }

    public void getStarted(View v){
        loadMainActivity();
    }
}
