package disjointset;

public class DisjointSet {
	private final int set[];
	private final int total;
	
	public DisjointSet(int total)
	{
		set = new int[total+1];
		this.total = total+1;
	}
	public void init()
	{
		for(int index=1;index<total;index++)
		{
			this.set[index] = index;
		}
	}
	//压缩路径
	//最好情况：O(1)
	//最坏情况：第一次O(n)，第二次O(1)
	public int find(int element)
	{
		int temp = element;
		while(set[temp]!=temp)
		{
			temp = set[temp];
		}
		int root = temp;
		temp = element;
		int temp2;
		while(set[temp]!=temp)
		{
			temp2 = set[temp];
			set[temp] = root;
			temp = temp2;
		}

		return root;
	}
	public int getHeight(int element)
	{
		int height = 1;
		int temp = element;
		while(set[temp]!=temp)
		{
			temp = set[temp];
			height++;
		}
		return height;
	}
	public void merge(int set1,int set2)
	{
		int height1 = getHeight(set1);
		int height2 = getHeight(set2);
		if(height1<height2)
		{
			set[set1]=set2;
		}
		else
		{
			set[set2]=set1;
		}
	}
	public int getGroup()
	{
		int total = 0;
		for(int index=1;index<set.length;index++)
		{
			if(set[index]==index)
			{
				total++;
			}
		}
		return total;
	}
	public void print()
	{
		for(int index=1;index<set.length;index++)
		{
			System.out.print(set[index]+" ");
		}
		
	}
	
	public static void main(String[] args)
	{
		//friends groups
		DisjointSet friends = new DisjointSet(5);
		friends.init();
		int[][] relations = new int[][]{{1,2},{2,3},{4,5}};
		for(int index=0;index<relations.length;index++)
		{
			int[] pair = relations[index];
			friends.merge(pair[0],pair[1]);
		}
		friends.print();
		
		System.out.println("There are "+friends.getGroup()+" group(s)");
	}
	
}
