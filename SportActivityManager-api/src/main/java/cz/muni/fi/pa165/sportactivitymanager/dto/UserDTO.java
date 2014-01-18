package cz.muni.fi.pa165.sportactivitymanager.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dobes Kuba
 */
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String credentials;
    private String login;
    private Date birthDay;
    private Integer weight;
    private Gender gender;
    private List<SportRecordDTO> records;

    public UserDTO() {
    }

    public List<SportRecordDTO> getRecords() {
        return records;
    }

    public void setRecords(List<SportRecordDTO> records) {
        this.records = records;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        UserDTO other = (UserDTO) object;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "{" + "id=" + id 
                + ", name=" + firstName + " " + lastName 
                + ", birthday=" + dateFormat.format(birthDay) 
                + ", weight=" + weight 
                + ", gender=" + gender 
                + ", login=" + login
                + ", password=" + password
                + ", credentials=" + credentials
                + '}';
    }
}
