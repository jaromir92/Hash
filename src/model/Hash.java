package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Hash {

    private static final int WORD_PER_BUCKET_BEFORE_EXPAND = BucketWords.getBucketSize() / Word.getWordSize();
    private static final int WORD_PER_BUCKET_AFTER_EXPAND = 64;

    private BinaryCode hash;

    private final List<BucketWords> inputWords;

    private final CharsDictionary chars;
  
    private Word A;
    private Word B;
    private Word C;
    private Word D;
    private Word E;
    private Word F;
    
    private final Word K;

    public Hash() {
        this.inputWords = new ArrayList<>();
        this.chars = new CharsDictionary();
        this.A = new Word(new BigInteger("0011111111111110000000000111111000000000000000111111111000000001", 2));  
        this.B = new Word(new BigInteger("0111001000110100010111111010000011100000001111011101011110010001", 2));
        this.C = new Word(new BigInteger("1001110011010101010110111000010011100000010111011101011111111000", 2));
        this.D = new Word(new BigInteger("0011111110110100011111111010111001100111001111010101010010010101", 2));
        this.E = new Word(new BigInteger("1001001111000000000000000010000011111111110001000001000111101111", 2));
        this.F = new Word(new BigInteger("0001001111110100010111110011000011100000001111011101011110011111", 2));
        this.K = new Word(new BigInteger("1101100111111111000000000010001010100011001000010100001000011110", 2));
    }

    public void makeHash(String inputText) {
        preprocessCalculation(inputText);
        calculateWords();
        setHash();
    }

    private void preprocessCalculation(String inputText) {
        BinaryCode binaryCode = getBinaryStream(inputText);
        setInputWords(binaryCode);
        expandInputWords();
    }

    private BinaryCode getBinaryStream(String inputText) {
        BinaryCode bc = new BinaryCode(inputText, true);
        bc.makeBinaryStream();
        bc.fillWithZeroesAfter(BucketWords.getBucketSize());
        return bc;
    }

    private void setInputWords(BinaryCode binaryStream) {
        BucketWords bucketWords;
        int startIndex = 0;
        int binaryStreamLength = binaryStream.getLength();
        while(startIndex < binaryStreamLength) {
            bucketWords = new BucketWords(binaryStream.getSubBinaryCode(startIndex, BucketWords.getBucketSize()));
            this.inputWords.add(bucketWords);
            startIndex += BucketWords.getBucketSize();
        }
    }

    private void expandInputWords() {
        Word newWord;
        for(BucketWords bw : this.inputWords) {
            for(int index = WORD_PER_BUCKET_BEFORE_EXPAND; index < WORD_PER_BUCKET_AFTER_EXPAND; index++) {
                newWord = WordOperation.createExpandedWord(bw.getWord(index - 8), bw.getWord(index - 5), bw.getWord(index - 3), bw.getWord(index - 1));
                bw.addWord(newWord);
            }
        }
    }

    private void calculateWords() {
        for(BucketWords bw : this.inputWords) {
            for(int index = 0; index < bw.getSize(); index++) {
                Word DwithE = WordOperation.moduloAfterMultiple(this.D, this.E);
                Word tempQ = WordOperation.moduloAfterMultiple(this.F, this.K, bw.getWord(index), DwithE);
                this.F = this.E;
                this.E = DwithE;
                this.D = WordOperation.bitwiseXOR(this.C, this.D);
                this.C = this.B;
                this.B = new UnaryOperation(this.A, index * index).getResult();
                this.A = WordOperation.moduloAfterShiftLeft(tempQ, 384);
            }
        }
    }

    private void setHash() {
        String binary = this.A.getBinary().getValue() + this.B.getBinary().getValue() + this.C.getBinary().getValue() + 
                        this.D.getBinary().getValue() + this.E.getBinary().getValue() + this.F.getBinary().getValue();
        this.hash = new BinaryCode(binary, false);
    }
    
    public String toDecimal() {
        int bitLengthOfChar = CharsDictionary.getBitLengthOfChar();
        String binary = this.hash.getValue();
        String result = "";
        int startIndex = 0;
        while(startIndex < this.hash.getLength()) {
            String cislo = binary.substring(startIndex, startIndex + bitLengthOfChar);
            int index = Integer.parseInt(cislo, 2);
            result += this.chars.getChar(index);
            startIndex += bitLengthOfChar;
        }
        return result;
    }
}