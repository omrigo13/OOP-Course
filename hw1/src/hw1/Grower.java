package hw1;

public class Grower extends Person{
	private Wholesaler myWholesaler;
	private Gardener myGardener;
	
	public Grower(String name, String city, Wholesaler myWholesaler) {
		super(name, city);
		this.myWholesaler = myWholesaler;
	}
	
	public Grower(String name, String city) {
		super(name, city);
	}
	
	public void setMyGardener(Gardener myGardener) {
		this.myGardener = myGardener;
	}

	public void passOrder(String flower) {
		/**
		 * @param String flowers: names of the flowers
		 * Grower forward request for flowers bouquet to the gardener
		 */
		System.out.println(this.getName()+" forwards request to " + myGardener.getName());
		myGardener.pickFlowers(flower);
	}
	
	public void retBouq(FlowersBouquet bouq) {
		/**
		 * @param FlowersBouquet bouq: Object of the flowers Bouquet
		 * Grower return flowers Bouquet to the Wholesaler
		 */
		System.out.println(this.getName() + " returns flowers to " + myWholesaler.getName());
		myWholesaler.retBouq(bouq);
	}
}
