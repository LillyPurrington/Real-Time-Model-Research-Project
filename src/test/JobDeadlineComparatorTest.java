package test;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Job;
import model.JobDeadlineComparator;

class JobDeadlineComparatorTest {

	@Test
	public void test() {
		JobDeadlineComparator jdc = new JobDeadlineComparator();
		assert(jdc.compare(new Job(2,0), new Job(1,0)) > 0);
	}
	
	@Test
	public void sortListTest() {
		JobDeadlineComparator jdc = new JobDeadlineComparator();
		ArrayList<Job> jobs = new ArrayList<Job>() ;
		jobs.add(new Job(1,0));
		jobs.add(new Job(1,0));
		jobs.add(new Job(4,0));
		jobs.add(new Job(5,0));
		jobs.add(new Job(4,0));
		jobs.add(new Job(2,0));
		
		jobs.sort(jdc);
		
		assert(jobs.get(0).getDeadline() == 1);
		assert(jobs.get(1).getDeadline() == 1);
		assert(jobs.get(2).getDeadline() == 2);
		assert(jobs.get(3).getDeadline() == 4);
		assert(jobs.get(4).getDeadline() == 4);
		assert(jobs.get(5).getDeadline() == 5);
	}

}
