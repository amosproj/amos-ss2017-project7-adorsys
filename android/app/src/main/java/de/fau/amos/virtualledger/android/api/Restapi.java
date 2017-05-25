package de.fau.amos.virtualledger.android.api;

import java.util.List;

import de.fau.amos.virtualledger.dtos.BankAccess;
import de.fau.amos.virtualledger.dtos.BankAccessCredential;
import de.fau.amos.virtualledger.dtos.SessionData;
import de.fau.amos.virtualledger.android.model.UserCredential;
import de.fau.amos.virtualledger.dtos.LoginData;
import de.fau.amos.virtualledger.dtos.StringApiModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Simon on 07.05.2017. taken from https://adityaladwa.wordpress.com/2016/05/09/
 * dagger-2-with-retrofit-and-okhttp-and-gson/)
 */

public interface Restapi {

    /**
     * Endpoint for registering a new user by UserCredential
     *
     * @param credential
     * @return
     */
    @POST("/api/auth/register")
    Call<StringApiModel> register(@Body UserCredential credential);

    @POST("/api/auth/logout")
    Call<StringApiModel> logout(@Header("Authorization") String token);

    @POST("/api/auth/login")
    Call<SessionData> login(@Body LoginData loginData);

    @GET("/api/banking")
    Call<List<BankAccess>> getBankAccess(@Header("Authorization") String token);

    @POST("/api/banking")
    Call<Void> addBankAccess(@Header("Authorization") String token, @Body BankAccessCredential bankAccessCredential);

    @DELETE("/api/banking/{accessId}")
    Call<Void> deleteBankAccess(@Header("Authorization") String token, @Path("accessId") String accessId);

    @DELETE("/api/banking/{accessId}/{accountId}")
    Call<Void> deleteBankAccount(@Header("Authorization") String token, @Path("accessId") String accessId, @Path("accountId") String accountId);

}
