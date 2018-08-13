package application;

import java.util.LinkedList;
import java.util.Queue;

public class PickAlgo {
	FirstComeFirstServed obj0;
	ShortestJobFirst obj1;
	RoundRobin obj2;
	ExponentialAveraging obj3;
	Priority obj4;
	ShortestRemainingTimeFirst obj5;

	public PickAlgo(int algorithmType, Queue<Process> jobQueue, int degreeOfMP, double alpha, int contix) {
		if (algorithmType == 0)
			obj0 = new FirstComeFirstServed(jobQueue, new LinkedList<Process>(), new LinkedList<Process>(), degreeOfMP,
					contix);
		else if (algorithmType == 1)
			obj1 = new ShortestJobFirst(jobQueue, new LinkedList<Process>(), new LinkedList<Process>(), degreeOfMP,
					contix);
		else if (algorithmType == 2)
			obj2 = new RoundRobin(jobQueue, new LinkedList<Process>(), new LinkedList<Process>(), degreeOfMP, contix);
		else if (algorithmType == 3)
			obj3 = new ExponentialAveraging(jobQueue, new LinkedList<Process>(), new LinkedList<Process>(), degreeOfMP,
					alpha, contix);
		else if (algorithmType == 4)
			obj4 = new Priority(jobQueue, new LinkedList<Process>(), new LinkedList<Process>(), degreeOfMP, contix);
		else if (algorithmType == 5)
			obj5 = new ShortestRemainingTimeFirst(jobQueue, new LinkedList<Process>(), new LinkedList<Process>(),
					degreeOfMP, contix);
		else
			System.out.println("Out of bounds");
	}

	public LinkedList<Process> getInitialJobQueue(int algorithmType) {
		if (algorithmType == 0)
			return obj0.initJobQueue;
		else if (algorithmType == 1)
			return obj1.initJobQueue;
		else if (algorithmType == 2)
			return obj2.initJobQueue;
		else if (algorithmType == 3)
			return obj3.initJobQueue;
		else if (algorithmType == 4)
			return obj4.initJobQueue;
		else if (algorithmType == 5)
			return obj5.initJobQueue;
		else
			return null;
	}

	public Queue<Process> getJobQueue(int algorithmType) {
		if (algorithmType == 0)
			return obj0.jobQueue;
		else if (algorithmType == 1)
			return obj1.jobQueue;
		else if (algorithmType == 2)
			return obj2.jobQueue;
		else if (algorithmType == 3)
			return obj3.jobQueue;
		else if (algorithmType == 4)
			return obj4.jobQueue;
		else if (algorithmType == 5)
			return obj5.jobQueue;
		else
			return null;
	}

	public LinkedList<Process> getDeviceList(int algorithmType) {
		if (algorithmType == 0)
			return obj0.deviceList;
		else if (algorithmType == 1)
			return obj1.deviceList;
		else if (algorithmType == 2)
			return obj2.deviceList;
		else if (algorithmType == 3)
			return obj3.deviceList;
		else if (algorithmType == 4)
			return obj4.deviceList;
		else if (algorithmType == 5)
			return obj5.deviceList;
		else {
			System.out.println("Out of bounds");
			return null;
		}
	}

	public Queue<Process> getReadyQueue(int algorithmType) {
		if (algorithmType == 0)
			return obj0.readyQueue;
		else if (algorithmType == 1)
			return obj1.readyQueue;
		else if (algorithmType == 2)
			return obj2.readyQueue;
		else if (algorithmType == 3)
			return obj3.readyQueue;
		else if (algorithmType == 4)
			return obj4.readyQueue;
		else if (algorithmType == 5)
			return obj5.readyQueue;
		else {
			System.out.println("Out of bounds");
			return null;
		}
	}

	public Process getRunningState(int algorithmType) {
		if (algorithmType == 0)
			return obj0.runningState;
		else if (algorithmType == 1)
			return obj1.runningState;
		else if (algorithmType == 2)
			return obj2.runningState;
		else if (algorithmType == 3)
			return obj3.runningState;
		else if (algorithmType == 4)
			return obj4.runningState;
		else if (algorithmType == 5)
			return obj5.runningState;
		else {
			System.out.println("Out of bounds");
			return null;
		}
	}

	public int getCurrentTime(int algorithmType) {
		if (algorithmType == 0)
			return obj0.currentTime;
		else if (algorithmType == 1)
			return obj1.currentTime;
		else if (algorithmType == 2)
			return obj2.currentTime;
		else if (algorithmType == 3)
			return obj3.currentTime;
		else if (algorithmType == 4)
			return obj4.currentTime;
		else if (algorithmType == 5)
			return obj5.currentTime;
		else {
			System.out.println("Out of bounds");
			return -1;
		}
	}

	public int getnumberOfChanges(int algorithmType) {
		if (algorithmType == 0)
			return obj0.numberOfChanges;
		else if (algorithmType == 1)
			return obj1.numberOfChanges;
		else if (algorithmType == 2)
			return obj2.numberOfChanges;
		else if (algorithmType == 3)
			return obj3.numberOfChanges;
		else if (algorithmType == 4)
			return obj4.numberOfChanges;
		else if (algorithmType == 5)
			return obj5.numberOfChanges;
		else {
			System.out.println("Out of bounds");
			return -1;
		}
	}

	public String[][] performAnIteration(int algorithmType, int timeQuantum) {
		if (algorithmType == 0)
			return obj0.doFCFS();
		else if (algorithmType == 1)
			return obj1.iterateSJF();
		else if (algorithmType == 2)
			return obj2.iterateRR(timeQuantum);
		else if (algorithmType == 3)
			return obj3.iterateExpAverage();
		else if (algorithmType == 4)
			return obj4.iteratePriority();
		else if (algorithmType == 5)
			return obj5.iterateSRJF();
		else {
			System.out.println("Out of bounds");
			return null;
		}
	}

	public void setDisplayInfo2(boolean b) {
		if (obj0 != null)
			obj0.displayInfo2 = b;
		else if (obj1 != null)
			obj1.displayInfo2 = b;
		else if (obj2 != null)
			obj2.displayInfo2 = b;
		else if (obj3 != null)
			obj3.displayInfo2 = b;
		else if (obj4 != null)
			obj4.displayInfo2 = b;
		else if (obj5 != null)
			obj5.displayInfo2 = b;
	}

	public boolean getDisplayInfo2() {
		if (obj0 != null)
			return obj0.displayInfo2;
		else if (obj1 != null)
			return obj1.displayInfo2;
		else if (obj2 != null)
			return obj2.displayInfo2;
		else if (obj3 != null)
			return obj3.displayInfo2;
		else if (obj4 != null)
			return obj4.displayInfo2;
		else if (obj5 != null)
			return obj5.displayInfo2;
		else
			return false;
	}

}