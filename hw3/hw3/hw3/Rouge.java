package hw3;

import java.util.ArrayList;

public class Rouge extends Player{

	private int cost;
	private int current_energy;
	
	public Rouge(String name, int health_pool, int current_health, int attack_points, int defense_points, int p_x,
			int p_y, String special_ability,int cost) {
		super(name, health_pool, current_health, attack_points, defense_points, p_x, p_y, special_ability);
		this.cost =cost;
		this.current_energy= 100;
	}

	@Override
	public void levelUp() {
		int curr_health = this.getHealth_pool();
		int curr_attack = this.getAttack_points();
		int curr_def = this.getDefense_points();
		
		this.levelUpToDo();
		this.setCurrent_energy(100);
		this.setAttack_points(this.getAttack_points()+(3*this.getLevel()-1));
		
		System.out.println("Level up: +" + (this.getHealth_pool() - curr_health) + " Health, +" + (this.getAttack_points() - curr_attack) + " Attack, +"
				+ (this.getDefense_points() - curr_def) + " Defense");
		
	}

	@Override
	public void game_tick() {
		this.setCurrent_energy(Math.min(this.getCurrent_energy()+10, 100));
		
	}

	@Override
	public void abiliti_cast(Board board, ArrayList<Integer> random) { 
		if (this.getCurrent_energy() < this.getCost()) 
			System.out.println("Error");
		else {
			ArrayList<Enemy> EnemiesInRange = EnemyInRange(board.getEnemies(), 2);
			this.setCurrent_energy(this.getCurrent_energy()-this.getCost());
			System.out.println(this.getName() + " cast Fan of Knives.");
			RandomGenerator rand = new RandomGeneratorImpl();
			for(int i = 0; i < EnemiesInRange.size(); i++) {
				int enemydef;
				if(random == null || random.size() == 0)
					enemydef = rand.nextInt(EnemiesInRange.get(i).getDefense_points());
				else
					enemydef = random.remove(0);
				System.out.println(EnemiesInRange.get(i).getName() + " rolled " + enemydef + " defense points.");
				int damage = this.getAttack_points() - enemydef;
				if (damage > 0){
					EnemiesInRange.get(i).setCurrent_health(EnemiesInRange.get(i).getCurrent_health() - damage);
				}
				else
					damage = 0;
				System.out.println(this.getName() + " hit " + EnemiesInRange.get(i).getName() + " for " + damage + " ability damage.");
				if(EnemiesInRange.get(i).getCurrent_health() <= 0) {
					this.setExperience(this.getExperience()+EnemiesInRange.get(i).getExp_val());
					System.out.println(EnemiesInRange.get(i).getName() + " died. " + this.getName() + " gained " + EnemiesInRange.get(i).getExp_val() + " experience!");
					board.delete(EnemiesInRange.get(i),'.');
				}
				if (this.getExperience() >= this.getLevel()*50)
					this.levelUp();
			}
		}
		
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCurrent_energy() {
		return current_energy;
	}

	public void setCurrent_energy(int current_energy) {
		this.current_energy = current_energy;
	}
	
	public String toString() { //Rouge toString
		return this.getName() + "		Health: " + this.getCurrent_health() + "		Attack damage: " + this.getAttack_points() 
		+ "		Defense: " + this.getDefense_points() + "\n 		Level: " + this.getLevel() + "		Experience: " 
		+ this.getExperience() + "/" + (this.getLevel()*50) + "		Energy: " + this.getCurrent_energy() + "/100";
	}

}
