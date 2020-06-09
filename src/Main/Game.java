package Main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import Display.Display;
import Graphics.Assets;
import Graphics.GameCamera;
import Input.KeyMenager;
import Input.MouseManager;
import States.DeadState;
import States.GameState;
import States.LevelPassingState;
import States.MapState;
import States.MenuState;
import States.OptionState;
import States.PauseMenuState;
import States.PlayerState;
import States.ScoreState;
import States.State;

// G³ówna klasa gry
public class Game implements Runnable{
	
	private Display display;
	private int width,height;
	public String title;	
	private boolean running = false; // zmienna mówi¹ca czy pêtla gr jest uruchomiona
	private Thread thread;			 // W¹tek pêtli gry 
	
	//States
	public GameState gamestate;
	public State menustate;
	public State optionstate;
	public State scorestate;
	public State mapstate;
	public State playerstate;
	public State deadstate;
	public State levelpassingstate;
	public State pausemenustate;
	
	//Input
	private KeyMenager keyMan;
	private MouseManager mouseManager;
	
	//Handler
	private Handler handler;
	
	// Graphics
	private GameCamera gameCamera;
	private BufferStrategy Bs;
	private Graphics G;
	
	public Game(String title,int width,int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyMan = new KeyMenager();
		mouseManager = new MouseManager();
		}
	

	private void init() {
		display = new Display(title,width,height);	
		display.getFrame().addKeyListener(keyMan);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		handler = new Handler(this);
		gameCamera = new GameCamera(handler,0,0);
		gamestate = new GameState(handler);
		optionstate = new OptionState(handler);
		scorestate = new ScoreState(handler);
		menustate = new MenuState(handler);
		playerstate = new PlayerState(handler);
		mapstate = new MapState(handler);
		deadstate = new DeadState(handler);
		levelpassingstate = new LevelPassingState(handler);
		pausemenustate = new PauseMenuState(handler);
		State.setState(menustate);
	}

	// Funkcja która aktualizuje stan gry
	private void update() {
		keyMan.update();
		if(State.getState()!=null) {
			State.getState().update();
		}
	}
	
	// Funkcja która wyœwietla zaktualizowany stan gry
	private void render() {
		Bs = display.getCanvas().getBufferStrategy();
		if (Bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		G = Bs.getDrawGraphics();
		G.clearRect(0, 0,width,height);
		
		// Draw here:	
		if(State.getState()!=null) {
			State.getState().render(G);
		}
		
		// Wys³anie BS na ekran i zamkniecie Graphics
		Bs.show();
		G.dispose();
	}
	
	@Override
	public void run() {
		init();
		
		// making game generating "fps" frames per second and counting it
		int fps = 60;
		double timePerframe = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int frames = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerframe;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){  // Gra jest aktualizowana i wyœwietlana z czêsotliwoœci¹ fps/s
				update();
				render();
				frames++;
				delta--;	
			}
			if(timer >= 1000000000){
				// wyœwietla iloœæ fps w konsoli, aktualnie nieu¿ywane
				//System.out.println("Frames per second: " + frames);
				frames = 0;
				timer = 0;
			}
		}
			
			
		stop();
	}
	
	// Funkcja rozpoczynaj¹ca pêtlê gry
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	// Funkcja zatrzymuj¹ca pêtlê gry
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	// Getters and Setters
	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public void setGameCamera(GameCamera gameCamera) {
		this.gameCamera = gameCamera;
	}


	public void setBs(BufferStrategy bs) {
		Bs = bs;
	}


	public void setG(Graphics g) {
		G = g;
	}


	public KeyMenager getkeyMan() {
		return keyMan;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	
}
