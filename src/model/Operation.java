package model;

/**
 * Abstraktní třída <strong>Operation</strong> poskytuje základní funkčnost pro jejé potomky. 
 *
 * @author Jara
 */
public abstract class Operation {
    
    /**
     * Proměnná operationSelector udržuje referenci na třídu OperationSelector
     */
    protected final OperationSelector operationSelector;
    
    /**
     * Proměnná numberOfOperations představuje počet operací, které potomek třídy nabízí.
     */
    protected final int numberOfOperations;
    
    /**
     * Konstruktor inicializuje vlastní proměnné.
     * 
     * @param firstDividend první z dělenců ve vzorci
     * @param secondDividend druhý z dělenců ve vzorci
     * @param divider dělitel ve vzorci
     */
    protected Operation(int firstDividend, int secondDividend, int divider) {
        this.operationSelector = new OperationSelector(firstDividend, secondDividend, divider);
        this.numberOfOperations = divider;
    }
    
    /**
     * Na základě výpočtu třídy <strong>OperationSelector</strong> vrátí výsledek dané operace.
     * 
     * @return Výsledek dané operace, který je typu Word (64bitové číslo typu long bez znaménka). 
     */
    protected abstract Word getResult();
}