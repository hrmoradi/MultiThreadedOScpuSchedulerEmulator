package Schaduler;
// import assignments.*; //importing other written package
import java.util.*;
import java.io.*;
/**
 * Created by hamid reza on 9/24/2016.
 */

public class MainApp {
    public static boolean fileReaderThreadx = true; //, cpuSchadulerThread, cpuThread, ioSchadulerThread, ioThread
    public static LinkedList<LinkedList<Long>> cpuReadyQ = new LinkedList<LinkedList<Long>>(); // cpuReadyQ
    public static LinkedList<LinkedList<Long>> IoReadyQ = new LinkedList<LinkedList<Long>>(); // IoReadyQ
    public static LinkedList<LinkedList<Long>> finished = new LinkedList<LinkedList<Long>>(); //
    static int numberOfProcesses = 0;
    static int numberOfProcessesIo = 0;
    static int i=0;



    /*public static synchronized void addNumberOfProcesses() { // synchronized
        numberOfProcesses++;
    }
    public static synchronized void minNumberOfProcesses() { // synchronized
        numberOfProcesses--;
    }
    public static synchronized int getNumberOfProcesses() { // synchronized
        return numberOfProcesses;
    }
    public static synchronized void addNumberOfProcessesIo() { // synchronized
        numberOfProcessesIo++;
    }
    public static synchronized void minNumberOfProcessesIo() { // synchronized
        numberOfProcessesIo--;
    }
    public static synchronized int getNumberOfProcessesIo() { // synchronized
        return numberOfProcessesIo;
    }
*/
    public static synchronized void setCpuReadyQ(LinkedList<Long>  x1) { // synchronized
        LinkedList<Long> x2 = new LinkedList<Long>(x1);
        cpuReadyQ.add(x2);
        System.out.println("     MainAPP:     setCputReadyQ:     Putted inside "); //    numberOfProcesses  +getNumberOfProcesses());
        //addNumberOfProcesses();
    }
    public static synchronized void setIoReadyQ(LinkedList<Long>  x1) { // synchronized
        LinkedList<Long> x2 = new LinkedList<Long>(x1);
        IoReadyQ.add(x2);
        System.out.println("     MainAPP:     setI/oReadyQ:     Putted inside  "); //   numberOfProcesses +getNumberOfProcessesIo());
        //addNumberOfProcesses();
    }
    public static synchronized void setFinished(LinkedList<Long>  x1) { // synchronized
        LinkedList<Long> x2 = new LinkedList<Long>(x1);
        finished.add(x2);
        System.out.println("     MainAPP:     setFinishd"+i);
        i++;
        //addNumberOfProcesses();
    }
    public static synchronized LinkedList<Long> getCpuReadyQ(int x) { // synchronized
        //MainApp.processes.get(0)[0]
         return MainApp.cpuReadyQ.get(x);

    }
    public static synchronized LinkedList<Long> getIoReadyQ(int x) { // synchronized
        //MainApp.processes.get(0)[0]
        return MainApp.IoReadyQ.get(x);

    }
    public static synchronized LinkedList<Long> getLastIoReadyQ() { // synchronized
        //MainApp.processes.get(0)[0]
        return MainApp.cpuReadyQ.get(cpuReadyQ.size()-1);

    }
    public static synchronized LinkedList<Long> getFinished() { // synchronized

        return MainApp.finished.get(finished.size()-1);

        //addNumberOfProcesses();
    }
    public static synchronized LinkedList<Long> removeCpuReadyQ(int x) { // synchronized
        //MainApp.processes.get(0)[0]
        //minNumberOfProcesses();
        return MainApp.cpuReadyQ.remove(x);

    }
    public static synchronized LinkedList<Long> removeIoReadyQ(int x) { // synchronized
        //MainApp.processes.get(0)[0]
        //minNumberOfProcessesIo();
        return MainApp.IoReadyQ.remove(x);

    }

    public static synchronized long getCpuReadyQxy(int x4,int y4) { // synchronized
        //MainApp.processes.get(0)[0]
        Long temp = getCpuReadyQ(x4).get(y4);
        System.out.println("     MainAPP:     getCpuReadyQxy:     returns: " +(temp));
        //xy = temp.get(y);
    return temp;

}

    public static synchronized long getIoReadyQxy(int x4,int y4) { // synchronized
        //MainApp.processes.get(0)[0]
        Long temp = getIoReadyQ(x4).get(y4);
        System.out.println("     MainAPP:     getIoReadyQxy:     returns: " +(temp));
        //xy = temp.get(y);
        return temp;

    }
    public static synchronized boolean remainingCpuReadyQ() { // synchronized
        //0-input time
        //1-output time
        //2-start working from block number 7
        //3-cpu work done time
        //4-io work done time
        //5-processing not finished 1 <<<<---------
        //6-is priority
        //7- FIRST CPU BURST
       /* long count2 = 0;
        int x2 = 0;
        int y2 = 5;*/
        // count remaining processes
        ////////////////////////////
       /* for (x2 = 0; x2 <= getNumberOfProcesses()-1; x2++) {
            count2 = count2 + getCpuReadyQxy(x2, 5);
            System.out.println("     MainAPP:     getCpuReadyQxy:     returns false if 0: " +(count2));
        }
        if (count2 ==0){return false;} else { return true;}*/
       return cpuReadyQ.isEmpty();
    }
    public static synchronized boolean remainingIoReadyQ() { // synchronized
        //0-input time
        //1-output time
        //2-start working from block number 7
        //3-cpu work done time
        //4-io work done time
        //5-processing not finished 1 <<<<---------
        //6-is priority
        //7- FIRST CPU BURST
/*
        long count2 = 0;
        int x2 = 0;
        int y2 = 5;
        // count remaining processes
        ////////////////////////////
        for (x2 = 0; x2 <= getNumberOfProcessesIo()-1; x2++) {
            count2 = count2 + getCpuReadyQxy(x2, 5);
            System.out.println("     MainAPP:     remainingIoReadyQ:     returns false if 0: " +(count2));
        }
        if (count2 ==0){return false;} else { return true;}
*/
        return IoReadyQ.isEmpty();
    }
    public static void main(String[] args) {
        long MainStartTime = System.currentTimeMillis();
        System.out.print("     *****Hamidreza Moradi Schaduler (OS assignment)*****     \n");
        System.out.print("     MainAPP:     Main Thread Running \n");

        // Reading command ////
        ///////////////////////
        System.out.println("     MainAPP:     Commandline Input Reading: ");
        System.out.println("     MainAPP:     EX: prog -alg [FIFO|SJF|PR|RR] [-quantum [integer(ms)]] -input [file name] \n     MainAPP:     prog -alg FIFO -quantum 20 -input c:\\text.txt \n ");
        //Scanner inputArray = new Scanner(System.in);
        //String command = inputArray.next(); //it does not read after the first space // test 2 -> test
        InputStreamReader inputCommand = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputCommand);
        String command=null;

        try {
            command = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error     MainAPP:     try:     command = reader.readLine()" + e.getMessage());
        }
        System.out.println(command);
        // Understanding Command ///
        ////////////////////////////

        System.out.println("\n*****     Personal Use: default command loading, remove for upload in blackboard     *****\n");
        command = "prog -alg FIFO -quantum 20 -input c:\\text.txt";

        String[] splitedCommand = command.split(" ");
        String alg = null;
        int quantum = 0;
        String input = null;
        for (int i = 0; i < splitedCommand.length; i++) {
            switch (splitedCommand[i]) {
                case "prog":
                            System.out.println("\n     MainAPP:     switch:     command detected"); break;
                case "-alg":
                            alg = splitedCommand[i+1]; System.out.println("   -alg: " +alg);
                            splitedCommand[i+1]= "detected";
                            break;
                case "-quantum":
                            quantum = Integer.parseInt(splitedCommand[i+1]); System.out.println("   -quantum: " +quantum);
                            // input check can be added later
                            splitedCommand[i+1]="detected";
                            break;
                case "-input":
                            input = splitedCommand[i+1]; System.out.println("   -input: " +input);
                            // input check can be added later
                            splitedCommand[i+1]="detected";
                            break;
                case "detected":
                            break;
                default:
                            System.out.println("\n     MainAPP:     switch:     Problem:     in detecting command in position " +splitedCommand[i]);
                            break;

            }
        }


        // now we have command,splitedCommand, // alg, quantum, input // all string
        // initializing threads
        // starting fileReadingThread

        System.out.println("\n     MainAPP:     fileReaderThread fileReader = new fileReaderThread(path);");
        fileReaderThread fileReader = new fileReaderThread(input);
        System.out.println("\n     MainAPP:     Thread fR = new Thread(fileReader);");
        Thread fR = new Thread(fileReader);
        System.out.println("\n     MainAPP:     fR.start();");
        fR.start(); // runs the run in new thread

        // Starting cpuSchadulerThread
        cpuSchadulerThread cpuSchaduler = new cpuSchadulerThread(alg, quantum);
        Thread cS = new Thread(cpuSchaduler);
        System.out.println("\n     MainAPP:     cS.start();");
        cS.start(); // runs the run in new thread

        // Starting cpuThread


        // starting ioSchadulerThread
        ioSchadulerThread ioSchaduler = new ioSchadulerThread();
        Thread iS = new Thread(ioSchaduler);
        System.out.println("\n     MainAPP:     iS.start();");
        iS.start(); // runs the run in new thread

        // starting ioThread

        // waiting for them to finish
        try {
             fR.join();
             cS.join();
             iS.join();
         } catch (InterruptedException e) {
             e.printStackTrace();
            System.err.println("Error     MainAPP:     fR.join();cS.join();iS.join();" + e.getMessage());
         }
        ////////////////////////////////////////////////////////////////////////////////////////////////// Main timing
        long MainfinishTime = System.currentTimeMillis();
        //long MainStartTime = ;  // at start of app
        /////////////////////////////////////////////////////////////////////////////////////////////////// Thread controlls
        //0-input time
        //1-output time
        //2-start working from block number 7
        //3-cpu work done time
        //4-io work done time
        //5-time of first block processed
        //6-is priority
        //7- FIRST CPU BURST
        long turnAround =0;
        long waiting =0;
        long responce =0;
        long comulativeTurnAround =0;
        long comulativeWaiting =0;
        long comulativeResponce =0;
        long comulativeCpu =0;
        long comulativeIO=0;
        for (int counter = 0; counter < finished.size(); counter++) {
            turnAround = (finished.get(counter).get(1) - finished.get(counter).get(0)); //(output-input)
            waiting = turnAround - (finished.get(counter).get(3) + finished.get(counter).get(4 )); // turn -cpu -io
            responce = (finished.get(counter).get(5) - finished.get(counter).get(0));//
            System.out.println(counter +"th finished process: turnAround: "+turnAround+" waiting: "+waiting+" responce: "+responce+ " cpu: "+finished.get(counter).get(3)+ " I/O: "+finished.get(counter).get(4));
            comulativeTurnAround = comulativeTurnAround + turnAround;
            comulativeWaiting = comulativeWaiting + waiting ;
            comulativeResponce = comulativeResponce + responce;
            comulativeCpu = comulativeCpu + finished.get(counter).get(3);
            comulativeIO = comulativeIO + finished.get(counter).get(4);
        }
        long MainFinishMinStart=MainfinishTime - MainStartTime;
        float cpuUtil = ((float)comulativeCpu/MainFinishMinStart);
        float throughPut = ((float)finished.size()/MainFinishMinStart);
        long avgTurnAround = comulativeTurnAround/finished.size();
        long avgWaiting = comulativeWaiting/finished.size();
        long avgResponce = comulativeResponce/finished.size();

        System.out.println("comulativeCpu: "+comulativeCpu+ " comulativeIO: "+comulativeIO+" (MainfinishTime - MainStartTime): "+MainFinishMinStart);
        System.out.println("finished.size(): "+finished.size());
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////// REPORTING /////////////////////////////////////////////////////////////
        System.out.println("-------------------------------------------------");
        System.out.println("                    Reporting                    ");
        System.out.println("-------------------------------------------------");
        System.out.println("Input File Name: "+input);
        System.out.println("CPU Schaduling Alg: "+alg+ " ( "+quantum+" ) 0:No Quntum");
        System.out.println("CPU utilization: "+ cpuUtil );
        System.out.println("Throughput: "+throughPut );
        System.out.println("Turnaround time: "+ avgTurnAround );
        System.out.println("Waiting time: "+ avgWaiting );
        System.out.println("Response time: "+ avgResponce);
        System.out.println("-------------------------------------------------");

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////Writting to file /////////////////////////////////////////////////////////

        // Reading command ////
        ///////////////////////
        System.out.println("     MainAPP:     Commandline Input Reading(give output file name): if file existed it will append to it ");
        System.out.println("     MainAPP:     EX: c:\\report.txt \n ");
        //Scanner inputArray = new Scanner(System.in);
        //String command = inputArray.next(); //it does not read after the first space // test 2 -> test
        InputStreamReader inputCommand2 = new InputStreamReader(System.in);
        BufferedReader reader2 = new BufferedReader(inputCommand2);
        String command2=null;

        try {
            command2 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error     MainAPP:     try:     command2 = reader.readLine()" + e.getMessage());
        }
        System.out.println(command2);
        // Understanding Command ///
        ////////////////////////////

        System.out.println("\n*****     Personal Use: default command loading, remove for upload in blackboard     *****\n");
        command2 = "c:\\report.txt";


           PrintWriter writer = null;

            try{
                File file =new File(command2);

                /* This logic is to create the file if the
                 * file is not already present
                 */
                //Here true is to append the content to file
                FileWriter fw = new FileWriter(file,true);
                //BufferedWriter writer give better performance
                BufferedWriter bw = new BufferedWriter(fw);

/*

                if(!file.exists()){file.createNewFile();}
                writer = new PrintWriter(new FileWriter(new File(command2),true));
*/
                bw.write(System.getProperty( "line.separator" ));
                bw.write("-------------------------------------------------");
                bw.write(System.getProperty( "line.separator" ));
                bw.write("-------------------------------------------------");
                bw.write(System.getProperty( "line.separator" ));
                bw.write("                    Reporting                    ");
                bw.write(System.getProperty( "line.separator" ));
                bw.write("-------------------------------------------------");
                bw.write(System.getProperty( "line.separator" ));
                bw.write("Input File Name: "+input );
                bw.write(System.getProperty( "line.separator" ));
                bw.write("CPU Schaduling Alg: "+alg+ " ( "+quantum+" ) 0:No Quntum");
                bw.write(System.getProperty( "line.separator" ));
                bw.write("CPU utilization: "+ cpuUtil );
                bw.write(System.getProperty( "line.separator" ));
                bw.write("Throughput: "+throughPut);
                bw.write(System.getProperty( "line.separator" ));
                bw.write("Turnaround time: "+ avgTurnAround);
                bw.write(System.getProperty( "line.separator" ));
                bw.write("Waiting time: "+ avgWaiting );
                bw.write(System.getProperty( "line.separator" ));
                bw.write("Response time: "+ avgResponce);
                bw.write(System.getProperty( "line.separator" ));
                bw.write("-------------------------------------------------");
                bw.write(System.getProperty( "line.separator" ));

                //Closing BufferedWriter Stream
                bw.close();

                System.out.println("Data successfully Written or Appended at the end of file");


               // writer.close();

            }catch(FileNotFoundException e){
                e.getMessage();
                System.out.println(" Writer error FileNotFoundException");

            }catch(IOException e){
                e.getMessage();
                System.out.println(" Writer error IOException");
            }
        }


    }

