package base.listener;

import base.models.FavoriteAddress;

public interface  FavoriteAddressClickListener{
        void onItemClick(FavoriteAddress address);
        void onDeleteItemClick(FavoriteAddress address);
    }