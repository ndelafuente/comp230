//Authors : Nicolas de la Fuente & Dillan Lopez
public class lab62{
    public static void main(String []args){
        int outcome = 0;
        for(int i = 1; i< 50; i*=2){
            double startTime = System.nanoTime();
            outcome = recursiveFib(i);
            double timeElapsed = System.nanoTime()-startTime;
            System.out.println("The interger is: " + i + " the time(in nanoseconds) it took to run is: " + 
            timeElapsed + " the sum is: " + outcome);
           
        }
    }

    public static int recursiveFib(int n){
        if (n == 0 || n == 1)
            return n;
        else
            return recursiveFib(n-1) + recursiveFib(n-2);
    }
}