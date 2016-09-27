package de.pascal25565.WriteFaster.Utils;

public class RandomGen {

	public static enum Change {
		mitzahlen
	}

	public static StringBuffer buffer = new StringBuffer();
	static String characters = "";

	public static String getRandomText(int length, Change change) throws Exception {
		switch (change) {
		case mitzahlen:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;
		}
		int charactersLength = characters.length();
		for (int i = 0; i < length; i++) {
			double string = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) string));
		}
		return buffer.toString();
	}
}
