package devlrmve.atrapacor.com.atrapacor.Utils;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

import devlrmve.atrapacor.com.atrapacor.Activities.Main_Game;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] mThumbIds;
    private int i;
    GridView.LayoutParams params;
    Main_Game mainActivity = new Main_Game();
    private ArrayList colores = mainActivity.colors;
    private ArrayList coloresAleatorios = mainActivity.colorsRandom;


    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter

    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            button = new Button(mContext);
            button.setLayoutParams(params);
            button.setTag(i++);
        } else {
            button = (Button) convertView;
        }
        button.setBackgroundResource(mThumbIds[position]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.comprobar(v);
                v.setBackgroundResource(color);
            }
        });
        return button;
    }

    int color;
    int colorAleatorio;

    public void completar(int tamaño, ArrayList<Integer> posicions, int colorPoñer, int width) {
        color = (int) colores.get(colorPoñer);
        colorAleatorio = (int) coloresAleatorios.get(colorPoñer);
        mThumbIds = new Integer[tamaño * tamaño];
        for (int i = 0; i < mThumbIds.length; i++) {
            mThumbIds[i] = color;
        }
        for (int posicion : posicions) {
            mThumbIds[posicion] = colorAleatorio;
        }
        params = new GridView.LayoutParams(((width) / tamaño), ((width) / tamaño));
        i = 0;
    }
}
