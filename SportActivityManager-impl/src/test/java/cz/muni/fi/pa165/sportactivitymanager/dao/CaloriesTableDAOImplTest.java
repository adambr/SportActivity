
package cz.muni.fi.pa165.sportactivitymanager.dao;

import cz.muni.fi.pa165.sportactivitymanager.CaloriesTable;
import cz.muni.fi.pa165.sportactivitymanager.dto.Gender;
import cz.muni.fi.pa165.sportactivitymanager.dao.impl.CaloriesTableDAOImpl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michal Galo
 */
public class CaloriesTableDAOImplTest
{
    private CaloriesTableDAO caloriesTableDAO;
    private EntityManager em;    
    
    @Before
    public void SetUp()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SportActivityTestInMemory-PU");
        em = emf.createEntityManager();
        caloriesTableDAO = new CaloriesTableDAOImpl(em);
    }    
    
    @After
    public void tearDown() { }
    
    @Test
    public void testCreateEmpty() 
    {
        CaloriesTable tab = new CaloriesTable();
        tab.setGender(Gender.MALE);
        
        em.getTransaction().begin();
        caloriesTableDAO.create(tab);
        em.getTransaction().commit();

        if (tab.getId() == null) { fail("Fail due to empty CaloriesTable"); }
    }
    
        
    @Test(expected = NullPointerException.class)
    public void testCreateNull()
    {       
        em.getTransaction().begin();
        caloriesTableDAO.create(null);    
        em.getTransaction().commit();
        
        fail("Create was called with null CaloriesTable");
    }
    
    @Test
    public void testCreateAndGet()
    {
        CaloriesTable ct = new CaloriesTable();
        ct.setCalories60Kg(384);
        ct.setCalories70Kg(457);
        ct.setCalories80Kg(531);
        ct.setCalories90Kg(605);
        ct.setGender(Gender.MALE);  
        
        em.getTransaction().begin();
        caloriesTableDAO.create(ct);
        em.getTransaction().commit();
        
        assertNotNull(ct.getId());
        Long ctID = ct.getId();
        
        CaloriesTable ctDB = caloriesTableDAO.get(ctID);
        assertEquals(ct, ctDB);
        assertSame(ct, ctDB);
        
        assertEquals(ct.getId(), ctDB.getId());
        assertEquals(ct.getCalories60Kg(), ctDB.getCalories60Kg());
        assertEquals(ct.getCalories70Kg(), ctDB.getCalories70Kg());
        assertEquals(ct.getCalories80Kg(), ctDB.getCalories80Kg());
        assertEquals(ct.getCalories90Kg(), ctDB.getCalories90Kg());
        assertEquals(ct.getGender(), ctDB.getGender());       
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetNull()
    {        
        caloriesTableDAO.get(null);        
        
        fail("CaloriesTable ID is Null");
    }
    
    @Test
    public void testGet()
    {
        
        CaloriesTable ct1 = new CaloriesTable();
        ct1.setCalories60Kg(384);
        ct1.setCalories70Kg(457);
        ct1.setCalories80Kg(531);
        ct1.setCalories90Kg(605);
        ct1.setGender(Gender.MALE);        

        CaloriesTable ct2 = new CaloriesTable();
        ct2.setCalories60Kg(413);
        ct2.setCalories70Kg(493);
        ct2.setCalories80Kg(572);
        ct2.setCalories90Kg(651);
        ct2.setGender(Gender.FEMALE);
                        
        em.getTransaction().begin();
        caloriesTableDAO.create(ct1);
        caloriesTableDAO.create(ct2);
        em.getTransaction().commit();

        Long id = ct1.getId();
        CaloriesTable ctDB = caloriesTableDAO.get(id);

        assertEquals(ctDB.getId(), ct1.getId());
        assertSame(ct1, ctDB);
        assertNotSame(ct2, ctDB);

        assertEquals(ct1.getId(), ctDB.getId());
        assertEquals(ct1.getCalories60Kg(), ctDB.getCalories60Kg());
        assertEquals(ct1.getCalories70Kg(), ctDB.getCalories70Kg());
        assertEquals(ct1.getCalories80Kg(), ctDB.getCalories80Kg());
        assertEquals(ct1.getCalories90Kg(), ctDB.getCalories90Kg());
        assertEquals(ct1.getGender(), ctDB.getGender()); 
    }    
    
    @Test(expected = NullPointerException.class)
    public void testUpdateNull()
    {
        em.getTransaction().begin();
        caloriesTableDAO.update(null);
        em.getTransaction().commit();      
        
        fail("It is NOT possible to update NULL user");
    }
    
    @Test
    public void testUpdate()
    {
        
    }
    
    
    @Test(expected = NullPointerException.class)
    public void testDeleteIdNull()
    {
        Long id = null;
        caloriesTableDAO.delete(id);       
    }
    
    @Test
    public void testDeleteId()
    {
        CaloriesTable ct = new CaloriesTable();
        ct.setCalories60Kg(295);
        ct.setCalories70Kg(352);
        ct.setCalories80Kg(409);
        ct.setCalories90Kg(465);
        ct.setGender(Gender.MALE);
        caloriesTableDAO.create(ct);
        
    }
    
    @Test(expected = NullPointerException.class)
    public void testDeleteNull()
    {
        CaloriesTable ct = null;
        caloriesTableDAO.delete(ct);
        
    }
    
    @Test
    public void testDelete()
    {
        
    }
    
    
    
    @Test
    public void testFindAll()
    {
        CaloriesTable ct1 = new CaloriesTable();
        ct1.setCalories60Kg(384);
        ct1.setCalories70Kg(457);
        ct1.setCalories80Kg(531);
        ct1.setCalories90Kg(605);
        ct1.setGender(Gender.MALE);        

        CaloriesTable ct2 = new CaloriesTable();
        ct2.setCalories60Kg(413);
        ct2.setCalories70Kg(493);
        ct2.setCalories80Kg(572);
        ct2.setCalories90Kg(651);
        ct2.setGender(Gender.FEMALE);
                        
        em.getTransaction().begin();
        caloriesTableDAO.create(ct1);
        caloriesTableDAO.create(ct2);
        em.getTransaction().commit();
        
        List<CaloriesTable> all = caloriesTableDAO.findAll();
        
        if (all.size() != 2) fail("Method findAll doesnot return all CaloriesTable.");              
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
