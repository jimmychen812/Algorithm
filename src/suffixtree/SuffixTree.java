package suffixtree;

import java.util.ArrayList;
import java.util.List;

public class SuffixTree {

	
	
	private int start;
	private int end;//if end equals 0, it means this node is a leaf
	private SuffixTree suffixlink;
	private List<SuffixTree> children = new ArrayList<SuffixTree>();
	
	
	private static final String text="abcabxabcd";
	private static SuffixTree root = new SuffixTree();
	private static int currentend = 0;
	private static State state = new State(root,(char)0,0);
	

	public SuffixTree()
	{

	}
	public SuffixTree(int start)
	{
		this.start = start;
	}
	public SuffixTree(int start,int end)
	{
		this.start = start;
		this.end = end;
	}
	
	public void buildSuffixTree()
	{
		StringBuilder insert = new StringBuilder();
		
		for(int index=0;index<text.length();index++)
		{
			char c = text.charAt(index);
			System.out.println("step "+(index+1)+" process "+c);
			
			SuffixTree lastNode = null;
			
			//implicitly update
			currentend++;
			
			insert.append(c);
			
			while(insert.length()>0)
			{
				String sub = insert.subSequence(0, insert.length()).toString();
				
				SuffixTree edge = inEdge(state,sub);
				//sub is in the edge, set the edge and increase the length
				if(edge!=null)
				{
					state.setActiveEdge(text.charAt(edge.start));
					state.setActiveLength(state.getActiveLength()+1);
					
					if( state.getActiveLength()==((edge.end==0?currentend:edge.end)-edge.start) )
					{
						state.setActiveNode(edge);
						state.setActiveLength(0);
						state.setActiveEdge((char)0);
						
					}

					break;
				}
				else
				{
					if(state.getActiveLength()==0)
					{
						//insert directly
						state.getActiveNode().addChild(new SuffixTree(index));
						
						state.setActiveNode(root);
						state.setActiveEdge((char)0);
						state.setActiveLength(0);
					}
					else
					{
						//find the edge and split it
						for(SuffixTree child:state.getActiveNode().children)
						{
							if(state.getActiveEdge()==text.charAt(child.start))
							{
								child.end = child.start+state.getActiveLength();
								
								child.addChild(new SuffixTree(child.start+state.getActiveLength()));
								child.addChild(new SuffixTree(index));
								
								//rule 2
								if(lastNode==null)
								{
									lastNode = child;
								}
								else
								{
									lastNode.suffixlink = child;
									lastNode = child;
								}
								
								insert.deleteCharAt(0);
								
								//rule 1
								if(state.getActiveNode()==root)
								{
									if(insert.length()>0)
									{
										state.setActiveEdge(insert.charAt(0));
										state.setActiveLength(state.getActiveLength()-1);
									}
								}
								//rule 3
								else
								{
									if(child.suffixlink==null)
									{
										state.setActiveNode(root);
									}
									else
									{
										state.setActiveNode(child.suffixlink);
									}
								}
								
								break;
							}
						}
					}

				}
			}
			
			
			//start to process in this step
			
		}
	}
	public void addChild(SuffixTree child)
	{
		children.add(child);
	}
	public SuffixTree inEdge(State state,String sub)
	{
		char head = sub.charAt(0);
		
		SuffixTree active_root = state.getActiveNode();
		char active_edge = state.getActiveEdge();
		for(SuffixTree child:active_root.children)
		{
			if(active_edge==0)
			{
				if(head==text.charAt(child.start))
				{
					return child;
				}
			}
			else
			{
				if(active_edge==text.charAt(child.start))
				{
					if(text.substring(child.start, child.start+state.getActiveLength()+1).equals(sub))
					{
						return child;
					}
					else
					{
						return null;
					}
				}
			}
		}
		
		
		return null;
	}
	
	@Override
	public String toString()
	{
		return text.substring(start, end==0?currentend:end);
	}
	
	private static class State
	{
		private SuffixTree active_node;
		private char active_edge;
		private int active_length;
		
		public State(SuffixTree node, char edge, int length)
		{
			active_node = node;
			active_edge = edge;
			active_length = length;
		}
		
		public void setActiveNode(SuffixTree node)
		{
			active_node = node;
		}
		public void setActiveEdge(char edge)
		{
			active_edge = edge;
		}
		public void setActiveLength(int length)
		{
			active_length = length;
		}
		
		public SuffixTree getActiveNode()
		{
			return active_node;
		}
		public char getActiveEdge()
		{
			return active_edge;
		}
		public int getActiveLength()
		{
			return active_length;
		}
	}
	
	
	public static void main(String[] args)
	{
		SuffixTree tree = new SuffixTree();
		tree.buildSuffixTree();
		
	}

}
