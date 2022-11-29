package by.grsu.ekunickiy.parking.db.model;

public class Car {
    private Integer id;
    
    private Integer ownerId;
    
    private Integer modelId;
    
    private String vin;
    
    public Integer getModelId() {
        return modelId;
    }
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }
    
    public Integer getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
    
    public String getVin() {
        return vin;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String toString(){
        return "Car [id=" + id + ", owner id=" + ownerId + ", model id=" + modelId + ", vin=" + vin + "]";
    }
}
