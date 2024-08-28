package base.manager;

import android.content.Context;
import android.os.AsyncTask;

import base.listener.Listener_Delete_favorite;
import base.listener.Listener_GetFavoriteAddress;
import base.utils.CommonVariables;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Manager_DeleteFavoriteAddress extends AsyncTask<Void, Void, String> {

    private Context context;
    private String customerId = "";
    private Listener_Delete_favorite listener;

    public Manager_DeleteFavoriteAddress(Context context, String customerId, Listener_Delete_favorite listener) {
        this.context = context;
        this.customerId = customerId;
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
            listener.onPost(result);
        }
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"jsonString\":\"{\\\"CustomerId\\\":" + customerId + "}\",\"defaultClientId\":5532,\"uniqueValue\":\"55324321orue\"}");
            Request request = new Request.Builder()
                    .url(CommonVariables.BASE_URL + "GetAllFavouriteAddress")
                    .method("GET", body)
                    .addHeader("Content-Type", "application/json")
                    .build();


            try (Response response = client.newCall(request).execute();) {
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }


        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }
    }

}
