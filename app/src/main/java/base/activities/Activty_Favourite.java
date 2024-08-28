package base.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eurosoft.customerapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import base.adapters.FavoriteAddressAdapter;
import base.listener.FavoriteAddressClickListener;
import base.listener.Listener_Delete_favorite;
import base.listener.Listener_GetFavoriteAddress;
import base.manager.Manager_DeleteFavoriteAddress;
import base.manager.Manager_GetAddressCoordinates;
import base.manager.Manager_GetFavorite;
import base.manager.Manager_SaveAddress;
import base.models.FavoriteAddress;
import base.models.LocAndField;
import base.models.SaveFavoriteRequest;
import base.models.SettingsModel;
import base.utils.CommonVariables;
import base.utils.SharedPrefrenceHelper;

public class Activty_Favourite extends AppCompatActivity {


    final int ADD_ADDRESS_REQUEST = 22222;
    private SettingsModel settingsModel;
    private SharedPrefrenceHelper helper;
    RecyclerView rv;
    ImageView menuIv;
    TextView titleTv;
    FloatingActionButton fab;

    FavoriteAddressAdapter adapter;
    ArrayList<FavoriteAddress> address = new ArrayList<FavoriteAddress>(); //ye khali hai EMPTY phirr

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activty_favourite);

        helper = new SharedPrefrenceHelper(this);
        settingsModel = helper.getSettingModel();

        rv = findViewById(R.id.rv);
        menuIv = findViewById(R.id.menuIv);
        titleTv = findViewById(R.id.titleTv);
        fab = findViewById(R.id.fab);

        setUpRecyclerView();

        getAddress();
        menuIv.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_back__));
        titleTv.setText("Favorite");

        menuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  finish();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewAddress();
            }
        });


    }

    private void setUpRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavoriteAddressAdapter(this, address, new FavoriteAddressClickListener() {
            @Override
            public void onItemClick(FavoriteAddress address) {
                setResult(address);
            }

            @Override
            public void onDeleteItemClick(FavoriteAddress address) {
                deleteAddress(address);
            }
        });
        rv.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ADDRESS_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {


                    ArrayList<LocAndField> fieldList  = data.getExtras().getParcelableArrayList("key_locAndFieldArrayList"); // Retrieve the data sent from Activity A

                    if(fieldList  !=null  && !fieldList.isEmpty()) {
                        FavoriteAddress fAddress = new FavoriteAddress();
                        LocAndField field = fieldList.get(0);

                        fAddress.setAddress1(field.getField());
                        fAddress.setAddress1Lat(field.getLat());
                        fAddress.setAddress1Long(field.getLon());
                        try {
                            if (field.getLocationType().equalsIgnoreCase("airport")) {
                                fAddress.setAddress1TypeId(1);
                            } else {
                                fAddress.setAddress1TypeId(0);
                            }


                        }catch (Exception ex){
                        }

                        saveNewFavorite(fAddress);
                    }
                }
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
                // Handle if the user canceled the action in Activity A
            }
        }
    }

    public void getAddress() {
        new Manager_GetFavorite(this,settingsModel.getUserServerID(), new Listener_GetFavoriteAddress() {
            @Override
            public void onComplete(String result) {

               // updateUI();



            }

            @Override
            public void onPre() {

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    // when user click on delete button call this methode
    // also change body and end point of this service
    // refresh old list

    public void deleteAddress(FavoriteAddress address) {

        new Manager_DeleteFavoriteAddress(this, "", new Listener_Delete_favorite() {


            @Override
            public void onPre() {

            }

            @Override
            public void onPost(String result) {


            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void addNewAddress() {
       Intent i = new Intent(this, Activity_SearchAddressForHomeAndWork.class).putExtra("setFrom", "favourite");
        startActivityForResult(i, ADD_ADDRESS_REQUEST);


    }
    // when user slect address give back a result to caller activity
    public void setResult(FavoriteAddress address) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("key", "value"); // Put whatever data you want to send back
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    private void updateUI(ArrayList<FavoriteAddress> address) {
        adapter.addItems(address);
    }


    // use this for save favorite address


    public void saveNewFavorite(FavoriteAddress address) {
     //  FavoriteAddress favoriteAddress = new FavoriteAddress();

       // saveNewFavorite(favoriteAddress);

        SaveFavoriteRequest request = new SaveFavoriteRequest();
        address.setCustomerName(settingsModel.getName()+" "+settingsModel.getlName());
        address.setCustomerId(Integer.valueOf(settingsModel.getUserServerID()));
        request.setJsonString(address);

      request.setUniqueValue(CommonVariables.clientid + "4321orue");
      request.setDefaultClientId(CommonVariables.clientid+"");
        new Manager_SaveAddress(this, request, new Listener_GetFavoriteAddress() {
            @Override
            public void onComplete(String result) {

                

                // updateUI
            }

            @Override
            public void onPre() {

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}