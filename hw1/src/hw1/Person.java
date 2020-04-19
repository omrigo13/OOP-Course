package hw1;

public class Person {
	private String name;
	private String city;

	public Person(String name, String city) {
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public void orderFlowers(String flowers, Florist fred, Person client) {
		/**
		 * @param String flowers: names of the flowers
		 * @param Florist fred: Object of the flowers shop
		 * @param Person client: Object of person that should get the order
		 * Client orders flowers From the flowers shop
		 */
		System.out.println(this.name + " orders flowers from Fred: "+flowers);
		fred.passOrder(flowers, client);
	}
	
	public void deliverBouq(FlowersBouquet bouq, Person client) {
		/**
		 * @param FlowersBouquet bouq: Object of the flowers Bouquet
		 * client gives feedback for getting the flowers bouquet
		 */
		System.out.println(client.getName()+ " accepts the flowers: " +bouq.getFlowersNames());
	}
}
