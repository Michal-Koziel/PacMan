package Display;

import java.awt.*;

import javax.swing.*;

// Klasa do wyœwietlania grafiki na ekranie
public class Display {

	private JFrame Frame;
	private Canvas Canvas;
	
	private String title;
	private int width, height;
	
	public Display(String title,int width,int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	
		createDisplay();
	}
	
	
	
	private void createDisplay() {
		// ustawienie i stworzenie "ekranu"
		Frame = new JFrame(title);
		Frame.setSize(width,height);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setResizable(false);           // nie mozna zmienac rozmiaru
		Frame.setLocationRelativeTo(null);   // okno pojawia sie na srodku ekranu
		Frame.setVisible(true);
		
		// Dodanie do ekranu komponentu Canvas który umo¿liwia rysowanie
		Canvas = new Canvas();
		Canvas.setPreferredSize(new Dimension(width,height));
		Canvas.setMaximumSize(new Dimension(width,height));
		Canvas.setMinimumSize(new Dimension(width,height));
		Canvas.setFocusable(false);
		
		Frame.add(Canvas);
		Frame.pack();
	}
	
	
	public Canvas getCanvas() {
		return Canvas;
	}
	
	public JFrame getFrame() {
		return Frame;
	}
}
