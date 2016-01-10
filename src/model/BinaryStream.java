package model;

import org.apache.commons.lang3.StringUtils;

/**
 * Třída <strong>BinaryStream</strong> reprezentuje vstupní řetězec v binární podobě odplněný o redundantní bity tak, aby byl počet bitů ve streamu dělitelný
 * délkou jednoho bloku (512 bitů).
 *
 * @author Jara
 */
public class BinaryStream {

    /**
     * Proměnná obsahuje binární stream.
     */
    private final String value;
 
    /**
     * Konstruktor doplní vstupní binární kód přidáním bitu "1" na začátek a délky binárního řetezce v binární podobě.
     * Poté doplní na konec tohoto řetězce příslušný počet bitů "0" tak, aby byla výsledná délka řetězce dělitelná délkou 1 bloku (512 bitů).
     * 
     * @param binaryCode binární kód, který je potřeba doplnit.
     */
    public BinaryStream(BinaryCode binaryCode) {
        String binary = "1" + binaryCode.getValue() + new BinaryCode(Integer.toString(binaryCode.getLength()), true).getValue();
        int numberOfMissingZeroes = getNumberOfMissingZeroes(binary);
        String binaryStream = binary + getZeroesStream(numberOfMissingZeroes);
        this.value = binaryStream;
    }
    
    /**
     * Privátní konstruktor, který slouží pouze pro vytvoření subřetězce z celkového binárního streamu.
     * @param binaryCode binární kód, která je potřeba uložit jako binární stream.
     */
    private BinaryStream(String binaryCode) {
        this.value = binaryCode;
    }
    
    /**
     * Vrátí řetězec obsahující počet nul dle zadaného parametru.
     * 
     * @param quantity požadovaný počet nul
     * @return String který obsahuje příslušný počet nul.
     */
    private String getZeroesStream(int quantity) {
        return StringUtils.repeat("0", quantity);
    }
    
    /**
     * Metoda vrátí počet chybějících nul, které je potřeba doplnit do binárního streamu.
     * 
     * @param binaryCode binární kód, u kterého je potřeba zjistit počet chybějících nul.
     * @return int počet chybějích nul
     */
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
    
    /**
     * Metoda vrátí subbinární stream, který je vybrán na základě startovního indexu a požadované délky subřetězce. 
     * 
     * @param startIndex index, na kterém má binární stream začínat
     * @param size délka nového streamu
     * @return BinaryStream o zadané délce, který začíná na zadaném indexu.
     */
    public BinaryStream getSubBinaryStream(int startIndex, int size) {
        String binaryBlock = this.value.substring(startIndex, startIndex + size);
        return new BinaryStream(binaryBlock);
    }
    
    /**
     * Metoda vrátí hodnotu binárního streamu.
     * 
     * @return String binárního streamu
     */
    public String getValue() {
        return this.value;
    }
    
    /**
     * Metoda vrátí délku binárního streamu.
     * 
     * @return int délka binárního streamu.
     */
    public int getLength() {
        return this.value.length();
    }
}