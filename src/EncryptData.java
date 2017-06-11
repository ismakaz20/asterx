import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;
//import java.util.Vector;

/*class AES 
{
	private static String algorithm = "AES";
	private static byte[] keyValue = new byte[16];
	
	// Sets the secret key to user's password
	static void setSecretKey(String password)
	{
		StringBuilder pass = new StringBuilder(password);
		if(pass.length() < 16)
		{
			int i = 0;
			while(pass.length() != 16)
			{
				pass.append(pass.charAt(i));
				//System.out.println(pass);
				i++;
			}
			keyValue = pass.toString().getBytes();
		}
		else if(pass.length() > 16)
		{
			pass.delete(16, pass.length());
			System.out.println(pass);
			keyValue = pass.toString().getBytes();
		}
		else if(pass.length() == 16)
			keyValue = pass.toString().getBytes();
	}

	// Performs Encryption
	public static String encrypt(String plainText) throws Exception 
	{
		Key key = generateKey();
		Cipher chiper = Cipher.getInstance(algorithm);
		chiper.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = chiper.doFinal(plainText.getBytes());
		Base64.Encoder base64Encoder = Base64.getEncoder().withoutPadding();
		String encryptedValue = base64Encoder.encodeToString(encVal);
		return encryptedValue;
	}
	
	// Performs decryption
	public static String decrypt(String encryptedText) throws Exception 
	{
		// generate key 
		Key key = generateKey();
		Cipher chiper = Cipher.getInstance(algorithm);
		chiper.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = Base64.getDecoder().decode(encryptedText);
		byte[] decValue = chiper.doFinal(decodedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	//generateKey() is used to generate a secret key for AES algorithm
    private static Key generateKey() throws Exception 
    {
    	Key key = new SecretKeySpec(keyValue, algorithm);
    	return key;
    }
}*/

public class EncryptData 
{
	private static String algorithm = "AES";
	private static byte[] keyValue = new byte[16];
	static String password;
	
	// Sets the secret key to user's password
	static void setSecretKey(String password)
	{
		StringBuilder pass = new StringBuilder(password);
		if(pass.length() < 16)
		{
			int i = 0;
			while(pass.length() != 16)
			{
				pass.append(pass.charAt(i));
				//System.out.println(pass);
				i++;
			}
			keyValue = pass.toString().getBytes();
		}
		else if(pass.length() > 16)
		{
			pass.delete(16, pass.length());
			System.out.println(pass);
			keyValue = pass.toString().getBytes();
		}
		else if(pass.length() == 16)
			keyValue = pass.toString().getBytes();
	}

	// Performs Encryption
	public static String encrypt(String plainText) throws Exception 
	{
		Key key = generateKey();
		Cipher chiper = Cipher.getInstance(algorithm);
		chiper.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = chiper.doFinal(plainText.getBytes());
		Base64.Encoder base64Encoder = Base64.getEncoder().withoutPadding();
		String encryptedValue = base64Encoder.encodeToString(encVal);
		return encryptedValue;
	}
	
	// Performs decryption
	public static String decrypt(String encryptedText) throws Exception 
	{
		// generate key 
		Key key = generateKey();
		Cipher chiper = Cipher.getInstance(algorithm);
		chiper.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = Base64.getDecoder().decode(encryptedText);
		byte[] decValue = chiper.doFinal(decodedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	//generateKey() is used to generate a secret key for AES algorithm
    private static Key generateKey() throws Exception 
    {
    	Key key = new SecretKeySpec(keyValue, algorithm);
    	return key;
    }

}