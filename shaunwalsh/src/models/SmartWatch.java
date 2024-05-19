package models;

import utils.DisplayTypeUtility;
import utils.Utilities;

public class SmartWatch extends WearableDevice {
    private String displayType = "LCD";

    /**
     * Constructor for objects of class models.SmartWatch
     *
     * @param size             Size of the wearable
     * @param price            price of the wearable
     * @param manufacturerName Manufacturer of the wearable
     * @param material         Material the wearable is made from
     * @param modelName        Madel name of the wearable
     * @param id               ID of the wearable
     * @param displayType      Display type of the wearable
     */
    public SmartWatch(String size, double price, String manufacturerName, String material, String modelName, String id, String displayType) {
        super(size, price, manufacturerName, material, modelName, id);
        setDisplayType(displayType);
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        if (DisplayTypeUtility.isValidDisplayType(displayType.toLowerCase()))
            this.displayType = displayType;
    }

    public double getInsurancePremium(){
        double insurancePremium = getPrice() * .06;
        return Utilities.toTwoDecimalPlaces(insurancePremium);
    }
    public String connectToInternet(){
       String internet = "Connects to the internet via bluetooth";
        return internet;
    }

    public String toString() {
        return super.toString()  + ", display type is:  " + displayType + ", insurance premium is: " + getInsurancePremium() + ", and it " + connectToInternet();
    }
}


