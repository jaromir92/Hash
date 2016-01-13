package model;

import org.apache.commons.lang3.StringUtils;

public class BinaryCode {

    private static final int BITS_PER_BYTE = 8;

    private final String value;

    public BinaryCode(String inputText, boolean tranform) {
        if(!isBinaryCode(inputText) || tranform) {
            this.value = getBinaryCode(inputText);
        }
        else {
            this.value = inputText;
        }
    }

    private boolean isBinaryCode(String inputText) {
        return StringUtils.containsOnly(inputText, "01");
    }

    private String getBinaryCode(String inputText) {
        String binaryCode = "";
        String binaryOfChar;
        for (char symbol: inputText.toCharArray()){
            binaryOfChar = Integer.toBinaryString(symbol);
            int numberOfMissingZeroes = getNumberOfMissingZeroes(binaryOfChar);
            binaryCode += getZeroesStream(numberOfMissingZeroes) + binaryOfChar;
        }
        return binaryCode;
    }

    private int getNumberOfMissingZeroes(String binaryCode) {
        int numberOfMissingZeroes;
        if(binaryCode.isEmpty()) {
            numberOfMissingZeroes = BITS_PER_BYTE;
        }
        else {
            int moduloResult = binaryCode.length() % BITS_PER_BYTE;
            if(moduloResult == 0) {
                numberOfMissingZeroes = 0;
            } 
            else {
                numberOfMissingZeroes = BITS_PER_BYTE - moduloResult;
            }
        }
        return numberOfMissingZeroes;
    }

    private String getZeroesStream(int quantity) {
        return StringUtils.repeat("0", quantity);
    }

    public String getValue() {
        return this.value;
    }

    public int getLength() {
        return this.value.length();
    }
}