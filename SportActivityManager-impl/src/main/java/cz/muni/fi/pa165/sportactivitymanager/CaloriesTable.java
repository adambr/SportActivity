package cz.muni.fi.pa165.sportactivitymanager;

import cz.muni.fi.pa165.sportactivitymanager.dto.Gender;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Michal Galo
 */
@Entity
@Table(name = "calories_table")
@NamedQuery(name = "findAllCaloriesTables", query = "SELECT c FROM CaloriesTable c")
public class CaloriesTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int calories60Kg;
    private int calories70Kg;
    private int calories80Kg;
    private int calories90Kg;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    private Gender gender;

    public CaloriesTable() {
    }

    public CaloriesTable(int calories60Kg, int calories70Kg, int calories80Kg, int calories90Kg, Gender gender) {
        this.calories60Kg = calories60Kg;
        this.calories70Kg = calories70Kg;
        this.calories80Kg = calories80Kg;
        this.calories90Kg = calories90Kg;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCalories60Kg() {
        return calories60Kg;
    }

    public void setCalories60Kg(int calories60Kg) {
        this.calories60Kg = calories60Kg;
    }

    public int getCalories70Kg() {
        return calories70Kg;
    }

    public void setCalories70Kg(int calories70Kg) {
        this.calories70Kg = calories70Kg;
    }

    public int getCalories80Kg() {
        return calories80Kg;
    }

    public void setCalories80Kg(int calories80Kg) {
        this.calories80Kg = calories80Kg;
    }

    public int getCalories90Kg() {
        return calories90Kg;
    }

    public void setCalories90Kg(int calories90Kg) {
        this.calories90Kg = calories90Kg;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final CaloriesTable other = (CaloriesTable) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
