package model;

public class UnaryOperation extends Operation {

    private static final int NUMBER_OF_UNARY_OPERATIONS = 2;

    private final Word word;

    public UnaryOperation(Word word, int firstDividend) {
        super(firstDividend, word.getBitCount() * 2, NUMBER_OF_UNARY_OPERATIONS);
        this.word = word;
    }

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