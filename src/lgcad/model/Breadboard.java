package lgcad.model;

public class Breadboard {
	public Pin[][] pins;
	public Socket[][] sockets;
	public int RowCounter[];
	
	
	public Breadboard(int width, int height){
		pins = new Pin[width][height];
		sockets = new Socket[width-1][height-1];
		
		RowCounter = new int[width];
		
		for (int i=0; i<width; i++)
			RowCounter[i] = 0;
		
		for (int i=0; i<width; i++){
			for (int j=0; j<height; j++){
				pins[i][j] = new Pin();
			}
		}
		
	}
	
}