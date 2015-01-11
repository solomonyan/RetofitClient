package com.visomind.retofitclient;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.visomind.retofitclient.model.Customer;
import com.visomind.retofitclient.service.CustomerService;

import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;


public class MainActivity extends ActionBarActivity {

    private static final String API_URL = "http://183.178.180.13:8080";
    private Gson gson;
    private CustomerService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        RestAdapter rest = new RestAdapter.Builder().setEndpoint(API_URL).setConverter(new GsonConverter(gson)).build();
        service = rest.create(CustomerService.class);

        Button btngetCustomerById = (Button) findViewById(R.id.btnGetCustomerById);
        btngetCustomerById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                service.getUserById(1, new Callback<Customer>() {
                    @Override
                    public void success(Customer customer, Response response) {
                        Log.v("Customer", customer.toString());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v("RetrofitError", error.toString());
                    }
                });
            }
        });

        Button btnCreateCustomer = (Button) findViewById(R.id.btnCreateCustomer);
        btnCreateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.createUser(new Customer(4, "Solomon"), new Callback<Customer>() {
                    @Override
                    public void success(Customer customer, Response response) {
                        Log.v("Customer", customer.toString());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v("RetrofitError", error.toString());
                    }
                });
            }
        });

        Button btnUpdateCustomer = (Button) findViewById(R.id.btnUpdateCustomer);
        btnUpdateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.updateCustomer(new Customer(1, "Solomon"), new Callback<Customer>() {
                    @Override
                    public void success(Customer customer, Response response) {
                        Log.v("Customer", customer.toString());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v("RetrofitError", error.toString());
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
