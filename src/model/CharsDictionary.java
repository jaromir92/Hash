package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída <strong>CharsDictionary</strong> představuje seznam povolených znaků ve výsledném otisku.
 *
 * @author Jara
 */
public class CharsDictionary {
    
    /**
     * Konstanta - počet znaků ve slovníku povolených znaků.
     */
    private static final int NUMBER_OF_CHARS = 64;
    
    /**
     * Konstanta - určuje, kolik bitů představuje jeden znak.
     */
    private static final int BIT_LENGTH_OF_CHAR = 6;
    
    /**
     * Konstanta - výčet povolených znaků pro výsledný otisk.
     */
    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789$=";

    /**
     * Proměnná - seznam znaků, které jsou naparsovány a následně uloženy z konstanty ALLOWED_CHARS.
     */
    private final List<String> charList;
    
    /**
     * Konstruktor uloží do seznamu znaků hodnoty převzaté z konstanty ALLOWED_CHARS.
     */
    public CharsDictionary() {
        this.charList = new ArrayList<>(NUMBER_OF_CHARS);
        for(int index = 0; index < NUMBER_OF_CHARS; index++) {
            this.charList.add(Character.toString(ALLOWED_CHARS.charAt(index)));
        }
    }
    
    /**
     * Metoda vrátí požadovaný znak.
     * 
     * @param index pozice, na které se nachází požadovaný znak.
     * @return String o délce 1, který představuje požadovaný znak.
     */
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
    
    /**
     * Vrací hodnotu konstanty BIT_LENGTH_OF_CHAR.
     * @return Počet bitů na jeden znak. 
     */
    public static int getBitLengthOfChar() {
        return BIT_LENGTH_OF_CHAR;
    }
}