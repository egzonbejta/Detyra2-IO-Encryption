package Detyra;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
	private static final String FILENAME = "lexo.txt";
	private static final String FILENAME2 = "shkruaj.txt";
	static String rreshti;

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			rreshti = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AES aes = new AES(rreshti);
		DES des = new DES(rreshti);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME2))) {
			bw.write("AES \n");
			bw.write("Teksti i enkriptuar: " + aes.getEncryption() + "\n");
			bw.write("Teksti i dekriptuar: " + aes.getDecryption() + "\n");
			bw.write("DES \n");
			bw.write("Teksti i enkriptuar: " + des.getEncryption() + "\n");
			bw.write("Teksti i dekriptuar: " + des.getDecryption() + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}