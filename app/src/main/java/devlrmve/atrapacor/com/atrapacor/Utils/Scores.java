package devlrmve.atrapacor.com.atrapacor.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Marco on 08/01/2016.
 */
public class Scores {
    private int mScore;
    private Date mDate;
    private int mlevel;

    public Scores(Date date, int score, int level) {
        mScore = score;
        mDate = date;
        mlevel = level;
    }

    public int getScore() {
        return mScore;
    }

    public Date getDate() {
        return mDate;
    }


    public static List<Scores> createScoreList(ArrayList<Scores> list) {
        List<Scores> scores = list;
        return scores;
    }
}
