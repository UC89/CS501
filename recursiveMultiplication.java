//Recursive Multiplication    By: Taylor Somma      CS501

public class recursiveMultiplication
{
	public static int mult(int x, int y)
	{
		if (x==0 || y==0)
			return 0;
		else if (x==1)
			return y;
		else if (y==1)
			return x;
		else if (x<0 && y<0)
			return mult(Math.abs(x),Math.abs(y));
		else if (x<0)
			return x + mult(x, y-1);
		else if (y<0)
			return  y + mult(x-1,y);
		else if (x<y)
			return y + mult(x-1,y);
		else if (y<=x)
			return x + mult(x, y-1);
		else
			System.out.println("Invalid input");
			return 1;

	}
	public static void main(String[] args)
	{
		System.out.println("Multiply x: 0 y:   = " + mult(0,0));
		System.out.println("Multiply x: 1 y: 0  = " + mult(1,0));
		System.out.println("Multiply x: 0 y: 1  = " + mult(0,1));
		System.out.println("Multiply x: 1 y: 52  = " + mult(1,52));
		System.out.println("Multiply x: 32524 y: 1  = " + mult(32524,1));
		System.out.println("Multiply x: 10 y: 10  = " + mult(10,10));
		System.out.println("Multiply x: 10 y: 15  = " + mult(10,15));
		System.out.println("Multiply x: 30 y: 10  = " + mult(30,10));
		System.out.println("Multiply x: -1 y: 1  = " + mult(-1,1));
		System.out.println("Multiply x: 1 y: -1  = " + mult(1,-1));
		System.out.println("Multiply x: 1 y: -110  = " + mult(1,-110));
		System.out.println("Multiply x: -300 y: 1  = " + mult(-300,1));
		System.out.println("Multiply x: -10 y: 20  = " + mult(-10,20));
		System.out.println("Multiply x: 30 y: -20  = " + mult(30,-20));
		System.out.println("Multiply x: -30 y: -30  = " + mult(-30,-30));

	}
}