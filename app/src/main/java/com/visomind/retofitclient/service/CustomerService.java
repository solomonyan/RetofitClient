package com.visomind.retofitclient.service;

import com.visomind.retofitclient.model.Customer;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by solomonyan on 12/1/15.
 */
public interface CustomerService {
    @GET("/customers/{id}")
    void getUserById(@Path("id") int customerId, Callback<Customer> cb);

    @POST("/customers")
    void createUser(@Body Customer customer, Callback<Customer> cb);

    @PUT("/customers")
    void updateCustomer(@Body Customer customer, Callback<Customer> cb);
}
