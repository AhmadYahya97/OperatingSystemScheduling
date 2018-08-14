


# Operating System Scheduling
This is Operating Systems project. 
The project idea is to generate a randomly uniform srteam of jobs as the first stage.
Then in the second stage we will pick a scheduling algorithms out of the most 6 commonly used algorithms.
after this we will make a comparison of which algorithm performed better on which data.

as a Bonus part we designed an interactive user interface so the project can be used later for educational uses.

in the UI the progress of each job and the progress of the whole system can be seen.

The implemented algorithms: 
 - First-Come, First-Served FCFS Scheduling
 - Shortest-Job-First SJF Scheduling
 - Shortest Remaining Time
 - Priority Scheduling
 - Round Robin - RR Scheduling
 - Exponential Average SRJF

## Features

 - The student can visualize the job queue, device queue and ready queue.
 - The visualizing of the queue is done time unit by time unit so he/she can catch everything.
 - There's a log window which shows all the changes in every time unit.
 - There's also changes window which logs up all the changes in a job burst.
 - There are mainly three options in tracking an algorithm:
	 1. Next Step: moves to the next time unit. 
	 2. Next Change: moves to the next change (a change in any queue).
	 3. Animation: which is adjusted by a speed bar.
- The student can visualize the status of  CPU utilization, throughput, Turnaround time and Waiting time of all process at any time unit.
-  also he can check the Waiting and Turnaround times for a specific job at any time unit.
- For the stream of job generation the user can choose all the variables he/she want. like :
	 1. Number of jobs.
	 2. Degree of multi-programming.
	 3. Number of bursts for a job.
	 4. CPU and IO bursts ranges.
	 5. Priority. 
	 6. Arrival time range.
	 7. The context switch.
	 8. Tau and Alpha. (if needed)
-  There are two progress bars, one for the current burst of the job in the running state. and the other for the total progress of this job.
## Screenshots
### Initial screen
![Alt text](/Screenshots/initialScreen.PNG?raw=true "Initial Screen")
### Jobs uniform generation
![Alt text](/Screenshots/generatingJobs.PNG?raw=true "Jobs uniform generation")
### Picking an algorithm
![Alt text](/Screenshots/pickingAnAlgo.PNG?raw=true "Picking an algorithm")
### Animation mode
![Alt text](/Screenshots/screenshotInAnimatingMode.PNG?raw=true "Animation mode")
## Authors
- Ahmad Yahya. ( @AhmadYahya97 )
- Hamed Hijazi.
## Special thanks
- For prof. Adnan Yahya on his amazing efforts during this course!.
- Jfoenix library for its flexible and stylish tools.

