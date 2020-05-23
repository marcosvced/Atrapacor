package devlrmve.atrapacor.com.atrapacor.DialogsFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import devlrmve.atrapacor.com.atrapacor.Activities.Main_Game;
import devlrmve.atrapacor.com.atrapacor.Database.Senteces;
import devlrmve.atrapacor.com.atrapacor.R;


public class EndGame_DialogFragment extends DialogFragment {

    public static EndGame_DialogFragment newInstance(Bundle savedInstanceState) {
        EndGame_DialogFragment fragment = new EndGame_DialogFragment();
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

        final String title = getArguments().getString("title");

        // Buscamos os compoñentes dentro do Diálogo
        final TextView puntuation = (TextView) inflador.findViewById(R.id.puntuacionDialogo);
        final TextView bestPuntuation = (TextView) inflador.findViewById(R.id.mejorDialogo);
        final TextView average = (TextView) inflador.findViewById(R.id.aciertosDialogo);

        //establecemos os valores
        puntuation.setText(getArguments().getString("puntuation"));
        bestPuntuation.setText(getArguments().getString("bestPuntuation"));
        average.setText(getArguments().getString("average"));


        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setView(inflador)
                .setPositiveButton(R.string.newGameButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int levelUser = checkLevel(Integer.parseInt(getArguments().getString("puntuation")), getArguments().getString("emailuser"), getArguments().getInt("levelUser"));

                        Senteces.insertRecords(getActivity(), getArguments().getString("emailuser"), Integer.parseInt(getArguments().getString("puntuation")));
                        Senteces.insertIntoScores(getActivity(), getArguments().getString("puntuation"), getArguments().getString("emailuser"), levelUser);

                        Context context = getActivity();
                        Intent mainGame = new Intent(context, Main_Game.class);

                        mainGame.putExtra("email", getArguments().getString("emailuser"));
                        mainGame.putExtra("username", getArguments().getString("nameuser"));
                        mainGame.putExtra("photoGame", getArguments().getString("photoGame"));
                        mainGame.putExtra("typeUser", getArguments().getString("typeUser"));
                        mainGame.putExtra("levelUser", getArguments().getInt("levelUser"));

                        //limpiamos o array de tags para que estea vacio na proxima partida
                        Main_Game.arrayOfTags.clear();

                        //e iniciamos outra vez o xogo
                        context.startActivity(mainGame);
                        getActivity().finish();

                    }
                })
                .setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int levelUser = checkLevel(Integer.parseInt(getArguments().getString("puntuation")), getArguments().getString("emailuser"), getArguments().getInt("levelUser"));

                        Senteces.insertRecords(getActivity(), getArguments().getString("emailuser"), Integer.parseInt(getArguments().getString("puntuation")));
                        Senteces.insertIntoScores(getActivity(), getArguments().getString("puntuation"), getArguments().getString("emailuser"), levelUser);

                        //limpiamos o array de tags para que estea vacio na proxima partida
                        Main_Game.arrayOfTags.clear();

                        //finalizamos a activity do xogo
                        getActivity().finish();
                    }
                })
                .create();
    }

    private int checkLevel(int bestScore, String email, int level) {
        int levelUser = level;
        int totalSocres = Integer.parseInt(Senteces.readTotalScores(getActivity(), email, levelUser));
        if (levelUser == 1 && bestScore > 110 || levelUser == 2 && totalSocres + bestScore > 2500 ) {
            if (Senteces.readScores(getActivity(), email, 2).size() == 0) {
                Senteces.insertIntoScores(getActivity(), "0", email, 2);
            }
        } else if (levelUser == 2 && bestScore > 100 || levelUser == 2 && totalSocres + bestScore > 1500) {
            if (Senteces.readScores(getActivity(), email, 3).size() == 0) {
                Senteces.insertIntoScores(getActivity(), "0", email, 3);
            }
        }
        return levelUser;
    }

}