package project.parkingmanagement.Classes;

public class VehicleRegister {
    private int vehicle_id;
    private String plate;
    private String manufacturer;
    private String model;
    private String color;
    private String year;

    public VehicleRegister(){ }

    public VehicleRegister(int vehicle_id, String plate, String manufacturer, String model, String color, String year) {
        this.vehicle_id = vehicle_id;
        this.plate = plate;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
