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
		int last1 = 0, last2 = 1;
		int i = 2;
		int result = 0;
		
		if(index==0)
		{
			return last1;
		}
		else if(index==1)
		{
			return last2;
		}
		else
		{
			do
			{
				result = last1 + last2;
				last1 = last2;
				last2 = result;
				
				
			}while(++i<=index);
			
			return result;
		}
		
	}
	
	
	public static void main(String[] args)
	{
		System.out.println(indexOf2(6));
	}
	

}
