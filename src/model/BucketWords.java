package model;

import java.util.ArrayList;
import java.util.List;

public class BucketWords {

    private static final int BUCKET_SIZE = 512;
    
    private final List<Word> wordList;

    public BucketWords(BinaryCode binaryBlock) {
        this.wordList = new ArrayList<>();
        Word word;
        BinaryCode binaryWord;
        int startIndex = 0;
        int binaryStreamLength = binaryBlock.getLength();
        while(startIndex < binaryStreamLength) {
            binaryWord = binaryBlock.getSubBinaryCode(startIndex, Word.getWordSize());
            word = new Word(binaryWord);
            this.wordList.add(word);
            startIndex += Word.getWordSize();
        }
    }

    public boolean addWord(Word word) {
        return this.wordList.add(word);
    }

    public Word getWord(int index) {
        return this.wordList.get(index);
    }

    public int getSize() {
        return this.wordList.size();
    }

    public List<Word> getWordList() {
        return this.wordList;
    }
    
    public static int getBucketSize() {
        return BUCKET_SIZE;
    }
}