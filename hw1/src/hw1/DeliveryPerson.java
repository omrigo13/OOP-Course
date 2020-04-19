package hw1;

public class DeliveryPerson extends Person{
	private Person myPerson;

	public DeliveryPerson(String name, String city, Person myPerson) {
		super(name, city);
		this.myPerson = myPerson;
	}
	
	public void deliverBouq(FlowersBouquet bouq, Person client) {
		/**
		 * @param FlowersBouquet bouq: Object of the flowers Bouquet
		 * delivery person delivers the flowers bouquet to the client
		 */
		System.out.println(this.getName() + " delivers flowers " + client.getName());
		myPerson.deliverBouq(bouq, client);
	}
}