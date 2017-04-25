/** RoundRobinSchedulingAlgorithm.java
 * 
 * A scheduling algorithm that randomly picks the next job to go.
 *
 * @author: Kyle Benson
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class RoundRobinSchedulingAlgorithm extends BaseSchedulingAlgorithm {

    /** the time slice each process gets */
    private int quantum;
    private int interruptTime;
    private int activeJobIndex;
    private Vector<Process> jobs;

    RoundRobinSchedulingAlgorithm() {
        // Fill in this method
        /*------------------------------------------------------------*/
        quantum = 10;
        interruptTime = quantum;
        activeJob = null;
        activeJobIndex = -1;
        jobs = new Vector<Process>();
        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue. */
    public void addJob(Process p) {
        // Remove the next lines to start your implementation
        // throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        jobs.add(p);
        /*------------------------------------------------------------*/
    }

    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p) {
        // Remove the next lines to start your implementation
        // throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        int index = jobs.indexOf(p);
        boolean removedJob = jobs.remove(p);
        boolean isTimeToPreemt = (activeJobIndex >= index && index >= 0);
        boolean needPreemt = activeJobIndex == index;
        
        if(isTimeToPreemt)
        {
            if(needPreemt)
            {
                interruptTime = 0;
            }
            activeJobIndex--;
            try
            {
                activeJob = jobs.get(activeJobIndex);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                activeJob = null;
            }
        }
        return removedJob;
        /*------------------------------------------------------------*/
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the value of quantum.
     * 
     * @return Value of quantum.
     */
    public int getQuantum() {
        return quantum;
    }

    /**
     * Set the value of quantum.
     * 
     * @param v
     *            Value to assign to quantum.
     */
    public void setQuantum(int v) {
        this.quantum = v;
    }

    /**
     * Returns the next process that should be run by the CPU, null if none
     * available.
     */
    public Process getNextJob(long currentTime) {
        // Remove the next lines to start your implementation
        // throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        interruptTime--;
        int jobsSize = jobs.size() - 1;
        boolean notTimeToPreempt = (activeJobIndex >= 0 && !isJobFinished() && interruptTime > 0);
        boolean isJobsEmpty = jobs.isEmpty();
        boolean noMoreJob = (activeJobIndex < 0 || activeJobIndex >= jobsSize);
        
        if (notTimeToPreempt)
        {
            return activeJob;
        }
        else if(isJobsEmpty)
        {
            activeJobIndex = -1;
            return null;
        }
        else
        {
            if(noMoreJob)
            {
                activeJobIndex = 0;
            }
            else
            {
                ++activeJobIndex;
            }
            activeJob = jobs.get(activeJobIndex);
            interruptTime = quantum;
            return activeJob;
        }
        /*------------------------------------------------------------*/
    }

    public String getName() {
        return "Round Robin";
    }
    
}