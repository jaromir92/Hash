package model;

import java.math.BigInteger;

/**
 * Statická třída <Strong>WordOperation</strong> poskytuje operace s datovým typem (třídou) Word.
 *
 * @author Jara
 */
public final class WordOperation {
  
    /**
     * Metoda vrátí výsledek výpočtu: word shiftLeft howMany mod maxValueOfWord.
     * 
     * @param word Slovo, které chceme přepočítat.
     * @param howMany O kolik bitů doleva se mají bity posunout.
     * @return Výsledek operace, který je typu Word.
     */
    public static Word moduloAfterShiftLeft(Word word, int howMany) {
        BigInteger shiftedWord = word.getDecimalValue().shiftLeft(Math.abs(howMany));
        BigInteger moduledWord = shiftedWord.mod(Word.getMaxValue());
        return new Word(moduledWord);
    }
    
    /**
     * Metoda vezme bity vstupního slova, naplní je do matice, kterou následně transponuje. Tuto matici pak přečte a z těchto transponovaných bitů
     * sestaví nové slovo.
     * 
     * @param word Slovo, které chceme transponovat.
     * @return Výsledek transponování, který je typu Word.
     */
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
    
    /**
     * Metoda vrátí výsledek výpočtu: word1 * word2 mod maxValueOfWord.
     * 
     * @param word1 první operand
     * @param word2 druhý operand
     * @return Výsledek výpočtu, který je typu Word.
     */
    public static Word moduloAfterMultiple(Word word1, Word word2) {
        BigInteger word = (word1.getDecimalValue().multiply(word2.getDecimalValue())).mod(Word.getMaxValue());
        return new Word(word);
    }
    
    /**
     * Metoda vrátí výsledek výpočtu: word1 XOR word2.
     * 
     * @param word1 první operand
     * @param word2 druhý operand
     * @return Výsledek výpočtu, který je typu Word.
     */
    public static Word bitwiseXOR(Word word1, Word word2) {
        BigInteger word = word1.getDecimalValue().xor(word2.getDecimalValue());
        return new Word(word);
    }
    
    /**
     * Metoda vrátí výsledek výpočtu: wordX * wordY * wordZ * wordK mod maxValueOfWord.
     * 
     * @param wordX první operand
     * @param wordY druhý operand
     * @param wordZ třetí operand
     * @param wordK čtvrtý operand
     * @return Výsledek výpočtu, který je typu Word.
     */
    public static Word moduloAfterMultiple(Word wordX, Word wordY, Word wordZ, Word wordK) {
        BigInteger multiple = wordX.getDecimalValue().multiply(wordY.getDecimalValue().multiply(wordZ.getDecimalValue().multiply(wordK.getDecimalValue())));
        BigInteger result = multiple.mod(Word.getMaxValue());
        return new Word(result);
    }
    
    /**
     * Metoda vrátí výsledek výpočtu: (wordX XOR wordY XOR wordZ XOR wordK) shiftLeft 500 mod maxValueOfWord.
     * 
     * @param wordX první operand
     * @param wordY druhý operand
     * @param wordZ třetí operand
     * @param wordK čtvrtý operand
     * @return Výsledek výpočtu, který je typu Word.
     */
    public static Word createExpandedWord(Word wordX, Word wordY, Word wordZ, Word wordK) {
        Word firstPart = bitwiseXOR(wordX, wordY);
        Word secondPart = bitwiseXOR(firstPart, wordZ);
        Word resultXOR = bitwiseXOR(secondPart, wordK);
        resultXOR = moduloAfterShiftLeft(resultXOR, 500);
        return resultXOR;
    }
}