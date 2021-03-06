package cz.muni.fi.pa165.sportactivitymanager.dto;

import java.util.Date;

/**
 *
 * @author Adam Brauner
 */
public class SportRecordDTO {

    private Long id;
    private SportActivityDTO activityDTO;
    private Long duration;
    private int distance;
    private Date StartTime;

    public SportRecordDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SportActivityDTO getActivityDTO() {
        return activityDTO;
    }

    public void setActivityDTO(SportActivityDTO activity) {
        this.activityDTO = activity;
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

    @Override
    public String toString() {
        return "SportRecordDTO{" + "id=" + id + ", activityDTO=" + activityDTO + ", duration=" + duration + ", distance=" + distance + ", StartTime=" + StartTime + '}';
    }
}
