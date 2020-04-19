package hw1;

public class FlowersBouquet{
	private String flowersNames;
	private boolean isArranged;

	public FlowersBouquet(String flowersNames) {
		this.flowersNames = flowersNames;
		this.isArranged = false;
	}

	public boolean isArranged() {
		return isArranged;
	}

	public void setIsArranged(boolean isArranged) {
		this.isArranged = isArranged;
	}

	public String getFlowersNames() {
		return flowersNames;
	}
	
}