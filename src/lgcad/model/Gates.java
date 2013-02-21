package lgcad.model;

class And extends Gate {

    public And() {
        super(Gate.Type.And, 2);
    }

    public boolean output(boolean[] inputs) {
        return (inputs[0] & inputs[1]);
    }
}

class Or extends Gate {

    public Or() {
        super(Gate.Type.Or, 2);
    }

    public boolean output(boolean[] inputs) {
        return (inputs[0] | inputs[1]);
    }
}

class Not extends Gate {

    public Not() {
        super(Gate.Type.Not, 1);
    }

    public boolean output(boolean[] inputs) {
        return !inputs[0];
    }
}

class Nand extends Gate {

    public Nand() {
        super(Gate.Type.Nand, 2);
    }

    public boolean output(boolean[] inputs) {
        return !(inputs[0] & inputs[1]);
    }
}

class Nor extends Gate {

    public Nor() {
        super(Gate.Type.Nor, 2);
    }

    public boolean output(boolean[] inputs) {
        return !(inputs[0] | inputs[1]);
    }
}

class Xor extends Gate {

    public Xor() {
        super(Gate.Type.Xor, 2);
    }

    public boolean output(boolean[] inputs) {
        return (inputs[0] ^ inputs[1]);
    }
}
