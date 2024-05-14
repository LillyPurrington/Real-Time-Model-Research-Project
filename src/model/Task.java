package model;

public class Task {

	private int period;
	private int cost;
	private int repeating;
	private boolean isInfinite;
	
	private Job[] jobs;
	
	public Task(int period, int cost, int repeating, boolean isInfinite) {
		this.period = period;
		this.cost = cost;
		this.repeating = repeating;
		this.isInfinite = isInfinite;
		
		if (!isInfinite) {
			jobs = new Job[repeating+1];
			for (int x = 0; x < jobs.length; x++) {
				jobs[x] = new Job((x+1)*period,cost);
			}
		}
		
	}
	
	public int getPeriod() {
		return period;
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getAmountOfRepeat() {
		return repeating;
	}
	
	public boolean isInfinite() {
		return isInfinite;
	}
	
	public Job[] getJobs(){
		return jobs;
	}
	
	public void setInifiniteJobs(int hyperperiod) {
		jobs = new Job[hyperperiod/period];
		for (int x = 0; x < jobs.length; x++) {
			jobs[x] = new Job((x+1)*period,cost);
		}
	}
}
