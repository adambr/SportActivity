/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.service.impl;

import cz.muni.fi.pa165.sportactivitymanager.DataAccException;
import cz.muni.fi.pa165.sportactivitymanager.SportRecord;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportRecordDTO;
import cz.muni.fi.pa165.sportactivitymanager.dao.SportRecordDAO;
import cz.muni.fi.pa165.sportactivitymanager.changer.SportRecordDTOChanger;
import cz.muni.fi.pa165.sportactivitymanager.service.SportRecordService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SportRecordServiceImpl implements SportRecordService {

    private SportRecordDAO sRDao;

    public void setSRDao(SportRecordDAO sRDao) {
        this.sRDao = sRDao;
    }

    @Transactional
    public void create(SportRecordDTO sportRecordTO) {
        if (sportRecordTO != null) {
            try {
                SportRecord sr = SportRecordDTOChanger.DTOToEntity(sportRecordTO);
                sRDao.create(sr);
                sportRecordTO.setId(sr.getId());
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("SportRecord can not be null.");
        }
    }

//    @Transactional
//    public SportRecordDTO getSportRecordByUser(User) {
//        SportRecordDTO sportRecordTO = null;
//
//        if (id != null) {
//            try {
//                SportRecord sportRecord = sRDao.getSportRecord(id);
//                sportRecordTO = SportRecordDTOChanger.entityToDTO(sportRecord);
//            } catch (Exception ex) {
//                throw new DataAccException(ex.toString());
//            }
//        } else {
//            throw new NullPointerException("SportRecord ID is Null");
//        }
//        return sportRecordTO;
//
//    }
        
    @Transactional
    public SportRecordDTO getSportRecord(Long id) {
        SportRecordDTO sportRecordTO = null;

        if (id != null) {
            try {
                SportRecord sportRecord = sRDao.getSportRecord(id);
                sportRecordTO = SportRecordDTOChanger.entityToDTO(sportRecord);
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("SportRecord ID is Null");
        }
        return sportRecordTO;

    }

    @Transactional
    public void delete(SportRecordDTO sportRecordTO) {
        if (sportRecordTO != null) {
            try {
                SportRecord sportRecord = SportRecordDTOChanger.DTOToEntity(sportRecordTO);
                sRDao.delete(sportRecord);
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("SportRecord can not be null.");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            try {
                sRDao.delete(id);
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("SportRecord ID is Null");
        }
    }
    
    @Transactional
    public void update(SportRecordDTO sportRecordTO) {
        if (sportRecordTO != null) {
            try {
                SportRecord sportRecord = SportRecordDTOChanger.DTOToEntity(sportRecordTO);
                sRDao.update(sportRecord);
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("User can not be null.");
        }
    }

    @Transactional
    public List<SportRecordDTO> findAll() {
        List<SportRecordDTO> sportRecordsTO = new ArrayList<SportRecordDTO>();
        List<SportRecord> sportRecords;
        try {
            sportRecords = sRDao.findAll();
            sportRecordsTO = SportRecordDTOChanger.entityListToDTOList(sportRecords);
        } catch (Exception ex) {
            throw new DataAccException(ex.toString());
        }
        return sportRecordsTO;
    }
}
