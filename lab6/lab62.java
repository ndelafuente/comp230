//Authors : Nicolas de la Fuente & Dillan Lopez
public class lab62{
    public static void main(String []args){
        System.out.println("i\t\t\tavg run time");
        for(int i = 1; i <= 32; i*=2){
            double timeElapsed = 0;
            int k;
            for (k = 1; k <= 10000; k++) {
                double startTime = System.nanoTime();
                recursiveFib(i);
                timeElapsed += System.nanoTime()-startTime;
            }
            timeElapsed /= k;
            System.out.printf("%d\t\t\t%.4f\n", i, timeElapsed);
        }
    }

    public static int recursiveFib(int n){
        if (n == 0 || n == 1)
            return n;
        else
            return recursiveFib(n-1) + recursiveFib(n-2);
    }
}
