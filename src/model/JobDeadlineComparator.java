package model;

import java.util.Comparator;

public class JobDeadlineComparator implements Comparator<Job> {

	@Override
	public int compare(Job job1, Job job2) {
		
		return job1.getDeadline() - job2.getDeadline();
	}

}
