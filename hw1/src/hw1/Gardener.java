package hw1;

public class Gardener extends Person{
	private Grower myGrower;
	private FlowersBouquet flower;
	
	public Gardener(String name, String city,Grower myGrower) {
		super(name,city);
		this.myGrower = myGrower;
		this.flower = null;
	}

	public void pickFlowers(String flowers) {
		/**
		 * @param String flowers: names of the flowers
		 * Gardener prepares the flowers bouquet and returns to the grower
		 */
		this.flower = new FlowersBouquet(flowers);
		System.out.println(this.getName() + " prepares flowers");
		System.out.println(this.getName() + " returns flowers to " + myGrower.getName());
		myGrower.retBouq(this.flower);
	}
}
