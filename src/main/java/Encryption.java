import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    public static String encryptThisString(String input) {
        String hashtext = null;
        //Validate Input
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hashtext;
    }


}
