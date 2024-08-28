package base.manager;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import base.listener.Listener_GetAddressCoordinate;
import base.listener.Listener_GetFavoriteAddress;
import base.models.ClsLocationData;
import base.models.SaveFavoriteRequest;
import base.utils.CommonVariables;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Manager_SaveAddress extends AsyncTask<Void, Void, String> {

    private Context context;
   private SaveFavoriteRequest request;
    private Listener_GetFavoriteAddress listener;

    public Manager_SaveAddress(Context context, SaveFavoriteRequest request, Listener_GetFavoriteAddress listener) {
        this.context = context;
        this.request = request;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (listener != null) {
            listener.onPre();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (listener != null) {
            listener.onComplete(result);
        }
    }

    @Override
    protected String doInBackground(Void... params) {

        String _jsonString = new Gson().toJson(request);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), _jsonString);
        Request request = new Request.Builder()
                .url(CommonVariables.BASE_URL + "SaveFavouriteAddress")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
