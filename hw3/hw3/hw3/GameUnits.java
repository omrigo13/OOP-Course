package hw3;

public abstract class GameUnits {
	
	
	protected String name;
	protected int health_pool;
	protected int current_health;
	protected int attack_points;
	protected int defense_points;
	protected int p_x;
	protected int p_y;
	
	
	public int range(int px, int py) {
		int x = this.getP_x()-px;
		x*=x;
		int y = this.getP_y()-py;
		y*=y;
		return (int)Math.sqrt(x+y);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHealth_pool() {
		return health_pool;
	}
	public void setHealth_pool(int health_pool) {
		this.health_pool = health_pool;
	}
	public int getCurrent_health() {
		return current_health;
	}
	public void setCurrent_health(int current_health) {
		this.current_health = current_health;
	}
	public int getAttack_points() {
		return attack_points;
	}
	public void setAttack_points(int attack_points) {
		this.attack_points = attack_points;
	}
	public int getDefense_points() {
		return defense_points;
	}
	public void setDefense_points(int defense_points) {
		this.defense_points = defense_points;
	}
	public int getP_x() {
		return p_x;
	}
	public void setP_x(int p_x) {
		this.p_x = p_x;
	}
	public int getP_y() {
		return p_y;
	}
	public void setP_y(int p_y) {
		this.p_y = p_y;
	}

}
