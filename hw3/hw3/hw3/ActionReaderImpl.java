package hw3;

import java.util.Scanner;

public class ActionReaderImpl implements ActionReader{
	
	Scanner reader = new Scanner(System.in);
	public String nextAction() {
		String action = reader.next();
		return action;
	}
}
