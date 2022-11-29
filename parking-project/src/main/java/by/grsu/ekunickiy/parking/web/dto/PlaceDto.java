package by.grsu.ekunickiy.parking.web.dto;

public class PlaceDto {
    private Integer id;
    
    private Integer busy;
    
    public Integer getBusy() {
        return busy;
    }

    public void setBusy(Integer busy) {
        this.busy = busy;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
}
