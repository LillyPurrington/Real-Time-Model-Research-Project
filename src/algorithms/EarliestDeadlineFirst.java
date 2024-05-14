package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import model.Job;
import model.JobDeadlineComparator;
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
		
		/*
		 * So since this is for a project, there are some required parts. One part that is required is using equations to show that a 
		 * particular set of jobs is unschedulable and than ending the solve there. I will do that by calculating utilization. This would
		 * all be fine, except the way my program is set up, I want to show the deadline misses when it is unsolvable. If I just ended when I
		 * found that utilization is > 1, I would then need to go back and do what I was going to do anyway to find where excactly it would mess up.
		 * So while it would be possible to calculate the utilization and then determine whether it is posiblile to solve, doing that would actually be 
		 * slower in this case. So I will just comment it out.
		 */
		/*
		 * int hyperperiod = 0;
		for (Task currentTask : schedule) {
			if (currentTask.getPeriod() > hyperperiod) {
				hyperperiod = currentTask.getPeriod();
			}
		}
		
		float utilization = 0;
		for (Task currentTask : schedule) {
			for (Job currentJob: currentTask){
				utilization += ((float) currentJob.get(period))/hyperperiod;
			}
		}
		
		if (utiliztion > 1){
			return Schedule.UNSOLVABLE;
		}
		 * 
		 * 
		 */
		
		
		
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
