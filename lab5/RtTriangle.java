import java.util.Scanner;

public class RtTriangle extends Shape{
    //Data Fields
    private double base = 0;
    private double height = 0;

    //Constructor
    public RtTriangle(){
        super("RtTriangle");
    }
    /**Constructs right triangle of the specified size.
     * @param base the base
     * @param height the height
     */

     public RtTriangle(double base, double height){
         super("RtTriangle");
         this.base = base;
         this.height = height;
     }

     //Methods
     /**Gets the base
      * @return the base
      */
      public double getBase(){
          return base;
      }

      /**Gets the height
      * @return the height
      */
      public double getHeight(){
        return height;
    }

    /**Computes the area
      * @return the area of the right triangle
      */
    @Override
    public double ComputeArea(){
        return .5 * base * height;
    }

    /**Computes the perimeter
      * @return the perimeter of the right triangle
      */
    @Override
    public double ComputePerimeter(){
        double hypo = (base*base) + (height* height);
        return Math.sqrt(hypo)+base+height;
      }
    
    // Read attribute of the right triangle
    @Override
    public void readShapeData(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the base of the right triangle");
        base = in.nextDouble();
        System.out.println("Enter the height of the right triangle");
        height = in.nextDouble();
    }

    /**Create a string representation of the right triangle
     * @return a string representation of the triangle
     */
    @Override
    public String toString(){
        return super.toString()+ ": base is " + base + ", height is " + height;
    }
    
}