package devlrmve.atrapacor.com.atrapacor.Utils;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import devlrmve.atrapacor.com.atrapacor.Activities.Main_All_Scores;
import devlrmve.atrapacor.com.atrapacor.DialogsFragment.ScoreGame_DialogFragment;
import devlrmve.atrapacor.com.atrapacor.R;


/**
 * Created by Marco on 08/01/2016.
 */
public class ScoresAdapter extends
        RecyclerView.Adapter<ScoresAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView score_TextView;
        public TextView hour_TextView;
        public TextView date_TextView;
        public View divider_View;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            itemView.setOnClickListener(this);
            score_TextView = (TextView) itemView.findViewById(R.id.score_item);
            hour_TextView = (TextView) itemView.findViewById(R.id.hour_item);
            date_TextView = (TextView) itemView.findViewById(R.id.date_item);
            divider_View = itemView.findViewById(R.id.divider_date_item);
        }

        @Override
        public void onClick(View v) {
            Scores score = mScores.get(getPosition());
            Bundle args = new Bundle();
            args.putString("title", "Score");
            args.putString("puntuation", score.getScore() + "");
            args.putString("bestPuntuation", "-");
            args.putString("emailuser", mEmail);
            args.putString("date", CurrentDate.getDateString(score.getDate()));
            args.putInt("level", mLevel);
            args.putString("typeUser", "google");

            double average = ((Double.parseDouble(score.getScore() + "")) / 60);
            DecimalFormat df = new DecimalFormat("#.###");
            args.putString("average", df.format(average));
            ScoreGame_DialogFragment dialogFragment = ScoreGame_DialogFragment.newInstance(args);
            dialogFragment.show(mFragmentManager, "dialog");
        }
    }

    // Store a member variable for the contacts
    private static List<Scores> mScores;
    private List<String> mTipe_View;
    private static FragmentManager mFragmentManager;
    private static String mEmail;
    private static int mLevel;

    // Pass in the contact array into the constructor
    public ScoresAdapter(List<Scores> scores, List<String> tipe_view,
                         FragmentManager fragmentManager, String email_all_score, int level) {
        mScores = scores;
        mTipe_View = tipe_view;
        mFragmentManager = fragmentManager;
        mEmail = email_all_score;
        mLevel = level;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ScoresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View scoreView = inflater.inflate(R.layout.item_score, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(scoreView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    String data_to_compare = "";

    @Override
    public void onBindViewHolder(ScoresAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Scores scores = mScores.get(position);

        // Set item views based on the data model
        TextView score_view = viewHolder.score_TextView;
        TextView hour_view = viewHolder.hour_TextView;
        TextView date_view = viewHolder.date_TextView;
        View divider_view = viewHolder.divider_View;

        date_view.setVisibility(View.GONE);
        divider_view.setVisibility(View.GONE);

        if (mTipe_View.get(position).equalsIgnoreCase("TYPE_VIEW")) {
            data_to_compare = CurrentDate.dateFormatString(scores.getDate());
            date_view.setVisibility(View.VISIBLE);
            divider_view.setVisibility(View.VISIBLE);
            String scoreDate = "";
            if (CurrentDate.isToday(scores.getDate())) {
                scoreDate = Main_All_Scores.today;
            } else if (CurrentDate.isYesterday(scores.getDate())) {
                scoreDate = Main_All_Scores.yesterday;
            } else {
                scoreDate = CurrentDate.dateFormatString(scores.getDate());
            }

            date_view.setText(scoreDate);
        }

        score_view.setText(scores.getScore() + "");
        hour_view.setText(CurrentDate.getHourString(scores.getDate()));

    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return mScores.size();
    }
}