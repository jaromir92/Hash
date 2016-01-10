package model;

/**
 * Třída <strong>UnaryOperation</strong> má na starost výběr jedné unární operace.
 *
 * @author Jara
 */
public class UnaryOperation extends Operation {
    
    /**
     * Konstanta - počet unárních operací k dispozici.
     */
    private static final int NUMBER_OF_UNARY_OPERATIONS = 2;
    
    /**
     * Proměnná obsahuje slovo, na které bude aplikována unární operace.
     */
    private final Word word;
    
    /**
     * Konstruktor naplní svou proměnnou a také proměnné svého předka, třídu <strong>Operation</strong>.
     * 
     * @param word slovo, které je potřeba modifikovat
     * @param firstDividend jeden z dělenců ve vzorci
     */
    public UnaryOperation(Word word, int firstDividend) {
        super(firstDividend, word.getBitCount() * 2, NUMBER_OF_UNARY_OPERATIONS);
        this.word = word;
    }
    
    /**
     * Metoda provede na základě vypočteného indexu konkrétní unární operaci.
     * 
     * @return Výsledek operace, který je typu Word. 
     */
    @Override
    public Word getResult() {
        int indexOperation = this.operationSelector.getIndexOperation();
        Word permutedWord;
        switch (indexOperation)
        {
            case 0:
                permutedWord = WordOperation.moduloAfterShiftLeft(this.word, this.operationSelector.getFirstDividend() + this.operationSelector.getSecondDividend());
                break;
            case 1:
                permutedWord = WordOperation.transformByMatrix(this.word);
                break;
            default:
                permutedWord = WordOperation.moduloAfterShiftLeft(this.word, this.operationSelector.getFirstDividend() + this.operationSelector.getSecondDividend());
                break;
        }
        return permutedWord;
    }
}