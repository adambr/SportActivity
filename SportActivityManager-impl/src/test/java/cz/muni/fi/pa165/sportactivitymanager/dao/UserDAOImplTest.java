package cz.muni.fi.pa165.sportactivitymanager.dao;

import cz.muni.fi.pa165.sportactivitymanager.dto.Gender;
import cz.muni.fi.pa165.sportactivitymanager.User;
import cz.muni.fi.pa165.sportactivitymanager.dao.impl.UserDAOImpl;
import static cz.muni.fi.pa165.sportactivitymanager.dao.UserDAOImplTest.AssertUserCompletely;
import java.util.Date;
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
 * @author Dobes Kuba
 */
public class UserDAOImplTest {

    private UserDAO userDao;
    private EntityManager em;

    @Before
    public void SetUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SportActivityTestInMemory-PU");
        em = emf.createEntityManager();
        userDao = new UserDAOImpl(em);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateEmpty() {
        User user = new User();
        em.getTransaction().begin();
        userDao.create(user);
        em.getTransaction().commit();
        if (user.getId() == null) {
            fail("Fail due to empty User");
        }
    }

    @Test
    public void testCreateNullUser() {
        User user = null;
        em.getTransaction().begin();
        try {
            userDao.create(user);

            fail("Create was called with null User");
        } catch (NullPointerException ex) {
        }
        em.getTransaction().commit();
    }

    @Test
    public void testCreate() {
        User user = new User();

        Date birthD3 = new Date(100, 10, 20);
        user.setBirthDay(birthD3);
        user.setFirstName("Matin");
        user.setLastName("Hajanek");
        user.setGender(Gender.MALE);
        user.setWeight(57);
        em.getTransaction().begin();
        userDao.create(user);
        em.getTransaction().commit();

        assertNotNull(user.getId());
        Long userId = user.getId();

        User user2fromDB = userDao.getByID(userId);

        assertEquals(user, user2fromDB);

        assertSame(user, user2fromDB);
        AssertUserCompletely(user, user2fromDB);
    }

    @Test
    public void testGet() {
        User user1 = new User();
        User user2 = new User();

        Date birthD1 = new Date(90, 3, 20);
        user1.setBirthDay(birthD1);
        user1.setFirstName("Brona");
        user1.setLastName("Kocu");
        user1.setWeight(120);
        user1.setGender(Gender.MALE);

        Date birthD2 = new Date(90, 3, 20);
        user2.setBirthDay(birthD2);
        user2.setFirstName("Brona");
        user2.setLastName("Kocu");
        user2.setWeight(120);
        user2.setGender(Gender.MALE);
        em.getTransaction().begin();
        userDao.create(user1);
        userDao.create(user2);
        em.getTransaction().commit();
        assertEquals(user1, userDao.getByID(user1.getId()));
        assertEquals(user2, userDao.getByID(user2.getId()));

        User getUserformDB = userDao.getByID(user1.getId());

        assertEquals(getUserformDB.getId(), user1.getId());

        if (getUserformDB.getId().equals(user2.getId())) {
            fail("Two different people with same atributes has same ID, but could not have.");
        }
        assertSame(getUserformDB, user1);

        AssertUserCompletely(user1, getUserformDB);

        try {
            userDao.getByID(null);
            fail("User id can not be Null");
        } catch (NullPointerException ex) {
        }

        try {
            userDao.getByID(Long.valueOf("-1"));
            fail("ID was set to negative number");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testFindAll() {
        System.out.println("test of findAll Users");

        User user1 = new User();
        Date birthD = new Date(89, 20, 10);
        user1.setBirthDay(birthD);
        user1.setFirstName("Kuba");
        user1.setGender(Gender.MALE);
        user1.setLastName("Dobe");

        User user2 = new User();
        Date birthD2 = new Date(89, 20, 10);
        user2.setBirthDay(birthD2);
        user2.setFirstName("Premysl Otakar");
        user1.setGender(Gender.MALE);
        user2.setLastName("Druhy");

        User user3 = new User();
        Date birthD3 = new Date(89, 20, 10);
        user3.setBirthDay(birthD3);
        user3.setFirstName("Vaclav");
        user1.setGender(Gender.MALE);
        user3.setLastName("Treti");
        em.getTransaction().begin();
        userDao.create(user1);
        userDao.create(user2);
        userDao.create(user3);
        em.getTransaction().commit();
        List<User> UserList = userDao.findAll();

        assertTrue(UserList.contains(user1));
        assertTrue(UserList.contains(user2));
        assertTrue(UserList.contains(user3));

        long listSize = UserList.size();
        assertEquals(3, listSize);

        User user1fromList = UserList.get(0);
        AssertUserCompletely(user1, user1fromList);

        User user2fromList = UserList.get(1);
        AssertUserCompletely(user2, user2fromList);

        User user3fromList = UserList.get(2);
        AssertUserCompletely(user3, user3fromList);
    }

    @Test
    public void testUpdate() {
        User user1 = new User();

        Date birthD1 = new Date(90, 3, 20);
        user1.setBirthDay(birthD1);
        user1.setFirstName("Brona");
        user1.setLastName("Stary");
        user1.setWeight(120);
        user1.setGender(Gender.MALE);
        em.getTransaction().begin();
        userDao.create(user1);
        em.getTransaction().commit();
        Date birthD2 = new Date(89, 3, 20);
        user1.setBirthDay(birthD2);
        user1.setFirstName("Honza");
        user1.setLastName("Novy");
        user1.setWeight(82);
        user1.setGender(Gender.MALE);
        em.getTransaction().begin();
        userDao.update(user1);
        em.getTransaction().commit();
        assertNotNull(user1.getId());
        User userFromDB = userDao.getByID(user1.getId());
        AssertUserCompletely(user1, userFromDB);

        try {
            userDao.update(null);
            fail("There is possible to update NULL user");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void TestDelete() {
        try {
            userDao.delete(null);
            fail("There is possible to delete NULL user");
        } catch (NullPointerException ex) {
        }

        User user1 = new User();

        Date birthD1 = new Date(90, 3, 20);
        user1.setBirthDay(birthD1);
        user1.setFirstName("Brona");
        user1.setLastName("Stary");
        user1.setWeight(120);
        user1.setGender(Gender.MALE);
        em.getTransaction().begin();
        userDao.create(user1);
        assertNotNull(userDao.getByID(user1.getId()));
        userDao.delete(user1);
        assertNull(userDao.getByID(user1.getId()));
        em.getTransaction().commit();
    }

    public static void AssertUserCompletely(User user1, User user2) {
        assertEquals(user1.getBirthDay(), user2.getBirthDay());
        assertEquals(user1.getGender(), user2.getGender());
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getLastName(), user2.getLastName());
        assertEquals(user1.getWeight(), user2.getWeight());
    }
}
