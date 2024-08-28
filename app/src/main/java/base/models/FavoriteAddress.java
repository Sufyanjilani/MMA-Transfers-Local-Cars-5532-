package base.models;

import com.google.gson.annotations.SerializedName;

public class FavoriteAddress {
    @SerializedName("Address1")
    String Address1;

    @SerializedName("Address1TypeId")
    int Address1TypeId;

    @SerializedName("Address2")
    String Address2;

    @SerializedName("Address1_Lat")
    String Address1Lat;


    @SerializedName("Address2_Lat")
    int Address2Lat;

    @SerializedName("Address1_Long")
    String Address1Long;

    @SerializedName("Address2_Long")
    int Address2Long;

    @SerializedName("CustomerId")
    int CustomerId;

    @SerializedName("Row_InsertedOn")
    String RowInsertedOn;

    @SerializedName("Row_InsertedBy")
    String RowInsertedBy;

    @SerializedName("customerName")
    String customerName;


    public void setAddress1(String Address1) {
        this.Address1 = Address1;
    }
    public String getAddress1() {
        return Address1;
    }

    public void setAddress1TypeId(int Address1TypeId) {
        this.Address1TypeId = Address1TypeId;
    }
    public int getAddress1TypeId() {
        return Address1TypeId;
    }

    public void setAddress2(String Address2) {
        this.Address2 = Address2;
    }
    public String getAddress2() {
        return Address2;
    }

    public void setAddress1Lat(String Address1Lat) {
        this.Address1Lat = Address1Lat;
    }
    public String getAddress1Lat() {
        return Address1Lat;
    }

    public void setAddress2Lat(int Address2Lat) {
        this.Address2Lat = Address2Lat;
    }
    public int getAddress2Lat() {
        return Address2Lat;
    }

    public void setAddress1Long(String Address1Long) {
        this.Address1Long = Address1Long;
    }
    public String getAddress1Long() {
        return Address1Long;
    }

    public void setAddress2Long(int Address2Long) {
        this.Address2Long = Address2Long;
    }
    public int getAddress2Long() {
        return Address2Long;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }
    public int getCustomerId() {
        return CustomerId;
    }

    public void setRowInsertedOn(String RowInsertedOn) {
        this.RowInsertedOn = RowInsertedOn;
    }
    public String getRowInsertedOn() {
        return RowInsertedOn;
    }

    public void setRowInsertedBy(String RowInsertedBy) {
        this.RowInsertedBy = RowInsertedBy;
    }
    public String getRowInsertedBy() {
        return RowInsertedBy;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerName() {
        return customerName;
    }
}
