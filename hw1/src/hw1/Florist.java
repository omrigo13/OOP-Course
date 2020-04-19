package hw1;

public class Florist extends Person{
	private FlowerArranger myFlowerArranger;
	private DeliveryPerson myDeliveryPerson;
	private Wholesaler myWholesaler;
	private Person client;
	
	public Florist(String name, String city) {
		super(name,city);
		this.myFlowerArranger = null;
		this.myDeliveryPerson = null;
		this.myWholesaler = null;
		this.client = null;
		
	}
	
	public void setMyFlowerArranger(FlowerArranger myFlowerArranger) {
		this.myFlowerArranger = myFlowerArranger;
	}

	public void setMyDeliveryPerson(DeliveryPerson myDeliveryPerson) {
		this.myDeliveryPerson = myDeliveryPerson;
	}

	public void setMyWholesaler(Wholesaler myWholesaler) {
		this.myWholesaler = myWholesaler;
	}

	public void passOrder(String flowers, Person client){
		/**
		 * @param String flowers: names of the flowers
		 * @param Person client: Object of person that should get the order
		 * Florist forward the flowers request to the wholesaler
		 */
		this.client = client;
		if(this.getCity() == client.getCity()) {
			System.out.println("Fred forwards request to " + myWholesaler.getName());
			myWholesaler.passOrder(flowers);
		}
		else {
			System.out.println("Fred forwards order to " + this.getName());
			System.out.println(this.getName() + " forwards request to " + myWholesaler.getName());
			myWholesaler.passOrder(flowers);
		}
	}
	
	public void retBouq(FlowersBouquet bouq) {
		/**
		 * @param FlowersBouquet bouq: Object of the flowers Bouquet
		 * Florist request flowers arrangement from the flower arranger
		 */
		System.out.println(this.getName() + " request flowers arrangement from " + myFlowerArranger.getName());
		myFlowerArranger.retBouq(bouq);
	}
	
	public void deliverBouq(FlowersBouquet bouq) {
		/**
		 * @param FlowersBouquet bouq: Object of the flowers Bouquet
		 * Florist request delivery to the client from the Delivery Person
		 */
		System.out.println(this.getName() + " forwards flowers to " + myDeliveryPerson.getName());
		myDeliveryPerson.deliverBouq(bouq,client);
	}

	public Person getClient() {
		return client;
	}
}
