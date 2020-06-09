package Graphics;

import java.awt.image.BufferedImage;

// Klasa pobieraj¹ca wszystkie grafiki i przypisuj¹ce je odpowiednim zmiennym
public class Assets {

	private static final int width =32, height =32; // rozmiar poszczególnych sheetów
	
	public static BufferedImage BlueBrick,BlueBrokenBrick,RedBrick,RedBrokenBrick,BlueDoor,
	RedDoor,Grass,GrassDoor, BlueBrickClosed,RedBrickClosed,Cookie,RedFloor,BlueFloor,Wood,
	ClosedWood, GameOver, MenuReturn, YourScore,LevelCompleted,NextLevel,Background; 
	public static BufferedImage[] PlayerDown,PlayerUp,PlayerLeft,PlayerRight,
	InkyDown,InkyUp,InkyLeft,InkyRight,BlinkyDown,BlinkyUp,BlinkyLeft,BlinkyRight,
	ClydeDown,ClydeUp,ClydeLeft,ClydeRight,PinkyDown,PinkyUp,PinkyLeft,PinkyRight,
	buttonStart, buttonEnd,buttonOption,buttonScore,buttonBack,buttonPlayer,buttonMap,
	PacManButton, HoboButton, CatManButton, CookieMButton,BlueMap,RedMap,GreenMap;
	
	private static int characterNumber1 = 0; // zmienne wyboru bohatera, zale¿nie od nich Pac Man ma inn¹ grafikê
	private static int characterNumber2 = 0; 
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet sheetP = new SpriteSheet(ImageLoader.loadImage("/textures/sheetP.png"));
		SpriteSheet sheetG = new SpriteSheet(ImageLoader.loadImage("/textures/sheetG.png"));
		SpriteSheet sheetObj = new SpriteSheet(ImageLoader.loadImage("/textures/sheetObj.png"));
		SpriteSheet sheetMenu = new SpriteSheet(ImageLoader.loadImage("/textures/sheetMenu.png"));
		SpriteSheet sheetMaps = new SpriteSheet(ImageLoader.loadImage("/textures/sheetMaps.png"));
		SpriteSheet sheetStrings = new SpriteSheet(ImageLoader.loadImage("/textures/StringsSheet.png"));
		SpriteSheet sheetBack = new SpriteSheet(ImageLoader.loadImage("/textures/sheetBackground.png"));
	
		// buttons
		buttonStart = new BufferedImage[2];	 buttonEnd = new BufferedImage[2];
		buttonOption = new BufferedImage[2]; buttonScore = new BufferedImage[2];
		buttonBack = new BufferedImage[2];   buttonPlayer = new BufferedImage[2];
		buttonMap = new BufferedImage[2];    CookieMButton = new BufferedImage[2];
		PacManButton = new BufferedImage[2]; HoboButton = new BufferedImage[2];
		CatManButton = new BufferedImage[2]; BlueMap = new BufferedImage[2];
		RedMap = new BufferedImage[2];		 GreenMap = new BufferedImage[2];

		buttonStart[0]= sheetMenu.crop(0*500, 0*100, 1*500, 1*100);
		buttonStart[1]= sheetMenu.crop(1*500, 0*100, 1*500, 1*100);
		buttonEnd[0]= sheetMenu.crop(0*500, 1*100, 1*500, 1*100);
		buttonEnd[1]= sheetMenu.crop(1*500, 1*100, 1*500, 1*100);
		buttonOption[0]= sheetMenu.crop(0*500, 2*100, 1*500, 1*100);
		buttonOption[1]= sheetMenu.crop(1*500, 2*100, 1*500, 1*100);
		buttonScore[0]= sheetMenu.crop(0*500, 3*100, 1*500, 1*100);
		buttonScore[1]= sheetMenu.crop(1*500, 3*100, 1*500, 1*100);
		buttonBack[0]= sheetMenu.crop(0*500, 4*100, 1*500, 1*100);
		buttonBack[1]= sheetMenu.crop(1*500, 4*100, 1*500, 1*100);
		buttonPlayer[0]= sheetMenu.crop(0*500, 5*100, 1*500, 2*100);
		buttonPlayer[1]= sheetMenu.crop(1*500, 5*100, 1*500, 2*100);
		buttonMap[0]= sheetMenu.crop(2*500, 0*100, 1*500, 1*100);
		buttonMap[1]= sheetMenu.crop(3*500, 0*100, 1*500, 1*100);
		buttonScore[0]= sheetMenu.crop(0*500, 3*100, 1*500, 1*100);
		buttonScore[1]= sheetMenu.crop(1*500, 3*100, 1*500, 1*100);
		
		PacManButton[0]= sheetP.crop(0*width, 1*height, width, height);
		PacManButton[1]= sheetP.crop(0*width, 0*height, width, height);
		HoboButton[0]= sheetP.crop(4*width, 1*height, width, height);
		HoboButton[1]= sheetP.crop(4*width, 0*height, width, height);
		CatManButton[0]= sheetP.crop(4*width, 3*height, width, height);
		CatManButton[1]= sheetP.crop(4*width, 2*height, width, height);
		CookieMButton[0]= sheetP.crop(0*width, 3*height, width, height);
		CookieMButton[1]= sheetP.crop(0*width, 2*height, width, height);
		BlueMap[0] = sheetMaps.crop(0,0,335,337);
		BlueMap[1] = sheetMaps.crop(0,337,335,337);
		RedMap [0] = sheetMaps.crop(336,0,335,337);
		RedMap [1] = sheetMaps.crop(336,337,335,337);
		GreenMap [0] = sheetMaps.crop(672,0,337,337);
		GreenMap [1] = sheetMaps.crop(672,337,337,337);

		// Player
		PlayerDown = new BufferedImage[2];
		PlayerUp = new BufferedImage[2];
		PlayerLeft = new BufferedImage[2];
		PlayerRight = new BufferedImage[2];
		PlayerDown[1] = sheetP.crop((2+characterNumber1)*width, (0+characterNumber2)*height, width, height);
		PlayerDown[0] = sheetP.crop((2+characterNumber1)*width, (1+characterNumber2)*height, width, height);
		PlayerUp[1] = sheetP.crop((3+characterNumber1)*width, (0+characterNumber2)*height, width, height);
		PlayerUp[0] = sheetP.crop((3+characterNumber1)*width, (1+characterNumber2)*height, width, height);
		PlayerLeft[1] = sheetP.crop((1+characterNumber1)*width, (0+characterNumber2)*height, width, height);
		PlayerLeft[0] = sheetP.crop((1+characterNumber1)*width, (1+characterNumber2)*height, width, height);
		PlayerRight[1] = sheetP.crop((0+characterNumber1)*width, (0+characterNumber2)*height, width, height);
		PlayerRight[0] = sheetP.crop((0+characterNumber1)*width, (1+characterNumber2)*height, width, height);
		
		// Ghosts
		InkyDown = new BufferedImage[2];
		InkyUp = new BufferedImage[2];
		InkyLeft = new BufferedImage[2];
		InkyRight = new BufferedImage[2];
		InkyDown[1] = sheetG.crop(2*width, 2*height, width, height);
		InkyDown[0] = sheetG.crop(2*width, 3*height, width, height);
		InkyUp[1] = sheetG.crop(3*width, 2*height, width, height);
		InkyUp[0] = sheetG.crop(3*width, 3*height, width, height);
		InkyLeft[1] = sheetG.crop(1*width, 2*height, width, height);
		InkyLeft[0] = sheetG.crop(1*width, 3*height, width, height);
		InkyRight[1] = sheetG.crop(0*width, 2*height, width, height);
		InkyRight[0] = sheetG.crop(0*width, 3*height, width, height);
		
		BlinkyDown = new BufferedImage[2];
		BlinkyUp = new BufferedImage[2];
		BlinkyLeft = new BufferedImage[2];
		BlinkyRight = new BufferedImage[2];
		BlinkyDown[1] = sheetG.crop(2*width, 0*height, width, height);
		BlinkyDown[0] = sheetG.crop(2*width, 1*height, width, height);
		BlinkyUp[1] = sheetG.crop(3*width, 0*height, width, height);
		BlinkyUp[0] = sheetG.crop(3*width, 1*height, width, height);
		BlinkyLeft[1] = sheetG.crop(1*width, 0*height, width, height);
		BlinkyLeft[0] = sheetG.crop(1*width, 1*height, width, height);
		BlinkyRight[1] = sheetG.crop(0*width, 0*height, width, height);
		BlinkyRight[0] = sheetG.crop(0*width, 1*height, width, height);
		
		ClydeDown = new BufferedImage[2];
		ClydeUp = new BufferedImage[2];
		ClydeLeft = new BufferedImage[2];
		ClydeRight = new BufferedImage[2];
		ClydeDown[1] = sheetG.crop(6*width, 0*height, width, height);
		ClydeDown[0] = sheetG.crop(6*width, 1*height, width, height);
		ClydeUp[1] = sheetG.crop(7*width, 0*height, width, height);
		ClydeUp[0] = sheetG.crop(7*width, 1*height, width, height);
		ClydeLeft[1] = sheetG.crop(5*width, 0*height, width, height);
		ClydeLeft[0] = sheetG.crop(5*width, 1*height, width, height);
		ClydeRight[1] = sheetG.crop(4*width, 0*height, width, height);
		ClydeRight[0] = sheetG.crop(4*width, 1*height, width, height);
		
		PinkyDown = new BufferedImage[2];
		PinkyUp = new BufferedImage[2];
		PinkyLeft = new BufferedImage[2];
		PinkyRight = new BufferedImage[2];
		PinkyDown[1] = sheetG.crop(6*width, 2*height, width, height);
		PinkyDown[0] = sheetG.crop(6*width, 3*height, width, height);
		PinkyUp[1] = sheetG.crop(7*width, 2*height, width, height);
		PinkyUp[0] = sheetG.crop(7*width, 3*height, width, height);
		PinkyLeft[1] = sheetG.crop(5*width, 2*height, width, height);
		PinkyLeft[0] = sheetG.crop(5*width, 3*height, width, height);
		PinkyRight[1] = sheetG.crop(4*width, 2*height, width, height);
		PinkyRight[0] = sheetG.crop(4*width, 3*height, width, height);
		
		// Strings
		GameOver = sheetStrings.crop(0*600, 0*100, 600, 100);
		YourScore = sheetStrings.crop(0*600, 1*100, 600, 200);
		MenuReturn = sheetStrings.crop(0*600, 3*100, 600, 200);
		LevelCompleted = sheetStrings.crop(1*600, 0*100, 600, 200);
		NextLevel = sheetStrings.crop(1*600, 3*100, 600, 200);
		Background =  sheetBack.crop(0,0, 700, 600);
		
		// Static Objects
		Cookie = sheetObj.crop(0*width, 0*height, width, height);
		
		// Tiles
		BlueBrick = sheet.crop(0, 2*height, width, height);
		BlueBrokenBrick = sheet.crop(width, 2*height, width, height);
		RedBrick = sheet.crop(2*width, 2*height, width, height);
		RedBrokenBrick = sheet.crop(3*width, 2*height, width, height);
		BlueDoor = sheet.crop(0, 3*height, width, height);
		RedDoor = sheet.crop(width, 3*height, width, height);
		GrassDoor = sheet.crop( 2*width, 3*height, width, height);
		BlueBrickClosed= sheet.crop(4*width, 2*height, width, height);
		RedBrickClosed= sheet.crop(5*width, 2*height, width, height);
		BlueFloor = sheet.crop(0*width, 1*height, width, height);
		RedFloor = sheet.crop(1*width, 1*height, width, height);
		Grass = sheet.crop(2*width, 1*height, width, height);
		Wood = sheet.crop(0*width, 0*height, width, height);
		ClosedWood = sheet.crop(1*width, 0*height, width, height);
	}
	
	// Klasa która aktualizuje grafikê Pac Mana zmieniaj¹c jego zmienne wyboru postaci
	public static void UpdatePlayer(int n, int m) {
		SpriteSheet sheetP = new SpriteSheet(ImageLoader.loadImage("/textures/sheetP.png"));
		characterNumber1 = n;
		characterNumber2 = m;
		
		PlayerDown[1] = sheetP.crop((2+characterNumber1)*width, (0+characterNumber2)*height, width, height);
		PlayerDown[0] = sheetP.crop((2+characterNumber1)*width, (1+characterNumber2)*height, width, height);
		PlayerUp[1] = sheetP.crop((3+characterNumber1)*width, (0+characterNumber2)*height, width, height);
		PlayerUp[0] = sheetP.crop((3+characterNumber1)*width, (1+characterNumber2)*height, width, height);
		PlayerLeft[1] = sheetP.crop((1+characterNumber1)*width, (0+characterNumber2)*height, width, height);
		PlayerLeft[0] = sheetP.crop((1+characterNumber1)*width, (1+characterNumber2)*height, width, height);
		PlayerRight[1] = sheetP.crop((0+characterNumber1)*width, (0+characterNumber2)*height, width, height);
		PlayerRight[0] = sheetP.crop((0+characterNumber1)*width, (1+characterNumber2)*height, width, height);
	}
	
}
