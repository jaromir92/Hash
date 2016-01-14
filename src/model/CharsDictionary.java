package model;

import java.util.ArrayList;
import java.util.List;

public class CharsDictionary {

    private static final int BIT_LENGTH_OF_CHAR = 6;
    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789$=";

    private final List<String> charList;

    public CharsDictionary() {
        this.charList = new ArrayList<>(ALLOWED_CHARS.length());
        for(int index = 0; index < ALLOWED_CHARS.length(); index++) {
            this.charList.add(Character.toString(ALLOWED_CHARS.charAt(index)));
        }
    }

    public String getChar(int index) {
        int indexChar;
        if(index >= ALLOWED_CHARS.length() - 1) {
            indexChar = 0;
        }
        else {
            indexChar = index;
        }
        return this.charList.get(indexChar);
    }

    public static int getBitLengthOfChar() {
        return BIT_LENGTH_OF_CHAR;
    }
}