/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.service;

import cz.muni.fi.pa165.sportactivitymanager.dto.SportRecordDTO;
import java.util.List;

/**
 *
 * @author Phaser
 */
public interface SportRecordService {

    /**
     * Creates new SportRecord
     *
     * @param sportRecordTO SportRecordTO that we store.
     * @throws NullPointerException when argument is Null.
     * @return nothing.
     */
    void create(SportRecordDTO sportRecordTO);

    /**
     * Find existing SportRecordTO
     *
     * @param id is ID of SportRecordTO that we want get.
     * @throws NullPointerException when ID is NULL.
     * @return SportRecordTO with same id, or Null when SportRecordTO does not
     * exist.
     */
    SportRecordDTO getSportRecord(Long id);

    /**
     * Delete existing SportRecord
     *
     * @param sportRecordTO is a SportRecordTO that we want to delete.
     * @throws IlleagalArgumentException when given SportRecordTO does not
     * exist.
     * @throws NullPointerException when given null.
     * @return nothing
     */
    void delete(SportRecordDTO sportRecordTO);

    /**
     * Delete existing SportRecordTO
     *
     * @param id is a id of SportRecordTO that we want to delete.
     * @throws IlleagalArgumentException when given SportRecordTO does not
     * exist.
     * @throws NullPointerException when given null.
     * @return nothing
     */
    void delete(Long id);

    /**
     * Update existing sportRecord
     *
     * @param sportRecordTO is a SportRecordTO that we want to update.
     * @throws IlleagalArgumentException when given sportRecord does not exist.
     * @throws NullPointerException when given null.
     * @return nothing
     */
    void update(SportRecordDTO sportRecordTO);

    /**
     * Find all existing sportRecordTO
     *
     * @param nothing
     * @return List of all sportRecordTO
     */
    List<SportRecordDTO> findAll();
}
