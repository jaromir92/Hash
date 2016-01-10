package model;

import org.apache.commons.lang3.StringUtils;

/**
 * Třída <strong>BinaryCode</strong> reprezentuje binární kód vstupního slova.
 *
 * @author Jara
 */
public class BinaryCode {
    
    /**
     * Konstanta - počet bitů na jeden byte.
     */
    private static final int BITS_PER_BYTE = 8;
    
    /**
     * Proměnná pro uložení binárního kódu.
     */
    private final String value;
    
    /**
     * Konstruktor, který zkontroluje, zda je vstup binárním řetězcem a zda je vyžadována transformace. Podle toho se rozhodne, zda vstup převést na binární kód či nikoliv.
     * 
     * @param inputText vstupní řetězec
     * @param tranform příznak, zda má být vstup převeden na binární kód
     */
    public BinaryCode(String inputText, boolean tranform) {
        if(!isBinaryCode(inputText) || tranform) {
            this.value = getBinaryCode(inputText);
        }
        else {
            this.value = inputText;
        }
    }
    
    /**
     * Metoda rozhodne, zda daný vstup je binárním kódem.
     * 
     * @param inputText vstupní řetězec
     * @return true jestliže vstup je binární kód. 
     */
    private boolean isBinaryCode(String inputText) {
        return StringUtils.containsOnly(inputText, "01");
    }
    
    /**
     * Metoda převede vstupní řetězec na binární kód.
     * 
     * @param inputText vstupní řetězec
     * @return String binární kód vstupu.
     */
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
    
    /**
     * Metoda vrátí počet chybějících nul, které je potřeba doplnit do binárního streamu.
     * 
     * @param binaryCode binární kód, u kterého chceme zjistit počet chybějících nul.
     * @return int počet chybějích nul
     */
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
     * Metoda vrátí hodnotu binárního kódu.
     * 
     * @return String binární kód.
     */
    public String getValue() {
        return this.value;
    }
    
    /**
     * Metoda vrátí délku binárního kódu.
     * 
     * @return int délka binárního kódu.
     */
    public int getLength() {
        return this.value.length();
    }
}