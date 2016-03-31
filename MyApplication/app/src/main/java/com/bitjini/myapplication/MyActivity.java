package com.bitjini.myapplication;

/**
 * Created by bitjini on 28/3/16.
 */

import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyActivity extends Activity {

    ListView listView;
    final static String TAG = "MainActivity";
    public RelativeLayout mlayout;
//    Handler mHandler = new Handler();
     EditText textSearch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.twitter_tweets_list);
        listView = (ListView) findViewById(R.id.list);
        Button searchBtn = (Button) findViewById(R.id.searchBtn);
       textSearch = (EditText) findViewById(R.id.enterText);

        mlayout= (RelativeLayout)findViewById(R.id.relativeLayout1);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // hide the keyboard
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null){
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);}

                String query = textSearch.getText().toString();

                AndroidNetworkUtility androidNetworkUtility = new AndroidNetworkUtility();
                if (androidNetworkUtility.isConnected(getApplicationContext())) {
                    new TwitterAsyncTask().execute(query, this);
//                    textSearch.setText("");
                } else {
                    Toast.makeText(MyActivity.this, "Network not Available!",Toast.LENGTH_LONG).show();
                }


            }
        });
        if(textSearch.getText().toString()!=null) {
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Start();
                }
            }, 0, 5000);//put here time 1000 milliseconds=1 second
        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                while (true) {
//                    try {
//                        Thread.sleep(10000);
//                        mHandler.post(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                // TODO Auto-generated method stub
//                                // Write your code here to update the UI.
//                                Start();
//                            }
//                        });
//                    } catch (Exception e) {
//                        // TODO: handle exception
//                    }
//                }
//            }
//        }).start();
    }
    public void Start() {
        Log.e("On start called..","");
        String query = textSearch.getText().toString();
        AndroidNetworkUtility androidNetworkUtility = new AndroidNetworkUtility();
        if (androidNetworkUtility.isConnected(getApplicationContext())) {
            new TwitterAsyncTask().execute(query, this);
//            textSearch.setText("");
        } else {
            Toast.makeText(MyActivity.this, "Network not Available!", Toast.LENGTH_LONG).show();
        }
    }
    class TwitterAsyncTask extends AsyncTask<Object, Void, ArrayList<Search>> {
        ListActivity callerActivity;

        final static String TWITTER_API_KEY = "H0vLNqlGfVXEQ7QeXML63lSMb";
        final static String TWITTER_API_SECRET = "t1pbXaepA3LsYUxM35061Mc5cMFefpUBXyU7QAwCvotxtF71yp";

        @Override
        protected ArrayList<Search> doInBackground(Object... params) {
            ArrayList<Search> twitterTweets = null;
//            callerActivity = (ListActivity) params[1];
//            listView = (ListView) params[1];
            if (params.length > 0) {
                try {
                    TwitterAPI twitterAPI = new TwitterAPI(TWITTER_API_KEY, TWITTER_API_SECRET);
                    twitterTweets = twitterAPI.getTwitterTweets(params[0].toString());
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
            return twitterTweets;
        }

        @Override
        protected void onPostExecute(ArrayList<Search> twitterTweets) {

            MyClassAdapter adapter =
                    new MyClassAdapter(MyActivity.this, R.layout.twitter_tweets_list, twitterTweets);
            listView.setAdapter(adapter);
//            ListView lv = callerActivity.getListView();
//            lv.setDividerHeight(0);
            //lv.setDivider(this.getResources().getDrawable(android.R.color.transparent));
//            lv.setBackgroundColor(callerActivity.getResources().getColor(Color.BLUE));
        }
    }

    class MyClassAdapter extends ArrayAdapter<Search> {

        private String Red="red";
        private String Blue="blue";
        private String Green="green";
        private String Yellow="yellow";
        private String Orange;
        private String Pink="pink";

        private  class ViewHolder {
            private TextView itemView;
            private ImageView imageView;
            private RelativeLayout layout;

        }

        ViewHolder viewHolder=new ViewHolder();

        public MyClassAdapter(Context context, int textViewResourceId, ArrayList<Search> items) {
            super(context, textViewResourceId, items);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(this.getContext())
                        .inflate(R.layout.activity_main, parent, false);


                viewHolder.itemView = (TextView) convertView.findViewById(R.id.listTextView);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
                viewHolder.layout= (RelativeLayout)findViewById(R.id.relativeLayout2);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Search item = getItem(0);
            if (item != null) {
                // My layout has only one TextView
                // do whatever you want with your string and long
//                viewHolder.itemView.setText(item.getText(),item.getName());
                viewHolder.itemView.setText(String.format("%s :- %s :- %s", item.getText(), item.getName(),item.getDateCreated()));
                viewHolder.imageView.setTag(String.valueOf(item.getProfile_image_url()));
                new DownloadImagesTask().execute(viewHolder.imageView);



               String example=item.getText();
                    if (example.contains(Red)) {
                        Log.e("example", "" + Red);
                        mlayout.setBackgroundColor(Color.RED);
//                        viewHolder.layout.setBackgroundColor(Color.RED);
                    }
                    if (example.contains(Green)) {
                        Log.e("example", "" + Green);
                        mlayout.setBackgroundColor(Color.GREEN);
//                        viewHolder.layout.setBackgroundColor(Color.GREEN);
                    }
                    if (example.contains(Yellow)) {
                        Log.e("example", "" +Yellow);
                        mlayout.setBackgroundColor(Color.YELLOW);
//                        viewHolder.layout.setBackgroundColor(Color.YELLOW);
                    }
                    if (example.contains(Pink)) {
                        Log.e("example", "" + Pink);
                        mlayout.setBackgroundColor(Color.CYAN);
//                        viewHolder.layout.setBackgroundColor(Color.CYAN);
                    }
                    if (example.contains(Blue)) {
                        Log.e("example", "" + Blue);
                        mlayout.setBackgroundColor(Color.BLUE);
//                        viewHolder.layout.setBackgroundColor(Color.BLUE);
                    }


//                if(values.toString().equals(Red))
//                {
//
//// set the color
//                    viewHolder.mlayout.setBackgroundColor(Color.RED);
//
//                }


            }

            return convertView;
        }
    }
}
class AndroidNetworkUtility {

   public boolean isConnected(Context ctx){
       boolean flag = false;
       ConnectivityManager connectivityManager =
               (ConnectivityManager) ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
       NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

       if (networkInfo != null && networkInfo.isConnected()) {
           flag = true;
       }
       return flag;
   }
}

class HttpUtil {

   public String getHttpResponse(HttpRequestBase request) {
       String result = null;
       try {


          HttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
           HttpResponse httpResponse = httpClient.execute(request);


           int statusCode = httpResponse.getStatusLine().getStatusCode();
           String reason = httpResponse.getStatusLine().getReasonPhrase();
           StringBuilder sb = new StringBuilder();
           if (statusCode == 200) {
               HttpEntity entity = httpResponse.getEntity();
               InputStream inputStream = entity.getContent();
               BufferedReader bReader = new BufferedReader(
                       new InputStreamReader(inputStream, "UTF-8"), 8);
               String line = null;
               while ((line = bReader.readLine()) != null) {
                   sb.append(line);
               }
           } else {
               sb.append(reason);
           }
           result = sb.toString();
           Log.e("response :",""+result);
       } catch (UnsupportedEncodingException ex) {
       } catch (ClientProtocolException ex1) {
       } catch (IOException ex2) {
       }
       return result;
   }
}