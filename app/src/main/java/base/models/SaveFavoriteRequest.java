package base.models;

import com.google.gson.annotations.SerializedName;

   
public class SaveFavoriteRequest {

   @SerializedName("jsonString")
   FavoriteAddress address;

   @SerializedName("defaultClientId")
   String defaultClientId;

   @SerializedName("uniqueValue")
   String uniqueValue;


    public void setJsonString(FavoriteAddress address) {
        this.address = address;
    }
    public FavoriteAddress getJsonString() {
        return address;
    }
    
    public void setDefaultClientId(String defaultClientId) {
        this.defaultClientId = defaultClientId;
    }
    public String getDefaultClientId() {
        return defaultClientId;
    }
    
    public void setUniqueValue(String uniqueValue) {
        this.uniqueValue = uniqueValue;
    }
    public String getUniqueValue() {
        return uniqueValue;
    }
    
}