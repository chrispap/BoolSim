package lgcad.model;

public class Pin {
    protected Socket sourceSocket = null;
    protected boolean value;

    public Pin() {
	this(false);
    }

    public Pin(boolean value) {
	this.value = value;
    }

    public void setSource(Socket socket) {
	sourceSocket = socket;
    }

    public Socket getSourceSocket() {
	return sourceSocket;
    }

    public void updateValueFromSource() {
	if (sourceSocket != null)
	    value = sourceSocket.getValue();
    }

    public boolean getValue() {
	return value;
    }

    public void setValue(boolean value) {
	this.value = value;
    }

    public void toggleValue() {
	value = !value;
    }
}
