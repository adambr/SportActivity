package cz.muni.fi.pa165.sportactivitymanager.dao;

import cz.muni.fi.pa165.sportactivitymanager.SportActivity;
import cz.muni.fi.pa165.sportactivitymanager.dao.impl.SportActivityDAOImpl;
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
 * @author Adam Brauner
 */
public class SportActivityDAOImplTest {
    
    private SportActivityDAO sportActivityDao;
    private EntityManager em;
    
    @Before
    public void SetUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SportActivityTestInMemory-PU");
        this.em = emf.createEntityManager();
        sportActivityDao = new SportActivityDAOImpl(em);
    }

    @After
    public void tearDown() {
        
    }

    @Test
    public void testCreateEmpty() {
        SportActivity sa = new SportActivity();
        sa.setName("Archery");
        
        em.getTransaction().begin();
        sportActivityDao.create(sa);
        em.getTransaction().commit();

        if (sa.getId() == null) {
            fail("Fail during SportActivity create");
        }
    }
    
    @Test
    public void testCreateNullActivity() {
        try {
            em.getTransaction().begin();
            sportActivityDao.create(null);
            em.getTransaction().commit();
            fail("Create was called with null Activity");
        } catch (NullPointerException ex) {
        }
    }
    
    @Test
    public void testDeleteNullOrNonExistActivity() {
        SportActivity sa = null;
        em.getTransaction().begin();
        try {
            sportActivityDao.delete(sa);
            fail("Delete was called with null Activity");
        } catch (NullPointerException ex) {
        }
        
        SportActivity sa2 = new SportActivity();
        sa2.setName("Archery");
        sa2.setId(Long.MIN_VALUE);
        try {
            sportActivityDao.delete(sa2);
            fail("Delete Activity is not exist in db");
        } catch (IllegalArgumentException ex) {
        }
        em.getTransaction().commit();
    }
    
    @Test
    public void testCreateAndGet() {
        SportActivity sa = new SportActivity();
        sa.setName("Archery");

        em.getTransaction().begin();
        sportActivityDao.create(sa);
        em.getTransaction().commit();

        //ID can't be null
        assertNotNull(sa.getId());
        Long saId = sa.getId();

        SportActivity sa2fromDB = sportActivityDao.getSportActivity(saId);
        //are two objects equal?
        assertEquals(sa, sa2fromDB);
        //refer two object to the same object?
        assertSame(sa, sa2fromDB);

        assertEquals(sa.getId(), sa2fromDB.getId());
        assertEquals(sa.getName(), sa2fromDB.getName());
    }
    
    @Test
    public void testFindAll() {
        SportActivity sa = new SportActivity();
        sa.setName("Archery");
        SportActivity sa2 = new SportActivity();
        sa2.setName("Diving");

        em.getTransaction().begin();
        sportActivityDao.create(sa);
        sportActivityDao.create(sa2);
        em.getTransaction().commit();
        
        List<SportActivity> all = sportActivityDao.findAll();
        
        if (all.size() != 2)
            fail("findAll dont return all activities");
    }    
}
