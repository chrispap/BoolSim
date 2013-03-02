package lgcad.model;

import java.util.ArrayList;

import lgcad.model.Gate.Type;

public class Socket {
    protected Gate gate = null;
    protected Breadboard parentBreadBoard;
    protected int column, row;
    protected Pin outputPin;
    protected ArrayList<Integer> inputPinNumbers;

    public Socket(Breadboard parent, int col, int row) {
        this.parentBreadBoard = parent;
        this.column = col;
        this.row = row;
        this.inputPinNumbers = new ArrayList<Integer>(2);
    }

    public Socket(Breadboard parent, int col, int row, Type gateType) {
        this(parent, col, row);
        gate = Gate.makeGate(gateType);
    }

    public boolean getValue() {
        if (gate == null) return false;

        int i = 0;
        boolean[] inputs = new boolean[inputPinNumbers.size()];
        for (int pinNum : inputPinNumbers)
            inputs[i++] = parentBreadBoard.pins[column][pinNum].value;

        return gate.output(inputs);
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Pin getOutputPin() {
        return outputPin;
    }

    public void setOutputPin(Pin outputPin) {
        this.outputPin = outputPin;
    }

    public ArrayList<Integer> getInputPinNumbers() {
        return inputPinNumbers;
    }

    public void setInputPinNumbers(ArrayList<Integer> inputPinNumbers) {
        this.inputPinNumbers = inputPinNumbers;
    }

    public Breadboard getParentBreadBoard() {
        return parentBreadBoard;
    }

}
