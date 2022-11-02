package by.grsu.ekuinckiy.parking.db.model;

public class Model {
    private Integer id;
    
    private String name;
    
    private Integer brandId;
    
    public Integer getBrandId() {
        return brandId;
    }
    public void setBrandId(Integer brand_id) {
        this.brandId = brand_id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String toString(){
        return "Model [id=" + id + ", drand id=" + brandId + ", name=" + name + "]";
    }
}
