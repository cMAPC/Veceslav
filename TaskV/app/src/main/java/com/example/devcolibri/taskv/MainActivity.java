package com.example.devcolibri.taskv;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn;
        final EditText editText;
        final TextView textView;
        btn = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

        final ArrayList<User> usersList = new ArrayList<>();





        // button function
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("https://api.github.com")
                        .build();

                MethodsAPI service = restAdapter.create(MethodsAPI.class);
                //octocat

                service.listUser(editText.getText().toString(), new Callback<List<User>>() {
                    @Override
                    public void success(List<User> users, Response response) {
                       // Log.d("TAG", users.get(0).getName() + ", " + users.get(0).getDescription());
                        textView.setText(users.get(0).getName() + ", " + users.get(0).getDescription());
                        for(int i = 0; i < users.size() ; i ++){
                            User obj = new User();
                            obj.setName(users.get(i).getName());
                            obj.setDescription(users.get(i).getDescription());
                            obj.setUrl(users.get(i).getUrl());
                            usersList.add(obj);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });


            }
        });






    }







}
