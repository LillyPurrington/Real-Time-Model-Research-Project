package io;

import model.Task;
import algorithms.EarliestDeadlineFirst;
import model.Schedule;

public class Controller {
	
	//Various strings used for input.
	private String[] yesNoAnswer = {"Y","N"};
	
	private String anotherTaskPrompt = "Would you like to add another task? Y/N";

	private String timeOutPrompt = "What time (in seconds) do you want to time out in?";
	
	private String costPrompt = "Input the time cost of the task.";
	
	private String periodPrompt = "Input the deadline.";
	
	private String repeatablePrompt = "Will the task repeat? Y/N";

	private String infiniteRepeatPrompt = "Will it repeat infinitly? Y/N";

	private String finiteRepeatPrompt = "How many times will it repeat?";
	
	
	
	public void run() {
		Schedule schedule = new Schedule();
		
		Input input = new Input();
		
		//Getting how long the program should run before timing out in seconds. 
		int timeOutTimeSeconds = input.numericalInput(timeOutPrompt);

				
		String answer = "Y";
		while(answer.compareTo("Y") == 0) {
			schedule.addTask(askForTask(input));
			answer = input.restrictedInput(anotherTaskPrompt, yesNoAnswer);
		}
	
		
		schedule.solve(new EarliestDeadlineFirst(timeOutTimeSeconds));
		
		schedule.display();
		
		input.close();
	}

	private Task askForTask(Input input) {
		int cost = input.numericalInput(costPrompt);
		int period = input.numericalInput(periodPrompt);
		int repeatNum;
		boolean infinite = false;
		
		String repeatable = input.restrictedInput(repeatablePrompt, yesNoAnswer);
		if (repeatable.compareTo("N") == 0) {
			//If it does not repeat
			repeatNum = 0;
		}else {
			
			String infinateRepeat = input.restrictedInput(infiniteRepeatPrompt, yesNoAnswer);
			if (infinateRepeat.compareTo("N") == 0) {
				//If it repeats a finite number of times
				repeatNum = input.numericalInput(finiteRepeatPrompt);
			}else {
				//If it repeats infinitely.
				repeatNum = -1;
				infinite = true;
			}
		}

		return new Task(period,cost,repeatNum,infinite);
	}
		
}