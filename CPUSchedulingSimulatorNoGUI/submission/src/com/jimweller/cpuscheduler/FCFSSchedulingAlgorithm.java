/** FCFSSchedulingAlgorithm.java
 * 
 * A first-come first-served scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class FCFSSchedulingAlgorithm extends BaseSchedulingAlgorithm {
    
    private Vector<Process> jobs;

    class FCFSC implements Comparator<Process>
    {
        public int compare(Process p1, Process p2)
        {
            long p1ArrivalTime = p1.getArrivalTime();
            long p2ArrivalTime = p2.getArrivalTime();
            long p1PID = p1.getPID();
            long p2PID = p2.getPID();
            int compareArrivalTime = Long.signum(p1ArrivalTime - p2ArrivalTime);
            int comparePID = Long.signum(p1PID - p2PID);
            
            if(p1ArrivalTime == p2ArrivalTime)
            {
                return comparePID;
            }
            else
            {
                return compareArrivalTime;
            }
        }
    }

    FCFSC c = new FCFSC();

    FCFSSchedulingAlgorithm(){
        // Fill in this method
        /*------------------------------------------------------------*/
        activeJob = null;
        jobs = new Vector<Process>();
        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
        // Remove the next lines to start your implementation
        // throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        jobs.add(p);
        jobs.sort(c);
        /*------------------------------------------------------------*/
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
        // Remove the next lines to start your implementation
        // throw new UnsupportedOperationException();

        // Fill in this method
        /*------------------------------------------------------------*/
        if(p == activeJob)
        {
            activeJob = null;
        }
        return jobs.remove(p);
        /*------------------------------------------------------------*/
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
        throw new UnsupportedOperationException();
    }

    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime){
        // Remove the next lines to start your implementation
        // throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        Process earliest = null;
        boolean waitForJobFinish = !isJobFinished();
        boolean isJobsEmpty = jobs.isEmpty();
        
        if(waitForJobFinish)
        {
            return activeJob;
        }
        else
        {
            if(!isJobsEmpty)
            {
                earliest = jobs.get(0);
            }
            activeJob = earliest;
            return activeJob;
        }
        /*------------------------------------------------------------*/
    }

    public String getName(){
        return "First-Come First-Served";
    }
    
}