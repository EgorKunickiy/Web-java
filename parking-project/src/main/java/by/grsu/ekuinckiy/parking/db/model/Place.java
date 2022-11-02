package by.grsu.ekuinckiy.parking.db.model;

public class Place {
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

    public String toString(){
        return "Car_2_Place [id=" + id + ", busy=" + busy + "]";
    }
}
