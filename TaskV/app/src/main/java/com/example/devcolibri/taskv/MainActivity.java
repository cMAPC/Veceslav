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

    private EditText editText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<User> usersList = new ArrayList<>();

        Button btn = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        final ListView listView = (ListView) findViewById(R.id.listView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(everyThingIsValid()){
                    executeHTTPRequest(editText, usersList, listView);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displayRepoUrl(position, usersList);
            }
        });
    }

    private void displayRepoUrl(int position, ArrayList<User> usersList) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("URL", usersList.get(position).getHtml_url());
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Item " + position, Toast.LENGTH_SHORT).show();
    }

    private boolean everyThingIsValid() {
        if (!haveNetworkConnection()) {
            Toast.makeText(getApplicationContext(), "No internet conection, try later !", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(editText.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(), "Enter a name pleas in the text box !", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void executeHTTPRequest(EditText editText, final ArrayList<User> usersList, final ListView listView) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .build();
        MethodsAPI service = restAdapter.create(MethodsAPI.class);


        service.listUser(editText.getText().toString(), new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                UserAdapter adapter = new UserAdapter(getBaseContext(), users);
                usersList.addAll(users);
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Enter a valide name !", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
