/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.dto;

import cz.muni.fi.pa165.sportactivitymanager.SportRecord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phaser
 */
public class SportRecordDTOChanger {
    
    //convert SportRecordTO to Entity SportRecord
    public static SportRecord DTOToEntity(SportRecordDTO sportRecordTO) {
        
        if(null == sportRecordTO) return null;
        
        SportRecord sportRecord = new SportRecord();  
        
        sportRecord.setId(sportRecordTO.getId());
        sportRecord.setDistance(sportRecordTO.getDistance());
        sportRecord.setDuration(sportRecordTO.getDuration());
        sportRecord.setStartTime(sportRecordTO.getStartTime());        
        sportRecord.setUser(UserDTOChanger.dtoToUserEntity(sportRecordTO.getUserDTO()));
        return sportRecord;
    }
    
        //convert SportRecord to Entity SportRecordTO
    public static SportRecordDTO entityToDTO(SportRecord sportRecord) {
        
        if(null == sportRecord) return null;
        
        SportRecordDTO sportRecordTO = new SportRecordDTO();  
        
        sportRecordTO.setId(sportRecord.getId());
        sportRecordTO.setDistance(sportRecord.getDistance());
        sportRecordTO.setDuration(sportRecord.getDuration());
        sportRecordTO.setStartTime(sportRecord.getStartTime());        
        sportRecordTO.setUserDTO(UserDTOChanger.entityToDTO(sportRecord.getUser()));
        return sportRecordTO;
    }
    
    public static List<SportRecordDTO> entityListDTOToList( List<SportRecord> sportRecord ) {
        if(sportRecord == null) return null;
        
        List<SportRecordDTO> sportRecordTOList =  new ArrayList<SportRecordDTO>();
        for(int i = 0; i<sportRecord.size(); i++){
            sportRecordTOList.add(entityToDTO(sportRecord.get(i)));
        }
        return sportRecordTOList;
    }
    
    
    
}
