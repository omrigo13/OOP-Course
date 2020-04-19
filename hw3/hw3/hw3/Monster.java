package hw3;

import java.util.*;

public class Monster extends Enemy {
	private int vision_range;

	public Monster(String name, int health_pool, int curr_healt, int attack_pt, int defence_pt, int x, int y,
			int exp_val, char tile,int vision_range) {
		super(name, health_pool, curr_healt, attack_pt, defence_pt, x, y, exp_val, tile);
		this.vision_range = vision_range;
	}

	public int getVision_range() {
		return vision_range;
	}

	public void setVision_range(int vision_range) {
		this.vision_range = vision_range;
	}
	
	@Override
	public void enemyTurn(Player pl, Board board, ArrayList<Integer> random_numbers) {
		if(this.range(pl.getP_x(),pl.getP_y())<this.getVision_range())
		{
			int dx=this.getP_x()-pl.getP_x();
			int dy=this.getP_y()-pl.getP_y();
			if (Math.abs(dx)>Math.abs(dy))
			{
				if (dx > 0) {
					if(check(this.getP_x(), this.getP_y() - 1, board) == '.')
						this.moveLeft(board);
					if(check(this.getP_x(), this.getP_y() - 1, board) == '@')
						this.combat(pl, board);
				}
				else {
					if(check(this.getP_x(), this.getP_y() + 1, board) == '.')
						this.moveRight(board);
					if(check(this.getP_x(), this.getP_y() - 1, board) == '@')
						this.combat(pl, board);
				}
			}
			else
			{
				if (dy > 0) {
					if(check(this.getP_x() - 1, this.getP_y(), board) == '.')
						this.moveUp(board);
					if(check(this.getP_x() - 1, this.getP_y(), board) == '@')
						this.combat(pl, board);
				}
				else {
					if(check(this.getP_x() + 1, this.getP_y(), board) == '.')
						this.moveDown(board);
					if(check(this.getP_x() + 1, this.getP_y(), board) == '@')
						this.combat(pl, board);
				}
			}
		}
		else
		{
			RandomGenerator rand = new RandomGeneratorImpl();
			int random;
			if(random_numbers == null || random_numbers.size() == 0)
				random = rand.nextInt(5);
			else
				random = random_numbers.remove(0);
			if(random < 5) {
				switch(random) {
					case 0:
						if(board.getBoard()[this.getP_x()][this.getP_y()-1]!='#')
							if(this.check(this.getP_x(), this.getP_y()-1, board)=='.')
								this.moveLeft(board);
							else if(this.check(this.getP_x(), this.getP_y()-1, board)=='@')
								this.combat(pl, board);
						break;
					case 1:
						if(board.getBoard()[this.getP_x()][this.getP_y()+1]!='#')
							if(this.check(this.getP_x(), this.getP_y()+1, board)=='.')
								this.moveRight(board);
							else if(this.check(this.getP_x(), this.getP_y()+1, board)=='@')
								this.combat(pl, board);
						break;
					case 2:
						if(board.getBoard()[this.getP_x()-1][this.getP_y()]!='#')
							if(this.check(this.getP_x()-1, this.getP_y(), board)=='.')
								this.moveUp(board);
							else if(this.check(this.getP_x()-1, this.getP_y(), board)=='@')
								this.combat(pl, board);
						break;
					case 3:
						if(board.getBoard()[this.getP_x()+1][this.getP_y()]!='#')
							if(this.check(this.getP_x()+1, this.getP_y(), board)=='.')
								this.moveDown(board);
							else if(this.check(this.getP_x()+1, this.getP_y(), board)=='@')
								this.combat(pl, board);
						break;
					case 4:
						break;
				}
			}
		}
	}
	
	
	public char check(int x, int y,Board board)
	{
		for (int i=0;i<board.getEnemies().size();i++)
		{
			if (x==board.getEnemies().get(i).getP_x() && board.getEnemies().get(i).getP_y()==y)
				return 'X';
		}
		if (board.getBoard()[x][y]=='@')
				return '@';
		return '.';
				
	}
	public void moveLeft(Board board)
	{
		if(board.getBoard()[this.getP_x()][this.getP_y()-1] != '#') {
			board.update(this.getP_x(), this.getP_y()-1, this.getTile());
			board.update(this.getP_x(), this.getP_y(), '.');
			this.setP_y(this.getP_y()-1);
		}
	}
	
	public void moveRight(Board board)
	{
		if(board.getBoard()[this.getP_x()][this.getP_y()+1] != '#') {
			board.update(this.getP_x(), this.getP_y()+1,  this.getTile());
			board.update(this.getP_x(), this.getP_y(), '.');
			this.setP_y(this.getP_y()+1);
		}
	}
	
	public void moveUp(Board board)
	{
		if(board.getBoard()[this.getP_x()-1][this.getP_y()] != '#') {
			board.update(this.getP_x()-1, this.getP_y(),  this.getTile());
			board.update(this.getP_x(), this.getP_y(), '.');
			this.setP_x(this.getP_x()-1);
		}
	}
	
	public void moveDown(Board board)
	{
		if(board.getBoard()[this.getP_x()+1][this.getP_y()] != '#') {
			board.update(this.getP_x()+1, this.getP_y(),  this.getTile());
			board.update(this.getP_x(), this.getP_y(), '.');
			this.setP_x(this.getP_x()+1);
		}
	}


}