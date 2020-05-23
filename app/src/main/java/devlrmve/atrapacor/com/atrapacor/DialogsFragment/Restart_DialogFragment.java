package devlrmve.atrapacor.com.atrapacor.DialogsFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.SQLException;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

import devlrmve.atrapacor.com.atrapacor.Database.Senteces;
import devlrmve.atrapacor.com.atrapacor.R;


/**
 * Created by marcos_vicente on 24/01/16.
 */
public class Restart_DialogFragment extends DialogFragment {
    public static Restart_DialogFragment newInstance(Bundle savedInstanceState) {
        Restart_DialogFragment fragment = new Restart_DialogFragment();
        fragment.setArguments(savedInstanceState);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        String title = getArguments().getString("title");
        final String userEmail = getArguments().getString("emailuser");
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage("You can't undo this action!")
                .setNegativeButton(R.string.buttonCancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton(R.string.buttonAccept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            if (Senteces.deleteRecords(getActivity(), userEmail)
                                    && Senteces.deleteScores(getActivity(), userEmail)) {
                                Snackbar.make(getActivity().findViewById(R.id.tools_layout), "Borrado correctamente",
                                        Snackbar.LENGTH_LONG)
                                        .show();
                            } else {
                                Snackbar.make(getActivity().findViewById(R.id.tools_layout), "Nada que borrar",
                                        Snackbar.LENGTH_LONG)
                                        .show();
                            }
                        } catch (SQLException ex) {
                            Snackbar.make(getActivity().findViewById(R.id.tools_layout), "Un error interno no permitio borrar correctamente los registros",
                                    Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    }
                })
                .create();
    }
}
