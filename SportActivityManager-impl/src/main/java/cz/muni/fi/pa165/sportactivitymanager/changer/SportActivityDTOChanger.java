package cz.muni.fi.pa165.sportactivitymanager.changer;

import cz.muni.fi.pa165.sportactivitymanager.SportActivity;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petr Jelínek
 */
public class SportActivityDTOChanger {

    public static SportActivity dtoToEntity(SportActivityDTO activity) {
        if (activity == null) {
            return null;
        }

        SportActivity sportActivity = new SportActivity();
        sportActivity.setId(activity.getId());
        sportActivity.setName(activity.getName());
        sportActivity.setCalories(CaloriesTableDTOChanger.dtoToEntity(activity.getCalories()));

        return sportActivity;
    }

    public static SportActivityDTO entityToDTO(SportActivity activity) {
        if (activity == null) {
            return null;
        }

        SportActivityDTO sportActivityDTO = new SportActivityDTO();
        sportActivityDTO.setId(activity.getId());
        sportActivityDTO.setName(activity.getName());
        sportActivityDTO.setCalories(CaloriesTableDTOChanger.entityToDTO(activity.getCalories()));

        return sportActivityDTO;
    }

    public static List<SportActivityDTO> entityListToDTOList(List<SportActivity> activities) {
        if (activities == null) {
            return null;
        }

        List<SportActivityDTO> activitiesDTOList = new ArrayList<SportActivityDTO>();

        for (SportActivity activity : activities) {
            activitiesDTOList.add(SportActivityDTOChanger.entityToDTO(activity));
        }

        return activitiesDTOList;
    }
}
