package application;

import com.jfoenix.controls.*;

public class listItems {
	public JFXProgressBar prog;
	public Process proc;
	
	public listItems(Process proc,JFXProgressBar prog) {
		this.prog = prog;
		this.proc = proc;
		prog.setProgress(proc.getCurrentBurst().timeWorked/proc.getCurrentBurst().timeNeeded);
	}
}
