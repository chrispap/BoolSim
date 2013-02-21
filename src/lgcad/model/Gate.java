package lgcad.model;

public abstract class Gate {

    public static enum Type {
        And, Or, Not, Nand, Nor, Xor
    };

    public final Type type;
    public final int inputCount;

    protected Gate(Type type, int inputCount) {
        this.type = type;
        this.inputCount = inputCount;
    }

    public abstract boolean output(boolean[] inputs);

    /**
     * Factory method that makes it easy to construct gates when we have the
     * type in the appropriate enum.
     * 
     * @param type
     *            The type of the gate to be created
     * */
    public static Gate makeGate(Type type) {
        switch (type) {
            case Nand:
                return new Nand();
            case And:
                return new And();
            case Nor:
                return new Nor();
            case Xor:
                return new Xor();
            case Not:
                return new Not();
            case Or:
                return new Or();
            default:
                return null;
        }
    }
}
