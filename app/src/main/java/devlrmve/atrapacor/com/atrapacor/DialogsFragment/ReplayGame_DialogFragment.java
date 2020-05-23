package devlrmve.atrapacor.com.atrapacor.DialogsFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import devlrmve.atrapacor.com.atrapacor.Activities.Main_Game;
import devlrmve.atrapacor.com.atrapacor.R;


/**
 * Created by Marco on 12/12/2015.
 */
public class ReplayGame_DialogFragment extends DialogFragment {
    public static ReplayGame_DialogFragment newInstance(Bundle savedInstanceState) {
        ReplayGame_DialogFragment fragment = new ReplayGame_DialogFragment();
        fragment.setArguments(savedInstanceState);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        String title = getArguments().getString("title");
        final String userEmail = getArguments().getString("emailuser");
        final String username=getArguments().getString("userName");
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(R.string.menssage_restartGame)
                .setNegativeButton(R.string.buttonCancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .setPositiveButton(R.string.buttonAccept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Context context = getActivity();
                        Intent mainGame = new Intent(context, Main_Game.class);
                        mainGame.putExtra("email", userEmail);
                        mainGame.putExtra("username",username);
                        mainGame.putExtra("photoGame",getArguments().getString("photoGame"));
                        mainGame.putExtra("typeUser",getArguments().getString("typeUser"));
                        mainGame.putExtra("levelUser", getArguments().getInt("levelUser"));
                        Main_Game.arrayOfTags.clear();
                        context.startActivity(mainGame);
                        getActivity().finish();
                    }
                })
                .create();
    }
}
