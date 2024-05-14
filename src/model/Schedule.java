package model;

import java.util.ArrayList;

import algorithms.Algorithm;
import algorithms.EarliestDeadlineFirst;
import io.Output;

public class Schedule {
	
	public static final int UNSOLVED = 0;
	public static final int TIMED_OUT = -1;
	public static final int UNSOLVABLE = -2;
	public static final int SOLVED = 1;
	
	private boolean infinite = false;
	private ArrayList<Task> schedule = new ArrayList<Task>();
	
	private int state = UNSOLVED;
	
	public void addTask(Task newTask) {
		schedule.add(newTask);
		if (newTask.isInfinite()) {
			infinite = true;
		}
	}
	
	public void solve(Algorithm algorithm) {
		int hyperperiod = 0;
		for (Task currentTask : schedule) {
				if (currentTask.getPeriod() > hyperperiod) {
					hyperperiod = currentTask.getPeriod();
				}
		}
		
		for (Task currentTask : schedule) {
			if (currentTask.isInfinite()) {
				currentTask.setInifiniteJobs(hyperperiod);
			}
		}
		
		state = algorithm.solve(schedule);
	}

	
	//Needs to be cleaned up
	public void display() {	
		if (state == UNSOLVED) {
			Output.textDisplay("This schedule has not been solved.");
		}else if (state == TIMED_OUT) {
			Output.textDisplay("The time limit set was reached.");
		}else if (state == UNSOLVABLE) {
			String display = "These task can not be scheduled:\n";
			
			for (int currentTask = 0; currentTask < schedule.size(); currentTask++) {
				display += "\nT" + (currentTask + 1) + ": runs during time:";
				
				for (Job currentJob : schedule.get(currentTask).getJobs()) {
					if (currentJob.getStartPoint() != Job.UNASSIGNED_POINT) {
						display += "[" + currentJob.getStartPoint() + "," + currentJob.getEndPoint() + "] ";
						if (currentJob.getEndPoint() > currentJob.getDeadline()) {
							display += "DEADLINE MISS";
						}
					}
				}
			}
			
			Output.textDisplay(display);
		}else if (state == SOLVED) {
			String display = "These tasks can be scheduled:";
			
			for (int currentTask = 0; currentTask < schedule.size(); currentTask++) {
				display += "\nT" + (currentTask + 1) + ": runs during time:";
				
				for (Job currentJob : schedule.get(currentTask).getJobs()) {
					display += "[" + currentJob.getStartPoint() + "," + currentJob.getEndPoint() + "] ";
				}
			}
			
			if (infinite) {
				
				int hyperperiod = 0;
				int latest = 0;
				Schedule infinite = new Schedule();
				for (Task currentTask : schedule) {
					if (currentTask.isInfinite()) {
						if (currentTask.getPeriod() > hyperperiod) {
							hyperperiod = currentTask.getPeriod();
						}
					}
					
					for (Job currentJob : currentTask.getJobs()) {
						if (currentJob.getEndPoint() > latest) {
							latest = currentJob.getEndPoint();
						}
					}
				}
				
				display += "\nEvery hyperperiod lasting " + hyperperiod + " units after " + latest + " units would look like:";

				Output.textDisplay(display);

				for (Task currentTask : schedule) {
					if (currentTask.isInfinite()) {
						infinite.addTask(new Task(currentTask.getPeriod(),currentTask.getCost(),hyperperiod/currentTask.getPeriod()-1,false));
					}
				}
				infinite.solve(new EarliestDeadlineFirst(5));				
				infinite.display();
			}
			else {
				Output.textDisplay(display);
			}
			
			
			
		}
	}
	
}
