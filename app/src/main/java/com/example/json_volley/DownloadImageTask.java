package com.example.json_volley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String,Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... strings) {
        String s1 = strings[0];
        InputStream in;
        try {
            URL myurl = new URL(s1);
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(20000);
            conn.setRequestMethod("GET");
            conn.connect();

            in = conn.getInputStream();

            Bitmap myMap = BitmapFactory.decodeStream(in);
            return myMap;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        MainActivity.myimage.setImageBitmap(bitmap);
    }
}