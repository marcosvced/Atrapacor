package devlrmve.atrapacor.com.atrapacor.DialogsFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import devlrmve.atrapacor.com.atrapacor.Database.Senteces;
import devlrmve.atrapacor.com.atrapacor.R;


public class ScoreGame_DialogFragment extends DialogFragment {

    public static ScoreGame_DialogFragment newInstance(Bundle savedInstanceState) {
        ScoreGame_DialogFragment fragment = new ScoreGame_DialogFragment();
        fragment.setArguments(savedInstanceState);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(infService);
        // Inflamos o compoñente composto definido no XML
        View inflador = li.inflate(R.layout.end_game_layout_dialog, null);

        // Buscamos os compoñentes dentro do Diálogo
        final TextView puntuation = (TextView) inflador.findViewById(R.id.puntuacionDialogo);
        puntuation.setText(getArguments().getString("puntuation"));
        final TextView bestPuntuation = (TextView) inflador.findViewById(R.id.mejorDialogo);
        String bestScore = Senteces.readBestScoreDate(getActivity(), getArguments().getString("emailuser"), getArguments().getInt("level")
                , getArguments().getString("date"));
        bestPuntuation.setText(bestScore);
        final TextView average = (TextView) inflador.findViewById(R.id.aciertosDialogo);
        average.setText(getArguments().getString("average"));

        final String title = getArguments().getString("title");

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setView(inflador)
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }
}