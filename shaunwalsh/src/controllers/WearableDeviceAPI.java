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
    /**
     * Creates an ArrayList of WearableDevice
     */
    private ArrayList<WearableDevice> wearableList;

    public WearableDeviceAPI(){
        wearableList = new ArrayList<>();
    }

    public ArrayList<WearableDevice> getWearableDevices() {
        return wearableList;
    }

    /**
     * Creates a File object
     */
    private File file;

    /**
     * Constructor that uses @param file to instantiate file object
     */
    public WearableDeviceAPI(File file) {
        this.file = file;
    }

    public boolean isValidId(String id) {
        for (WearableDevice techDev : wearableList) {
            if (techDev.getId().equals(id))
                return false;
        }
            return true;
        }

    /**
     * This method takes in an integer and checks if it is a valid index in the products ArrayList.
     * @param index An integer representing a potential index in the ArrayList.
     * @return True of the index number passed is a valid index in the ArrayList, false otherwise.
     */
    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < wearableList.size());
    }

    /**
     * Find a product object at a specific index location.
     * If the index location is not valid, return null.
     * @param index Index of the models.Product object in the ArrayList
     * @return The product object or null if no object is at the index location
     */
    public WearableDevice findWearableDevice(int index) {
        if (isValidIndex(index)) {
            return wearableList.get(index);
        }
        return null;
    }

    /**
     * Add the wearableDevice object, passed as a parameter, to the ArrayList.
     * @param wearableDevice models.wearableDevice object to be added to the ArrayList.
     * proposedId represents the new id that has been entered, this is then checked using the isValidId method.
     */
    public boolean addWearableDevice(WearableDevice wearableDevice) {
        String proposedId = wearableDevice.getId();
        if (isValidId(proposedId)){
            return wearableList.add(wearableDevice);
        }
        return false;
    }

    /**
     * Update a models.SmartWatch in the ArrayList with the contents passed in the models.SmartWatch object parameter.
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
        return false;
    }

    /**
     * Update a models.SmartBand in the ArrayList with the contents passed in the models.SmartBand object parameter.
     * @param indexToUpdate Index of the models.SmartWatch object in the ArrayList
     * @param size             Size of the wearable
     * @param price            price of the wearable
     * @param manufacturerName Manufacturer of the wearable
     * @param material         Material the wearable is made from
     * @param modelName        Madel name of the wearable
     * @param id               ID of the wearable
     * @param heartRateMonitor If the wearable has a heart rate monitor
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
        return false;
    }

    /**
     * This method returns the number of WearableDevice objects stored in the ArrayList.
     * @return An int value representing the number of WearableDevice objects in the ArrayList.
     */
    public int numberOfWearableDevices() {
        return wearableList.size();
    }

    /**
     * This method returns the number of WearableDevice objects stored in the ArrayList that are SmartBands.
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
     * @return An int value representing the number of WearableDevice objects in the ArrayList that match the input name.
     */
    public int numberOfWearableDeviceByChosenManufacturer(String manufacturer) {
        int number = 0;
        for (WearableDevice wearableDevice : wearableList) {
            if (wearableDevice.getManufacturerName().equalsIgnoreCase(manufacturer)) {
                number++;
            }
        }
        return number;
    }

    /**
     * This method builds and returns a String containing all the WearableDevices in the ArrayList.
     * For each WearableDevice stored, the associated index number is included.
     * If no products are stored in the ArrayList, the String "No WearableDevice Devices" is returned.
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
     * For each WearableDevice that is a SmartBand, the associated index number is included.
     * If no WearableDevices that are a SmartBand are stored in the ArrayList, the String "No Smart Bands" is returned.
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
     * For each WearableDevice that is a SmartWatch, the associated index number is included.
     * If no WearableDevices that are a SmartWatch are stored in the ArrayList, the String "No SmartWatches" is returned.
     * @return A String containing all the WearableDevices of SmartWatch Class in the ArrayList, or "No SmartWatches",
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
     * For each WearableDevice added to the String, the associated index number is included.
     * If no WearableDevice are stored in the ArrayList, the returned String indicates this.
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
     * For each WearableDevice added to the String, the associated index number is included.
     * If no WearableDevice are stored in the ArrayList, the returned String indicates this.
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

    /**
     * This method returns a model.WearableDevice given the index. If the
     * index does not exist in the collection, null is returned.
     * @param index  The integer of the index to search by
     * @return  position of the product if it exists, null otherwise.
     */
    public WearableDevice getWearableDeviceByIndex(int index) {
        if (isValidIndex(index)) {
            return wearableList.get(index);
        }
        return null;
    }

    /**
     * This method returns the models.WearableDevice of a WearableDevice given the WearableDevice ID. If the
     * ID does not exist in the collection, null is returned.
     * @param id  The string to search by
     * @return  position of the WearableDevice if it exists, otherwise null.
     */
    public WearableDevice getWearableDeviceById(String id) {
        for(int i = 0; i< wearableList.size(); i++) {
            if (wearableList.get(i).getId().equals(id)){
                return wearableList.get(i);
            }
        }
        return null;
    }

    /**
     * Delete a models.WearableDevice from the ArrayList, if it exists, at the index passed as a parameter.
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
     * @param id  of the models.WearableDevice object in the ArrayList
     * @return The deleted WearableDevice object or null if no object is at the index location
     */
    public WearableDevice deleteWearableDeviceId(String id) {
        for(int i = 0; i< wearableList.size(); i++) {
            if (wearableList.get(i).getId().equals(id)){
                return wearableList.remove(i);
            }
        }
        return null;
    }

    /**
     * Selection sort algorithm for sorting the arraylist of WearableDevices by price ascending.
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
     * Selection sort algorithm for sorting the arraylist of WearableDevices by price descending.
     */
    public void sortByPriceDescending() {
        for (int i = 0; i < wearableList.size() - 1; i++) {
            int highestIndex = i;
            for (int j = i + 1; j < wearableList.size(); j++) {
                if (wearableList.get(j).getPrice() > wearableList.get(highestIndex).getPrice()) {
                    highestIndex = j;
                }
            }
            swapWearableDevice(wearableList, i, highestIndex);
        }
    }

    /**
     * A method that swamps the position of two WearableDevices when given tow index positions.
     */
    private void swapWearableDevice(ArrayList<WearableDevice> wearableList, int i, int j) {
        WearableDevice smaller = wearableList.get(i);
        WearableDevice bigger = wearableList.get(j);

        wearableList.set(i,bigger);
        wearableList.set(j,smaller);
    }

    /**
     * This method returns of the top 5 most expensive WearableDevices. The Arraylist is first sorted, and then
     * if the Arraylist is not empty a for loop gets the first five products in the sorted array. The break is used in case the
     * Arraylist has less than 5 WearableDevices
     * @return  Top 5 most expensive WearableDevices , otherwise no devices found.
     */
    public String topFiveMostExpensiveWearableDevice() {
        sortByPriceDescending();
        String str = "";
        if (!wearableList.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                if ( i >= wearableList.size() ) {
                    break;
                }
                str += wearableList.get(i) + "\n";
            }
            return str;
        }
        return "No devices found";
    }

    /**
     * This method returns of the top 5 most expensive SmartWatches. The Arraylist is first sorted, and then
     * a for each  loop gets the first five instances of SmartWatches in the sorted Arraylist.
     * @return  Top 5 most expensive SmartWatches, or less if there is not 5 in the Arraylist , otherwise no smartwatches found.
     */
    public String topFiveMostExpensiveSmartWatches() {
        sortByPriceDescending();
        String str = "";
        int smartWatches = 0;

        for (WearableDevice wearableDevice : wearableList) {
            if (wearableDevice instanceof SmartWatch) {
                SmartWatch smartWatch = (SmartWatch) wearableDevice;
                str += smartWatch + "\n";
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

    /**
     * The load method uses the XStream component to read all the WearableDevice objects from the wearableList.xml
     * file stored on the hard disk.  The read WearableDevices are loaded into the wearableList ArrayList
     * @throws Exception  An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {

        Class<?>[] classes = new Class[]{ WearableDevice.class, SmartWatch.class, SmartBand.class,};

        XStream xstream = new XStream(new DomDriver());
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(fileName()));
        wearableList = (ArrayList<WearableDevice>) in.readObject();
        in.close();
    }

    /**
     * The save method uses the XStream component to write all the WearableDevice objects in the wearableList ArrayList
     * to the wearableList.xml file stored on the hard disk.
     * @throws Exception  An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName()));
        out.writeObject(wearableList);
        out.close();
    }

    @Override
    public String fileName() {
        return "wearableList.xml";
    }
}
