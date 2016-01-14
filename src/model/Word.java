package model;

import java.math.BigInteger;

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

    public Word(BinaryCode binaryCode) {
        String binaryWord;
        if(binaryCode.getLength() <= WORD_SIZE) {
            binaryWord = binaryCode.getValue();
        }
        else {
            binaryWord = binaryCode.getSubBinaryCode(binaryCode.getLength() - WORD_SIZE, WORD_SIZE).getValue();
        }
        this.value = new BigInteger(binaryWord, 2);
    }

    public BigInteger getDecimal() {
        return this.value;
    }

    public BinaryCode getBinary() {
        String binaryValue = this.value.toString(2);
        BinaryCode binaryCode = new BinaryCode(binaryValue, false);
        binaryCode.fillWithZeroesBefore(WORD_SIZE);
        return binaryCode;
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