package Schaduler;

import java.util.LinkedList;

/**
 * Created by hamid reza on 9/24/2016.
 */
public class ioSchadulerThread extends Thread{
    // variables
    public static void main(String[] args) {
        //it was not in main
        System.out.print("RUNNING     MainApp.main     FROM     I/oSchadulerThread.main\n\n");
        MainApp.main(args);
        System.out.print("  \n ");
        // initializing
        // first
    }
    ioSchadulerThread(){ //args
        //firstHalf =  leftA; //firstHalf Can be used in program
    }
    public void run(){
        //new variables creation
        System.out.print("     I/oSchadulerThread:     run()\n");
        System.out.println("     I/oSchadulerThread:     switch:     FIFO");
        while (( (MainApp.fileReaderThreadx) || (!MainApp.remainingCpuReadyQ()) || (!MainApp.remainingIoReadyQ()) )) {
            //System.out.println("     I/oSchadulerThread:     while:     remainingCpuReadyQ: " + !MainApp.remainingCpuReadyQ() + "     fileReaderThread: " + MainApp.fileReaderThreadx + " remainingIoReadyQ: " +!MainApp.remainingIoReadyQ() );

            /*try {
                Thread.sleep(9);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("Error     I/oSchadulerThread:     while->try:    sleep(200)" + e.getMessage());
            }*/
            //0-input time
            //1-output time
            //2-start working from block number 7
            //3-cpu work done time
            //4-io work done time
            //5-processing not finished 1 <<<<---------
            //6-is priority
            //7- FIRST CPU BURST
            int i = 0;
            ///// get the process which turn is
            if (MainApp.remainingIoReadyQ() ) {
            } else {
                LinkedList<Long> temp2 = new LinkedList<Long>(MainApp.getIoReadyQ(i));

                ///// get the process block cpu turn is now
                long processTurn = temp2.get(2);
                long inNeed = temp2.get((int) processTurn);
                long thisCycle = inNeed;
                ///// process the amount needed
                System.out.println("     I/oSchadulerThread:     while:     sleep(" + temp2.get((int) processTurn) + ")");
                try {
                    Thread.sleep(temp2.get((int) processTurn));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.err.println("Error     I/oSchadulerThread:     while->try:    sleep(200)" + e.getMessage());
                }
                ///// change control variables
                inNeed = inNeed - thisCycle; // which in this case in fifo is 0 always
                temp2.set((int) processTurn, inNeed);

                Long doneIo = temp2.get(4);
                doneIo = doneIo + thisCycle;
                temp2.set(4, doneIo);
                System.out.println("     I/oSchadulerThread:     while:     inNeed Time: " + inNeed + " this cycle: " + thisCycle+"     doneI/o: " + doneIo);
                // if (inNeed==0) {} // which in fifo is ziro every time because in done completely
                if (temp2.get(2) < temp2.size() && temp2.get((int) processTurn)==0 ) {
                    processTurn++;
                    temp2.set(2, processTurn); // point to next burst which is I/O
                    MainApp.setCpuReadyQ(temp2);
                    MainApp.removeIoReadyQ(i);
                    System.out.println("     I/oSchadulerThread:     End     setCpuReadyQ" +temp2);
                }

            }
        }
                }


        }




