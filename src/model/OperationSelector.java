package model;

public class OperationSelector {

    private final int firstDividend;
    private final int secondDividend;
    private final int divider;

    public OperationSelector(int firstDividend, int secondDividend, int divider) {
        this.firstDividend = firstDividend;
        this.secondDividend = secondDividend;
        this.divider = divider;
    }

    public int getIndexOperation() {
        return (this.firstDividend * this.secondDividend) % this.divider;
    }

    public int getFirstDividend() {
        return this.firstDividend;
    }

    public int getSecondDividend() {
        return this.secondDividend;
    }

    public int getDivider() {
        return this.divider;
    }
}