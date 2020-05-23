package devlrmve.atrapacor.com.atrapacor.Utils;

import java.util.ArrayList;

import devlrmve.atrapacor.com.atrapacor.R;


/**
 * Created by Marco on 23/12/2015.
 */
public class ResourceLevels {
    public static ArrayList colores = new ArrayList<>();
    public static ArrayList coloresAleatorios = new ArrayList<>();

    public static ArrayList coloresArrayRandomLevels(int level){
        switch (level){
            case 1:
                return coloresArrayRandomLevelOne();
            case 2:
                return coloresArrayRandomLevelTwo();
            case 3:
                return coloresArrayRandomLevelThree();
        }
        return null;
    }



    public static   ArrayList coloresArray() {
        colores.add(R.drawable.teal);
        colores.add(R.drawable.red);
        colores.add(R.drawable.orange);
        colores.add(R.drawable.green);
        colores.add(R.drawable.purple);
        colores.add(R.drawable.blue);
        colores.add(R.drawable.amber);
        colores.add(R.drawable.brown);
        colores.add(R.drawable.cyan);
        colores.add(R.drawable.indigo);
        colores.add(R.drawable.greyblue);
        colores.add(R.drawable.pink);
        colores.add(R.drawable.lightgreen);
        colores.add(R.drawable.ligthblue);
        colores.add(R.drawable.deeppurple);
        colores.add(R.drawable.deeporange);


        return colores;
    }

    private static ArrayList coloresArrayRandomLevelOne() {
        coloresAleatorios.add(R.drawable.teal_random_one);
        coloresAleatorios.add(R.drawable.red_random_one);
        coloresAleatorios.add(R.drawable.orange_random_one);
        coloresAleatorios.add(R.drawable.green_random_one);
        coloresAleatorios.add(R.drawable.purple_random_one);
        coloresAleatorios.add(R.drawable.blue_random_one);
        coloresAleatorios.add(R.drawable.amber_random_one);
        coloresAleatorios.add(R.drawable.brown_random_one);
        coloresAleatorios.add(R.drawable.cyan_random_one);
        coloresAleatorios.add(R.drawable.indigo_random_one);
        coloresAleatorios.add(R.drawable.greyblue_random_one);
        coloresAleatorios.add(R.drawable.pink_random_one);
        coloresAleatorios.add(R.drawable.lightgreen_random_one);
        coloresAleatorios.add(R.drawable.lightblue_random_one);
        coloresAleatorios.add(R.drawable.deeppurple_random_one);
        coloresAleatorios.add(R.drawable.deeporange_random_one);

        return coloresAleatorios;

    }
    private static ArrayList coloresArrayRandomLevelTwo() {
        coloresAleatorios.add(R.drawable.teal_random_two);
        coloresAleatorios.add(R.drawable.red_random_two);
        coloresAleatorios.add(R.drawable.orange_random_two);
        coloresAleatorios.add(R.drawable.green_random_two);
        coloresAleatorios.add(R.drawable.purple_random_two);
        coloresAleatorios.add(R.drawable.blue_random_two);
        coloresAleatorios.add(R.drawable.amber_random_two);
        coloresAleatorios.add(R.drawable.brown_random_two);
        coloresAleatorios.add(R.drawable.cyan_random_two);
        coloresAleatorios.add(R.drawable.indigo_random_two);
        coloresAleatorios.add(R.drawable.greyblue_random_two);
        coloresAleatorios.add(R.drawable.pink_random_two);
        coloresAleatorios.add(R.drawable.lightgreen_random_two);
        coloresAleatorios.add(R.drawable.lightblue_random_two);
        coloresAleatorios.add(R.drawable.deeppurple_random_two);
        coloresAleatorios.add(R.drawable.deeporange_random_two);
        return coloresAleatorios;

    }
    private static ArrayList coloresArrayRandomLevelThree() {
        coloresAleatorios.add(R.drawable.teal_random_three);
        coloresAleatorios.add(R.drawable.red_random_three);
        coloresAleatorios.add(R.drawable.orange_random_three);
        coloresAleatorios.add(R.drawable.green_random_three);
        coloresAleatorios.add(R.drawable.purple_random_three);
        coloresAleatorios.add(R.drawable.blue_random_three);
        coloresAleatorios.add(R.drawable.amber_random_three);
        coloresAleatorios.add(R.drawable.brown_random_three);
        coloresAleatorios.add(R.drawable.cyan_random_three);
        coloresAleatorios.add(R.drawable.indigo_random_three);
        coloresAleatorios.add(R.drawable.greyblue_random_three);
        coloresAleatorios.add(R.drawable.pink_random_three);
        coloresAleatorios.add(R.drawable.lightgreen_random_three);
        coloresAleatorios.add(R.drawable.lightblue_random_three);
        coloresAleatorios.add(R.drawable.deeppurple_random_three);
        coloresAleatorios.add(R.drawable.deeporange_random_three);
        return coloresAleatorios;

    }
}
