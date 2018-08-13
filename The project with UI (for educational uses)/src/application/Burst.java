package application;


public class Burst implements Cloneable {

	boolean type;
	int timeNeeded;
	int timeWorked;

	public Burst(boolean type, int timeNeeded, int timeWorked) {
		super();
		this.type = type;
		this.timeNeeded = timeNeeded;
		this.timeWorked = timeWorked;
	}

	public void applyWork(int timeUnit) {
		timeNeeded -= timeUnit;
		timeWorked += timeUnit;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
