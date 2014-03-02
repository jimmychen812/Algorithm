package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Graph {
	
	private static class GraphNode
	{
		private int value;
		public GraphNode(int value)
		{
			this.value = value;
		}
		
		public int getValue()
		{
			return value;
		}
		
		@Override
		public String toString()
		{
			return String.valueOf(value);
		}
	}
	
	public static GraphNode[][] createAdjacencyListFromAdjancencyMatrix(int[][] adjacencyList)
	{
		GraphNode[][] list = new GraphNode[adjacencyList.length][];
		for(int j=0;j<adjacencyList.length;j++)
		{
			int total=0;
			for(int i=0;i<adjacencyList[j].length;i++)
			{
				if(adjacencyList[j][i]!=0)
				{
					total++;
				}
			}
			
			list[j] = new GraphNode[total];
			
			int index=0;
			
			for(int i=0;i<adjacencyList[j].length;i++)
			{
				if(adjacencyList[j][i]!=0)
				{
					list[j][index++]=new GraphNode(i);
				}
			}
			
			
		}
		
		return list;
		
	}
	
	public static int[][] createAdjacencyMatrixFromAdjacencyList(GraphNode[][] list)
	{
		int size = list.length;
		int[][] matrix = new int[size][size];
		for(int i=0;i<list.length;i++)
		{
			for(int j=0;j<list[i].length;j++)
			{
				matrix[i][list[i][j].value] = 1;
			}
		}
		
		return matrix;
	}
	
	private static void printAdjancencyMatrix(int[][] matrix)
	{
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix[i].length;j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void printAdjancencyList(GraphNode[][] list)
	{
		for(int i=0;i<list.length;i++)
		{
			System.out.print("Node "+i+":");
			for(int j=0;j<list[i].length;j++)
			{
				System.out.print(list[i][j]+" ");
			}
			
			System.out.println();
		}
	}
	
	public static void DFSAdjancencyList(GraphNode[][] list)
	{
		int[] visited = new int[list.length];
		
		for(int index=0;index<list.length;index++)
		{
			if(visited[index]==0)
			{
				visited[index]=1;
				System.out.print(index+" ");
				
				int target = index;
				do
				{
					for(int i=0;i<list[target].length;i++)
					{
						if(visited[list[target][i].value]==0)
						{
							visited[list[target][i].value] = 1;
							
							System.out.print(list[target][i].value+" ");
							
							target = list[target][i].value;
							
							continue;
						}
					}
					target=-1;
					
				}while(target!=-1);	
				
			}
			
		}
		
		
	}
	
	public static void DFSAdjancencyMatrix(int[][] matrix)
	{
		int[] visited = new int[matrix.length];
		
		for(int index=0;index<matrix.length;index++)
		{
			if(visited[index]==0)
			{
				visited[index]=1;
				
				System.out.print(index+" ");
				
				int target = index;
				do
				{
					for(int i=0;i<matrix[target].length;i++)
					{
						if(matrix[target][i]!=0&&visited[matrix[target][i]]==0)
						{
							visited[matrix[target][i]]=1;
							
							System.out.print(matrix[target][i]+" ");
							
							target = matrix[target][i];
							
							
							
							continue;
						}
						
					}
					
					target = -1;
					
					
				}while(target!=-1);
				
				
				
			}
			
			
			
		}
		
		
	}
	
	
	public static int getMinCost(int[][] matrix,boolean directed)
	{
		int cost = 0;
		
		final class Edge implements Comparable<Edge>
		{
			private int start;
			private int end;
			private int cost;
			private boolean directed;
			public Edge(int start,int end,int cost,boolean directed)
			{
				this.start = start;
				this.end = end;
				this.cost = cost;
				this.directed = directed;
			}
			@Override
			public int compareTo(Edge o) {
				if(cost>o.cost)
				{
					return 1;
				}
				else if(cost<o.cost)
				{
					return -1;
				}
				return 0;
			}
			
			@Override
			public boolean equals(Object o)
			{
				if(o instanceof Edge)
				{
					Edge e = (Edge)o;
					if(directed)
					{
						return start==e.start&&end==e.end&&cost==e.cost;
					}
					else
					{
						return cost==e.cost&&(start==e.start&&end==e.end||start==e.end&&end==e.start);
					}
					
				}
				return false;
			}
			
			@Override
			public int hashCode()
			{
				return start^end^cost;
			}
			
			@Override
			public String toString()
			{
				return "from:"+start+"end:"+end+"cost:"+cost;
			}
		};
		
		ArrayList<Edge> edges = new ArrayList<Edge>();
		ArrayList<Edge> result = new ArrayList<Edge>();
		
		if(directed)
		{
			for(int i=0;i<matrix.length;i++)
			{
				for(int j=0;j<matrix.length;j++)
				{
					edges.add(new Edge(i,j,matrix[i][j],directed));
				}
			}
		}
		else
		{
			for(int i=0;i<matrix.length;i++)
			{
				for(int j=i+1;j<matrix.length;j++)
				{
					edges.add(new Edge(i,j,matrix[i][j],directed));
				}
				
				
			}
		}
		
		Collections.sort(edges);
		int target = matrix.length-1;
		for(Edge edge:edges)
		{
			int start = edge.start;
			int end = edge.end;
			
			ArrayList<Integer> nodeConnectedToStart = new ArrayList<Integer>();
			for(Edge resultedge:result)
			{
				if(resultedge.start==start)
				{
					nodeConnectedToStart.add(resultedge.end);
				}
				else if(resultedge.end==start)
				{
					nodeConnectedToStart.add(resultedge.start);
				}
			}
			
			ArrayList<Integer> nodeConnectedToEnd = new ArrayList<Integer>();
			for(Edge resultedge:result)
			{
				if(resultedge.start==end)
				{
					nodeConnectedToEnd.add(resultedge.end);
				}
				else if(resultedge.end==end)
				{
					nodeConnectedToEnd.add(resultedge.start);
				}
			}
			
			boolean hasCommon = false;
			for(Integer i:nodeConnectedToStart)
			{
				if(nodeConnectedToEnd.contains(i))
				{
					hasCommon = true;
					break;
				}
			}
			
			if(!hasCommon)
			{
				result.add(new Edge(start,end,edge.cost,directed));
				cost += edge.cost;
				target--;
				if(target==0)
				{
					break;
				}
				
			}
			
		}
		
		return cost;
	}
	public static void getPath(int[][] matrix,int start,int end)
	{
		int[][] path = getShortestPath(matrix);
		
		
		findPath(path,start,end);
	}
	
	private static int[][] getShortestPath(int[][] matrix)
	{
		int[][] dist = new int[matrix.length][matrix.length];
		int[][] path = new int[matrix.length][matrix.length];
		
		for(int i=0;i<dist.length;i++)
		{
			for(int j=0;j<dist[i].length;j++)
			{
				dist[i][j] = matrix[i][j];
				
				path[i][j] = -1;
			}
		}
		
		for(int k=0;k<dist.length;k++)
		{
			for(int i=0;i<dist.length;i++)
			{
				for(int j=0;j<dist.length;j++)
				{
					if(dist[i][k]!=Integer.MAX_VALUE&&
							dist[k][j]!=Integer.MAX_VALUE&&
							dist[i][k]+dist[k][j]<dist[i][j])
					{
						dist[i][j] = dist[i][k]+dist[k][j];
						path[i][j] = k;
					}	
				}
			}
		}
		
		
		
		return path;
	}
	private static void findPath(int[][] path,int start,int end)
	{
		int k = path[start][end];
		if(k==-1)
		{
			return;
		}
		findPath(path,start,k);
		
		System.out.print(k+" ");
		
		findPath(path,k,end);
		
	}
	
	
	
	public static void main(String[] args)
	{
		int[][] adjacencyList ={{0,1,0,0,1},{1,0,0,1,0},{0,0,0,0,1},{1,0,0,0,0},{0,0,0,0,0}};
		
		
		GraphNode[][] list = Graph.createAdjacencyListFromAdjancencyMatrix(adjacencyList);
		Graph.printAdjancencyList(list);
		
		
		int[][] adjacencyList2 = createAdjacencyMatrixFromAdjacencyList(list);
		printAdjancencyMatrix(adjacencyList2);
		
		
		DFSAdjancencyList(list);
		System.out.println();
		DFSAdjancencyMatrix(adjacencyList);
		
		
		
		
		int[][] path={
				{0,4,9,21},
				{4,0,8,17},
				{9,8,0,16},
				{21,17,16,0},
				};
		
		System.out.println("cost "+getMinCost(path,false));
		
	}
}
