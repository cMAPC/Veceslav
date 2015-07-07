package com.example.devcolibri.taskv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {

    EditText editText;

    //=============================ERROR==========================================
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
    //=============================ON CREATE========================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView;
        final ListView listView;
        final ArrayList<User> usersList;
        final List<String> urlList = new ArrayList<String>();

        Button btn = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView);


        //=====================ON CLICK============================================
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (haveNetworkConnection()) {
                    if (!editText.getText().toString().matches("")) {

                        //======================ADAPTER===================
                        RestAdapter restAdapter = new RestAdapter.Builder()
                                .setEndpoint("https://api.github.com")
                                .build();
                        MethodsAPI service = restAdapter.create(MethodsAPI.class);


                        service.listUser(editText.getText().toString(), new Callback<List<User>>() {
                            @Override
                            public void success(List<User> users, Response response) {

                                ArrayList<User> usersList = new ArrayList<>();
                                UserAdapter adapter;
                                adapter = new UserAdapter(getBaseContext(), R.layout.row, usersList);
                                for (int i = 0; i < users.size(); i++) {
                                    User obj = new User();
                                    obj.setName("Name : " + users.get(i).getName());
                                    obj.setDescription("Description : " + users.get(i).getDescription());
                                    //obj.setUrl(users.get(i).getUrl());
                                    usersList.add(obj);
                                    urlList.add(i, users.get(i).getHtml_url());
                                }

                                listView.setAdapter(adapter);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(getApplicationContext(), "Enter a valide name !", Toast.LENGTH_SHORT).show();
                            }
                        });


                    } else {
                        Toast.makeText(getApplicationContext(), "Enter a name pleas in the text box !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No internet conection, try later !", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //=========================ON CLICK LIST=============================================
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle b = new Bundle();
                b.putString("URL", urlList.get(position));
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtras(b);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Item " + position, Toast.LENGTH_SHORT).show();

            }
        });


    }

}
