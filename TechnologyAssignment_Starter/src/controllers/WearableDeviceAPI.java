package controllers;


import models.SmartBand;
import models.SmartWatch;
import models.WearableDevice;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import utils.ISerializer;
import utils.ManufacturerNameUtility;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.List;

public class WearableDeviceAPI implements ISerializer {
    //TODO Create a list to store the Wearable Devices
    /**
     * Creates an ArrayList of WearableDevice
     */
    private ArrayList<WearableDevice> wearableList;

    public WearableDeviceAPI(){
        wearableList = new ArrayList<WearableDevice>();
    }

    public ArrayList<WearableDevice> getWearableDevices() {
        return wearableList;
    }




    //TODO create a File field to story filename
    /**
     * Creates a File object
     */
    private File file;

    //TODO create constructor to initialise filename
    /**
     * Constructor that uses @param file to instantiate file object
     */
    public WearableDeviceAPI(File file) {
        this.file = file;
    }
    //todo leave this method in starter

    //leave this method in starter
    //the following is isValidId can be updated
    //to suit your code

    public boolean isValidId(String id) {
        for (WearableDevice techDev : wearableList) {
            if (techDev.getId().equals(id))
                return false;
        }
            return true;
        }

    /**
     * This method takes in a number and checks if it is a valid index in the products ArrayList.
     *
     * @param index A number representing a potential index in the ArrayList.
     * @return True of the index number passed is a valid index in the ArrayList, false otherwise.
     */
    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < wearableList.size());
    }

    /**
     * Find a product object at a specific index location.
     * If the index location is not valid, return null.
     *
     * @param index Index of the models.Product object in the ArrayList
     * @return The product object or null if no object is at the index location
     */
    public WearableDevice findWearableDevice(int index) {
        if (isValidIndex(index)) {
            return wearableList.get(index);
        }
        return null;
    }

    //TODO - CRUD Methods
    /**
     * Add the wearableDevice object, passed as a parameter, to the ArrayList.
     * @param wearableDevice models.wearableDevice object to be added to the ArrayList.
     */
    public boolean addWearableDevice(WearableDevice wearableDevice) {
        return wearableList.add(wearableDevice);
    }

    /**
     * Update a models.SmartWatch in the ArrayList with the contents passed in the models.SmartWatch object parameter.
     *
     * @param indexToUpdate Index of the models.SmartWatch object in the ArrayList
     * @param size             Size of the wearable
     * @param price            price of the wearable
     * @param manufacturerName Manufacturer of the wearable
     * @param material         Material the wearable is made from
     * @param modelName        Madel name of the wearable
     * @param id               ID of the wearable
     * @param displayType      Display type of the wearable
     */
    public boolean updateSmartWatch(int indexToUpdate, String size, double price, String manufacturerName, String material, String modelName, String id, String displayType) {

        WearableDevice foundWearableDevice = findWearableDevice(indexToUpdate);

        if ((foundWearableDevice != null) && (foundWearableDevice instanceof SmartWatch)) {
            foundWearableDevice.setSize(size);
            foundWearableDevice.setPrice(price);
            foundWearableDevice.setManufacturerName(manufacturerName);
            foundWearableDevice.setMaterial(material);
            foundWearableDevice.setModelName(modelName);
            foundWearableDevice.setId(id);
            ((SmartWatch) foundWearableDevice).setDisplayType(displayType);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    /**
     * Update a models.SmartBand in the ArrayList with the contents passed in the models.SmartBand object parameter.
     *
     * @param indexToUpdate Index of the models.SmartWatch object in the ArrayList
     * @param size             Size of the wearable
     * @param price            price of the wearable
     * @param manufacturerName Manufacturer of the wearable
     * @param material         Material the wearable is made from
     * @param modelName        Madel name of the wearable
     * @param id               ID of the wearable
     * @param heartRateMonitor IF the wearable has a heart rate monitor
     */
    public boolean updateSmartBand(int indexToUpdate, String size, double price, String manufacturerName, String material, String modelName, String id, boolean heartRateMonitor) {

        WearableDevice foundWearableDevice = findWearableDevice(indexToUpdate);

        if ((foundWearableDevice != null) && (foundWearableDevice instanceof SmartBand)) {
            foundWearableDevice.setSize(size);
            foundWearableDevice.setPrice(price);
            foundWearableDevice.setManufacturerName(manufacturerName);
            foundWearableDevice.setMaterial(material);
            foundWearableDevice.setModelName(modelName);
            foundWearableDevice.setId(id);
            ((SmartBand) foundWearableDevice).setHeartRateMonitor(heartRateMonitor);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }


    //TODO - Number methods
    /**
     * This method returns the number of WearableDevice objects stored in the ArrayList.
     *
     * @return An int value representing the number of WearableDevice objects in the ArrayList.
     */
    public int numberOfWearableDevices() {
        return wearableList.size();
    }

    /**
     * This method returns the number of WearableDevice objects stored in the ArrayList that are SmartBands.
     *
     * @return An int value representing the number of WearableDevice objects in the ArrayList that are SmartBands.
     */
    public int numberOfSmartBands() {
        int number = 0;
        for (WearableDevice wearableDevice : wearableList) {
            if (wearableDevice instanceof SmartBand) {
                number++;
            }
        }
        return number;
    }

    /**
     * This method returns the number of WearableDevice objects stored in the ArrayList that are SmartWatches.
     *
     * @return An int value representing the number of WearableDevice objects in the ArrayList that are SmartWatches.
     */
    public int numberOfSmartWatch() {
        int number = 0;
        for (WearableDevice wearableDevice : wearableList) {
            if (wearableDevice instanceof SmartWatch) {
                number++;
            }
        }
        return number;
    }

    /**
     * This method returns the number of WearableDevice objects stored in the ArrayList that match
     * the @param manufacturer which is passed in.
     *
     * @return An int value representing the number of WearableDevice objects in the ArrayList that match the input name.
     */
    public int numberOfWearableDeviceByChosenManufacturer(String manufacturer) {
        int number = 0;
        for (WearableDevice wearableDevice : wearableList) {
            if (wearableDevice.getManufacturerName().equals(manufacturer)) {
                number++;
            }
        }
        return number;
    }


    // TODO Read/list methods
    /**
     * This method builds and returns a String containing all the WearableDevices in the ArrayList.
     * For each WearableDevice stored, the associated index number is included.
     * If no products are stored in the ArrayList, the String "No WearableDevice Devices" is returned.
     *
     * @return A String containing all the WearableDevices in the ArrayList, or "No WearableDevice Devices",
     * if empty.
     */
    public String listAllWearableDevices() {
        if (wearableList.isEmpty()) {
            return "No WearableDevice Devices";
        } else {
            String listOfWearableDevices = "";
            for (int i = 0; i < wearableList.size(); i++) {
                listOfWearableDevices += i + ": " + wearableList.get(i) + "\n";
            }
            return listOfWearableDevices;
        }
    }
    /**
     * This method builds and returns a String containing all the SmartBands in the ArrayList.
     * For each WearableDevice that are a SmartBand, the associated index number is included.
     * If no WearableDevices that are a SmartBand are stored in the ArrayList, the String "No Smart Bands" is returned.
     *
     * @return A String containing all the WearableDevices of SmartBand Class in the ArrayList, or "No Smart Band",
     * if empty.
     */
    public String listAllSmartBands() {
        String str = "";

        for (WearableDevice wearableDevice : wearableList) {
            if (wearableDevice instanceof SmartBand) {
                str += wearableList.indexOf(wearableDevice) + ": " +wearableDevice + "\n";
            }
        }

        if (str.isEmpty()) {
            return "No Smart Bands";
        } else {
            return str;
        }
    }

    /**
     * This method builds and returns a String containing all the SmartWatches in the ArrayList.
     * For each WearableDevice that are a SmartWatch, the associated index number is included.
     * If no WearableDevices that are a SmartWatch are stored in the ArrayList, the String "No Smart Watches" is returned.
     *
     * @return A String containing all the WearableDevices of SmartWatch Class in the ArrayList, or "No Smart Watches",
     * if empty.
     */
    public String listAllSmartWatches() {
        String str = "";

        for (WearableDevice wearableDevice : wearableList) {
            if (wearableDevice instanceof SmartWatch) {
                str += wearableList.indexOf(wearableDevice) + ": " +wearableDevice + "\n";
            }
        }

        if (str.isEmpty()) {
            return "No Smart Watches";
        } else {
            return str;
        }
    }

    /**
     * This method builds and returns a String containing all the WearableDevices in the ArrayList
     * that are more expensive that a specific amount (passed as a parameter).
     * <p>
     * For each WearableDevice added to the String, the associated index number is included.
     * If no WearableDevice are stored in the ArrayList, the returned String indicates this.
     *
     * @param price The value used to filter for WearableDevice costing more than it.
     * @return A String containing all the WearableDevice in the ArrayList more expensive than the parameter value
     * or "No WearableDevice more expensive than: ", if none are more expensive.  If no WearableDevice are
     * in the ArrayList, the returned String contains "No WearableDevice Device".
     */
    public String listWearableDeviceAbovePrice(double price) {
        if (wearableList.isEmpty()) {
            return "No WearableDevice Device";
        } else {
            String str = "";
            for (int i = 0; i < wearableList.size(); i++) {
                if (wearableList.get(i).getPrice() > price)
                    str += i + ": " + wearableList.get(i) + "\n";
            }
            if (str.equals("")) {
                return "No WearableDevice more expensive than: " + price;
            } else {
                return str;
            }
        }
    }

    /**
     * This method builds and returns a String containing all the WearableDevices in the ArrayList
     * that are cheaper than a specific amount (passed as a parameter).
     * <p>
     * For each WearableDevice added to the String, the associated index number is included.
     * If no WearableDevice are stored in the ArrayList, the returned String indicates this.
     *
     * @param price The value used to filter for WearableDevice costing less than it.
     * @return A String containing all the WearableDevice in the ArrayList cheaper than the parameter value
     * or "No WearableDevice cheaper than: ", if none are cheaper.  If no WearableDevice are
     * in the ArrayList, the returned String contains "No WearableDevice Device".
     */
    public String listWearableDeviceBelowPrice(double price) {
        if (wearableList.isEmpty()) {
            return "No WearableDevice Device";
        } else {
            String str = "";
            for (int i = 0; i < wearableList.size(); i++) {
                if (wearableList.get(i).getPrice() < price)
                    str += i + ": " + wearableList.get(i) + "\n";
            }
            if (str.equals("")) {
                return "No WearableDevice cheaper than: " + price;
            } else {
                return str;
            }
        }
    }


    //TODO get Technology methods
    /**
     * This method returns a model.WearableDevice given the index. If the
     * index does not exist in the collection, null is returned.
     *
     * @param index  The integer of the index  to search by
     * @return  position of the product if it exists, -1 otherwise.
     */
    public WearableDevice getWearableDeviceByIndex(int index) {
        if (isValidIndex(index)) {
            return wearableList.get(index);
        }
        return null;
    }

    /**
     * This method returns the Product of a product given the product code. If the
     * product code does not exist in the collection, null is returned.
     *
     * @param id  The string to search by
     * @return  position of the product if it exists, otherwise null.
     */
    public WearableDevice getWearableDeviceById(String id) {
        String matchingIds = "";
        for(int i = 0; i< wearableList.size(); i++) {
            if (wearableList.get(i).getId().equals(id)){
                return wearableList.get(i);
            }
        }
        return null;
    }

    //TODO - delete methods
    /**
     * Delete a models.WearableDevice from the ArrayList, if it exists, at the index passed as a parameter.
     *
     * @param indexToDelete Index of the models.WearableDevice object in the ArrayList
     * @return The deleted product object or null if no object is at the index location
     */
    public WearableDevice deleteWearableDeviceByIndex(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return wearableList.remove(indexToDelete);
        }
        return null;
    }

    /**
     * Delete a models.WearableDevice from the ArrayList, if it exists as the id passed as a parameter.
     * CHECK THIS METHOD LATER
     * @param strId  of the models.WearableDevice object in the ArrayList
     * @return The deleted product object or null if no object is at the index location
     */
    public WearableDevice deleteWearableDeviceId(String strId) {
        for (int i = 0; i < wearableList.size(); i++) {
            WearableDevice techDev = wearableList.get(i);
            if (techDev.getId().equals(strId)) {
                return wearableList.remove(i);
            }
        }
        return null;
    }



    //TODO - sort methods

    /**
     * Selection sort algorithm for  sorting the arraylist of WearableDevices by price ascending.
     */
    public void sortByPriceAscending() {
        for (int i = 0; i < wearableList.size() - 1; i++) {
            int lowestIndex = i;
            for (int j = i + 1; j < wearableList.size(); j++) {
                if (wearableList.get(j).getPrice() < wearableList.get(lowestIndex).getPrice()) {
                    lowestIndex = j;
                }
            }
            swapWearableDevice(wearableList, i, lowestIndex);
        }
    }

    /**
     * Selection sort algorithm for  sorting the arraylist of WearableDevices by price ascending.
     */
    public void sortByPriceDescending() {
        for (int i = wearableList.size() - 1; i >= 0; i--) {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++) {
                if (wearableList.get(j).getPrice() > wearableList.get(highestIndex).getPrice()) {
                    highestIndex = j;
                }

            }
            swapWearableDevice(wearableList, i, highestIndex);
        }
    }
    private void swapWearableDevice(ArrayList<WearableDevice> wearableList, int i, int j) {
        WearableDevice smaller = wearableList.get(i);
        WearableDevice bigger = wearableList.get(j);

        wearableList.set(i,bigger);
        wearableList.set(j,smaller);
    }
    //TODO Top 5 methods
    public String topFiveMostExpensiveWearableDevice() {
        sortByPriceDescending();
        String str = "";
        if (!wearableList.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                str += wearableList.get(i).toString() + "\n";
            }
            return str;
        }
        return "No devices found";
    }
    public String topFiveMostExpensiveSmartWatches() {
        sortByPriceDescending();
        String str = "";
        int smartWatches = 0;

        for (WearableDevice wearableDevice : wearableList) {
            if (wearableDevice instanceof SmartWatch) {
                SmartWatch smartWatch = (SmartWatch) wearableDevice;
                str += smartWatch.toString() + "\n";
                smartWatches++;
                if (smartWatches == 5) {
                    return str;
                }
            }
        }
        if (smartWatches == 0) {
            return "No smartwatches found";
        }
        return str;
    }







    // TODO Persistence methods
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[] { WearableDevice.class, SmartBand.class, SmartWatch.class };


        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("wearableList.xml"));
        wearableList = (ArrayList<WearableDevice>) in.readObject();
        in.close();
    }

    @Override
    public String fileName() {
        return "wearableList.xml";
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("wearableList.xml"));
        out.writeObject(wearableList);
        out.close();
    }

}
