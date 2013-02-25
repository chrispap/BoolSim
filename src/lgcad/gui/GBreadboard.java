package lgcad.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import lgcad.model.Breadboard;
import lgcad.model.Pin;
import lgcad.model.Socket;

public class GBreadboard extends JPanel {
    private static final long serialVersionUID = 1L;

    Breadboard mBreadboard;

    public GBreadboard(int columns, int rows) {
        super();
        mBreadboard = new Breadboard(columns, rows);

        setLayout(null);
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension((getColumnCount() - 1) * 200 + 30, (getRowCount()) * 50 + 30));

        /* Make one graphic socket for every socket of the model */
        for (int i = 0; i < getColumnCount() - 1; i++) {
            for (int j = 0; j < getRowCount() - 1; j++) {
                GSocket gSocket = new GSocket(mBreadboard.sockets[i][j], this);
                gSocket.setLocation(getSocketLocation(i, j));
                add(gSocket);
            }

        }

        /* Make one graphic pin for every pin of the model */
        for (int i = 0; i < getColumnCount(); i++) {
            boolean editable = i == 0 ? true : false;
            for (int j = 0; j < rows; j++) {
                GPin gPin = new GPin(this, mBreadboard.pins[i][j], editable);
                gPin.setLocation(getPinLocation(i, j));
                add(gPin);
            }

        }

    }

    public int getRowCount() {
        return mBreadboard.getRowCount();
    }

    public int getColumnCount() {
        return mBreadboard.getColumnCount();
    }

    protected Point getPinLocation(int i, int j) {
        return new Point(i * 200 + 10, (j + 1) * 50);
    }

    protected Point getSocketLocation(int i, int j) {
        return new Point(i * 200 + 85, (j + 1) * 50 + 10);
    }

    public void updateSimulation() {
        mBreadboard.updateSimulation();
        repaint();
    }

    public void paint(Graphics g_) {
        super.paint(g_);
        Graphics2D g = (Graphics2D) g_;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (mBreadboard.sockets != null) {
            /* Draw socket input connections */
            for (int x = 0; x < getColumnCount() - 1; x++) {
                for (int y = 0; y < getRowCount() - 1; y++) {
                    if (mBreadboard.sockets[x][y] != null) {
                        Socket sock = mBreadboard.sockets[x][y];
                        int x1, y1, x2, y2;
                        for (int row : sock.getInputPinNumbers()) {
                            x1 = getPinLocation(sock.getColumn(), row).x;
                            y1 = getPinLocation(sock.getColumn(), row).y;
                            x2 = getSocketLocation(sock.getColumn(), sock.getRow()).x;
                            y2 = getSocketLocation(sock.getColumn(), sock.getRow()).y + 20;
                            g.drawLine(x1, y1, x2, y2);
                        }
                    }
                }
            }
            /* Draw socket output connections */
            for (int x = 1; x < getColumnCount(); x++) {
                for (int y = 0; y < getRowCount(); y++) {
                    if (mBreadboard.pins[x][y] != null) {
                        Pin pin = mBreadboard.pins[x][y];
                        Socket sock;
                        int x1, y1, x2, y2;
                        if ((sock = pin.getSourceSocket()) != null) {
                            x1 = getPinLocation(x, y).x;
                            y1 = getPinLocation(x, y).y;
                            x2 = getSocketLocation(sock.getColumn(), sock.getRow()).x + 50;
                            y2 = getSocketLocation(sock.getColumn(), sock.getRow()).y + 20;
                            g.drawLine(x1, y1, x2, y2);
                        }
                    }
                }
            }
        }

    }

}
