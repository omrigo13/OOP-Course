package hw3;

import java.util.ArrayList;

public class Mage extends Player {
	
	private int mana_pool;
	private int spell_power;
	private int current_mana;
	private int cost;
	private int hit_times;
	private int range;
	
	public Mage(String name, int health_pool, int current_health, int attack_points, int defense_points, int p_x,
			int p_y, String special_ability,int spell_power,int mana_pool,int cost,int hit_times,int range) {
		super(name, health_pool, current_health, attack_points, defense_points, p_x, p_y, special_ability);
		this.mana_pool = mana_pool;
		this.spell_power = spell_power;
		this.current_mana = mana_pool/4;
		this.cost = cost;
		this.hit_times =hit_times;
		this.range = range;
	}

	@Override
	public void levelUp() {
		int curr_health = this.getHealth_pool();
		int curr_attack = this.getAttack_points();
		int curr_def = this.getDefense_points();
		int mana_pool = this.getMana_pool();
		int spell_power = this.getSpell_power();
		
		this.levelUpToDo();
		this.setCurrent_mana(Math.min(this.getCurrent_mana()+(this.getMana_pool()/4),
				               this.getMana_pool()));
		this.setMana_pool(this.getMana_pool() + (25*(this.getLevel()-1)));
		this.setSpell_power(this.getSpell_power()+(10*this.getLevel()-1));
		
		System.out.println("Level up: +" + (this.getHealth_pool() - curr_health) + " Health, +" + (this.getAttack_points() - curr_attack) + " Attack, +"
				+ (this.getDefense_points() - curr_def) + " Defense");
		System.out.println("		+" + (this.getMana_pool() - mana_pool) + " maximum mana, +" + (this.getSpell_power() -  spell_power + " spell power"));
		
	}

	@Override
	public void game_tick() {
		this.setCurrent_mana(Math.min(this.getMana_pool(),this.getCurrent_mana()+1));
		
	}

	public int getMana_pool() {
		return mana_pool;
	}

	public void setMana_pool(int mana_pool) {
		this.mana_pool = mana_pool;
	}

	public int getSpell_power() {
		return spell_power;
	}

	public void setSpell_power(int spell_power) {
		this.spell_power = spell_power;
	}

	public int getCurrent_mana() {
		return current_mana;
	}

	public void setCurrent_mana(int current_mana) {
		this.current_mana = current_mana;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getHit_times() {
		return hit_times;
	}

	public void setHit_times(int hit_times) {
		this.hit_times = hit_times;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	@Override
	public void abiliti_cast(Board board, ArrayList<Integer> random) { 
		if (this.getCurrent_mana() < this.getCost()) 
			System.out.println("Error");
		else {
			System.out.println(this.getName() + " cast Blizzard.");
			this.setCurrent_mana(this.getCurrent_mana()-this.getCost());
			int hits = 0;
			ArrayList<Enemy> EnemiesInRange = EnemyInRange(board.getEnemies(), this.getRange()); 
			while(hits < this.getHit_times() && EnemiesInRange.size() != 0) { 
				RandomGenerator rand = new RandomGeneratorImpl();
				int n, enemydef;
				if(random == null || random.size() == 0) {
					n = rand.nextInt(EnemiesInRange.size());
					enemydef = rand.nextInt(EnemiesInRange.get(n).getDefense_points());
				}
				else if (random.size() == 1) {
					n = random.remove(0);
					enemydef = rand.nextInt(EnemiesInRange.get(n).getDefense_points());
				}
				else {
					n = random.remove(0);
					enemydef = random.remove(0);
				}
				if(n < EnemiesInRange.size()) {
					System.out.println(EnemiesInRange.get(n).getName() + " rolled " + enemydef + " defense points.");
					int damage = this.getSpell_power() - enemydef;
					if (damage > 0){
						EnemiesInRange.get(n).setCurrent_health(EnemiesInRange.get(n).getCurrent_health() - damage);
					}
					else
						damage = 0;
					System.out.println(this.getName() + " hit " + EnemiesInRange.get(n).getName() + " for " + damage + " ability damage.");
					if(EnemiesInRange.get(n).getCurrent_health() <= 0) {
						this.setExperience(this.getExperience()+EnemiesInRange.get(n).getExp_val());
						System.out.println(EnemiesInRange.get(n).getName() + " died. " + this.getName() + " gained " + EnemiesInRange.get(n).getExp_val() + " experience!");
						board.delete(EnemiesInRange.get(n),'.');
						EnemiesInRange.remove(n);
					}
				}
				if (this.getExperience() >= this.getLevel()*50)
					this.levelUp();
				hits++;
			}
		}
		

	}
	public String toString() { // Mage toString
		return this.getName() + "		Health: " + this.getCurrent_health() + "		Attack damage: " + this.getAttack_points() +
		 "		Defense: " + this.getDefense_points() + "\n 		Level: " + this.getLevel() + "		Experience: " 
			+ this.getExperience() + "/" + (this.getLevel()*50) + "		SpellPower: " + this.getSpell_power() + "		Mana: " 
			+ this.getCurrent_mana() + "/" + this.getMana_pool();
	}
	
	
}
