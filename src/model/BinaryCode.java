package model;

import org.apache.commons.lang3.StringUtils;

public class BinaryCode {

    private static final int BITS_PER_BYTE = 8;

    private String value;

    public BinaryCode(String inputText, boolean tranform) {
        if(!isBinaryCode(inputText) || tranform) {
            this.value = getBinaryCode(inputText);
        }
        else {
            this.value = inputText;
        }
    }
    
    private BinaryCode(String binaryCode) {
        this.value = binaryCode;
    }

    private boolean isBinaryCode(String inputText) {
        return StringUtils.containsOnly(inputText, "01");
    }

    private String getBinaryCode(String inputText) {
        String binaryCode = "";
        BinaryCode binaryOfChar;
        for (char symbol: inputText.toCharArray()){
            binaryOfChar = new BinaryCode(Integer.toBinaryString(symbol));
            binaryOfChar.fillWithZeroesBefore(BITS_PER_BYTE);
            binaryCode += binaryOfChar.value;
        }
        return binaryCode;
    }

    public String getValue() {
        return this.value;
    }

    public int getLength() {
        return this.value.length();
    }
    
    public boolean isEmpty() {
        return this.value.isEmpty();
    }
    
    public BinaryCode getSubBinaryCode(int startIndex, int length) {
        int endIndex = Math.abs(startIndex) + Math.abs(length);
        if(endIndex > this.value.length()) {
            endIndex = this.value.length();
        }
        String subBinaryCode = this.value.substring(Math.abs(startIndex), endIndex);
        return new BinaryCode(subBinaryCode);
    }
    
    public void makeBinaryStream() {
        this.value = "1" + this.value + new BinaryCode(Integer.toString(this.value.length()), true).getValue();
        
    }
    
    public void fillWithZeroesAfter(int moduloValue) {
        int numberOfMissingZeroes = getNumberOfMissingZeroes(moduloValue);
        this.value += getZeroesStream(numberOfMissingZeroes);
    }
    
    public void fillWithZeroesBefore(int moduloValue) {
        int numberOfMissingZeroes = getNumberOfMissingZeroes(moduloValue);
        this.value = getZeroesStream(numberOfMissingZeroes) + this.value;
    }
    
    private int getNumberOfMissingZeroes(int moduloValue) {
        int numberOfMissingZeroes;
        if(this.value.isEmpty()) {
            numberOfMissingZeroes = moduloValue;
        }
        else {
            int moduloResult = this.value.length() % moduloValue;
            if(moduloResult == 0) {
                numberOfMissingZeroes = 0;
            } 
            else {
                numberOfMissingZeroes = moduloValue - moduloResult;
            }
        }
        return numberOfMissingZeroes;
    }
    
    private String getZeroesStream(int quantity) {
        return StringUtils.repeat("0", quantity);
    }
}