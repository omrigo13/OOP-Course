package hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RandomGeneratorImpl implements RandomGenerator{
	
	public int nextInt(int n) {
		Random rand = new Random(); 
		return rand.nextInt(n);
	}
	
	public ArrayList<Integer> next(String path) {
		ArrayList<Integer> n = new ArrayList<>();
		try {
			BufferedReader random = new BufferedReader(new FileReader(path));
			String next;
			while((next = random.readLine()) != null)
				n.add(Integer.parseInt(next));
			random.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Unable to open file '" + path + "'");
		}
		catch (IOException e) {
			System.out.println("Error reading file '" + path + "'");
		}
		return n;
	}
}
