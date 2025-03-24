package AAA.com.SW.EncryptorAndDecryptor;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SketchwareEncryptorAndDecryptor {
    private static final String AES_KEY = "sketchwaresecure";

    // Method to decrypt and return raw text (without JSON processing)
    public static String decryptFile(String filePath) {
        try {
            // Read encrypted file
            byte[] encryptedData = readFile(filePath);

            // Decrypt data
            byte[] decryptedData = decryptAES(encryptedData);

            // Convert bytes to string and return raw text
            return new String(decryptedData, StandardCharsets.UTF_8);

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Method to encrypt and save exactly like Sketchware

    public static String encryptAndSaveString(String inputText, String targetPath) {
        // Convert string to bytes and call the base method
        return encryptAndSave(inputText.getBytes(StandardCharsets.UTF_8), targetPath);
    }

    public static String encryptAndSaveFile(String unencryptedFilePath, String targetPath) {
        try {
            // Read the unencrypted file and call the base method
            return encryptAndSave(readFile(unencryptedFilePath), targetPath);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    public static String encryptAndSave(byte[] unencryptedData, String targetPath) {
        try {
            // Encrypt the data
            byte[] encryptedData = encryptAES(unencryptedData);
            
            // Write the encrypted data to target file
            writeFile(targetPath, encryptedData);
            
            return "Success: Saved to " + targetPath;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    






    private static byte[] readFile(String filePath) throws Exception {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return data;
    }

    private static void writeFile(String filePath, byte[] data) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(data);
        fos.close();
    }

    private static byte[] encryptAES(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = AES_KEY.getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, "AES"), new IvParameterSpec(keyBytes));
        return cipher.doFinal(data);
    }

    private static byte[] decryptAES(byte[] encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = AES_KEY.getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, "AES"), new IvParameterSpec(keyBytes));

        try {
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed: " + e.getMessage());
        }
    }
}