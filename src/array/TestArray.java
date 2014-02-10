package array;

public class TestArray {
	private char[] chars1 = {'H','e','l','l','o',',','w','o','r','l','d','!'};
	
	/*

		题目：输入一个整形数组，数组里有正数也有负数。数组中连续的一个或多个整数组成一个子数组，每个子数组都有一个和。求所有子数组的和的最大值。要求时间复杂度为O(n)。
		例如输入的数组为1, -2, 3, 10, -4, 7, 2, -5，和最大的子数组为3, 10, -4, 7, 2，因此输出为该子数组的和18。
	
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
		题目：输入一个已经按升序排序过的数组和一个数字，在数组中查找两个数，使得它们的和正好是输入的那个数字。要求时间复杂度是O(n)。如果有多对数字的和等于输入的数字，输出任意一对即可。

		例如输入数组1、2、4、7、11、15和数字15。由于4+11=15，因此输出4和11
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
		
		题目：输入一个整数数组，调整数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。要求时间复杂度为O(n)。
	 
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
		
		位运算面试题：一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
	 
	 */
	public int findSingle()
	{
		int[] numbers = {1,3,3,4,4,5,5};
		int result = 0;
		
		for(int index=0;index<numbers.length;index++)
		{
			result ^= numbers[index]++;
			
		}
		
		return result;
		
	}
	/*
		
		题目：从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2-10为数字本身，A为1，J为11，Q为12，K为13，而大小王可以看成任意数字。
	 
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
		
		题目：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个排好序的数组的一个旋转，输出旋转数组的最小元素。例如数组{3, 4, 5, 1, 2}为{1, 2, 3, 4, 5}的一个旋转，该数组的最小值为1。
	
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
		
		题目：数组中有一个数字出现的次数超过了数组长度的一半，找出这个数字。
	 
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
		题目：输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。 
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
		
		题目：某公司有几万名员工，请完成一个时间复杂度为O(n)的算法对该公司员工的年龄作排序，可使用O(1)的辅助空间。
	
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
		
		JudgeEven judge = new JudgeEven();
		ta.arrangeByOddAndEven(judge);
		
		System.out.println("Single is "+ta.findSingle());
		
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
