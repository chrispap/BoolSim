package lgcad.model;

public class Breadboard {
    public Pin[][] pins;
    public Socket[][] sockets;

    public Breadboard(int columns, int rows) {
        pins = new Pin[columns][rows];
        sockets = new Socket[columns - 1][rows - 1];

        /* Create pins */
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                pins[i][j] = new Pin();
            }
        }

        /* Create sockets */
        for (int i = 0; i < getColumnCount() - 1; i++) {
            for (int j = 0; j < getRowCount() - 1; j++) {
                sockets[i][j] = new Socket(this, i, j);
            }
        }

    }

    public void updateSimulation() {
        for (int x = 1; x < pins.length; x++) {
            for (int y = 0; y < pins[0].length; y++) {
                if (pins[x][y] != null) {
                    pins[x][y].updateValueFromSource();
                }
            }
        }
    }

    public int getColumnCount() {
        return pins.length;

    }

    public int getRowCount() {
        return pins[0].length;
    }

}
