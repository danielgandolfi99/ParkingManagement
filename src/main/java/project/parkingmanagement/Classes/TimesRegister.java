package project.parkingmanagement.Classes;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimesRegister extends VehicleRegister {
    private int time_id;
    private int vehicle_id;
    private Timestamp entry_time;
    private Timestamp exit_time;

    public TimesRegister(int time_id, int vehicle_id, Timestamp entry_time, Timestamp exit_time) {
        this.time_id = time_id;
        this.vehicle_id = vehicle_id;
        this.entry_time = entry_time;
        this.exit_time = exit_time;
    }

    public TimesRegister(int vehicle_id, String plate, String manufacturer, String model, String color, String year, int time_id, int vehicle_id1, Timestamp entry_time, Timestamp exit_time) {
        super(vehicle_id, plate, manufacturer, model, color, year);
        this.time_id = time_id;
        this.vehicle_id = vehicle_id1;
        this.entry_time = entry_time;
        this.exit_time = exit_time;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getEntry_time() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(entry_time);
    }

    public Timestamp getNoFormattingEntryTime() {
        return entry_time;
    }

    public void setEntry_time(Timestamp entry_time) {
        this.entry_time = entry_time;
    }

    public String getExit_time() {
        if (exit_time != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return sdf.format(exit_time);
        } else {
            return "";
        }
    }

    public Timestamp getNoFormattingExitTime() {
        return exit_time;
    }

    public void setExit_time(Timestamp exit_time) {
        this.exit_time = exit_time;
    }

}
