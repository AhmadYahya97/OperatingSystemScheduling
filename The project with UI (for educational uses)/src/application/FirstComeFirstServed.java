package application;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class FirstComeFirstServed {

	public Queue<Process> jobQueue;
	public LinkedList<Process> initJobQueue;
	public Queue<Process> readyQueue;
	// the devices are list because we have infinitely many IOs
	public LinkedList<Process> deviceList;
	public int degreeOfMP;
	public LinkedList<Process> tempList;
	public int currentTime;

	int contix;
	public int capicity;

	public Process runningState;

	public int totalTimeInTheSystem;
	public int totalTimeInTheReadyQueue;
	public int timeRunningStateIsNotWorking;
	public int numberOfProcessesDoneSoFar;
	public int prevTime;

	public int numberOfProcessesEnterTheReady;
	public int numberOfProcessesEnterTheSystem;

	public boolean displayInfo;

	public boolean displayInfo2;

	public Process prevRunningState;

	public int numberOfChanges;
	boolean flaga = true;

	public FirstComeFirstServed(Queue<Process> jobQueue, Queue<Process> readyQueue, LinkedList<Process> deviceList,
			int degreeOfMP, int contix) {
		super();
		this.contix = contix;
		this.jobQueue = jobQueue;
		this.readyQueue = readyQueue;
		this.deviceList = deviceList;
		this.degreeOfMP = degreeOfMP;

		tempList = new LinkedList<Process>();
		for (Process p : jobQueue) {
			try {
				tempList.add((Process) p.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		initJobQueue = tempList;
		currentTime = 0;

		capicity = degreeOfMP;

		runningState = null;

		totalTimeInTheSystem = 0;
		totalTimeInTheReadyQueue = 0;
		timeRunningStateIsNotWorking = 0;
		numberOfProcessesDoneSoFar = 0;
		prevTime = 0;

		numberOfProcessesEnterTheReady = 0;
		numberOfProcessesEnterTheSystem = 0;

		displayInfo = false;
		displayInfo2 = false;

		prevRunningState = null;

	}

	// public void applyFCFS() {

	// }
	String[][] doFCFS() {

		// while (true) {
		// //System.out.println("At time " + currentTime);
		// //System.out.println();
		// printEveryThing(jobQueue, readyQueue, deviceList);

		// MainSceneController.latch = new CountDownLatch(1);
		// //System.out.println("starting await: "+ currentTime);

		// try {
		// MainSceneController.latch.await();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// //System.out.println("ending await"+ currentTime);
		String[][] status = new String[2][4];
		status[1][0] = "";
		status[1][1] = "";
		status[1][2] = "";
		// System.out.println(jobQueue.size());
		// System.out.println("DOM: " + degreeOfMP);
		// System.out.println("Capacity is : " + capicity);
		int temp = 0;
		System.out.println(capicity);
		for (int i = 0; i < capicity; i++) {
			if (jobQueue.size() != 0) {

				if (jobQueue.peek().arrivalTime <= currentTime) {

					System.out.println(i);
					readyQueue.add(jobQueue.poll());
					temp++;

				}
			}
		}
		numberOfProcessesEnterTheReady += temp;
		numberOfChanges += temp;
		capicity -= temp;

		numberOfProcessesEnterTheSystem = 0;
		for (Process p : jobQueue) {
			if (p.arrivalTime <= currentTime) {
				numberOfProcessesEnterTheSystem++;
				p.timeInTheSystem++;
				totalTimeInTheSystem++;
			}
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
				status[1][0] = "Process(" + runningState.pID + ")" + " has entered running state";
			} else {
				timeRunningStateIsNotWorking++;
				// //System.out.println(
				// "all the processes in the whole SYSTEM are waiting! Or no processes have
				// arrived yet Or both two");
				if (jobQueue.isEmpty()) {
					status[1][0] = "Processes are waiting for I/O";
					// System.out.println("Processes are waiting for I/O");
				} else if (!deviceList.isEmpty()) {
					status[1][0] = "Processes are waiting for I/O, and in Job Queue";
					// System.out.println("Processes are waiting for I/O, and in Job Queue");
				} else {
					status[1][0] = "Waiting for Processes to arrive!";
					// System.out.println("Waiting for Processes to arrive!");
				}
			}
		}
		if (runningState != null && runningState.getCurrentBurst().timeNeeded == 0) {

			runningState.currentBurst++;
			if (runningState.currentBurst >= runningState.content.length) {
				status[1][0] = "Process(" + runningState.pID + ")" + " left running state\n" + status[1][0];

				// System.out.print("At time unit : " + currentTime);

				displayInfo = true;
				displayInfo2 = true;
				status[1][0] = "End of process: " + runningState.pID;
				// System.out.println("End of process: " + runningState.pID);
				numberOfProcessesDoneSoFar++;
				capicity++;
				if (readyQueue.size() != 0) {
					prevRunningState = runningState;
					delayForContix();
					runningState = readyQueue.poll();
					status[1][0] = "Process(" + runningState.pID + ")" + " has entered running state\n" + status[1][0];
					// prevTime = currentTime;
				} else {
					prevRunningState = runningState;

					runningState = null;
				}

			} else {
				status[1][0] = "Process (" + runningState.pID + ")" + "  CPU burst done";
				// System.out.println("Process (" + runningState.pID + ")" + " CPU burst done");
				if (!runningState.isCPU()) {
					status[1][0] = "Process(" + runningState.pID + ")" + " left running state\n" + status[1][0];

					displayInfo = true;
					displayInfo2 = true;
					deviceList.add(runningState);
					if (readyQueue.size() != 0) {
						prevRunningState = runningState;
						delayForContix();
						runningState = readyQueue.poll();
						status[1][0] = "Process(" + runningState.pID + ")" + " has entered running state\n"
								+ status[1][0];
						// prevTime = currentTime;
					} else {
						prevRunningState = runningState;

						runningState = null;
					}
				}
			}
		}

		if (runningState != null) {

			runningState.spentTime++;
			runningState.decrementFCFS();

			// //System.out.println("The Total percentage for process(" + runningState.pID +
			// ") is = "
			// + (runningState.spentTime / (runningState.overallTime * 1.0) * 100) + "%");

			// System.out.println("time worked : " +
			// runningState.getCurrentBurst().timeWorked);
			// System.out.println("time needed : " +
			// runningState.getCurrentBurst().timeNeeded);

			// //System.out.println("The Current burst percentage for process(" +
			// runningState.pID + ") is = "
			// + (runningState.getCurrentBurst().timeWorked
			// / ((runningState.getCurrentBurst().timeNeeded +
			// runningState.getCurrentBurst().timeWorked)
			// * 1.0)
			// * 100)
			// + "%");
		}

		currentTime++;

		for (int i = 0; i < deviceList.size(); i++) {
			deviceList.get(i).decrementFCFS();
			if (deviceList.get(i).getCurrentBurst().timeNeeded == 0) {
				status[1][0] = "Process(" + deviceList.get(i).pID + ")" + "  IO burst done\n" + status[1][0];
				// System.out.println("Process(" + deviceList.get(i).pID + ")" + " IO burst
				// done\n" + status[1][0]);
				deviceList.get(i).currentBurst++;
				deviceList.get(i).arrivalTime = currentTime;
				readyQueue.add(deviceList.get(i));
				deviceList.remove(i);
				i--;
			}
		}

		if (displayInfo) {/// changes
			/*
			 * status[1][0] = prevTime + "\t------>\t\t" + (currentTime - 1) + "\t::::\t" +
			 * (prevRunningState != null ? "pID(" + prevRunningState.pID + ")" :
			 * "No process can run") + "\n" + status[1][0];
			 */
			status[1][3] = prevTime + "," + (currentTime - 1) + "," + prevRunningState.pID;
			// //System.out.println(prevTime + "\t------>\t\t" + (currentTime - 1) +
			// "\t::::\t"
			// + (prevRunningState != null ? "pID(" + prevRunningState.pID + ")" : "No
			// process can run") + "\n"
			// + status[1][0]);

			// status[1][2] = prevTime + "\t------>\t\t" + (currentTime - 1) + "\t::::\t"
			// + (prevRunningState != null ? "pID(" + prevRunningState.pID + ")" : "No
			// process can run");

			// //System.out.println(
			// "CPU utilization : " + (currentTime - timeRunningStateIsNotWorking) /
			// (currentTime * 1.0));
			// //System.out.println("Throughput : " + (1000 * numberOfProcessesDoneSoFar) /
			// (currentTime * 1.0));
			// //System.out.println("Turnaround time (So Far): " + totalTimeInTheSystem);
			// //System.out.println("Waiting time (So Far): " + totalTimeInTheReadyQueue);
			prevTime = currentTime - 1;
			displayInfo = false;

		}
		status[0][0] = (currentTime - timeRunningStateIsNotWorking) / (currentTime * 1.0) + ""; // cpu utilization
		// System.out.println((currentTime - timeRunningStateIsNotWorking) /
		// (currentTime * 1.0) + ""); // cpu utilization
		// System.out.println("" + (1000 * numberOfProcessesDoneSoFar) / (currentTime *
		// 1.0)); // throughput
		status[0][1] = "" + (1000 * numberOfProcessesDoneSoFar) / (currentTime * 1.0); // throughput
		// System.out.println("" + totalTimeInTheSystem); // turnarround
		status[0][2] = ""
				+ totalTimeInTheSystem / ((numberOfProcessesEnterTheSystem + numberOfProcessesEnterTheReady) * 1.0); // turnarround
		// System.out.println(totalTimeInTheReadyQueue + ""); // waiting
		status[0][3] = totalTimeInTheReadyQueue / (numberOfProcessesEnterTheReady * 1.0) + ""; // waiting
		if (runningState == null && deviceList.isEmpty() && readyQueue.isEmpty() && jobQueue.isEmpty()) {
			// System.out.println("here is null");
			status[1][1] = null; // ?
		}
		return status;
	}
	// }

	public void delayForContix() {
		timeRunningStateIsNotWorking += contix;

		for (int j = 0; j < contix; j++) {
			currentTime++;
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
			numberOfChanges += temp;
			numberOfProcessesEnterTheReady += temp;
			capicity -= temp;

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
		}

	}
}
