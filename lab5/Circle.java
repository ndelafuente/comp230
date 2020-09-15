import java.util.Scanner;

public class Circle extends Shape {
    // Data Fields
    private double radius = 0;

    // Constructors
    public Circle() {
        super("Circle");
    }

    /**
     * Constructs a circle of the specified size.
     * @param radius the radius
     */
    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    // Methods
    /**
     * Get the radius.
     * @return The radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Compute the area (a = pi * r^2)
     * @return The area
     */
    public double computeArea() {
        return Math.PI * radius * radius;
    }

    /**
     * Compute the circumference (c = 2 * pi * r)
     * @return The circumference
     */
    public double computePerimeter() {
        return 2 * Math.PI * radius;
    }

    /** Read the attributes of the rectangle */
    public void readShapeData() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the radius of the Circle");
        radius = in.nextDouble();
        in.close();
    }

    @Override
    public String toString() {
        return super.toString() + ": radius is " + radius;
    }
}