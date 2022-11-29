package by.grsu.ekunickiy.parking.db.model;

public class User {
    private Integer id;
    private String firstName;
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String toString(){
        return "User [id=" + id + ", first name=" + firstName + ", last name=" + lastName + "]";
    }
}
