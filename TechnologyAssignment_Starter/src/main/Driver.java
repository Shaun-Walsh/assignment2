package main;

import controllers.WearableDeviceAPI;


import models.SmartBand;
import models.SmartWatch;
import models.WearableDevice;
import utils.ScannerInput;
import utils.Utilities;


public class Driver {


    private WearableDeviceAPI wearableDeviceAPI = new WearableDeviceAPI();

    public static void main(String[] args) throws Exception {

        new Driver().start();
    }

    public void start() {

        //TODO - construct fields

        //TODO - load all data once the serializers are set up
        runMainMenu();
    }
//TODO - construct menus

    private int mainMenu() {
        int option = ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                |----------------------WearableDevice Store----------------------|
                |   1) WearableDevice CRUD MENU                                  |
                |   2) Reports MENU                                              |
                ------------------------------------------------------------------
                |   4) Search WearableDevice Devices by Index                    |
                |   5) Search WearableDevice Devices by ID                       |
                |   6) Sort WearableDevice Devices by Price Ascending            |
                |   7) Sort WearableDevice Devices by Price Descending           |
                ------------------------------------------------------------------
                |   10) Save all                                                 |
                |   11) Load all                                                 |
                |   0)  Exit                                                     |
                ------------------------------------------------------------------
                ==>>  """);
        return option;
    }

    //TODO - write code to call appropiate method based on value in option
    private void runMainMenu() {
        int option = mainMenu();

        while (option != 0) {

            switch (option) {
                case 1 -> runCrudMenu();
                case 2 -> runReportsMenu();
                case 4 -> searchWearableDevicesByIndex();
                case 5 -> searchWearableDevicesByID();
                case 6 -> sortWearableDevicesByPriceAscending();
                case 7 -> sortWearableDevicesByPriceDescending();
                case 10 -> saveProducts();
                case 11 -> loadProducts();
                default -> System.out.println("Invalid option entered: " + option);
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = mainMenu();
        }
        exitApp();
        //the user chose option 0, so exit the program
        System.out.println("Exiting...bye");
        System.exit(0);
    }

    private void loadProducts() {
        try {
            wearableDeviceAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

    private void saveProducts() {
        try {
            wearableDeviceAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void sortWearableDevicesByPriceDescending() {
            wearableDeviceAPI.sortByPriceDescending();
            System.out.println("List of products in product code (descending) order");
            System.out.println(wearableDeviceAPI.listAllWearableDevices());
        }

    private void sortWearableDevicesByPriceAscending() {
        wearableDeviceAPI.sortByPriceAscending();
        System.out.println("List of products in product code (ascending) order");
        System.out.println(wearableDeviceAPI.listAllWearableDevices());

    }

    private void searchWearableDevicesByID() {
        String deviceId = ScannerInput.readNextLine("Please enter a WearableDevice ID to search by:");
        System.out.println(wearableDeviceAPI.getWearableDeviceById(deviceId));
    }

    private void searchWearableDevicesByIndex() {
        int index = ScannerInput.readNextInt("Please enter an index to search for:");
        WearableDevice device = wearableDeviceAPI.getWearableDeviceByIndex(index);

        if (device != null)
            System.out.println(device);
        else
            System.out.println("No wearable device found at index " + index);
    }

    private int reportsMenu() {
        int option = ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                |-------------------------Reports Menu---------------------------|
                |   1) WearableDevice Overview                                   |
                |   0) Return to main menu                                       |
                ------------------------------------------------------------------
                ==>>  """);
        return option;
    }

    private void runReportsMenu() {
        int option = reportsMenu();

        while (option != 0) {

            switch (option) {
                case 1 -> runWearableDeviceReportsMenu();
                case 0 -> mainMenu();
                default -> System.out.println("Invalid option entered: " + option);
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = reportsMenu();
        }
    }

    private int WearableDeviceReportsMenu() {
        int option = ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                |------------------WearableDevice Reports Menu-------------------|
                |   1) List all technology                                       |
                |   2) List all SmartBands                                       |
                |   3) List all SmartWatches                                     |
                |   4) List all devices above a price                            |
                |   5) List all devices below a price                            |
                |   6) List the top five most expensive smart watches            |
                |   7) List the top five most expensive wearable devices         |
                |   8) List all devices for a chosen manufacturer                |
                |   0) Return to main menu                                       |
                ------------------------------------------------------------------
                ==>>  """);
        return option;
    }

    private void runWearableDeviceReportsMenu() {
        int option = WearableDeviceReportsMenu();

        while (option != 0) {

            switch (option) {
                case 1 -> listAllTechnology();
                case 2 -> listAllSmartBandsMenu();
                case 3 -> listAllSmartWatchesMenu();
                case 4 -> listDevicesAboveAPrice();
                case 5 -> listDevicesBelowAPrice();
                case 6 -> listMostExpensiveSmartWatches();
                case 7 -> listMostExpensiveWearableDevices();
                case 8 -> listAllDevicesByAManufacturer();
                case 0 -> mainMenu();
                default -> System.out.println("Invalid option entered: " + option);
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = WearableDeviceReportsMenu();
        }
    }

    private void listAllDevicesByAManufacturer() {
        String manufacturer = ScannerInput.readNextLine("Please enter a manufacturer name to search by: ");
        System.out.println(wearableDeviceAPI.numberOfWearableDeviceByChosenManufacturer(manufacturer));

    }

    private void listMostExpensiveWearableDevices() {
        System.out.println(wearableDeviceAPI.topFiveMostExpensiveWearableDevice());

    }

    private void listMostExpensiveSmartWatches() {

    }

    private void listDevicesBelowAPrice() {
        double price = ScannerInput.readNextDouble("View the WearableDevices costing more les this price:  ");
        System.out.println(wearableDeviceAPI.listWearableDeviceBelowPrice(price));

    }

    private void listDevicesAboveAPrice() {
        double price = ScannerInput.readNextDouble("View the WearableDevices costing more than this price:  ");
        System.out.println(wearableDeviceAPI.listWearableDeviceAbovePrice(price));

    }

    private void listAllSmartWatchesMenu() {
        System.out.println("List of SmartWatches:");
        System.out.println(wearableDeviceAPI.listAllSmartWatches());

    }

    private void listAllSmartBandsMenu() {
        System.out.println("List of SmartBands:");
        System.out.println(wearableDeviceAPI.listAllSmartBands());
    }

    private void listAllTechnology() {
        System.out.println("List of WearableDevices:");
        System.out.println(wearableDeviceAPI.listAllWearableDevices());

    }


    private int wearableDeviceCrudMenu()  {
            int option = ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                |--------------------WearableDevice Store Menu-------------------|
                |   1) Add a WearableDevice                                      |
                |   2) Delete a Wearable Device by Index                         |
                |   3) Delete a Wearable Device by ID                            |
                |   4) List all Wearable Devices                                 |
                |   5) Update a Wearable Device                                  |
                |   0) Return to main menu                                       |
                ------------------------------------------------------------------
                ==>>  """);
            return option;
        }
        private void runCrudMenu() {
            int option = wearableDeviceCrudMenu();

            while (option != 0) {

                switch (option) {
                    case 1 -> addAWearableDevice();
                    case 2 -> deleteAWearableDeviceByIndex();
                    case 3 -> deleteAWearableDeviceById();
                    case 4 -> listAllTechnology();
                    case 5 -> updateAWearableDevice();
                    case 0 -> mainMenu();
                    default -> System.out.println("Invalid option entered: " + option);
                }

                //pause the program so that the user can read what we just printed to the terminal window
                ScannerInput.readNextLine("\nPress enter key to continue...");

                //display the main menu again
                option = wearableDeviceCrudMenu();
            }
        }

    private void updateAWearableDevice() {

        if (wearableDeviceAPI.numberOfWearableDevices() > 0) {
            boolean isUpdated = false;

            int option = ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                |--------------------WearableDevice Store Menu-------------------|
                |   1) Update a SmartWatch                                       |
                |   2) Update a SmartBand                                        |
                |   0) Return to main menu                                       |
                ------------------------------------------------------------------
                ==>>  """);


            switch (option) {
                case 1 -> {
                    //ask the user to enter the index of the object to update, and assuming it's valid and is a MessagePost,
                    //gather the new data from the user and update the selected object.
                    listAllSmartWatchesMenu();
                    if (wearableDeviceAPI.numberOfSmartWatch() > 0) {
                        int smartWatchIndex = ScannerInput.readNextInt("Enter the index of the SmartWatch to update ==> ");
                        if (wearableDeviceAPI.isValidIndex(smartWatchIndex)) {
                            String size = ScannerInput.readNextLine("Enter the size of the WearableDevice:  ");
                            double price = ScannerInput.readNextDouble("Enter the price of the WearableDevice:  ");
                            String manufacturerName = ScannerInput.readNextLine("Enter the manufacturer name of the WearableDevice: ");
                            String material = ScannerInput.readNextLine("Enter the material of the WearableDevice:  ");
                            String modelName = ScannerInput.readNextLine("Enter the model name of the WearableDevice:   ");
                            String id = ScannerInput.readNextLine("Enter the ID of the WearableDevice:  ");
                            String displayType = ScannerInput.readNextLine("Enter the display type of the WearableDevice:   ");
                            //pass the index of the product and the new product details to Store for updating and check for success.
                            isUpdated = wearableDeviceAPI.updateSmartWatch(smartWatchIndex, size, price, manufacturerName, material, modelName, id, displayType);
                        }
                    }
                }
                case 2 -> {
                    //ask the user to enter the index of the object to update, and assuming it's valid and is a PhotoPost,
                    //gather the new data from the user and update the selected object.
                    listAllSmartBandsMenu();
                    if (wearableDeviceAPI.numberOfSmartBands() > 0) {
                        int smartBandIndex = ScannerInput.readNextInt("Enter the index of the SmartBand to update ==> ");
                        if (wearableDeviceAPI.isValidIndex(smartBandIndex)) {
                            String size = ScannerInput.readNextLine("Enter the size of the WearableDevice:  ");
                            double price = ScannerInput.readNextDouble("Enter the price of the WearableDevice:  ");
                            String manufacturerName = ScannerInput.readNextLine("Enter the manufacturer name of the WearableDevice: ");
                            String material = ScannerInput.readNextLine("Enter the material of the WearableDevice:  ");
                            String modelName = ScannerInput.readNextLine("Enter the model name of the WearableDevice:   ");
                            String id = ScannerInput.readNextLine("Enter the ID of the WearableDevice:  ");
                            char heartRateMonitor = ScannerInput.readNextChar("Does the WearableDevice have a heart rate monitor Y or N:    ");
                            //pass the index of the product and the new product details to Store for updating and check for success.
                            isUpdated = wearableDeviceAPI.updateSmartBand(smartBandIndex, size, price, manufacturerName, material, modelName, id, Utilities.YNtoBoolean(heartRateMonitor));
                        }
                    }
                }

                default -> System.out.println("Invalid option entered: " + option);
            }

            if (isUpdated) {
                System.out.println("Post Updated Successfully");
            } else {
                System.out.println("No Post Updated");
            }
        }
        else{
            System.out.println("No posts added yet");
        }
    }

    private void deleteAWearableDeviceById() {
        wearableDeviceAPI.numberOfWearableDevices();
        if (wearableDeviceAPI.numberOfWearableDevices() > 0){
            //only ask the user to choose the message post to delete if posts exist
            String iDToDelete = ScannerInput.readNextLine("Enter the ID of the post to delete ==> ");
            //pass the index of the message post to NewsFeed for deleting and check for success.
            WearableDevice wearAbleDeviceToDelete = wearableDeviceAPI.deleteWearableDeviceId(iDToDelete);
            if (wearAbleDeviceToDelete != null){
                System.out.println("Delete Successful! Deleted post: " + wearAbleDeviceToDelete);
            }
            else{
                System.out.println("Delete NOT Successful");
            }
        }
    }

    private void deleteAWearableDeviceByIndex() {
        wearableDeviceAPI.numberOfWearableDevices();
        if (wearableDeviceAPI.numberOfWearableDevices() > 0){
            //only ask the user to choose the message post to delete if posts exist
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the post to delete ==> ");
            //pass the index of the message post to NewsFeed for deleting and check for success.
            WearableDevice wearAbleDeviceToDelete = wearableDeviceAPI.deleteWearableDeviceByIndex(indexToDelete);
            if (wearAbleDeviceToDelete != null){
                System.out.println("Delete Successful! Deleted post: " + wearAbleDeviceToDelete);
            }
            else{
                System.out.println("Delete NOT Successful");
            }
        }
    }

    private void addAWearableDevice() {
        boolean isAdded = false;

        int option = ScannerInput.readNextInt("""
                    ---------------------------
                    |   1) Add a SmartWatch   |
                    |   2) Add a SmartBand    |
                    |   0) Main Menu          |
                    ---------------------------
                    ==>> """);

        switch (option) {
            case 1 -> {
                String size = ScannerInput.readNextLine("Enter the size of the WearableDevice:  ");
                double price = ScannerInput.readNextDouble("Enter the price of the WearableDevice:  ");
                String manufacturerName = ScannerInput.readNextLine("Enter the manufacturer name of the WearableDevice: ");
                String material = ScannerInput.readNextLine("Enter the material of the WearableDevice:  ");
                String modelName = ScannerInput.readNextLine("Enter the model name of the WearableDevice:   ");
                String id = ScannerInput.readNextLine("Enter the ID of the WearableDevice:  ");
                String displayType = ScannerInput.readNextLine("Enter the display type of the WearableDevice:   ");
                isAdded = wearableDeviceAPI.addWearableDevice(new SmartWatch(size, price, manufacturerName, material, modelName,id, displayType) {
                });
            }
            case 2 -> {
                String size = ScannerInput.readNextLine("Enter the size of the WearableDevice:  ");
                double price = ScannerInput.readNextDouble("Enter the price of the WearableDevice:  ");
                String manufacturerName = ScannerInput.readNextLine("Enter the manufacturer name of the WearableDevice: ");
                String material = ScannerInput.readNextLine("Enter the material of the WearableDevice:  ");
                String modelName = ScannerInput.readNextLine("Enter the model name of the WearableDevice:   ");
                String id = ScannerInput.readNextLine("Enter the ID of the WearableDevice:  ");
                char heartRateMonitor = ScannerInput.readNextChar("Does the WearableDevice have a heart rate monitor Y or N");
                isAdded = wearableDeviceAPI.addWearableDevice(new SmartBand(size, price, manufacturerName, material, modelName,id, Utilities.YNtoBoolean(heartRateMonitor)) {
                });
            }
            case 0 -> { mainMenu();
            }

            default -> System.out.println("Invalid option entered: " + option);
        }

        if (isAdded){
            System.out.println("WearableDevice added Successfully");
        }
        else{
            System.out.println("No WearableDevice Added");
        }

    }


    private void exitApp() {

        System.out.println("Exiting....");
        System.exit(0);
    }







    //todo update methods counting methods


    //---------------------
    //  General Menu Items
    //---------------------

//TODO - write all the methods that are called from your menu
    //---------------------
    //  Search/Sort
    //---------------------
// TODO search by different criteria i.e. look at the list methods and give options based on that.
// TODO sort  (and give a list of options - not a recurring menu thou)
    //---------------------
    //  Helper Methods
    //---------------------

//TODO- write any helper methods that are required




}

