package base.manager;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.support.parser.PropertyInfo;
import com.support.parser.SoapHelper;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import base.listener.Listener_VerifyPromotionCode;
import base.models.ConfirmedBooking;
import base.utils.AppConstants;
import base.utils.CommonVariables;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Manager_VerifyPromotionCode extends AsyncTask<String, Void, String> {

    private Context context;
    private ConfirmedBooking obj;
    private Listener_VerifyPromotionCode listener;
    private SweetAlertDialog mDialog;

    public Manager_VerifyPromotionCode(Context context, ConfirmedBooking obj, Listener_VerifyPromotionCode listener) {
        this.context = context;
        this.obj = obj;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try {
            mDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            mDialog.setTitleText("Verifying code");
            mDialog.setContentText("Please wait..");
            mDialog.setCancelable(false);
            mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (mDialog != null) {
            mDialog.dismiss();
        }

        if (listener != null) {
            listener.onComplete(result);
        }
    }

    @Override
    protected String doInBackground(String... params) {
//        String json_String = new Gson().toJson(obj);

        HashMap<String, Object> map = new HashMap<>();
        String aaa = new Gson().toJson(obj);
        map.put("jsonString", aaa);
        String token = AppConstants.getAppConstants().getToken();
        map.put("Token", token);

        String _jsonString = new Gson().toJson(map);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), _jsonString);
        Request request = new Request.Builder()
                .url(CommonVariables.BASE_URL + "VerifyPromotion")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

//        SoapHelper.Builder builder = new SoapHelper.Builder(CommonVariables.SERVICE, context);
//        builder
//                .setMethodName("VerifyPromotion", true)
//                .addProperty("jsonString", json_String, PropertyInfo.STRING_CLASS);
//        try {
//            return builder.getResponse();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}
