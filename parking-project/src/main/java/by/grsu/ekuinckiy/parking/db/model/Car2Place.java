package by.grsu.ekuinckiy.parking.db.model;

import java.sql.Timestamp;

public class Car2Place {
    private Integer id;
    
    private Integer placeId;
    
    private Integer carId;

    private Timestamp contractStart;
    
    private Timestamp contractEnd;
    
    public Timestamp getContractEnd() {
        return contractEnd;
    }

    public void setContractEnd(Timestamp contractEnd) {
        this.contractEnd = contractEnd;
    }

    public Timestamp getContractStart() {
        return contractStart;
    }

    public void setContractStart(Timestamp contractStart) {
        this.contractStart = contractStart;
    }
    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toString(){
        return "Car_2_Place [id=" + id + ", place id=" + placeId + ", car id=" + carId + ", contract start=" + contractStart + ", contract end=" + contractEnd + "]";
    }
}
