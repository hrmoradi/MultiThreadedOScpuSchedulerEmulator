package Schaduler;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by hamid reza on 9/24/2016.
 */
public class fileReaderThread extends Thread{
    // variables
    String pathLocal;
    public static void main(String[] args) {
        //it was not in main
        System.out.print("RUNNING     MainApp.main     FROM     fileReaderThread.main\n\n");
        MainApp.main(args);
        System.out.print("  \n ");
        // initializing
        // first
    }
    fileReaderThread(String path){ //args
        //firstHalf =  leftA; //firstHalf Can be used in program
        System.out.print("     fileReaderThread: (String path)\n");
        pathLocal = path;

    }
    public void run(){
        //new variables creation
        System.out.print("     fileReaderThread: run()\n");
        String[] aLine;
        LinkedList<Long>  timeCpuIoProc = new LinkedList<Long> ();
        int i=0;
        int j=0;

        try{
            System.out.print("     fileReaderThread: run():     try->     reading file\n");
            FileInputStream fiStream = new FileInputStream(pathLocal);
            DataInputStream diStream = new DataInputStream(fiStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(diStream));
            String textString;
            System.out.print("     fileReaderThread: run():     try->     textString.split( )\n" );
            while ((textString = br.readLine() ) != null) {  //seperating lines
                aLine = textString.split(" "); // \n
                switch (aLine[0]) {
                        case "proc":
                                    System.out.println("     fileReaderThread: run():     try->while     creating "+ j + " th timeCpuIoProc(temp var to put into cpu ready Q)");
                                    timeCpuIoProc.add(System.currentTimeMillis());   //0-input time
                                    timeCpuIoProc.add((long) 0); //1-output time
                                    timeCpuIoProc.add((long) 7); //2-start working from block index number 7 // first cpu burst // before all control data
                                    timeCpuIoProc.add((long) 0); // cpu work done
                                    timeCpuIoProc.add((long) 0); // io work done
                                    timeCpuIoProc.add((long) 0); // first responce cpu
                                                                 // # 6 is priority
                                    for (i=6; i< (aLine.length+5) ; i++){
                                        timeCpuIoProc.add(Long.parseLong(aLine[i-5]));
                                    }

                                    MainApp.setCpuReadyQ(timeCpuIoProc); //put into cpu ready Q
                                    //j pointer problem//System.out.println("     fileReaderThread: run():     try->while->switch->     getting j:"+ j + "th timeCpuIoProc from setCpuReadyQ " +MainApp.getCpuReadyQ(j));
                                    j++;
                                    timeCpuIoProc.clear();
                                    break;
                        case "sleep":
                                    System.out.print("     fileReaderThread: run():     try->while->switch     sleep for:"+Integer.parseInt(aLine[1])+"  \n");Thread.sleep(Integer.parseInt(aLine[1]));break;
                        case "stop":
                                    System.out.print("     fileReaderThread: run():     try->while->switch     Stop reading from file\n");break;
                        default:
                                    System.out.println("\n     fileReaderThread: run():     try->while->switch     Problem in Switch " +aLine[0]); break;

                    }
                //j pointer problem//System.out.println("     fileReaderThread: run():     try->while     getting last j:"+ (j-1) + "th timeCpuIoProc from setCpuReadyQ " +MainApp.getCpuReadyQ(j));

            }
            diStream.close();
        }catch (Exception e){
            System.err.println("Error     fileReaderThread: run():     try: " + e.getMessage());
        }

        System.out.println("     fileReaderThread: run():     MainApp.fileReaderThread = false;     END Thread*****");
        MainApp.fileReaderThreadx = false;
    }

}


