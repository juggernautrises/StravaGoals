package com.ashnayar.stravagoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = new Intent(this, StavaGoalsMain.class);
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization:", "Bearer " + GoalData.auth_key);

        RequestParams params = new RequestParams();

        AsyncHttpResponseHandler rhandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {

                try {
                    String tmp = new String(bytes, "UTF-8");
                    intent.putExtra("athlete",tmp);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                    Log.d("smashy", e.getMessage());
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        };
        String url = "https://www.strava.com/api/v3/athletes/" + GoalData.strava_id + "/stats";
        client.get(url, rhandler);
    }

}
