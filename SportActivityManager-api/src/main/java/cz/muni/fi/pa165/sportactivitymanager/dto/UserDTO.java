package cz.muni.fi.pa165.sportactivitymanager.dto;

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
        return "UserDTO name = " + firstName + lastName
                + ", id = " + id
                + ", gender = " + gender
                + ", Birdthday = " + birthDay;
    }
}
