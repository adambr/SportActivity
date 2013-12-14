package cz.muni.fi.pa165.sportactivitymanager.dto;

/**
 *
 * @author Petr Jelínek
 */
public class SportActivityDTO {

    private Long id;
    private String name;
    private CaloriesTableDTO calories;

    public CaloriesTableDTO getCalories() {
        return calories;
    }

    public void setCalories(CaloriesTableDTO calories) {
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
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final SportActivityDTO other = (SportActivityDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id 
                + ", name=" + name 
                + ", calories=" + calories 
                + '}';
    }
}
