package base.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eurosoft.customerapp.R;

import java.util.ArrayList;
import java.util.List;

import base.listener.FavoriteAddressClickListener;
import base.models.FavoriteAddress;

public class FavoriteAddressAdapter extends RecyclerView.Adapter<FavoriteAddressAdapter.ViewHolder> {

    private ArrayList<FavoriteAddress> address = new ArrayList<FavoriteAddress>();
    Context context;

    FavoriteAddressClickListener listener;

    // Data is passed into the constructor
    public FavoriteAddressAdapter(Context context, List<FavoriteAddress> data,FavoriteAddressClickListener listener) {
        this.context = context;
        address.clear();
        address.addAll(data);
        this.listener = listener;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.address_row, parent, false);
        return new ViewHolder(view);
    }

    // Binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FavoriteAddress item = address.get(position);
        holder.bind(item);
    }


    @Override
    public int getItemCount() {
        return address.size();
    }

    public  void addItems(ArrayList<FavoriteAddress> address){
        address.clear();
        address.addAll(address);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hTv, address;

        RelativeLayout rlAddress;
        ImageView icon, deleteIv;

        ViewHolder(View itemView) {
            super(itemView);
            hTv = itemView.findViewById(R.id.hTv);
            address = itemView.findViewById(R.id.address);
            icon = itemView.findViewById(R.id.icon);
            rlAddress = itemView.findViewById(R.id.rlAddress);
            deleteIv = itemView.findViewById(R.id.deleteIv);
        }

        void bind(FavoriteAddress address) {
            //hTv.setText(item);

            deleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDeleteItemClick(address);

                }
            });
            rlAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(address);
                }
            });
        }
    }



}
