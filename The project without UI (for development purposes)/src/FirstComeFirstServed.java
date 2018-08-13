import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class FirstComeFirstServed {

	Queue<Process> jobQueue;
	Queue<Process> readyQueue;
	// the devices are list because we have infinitely many IOs
	LinkedList<Process> deviceList;
	int degreeOfMP;
	int contix;

	LinkedList<Process> tempList;

	public FirstComeFirstServed(Queue<Process> jobQueue, Queue<Process> readyQueue, LinkedList<Process> deviceList,
			int degreeOfMP, int contix) {
		super();
		this.jobQueue = jobQueue;
		this.readyQueue = readyQueue;
		this.deviceList = deviceList;
		this.degreeOfMP = degreeOfMP;
		this.contix = contix;

		tempList = new LinkedList<Process>();
		for (Process p : jobQueue) {
			tempList.add(p);
		}
	}

	public void applyFCFS() {
		int currentTime = 0;

		int capicity = degreeOfMP;

		Process runningState = null;

		int totalTimeInTheSystem = 0;
		int totalTimeInTheReadyQueue = 0;
		int timeRunningStateIsNotWorking = 0;
		int numberOfProcessesDoneSoFar = 0;
		int prevTime = 0;

		boolean displayInfo = false;

		Process prevRunningState = null;
		int wait = 0;
		while (true) {
			// System.out.println("At time " + currentTime);
			// System.out.println();
			// printEveryThing(jobQueue, readyQueue, deviceList);

			int temp = 0;
			for (int i = 0; i < capicity; i++) {
				if (jobQueue.size() != 0) {
					// System.out.println(jobQueue.peek().arrivalTime + "::" + currentTime);
					if (jobQueue.peek().arrivalTime <= currentTime) {
						readyQueue.add(jobQueue.poll());
						temp++;
					}
				}
			}
			capicity -= temp;
			for (Process p : jobQueue) {
				p.timeInTheSystem++;
				totalTimeInTheSystem++;
			}
			for (Process p : readyQueue) {
				p.timeInTheSystem++;
				p.timeInTheReadyQueue++;
				totalTimeInTheSystem++;
				totalTimeInTheReadyQueue++;
			}
			for (Process p : deviceList) {
				p.timeInTheSystem++;
				totalTimeInTheSystem++;
			}

			if (runningState != null) {
				runningState.timeInTheSystem++;
				totalTimeInTheSystem++;
			}

			if (runningState == null) {
				if (readyQueue.size() != 0) {
					prevTime = currentTime;
					runningState = readyQueue.poll();
				} else {
					timeRunningStateIsNotWorking++;
					System.out.println(
							"all the processes in the whole SYSTEM are  waiting! Or no processes have arrived yet Or both two");
				}
			}
			if (runningState != null && runningState.getCurrentBurst().timeNeeded == 0) {

				runningState.currentBurst++;
				if (runningState.currentBurst >= runningState.content.length) {
					System.out.print("At time unit : " + currentTime);

					System.out.println("  ||   process (" + runningState.pID + ")" + "  CPU burst done");
					displayInfo = true;
					System.out.println("End of process : " + runningState.pID);
					numberOfProcessesDoneSoFar++;
					capicity++;
					if (readyQueue.size() != 0) {
						prevRunningState = runningState;

						for (int j = 0; j < contix; j++) {

							temp = 0;
							for (int i = 0; i < capicity; i++) {
								if (jobQueue.size() != 0) {
									// System.out.println(jobQueue.peek().arrivalTime + "::" + currentTime);
									if (jobQueue.peek().arrivalTime <= currentTime) {
										readyQueue.add(jobQueue.poll());
										temp++;
									}
								}
							}
							capicity -= temp;

							for (int i = 0; i < deviceList.size(); i++) {
								deviceList.get(i).decrementFCFS();
								if (deviceList.get(i).getCurrentBurst().timeNeeded == 0) {
									System.out.print("At time unit : " + currentTime);
									System.out.println(
											"  ||   process (" + deviceList.get(i).pID + ")" + "  IO burst done");
									deviceList.get(i).currentBurst++;
									deviceList.get(i).arrivalTime = currentTime;
									readyQueue.add(deviceList.get(i));
									deviceList.remove(i);
									i--;
								}
							}
						}

						runningState = readyQueue.poll();

						// prevTime = currentTime;
					} else {
						prevRunningState = runningState;

						runningState = null;
					}

				} else {
					System.out.print("At time unit : " + currentTime);

					System.out.println("  ||   process (" + runningState.pID + ")" + "  CPU burst done");
					if (!runningState.isCPU()) {
						displayInfo = true;
						deviceList.add(runningState);
						if (readyQueue.size() != 0) {
							prevRunningState = runningState;

							runningState = readyQueue.poll();
							// prevTime = currentTime;
						} else {
							prevRunningState = runningState;

							runningState = null;
						}
					}
				}
			}

			if (runningState != null)
				runningState.decrementFCFS();

			currentTime++;

			for (int i = 0; i < deviceList.size(); i++) {
				deviceList.get(i).decrementFCFS();
				if (deviceList.get(i).getCurrentBurst().timeNeeded == 0) {
					System.out.print("At time unit : " + currentTime);
					System.out.println("  ||   process (" + deviceList.get(i).pID + ")" + "  IO burst done");
					deviceList.get(i).currentBurst++;
					deviceList.get(i).arrivalTime = currentTime;
					readyQueue.add(deviceList.get(i));
					deviceList.remove(i);
					i--;
				}
			}

			if (displayInfo) {
				System.out.println((prevTime) + "\t------>\t\t" + (currentTime - 1) + "\t::::\t"
						+ (prevRunningState != null ? "pID(" + prevRunningState.pID + ")" : "No process can run"));
				// System.out.println(
				// "CPU utilization : " + (currentTime - timeRunningStateIsNotWorking) /
				// (currentTime * 1.0));
				// System.out.println("Throughput : " + (1000 * numberOfProcessesDoneSoFar) /
				// (currentTime * 1.0));
				// System.out.println("Turnaround time (So Far): " + totalTimeInTheSystem);
				// System.out.println("Waiting time (So Far): " + totalTimeInTheReadyQueue);
				prevTime = currentTime - 1;
				displayInfo = false;

			}
			if (runningState == null && deviceList.isEmpty() && readyQueue.isEmpty() && jobQueue.isEmpty()) {
				break;
			}

		}
	}

}
