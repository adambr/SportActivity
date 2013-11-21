/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.dto;
import java.util.Date;

/**
 *
 * @author Phaser
 */
public class SportRecordDTO {
    private Long id;
    private UserDTO user;
    //In seconds
    private Long duration;
    //in meters
    private int distance;
    private Date StartTime;

    public SportRecordDTO() {
    }
    
    public SportRecordDTO(Long id, UserDTO user, Long duration, int distance, Date StartTime) {
        this();
        this.id = id;
        this.user = user;
        this.duration = duration;
        this.distance = distance;
        this.StartTime = StartTime;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return user;
    }

    public void setUserDTO(UserDTO user) {
        this.user = user;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date StartTime) {
        this.StartTime = StartTime;
    }   

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SportRecordDTO other = (SportRecordDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    
}
