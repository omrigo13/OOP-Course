package hw1;

public class FlowerArranger extends Person{
	private Florist myFlorist;

	public FlowerArranger(String name, String city, Florist myFlorist) {
		super(name,city);
		this.myFlorist = myFlorist;
	}
	
	public void retBouq(FlowersBouquet bouq) {
		/**
		 * @param FlowersBouquet bouq: Object of the flowers Bouquet
		 * Flower Arranger arranges flowers and return the arranged flowers to the florist
		 */
		bouq.setIsArranged(true);
		System.out.println(this.getName() + " arranges flowers");
		System.out.println(this.getName() + " returns arranged flowers to " + myFlorist.getName());
		myFlorist.deliverBouq(bouq);
	}
}
