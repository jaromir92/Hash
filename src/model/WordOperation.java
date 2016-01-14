package model;

import java.math.BigInteger;

public final class WordOperation {

    public static Word moduloAfterShiftLeft(Word word, int howMany) {
        BigInteger shiftedWord = word.getDecimal().shiftLeft(Math.abs(howMany));
        BigInteger moduledWord = shiftedWord.mod(Word.getMaxValue());
        return new Word(moduledWord);
    }

    public static Word transformByMatrix(Word word) {
        int matrixSize = 8;
        String binaryWord = word.getBinary().getValue();
        String transformedBinaryString = "";
        for(int row = 0; row < matrixSize; row++) {
            for(int column = 0; column < matrixSize; column++) {
                transformedBinaryString += Character.toString(binaryWord.charAt((row * matrixSize) + column));
            }
        }
        BigInteger transformedWord = new BigInteger(transformedBinaryString, 2);
        return new Word(transformedWord);
    }

    public static Word bitwiseXOR(Word word1, Word word2) {
        BigInteger word = word1.getDecimal().xor(word2.getDecimal());
        return new Word(word);
    }
    
    public static Word moduloAfterMultiple(Word word1, Word word2) {
        BigInteger word = (word1.getDecimal().multiply(word2.getDecimal())).mod(Word.getMaxValue());
        return new Word(word);
    }

    public static Word moduloAfterMultiple(Word wordX, Word wordY, Word wordZ, Word wordK) {
        BigInteger multiple = wordX.getDecimal().multiply(wordY.getDecimal().multiply(wordZ.getDecimal().multiply(wordK.getDecimal())));
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