package model;

/**
 * Třída <strong>Input</strong> reprezentuje vstupní řetezec, který je třeba zašifrovat.
 *
 * @author Jara
 */
public class Input {

    /**
     * Proměnná value slouží k uložení vstupního řetězce.
     */
    private final String value;

    /**
     * Konstruktor přijme vstupní řetězec inputText, který se uloží do proměnné value.
     * 
     * @param inputText vstupní řetězec
     */
    public Input(String inputText) {
        this.value = inputText;
    }
    
    /**
     * Vrací hodnotu proměnné value, tedy hodnotu vstupního řetězce.
     * 
     * @return String hodnota vstupního řetězce.
     */
    public String getValue() {
        return this.value;
    }
}