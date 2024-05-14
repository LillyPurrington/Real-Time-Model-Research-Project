package algorithms;

import java.util.ArrayList;

import model.Task;

/**
 * This is the generic algorithm class. I only have the earliest deadline first algorithm written, but different algorithms could be used.
 * @author Lilly Purrington
 */
public abstract class Algorithm {
	
	protected int timeOutTimeSeconds;
	
	public Algorithm(int timeOutTimeSeconds) {
		this.timeOutTimeSeconds = timeOutTimeSeconds;
	}

	public abstract int solve(ArrayList<Task> schedule);


}
