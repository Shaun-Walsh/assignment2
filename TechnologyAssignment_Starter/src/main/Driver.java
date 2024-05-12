package main;

import controllers.WearableDeviceAPI;


import utils.ScannerInput;


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
    }

    private void saveProducts() {

    }

    private void sortWearableDevicesByPriceDescending() {
    }

    private void sortWearableDevicesByPriceAscending() {

    }

    private void searchWearableDevicesByID() {
    }

    private void searchWearableDevicesByIndex() {

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
                |   0) Return to main menu                                                     |
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
            option = reportsMenu();
        }
    }

    private void listAllDevicesByAManufacturer() {

    }

    private void listMostExpensiveWearableDevices() {

    }

    private void listMostExpensiveSmartWatches() {

    }

    private void listDevicesBelowAPrice() {

    }

    private void listDevicesAboveAPrice() {

    }

    private void listAllSmartWatchesMenu() {

    }

    private void listAllSmartBandsMenu() {
    }

    private void listAllTechnology() {

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
                    case 1 -> addAwearableDevice();
                    case 2 -> deleteAWearableDeviceByIndex();
                    case 3 -> deleteAWearableDeviceById();
                    case 4 -> listAllWearableDevicesMenu();
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
    }

    private void listAllWearableDevicesMenu() {

    }

    private void deleteAWearableDeviceById() {

    }

    private void deleteAWearableDeviceByIndex() {

    }

    private void addAwearableDevice() {

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

