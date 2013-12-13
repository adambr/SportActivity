package cz.muni.fi.pa165.sportactivitymanager.dao.impl;

import cz.muni.fi.pa165.sportactivitymanager.SportRecord;
import cz.muni.fi.pa165.sportactivitymanager.dao.SportRecordDAO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Adam Brauner
 */
public class SportRecordDAOImpl implements SportRecordDAO {

    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {
        return em;
    }

    public SportRecordDAOImpl() {
    }

    public SportRecordDAOImpl(EntityManager em) {
        this.em = em;
    }

    public void create(SportRecord sportRecord) {
        if (sportRecord == null) {
            throw new NullPointerException();
        }

        em.persist(sportRecord);

    }

    public SportRecord getSportRecord(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        return em.find(SportRecord.class, id);
    }

    public void delete(SportRecord sportRecord) {
        if (sportRecord == null) {
            throw new NullPointerException();
        }

        this.delete(sportRecord.getId());
    }

    public void delete(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }

        SportRecord toDelete = em.find(SportRecord.class, id);

        if (toDelete == null) {
            throw new IllegalArgumentException("this entity does not exist in database.");
        }


        em.remove(toDelete);

    }

    public void update(SportRecord sportRecord) {
        if (sportRecord == null) {
            throw new NullPointerException();
        }
        SportRecord srUpd = em.find(SportRecord.class, sportRecord.getId());
        if (srUpd == null) {
            throw new IllegalArgumentException("This entity does not exist in database.");
        }

        srUpd.setActivity(sportRecord.getActivity());
        srUpd.setDistance(sportRecord.getDistance());
        srUpd.setDuration(sportRecord.getDuration());
        srUpd.setStartTime(sportRecord.getStartTime());

        em.persist(srUpd);
    }

    public List<SportRecord> findAll() {
        return em.createNamedQuery("findAllSportRecord", SportRecord.class)
                .getResultList();
    }
}
