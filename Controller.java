package io;

import model.Task;
import algorithms.Algorithm;
import algorithms.EarliestDeadlineFirst;
import model.Schedule;

public class Controller {
	
	private int timeOutTimeSeconds = 20;
	
	private String anotherTaskPrompt = "Would you like to add another task? Y/N";
	private String[] anotherTaskAnswer = {"Y","N"};

	private String fileInputPrompt = "Would you like to use manual input (M) or file input (F)?";
	private String[] fileInputAnswer = {"F","M"};

	
	public void run() {
		Schedule schedule = new Schedule();

		String fileInput = Input.restrictedInput(fileInputPrompt, fileInputAnswer);
		
		if(fileInput.compareTo("M") == 0) { 
			String answer = "Y";
			while(answer.compareTo("Y") == 0) {
				schedule.addTask(askForTask());
				answer = Input.restrictedInput(anotherTaskPrompt, anotherTaskAnswer);
			}
		}else if(fileInput.compareTo("F") == 0) {
			fileInputParser(schedule);
		}
		
		Algorithm algorithm = new EarliestDeadlineFirst(schedule,timeOutTimeSeconds);
		
		String solveResult = algorithm.solve();
		
		if (solveResult.compareTo("Solved") == 0) {
			visualizeCorrectSchedule(schedule);
		}else if (solveResult.compareTo("Impossible") == 0) {
			visualizeImpossibleSchedule(schedule);
		}else if (solveResult.compareTo("Time Out") == 0) {
			System.out.println("Trying to schedule the given tasks has exceeded " + timeOutTimeSeconds + " seconds.");
		}
	}
	
	private void fileInputParser(Schedule schedule) {		
	}

	private void visualizeImpossibleSchedule(Schedule schedule) {
		
	}

	private Task askForTask() {
		return null;
	}
	
	private void visualizeCorrectSchedule(Schedule scedule) {
		
	}
	
}