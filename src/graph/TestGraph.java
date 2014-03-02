package graph;

import org.junit.Test;

public class TestGraph {
	
	@Test
	public void test_getPath()
	{
		int[][] distance={
				{0,10,Integer.MAX_VALUE,1,Integer.MAX_VALUE},
				{Integer.MAX_VALUE,0,20,Integer.MAX_VALUE,Integer.MAX_VALUE},
				{Integer.MAX_VALUE,Integer.MAX_VALUE,0,Integer.MAX_VALUE,Integer.MAX_VALUE},
				{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0,2},
				{Integer.MAX_VALUE,Integer.MAX_VALUE,3,Integer.MAX_VALUE,0}};
		
		int start = 0, end = 2;
		
		System.out.println("the path between "+start+" and +"+end+" is:");
		System.out.print(start+" ");
		Graph.getPath(distance, 0, 2);
		System.out.print(end+" ");
	}
}
