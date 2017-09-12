package com.example.dauda.getgithubusers;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //the api query
    private static final String URL_DATA = "https://api.github.com/search/users?q=language:java+location:lagos";

    //creating instance of RecyclerView
    private RecyclerView recyclerView;

    //creating instance of RecyclerView.Adapter
    private RecyclerView.Adapter adapter;
    //creating instance of List of Users
    private List<Users> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the recyclerView instance
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adds the divider to the recyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        //Initializing the users; an instance of List<Users>
        users = new ArrayList<>();

        //calling the loadUrlData
        loadUrlData();
    }

    /**
     * Loads the data from the URL_Data and format it into the
     *      list of users
     */
    private void loadUrlData() {

        //instantiating the progressDialog
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        // requesting for list of developers vis URL_DATA
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {

            /**
             * gets the required data from the response i.e developer name(login)
             *      ,github url (html_url), developer avatar_url into users arraylist
             * @param response
             */
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("items");

                    for (int i = 0; i < array.length(); i++){

                        JSONObject jo = array.getJSONObject(i);

                        Users developer = new Users(jo.getString("login"), jo.getString("html_url"),
                                jo.getString("avatar_url"));
                        users.add(developer);

                    }

                    //get the list of users data into adapter
                    adapter = new UserAdapter(users, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, " No internet connection "  , Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                //error.toString()
            }
        });

        //
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
