package application;

import java.util.Comparator;

public class SortByShortestCPUBurst implements Comparator<Object> {

	public int compare(Object o1, Object o2) {

		Process p1 = (Process) o1;
		Process p2 = (Process) o2;
		return Integer.compare(p1.getTimeOfCurrentCPUBurst(), p2.getTimeOfCurrentCPUBurst());
	}

}
