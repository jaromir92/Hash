package model;

/**
 * Třída <strong>OperationSelector</strong> má na základě vstupních hodnot na starosti určit, která operace se bude provádět.
 *
 * @author Jara
 */
public class OperationSelector {
    
    /**
     * První dělenec ve vzorci.
     */
    private final int firstDividend;
    
    /**
     * Druhý dělenec ve vzorci.
     */
    private final int secondDividend;
    
    /**
     * Dělitel ve vzorci.
     */
    private final int divider;
    
    
    /**
     * Konstruktor inicializuje vlastní atributy.
     * 
     * @param firstDividend první z dělenců ve vzorci
     * @param secondDividend druhý z dělenců ve vzorci
     * @param divider dělitel ve vzorci
     */
    public OperationSelector(int firstDividend, int secondDividend, int divider) {
        this.firstDividend = firstDividend;
        this.secondDividend = secondDividend;
        this.divider = divider;
    }
    
    /**
     * Metoda vrátí zbytek po dělení součinu atributů firstDividend a secondDividend atributem divider.
     * 
     * @return Index operace, která se následně provede.
     */
    public int getIndexOperation() {
        return (this.firstDividend * this.secondDividend) % this.divider;
    }
    
    /**
     * Metoda pro vrácení atributu firstDividend
     * 
     * @return int firstDividend
     */
    public int getFirstDividend() {
        return this.firstDividend;
    }
    
    /**
     * Metoda pro vrácení atributu secondDividend
     * 
     * @return int secondDividend
     */
    public int getSecondDividend() {
        return this.secondDividend;
    }
    
    /**
     * Metoda pro vrácení atributu divider
     * 
     * @return int divider
     */
    public int getDivider() {
        return this.divider;
    }
}