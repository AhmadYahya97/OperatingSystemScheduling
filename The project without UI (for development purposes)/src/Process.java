import java.util.ArrayList;

public class Process implements Cloneable {

	int pID;
	static int counter = 0;
	int priority;
	int arrivalTime;
	Burst[] content;
	int currentBurst;
	int timeInTheSystem;
	int timeInTheReadyQueue;
	int timeOfCurrentCPUBurst;
	int temp;
	double tau;
	double alpha;
	int holder;

	public Process(int priority, int arrivalTime, Burst[] content, int tau) {
		this.priority = priority;
		this.arrivalTime = arrivalTime;
		this.content = content;
		pID = counter++;
		this.tau = tau;
	}

	public Burst getCurrentBurst() {
		return content[currentBurst];
	}

	public void decrementFCFS() {
		content[currentBurst].applyWork(1);

	}

	public void decrement() {
		// while (currentBurst != content.length && content[currentBurst].type) {
		if (content[currentBurst].timeNeeded != 0 && content[currentBurst].type) {
			content[currentBurst].applyWork(1);
			if (content[currentBurst].timeNeeded == 0) {
				currentBurst++;
				if (currentBurst < content.length && !content[currentBurst].type) {
					updateTau();
				}
			}
		}
		// }

	}

	public boolean isCPU() {
		if (currentBurst != content.length)
			return content[currentBurst].type;
		else
			return false;

	}

	public int getTimeOfCurrentCPUBurst() {
		System.out.println("Accessed ! " + pID);

		// check it later for changes

		int sum = 0;
		temp = currentBurst;
		System.out.println("temp calculated");
		while (temp < content.length && content[temp].type) {
			sum += content[temp].timeNeeded;
			temp++;
		}
		System.out.println(pID);
		System.out.println("sum = " + sum);
		holder = sum;
		return sum;
	}

	public double getExpAveraging() {
		System.out.println("Accessed !(exp) " + pID);

		return tau * alpha + (1 - alpha) * getTimeOfCurrentCPUBurst();
	}

	public void updateTau() {
		System.out.println("holder : " + holder);
		tau = tau * alpha + (1 - alpha) * holder;
		System.out.println("this is tau wowah :: " + pID + "::::" + tau);
	}

	public void incrementBurst() {
		currentBurst = temp;
	}

	public String toString() {
		return arrivalTime + " :: " + arrivalTime;
	}

	protected Object clone() throws CloneNotSupportedException {

		Process var = (Process) super.clone();

		var.content = new Burst[content.length];

		for (int i = 0; i < content.length; i++) {
			var.content[i] = (Burst) content[i].clone();
		}

		return var;
	}
}