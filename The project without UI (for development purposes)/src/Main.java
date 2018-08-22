import java.util.*;
import java.util.concurrent.ThreadLocalRandom;



public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the context switch");

		int context = scan.nextInt();

		System.out.println("enter the # of processes: ");

		// reading the total number of processes
		int numberOfTotalProcesses = scan.nextInt();

		System.out.println("enter the degree of MP: ");
		int degreeOfMP = scan.nextInt();

		System.out.println("enter the number of bursts in each process: ");
		int numberOfBursts = scan.nextInt();

		System.out.println("enter the range of CPU bursts");
		System.out.println("Min: ");
		int minCPU = scan.nextInt();
		System.out.println("Max: ");
		int maxCPU = scan.nextInt();

		System.out.println("enter the range of IO bursts");
		System.out.println("Min: ");
		int minIO = scan.nextInt();
		System.out.println("Max: ");
		int maxIO = scan.nextInt();

		System.out.println("enter the range of priority");
		System.out.println("Min: ");
		int minPriority = scan.nextInt();
		System.out.println("Max: ");
		int maxPriority = scan.nextInt();

		System.out.println("enter the range of initial arrival times");
		System.out.println("Min: ");
		int minArrival = scan.nextInt();
		System.out.println("Max: ");
		int maxArrival = scan.nextInt();

		System.out.println("enter initial Tau: ");
		int initialTau = scan.nextInt();

		System.out.println("enter alpha: ");
		double alpha = scan.nextDouble();

		System.out.println("enter your type of generating random number of CPU/IO bursts");
		System.out.println("(0) for gaussian / (other) for binomial: ");
		int type = scan.nextInt();

		boolean randomType = type == 0 ? true : false;

		// declaring the jobQueue
		Queue<Process> jobQueue = new PriorityQueue<Process>(numberOfTotalProcesses, new SortByArraivalTime());

		// generating the processes
		generateJobs(jobQueue, numberOfTotalProcesses, randomType, numberOfBursts, minCPU, maxCPU, minIO, maxIO,
				minPriority, maxPriority, minArrival, maxArrival, initialTau);

		// ------------Cloning 6 instances of thejobQueue------------
		Queue<Process> jobQueueInst1 = new PriorityQueue<Process>(numberOfTotalProcesses, new SortByArraivalTime());

		for (Process p : jobQueue) {
			jobQueueInst1.add((Process) p.clone());
		}

		Queue<Process> jobQueueInst2 = new PriorityQueue<Process>(numberOfTotalProcesses, new SortByArraivalTime());

		for (Process p : jobQueue) {
			jobQueueInst2.add((Process) p.clone());
		}

		Queue<Process> jobQueueInst3 = new PriorityQueue<Process>(numberOfTotalProcesses, new SortByArraivalTime());

		for (Process p : jobQueue) {
			jobQueueInst3.add((Process) p.clone());
		}

		Queue<Process> jobQueueInst4 = new PriorityQueue<Process>(numberOfTotalProcesses, new SortByArraivalTime());

		for (Process p : jobQueue) {
			jobQueueInst4.add((Process) p.clone());
		}

		Queue<Process> jobQueueInst5 = new PriorityQueue<Process>(numberOfTotalProcesses, new SortByArraivalTime());

		for (Process p : jobQueue) {
			jobQueueInst5.add((Process) p.clone());
		}

		Queue<Process> jobQueueInst6 = new PriorityQueue<Process>(numberOfTotalProcesses, new SortByArraivalTime());

		for (Process p : jobQueue) {
			jobQueueInst6.add((Process) p.clone());
		}
		// --------------------------------------------

		// ------------Creating 6 different objects for each algorithm!------------

		// FCFS object
		FirstComeFirstServed FCFSObject = new FirstComeFirstServed(jobQueueInst1, new LinkedList<Process>(),
				new LinkedList<Process>(), degreeOfMP,context);

		ShortestJobFirst SJFObject = new ShortestJobFirst(jobQueueInst2,
				new PriorityQueue<Process>(numberOfTotalProcesses, new SortByShortestCPUBurst()),
				new LinkedList<Process>(), degreeOfMP);

		ShortestRemainingTimeFirst SRJFObject = new ShortestRemainingTimeFirst(jobQueueInst3,
				new PriorityQueue<Process>(numberOfTotalProcesses, new SortByShortestCPUBurst()),
				new LinkedList<Process>(), degreeOfMP);

		Priority priority = new Priority(jobQueueInst4,
				new PriorityQueue<Process>(numberOfTotalProcesses, new SortByPriority()), new LinkedList<Process>(),
				degreeOfMP);

		RoundRobin RR = new RoundRobin(jobQueueInst5,
				new PriorityQueue<Process>(numberOfTotalProcesses, new SortByArraivalTime()), new LinkedList<Process>(),
				degreeOfMP);

		ExponentialAveraging expAveraging = new ExponentialAveraging(jobQueueInst6,
				new PriorityQueue<Process>(numberOfTotalProcesses, new SortByExpAveraging()), new LinkedList<Process>(),
				degreeOfMP, alpha, context);
		// ... (here goes the other 4 algorithms.)
		// --------------------------------------------

		System.out.println("Your processes status are like this before performing any algorithm : ");
		System.out.println("________________");
		while (!jobQueue.isEmpty()) {
			Process p = jobQueue.poll();
			System.out.println("pID : " + p.pID + "\t\tArrival Time : " + p.arrivalTime + "\tPriority : " + p.priority
					+ "\tBursts : " + returnBursts(p));
		}

		System.out.println("Press enter to perform expAveraging: ");
		scan.nextLine();
		scan.nextLine();
		// FCFSObject.applyFCFS();
		expAveraging.applyExpAverage();

	}

	public static String returnBursts(Process p) {

		String str = "";
		for (int i = 0; i < p.content.length; i++) {
			if (p.content[i].type) {
				str += "CPU(" + p.content[i].timeNeeded + ")";
			} else {
				str += "IO(" + p.content[i].timeNeeded + ")";

			}
			if (i != p.content.length - 1) {
				str += ", ";
			}
		}
		return str;

	}

	public static void generateJobs(Queue<Process> jobQueue, int numberOfTotalProcesses, boolean typeOfRandomGenerator,
			int numberOfBursts, int minCPU, int maxCPU, int minIO, int maxIO, int minPriority, int maxPriority,
			int minArrival, int maxArrival, int initialTau) {

		for (int i = 0; i < numberOfTotalProcesses; i++) {
			int numberOfCpuBursts;
			if (typeOfRandomGenerator)
				numberOfCpuBursts = randomGaussianInt(0.75 * numberOfBursts, numberOfBursts / 2, numberOfBursts);
			else {
				numberOfCpuBursts = randomBinomialInt(0.75 * numberOfBursts, numberOfBursts / 2, numberOfBursts);
			}

			int numberOfIoBursts = numberOfBursts - numberOfCpuBursts;

			Burst[] content = new Burst[numberOfBursts];

			content[0] = new Burst(true, ThreadLocalRandom.current().nextInt(minCPU, maxCPU + 1), 0);
			content[numberOfBursts - 1] = new Burst(true, ThreadLocalRandom.current().nextInt(minCPU, maxCPU + 1), 0);
			numberOfCpuBursts -= 2;
			for (int j = 1; j < numberOfBursts - 1; j++) {
				if (content[j - 1].type) {
					if (numberOfCpuBursts + 1 > numberOfIoBursts) {
						if (numberOfCpuBursts != 0
								&& (ThreadLocalRandom.current().nextInt(0, 2) == 0 || numberOfIoBursts == 0)) {
							content[j] = new Burst(true, ThreadLocalRandom.current().nextInt(minCPU, maxCPU + 1), 0);
							numberOfCpuBursts--;
						} else {
							content[j] = new Burst(false, ThreadLocalRandom.current().nextInt(minIO, maxIO + 1), 0);
							numberOfIoBursts--;
						}
					} else {
						content[j] = new Burst(false, ThreadLocalRandom.current().nextInt(minIO, maxIO + 1), 0);
						numberOfIoBursts--;
					}
				} else {
					content[j] = new Burst(true, ThreadLocalRandom.current().nextInt(minCPU, maxCPU + 1), 0);
					numberOfCpuBursts--;
				}

			}
			jobQueue.add(new Process(ThreadLocalRandom.current().nextInt(minPriority, maxPriority + 1),
					ThreadLocalRandom.current().nextInt(minArrival, maxArrival + 1), content, initialTau));
		}

	}

	public static int randomBinomialInt(double mean, int min, int max) {
		if (max < min || mean < min || mean > max) {
			throw new IllegalArgumentException();
		}
		int n = max - min;
		double p = ((double) (mean - min)) / n;
		int val = min;
		for (int i = 0; i < n; i++) {
			if (Math.random() <= p) {
				val++;
			}
		}
		return val;
	}

	public static int randomGaussianInt(double mean, int min, int max) {
		if (max < min || mean < min || mean > max) {
			throw new IllegalArgumentException();
		}
		int n = max - min;
		double p = ((double) (mean - min)) / n;
		double sd = n * p * (1 - p); // standard deviation
		int val = (int) (new Random().nextGaussian() * sd + mean + .5);
		// truncate if outside desired range:
		if (val < min) {
			val = min;
		} else if (val > max) {
			val = max;
		}
		return val;
	}
}
