package cz.muni.fi.pa165.sportactivitymanager.changer;

import cz.muni.fi.pa165.sportactivitymanager.CaloriesTable;
import cz.muni.fi.pa165.sportactivitymanager.dto.CaloriesTableDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michal Galo
 */
public class CaloriesTableDTOChanger {

    public static CaloriesTable dtoToEntity(CaloriesTableDTO caloriesTableDTO) {
        if (caloriesTableDTO == null) {
            return null;
        }

        CaloriesTable caloriesTable = new CaloriesTable();
        caloriesTable.setId(caloriesTableDTO.getId());
        caloriesTable.setCalories60Kg(caloriesTableDTO.getCalories60Kg());
        caloriesTable.setCalories70Kg(caloriesTableDTO.getCalories70Kg());
        caloriesTable.setCalories80Kg(caloriesTableDTO.getCalories80Kg());
        caloriesTable.setCalories90Kg(caloriesTableDTO.getCalories90Kg());
        caloriesTable.setGender(caloriesTableDTO.getGender());

        return caloriesTable;
    }

    public static CaloriesTableDTO entityToDTO(CaloriesTable caloriesTable) {
        if (caloriesTable == null) {
            return null;
        }

        CaloriesTableDTO caloriesTableDTO = new CaloriesTableDTO();
        caloriesTableDTO.setId(caloriesTable.getId());
        caloriesTableDTO.setCalories60Kg(caloriesTable.getCalories60Kg());
        caloriesTableDTO.setCalories70Kg(caloriesTable.getCalories70Kg());
        caloriesTableDTO.setCalories80Kg(caloriesTable.getCalories80Kg());
        caloriesTableDTO.setCalories90Kg(caloriesTable.getCalories90Kg());
        caloriesTableDTO.setGender(caloriesTable.getGender());

        return caloriesTableDTO;
    }

    public static List<CaloriesTableDTO> entityListToDTOList(List<CaloriesTable> tables) {
        if (tables == null) {
            return null;
        }

        List<CaloriesTableDTO> tablesDTOList = new ArrayList<CaloriesTableDTO>();
        for (int i = 0; i < tables.size(); i++) {
            tablesDTOList.add(entityToDTO(tables.get(i)));
        }

        return tablesDTOList;
    }
}
