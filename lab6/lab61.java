//Authors : Nicolas de la Fuente & Dillan Lopez
public class lab61{
    public static void main(String []args){
        int outcome = 0;
        for(int i = 1; i< 150; i*=2){
            double startTime = System.nanoTime();
            outcome = iterativeFib(i);
            double timeElapsed = System.nanoTime()-startTime;
            System.out.println("The interger is: " + i + " the time it took to run is: " + 
            timeElapsed + " the sum is: " + outcome);
           
        }
    }

    public static int iterativeFib(int n){
        int nextNum = 1;
        int previousNum = 0;
        int sum = 0;
        if(n == 0){
            return 0;
        }
        else if(n == 1){
            return 1;
        }
        for(int i = 2; i <= n; i++){
            sum = nextNum + previousNum;
            previousNum = nextNum;
            nextNum = sum;
        }
        return sum;
    }
}