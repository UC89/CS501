public class recurseMultiplyTest
{
	public static int recurseMultiply(int i, int j)
	{
		if (i==1)
			return j;
		else if (j==1)
			return i;
		else if(i==0||j==0)
			return 0;
		if(i<j)
			return j + recurseMultiply(i-1,j);
		else if(j<=i)
			return i + recurseMultiply(i,j-1);
		return 0;
	}
	public static void main(String[] args)
	{
			System.out.println("Testing recursive multiplication");
			System.out.println("1*1 = "+recurseMultiply(1,1));
			System.out.println("4*49 = "+recurseMultiply(4,49));
	}
}