package lgcad.model;

public class Breadboard {
    public Pin[][] pins;
    public Socket[][] sockets;
    public int RowCounter[];

    public Breadboard(int width, int height) {
        pins = new Pin[width][height];
        sockets = new Socket[width - 1][height - 1];
        RowCounter = new int[width];
        
        /* Create pins */
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pins[i][j] = new Pin();
            }
        }
        
        /* Create sockets */
        for (int i = 0; i < width-1; i++) {
            for (int j = 0; j < height-1; j++) {
                sockets[i][j] = new Socket(this, i, j);
            }
        }
        
        
        for (int i = 0; i < width; i++)
            RowCounter[i] = 0;


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

}
