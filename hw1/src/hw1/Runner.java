package hw1;

public class Runner {
	public static void main(String[] args) {
		Person chris = new Person("Chris", "Tel Aviv");
		Florist fred = new Florist("Robin's florist", "Haifa");
		Wholesaler w = new Wholesaler("Wholesaler", "Tel Aviv", fred);
		Grower grow = new Grower("Grower", "Tel Aviv", w);
		Gardener garden = new Gardener("Gardener", "Tel Aviv", grow);
		FlowerArranger f = new FlowerArranger("Flower Arranger", "Tel Aviv", fred);
		Person robin = new Person("Robin", "Tel Aviv");
		DeliveryPerson d = new DeliveryPerson("Delivery Person", "Tel Aviv", robin);
		fred.setMyDeliveryPerson(d);
		fred.setMyFlowerArranger(f);
		fred.setMyWholesaler(w);
		w.setMyGrower(grow);
		grow.setMyGardener(garden);
		chris.orderFlowers("Roses, Violets, Gladiolus", fred, robin);
	}
}