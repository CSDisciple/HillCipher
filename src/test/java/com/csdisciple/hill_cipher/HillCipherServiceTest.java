package com.csdisciple.hill_cipher;

import org.junit.jupiter.api.Test;
import Jama.Matrix;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HillCipherServiceTest {
// tests are just too simplistic, only asserting length
    // need to test for edge cases
        // make sure we only work with lower case, non null, 1-26, a-z
            // nothing else
    @Autowired
    private HillCipherService service;

    @Test
    public void testDecryptChar() throws Exception {
        int encryptedChar = service.encryptCharToNumber('g');

        assertEquals('g', service.decryptNumberToChar(encryptedChar));
    }

    @Test
    public void encryptCharToNumber() throws Exception {
        int encryptedChar = service.encryptCharToNumber('z');
        assertEquals(26, encryptedChar);
    }

    @Test
    public void getRandomKey() throws Exception {
        int keyLength = 3;
        String randomKey = service.generateRandomKey(keyLength);
        assertEquals(Math.pow(keyLength, 2), randomKey.length());
    }
//
//    @Test
//    public void convertKeyToIntMatrix(){
//        int keyLength = 3;
//        String randomKey = service.generateRandomKey(keyLength);
//        Matrix matrix = service.convertKeyToIntMatrix(randomKey);
//
//        assertEquals(keyLength, matrix.length);
//
//
//    }
//
//    @Test
//    public void convertMessageToIntMatrix(){
//        int keyLength = 3;
//        // generates a key of length keyLength * keyLength
//        String randomKey = service.generateRandomKey(keyLength);
//        Matrix matrix = service.convertMessageToIntMatrix(randomKey);
//
//        assertEquals(randomKey.length(), matrix.length);
//    }

    @Test
    public void encrypt() throws Exception {
        String message = service.encrypt("hellomynameis");
        assertEquals(message.length(), "hellomynameis".length());
    }
    @Test
    public void decrypt() throws Exception {
        String message = service.decrypt("hellomynameis");
        assertEquals(message.length(), "hellomynameis".length());
    }

}
