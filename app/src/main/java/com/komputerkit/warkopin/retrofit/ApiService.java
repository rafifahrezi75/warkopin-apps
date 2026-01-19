package com.komputerkit.warkopin.retrofit;

import com.komputerkit.warkopin.cart.CartModel;
import com.komputerkit.warkopin.historydetail.HistoryDetailModel;
import com.komputerkit.warkopin.historyproses.HistoryProsesModel;
import com.komputerkit.warkopin.historydone.HistoryDoneModel;
import com.komputerkit.warkopin.menu.MenuModel;
import com.komputerkit.warkopin.response.LoginResponse;
import com.komputerkit.warkopin.response.MessageResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @FormUrlEncoded
    @POST("login") // Ganti dengan path endpoint login Anda
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );
    @Headers({
            "Accept:application/json",
            "Content-Type:application/json"
    })
    @POST("logout")
    Call<Void> logout(@Header("Authorization") String bearerToken);
    @FormUrlEncoded
    @POST("registerotp")
    Call<MessageResponse> registerOtp(
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("registerverifyotp")
    Call<MessageResponse> registerVerifyOtp(
            @Field("email") String email,
            @Field("name") String name,
            @Field("otp") String otp,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("sendotpforgotpassword")
    Call<MessageResponse> forgotPassOtp(
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("otpforgotpassword")
    Call<MessageResponse> forgotPassword(
            @Field("email") String email,
            @Field("otp") String otp,
            @Field("new_password") String password
    );
    @GET("menus")
    Call<MenuModel> getMenu(@Header("Authorization") String bearerToken);
    @GET("getbykategori/{id}")
    Call<MenuModel> getMenuByKategori(
            @Header("Authorization") String bearerToken,
            @Path("id") int id
    );
    @GET("cart/show/{id}")
    Call<CartModel> getCart(
            @Header("Authorization") String bearerToken,
            @Path("id") int iduser
    );
    @POST("cart/tambahjumlah/{id}")
    Call<MessageResponse> tambahJumlahPesanan(
            @Header("Authorization") String bearerToken,
            @Path("id") int id
    );
    @POST("cart/kurangjumlah/{id}")
    Call<MessageResponse> kurangJumlahPesanan(
            @Header("Authorization") String bearerToken,
            @Path("id") int id
    );
    @DELETE("cart/remove/{id}")
    Call<Void> removePesanan(
            @Header("Authorization") String bearerToken,
            @Path("id") int id
    );
    @GET("cart/sumharga/{id}")
    Call<MessageResponse> sumHargaCart(
            @Header("Authorization") String bearerToken,
            @Path("id") int iduser
    );
    @POST("ordermenu/{id}")
    Call<MessageResponse> orderMenu(
            @Header("Authorization") String bearerToken,
            @Path("id") int id
    );
    @GET("historyordertuntas/{id}")
    Call<HistoryDoneModel> getHistoryTuntas(
            @Header("Authorization") String bearerToken,
            @Path("id") int iduser
    );
    @GET("historyorderbelum/{id}")
    Call<HistoryProsesModel> getHistoryBelum(
            @Header("Authorization") String bearerToken,
            @Path("id") int iduser
    );
    @FormUrlEncoded
    @POST("cart/add/{id}")
    Call<Void> addCart(
            @Header("Authorization") String bearerToken,
            @Path("id") int id,
            @Field("iduser") int iduser
    );
    @GET("orderdetail/show/{id}")
    Call<HistoryDetailModel> getHistoryDetail(
            @Header("Authorization") String bearerToken,
            @Path("id") int id
    );
}
