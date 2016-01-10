package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída <strong>BucketWords</strong> reprezentuje seznam slov ze vstupního řetězce.
 *
 * @author Jara
 */
public class BucketWords {

    /**
     * Proměnná, ve které je uchováván seznam slov vytovřených ze vstupního řetězce.
     */
    private final List<Word> wordList;
    
    /**
     * Konstruktor naparsuje binární stream nul a jedniček, ze kterých pak konstruuje jednotlivá slova. Tato slova následně přidá do seznamu slov.
     * 
     * @param binaryBlock - binární blok, který bude parsován. 
     */
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
    
    /**
     * Metoda přidá nové slovo na konec seznamu.
     * 
     * @param word slovo, které je potřeba přidat.
     * @return true jestliže se zvýší počet slov v seznamu.
     */
    public boolean addWord(Word word) {
        return this.wordList.add(word);
    }
    
    /**
     * Matoda vrátí slovo na zadaném indexu.
     * 
     * @param index pozice, na které se nachází požadované slovo
     * @return Prvek ze seznamu, který je typu Word.
     */
    public Word getWord(int index) {
        return this.wordList.get(index);
    }
    
    /**
     * Metoda vrátí počet slov v seznamu.
     * 
     * @return int počet prvků v seznamu.
     */
    public int getSize() {
        return this.wordList.size();
    }
    
    /**
     * Vrátí seznam všech slov typu Word.
     * 
     * @return List of Words 
     */
    public List<Word> getWordList() {
        return this.wordList;
    }
}