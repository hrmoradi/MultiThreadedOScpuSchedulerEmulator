package Schaduler;
import assignments.ThreadedSorting;
import java.util.Arrays;
// Calling them


/////////////Merge.java
public class Test extends Thread{
    // variables
    int[] firstHalf;

    public static void main(String[] args) {
        //it was not in main
        System.out.print(" \n ");
        System.out.print("  \n ");
        // initializing
        // first
    }
    Test(int [] leftA){ //args
        firstHalf =  leftA; //firstHalf Can be used in program
    }
    public void run(){
        //new variables creation

        //System.out.println(Arrays.toString(result));			//the result(sorted array) is displayed
        //ThreadedSorting.Output(output_path, result);			// the result gets printed in output file

    }
}
//////ThreadedSorting.java
class Sorting implements Runnable{		//sorting class implements the runnable interface for threaded sorting
    int [] A;
    Sorting(int[] array){					//Sorting function definition
        A = array;
    }
    public void run(){						// run method defined which includes inbuilt sorting function for arrays
        Arrays.sort(A);
    }
}


// Calling from same class


// Sorting leftA = new Sorting(left);		//Runnable object created using Sorting class
// Sorting rightA = new Sorting(right);
// Thread t1 = new Thread(leftA);			// First thread is constructed
// Thread t2 = new Thread(rightA);
// t1.start();							    //First thread starts
// t2.start();

// try {
//    t1.join();                          // wait for it to finish
//    Output(path,left);					//Write to output file
// }catch (InterruptedException e){
//    e.printStackTrace();
// }


// Calling a thread in different Class (the thread is a class by it self)

//    Merge sortedArray = new Merge(left, right,path);		//Merge two threads
//    Thread t3 = new Thread(sortedArray);
//    t3.start();