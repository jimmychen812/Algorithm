package sort;

import org.junit.Test;

public class SortTest {

	public void heapSort(int[] numbers)
	{
		
		//build heap
		for(int index=numbers.length/2-1;index>=0;index--)
		{
			heapAdjust(numbers,index,numbers.length);
		}
		for(int index=numbers.length;index>0;index--)
		{
			heapSwap(numbers,0,index-1);
			heapAdjust(numbers,0,index-1);
		}
		
	}
	
	private void heapAdjust(int[] numbers,int index,int size)
	{
		//adjust non-leaf node only
		if(index<size/2)
		{
			boolean hasLeft = (2*index+1)<size;
			boolean hasRight = (2*index+2)<size;
			int lastIndex = -1;
			int biggerChild = index;
			
			if(!hasLeft&&!hasRight)
			{
				//impossible to be here.
				return;
			}
			
			if(hasLeft&&numbers[2*index+1]>numbers[biggerChild])
			{
				biggerChild = 2*index+1;
			}
			if(hasRight&&numbers[2*index+2]>numbers[biggerChild])
			{
				biggerChild = 2*index+2;
			}
			
			if(biggerChild!=index)
			{
				heapSwap(numbers,biggerChild,index);
				
				lastIndex = biggerChild;
			}
			
			
			if(lastIndex!=-1)
			{
				heapAdjust(numbers,lastIndex,size);
			}
		}
		
		
	}
	private void heapSwap(int[] numbers, int from, int to)
	{
		int temp = numbers[from];
		numbers[from] = numbers[to];
		numbers[to] = temp;

	}
	
	
	public void quickSort(int[] numbers,int start,int end)
	{
		if(start<end)
		{
			int i = start, j = end;
			int key = numbers[i];
			while(i!=j)
			{
				while(i!=j)
				{
					if(numbers[j]<=key)
					{
						numbers[i] = numbers[j];
						i++;
						break;
					}
					else
					{
						j--;
					}
				}
				
				while(i!=j)
				{
					if(numbers[i]>key)
					{
						numbers[j]=numbers[i];
						j--;
						break;
					}
					else
					{
						i++;
					}
				}

			}
			numbers[i]=key;
			
			//print
			print(numbers);
			
			quickSort(numbers,start,i-1);
			quickSort(numbers,i+1,end);
		}
		
	}
	
	
	/*
	 * �鲢�����Ч���ǱȽϸߵģ������г�ΪN�������зֿ���С����һ��ҪlogN����ÿ������һ���ϲ��������еĹ��̣�ʱ�临�Ӷȿ��Լ�ΪO(N)����һ��ΪO(N*logN)��
	 * ��Ϊ�鲢����ÿ�ζ��������ڵ������н��в��������Թ鲢������O(N*logN)�ļ������򷽷����������򣬹鲢����ϣ�����򣬶�����Ҳ��Ч�ʱȽϸߵġ�
	 */
	public void mergeSort(int[] numbers,int start,int end,int[] result)
	{
		if(start<end)
		{
			int mid = (start+end)/2;
			
			mergeSort(numbers,start,mid,result);
			mergeSort(numbers,mid+1,end,result);
			
			mergeArray(numbers,start,mid,end,result);
		}
		
		
	}
	private void mergeArray(int[]numbers,int start,int mid,int end,int[] result)
	{
		int start1 = start, end1 = mid;
		int start2 = mid+1, end2 = end;
		int index = start;
		while(start1<=end1&&start2<=end2)
		{
			if(numbers[start1]<=numbers[start2])
			{
				result[index++] = numbers[start1++];
			}
			else
			{
				result[index++] = numbers[start2++];
			}
		}
		
		while(start1<=end1)
		{
			result[index++] = numbers[start1++];
		}
		
		while(start2<=end2)
		{
			result[index++] = numbers[start2++];
		}
		
	}
	
	//@Test
	public void test_heapSort()
	{
		int[] numbers={16,7,3,20,17,8};
		heapSort(numbers);
		
		//print
		print(numbers);
	}
	
	@Test
	public void test_quickSort()
	{
		//int[] numbers={16,7,3,20,17,8};
		int[] numbers={48,6,57,88,60,42,83,73,88,85};
		
		quickSort(numbers,0,numbers.length-1);
		
		//print
		print(numbers);
	}
	
	@Test
	public void test_mergeSort()
	{
		int[] numbers={48,6,57,88,60,42,83,73,88,85};
		int[] result = new int[numbers.length];
		
		mergeSort(numbers,0,numbers.length-1,result);
		
		
		//print
		print(result);
	}
	
	private void print(int[] numbers)
	{
		for(int i=0;i<numbers.length;i++)
		{
			System.out.print(numbers[i]+" ");
		}
		System.out.println();
	}

}