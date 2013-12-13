package cz.muni.fi.pa165.sportactivitymanager.dao;

import cz.muni.fi.pa165.sportactivitymanager.SportRecord;
import cz.muni.fi.pa165.sportactivitymanager.dao.impl.SportRecordDAOImpl;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Petr Jelínek
 */
public class SportRecordDAOImplTest {

    private SportRecordDAO sportRecordDAO;
    private EntityManager em;

    @Before
    public void SetUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SportActivityTestInMemory-PU");
        em = emf.createEntityManager();
        sportRecordDAO = new SportRecordDAOImpl(em);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateEmpty() {
        SportRecord sr = new SportRecord();
        em.getTransaction().begin();
        sportRecordDAO.create(sr);
        em.getTransaction().commit();
        if (sr.getId() == null) {
            fail("Fail due to empty Record");
        }
    }

    @Test
    public void testCreateNullSportRecord() {
        try {
            em.getTransaction().begin();
            sportRecordDAO.create(null);
            em.getTransaction().commit();
            fail("Create was called with null Record");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testDeleteNullRecord() {
        SportRecord sr1 = null;
        em.getTransaction().begin();
        try {

            sportRecordDAO.delete(sr1);

            fail("Create was called with null Record");
        } catch (NullPointerException ex) {
        }
        em.getTransaction().commit();
    }

    @Test
    public void testDeleteNonExistRecord() {
        SportRecord sr2 = new SportRecord();
        sr2.setId(Long.MIN_VALUE);
        sr2.setDistance(10);
        sr2.setDuration(Long.MIN_VALUE);
        sr2.setStartTime(new Date());
        try {
            em.getTransaction().begin();
            sportRecordDAO.delete(sr2);
            em.getTransaction().commit();
            fail("Delete Activity is not exist in db");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testCreateAndGet() {
        SportRecord sr = new SportRecord();
        sr.setDistance(10);
        sr.setDuration(Long.MIN_VALUE);
        sr.setStartTime(new Date());
        em.getTransaction().begin();
        sportRecordDAO.create(sr);
        em.getTransaction().commit();
        assertNotNull(sr.getId());
        Long srId = sr.getId();

        SportRecord sr2fromDB = sportRecordDAO.getSportRecord(srId);

        assertEquals(sr, sr2fromDB);

        assertSame(sr, sr2fromDB);

        assertEquals(sr.getId(), sr2fromDB.getId());
        assertEquals(sr.getDistance(), sr2fromDB.getDistance());
        assertEquals(sr.getDuration(), sr2fromDB.getDuration());
        assertEquals(sr.getStartTime(), sr2fromDB.getStartTime());
    }

    @Test
    public void testFindAll() {
        SportRecord sr1 = new SportRecord();
        SportRecord sr2 = new SportRecord();
        em.getTransaction().begin();
        sportRecordDAO.create(sr1);
        sportRecordDAO.create(sr2);
        em.getTransaction().commit();
        List<SportRecord> all = sportRecordDAO.findAll();

        if (all.size() != 2) {
            fail("findAll dont return all activities");
        }
    }
}
