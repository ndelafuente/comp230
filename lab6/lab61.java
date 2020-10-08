//Authors : Nicolas de la Fuente & Dillan Lopez
public class lab61{
    public static void main(String []args){
        System.out.println("i\t\t\tavg run time");
        for(int i = 1; i<= 4096; i*=2){
            double timeElapsed = 0;
            int k;
            for (k = 1; k <= 1000000; k++) {
                double startTime = System.nanoTime();
                iterativeFib(i);
                timeElapsed += System.nanoTime()-startTime;
            }
            timeElapsed /= k;
            System.out.printf("%d\t\t\t%.4f\n", i, timeElapsed);
        }
    }

    public static int iterativeFib(int n){
        if(n == 0){
            return 0;
        }
        int nextNum = 1;
        int previousNum = 0;
        int sum = 1;
        for(int i = 2; i <= n; i++){
            sum = nextNum + previousNum;
            previousNum = nextNum;
            nextNum = sum;
        }
        return sum;
    }
}
