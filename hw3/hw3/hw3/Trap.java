package hw3;

import java.util.ArrayList;

public class Trap extends Enemy {
	
	private int r_range;
	private int r_time;
	private int vis_time;
	private int ticks_count;
	private char visible;
	
	public Trap(String name, int health_pool, int current_health, int attack_points, int defense_points, int p_x,
			int p_y, int exp_val, char tile,int r_range,int r_time,int vis_time) {
		super(name, health_pool, current_health, attack_points, defense_points, p_x, p_y, exp_val, tile);
		this.r_range = r_range;
		this.r_time = r_time;
		this.vis_time = vis_time;
		this.ticks_count = 0;
		this.visible = tile;
	}
	
	@Override
	public void enemyTurn(Player pl, Board board, ArrayList<Integer> random) {
		RandomGenerator rand = new RandomGeneratorImpl();
		if (this.getTicks_count()==this.getR_time()) {
			this.setTicks_count(0);
			ArrayList<int[]> options = new ArrayList<int[]>();
			for(int i = 0; i< board.getBoard().length; i++) {
				for(int j = 0; j < board.getBoard()[i].length; j++) {
					if(this.range(i, j) < this.getR_range() && board.getBoard()[i][j] == '.')
						options.add(new int[] {i,j});
				}
			}
			for(int i = 0; i < board.getEnemies().size(); i++) {
				for(int j = 0; j < options.size(); j++) {
					if(options.get(j)[0] == board.getEnemies().get(i).getP_x() &&
					options.get(j)[1] == board.getEnemies().get(i).getP_y())
						options.remove(j);
				}
			}
			int n;
			if(random == null || random.size() == 0)
				n = rand.nextInt(options.size());
			else
				n = random.remove(0);
			if(n < options.size()) {
				board.update(this.getP_x() , this.getP_y(), '.');
				this.setP_x(options.get(n)[0]);
				this.setP_y(options.get(n)[1]);
			}
		}
		else {
			this.setTicks_count(this.getTicks_count() + 1);
			if (this.range(pl.getP_x(),pl.getP_y()) < 2) this.combat(pl, board);
		}
		
		if (this.getTicks_count()<this.getVis_time()) {
			this.setTile(this.getVisible());
			board.update(this.getP_x() , this.getP_y(), this.getVisible());
		}
		else {
			this.setTile('.');
			board.update(this.getP_x() , this.getP_y(), '.');
		}
		
	}

	public int getR_range() {
		return r_range;
	}

	public void setR_range(int r_range) {
		this.r_range = r_range;
	}

	public int getR_time() {
		return r_time;
	}

	public void setR_time(int r_time) {
		this.r_time = r_time;
	}

	public int getVis_time() {
		return vis_time;
	}

	public void setVis_time(int vis_time) {
		this.vis_time = vis_time;
	}

	public int getTicks_count() {
		return ticks_count;
	}

	public void setTicks_count(int ticks_count) {
		this.ticks_count = ticks_count;
	}

	public char getVisible() {
		return visible;
	}

	public void setVisible(char visible) {
		this.visible = visible;
	}
	

}
