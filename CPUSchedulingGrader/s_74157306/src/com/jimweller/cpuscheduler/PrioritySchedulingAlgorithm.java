/** PrioritySchedulingAlgorithm.java
 * 
 * A single-queue priority scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

import com.jimweller.cpuscheduler.Process;

public class PrioritySchedulingAlgorithm extends BaseSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {
    
    private boolean preemptive;
    private Vector<Process> jobs;
    
    class PC implements Comparator<Process>
    {
        public int compare(Process p1, Process p2)
        {
            long p1PriorityWeight = p1.getPriorityWeight();
            long p2PriorityWeight = p2.getPriorityWeight();
            long p1PID = p1.getPID();
            long p2PID = p2.getPID();
            int comparePriorityWeight = Long.signum(p1PriorityWeight - p2PriorityWeight);
            int comparePID = Long.signum(p1PID - p2PID);
            
            if(p1PriorityWeight == p2PriorityWeight)
            {
                return comparePID;
            }
            else
            {
                return comparePriorityWeight;
            }
        }
    }
    
    PC c = new PC();
    
    PrioritySchedulingAlgorithm(){
        // Fill in this method
        /*------------------------------------------------------------*/
        activeJob = null;
        preemptive = false;
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
        Process loftiest = null;
        boolean waitForJobFinish = (!isJobFinished() && !isPreemptive());
        boolean isJobsEmpty = jobs.isEmpty();
        
        if(waitForJobFinish)
        {
            return activeJob;
        }
        else
        {
            if(!isJobsEmpty)
            {
                loftiest = jobs.get(0);
            }
            activeJob = loftiest;
            return activeJob;
        }
        /*------------------------------------------------------------*/
    }

    public String getName(){
        return "Single-Queue Priority";
    }

    /**
     * @return Value of preemptive.
     */
    public boolean isPreemptive(){
        // Remove the next lines to start your implementation
        // throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        return preemptive;
        /*------------------------------------------------------------*/
    }
    
    /**
     * @param v Value to assign to preemptive.
     */
    public void setPreemptive(boolean v){
        // Remove the next lines to start your implementation
        // throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        preemptive = v;
        /*------------------------------------------------------------*/
    }
    
}