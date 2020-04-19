package hw3;

import java.util.ArrayList;

public class Warrior extends Player{
	
	private int cool_down;
	private int remaining;
	
	
	public Warrior(String name, int health_pool, int current_health, int attack_points, int defense_points, int p_x,
			int p_y, String special_ability,int cool_down) {
		super(name, health_pool, current_health, attack_points, defense_points, p_x, p_y, special_ability);
		this.cool_down = cool_down;
		this.remaining = 0;
	}
	
	@Override
	public void levelUp() { //Warrior Class
		int curr_health = this.getHealth_pool();
		int curr_attack = this.getAttack_points();
		int curr_def = this.getDefense_points();
		this.levelUpToDo();
		this.setRemaining(0);
		this.setHealth_pool(this.getHealth_pool() + (5*(this.getLevel()-1)));
		this.setDefense_points(this.getDefense_points() + (1*(this.getLevel()-1)));
		System.out.println("Level up: +" + (this.getHealth_pool() - curr_health) + " Health, +" + (this.getAttack_points() - curr_attack) + " Attack, +"
		+ (this.getDefense_points() - curr_def) + " Defense");
		
	}
	
	@Override
	public void game_tick() {
		if(this.getRemaining() > 0)
			this.setRemaining(this.getRemaining() - 1);
	}
	
	@Override
	public void abiliti_cast(Board board, ArrayList<Integer> random) {
		if (this.getRemaining() > 0) 
			System.out.println("Error");
		else {
			System.out.println(this.getName() + " cast Heal, healing for " + (2*this.getDefense_points()));
			this.setRemaining(this.getCool_down());
			this.setCurrent_health(Math.min(this.getCurrent_health()+(2*this.getDefense_points()),
					                  this.getHealth_pool()));
		}
			
		
		
	}

	public int getCool_down() {
		return cool_down;
	}

	public void setCool_down(int cool_down) {
		this.cool_down = cool_down;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	public String toString() { //Warrior toString
		return this.getName() + "		Health: " + this.getCurrent_health() + "		Attack damage: " + this.getAttack_points() 
		+ "		Defense: " + this.getDefense_points() + "\n" + "		Level: " + this.getLevel() + "		Experience: " 
		+ this.getExperience() + "/" + (this.getLevel()*50) + "		Ability cooldown: " + this.getCool_down() + "		Remaining: " 
		+ this.getRemaining();
	}
	

	
	

}
