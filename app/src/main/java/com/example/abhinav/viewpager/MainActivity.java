package com.example.abhinav.viewpager;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    static final String TAG = MainActivity.class.getName();
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        text.setMovementMethod(new ScrollingMovementMethod());
        ApiService api = RetroClient.getApiService();
        Call<ContactList> call = api.getMyJSON();
        call.enqueue(new Callback<ContactList>() {


            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if(response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    ArrayList<Contact> contactList = response.body().getContacts();
                    try {
                        Log.i(TAG,"contact \n"+new ObjectMapper().writeValueAsString(contactList));
                        text.setText(new ObjectMapper().writeValueAsString(contactList));
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }


                } else {
                    Snackbar.make(findViewById(R.id.parentLayout), R.string.down, Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {

            }
        });
    }
}

