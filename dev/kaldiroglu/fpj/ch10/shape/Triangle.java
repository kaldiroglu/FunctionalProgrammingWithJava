package dev.kaldiroglu.fpj.ch10.shape;

public record Triangle(int a, int b, int c) implements Shape{
    private static int count;

    public Triangle{
        ++count;
        System.out.println("Number of all triangles: " + count);
    }

     Triangle(int a, int b, int c, String message){
        this(a, b, c); // Call to a canonical constructor
//        this.a = a;
//        this.b = b;
//        this.c = c;
        System.out.println("Message: " + message);
    }

    public Triangle(int a, int b, int c, String message1, String message2){
        this(a, b, c, message1); // Call to a non-canonical constructor
        System.out.println("Message: " + message2);
    }

    @Override
    public double calculateArea() {
        double s = (a + b + c) / 2.0;
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        return area;
    }

    @Override
    public double calculateCircumference() {
        return a + b + c;
    }

    public static int count(){
        return count;
    }
}
