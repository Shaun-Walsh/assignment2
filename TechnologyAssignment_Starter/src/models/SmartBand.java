package models;
import utils.Utilities;
public class SmartBand extends WearableDevice{
    private boolean heartRateMonitor;
    /**
     * Constructor for objects of class models.SmartWatch
     *
     * @param size             Size of the wearable
     * @param price            price of the wearable
     * @param manufacturerName Manufacturer of the wearable
     * @param material         Material the wearable is made from
     * @param modelName        Madel name of the wearable
     * @param id               ID of the wearable
     * @param heartRateMonitor IF the wearable has a heart rate monitor
     */

    public SmartBand(String size, double price, String manufacturerName, String material, String modelName, String id, boolean heartRateMonitor) {
        super(size, price, manufacturerName, material, modelName, id);
        this.heartRateMonitor = heartRateMonitor;
    }
    public boolean isHeartRateMonitor() {
        return heartRateMonitor;
    }
    public void setHeartRateMonitor(boolean heartRateMonitor) {
        this.heartRateMonitor = heartRateMonitor;
    }
    public double getInsurancePremium(){
        // Algorithm -
        //  price of Smart Watch * .07
        //  e.g.  230 *.07 = 16.1
        double insurancePremium = getPrice() * .07;
        return insurancePremium;
    }
    public String connectToInternet(){
        String internet =  "Connects to the internet via Companion App";
        return internet;
        // return the String "Connects to the internet via bluetooth"
    }

    public String toString() {
        return super.toString()  + " " + heartRateMonitor + " insurance premium is " + getInsurancePremium() + " and it " + connectToInternet();
    }

}
