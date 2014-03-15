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
			int max = numbers[index];
			int changed = index;
			int leftChildIndex = index*2+1;
			int rightChildIndex = index*2+2;
			if(leftChildIndex<size&&max<numbers[leftChildIndex])
			{
				max = numbers[leftChildIndex];
				changed = leftChildIndex;
			}
			if(rightChildIndex<size&&max<numbers[rightChildIndex])
			{
				max = numbers[rightChildIndex];
				changed = rightChildIndex;
			}
			
			if(changed!=index)
			{
				int temp = numbers[index];
				numbers[index] = numbers[changed];
				numbers[changed] = temp;
				
				
				heapAdjust(numbers,changed,size);
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
	 * 归并排序的效率是比较高的，设数列长为N，将数列分开成小数列一共要logN步，每步都是一个合并有序数列的过程，时间复杂度可以记为O(N)，故一共为O(N*logN)。
	 * 因为归并排序每次都是在相邻的数据中进行操作，所以归并排序在O(N*logN)的几种排序方法（快速排序，归并排序，希尔排序，堆排序）也是效率比较高的。
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
	
	public static void bubbleSort(int[] numbers)
	{
		int start=0,end=numbers.length-1;
		for(int i=end;i>start;i--)
		{
			for(int j=start;j<i;j++)
			{
				if(numbers[j]>numbers[j+1])
				{
					int temp = numbers[j];
					numbers[j] = numbers[j+1];
					numbers[j+1] = temp;
				}
			}
		}
	}
	
	@Test
	public void test_heapSort()
	{
		int[] numbers={16,7,3,20,17,8};
		heapSort(numbers);
		
		//print
		print(numbers);
	}
	
	//@Test
	public void test_quickSort()
	{
		//int[] numbers={16,7,3,20,17,8};
		int[] numbers={48,6,57,88,60,42,83,73,88,85};
		
		quickSort(numbers,0,numbers.length-1);
		
		//print
		print(numbers);
	}
	
	//@Test
	public void test_mergeSort()
	{
		int[] numbers={48,6,57,88,60,42,83,73,88,85};
		int[] result = new int[numbers.length];
		
		mergeSort(numbers,0,numbers.length-1,result);
		
		
		//print
		print(result);
	}
	
	//@Test
	public void test_bubbleSort()
	{
		int[] numbers={48,6,57,88,60,42,83,73,88,85};
		bubbleSort(numbers);
		
		print(numbers);
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
