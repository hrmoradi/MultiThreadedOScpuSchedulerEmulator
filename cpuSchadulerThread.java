package Schaduler;
import java.util.LinkedList;
/**
 * Created by hamid reza on 9/24/2016.
 */
public class cpuSchadulerThread extends Thread{
    // variables
    String alg = null;
    int quantum = 0;
    public static void main(String[] args) {
        //it was not in main
        System.out.print("RUNNING     MainApp.main     FROM     cpuSchadulerThread.main\n\n");
        MainApp.main(args);
        System.out.print("  \n ");
        // initializing
        // first
    }
    cpuSchadulerThread(String Malg,int Mquantum){ //args
        alg = Malg;
        quantum = Mquantum;
    }
    public void run(){
        //new variables creation
        System.out.print("     cpuSchadulerThread:     run()\n");


            switch (alg) {
                case "FIFO":    System.out.println("     cpuSchadulerThread:     switch:     FIFO");
                                while (( (MainApp.fileReaderThreadx) || (!MainApp.remainingCpuReadyQ()) || (!MainApp.remainingIoReadyQ()) )) {
                                    //System.out.println("     cpuSchadulerThread:     switch->while:     remainingCpuReadyQ: " + !MainApp.remainingCpuReadyQ() + "     fileREaderThread: " + MainApp.fileReaderThreadx + " remainingIoReadyQ: " + !MainApp.remainingIoReadyQ());

                                    /*try {
                                        Thread.sleep(9);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                        System.err.println("Error     cpuSchadulerThread:     switch->while->try:    sleep(200)" + e.getMessage());
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
                                    if (MainApp.remainingCpuReadyQ()) {
                                    } else {
                                        LinkedList<Long> temp = new LinkedList<Long>(MainApp.getCpuReadyQ(i));
                                        MainApp.removeCpuReadyQ(i);
                                        ///// get the process block cpu turn is now
                                        long processTurn = temp.get(2);
                                        long inNeed = temp.get((int) processTurn);
                                        long thisCycle = inNeed;
                                        ///// process the amount needed
                                        System.out.println("     cpuSchadulerThread:     switch->while:     sleep(" + temp.get((int) processTurn) + ")");
                                        try {
                                            Thread.sleep(temp.get((int) processTurn));
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                            System.err.println("Error     cpuSchadulerThread:     switch->while->try:    sleep(200)" + e.getMessage());
                                        }
                                        ///// change control variables
                                        inNeed = inNeed - thisCycle; // which in this case in fifo is 0 always
                                        temp.set((int) processTurn, inNeed);
                                        //System.out.println("     cpuSchadulerThread:     switch->while:     inNeed Time: " + inNeed);
                                        Long doneBurst = temp.get(3);
                                        doneBurst = doneBurst + thisCycle;
                                        temp.set(3, doneBurst);
                                        System.out.println("     cpuSchadulerThread:     switch->while:     inNeed Time: " + inNeed + " this cycle: " + thisCycle+"     doneBurst: " + doneBurst);
                                        // if (inNeed==0) {} // which in fifo is ziro every time because in done completely
                                        if (temp.get(5)==0) {temp.set(5,System.currentTimeMillis());}
                                        if (temp.get(2) < (temp.size()-1) && temp.get((int) processTurn) == 0) {
                                            processTurn++;
                                            temp.set(2, processTurn); // point to next burst which is I/O
                                            MainApp.setIoReadyQ(temp);
                                            System.out.println("     cpuSchadulerThread:     switch->while->if     setIoReadyQ" +temp);// + MainApp.getLastIoReadyQ());
                                        } else {
                                            if (temp.get((int) processTurn) != 0) {
                                                MainApp.setCpuReadyQ(temp);
                                                System.out.println("     cpuSchadulerThread:     switch->while->if     Not finished     setCpuReadyQ end" +temp);
                                            } else {
                                                if (temp.get(2) == (temp.size()-1)) {
                                                    temp.set(1,System.currentTimeMillis());
                                                    MainApp.setFinished(temp);
                                                    System.out.println("     cpuSchadulerThread:     switch->while->if     finished     setFinished"+MainApp.getFinished());
                                                }

                                            }
                                        }
                                    }
                                }
                                break;
                case "SJF":     System.out.println("     cpuSchadulerThread:     switch:     SJF");

                                 break;
                case "PR":      System.out.println("     cpuSchadulerThread:     switch:     PR");

                                 break;
                case "RR":      System.out.println("     cpuSchadulerThread:     switch:     RR");

                                 break;
                default: System.out.println("\n     cpuSchadulerThread:     while->switch     Problem: detecting Algorithm"); break;

            }

        }

    }

