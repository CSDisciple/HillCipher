package com.csdisciple.hill_cipher;

import Jama.Matrix;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Logger;

/*
 * Step one: convert the message to a 2D array
 * Step two: convert key to a 2D array
 * Multiply 2Dx2D = result2DArray mod 26 (%26)
 * Then convert number array to letters
 * Output encrypted result as String
 * */

/*
 * To Decrypt
 * Take an inverse of the key matrix and then mod 26
 * multiply the inverse matrix by the encrypted message
 * Then mod 26 the matrix to get decrypted message
 *
 * */
@Service
public class HillCipherService {
    String key, encryptedMessage;
    Matrix keyMatrix, messageMatrix, encryptedKeyMatrix;
    char[] alphabet =  new char[26];

    Logger logger =  Logger.getLogger(HillCipherService.class.toString());

    public String encrypt(String message) throws Exception {
        logger.info("Starting to encrypt..." + message);
        int messageLength = message.length();
        String messageLowerCase = message.toLowerCase(Locale.ROOT);
        key = "gybnqkurp";
        keyMatrix = convertKeyToMatrix(key);
        messageMatrix = convertMessageToMatrix(messageLowerCase);
        encryptedKeyMatrix = keyMatrix.times(messageMatrix);
        encryptedKeyMatrix = mod26Matrix(encryptedKeyMatrix); // returning a matrix with zeros
        encryptedMessage = toString(encryptedKeyMatrix);


        // convert key to a messageLength x messageLength array
        // convert message to a 1 x message.length array
        // multiply them
        // mod  26 the result
        // convert array to a char array
        // return to user
        logger.info("Finished encrypting message..." + encryptedMessage);

        return encryptedMessage;
    }

    // inverse of the encrypted key matrix mod 26 * the encrypted message vector
    public String decrypt(String message) throws Exception {
        // get inverse keyMatrix mod 26
        // multiply by message

        logger.info("Decrypting message..." + message);
        Matrix matrix = keyMatrix;
        matrix = matrix.inverse();
        matrix = mod26Matrix(matrix);
        matrix = matrix.times(encryptedKeyMatrix);
         logger.info("Decrypted message..." + toString(matrix));
        return toString(matrix);
    }
    public int encryptCharToNumber(char letter) throws Exception {
        logger.info(String.valueOf(letter));
        if(isLetter(letter)){
            return Integer.valueOf(letter - 'a' + 1);
        }else{
            throw new Exception("Cannot encrypt an invalid character!");
        }
    }

    public char decryptNumberToChar(double number) throws Exception {
        logger.info(String.valueOf(number));
        if(number > 0 && number <=26){

            return Character.valueOf((char) (number + 'a' - 1));
        }else {
            throw new Exception("Number is invalid and cannot be decrypted!");
        }
    }


    private static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z');
    }
    // 5 x 1 returns
//    public Matrix mod26Matrix(Matrix matrix) {
//        Matrix ans = new Matrix(matrix.getRowDimension(), matrix.getColumnDimension());
//        for (int row = 0; row < matrix.getRowDimension(); row++) {
//            ans.set(row,0, matrix.get(row, 0) % 26);
//        }
//        return ans;
//    }

    public Matrix mod26Matrix(Matrix matrix) {
        Matrix ans = new Matrix(matrix.getRowDimension(), matrix.getColumnDimension());
        for (int row = 0; row < matrix.getRowDimension(); row++) {
            for (int col = 0; col < matrix.getColumnDimension(); col++) {
                ans.set(row,col, matrix.get(row, col) % 26);
            }

        }
        return ans;
    }

    // generate random key based on message size EX: given message size is 3, key size is 3^2
    public String generateRandomKey(int messageLength) throws Exception {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        int keySize = (int) Math.pow(messageLength, 2);
        for (int i = 0; i < keySize; i++) {
            sb.append(decryptNumberToChar(r.nextInt(27 - 1) + 1));
        }
        return sb.toString();
    }

    public Matrix convertKeyToMatrix(String key) throws Exception {
        int row = (int) Math.sqrt(key.length());
        int col = row;
        Matrix keyMatrix = new Matrix(row, col);
        int countStringIndex = 0;

        for (int i = 0; i < keyMatrix.getRowDimension(); i++) {
            for (int y = 0; y < keyMatrix.getColumnDimension(); y++) {
                // convert each char into int and save to matrix;
                keyMatrix.set(i, y, encryptCharToNumber(key.charAt(countStringIndex)));
                countStringIndex++; // keep going through the string;
            }
        }
        return keyMatrix;
    }

    public Matrix convertMessageToMatrix(String message) throws Exception {
        Matrix matrix = new Matrix(message.length(), 1);
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            matrix.set(i, 0, encryptCharToNumber(message.charAt(i)));
        }
        return matrix;
    }

    // we usually just want to toString a x by 1 matrix hence we are only concerned with the first column
    public String toString(Matrix matrix) throws Exception{
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < matrix.getRowDimension(); i++){
            sb.append(decryptNumberToChar(matrix.get(i, 0)));
        }
        return sb.toString();
    }

}
