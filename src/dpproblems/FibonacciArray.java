package dpproblems;

public class FibonacciArray {

	
	//获取第index个斐波那契数
	public static int indexOf(int index)
	{
		int[] array = new int[index+1];
		array[0]=0;
		array[1]=1;
		for(int i=2;i<array.length;i++)
		{
			array[i] = array[i-2]+array[i-1];
		}
		return array[index];
		
	}
	
	public static int indexOf2(int index)
	{
		
		return 0;
	}

}
