package models;
import controllers.WearableDeviceAPI;
import utils.Utilities;
import utils.ManufacturerNameUtility;

import java.util.Objects;

public abstract class WearableDevice {
    private String size = "";
    private double price = 20;
    private String manufacturerName = "Unknown";
    private String material = "";
    private String modelName = "";
    private String id = "";


    /**
     * Constructor for objects of class models.WearableDevice
     * @param size Size of the wearable
     * @param price price of the wearable
     * @param manufacturerName Manufacturer of the wearable
     * @param material Material the wearable is made from
     * @param modelName Madel name of the wearable
     * @param id ID of the wearable
     */
    public WearableDevice (String size, double price, String manufacturerName, String material, String modelName, String id) {
    this.size = Utilities.truncateString(size, 10);
    this.material = Utilities.truncateString(material, 20);
    this.id = Utilities.truncateString(id,10);
    this.modelName = Utilities.truncateString(modelName,30);
    setPrice(price);
    setManufacturerName(manufacturerName);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if (Utilities.validStringlength(size, 10)){
        this.size = size;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 20)
            this.price = price;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        if (ManufacturerNameUtility.isValidManuName(manufacturerName)) {
            this.manufacturerName = manufacturerName;
        }
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (Utilities.validStringlength(material, 10)) {
            this.material = material;
        }
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        if (Utilities.validStringlength(modelName, 30)) {
            this.modelName = modelName;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (Utilities.validStringlength(id, 10)) {
            this.id = id;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WearableDevice that)) return false;
        return Double.compare(getPrice(), that.getPrice()) == 0 && Objects.equals(getSize(), that.getSize()) && Objects.equals(getManufacturerName(), that.getManufacturerName()) && Objects.equals(getMaterial(), that.getMaterial()) && Objects.equals(getModelName(), that.getModelName()) && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSize(), getPrice(), getManufacturerName(), getMaterial(), getModelName(), getId());
    }


    public abstract double getInsurancePremium();
    public abstract String connectToInternet();

    public String toString() {
        return "WearableDevice: " +
                "size: " + size +
                ", price: " + price +
                ", manufacturerName: " + manufacturerName +
                ", material: " + material  +
                ", modelName: " + modelName +
                ", id: " + id;
    }
}
