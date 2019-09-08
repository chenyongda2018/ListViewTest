package com.example.chen.listviewtest.first_image_load;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.chen.listviewtest.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageAdapter extends ArrayAdapter {

    LruCache<String, BitmapDrawable> mCache;
    private ListView mListView;

    public ImageAdapter(@NonNull Context context, int resource, String[] objects) {
        super(context, resource, objects);
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mCache = new LruCache<String, BitmapDrawable>(cacheSize) {
            @Override
            protected int sizeOf(String key, BitmapDrawable value) {
                return value.getBitmap().getByteCount();
            }
        };
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String url = (String) getItem(position);
        View view;
        mListView = (ListView) parent;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_first_list_item, null);
        } else {
            view = convertView;
        }
        ImageView imageView = view.findViewById(R.id.item_imageView);
        imageView.setImageResource(R.drawable.ic_launcher_foreground);
        imageView.setTag(url);
        BitmapDrawable bd = getBitmapFromCache(url);
        if (bd == null) {
            ImageTask imageTask = new ImageTask();
            imageTask.execute(url);
        } else {
            imageView.setImageDrawable(bd);
        }

        return view;
    }

    private void addBitmapToCache(String key, BitmapDrawable bitmapDrawable) {
        if (getBitmapFromCache(key) == null) {
            mCache.put(key, bitmapDrawable);
        }
    }

    public BitmapDrawable getBitmapFromCache(String key) {
        return mCache.get(key);
    }


    class ImageTask extends AsyncTask<String, Void, BitmapDrawable> {

        String url;

        @Override
        protected BitmapDrawable doInBackground(String... strings) {
            url = strings[0];
            Bitmap bitmap = downloadImage(url);
            BitmapDrawable bd = new BitmapDrawable(getContext().getResources(), bitmap);
            addBitmapToCache(url, bd);
            return bd;
        }


        @Override
        protected void onPostExecute(BitmapDrawable bitmapDrawable) {
            ImageView imageView = (ImageView) mListView.findViewWithTag(url);
            if (imageView != null && bitmapDrawable != null) {
                imageView.setImageDrawable(bitmapDrawable);
            }
        }


        private Bitmap downloadImage(String imgUrl) {
            Bitmap bitmap = null;
            HttpURLConnection conn = null;

            try {
                URL url = new URL(imgUrl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5 * 1000);
                conn.setReadTimeout(10 * 1000);
                bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return bitmap;
        }
    }
}
