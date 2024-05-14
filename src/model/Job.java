package model;

public class Job {

	public static final int UNASSIGNED_POINT = -1;
	
	private int deadline;
	private int cost;
	private int startPoint = UNASSIGNED_POINT;
	private int endPoint = UNASSIGNED_POINT;
	
	public Job(int deadline, int cost) {
		this.deadline = deadline;
		this.cost = cost;
	}

	public int getDeadline() {
		return deadline;
	}

	public int getCost() {
		return cost;
	}

	public int getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(int startPoint) {
		this.startPoint = startPoint;
	}

	public int getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
	}
	
	

}
