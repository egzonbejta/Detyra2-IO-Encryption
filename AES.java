package Detyra;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;

public class AES {
	byte[] skey = new byte[1000];
	String skeyString;
	static byte[] raw;
	private String inputMessage;// , encryptedData, decryptedMessage;
	String enc, dec;

	public AES(String input) {
		inputMessage = input;
		try {
			generateSymmetricKey();
			byte[] ibyte = inputMessage.getBytes();
			byte[] ebyte = encrypt(raw, ibyte);
			String encryptedData = new String(ebyte);
			// System.out.println("Encrypted message " + encryptedData);
			enc = encryptedData;
			byte[] dbyte = decrypt(raw, ebyte);
			String decryptedMessage = new String(dbyte);
			// System.out.println("Decrypted message " + decryptedMessage);
			dec = decryptedMessage;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void generateSymmetricKey() {
		try {
			Random r = new Random();
			int num = r.nextInt(10000);
			String knum = String.valueOf(num);
			byte[] knumb = knum.getBytes();
			skey = getRawKey(knumb);
			skeyString = new String(skey);
			System.out.println("AES Symmetric key = " + skeyString);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static byte[] getRawKey(byte[] seed) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(seed);
		kgen.init(128, sr); // 192 and 256 bits may not be available
		SecretKey skey = kgen.generateKey();
		raw = skey.getEncoded();
		return raw;
	}

	private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
	}

	private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}

	public String getEncryption() {
		return enc;
	}

	public String getDecryption() {
		return dec;
	}
}