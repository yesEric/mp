package com.eric.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.eric.Constants;
import com.eric.dom.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserManagerTest extends BaseManagerTestCase {
    private Log log = LogFactory.getLog(UserManagerTest.class);
    @Autowired
    private UserManager mgr;

    private User user;

    @Test
    public void testGetUser() throws Exception {
        user = mgr.getUserByUsername("Liu");
        assertNotNull(user);
        
        log.debug(user);
       
    }

    @Test
    public void testSaveUser() throws Exception {
        user = mgr.getUserByUsername("Liu");
        user.setPhoneNumber("303-555-1212");

        log.debug("saving user with updated phone number: " + user);

        user = mgr.saveUser(user);
        assertEquals("303-555-1212", user.getPhoneNumber());
        
    }

    @Test
    public void testAddAndRemoveUser() throws Exception {
        user = new User();

        // call populate method in super class to populate test data
        // from a properties file matching this class name
        user = (User) populate(user);

        

        user = mgr.saveUser(user);
        assertEquals("john", user.getUsername());
        

        log.debug("removing user...");

        mgr.removeUser(user.getId().toString());

        try {
            user = mgr.getUserByUsername("john");
            fail("Expected 'Exception' not thrown");
        } catch (Exception e) {
            log.debug(e);
            assertNotNull(e);
        }
    }
}
