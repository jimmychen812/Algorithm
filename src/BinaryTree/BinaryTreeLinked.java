package BinaryTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeLinked {

	private Node root;
	

	
	public static class Node
	{
		public Node leftchild;
		public Node rightchild;
		public int value;
		
		public Node(int value)
		{
			this.value = value;
		}
		public Node()
		{
			
		}
		public int getValue()
		{
			return this.value;
		}
		public Node getLeft()
		{
			return this.leftchild;
		}
		public Node getRight()
		{
			return this.rightchild;
		}
		public void setLeft(Node left)
		{
			this.leftchild = left;
		}
		public void setRight(Node right)
		{
			this.rightchild = right;
		}
		
		public boolean isEmptyNode()
		{
			return value==-1;
		}
		
		public boolean isLeaf()
		{
			return leftchild==null&&rightchild==null;
		}
		
		@Override
		public boolean equals(Object obj)
		{
			if(obj==null || !(obj instanceof Node))
			{
				return false;
			}
			Node node = (Node)obj;
			return node.value==this.value;
		}

	}
	
	
	public Node getRoot(int value)
	{
		root = new Node();
		root.value = value;
		return root;
	}
	
	/*
	 * ������ȱ���������
	 * ˼·������queue���Ƚ��ȳ�
	*/
	public static int printBreadthFirst(Node root)
	{
		int maxwidth = 0;
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		
		Node node=null;
		int level = 0;
		int total = 0;
		boolean levelchanged = true;
		int currentWidth=0;
		while((node=queue.poll())!=null)
		{
			if(levelchanged)
			{
				System.out.print("Level "+level+" ");
				levelchanged = false;	
			}
			if(node.isEmptyNode())
			{
				System.out.print("#");
			}
			else
			{
				System.out.print(node.value);
				
				currentWidth++;
			}
			System.out.print(" ");
			
			total++;
			if(total>=Math.pow(2, level+1)-1)
			{
				level++;
				levelchanged = true;
				System.out.println();
				
				if(currentWidth>=maxwidth)
				{
					maxwidth = currentWidth;	
				}
				
				currentWidth = 0;
			}
			
			if(!node.isEmptyNode())
			{
				if(node.leftchild!=null)
				{
					queue.add(node.leftchild);
				}
				else if(node.leftchild==null)
				{
					queue.add(new Node(-1));
				}
				
				if(node.rightchild!=null)
				{
					queue.add(node.rightchild);
				}
				else if(node.rightchild==null)
				{
					queue.add(new Node(-1));
				}
				
			}
			
		}
		
		return maxwidth;
		
	}
	
	//������ȱ���
	public static Node[] getBreadthFirst(Node root)
	{	
		List<Node> nodes = new ArrayList<Node>();
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		
		Node node=null;

		while((node=queue.poll())!=null)
		{

			nodes.add(node);
			
			
			if(!node.isEmptyNode())
			{
				if(node.leftchild!=null)
				{
					queue.add(node.leftchild);
				}
				else if(node.leftchild==null)
				{
					queue.add(new Node(-1));
				}
				
				if(node.rightchild!=null)
				{
					queue.add(node.rightchild);
				}
				else if(node.rightchild==null)
				{
					queue.add(new Node(-1));
				}
				
			}
			
		}
		
		
		Node[] result = new Node[nodes.size()];
		return nodes.toArray(result);
		
	}
	
	//������ȱ���֮�ȸ�����
	public static void printDLR(Node root)
	{
		if(root!=null&&!root.isEmptyNode())
		{
			System.out.print(root.value+" ");
			
			if(root.leftchild!=null)
			{
				printDLR(root.leftchild);
			}
			if(root.rightchild!=null)
			{
				printDLR(root.rightchild);
			}
		}
	}
	//������ȱ���֮�и�����
	public static void printLDR(Node root)
	{
		if(root.leftchild!=null)
		{
			printLDR(root.leftchild);
		}
		
		System.out.print(root.value+" ");
		
		if(root.rightchild!=null)
		{
			printLDR(root.rightchild);
		}
	}
	
	//������ȱ���֮�������
	public static void printLRD(Node root)
	{
		if(root==null)
		{
			return;
		}
		
		printLRD(root.leftchild);
		printLRD(root.rightchild);
		System.out.print(root.value+" ");
	}
	
	
	//������ȱ���֮�ǵݹ��ȸ�����
	public static void NonRescursionDLR(Node root)
	{
		Stack<Node> stack = new Stack<Node>();
		stack.add(root);
		Node node = null;
		while(!stack.isEmpty())
		{
			node = stack.pop();
			
			System.out.print(node.value+" ");
			
			if(node.rightchild!=null)
			{
				stack.add(node.rightchild);
			}
			
			
			if(node.leftchild!=null)
			{
				stack.add(node.leftchild);
			}
			
			
		}
		
		System.out.println();
	}
	//������ȱ���֮�ǵݹ��и�����
	public static void NonRescursionLDR(Node root)
	{
		Stack<Node> stack = new Stack<Node>();
		Node node = root;
		while(node!=null||!stack.isEmpty())
		{
			while(node!=null)
			{
				stack.add(node);
				node=node.leftchild;
			}
			if(!stack.isEmpty())
			{
				node = stack.pop();
				System.out.print(node.value+" ");
				node = node.rightchild;
			}
		}
		
		System.out.println();
	}
	//������ȱ���֮�ǵݹ�������
	public static void NonRescursionLRD(Node root)
	{
		Stack<Node> stack = new Stack<Node>();
		Node lastNode = null;
		Node node = root;
		while(node!=null||!stack.isEmpty())
		{
			while(node!=null)
			{
				stack.add(node);
				node=node.leftchild;
			}
			if(!stack.isEmpty())
			{
				node = stack.peek();
				if(node.rightchild==null||node.rightchild==lastNode)
				{
					stack.pop();
					System.out.print(node.value+" ");
					lastNode = node;
					
					node = null;
				}
				else
				{
					node = node.rightchild;
				}
				
				
				
				
			}
		}
	}
	
	public int FindKthMax(Node root,int k)
	{
		
		
		return -1;
	}
	
	//������������Ŀ��(���:������������Ĳ��а����Ľ����)
	//������ȱ���ʱ���Լ�����������������
	public int getBTreeMaxWidth(Node root)
	{
		
		return 0;
	}
	//����������������
	//���ĸ߶� = max(�������ĸ߶ȣ��������ĸ߶�) + 1
	public static int getBTreeMaxHeight(Node root)
	{
		if(root==null)
		{
			return 0;
		}
		
		int leftsubtreeheight = getBTreeMaxHeight(root.leftchild);
		int rightsubtreeheight = getBTreeMaxHeight(root.rightchild);
		
		return leftsubtreeheight>rightsubtreeheight?leftsubtreeheight+1:rightsubtreeheight+1;
	}
	//��ȡҶ�ӽڵ�ĸ���
	public static int getLeaves(Node root)
	{
		if(root.leftchild==null&&root.rightchild==null)
		{
			return 1;
		}
		int leftsubtreeleaves = root.leftchild==null?0:getLeaves(root.leftchild);
		int rightsubtreeleaves = root.rightchild==null?0:getLeaves(root.rightchild);
		
		return leftsubtreeleaves+rightsubtreeleaves;
	}
	
	//�ж϶������Ƿ�����ȫ������
	public static boolean isCompleteBinaryTree(Node root)
	{
		boolean findEmpty = false;
		Node[] nodes = getBreadthFirst(root);
		for(int index=0;index<nodes.length;index++)
		{
			if(nodes[index].isEmptyNode())
			{
				findEmpty = true;
			}
			else
			{
				if(findEmpty)
				{
					return false;
				}
			}
		}
		
		
		
		return true;
	}
	//��ȡ���ڵ��е�����������Ƚڵ�
	//(Oracle)
	public static int getNearestCommonFather(Node root,Node node1,Node node2)
	{
		Node[] nodes = getBreadthFirst(root);
		int indexNode1 = -1;
		int indexNode2 = -1;
		for(int index=0;index<nodes.length;index++)
		{
			if(nodes[index].equals(node1))
			{
				indexNode1 = index;
			}
			if(nodes[index].equals(node2))
			{
				indexNode2 = index;
			}
			if(indexNode1!=-1&&indexNode2!=-1)
			{
				break;
			}
		}
		
		if(indexNode1==-1||indexNode2==-1)
		{
			System.err.println("can't find the node in the tree!");
			return -1;
		}
		
		int p1 = (indexNode1-1)/2;
		int p2 = (indexNode2-1)/2;
		//p1 = p1<0?0:p1;
		//p2 = p2<0?0:p2;
		while(p1!=p2)
		{
			if(p1>p2)
			{
				p1 = (p1-1)/2;
				//p1 = p1<0?0:p1;
			}
			else
			{
				p2 = (p2-1)/2;
				//p2 = p2<0?0:p2;
			}
		}
		
		
		
		return nodes[p1].value;
	}
	public static void switchChildren(Node root)
	{
		if(root==null)
		{
			return;
		}
		if(root.isLeaf())
		{
			return;
		}

		Node oldleft = root.leftchild;
		root.leftchild = root.rightchild;
		root.rightchild = oldleft;
		
		switchChildren(root.leftchild);
		switchChildren(root.rightchild);
	
	}
	
	public static void switchChildrenNonRescursion(Node root)
	{
		if(root==null)
		{
			return;
		}
		if(root.isLeaf())
		{
			return;
		}
		
		Stack<Node> stack = new Stack<Node>();
		stack.add(root);
		
		while(!stack.isEmpty())
		{
			Node node = stack.pop();
			
			Node oldleft = node.getLeft();
			node.setLeft(node.getRight());
			node.setRight(oldleft);
			
			if(node.getLeft()!=null)
			{
				stack.add(node.getLeft());
			}
			if(node.getRight()!=null)
			{
				stack.add(node.getRight());
			}
		}
		
		
	}
	
	
	//return the tail of the double-linked chain
	/*
	 *˼·��ÿ���ڵ��leftΪ����������������������һ���ڵ�last��last��rightָ������ڵ� 
	 */
	public static BinaryTreeLinked.Node convertBSTreeToDoubleLinked(BinaryTreeLinked.Node root,BinaryTreeLinked.Node last)
	{
		if(root==null)
		{
			return null;
		}
			
		if(root.getLeft()!=null)
		{
			last = convertBSTreeToDoubleLinked(root.getLeft(),last);
		}
			
		root.setLeft(last);
			
		if(last!=null)
		{
			last.setRight(root);
		}
			
		last = root;
		if(root.getRight()!=null)
		{
			last = convertBSTreeToDoubleLinked(root.getRight(),last);
		}
			
		return last;
			
	}
	
	private static void findPath(Node root,LinkedList<Integer> queue,int target,int total)
	{
		total += root.getValue();
		queue.add(root.getValue());
		if(root.isLeaf())
		{
			if(total==target)
			{
				Iterator<Integer> iterator = queue.iterator();
				while(iterator.hasNext())
				{
					System.out.print(iterator.next()+" ");
				}
			}
		}
		else
		{
			if(root.getLeft()!=null)
			{
				findPath(root.getLeft(),queue,target,total);
			}
			
			if(root.getRight()!=null)
			{
				findPath(root.getRight(),queue,target,total);
			}
		}
		
		total -=root.getValue();
		queue.removeLast();
		
	}
	public static void printAllRoutesMeetTarget(Node root,int target)
	{
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		findPath(root,queue,target,0);
	}
	
	public static boolean hasSubTreeRelationship(Node root1,Node root2)
	{
		if(root1==null&&root2!=null
				||root1!=null&&root2==null)
		{
			return false;
		}
		if(root1==null&&root2==null)
		{
			return true;
		}
		return hasSubTreeRelationshipCore(root1,root2);
	}
	private static boolean hasSubTreeRelationshipCore(Node root1,Node root2)
	{
		boolean result = false;
		
		if(root1.getValue()==root2.getValue())
		{
			result = hasSubTreeRelationshipCore2(root1,root2);
		}
		

		if(!result&&root1.getLeft()!=null)
		{
			result = hasSubTreeRelationshipCore(root1.getLeft(),root2);
		}
		if(!result&&root1.getRight()!=null)
		{
			result = hasSubTreeRelationshipCore(root1.getRight(),root2);
		}
		
		return result;
	}
	private static boolean hasSubTreeRelationshipCore2(Node root1,Node root2)
	{
		if(root2==null)
		{
			return true;
		}
		if(root1==null)
		{
			return false;
		}
		
		if(root1.getValue()!=root2.getValue())
		{
			return false;
		}

		return hasSubTreeRelationshipCore2(root1.getLeft(),root2.getLeft())&&hasSubTreeRelationshipCore2(root1.getRight(),root2.getRight());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
