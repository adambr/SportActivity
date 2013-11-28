/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.changer;

import cz.muni.fi.pa165.sportactivitymanager.SportRecord;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportRecordDTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phaser
 */
public class SportRecordDTOChanger {

    //convert SportRecordTO to Entity SportRecord
    public static SportRecord DTOToEntity(SportRecordDTO sportRecordDTO) {
        
        if (null == sportRecordDTO) {
            return null;
        }
        
        SportRecord sportRecord = new SportRecord();
        
        sportRecord.setId(sportRecordDTO.getId());
        sportRecord.setDistance(sportRecordDTO.getDistance());
        sportRecord.setDuration(sportRecordDTO.getDuration());
        sportRecord.setStartTime(sportRecordDTO.getStartTime());
        sportRecord.setActivity(SportActivityDTOChanger.dtoToEntity(sportRecordDTO.getActivityDTO()));
        return sportRecord;
    }

    //convert SportRecord to Entity SportRecordTO
    public static SportRecordDTO entityToDTO(SportRecord sportRecord) {
        
        if (null == sportRecord) {
            return null;
        }
        
        SportRecordDTO sportRecordDTO = new SportRecordDTO();
        
        sportRecordDTO.setId(sportRecord.getId());
        sportRecordDTO.setDistance(sportRecord.getDistance());
        sportRecordDTO.setDuration(sportRecord.getDuration());
        sportRecordDTO.setStartTime(sportRecord.getStartTime());
        sportRecordDTO.setActivityDTO(SportActivityDTOChanger.entityToDTO(sportRecord.getActivity()));
        return sportRecordDTO;
    }
    
    public static List<SportRecordDTO> entityListToDTOList(List<SportRecord> sportRecord) {
        if (sportRecord == null) {
            return null;
        }
        
        List<SportRecordDTO> sportRecordTOList = new ArrayList<SportRecordDTO>();
        for (int i = 0; i < sportRecord.size(); i++) {
            sportRecordTOList.add(entityToDTO(sportRecord.get(i)));
        }
        return sportRecordTOList;
    }
    
    public static List<SportRecord> DTOListToEntityList(List<SportRecordDTO> sportRecordsDTO) {
        if (sportRecordsDTO == null) {
            return null;
        }
        
        List<SportRecord> sportRecords = new ArrayList<SportRecord>();
        for (SportRecordDTO sportRecordDTO : sportRecordsDTO) {
            sportRecords.add(DTOToEntity(sportRecordDTO));
        }
        return sportRecords;
    }
}
