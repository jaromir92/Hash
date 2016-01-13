package model;

import java.math.BigInteger;

public final class WordOperation {

    public static Word moduloAfterShiftLeft(Word word, int howMany) {
        BigInteger shiftedWord = word.getDecimalValue().shiftLeft(Math.abs(howMany));
        BigInteger moduledWord = shiftedWord.mod(Word.getMaxValue());
        return new Word(moduledWord);
    }

    public static Word transformByMatrix(Word word) {
        int matrixSize = 8;
        String binaryWord = word.getBinaryValue();
        String transformedBinaryString = "";
        for(int row = 0; row < matrixSize; row++) {
            for(int column = 0; column < matrixSize; column++) {
                transformedBinaryString += Character.toString(binaryWord.charAt((row * matrixSize) + column));
            }
        }
        BigInteger transformedWord = new BigInteger(transformedBinaryString, 2);
        return new Word(transformedWord);
    }

    public static Word moduloAfterMultiple(Word word1, Word word2) {
        BigInteger word = (word1.getDecimalValue().multiply(word2.getDecimalValue())).mod(Word.getMaxValue());
        return new Word(word);
    }

    public static Word bitwiseXOR(Word word1, Word word2) {
        BigInteger word = word1.getDecimalValue().xor(word2.getDecimalValue());
        return new Word(word);
    }

    public static Word moduloAfterMultiple(Word wordX, Word wordY, Word wordZ, Word wordK) {
        BigInteger multiple = wordX.getDecimalValue().multiply(wordY.getDecimalValue().multiply(wordZ.getDecimalValue().multiply(wordK.getDecimalValue())));
        BigInteger result = multiple.mod(Word.getMaxValue());
        return new Word(result);
    }

    public static Word createExpandedWord(Word wordX, Word wordY, Word wordZ, Word wordK) {
        Word firstPart = bitwiseXOR(wordX, wordY);
        Word secondPart = bitwiseXOR(firstPart, wordZ);
        Word resultXOR = bitwiseXOR(secondPart, wordK);
        resultXOR = moduloAfterShiftLeft(resultXOR, 500);
        return resultXOR;
    }
}