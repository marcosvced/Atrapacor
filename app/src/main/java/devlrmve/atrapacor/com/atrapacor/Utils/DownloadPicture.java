package devlrmve.atrapacor.com.atrapacor.Utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Marco on 19/12/2015.
 */
public class DownloadPicture {
    static File rutaArquivo;
    static File rutaArquivoImagen;
    public static String mCurrentPhotoPath;


    /**
     * descarga
     */
    public static void descargarArquivo(String IMAXE_DESCARGAR, String fioEmail, File rutaArquivoRecibida) {
        URL url = null;
        try {
            url = new URL(IMAXE_DESCARGAR);
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return;
        }


        HttpURLConnection conn = null;
        String nomeArquivo = fioEmail;
        rutaArquivo = rutaArquivoRecibida;

        rutaArquivoImagen = new File(rutaArquivo, nomeArquivo + ".jpg");

        mCurrentPhotoPath = "" + rutaArquivoImagen.getAbsolutePath();
        try {

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);     /* milliseconds */
            conn.setConnectTimeout(15000);  /* milliseconds */
            conn.setRequestMethod("POST");
            conn.setDoInput(true);          /* Indicamos que a conexión vai recibir datos */

            conn.connect();

            int response = conn.getResponseCode();
            if (response != HttpURLConnection.HTTP_OK) {
                return;
            }
            OutputStream os = new FileOutputStream(rutaArquivoImagen);
            InputStream in = conn.getInputStream();
            byte data[] = new byte[1024];   // Buffer a utilizar
            int count;
            while ((count = in.read(data)) != -1) {
                os.write(data, 0, count);
            }
            os.flush();
            os.close();
            in.close();
            conn.disconnect();
            Log.i("COMUNICACION", "ACABO");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Log.e("COMUNICACION", e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("COMUNICACION", e.getMessage());
        }
    }

    public static Boolean checkNetwork(ConnectivityManager conection) {
        NetworkInfo networkInfo = null;

        ConnectivityManager connMgr = conection;
        networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;

        }
        return false;
    }
}
