package hw3;

import java.util.ArrayList;

public abstract class Player extends GameUnits {


	private int experience;
	private int level;
	private String special_ability;
	
	
	public Player(String name,int health_pool,int current_health,int attack_points,
			int defense_points,int p_x,int p_y,String special_ability) {
		
		this.experience = 0;
		this.level = 1;
		this.special_ability = special_ability;
		this.name =name;
		this.health_pool =health_pool;
		this.current_health =current_health;
		this.attack_points = attack_points;
		this.defense_points = defense_points;
		this.p_x = p_x;
		this.p_y = p_y;
		
	}
	
	public void levelUpToDo() {
		this.setExperience(this.getExperience()-(50*this.getLevel()));
		this.setCurrent_health(this.getHealth_pool());
		this.setHealth_pool(this.getHealth_pool()+(10*this.getLevel()));
		this.setAttack_points(this.getAttack_points()+(5*this.getLevel()));
		this.setDefense_points(this.getDefense_points()+(2*this.getLevel()));
		this.setLevel(this.getLevel()+1);
	}
	
	public abstract void levelUp();
	
	public abstract void game_tick();
	
	public abstract void abiliti_cast(Board board, ArrayList<Integer> random);
	
	public ArrayList<Enemy> EnemyInRange(ArrayList<Enemy> enemies, int range) { 
		ArrayList<Enemy> RangeEnemies = new ArrayList<Enemy>();
		for(int i = 0; i < enemies.size(); i++){
			if(this.range(enemies.get(i)) < range)
				RangeEnemies.add(enemies.get(i));
		}
		return RangeEnemies;
	}

	public int range(Enemy en) {
		int x = this.getP_x()-en.getP_x();
		x*=x;
		int y = this.getP_y()-en.getP_y();
		y*=y;
		return (int)Math.sqrt(x+y);
	}
	
	public void move(String c,Board board) {
		move(c, board, null);
	}
	
	public void move(String c,Board board, ArrayList<Integer> random) {
		this.game_tick();
		switch(c) {
		case "w":
			if(random == null || random.size() == 0) {
				if(board.getBoard()[this.getP_x()-1][this.getP_y()] != '#')
					if(this.check(this.getP_x()-1, this.getP_y(), board)==null)
						this.moveUp(board);
					else
						this.combat(this.check(this.getP_x()-1, this.getP_y(), board), board);
			}
			else {
				if(board.getBoard()[this.getP_x()-1][this.getP_y()] != '#')
					if(this.check(this.getP_x()-1, this.getP_y(), board)==null)
						this.moveUp(board);
					else
						this.combat(this.check(this.getP_x()-1, this.getP_y(), board), board, random);
			}
			break;
		case "s":
			if(random == null || random.size() == 0) {
				if(board.getBoard()[this.getP_x()+1][this.getP_y()] != '#')
					if(this.check(this.getP_x()+1, this.getP_y(), board)==null)
						this.moveDown(board);
					else
						this.combat(this.check(this.getP_x()+1, this.getP_y(), board), board);
			}
			else {
				if(board.getBoard()[this.getP_x()-1][this.getP_y()] != '#')
					if(this.check(this.getP_x()-1, this.getP_y(), board)==null)
						this.moveUp(board);
					else
						this.combat(this.check(this.getP_x()-1, this.getP_y(), board), board, random);
			}
			break;
		case "a":
			if(random == null || random.size() == 0) {
				if(board.getBoard()[this.getP_x()][this.getP_y()-1] != '#')
					if(this.check(this.getP_x(), this.getP_y()-1, board)==null)
						this.moveLeft(board);
					else
						this.combat(this.check(this.getP_x(), this.getP_y()-1, board), board);
			}
			else {
				if(board.getBoard()[this.getP_x()-1][this.getP_y()] != '#')
					if(this.check(this.getP_x()-1, this.getP_y(), board)==null)
						this.moveUp(board);
					else
						this.combat(this.check(this.getP_x()-1, this.getP_y(), board), board, random);
			}
			break;
		case "d":
			if(random == null || random.size() == 0) {
				if(board.getBoard()[this.getP_x()][this.getP_y()+1] != '#')
					if(this.check(this.getP_x(), this.getP_y()+1, board)==null)
						this.moveRight(board);
					else
						this.combat(this.check(this.getP_x(), this.getP_y()+1, board), board);
			}
			else {
				if(board.getBoard()[this.getP_x()-1][this.getP_y()] != '#')
					if(this.check(this.getP_x()-1, this.getP_y(), board)==null)
						this.moveUp(board);
					else
						this.combat(this.check(this.getP_x()-1, this.getP_y(), board), board, random);
			}
			break;
		case "e":
			if(random == null || random.size() == 0)
				this.abiliti_cast(board, random);
			else
				this.abiliti_cast(board, random);
			break;
		case "q":
			break;
	    }
	}
	
	public void moveLeft(Board board)
	{
		board.update(this.getP_x(), this.getP_y()-1, '@');
		board.update(this.getP_x(), this.getP_y(), '.');
		this.setP_y(this.getP_y()-1);
	}
	
	public void moveRight(Board board)
	{
		board.update(this.getP_x(), this.getP_y()+1, '@');
		board.update(this.getP_x(), this.getP_y(), '.');
		this.setP_y(this.getP_y()+1);
	}
	
	public void moveUp(Board board)
	{
		board.update(this.getP_x()-1, this.getP_y(), '@');
		board.update(this.getP_x(), this.getP_y(), '.');
		this.setP_x(this.getP_x()-1);
	}
	
	public void moveDown(Board board)
	{
		board.update(this.getP_x()+1, this.getP_y(), '@');
		board.update(this.getP_x(), this.getP_y(), '.');
		this.setP_x(this.getP_x()+1);
	}
	
	public Enemy check(int x, int y, Board board) {
		for(int i = 0; i < board.getEnemies().size(); i++) {
			if(board.getEnemies().get(i).getP_x() == x && 
					board.getEnemies().get(i).getP_y() == y)
				return board.getEnemies().get(i);
		}
		return null;
	}
	
	public void combat(Enemy defender,Board board) {
		combat(defender, board, null);
	}
	
	public void combat(Enemy defender,Board board, ArrayList<Integer> random) {
		System.out.println(this.getName() + " engaged in battle with " + defender.getName() + ":\n" + this.toString() + "\n" + defender.toString() + "\n");
		RandomGenerator rand = new RandomGeneratorImpl();
		int attacker_p, deffender_p;
		if(random == null || random.size() == 0) {
			attacker_p = rand.nextInt(this.getAttack_points());
			deffender_p = rand.nextInt(defender.getDefense_points());	
		}
		else {
			attacker_p = random.remove(0);
			deffender_p = random.remove(0);
		}
		System.out.println(this.getName() + " rolled " + attacker_p + " attack points.");
		System.out.println(defender.getName() + " rolled " + deffender_p + " defense points.");
		if (attacker_p-deffender_p>0) {
			defender.setCurrent_health(defender.getCurrent_health()-(attacker_p-deffender_p));
			System.out.println(this.getName() + " hit " + defender.getName() + " for " + (attacker_p-deffender_p) + " damage.");
			
		}
		else
			System.out.println(this.getName() + " hit " + defender.getName() + " for 0 damage.");
		if (defender.getCurrent_health()<=0) {
			this.setExperience(this.getExperience()+defender.getExp_val());
			board.update(this.getP_x() , this.getP_y(), '.');
			this.setP_x(defender.getP_x());
			this.setP_y(defender.getP_y());
			System.out.println(defender.getName() + " died. " + this.getName() + " gained " + defender.getExp_val() + " experience!");
			board.delete(defender,'@');
		}
		if (this.getExperience() >= this.getLevel()*50)
			this.levelUp();
	}
	
	public void getPosFromBoard(Board board) {
		for (int i = 0;i<board.getBoard().length;i++) 
			for(int j=0;j<board.getBoard()[i].length;j++) 
				if (board.getBoard()[i][j]=='@') {
					this.setP_x(i);
					this.setP_y(j);
					break;
				}
	}
	
	
	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSpecial_ability() {
		return special_ability;
	}

	public void setSpecial_ability(String special_ability) {
		this.special_ability = special_ability;
	}
	
		
}
