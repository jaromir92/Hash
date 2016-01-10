package model;

import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;

/**
 * Třída <strong>Word</strong> simuluje 64bitové slovo typu long bez znaménka.
 *
 * @author Jara
 */
public class Word {
    
    /**
     * Konstanta - maximální hodnota slova.
     */
    private static final BigInteger MAX_VALUE = new BigInteger("1111111111111111111111111111111111111111111111111111111111111111", 2);
    
    /**
     * Konstanta - maximální počet bitů slova.
     */
    private static final int WORD_SIZE = 64;
    
    /**
     * Proměnná - hodnota slova.
     */
    private final BigInteger value;
 
    /**
     * Konstruktor, který zkontroluje hodnotu vstupního slova. Pokud je větší než maximální hodnota, uloží právě maximální hodnotu.
     * 
     * @param bigInteger hodnota, která má být uložena jako slovo. 
     */
    public Word(BigInteger bigInteger) {
        if(Word.MAX_VALUE.compareTo(bigInteger) == 1) {
            this.value = bigInteger;
        }
        else {
            this.value = Word.MAX_VALUE;
        } 
    }
    
    /**
     * Konstruktor, který z bitového streamu vezme 64 nejméně významných bitů a udělá z nich slovo typu Word.
     * 
     * @param binaryStream binární zápis, ze kterého má být vytvořeno slovo.
     */
    public Word(BinaryStream binaryStream) {
        String binaryWord;
        if(binaryStream.getLength() <= WORD_SIZE) {
            binaryWord = binaryStream.getValue();
        }
        else {
            binaryWord = binaryStream.getValue().substring(binaryStream.getLength() - WORD_SIZE);
        }
        this.value = new BigInteger(binaryWord, 2);
    }
    
    /**
     * Metoda vrátí hodnotu typu BigInteger v decimální podobě.
     * 
     * @return BigInteger decimální podoba slova.
     */
    public BigInteger getDecimalValue() {
        return this.value;
    }
    
    /**
     * Metoda vrátí slovo v binární podobě, doplněna na délku 64 bitů.
     * 
     * @return String binární podoba slova.
     */
    public String getBinaryValue() {
        String binaryValue = this.value.toString(2);
        int numberOfMissingZeroes = getNumberOfMissingZeroes(binaryValue);
        String zeroesStream = getZeroesStream(numberOfMissingZeroes);
        return zeroesStream += binaryValue;
    }
    
    /**
     * Metoda vrátí počet chybějících nul, které je třeba doplnit k binárnímu streamu, aby měl délku 64 bitů.
     * @param binaryValue binární kód, kterému je potřeba zjistit počet chybějích nul.
     * @return int počet chybějících nul.
     */
    private int getNumberOfMissingZeroes(String binaryValue) {
        return WORD_SIZE - binaryValue.length();
    }
    
    /**
     * Vrátí řetězec obsahující počet nul dle zadaného parametru.
     * 
     * @param quantity požadovaný počet nul.
     * @return String který obsahuje příslušný počet nul.
     */
    private String getZeroesStream(int quantity) {
        return StringUtils.repeat("0", quantity);
    }
    
    /**
     * Metoda vrátí počet bitů, které obsahují hodnotu "1".
     * 
     * @return int počet jedniček v bitové podobě slova.
     */
    public int getBitCount() {
        return this.value.bitCount();
    }
    
    /**
     * Metoda vrátí maximální bitovou délku slova.
     * 
     * @return int maximální délka slova.
     */
    public static int getWordSize() {
        return WORD_SIZE;
    }
    
    /**
     * Metoda vrátí maximální hodnotu, kterou lze uložit jako slovo.
     * 
     * @return BigInteger Max hodnota slova. 
     */
    public static BigInteger getMaxValue() {
        return MAX_VALUE;
    }
}