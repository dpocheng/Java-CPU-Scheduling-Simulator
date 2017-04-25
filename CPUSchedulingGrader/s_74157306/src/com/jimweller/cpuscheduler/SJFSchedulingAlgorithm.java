/** SJFSchedulingAlgorithm.java
 * 
 * A shortest job first scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

import com.jimweller.cpuscheduler.Process;

public class SJFSchedulingAlgorithm extends BaseSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {
    
    private boolean preemptive;
    private Vector<Process> jobs;

    class SJFC implements Comparator<Process>
    {
        public int compare(Process p1, Process p2)
        {
            long p1BurstTime = p1.getBurstTime();
            long p2BurstTime = p2.getBurstTime();
            long p1PID = p1.getPID();
            long p2PID = p2.getPID();
            int compareBurstTime = Long.signum(p1BurstTime - p2BurstTime);
            int comparePID = Long.signum(p1PID - p2PID);
            
            if(p1BurstTime == p2BurstTime)
            {
                return comparePID;
            }
            else
            {
                return compareBurstTime;
            }
        }
    }

    SJFC c = new SJFC();

    SJFSchedulingAlgorithm(){
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
        Process shortest = null;
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
                shortest = jobs.get(0);
            }
            activeJob = shortest;
            return activeJob;
        }
        /*------------------------------------------------------------*/
    }

    public String getName(){
        return "Shortest Job First";
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
     * @param v  Value to assign to preemptive.
     */
    public void setPreemptive(boolean  v){
        // Remove the next lines to start your implementation
        // throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        preemptive = v;
        /*------------------------------------------------------------*/
    }
    
}