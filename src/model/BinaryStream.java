package model;

import org.apache.commons.lang3.StringUtils;

public class BinaryStream {

    private final String value;

    public BinaryStream(BinaryCode binaryCode) {
        String binary = "1" + binaryCode.getValue() + new BinaryCode(Integer.toString(binaryCode.getLength()), true).getValue();
        int numberOfMissingZeroes = getNumberOfMissingZeroes(binary);
        String binaryStream = binary + getZeroesStream(numberOfMissingZeroes);
        this.value = binaryStream;
    }

    private BinaryStream(String binaryCode) {
        this.value = binaryCode;
    }

    private String getZeroesStream(int quantity) {
        return StringUtils.repeat("0", quantity);
    }

    private int getNumberOfMissingZeroes(String binaryCode) {
        int numberOfMissingZeroes;
        if(binaryCode.isEmpty()) {
            numberOfMissingZeroes = Hash.getBucketSize();
        }
        else {
            int moduloResult = binaryCode.length() % Hash.getBucketSize();
            if(moduloResult == 0) {
                numberOfMissingZeroes = 0;
            } 
            else {
                numberOfMissingZeroes = Hash.getBucketSize() - moduloResult;
            }
        }
        return numberOfMissingZeroes;
    }

    public BinaryStream getSubBinaryStream(int startIndex, int size) {
        String binaryBlock = this.value.substring(startIndex, startIndex + size);
        return new BinaryStream(binaryBlock);
    }

    public String getValue() {
        return this.value;
    }

    public int getLength() {
        return this.value.length();
    }
}