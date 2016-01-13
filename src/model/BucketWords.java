package model;

import java.util.ArrayList;
import java.util.List;

public class BucketWords {

    private final List<Word> wordList;

    public BucketWords(BinaryStream binaryBlock) {
        this.wordList = new ArrayList<>();
        Word word;
        BinaryStream binaryWord;
        int startIndex = 0;
        int binaryStreamLength = binaryBlock.getLength();
        while(startIndex < binaryStreamLength) {
            binaryWord = binaryBlock.getSubBinaryStream(startIndex, Word.getWordSize());
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
}