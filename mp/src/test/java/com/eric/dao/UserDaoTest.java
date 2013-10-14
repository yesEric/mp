package com.eric.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.ExpectedException;

import com.eric.dom.User;

public class UserDaoTest extends BaseDaoTestCase {
    @Autowired
    private UserDao dao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testGetUserInvalid() throws Exception {
        // should throw DataAccessException
        dao.get(1000L);
    }

    @Test
    public void testGetUser() throws Exception {
        User user = dao.get(-1L);

        assertNotNull(user);
       
    }



    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveUser() throws Exception {
        User user = new User();
        user.setUsername("Eric");
        user.setPassword("pas");
        
        user = dao.saveUser(user);
        flush();

        assertNotNull(user.getId());
        user = dao.get(user.getId());
        assertEquals("pas", user.getPassword());

        dao.remove(user);
        flush();

        // should throw DataAccessException
        dao.get(user.getId());
    }

    @Test
    public void testUserExists() throws Exception {
        boolean b = dao.exists(-1L);
        assertTrue(b);
    }

    @Test
    public void testUserNotExists() throws Exception {
        boolean b = dao.exists(111L);
        assertFalse(b);
    }

    @Test
    public void testUserSearch() throws Exception {
        // reindex all the data
        dao.reindex();

        List<User> found = dao.search("Liu");
        assertEquals(1, found.size());
        User user = found.get(0);
        assertEquals("Liu", user.getUsername());

        // test mirroring
        user = dao.get(-2L);
        user.setUsername("MattX");
        dao.saveUser(user);
        flush();
        flushSearchIndexes();

        // now verify it is reflected in the index
        found = dao.search("MattX");
        assertEquals(1, found.size());
        user = found.get(0);
        assertEquals("MattX", user.getUsername());
    }
}
