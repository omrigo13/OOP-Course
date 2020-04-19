package hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

	public static String find(String path, String item) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for(File file : listOfFiles)
			if(file.getName().compareTo(item) == 0)
				return file.getPath();
		return "";
	}
	
	public static void main(String[] args) {
		String level = find(args[0], "level 1.txt"), actions = find(args[0], "user_actions.txt"), random = find(args[0], "random_numbers.txt");
		File folder = new File(args[0]);
		File[] listOfFiles = folder.listFiles();
		int maxlevel = listOfFiles.length;
		Scanner in = new Scanner(System.in);
		Board gameBoard = new Board(level);
		gameBoard.listEnemy();
		Player player = null;
		System.out.println("Select player");
		System.out.println("1. Jon Snow		Health: 300		Attack damage: 30		Defense: 4\r\n" + 
				"		Level: 1		Experience: 0/50		Ability cooldown: 6		Remaining: 0");
		System.out.println("2. The Hound		Health: 400		Attack damage: 20		Defense: 6\r\n" + 
				"		Level: 1		Experience: 0/50		Ability cooldown: 4		Remaining: 0");
		System.out.println("3. Melisandre		Health: 160		Attack damage: 10		Defense: 1\r\n" + 
				"		Level: 1		Experience: 0/50		SpellPower: 15		Mana: 75/300");
		System.out.println("4. Thoros of Myr		Health: 250		Attack damage: 25		Defense: 3\r\n" + 
				"		Level: 1		Experience: 0/50		SpellPower: 20		Mana: 37/150");
		System.out.println("5. Arya Stark		Health: 150		Attack damage: 40		Defense: 2\r\n" + 
				"		Level: 1		Experience: 0/50		Energy: 100/100");
		System.out.println("6. Bronn		Health: 250		Attack damage: 35		Defense: 3\r\n" + 
				"		Level: 1		Experience: 0/50		Energy: 100/100");
		if(args.length != 0 && args[args.length-1].equals("-D") == true)
			DeterministicMode(args[0], actions, random, maxlevel);
		else {
			int pl_ch=in.nextInt();
			switch(pl_ch) {
				case 1:
					player=new Warrior("Jon Snow", 300, 300, 30, 4, 0,0,"heal",6);
					break;
				case 2:
					player=new Warrior("The Hound", 400, 400, 20, 6, 0,0 ,"heal",4);
					break;
				case 3:
					player =new Mage("Melisandre", 160, 160, 10, 1, 0,0,"Blizzard",40, 300, 30, 5, 6);
					break;
				case 4: 
					player=new Mage("Thoros of Myr", 250, 250, 25, 3, 0,0,"Blizzard",15, 150, 50, 3, 3);
					break;
				case 5:
					player=new Rouge("Arya Stark",150,150,40,2,0,0,"Fan of knives",20);
					break;
				case 6:
					player=new Rouge("Bronn",250,250,35,3,0,0,"Fan of Knives",60);
			}
			player.getPosFromBoard(gameBoard);
			System.out.println("You have selected:" + "\n" + player.toString() + "\nUse w/s/a/d to move." + "\nUse e for special ability or q to pass");
			System.out.println(gameBoard);
			System.out.println(player);
			int count_level = 2;
			ActionReader action = new ActionReaderImpl();
			while (((count_level < (maxlevel + 2)) && (gameBoard.getEnemies().size() != 0)) && 
				gameBoard.getBoard()[player.getP_x()][player.getP_y()]!='X') {
				String move = action.nextAction();
				player.move(move, gameBoard, null);
				for (int i=0;i<gameBoard.getEnemies().size();i++) {
					if(gameBoard.getBoard()[player.getP_x()][player.getP_y()] == 'X')
						break;
						gameBoard.getEnemies().get(i).enemyTurn(player, gameBoard, null);
				}
				if(gameBoard.getEnemies().size()==0 && count_level < (maxlevel + 1)) {
					gameBoard.nextLevel(find(args[0],"level " + count_level + ".txt")); //C:\\Users\\omrig\\Desktop\\level
					player.getPosFromBoard(gameBoard);
					count_level++;
				}
				System.out.println(gameBoard);
				System.out.println(player);
			}
			if(count_level == (maxlevel + 1))
				System.out.println("Game is finished. You won!");
			in.close();
		}
	}
	
	public static void DeterministicMode(String path,String actions, String random_numbers, int maxlevel) {
		int count_level = 2;
		Board gameBoard = new Board(find(path,"level 1.txt"));
		gameBoard.listEnemy();
		Player player = null;
		String player_choice , move;
		RandomGenerator rand = new RandomGeneratorImpl();
		ArrayList<Integer> random = rand.next(random_numbers);
		try {
			BufferedReader action = new BufferedReader(new FileReader(actions));
			player_choice = action.readLine();
			switch(Integer.parseInt(player_choice))
			{
				case 1:
					player=new Warrior("Jon Snow", 300, 300, 30, 4, 0,0,"heal",6);
					break;
				case 2:
					player=new Warrior("The Hound", 400, 400, 20, 6, 0,0 ,"heal",4);
					break;
				case 3:
					player =new Mage("Melisandre", 160, 160, 10, 1, 0,0,"Blizzard",40, 300, 30, 5, 6); //160 = 1
					break;
				case 4: 
					player=new Mage("Thoros of Myr", 250, 250, 25, 3, 0,0,"Blizzard",15, 150, 50, 3, 3);
					break;
				case 5:
					player=new Rouge("Arya Stark",150,150,40,2,0,0,"Fan of knives",20);
					break;
				case 6:
					player=new Rouge("Bronn",250,250,35,3,0,0,"Fan of Knives",60);
			}
			player.getPosFromBoard(gameBoard);
			System.out.println("You have selected:" + "\n" + player.toString() + "\nUse w/s/a/d to move." + "\nUse e for special ability or q to pass");
			System.out.println(gameBoard);
			System.out.println(player);
			move = action.readLine();
			while (((count_level < maxlevel) && (gameBoard.getEnemies().size() != 0)) && 
					gameBoard.getBoard()[player.getP_x()][player.getP_y()]!='X') { // move != null
				if(move == null) {
					int n = rand.nextInt(6);
					switch(n) {
					case 0: move = "a"; break;
					case 1: move = "s"; break;
					case 2: move = "d"; break;
					case 3: move = "w"; break;
					case 4: move = "q"; break;
					case 5: move = "e"; break;
					}
				}
				System.out.println(move);
				player.move(move, gameBoard, random);
				move = action.readLine();
				for (int i=0;i<gameBoard.getEnemies().size();i++) {
					if(gameBoard.getBoard()[player.getP_x()][player.getP_y()] == 'X')
						break;
					gameBoard.getEnemies().get(i).enemyTurn(player, gameBoard, random);
				}
				if(gameBoard.getEnemies().size()==0 && count_level < (maxlevel - 1)) {
					gameBoard.nextLevel(find(path,"level " + count_level + ".txt"));//C:\\Users\\omrig\\Desktop\\Minimized level
					player.getPosFromBoard(gameBoard);
					count_level++;
				}
				System.out.println(gameBoard);
				System.out.println(player);
			}
			if(count_level == (maxlevel - 1))
				System.out.println("Game is finished. You won!");
			action.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Unable to open file '" + path + "'");
		}
		catch (IOException e) {
			System.out.println("Error reading file '" + path + "'");
		}
	}
}
