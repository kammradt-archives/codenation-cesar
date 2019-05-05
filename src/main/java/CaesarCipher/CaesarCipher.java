package CaesarCipher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CaesarCipher {
    private String text;
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public CaesarCipher(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text
                .toLowerCase();
    }

    public void setText(String text) {
        this.text = text;
    }

    public char[] getTextAsCharArray(){
        return this.text.toCharArray();
    }

    public String decode(int shiftNumber){
        char[] encodedText = getTextAsCharArray();

        String decoded = "";
        for (int i = 0; i < encodedText.length; i++) {
            char character = encodedText[i];

            if (isCharacter(character)) {
                int indexOfLetter = getIndexOfLetter(shiftNumber, character);
                decoded += newCharacter(indexOfLetter);
            } else {
                decoded += character;
            }
        }

        return decoded;
    }

    private char newCharacter(int indexOfLetter) {
        return  indexOfLetter >= 0 ? ALPHABET.charAt(indexOfLetter) : ALPHABET.charAt(ALPHABET.toCharArray().length + indexOfLetter);
    }

    private int getIndexOfLetter(int shiftNumber, char character) {
        return ALPHABET.indexOf(character) - shiftNumber;
    }

    private boolean isCharacter(char character) {
        return this.ALPHABET.contains(String.valueOf(character));
    }

    public String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
