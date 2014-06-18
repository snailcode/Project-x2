package model;

public class Hollow {
	private int numberOfSeeds;
	private boolean kallah;
	
	public Hollow(int seeds, boolean kallah) {
		this.kallah = kallah;
		this.numberOfSeeds = seeds;
	}
	
	public void addSeed(int num) {
		this.numberOfSeeds += num;
	}
	
	public void removeSeeds() {
		this.numberOfSeeds = 0;
	}
	
	public boolean isKallah() {
		return this.kallah;
	}
	
	public int getNumberOfSeeds() {
		return this.numberOfSeeds;
	}
}
