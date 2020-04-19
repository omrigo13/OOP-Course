package hw1;

public class Wholesaler extends Person{
	private Florist myFlorist;
	private Grower myGrower;
	
	public Wholesaler(String name, String city, Florist myFlorist) {
		super(name, city);
		this.myFlorist = myFlorist;
	}
	
	public Wholesaler(String name, String city) {
		super(name, city);
	}
	
	public void setMyGrower(Grower myGrower) {
		this.myGrower = myGrower;
	}


	public void passOrder(String flowers) {
		/**
		 * @param String flowers: names of the flowers
		 * Wholesaler forward request for flowers bouquet to the grower
		 */
		System.out.println(this.getName() + " forwards request to " + myGrower.getName());
		myGrower.passOrder(flowers);
	}
	
	public void retBouq(FlowersBouquet bouq) {
		/**
		 * @param FlowersBouquet bouq: Object of the flowers Bouquet
		 * Wholesaler return flowers Bouquet to the florist
		 */
		System.out.println(this.getName() + " returns flowers to " + myFlorist.getName());
		myFlorist.retBouq(bouq);
	}
}
