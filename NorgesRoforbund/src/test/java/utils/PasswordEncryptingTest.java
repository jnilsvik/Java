package utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static utils.PasswordEncrypting.encrypt;

public class PasswordEncryptingTest {
    @Test
    public void testEncrypt() {
        assertEquals("C308375024AA2C143A307970A857EF47", encrypt("NorgesRoforbund2020"));
    }
}