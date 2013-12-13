package cz.muni.fi.pa165.sportactivitymanager;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Petr Jelínek
 */
@Entity
@Table(name = "Activity")
@NamedQueries({
    @NamedQuery(name = "findByName", query = "SELECT s FROM SportActivity s where s.name = :name"),
    @NamedQuery(name = "findAll", query = "SELECT s FROM SportActivity s")
})
public class SportActivity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.REMOVE)
    private CaloriesTable calories;
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SportRecord> records;

    public SportActivity() {
    }

    public SportActivity(String name, CaloriesTable calories) {
        this.name = name;
        this.calories = calories;
    }

    public List<SportRecord> getRecords() {
        return records;
    }

    public void setRecords(List<SportRecord> records) {
        this.records = records;
    }

    public CaloriesTable getCalories() {
        return calories;
    }

    public void setCalories(CaloriesTable calories) {
        this.calories = calories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SportActivity{" + "id=" + id + ", name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final SportActivity other = (SportActivity) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
