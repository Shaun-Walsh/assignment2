package controllers;


import models.WearableDevice;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;

public class WearableDeviceAPI {
    //TODO Create a list to store the Wearable Devices
    /**
     * Creates an ArrayList of WearableDevice
     */
    private ArrayList<WearableDevice> wearableList;



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
    /*
    public boolean isValidId(String id) {
        for (WearableDevice techDev : whateverYouCalledYourList) {
            if (techDev.getId().equals(id))
                return false;
        }
            return true;
        }
*/

    //TODO - CRUD Methods
    /**
     * Add the wearableDevice object, passed as a parameter, to the ArrayList.
     *
     * @param wearableDevice models.wearableDevice object to be added to the ArrayList.
     */
    public boolean addWearableDevice(WearableDevice wearableDevice) {
        return wearableList.add(wearableDevice);
    }



    //TODO - Number methods


    // TODO Read/list methods




    //TODO get Technology methods

    //TODO - delete methods



    //TODO - sort methods

    //TODO Top 5 methods





    // TODO Persistence methods
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[] { WearableDevice.class };

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("wearableList.xml"));
        wearableList = (ArrayList<WearableDevice>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("wearableList.xml"));
        out.writeObject(wearableList);
        out.close();
    }

}
