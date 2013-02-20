package lgcad.model;

import java.util.ArrayList;

import lgcad.model.Gate.Type;

public class Socket {
	public Gate gate=null;
	public Breadboard parentBreadBoard;
	public ArrayList<Integer> inputPinNumbers;
	public int column,row;
	public Pin outputPin;
	
	public Socket(Breadboard parent, int col, int row){
		this.parentBreadBoard = parent;
		this.column = col;
		this.row = row;
		this.inputPinNumbers = new ArrayList<Integer>(2);
	}
	
	public Socket(Breadboard parent, int col, int row, Type gateType){
		this(parent, col, row);
		gate = Gate.makeGate(gateType);
	}
	
	public boolean getValue(){
		boolean[] inputs = new boolean[inputPinNumbers.size()];
		int i=0;
		for (int pinNum : inputPinNumbers )
			inputs[i++] = parentBreadBoard.pins[column][pinNum].value;
			
		return gate.output(inputs);
	}
	
}
