package cz.muni.fi.pa165.sportactivitymanager.dependencyInjection;

import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.UserService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adam Brauner
 */
public class UserSpringInjectionTest extends BaseSpringInjectionTest {

    @Autowired
    private UserService sportService;

    @Test
    public void testCreateAndFind() {
        UserDTO userDto = new UserDTO();

        sportService.create(userDto);
        assertNotNull(userDto.getId());

        UserDTO sport2fromDB = sportService.getByID(userDto.getId());
        assertEquals(userDto, sport2fromDB);
    }
}
