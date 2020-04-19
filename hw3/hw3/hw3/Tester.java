package hw3;

import java.util.ArrayList;

/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester {

	private static boolean testPassed = true;
	private static int testNum = 0;
	
	/**
	 * This entry function will test all classes created in this assignment.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
	
		// Each function here should test a different class.
		
		// random interface class
		testRandomGenerator();

		// Board class
		testBoard();
		
		// Player, Warrior, Mage, Rouge classes
		testPlayer();
		
		// Enemy, Monster, Trap classes
		testEnemy();

		// Notifying the user that the code have passed all tests. 
		if (testPassed) {
			System.out.println("All " + testNum + " tests passed!");
		}
	}

	/**
	 * This utility function will count the number of times it was invoked. 
	 * In addition, if a test fails the function will print the error message.  
	 * @param exp The actual test condition
	 * @param msg An error message, will be printed to the screen in case the test fails.
	 */
	private static void test(boolean exp, String msg) {
		testNum++;
		
		if (!exp) {
			testPassed = false;
			System.out.println("Test " + testNum + " failed: "  + msg);
		}
	}

	/**
	 * Checks the random interface class.
	 */
	private static void testRandomGenerator(){
		RandomGenerator rand = new RandomGeneratorImpl();
		int n = rand.nextInt(5);
		test(rand.nextInt(5) < 5, "should return random number between 0 - 5");
	}

	/**
	 * Checks the Board class.
	 */
	private static void testBoard(){
		Board board = new Board("level 1.txt");
		test(board.getBoard().length == 19, "the board should contains 19 rows");
		test(board.getBoard()[0].length == 49, "the board should contains 49 cols");
		test(board.getEnemies() == null, "the board should be empty");
		board.listEnemy();
		test(board.getEnemies().size() == 13, "the board should contains 13 enemies");
		test(board.getBoard()[9][1] == '@', "this place should contains the player location");
		for(int i = 0; i < board.getBoard().length; i++) {
			test(board.getBoard()[i][0] == '#', "the frame of the board should contains only '#'");
			test(board.getBoard()[i][48] == '#', "the frame of the board should contains only '#'");
		}
		for(int i = 0; i < board.getBoard()[0].length; i++) {
			test(board.getBoard()[0][i] == '#', "the frame of the board should contains only '#'");
			test(board.getBoard()[18][i] == '#', "the frame of the board should contains only '#'");
		}
		int x = board.getEnemies().get(0).getP_x();
		int y = board.getEnemies().get(0).getP_y();
		char c = board.getEnemies().get(0).getTile();
		test(board.getBoard()[x][y] == c, "the board should contains" + c + "in this location");
		board.delete(board.getEnemies().get(0), '.');
		test(board.getBoard()[x][y] == '.', "the board should contains '.' in this location");
		test(board.getEnemies().size() == 12, "the board should contains 12 enemies, 1 enemy should be deleted");
		board.update(9, 1, '.');
		board.update(9, 2, '@');
		test(board.getBoard()[9][1] == '.', "the board should contains '.' in this location");
		test(board.getBoard()[9][2] == '@', "the board should contains '@' in this location");
		board.nextLevel("level 2.txt");
		test(board.getEnemies().size() == 28, "the board should contains 28 enemies");
		test(board.getBoard()[19][12] == '@', "the board should contains '@' in this location");
	}
	
	/**
	 * Checks the Player class including subclasses Warrior, Mage, Rouge.
	 */
	private static void testPlayer() {
		Board board = new Board("level 1.txt");
		board.listEnemy();
		Warrior warrior = new Warrior("Jon Snow", 300, 250, 30, 4, 9,1,"heal",6);
		Mage mage = new Mage("Melisandre", 160, 160, 10, 1, 9,1,"Blizzard",40, 300, 30, 5, 6);
		Rouge rouge = new Rouge("Arya Stark",150,150,40,2,9,1,"Fan of knives",20);
		test(warrior.EnemyInRange(board.getEnemies(), 100).size() == 13, "the board should contains 13 enemies");
		test(warrior.range(board.getEnemies().get(0)) == 8, "the range between the player and the enemy should be 8");
		warrior.move("w", board);
		mage.move("s", board);
		rouge.move("d", board);
		test(rouge.getP_x() == 9 && rouge.getP_y() == 2, "should go right");
		rouge.move("a", board);
		warrior.move("a", board);
		mage.move("e", board);
		rouge.move("q", board);
		test(warrior.getP_x() == 8 && warrior.getP_y() == 1, "should go up");
		test(mage.getP_x() == 10 && mage.getP_y() == 1, "should go down");
		test(rouge.getP_x() == 9 && rouge.getP_y() == 1, "should go left");
		test(warrior.getP_x() == 8 && warrior.getP_y() == 1, "should not move backward because there is '#'");
		test(mage.getCurrent_mana() == 47, "should cast ability");
		test(rouge.getP_x() == 9 && rouge.getP_y() == 1, "should do nothing");
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(20);
		numbers.add(10);
		warrior.combat(board.getEnemies().get(0), board, numbers);
		test(board.getEnemies().get(0).getCurrent_health() == 70, "enemy should lose 10 hp");
		warrior.abiliti_cast(board, null);
		test(warrior.getCurrent_health() == 258, "warrior health should increase by 8 HP");
		warrior.game_tick();
		test(warrior.getRemaining() == 5, "warrior ability cast remaining should down by 1");
		mage.game_tick();
		test(mage.getCurrent_mana() == 48, "mage should have 48 on mana pool");
		numbers.add(5);
		rouge.setP_x(1);
		rouge.setP_y(6);
		rouge.abiliti_cast(board, numbers);
		test(board.getEnemies().get(0).getCurrent_health() == 35, "enemy should lose 35 hp");
		rouge.game_tick();
		test(rouge.getCurrent_energy() == 90, "rouge should have 90 on energy");
		warrior.levelUp();
		mage.levelUp();
		rouge.levelUp();
		mage.abiliti_cast(board, null);
		test(mage.getCurrent_mana() == 93, "mage should have 93 on mana pool");
		test(warrior.getLevel() == 2, "warrior should level up");
		test(mage.getLevel() == 2, "mage should level up");
		test(rouge.getLevel() == 2, "rouge should level up");
	}
	
	/**
	 * Checks the Enemy class including subclasses Monster, Trap.
	 */
	private static void testEnemy() {
		Board board = new Board("level 1.txt");
		board.listEnemy();
		Monster monster = new Monster("Lannister Soldier",80,80,8,3,1,2,25,'s',3);
		Trap trap = new Trap("Bonus \"Trap\""+'"',1,1,1,1,1,1,250,'B',5,6,2);
		Mage mage = new Mage("Melisandre", 160, 160, 10, 1, 9,1,"Blizzard",40, 300, 30, 5, 6);
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(20);
		numbers.add(10);
		trap.combat(mage, board, numbers);
		trap.setTicks_count(6);
		numbers.add(5);
		test(mage.getCurrent_health() == 150, "mage should lose 10 HP");
		trap.enemyTurn(mage, board, numbers);
		test(trap.getP_x() == 2 && trap.getP_y() == 2, "trap should move to (2,2)");
		numbers.add(2);
		monster.enemyTurn(mage, board, numbers);
		test(monster.getP_x() == 1 && monster.getP_y() == 2, "monster should move to (1,2)");
	}

}