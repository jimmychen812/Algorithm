package array;

import org.junit.Test;

public class TestArray {
	private char[] chars1 = {'H','e','l','l','o',',','w','o','r','l','d','!'};
	
	/*

		��Ŀ������һ���������飬������������Ҳ�и�����������������һ�������������һ�������飬ÿ�������鶼��һ���͡�������������ĺ͵����ֵ��Ҫ��ʱ�临�Ӷ�ΪO(n)��
		�������������Ϊ1, -2, 3, 10, -4, 7, 2, -5��������������Ϊ3, 10, -4, 7, 2��������Ϊ��������ĺ�18��
	
	*/
	public int getMax()
	{
		//int[] numbers = {1, -2, 3, 10, -4, 7, 2, -5};
		
		int[] numbers = {-1, -2, -3, -10, -4, -7, -2, -5};
		
		int max = -1;
		int total = 0;
		for(int index=0;index<numbers.length;index++)
		{
			total += numbers[index];
			if(total<0)
			{
				total = 0;
				continue;
			}
			else
			{
				if(max<total)
				{
					max = total;
				}
			}
		}
		
		//There is no positive number in this array
		
		if(max==-1)
		{
			max = numbers[0];
			int index = 1;
			
			while(index<numbers.length)
			{
				if(max<numbers[index])
				{
					max = numbers[index];
				}
				index++;
				
				
			}
			
		}
		
		
		return max;
	}
	
	/*
	 * ��̬�滮��
	 * f(0)=a[0]
	 * f(i)=max{f(i-1)+a[i],a[i]} i>=1 ������a(0),a(1),...a(i)�����ֵ
	 * maxvalue = max{f(0),f(1),...,f(n)}
	*/
	public int getMax2(int[] numbers)
	{
		
		int max = numbers[0];
		int total = numbers[0];

		for(int index=1;index<numbers.length;index++)
		{
			total = total+numbers[index];
			if(numbers[index]>total)
			{
				total = numbers[index];

			}
			if(total>max)
			{
				max = total;

			}

		}
		
		System.out.println("max value is "+max);

		return max;
	}
	
	/*
	 * 
	 * ��һ������A{a0,a1,a2,a3...,an-1}(ai>0,0<=i<=n-1)�������У�Ҫ����������ÿ��Ԫ�ز������ڣ�����ʹ������
	 * f(0)=0
	 * g(0)=a[0]
	 * f(i)=max{f(i-1),g(i-1)},i>=1 ��ѡ��iԪ�ص������еĺ͵����ֵ
	 * g(i)=f(i-1)+a[i] i>=1 ѡ��iԪ�ص������еĺ͵����ֵ
	 */
	public int getMax3(int[] numbers)
	{
		//numbers = new int[]{1, -2, 3, 10, -4, 7, 2, -5};
		
		int max = numbers[0];
		int f = 0,g=numbers[0];
		for(int index=1;index<numbers.length;index++)
		{
			int temp = f;
			f = f>g?f:g;
			g = temp+numbers[index];
			
			int bigger = g;
			if(f>g)
			{
				bigger = f;
			}
			if(bigger>max)
			{
				max = bigger;
			}
			
		}
		
		
		return max;
	}
	
	public int binarySearch(int[] numbers, int key)
	{
		
		//return binarySearch(numbers,0,numbers.length-1,key);
		return binarySearch2(numbers,0,numbers.length-1,key);
	}
	//Recursion
	private int binarySearch(int[] numbers,int start,int end,int key)
	{
		int mid = (end+start)>>>1;
		
		if(numbers[mid]==key)
		{
			return mid;
		}
		
		int result = -1;
		
		if(mid!=start)
		{
			result = binarySearch(numbers,start,mid-1,key);
		}
		
		if(result==-1 && mid!=end)
		{
			result = binarySearch(numbers,mid+1,end,key);
		}
		
		return result;

	}
	
	private int binarySearch2(int[] numbers,int start,int end,int key)
	{
		
		while(start<=end)
		{
			int mid = (start+end)>>>1;
			int midValue = numbers[mid];
			
			if(midValue<key)
			{
				start = mid+1;
			}
			else if(midValue>key)
			{
				end = mid-1;
			}
			else
			{
				return mid;
			}
			
			
		}
		return -(start+1);
	}
	
	/*
		��Ŀ������һ���Ѿ�������������������һ�����֣��������в�����������ʹ�����ǵĺ�������������Ǹ����֡�Ҫ��ʱ�临�Ӷ���O(n)������ж�����ֵĺ͵�����������֣��������һ�Լ��ɡ�

		������������1��2��4��7��11��15������15������4+11=15��������4��11
	*/
	public int[] getAugendAndAddend()
	{
		int[] numbers = {1,2,4,7,11,15};
		int key = 15;
		
		
		int[] result = new int[2];
		
		int start = 0,end = numbers.length-1;
		while(start!=end)
		{
			int sum = numbers[start]+numbers[end];
			if(sum<key)
			{
				start++;
			}
			else if(sum>key)
			{
				end--;
			}
			else
			{
				result[0] = numbers[start];
				result[1] = numbers[end];
				break;
			}
		}
		
		
		return result;
	}
	
	
	/*
		
		��Ŀ������һ���������飬�������������ֵ�˳��ʹ����������λ�������ǰ�벿�֣�����ż��λ������ĺ�벿�֡�Ҫ��ʱ�临�Ӷ�ΪO(n)��
	 
	 */

	public int[] arrangeByOddAndEven(Judge judge)
	{
		int[] numbers = {1,2,4,7,11,15};
		
		
		int start = 0,end = numbers.length-1;
		int temp = 0;
		while(start<end)
		{
			while(start<end)
			{

				if(!judge.isSatisfied(numbers[start]))
				{
					start++;
				}
				else
				{
					break;
				}
			}
			while(start<end)
			{
				if(judge.isSatisfied(numbers[end]))
				{
					end--;
				}
				else
				{
					break;
				}
			}
			if(start<end)
			{
				temp = numbers[start];
				numbers[start] = numbers[end];
				numbers[end] = temp;
			}
		}
		for(int index=0;index<numbers.length;index++)
		{
			System.out.print(numbers[index]+" ");
		}
		return numbers;
	}
	
	/*
		
		λ���������⣺һ�����������������������֮�⣬���������ֶ����������Ρ���д�����ҳ�������ֻ����һ�ε����֡�Ҫ��ʱ�临�Ӷ���O(n)���ռ临�Ӷ���O(1)��
	 
	 */
	public int findSingle(int[] numbers)
	{
		//int[] numbers = {1,3,3,4,4,5,5};
		int result = 0;
		
		for(int index=0;index<numbers.length;index++)
		{
			result ^= numbers[index];
			
		}
		
		return result;
		
	}
	
	public int[] findTwoSingles(int[] numbers)
	{
		int[] result = new int[2];
		int total = 0;
		
		for(int index=0;index<numbers.length;index++)
		{
			total ^= numbers[index];
			
		}
		
		int test = 1;
		int offset = 0;
		while(offset<32)
		{
			if( (test<<offset&total)!=0 )
			{
				if( ((~(test<<offset))&total)==0 )
				{
					
				}
				else
				{
					break;
				}
			}
			else
			{
				offset++;
			}
		}
		if(offset==32)
		{
			return null;
		}
		int value = test<<offset;
		
		for(int index=0;index<numbers.length;index++)
		{
			if( (numbers[index]&value)==0 )
			{
				result[0]^=numbers[index];
			}
			else
			{
				result[1]^=numbers[index];
			}
			

		}
		
		
		
		return result;
	}
	/*
		
		��Ŀ�����˿����������5���ƣ��ж��ǲ���һ��˳�ӣ�����5�����ǲ��������ġ�2-10Ϊ���ֱ���AΪ1��JΪ11��QΪ12��KΪ13������С�����Կ����������֡�
	 
	 */
	public boolean isStraight()
	{
		int[] cards = {5,4,2,-1,-1};//-1 means king
		
		int[] temp = new int[9];
		temp[4] = cards[0];
		int start=4,end=4;
		int kings = 0;
		for(int index=1;index<cards.length;index++)
		{
			if(cards[index]==-1)
			{
				kings++;
				continue;
			}
			
			int insert = 4-(temp[4]-cards[index]);
			if(insert>=0&&insert<=8)
			{
				if(temp[insert]!=0)
				{
					return false;
				}
				else
				{
					temp[insert]=cards[index];
					if(start>insert&&insert<end)
					{
						start = insert;
					}
					else if(end<insert)
					{
						end = insert;
					}
				}
			}
			else
			{
				return false;
			}
			
		}
		
		if(end-start+1<=5)
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	/*
		
		��Ŀ����һ�������ʼ�����ɸ�Ԫ�ذᵽ�����ĩβ�����ǳ�֮Ϊ�������ת������һ���ź���������һ����ת�������ת�������СԪ�ء�
		��������{3, 4, 5, 1, 2}Ϊ{1, 2, 3, 4, 5}��һ����ת�����������СֵΪ1��
	
	*/
	public int getMinFromRotatedArray()
	{
		int[] numbers = {3, 4, 5, 1, 2};

		int temp = numbers[0];
		
		for(int index=1;index<numbers.length;index++)
		{
			if(numbers[index]>temp)
			{
				temp = numbers[index];
			}
			else
			{
				return numbers[index];
			}
		}
		
		return numbers[0];
		
	}
	
	/*
		
		��Ŀ����������һ�����ֳ��ֵĴ������������鳤�ȵ�һ�룬�ҳ�������֡�
	 
	 */
	public int getMoreThanHalfNumber()
	{
		int[] numbers = {3, 3, 3, 3, 3, 1, 2, 4, 5};
		
		int result = numbers[0];
		int total = 1;
		for(int index=1;index<numbers.length;index++)
		{
			if(result!=numbers[index])
			{
				total--;
				if(total==0)
				{
					total = 1;
					result = numbers[index];
				}
				
			}
			else
			{
				total++;
			}
		}
		
		//TODO check if the result is valid
		return result;
	}
	
	/*
		��Ŀ������һ�����󣬰��մ���������˳ʱ���˳�����δ�ӡ��ÿһ�����֡� 
	*/
	public void printMatrix()
	{
		//int[][] matrix=new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
		//int[][] matrix=new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		
		//int[][] matrix=new int[][]{{1,2,3,4,5}};//1*5
		int[][] matrix=new int[][]{{1},{2},{3},{4},{5}};//5*1
		
		int XStart = 0,XEnd = matrix[0].length-1;
		int YStart = 0,YEnd = matrix.length-1;
		
		
		if(XStart==XEnd)
		{
			for(int index=YStart;index<=YEnd;index++)
			{
				System.out.print(matrix[index][XStart]+" ");
			}
			return;
		}
		
		if(YStart==YEnd)
		{
			for(int index=XStart;index<=XEnd;index++)
			{
				System.out.print(matrix[YStart][index]+" ");
			}
			return;
		}
		
		
		while(XStart<=XEnd && YStart<=YEnd)
		{

			for(int index=XStart;index<=XEnd;index++)
			{
				System.out.print(matrix[YStart][index]+" ");
			}
			YStart++;

			
			for(int index=YStart;index<=YEnd;index++)
			{
				System.out.print(matrix[index][XEnd]+" ");
			}
			XEnd--;
			for(int index=XEnd;index>=XStart;index--)
			{
				System.out.print(matrix[YEnd][index]+" ");
			}
			YEnd--;
			for(int index=YEnd;index>=YStart;index--)
			{
				System.out.print(matrix[index][XStart]+" ");
			}
			XStart++;

		}
	
	
	
	}
	/*
		
		��Ŀ��ĳ��˾�м�����Ա���������һ��ʱ�临�Ӷ�ΪO(n)���㷨�Ըù�˾Ա�������������򣬿�ʹ��O(1)�ĸ����ռ䡣
	
	*/
	public void sortArray()
	{
		int[] numbers = {23, 23, 23, 23, 23, 21, 22, 24, 25,30,30,40,40,40,40,30};
		int[] result = new int[101];
		
		for(int index=0;index<numbers.length;index++)
		{
			if(numbers[index]<result.length&&result[numbers[index]]==0)
			{
				result[numbers[index]]=numbers[index];
			}
		}
		
		for(int index=0;index<result.length;index++)
		{
			if(result[index]!=0)
			{
				System.out.print(result[index]+" ");
			}
		}
		
	}
	
	public void reversWords()
	{
		//char[] sentence = {'I',' ','a','m',' ','r','o','b','e','r','t','!'};
		//char[] sentence = {' ',' ','a','m',' ','r','o','b','e','r','t','!'};
		char[] sentence = {'I',' ','a','m',' ','r','o','b','e','r','t','!',' ',' '};
		char temp=0;
		for(int start=0,end=sentence.length-1;start<(sentence.length/2);start++,end--)
		{
			temp = sentence[end];
			sentence[end] = sentence[start];
			sentence[start] = temp;
		}
		
		int index=0;
		int start = 0;
		int end = 0;
		while(index<sentence.length)
		{
			while(sentence[index]==' ')
			{
				index++;
				
				if(index>=sentence.length)
				{
					for(int i=0;i<sentence.length;i++)
					{
						System.out.print(sentence[i]+" ");
					}
					
					return;
				}
			}
			start = index;
			
			
			while(sentence[index]!=' ')
			{
				index++;
				
				if(index>=sentence.length)
				{
					break;
				}
			}
			end = index-1;
			
			for(int i=start,j=end;i<start+(end-start+1)/2;i++,j--)
			{
				temp = sentence[i];
				sentence[i] = sentence[j];
				sentence[j] = temp;
			}
			
			
			index++;
		}
		
		
		for(int i=0;i<sentence.length;i++)
		{
			System.out.print(sentence[i]+" ");
		}
		
	}
	
	public char findFirstSingle()
	{
		char[] chars = {'a','b','a','c','c','d','e','f','f'};
		int[] result = new int[26];
		for(int index=0;index<chars.length;index++)
		{
			result[chars[index]-'a']++;	
		}
		
		for(int index=0;index<result.length;index++)
		{
			if(result[index]==1)
			{
				return (char) ('a'+index);
			}
		}
		return 0;
		
	}
	
	public byte toInteger(char[] numbers) throws Exception
	{
		boolean positive = !(numbers[0]=='-');
		byte integer = (byte) (positive?0:-0);

		for(int index=0,i=numbers.length-1;i>=0;i--,index++)
		{
			if(numbers[i]>='0'&&numbers[i]<='9')
			{
				byte add = (byte) ((byte)(numbers[i]-'0')*Math.pow(10, index));
				if(positive)
				{
					if(add>Byte.MAX_VALUE-integer)
					{
						throw new Exception();
					}
					
				}
				else
				{
					if(add>integer-Byte.MIN_VALUE)
					{
						throw new Exception();
					}
				}
				
				if(positive)
				{
					integer += add;
				}
				else
				{
					integer -= add;
				}
				
				
				
				
			}
			else
			{
				if(numbers[i]=='-'&&i==0||numbers[i]=='+'&&i==0)
				{
				}
				else
				{
					throw new Exception();
				}
			}
			
			
			
		}
		return integer;
	}
	
	public void moveToEnd(char[] chars,int k)
	{
		if(k==chars.length)
		{
			return;
		}
		char temp = 0;
		for(int start=0,end=k-1;start<0+(k-1-0+1)/2;start++,end--)
		{
			temp = chars[start];
			chars[start] = chars[end];
			chars[end] = temp;
		}
		for(int start=k,end=chars.length-1;start<k+(chars.length-1-k+1)/2;start++,end--)
		{
			temp = chars[start];
			chars[start] = chars[end];
			chars[end] = temp;
		}
		
		for(int start=0,end=chars.length-1;start<0+(chars.length-1-0+1)/2;start++,end--)
		{
			temp = chars[start];
			chars[start] = chars[end];
			chars[end] = temp;
		}
		
		
		//print
		for(int index=0;index<chars.length;index++)
		{
			System.out.print(chars[index]+" ");
		}
	}
	
	public int calculator(String str)
	{
		int total = 0;
		for(int index=str.length()-1;index>=0;index--)
		{
			int number1=0;
			int number2=0;
			int offset = 0;
			
			char c = str.charAt(index);
			if(c>='0'&&c<='9')
			{
				
			}
			
			
		}
		
		return total;
	}
	
	//@Test
	public void test_getMax2()
	{
		int[] numbers = {1, -2, 3, 10, -4, 7, 2, -5};
		//int[] numbers = {-1, -2, -3, -10, -4, -7, -2, -5};
		getMax2(numbers);
	}
	
	//@Test
	public void test_getMax3()
	{
		int[] numbers = {1, -2, 3, 10, -4, 7, 2, -5};
		int max = getMax3(numbers);
		
		System.out.println(max);
	}
	
	@Test
	public void test_binarySearch()
	{
		int[] numbers = {1,2,3,4,5,6,7,8,9,10};
		
		int result = binarySearch(numbers,11);
		
		System.out.println(result);
	}
	
	//@Test
	public void test_findTwoSingles()
	{
		int[] numbers = {1,3,3,4,4,5,10,5};
		
		int[] result = findTwoSingles(numbers);
		
		System.out.println(result[0]+" "+result[1]);
	}
	
	public static void main(String[] args) throws Exception
	{
		TestArray ta = new TestArray();
		System.out.println("max is "+ta.getMax());
		
		int[] result = ta.getAugendAndAddend();
		if(result!=null)
		{
			System.out.println("Augend and Addend are "+result[0]+" "+result[1]);
		}
		else
		{
			System.out.println("Can't find a result");
		}
		
		
		System.out.println("Is straight "+ta.isStraight());
		
		System.out.println("minimum "+ta.getMinFromRotatedArray());
		
		System.out.println("more than half number is "+ta.getMoreThanHalfNumber());
		
		ta.printMatrix();
		
		ta.sortArray();
		
		ta.reversWords();
		
		ta.findFirstSingle();
		
		System.out.println(ta.toInteger(new char[]{'+','1','2','7'}));
		
		ta.moveToEnd(new char[]{'a','b','c','d','e','f','g'}, 2);
		
	}
}
