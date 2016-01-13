package model;

import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;

public class Word {

    private static final BigInteger MAX_VALUE = new BigInteger("1111111111111111111111111111111111111111111111111111111111111111", 2);
    private static final int WORD_SIZE = 64;

    private final BigInteger value;

    public Word(BigInteger bigInteger) {
        if(Word.MAX_VALUE.compareTo(bigInteger) == 1) {
            this.value = bigInteger;
        }
        else {
            this.value = Word.MAX_VALUE;
        } 
    }

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

    public BigInteger getDecimalValue() {
        return this.value;
    }

    public String getBinaryValue() {
        String binaryValue = this.value.toString(2);
        int numberOfMissingZeroes = getNumberOfMissingZeroes(binaryValue);
        String zeroesStream = getZeroesStream(numberOfMissingZeroes);
        return zeroesStream += binaryValue;
    }

    private int getNumberOfMissingZeroes(String binaryValue) {
        return WORD_SIZE - binaryValue.length();
    }

    private String getZeroesStream(int quantity) {
        return StringUtils.repeat("0", quantity);
    }

    public int getBitCount() {
        return this.value.bitCount();
    }

    public static int getWordSize() {
        return WORD_SIZE;
    }

    public static BigInteger getMaxValue() {
        return MAX_VALUE;
    }
}