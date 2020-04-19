package hw3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	
	private char[][] board;
	private ArrayList<Enemy> enemies;
	
	public Board(String path) {
		this.readAllLines(path);
		this.enemies = null;
	}

	public void readAllLines(String path) {
	int[] size = new int[2];
	try {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String next;
		next = reader.readLine();
		while((next) != null) {
			size[0]+=1;
			size[1] = next.length();
			next = reader.readLine();
		}
		Scanner input = new Scanner(new File(path));
		char[][] board = new char[size[0]][size[1]];
		for(int row = 0; row < board.length; row++) {
			next = input.nextLine();
			for(int col = 0; col < board[row].length; col++) {
				char nextChar = next.charAt(col);
				board[row][col] = nextChar;
			}
		}
		this.board = board;
		reader.close();
		input.close();
	}
		catch (FileNotFoundException e) {
			System.out.println("Unable to open file '" + path + "'");
		}
		catch (IOException e) {
			System.out.println("Error reading file '" + path + "'");
		}
	}	

	
	public void delete(Enemy enm,char c) {
		this.update(enm.getP_x(),enm.getP_y(),c);
		this.enemies.remove(enm);
	}
	
	public void update(int x, int y, char c) {
		this.getBoard()[x][y]=c;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}


	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	public String toString() { //prints board
		String str = "";
		for(int i = 0; i < this.getBoard().length; i++)
		{
			for(int j = 0; j < this.getBoard()[i].length; j++)
				str+=this.getBoard()[i][j];
			str+="\n";
		}
		return str;
	}
	
	public void listEnemy()
	{
		ArrayList<Enemy> enemyList=new ArrayList<Enemy>();
		for (int i=0;i<this.getBoard().length;i++)
		{
			for (int j=0;j<this.getBoard()[i].length;j++)
			{
				switch(this.getBoard()[i][j])
				{
					case 's':
						enemyList.add(new Monster("Lannister Soldier",80,80,8,3,i,j,25,'s',3));
						break;
					case 'k':
						enemyList.add(new Monster("Lannister Knight",200,200,14,8,i,j,50,'k',4));
						break;
					case 'q':
						enemyList.add(new Monster("Queen's Gurd",400,400,20,15,i,j,100,'q',5));
						break;
					case 'z':
						enemyList.add(new Monster("Wright",600,600,30,15,i,j,100,'z',3));
						break;
					case 'b':
						enemyList.add(new Monster("Bear-Wright",1000,1000,75,30,i,j,250,'b',4));
						break;
					case 'g':
						enemyList.add(new Monster("Giant-Wright",1500,1500,100,40,i,j,500,'g',5));
						break;
					case 'w':
						enemyList.add(new Monster("White Walker",2000,2000,150,50,i,j,1000,'w',6));
						break;
					case 'M':
						enemyList.add(new Monster("The Mountain",1000,1000,60,25,i,j,500,'M',6));
						break;
					case 'C':
						enemyList.add(new Monster("Queen Cersei",100,100,10,10,i,j,1000,'C',1));
						break;
					case 'K':
						enemyList.add(new Monster("Night's King",5000,5000,300,150,i,j,5000,'K',8));
						break;
					case 'B':
						enemyList.add(new Trap("Bonus \"Trap\""+'"',1,1,1,1,i,j,250,'B',5,6,2)); 
						break;
					case 'Q':
						enemyList.add(new Trap("Queen's Trap",250,250,50,10,i,j,100,'Q',4,10,4));
						break;
					case 'D':
						enemyList.add(new Trap("Death Trap",500,500,100,20,i,j,250,'D',6,10,3));
						break;
				}
			}
		}
		this.enemies=enemyList;
	}
	
	public void nextLevel(String path) {
		this.readAllLines(path);
		this.listEnemy();
	}
}
