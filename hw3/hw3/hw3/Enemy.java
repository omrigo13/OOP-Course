package hw3;

import java.util.ArrayList;

public abstract class Enemy extends GameUnits {
	
	private int exp_val;
	private char tile;
	
	public Enemy(String name,int health_pool,int current_health,int attack_points,
			int defense_points,int p_x,int p_y,int exp_val, char tile) {

		this.name =name;
		this.health_pool =health_pool;
		this.current_health =current_health;
		this.attack_points = attack_points;
		this.defense_points = defense_points;
		this.p_x = p_x;
		this.p_y = p_y;
		this.exp_val = exp_val;
		this.tile = tile;
	}
	
	public abstract void enemyTurn(Player pl, Board board, ArrayList<Integer> random);
	
	public void combat(Player defender,Board board) {
		combat(defender, board, null);
	}
	
	public void combat(Player defender,Board board, ArrayList<Integer> random) {
		System.out.println(this.getName() + " engaged in battle with " + defender.getName() + ":\n" + this.toString() + "\n" + defender.toString() + "\n");
		RandomGenerator rand = new RandomGeneratorImpl();
		int attacker_p, deffender_p;
		if(random == null || random.size() == 0) {
			attacker_p = rand.nextInt(this.getAttack_points());
			deffender_p = rand.nextInt(defender.getDefense_points());
		}
		else if (random.size() == 1){
			attacker_p = random.remove(0);
			deffender_p = rand.nextInt(defender.getDefense_points());
		}
		else {
			attacker_p = random.remove(0);
			deffender_p = random.remove(0);
		}
		System.out.println(this.getName() + " rolled " + attacker_p + " attack points.");
		System.out.println(defender.getName() + " rolled " + deffender_p + " defense points.");
		if (attacker_p-deffender_p > 0) {
			defender.setCurrent_health(defender.getCurrent_health()-(attacker_p-deffender_p));
			System.out.println(this.getName() + " hit " + defender.getName() + " for " + (attacker_p-deffender_p) + " damage.");
		}
		else
			System.out.println(this.getName() + " hit " + defender.getName() + " for 0 damage.");
		if (defender.getCurrent_health() <= 0) {
			board.update(defender.getP_x(),defender.getP_y(),'X');
			System.out.println(defender.getName() + " died.\nYou Lost.");
			System.exit(0);
		}
	}

	public int getExp_val() {
		return exp_val;
	}

	public void setExp_val(int exp_val) {
		this.exp_val = exp_val;
	}

	public char getTile() {
		return tile;
	}

	public void setTile(char tile) {
		this.tile = tile;
	}
	
	public String toString() {// Enemy toString
		return this.getName() + "		Health: " + this.getCurrent_health() + "		Attack damage: " + this.getAttack_points() + 
		"		Defense: " + this.getDefense_points();
	}
	
	
	
	
	
	
	
}
