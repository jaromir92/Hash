package model;

public abstract class Operation {

    protected final OperationSelector operationSelector;
    protected final int numberOfOperations;

    protected Operation(int firstDividend, int secondDividend, int divider) {
        this.operationSelector = new OperationSelector(firstDividend, secondDividend, divider);
        this.numberOfOperations = divider;
    }

    protected abstract Word getResult();
}