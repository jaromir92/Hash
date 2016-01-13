package model;

import java.util.ArrayList;
import java.util.List;

public class CharsDictionary {

    private static final int NUMBER_OF_CHARS = 64;
    private static final int BIT_LENGTH_OF_CHAR = 6;
    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789$=";

    private final List<String> charList;

    public CharsDictionary() {
        this.charList = new ArrayList<>(NUMBER_OF_CHARS);
        for(int index = 0; index < NUMBER_OF_CHARS; index++) {
            this.charList.add(Character.toString(ALLOWED_CHARS.charAt(index)));
        }
    }

    public String getChar(int index) {
        int indexChar;
        if(index >= NUMBER_OF_CHARS - 1) {
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