package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import model.Job;
import model.JobDeadlineComparator;
import model.LeastCommonMultipleFinder;
import model.Schedule;
import model.Task;

/**
 * This is the earliest deadline first algorithm. It works by looking at all the given tasks and doing them in the order of their deadlines.
 * @author Lilly Purrington
 */
public class EarliestDeadlineFirst extends Algorithm {

	public EarliestDeadlineFirst(int timeOutTimeSeconds) {
		super(timeOutTimeSeconds);
	}

	@Override
	public int solve(ArrayList<Task> schedule) {
		
		ArrayList<Integer> periodArray = new ArrayList<Integer>();
		for (Task currentTask : schedule) {
			periodArray.add(currentTask.getPeriod());
		}
		int hyperperiod = LeastCommonMultipleFinder.FindLeastCommonMultipleOfArray(periodArray);
		
		float utilization = 0;
		for (Task currentTask : schedule) {
			for (Job currentJob: currentTask.getJobs()){
				utilization += ((float) currentJob.getCost()/hyperperiod);
			}
		}
		
		if (utilization > 1){
			return Schedule.UNSOLVABLE_OVER_UTILIZATION;
		}
		
		long startTime = System.currentTimeMillis();
		ArrayList<Job> jobs = new ArrayList<Job>();
		
		for (Task currentTask : schedule) {
			jobs.addAll(Arrays.asList(currentTask.getJobs()));
		}
		
		jobs.sort(new JobDeadlineComparator());
		
		int time = 0;
		for (Job currentJob : jobs) {
			currentJob.setStartPoint(time);
			currentJob.setEndPoint(time += currentJob.getCost());
			
			if (currentJob.getEndPoint() > currentJob.getDeadline()) {
				return Schedule.UNSOLVABLE;
			}
			if ((System.currentTimeMillis() - startTime) / 100 > timeOutTimeSeconds) {
				return Schedule.TIMED_OUT;
			}
		}
		return Schedule.SOLVED;
	}

}
