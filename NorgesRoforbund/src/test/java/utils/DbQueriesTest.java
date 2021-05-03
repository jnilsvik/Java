package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.DbQueries.getSuperuserEmail;

class DbQueriesTest {

    @Test
    void testGetSuperuserEmail() {
        assertEquals("nr@nr.com", getSuperuserEmail());
    }
}