package Utilities;

import static java.lang.Math.sqrt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Klasa z przydatnymi funkcjami
public class Utils {
	
	public static Random RandomNumber = new Random();
	
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null)
				builder.append(line + "\n");	
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static void saveToFile(String path,String string){	
		try{
	        PrintWriter zapis = new PrintWriter(path);
	         zapis.println(string);
	         zapis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public static int parseInt(String number){
		try{
			return Integer.parseInt(number);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public static float GetPath(float x,float y, float x2,float y2) {
		float path = (float) sqrt((x-x2)*(x-x2) + (y-y2)*(y-y2));
		return path;
	}
	
	public static float[] ShortestPath(ArrayList<Float> Paths) {
		float[] path = new float[Paths.size()];
		for(int i=0;i<Paths.size();i++) {
			path[i]=Paths.get(i);}
		Arrays.sort(path);
		return path;
	}

}