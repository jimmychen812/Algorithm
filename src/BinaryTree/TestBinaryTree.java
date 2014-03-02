package BinaryTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import org.junit.Test;

public class TestBinaryTree {

	private static final int[] preorder = {11, 8, 3, 1, 4, 9, 17, 13, 12, 14, 19};
	private static final int[] inorder = {1, 3, 4, 8, 9, 11, 12, 13, 14, 17, 19};
	private static final int[] postorder = {1, 4, 3, 9, 8, 12, 14, 13, 19, 17, 11};
	
	/*
		Level 0 11 
		Level 1 8 17 
		Level 2 3 9 13 19 
		Level 3 1 4 # # 12 14 # # 
		Level 4 # # # # # # # # 
	 */
	
	//@Test
	public void test_printAllRoutesMeetTarget()
	{
		BinaryTreeLinked.Node root = BinaryTreeArray.getBinaryTreeFromPreOrderAndInOrder(preorder,0,inorder,0,inorder.length-1);
		BinaryTreeLinked.printBreadthFirst(root);
		
		BinaryTreeLinked.printAllRoutesMeetTarget(root,26);
	}

	//@Test
	public void test_treeset()
	{
		int total = 5;
		int[] numbers = {8,7,6,4,5,3,2,1};
		TreeSet<Integer> set = new TreeSet<Integer>();
		for(int index=0;index<numbers.length;index++)
		{
			set.add(numbers[index]);
		}
		
		int i = 0;
		Iterator<Integer> iterator = set.iterator();
		while(iterator.hasNext())
		{
			System.out.print(iterator.next()+" ");
			if(++i>=total)
			{
				break;
			}
		}
	}
	
	//@Test
	public void test_isBSTree()
	{
		int[] postorder1 = {1, 4, 3, 9, 8, 12, 14, 13, 19, 17, 11};
		int[] postorder2 = {7,4,6,8};
		
		//System.out.println("is BSTree:"+BinaryTreeArray.isBSTreeByLRD(postorder1,0,postorder1.length-1));
		System.out.println("is BSTree:"+BinaryTreeArray.isBSTreeByLRD(postorder2,0,postorder2.length-1));
		
	}
	
	//@Test
	public void test_hasSubTreeRelationship()
	{
		int[] preorder1 = {11, 8, 3, 1, 4, 9, 17, 13, 12, 14, 19};
		int[] inorder1 = {1, 3, 4, 8, 9, 11, 12, 13, 14, 17, 19};
		
		//int[] preorder2 = {4, 1, 3, 9, 8};
		//int[] inorder2 = {1, 3, 4, 8, 9};
		
		int[] preorder2 = {8, 9};
		int[] inorder2 = {8, 9};
		
		BinaryTreeLinked.Node root1 = BinaryTreeArray.getBinaryTreeFromPreOrderAndInOrder(preorder1,0,inorder1,0,inorder1.length-1);
		BinaryTreeLinked.Node root2 = BinaryTreeArray.getBinaryTreeFromPreOrderAndInOrder(preorder2,0,inorder2,0,inorder2.length-1);
	
		System.out.println("is sub tree "+BinaryTreeLinked.hasSubTreeRelationship(root1,root2));
	
	}
	
	//@Test
	public void test_switchChildrenNonRescursion()
	{
		int[] preorder = {11, 8, 3, 1, 4, 9, 17, 13, 12, 14, 19};
		int[] inorder = {1, 3, 4, 8, 9, 11, 12, 13, 14, 17, 19};
		
		BinaryTreeLinked.Node root = BinaryTreeArray.getBinaryTreeFromPreOrderAndInOrder(preorder,0,inorder,0,inorder.length-1);
		
		System.out.println("\nbefore");
		BinaryTreeLinked.printBreadthFirst(root);
	
		BinaryTreeLinked.switchChildrenNonRescursion(root);		
		
		System.out.println("\nafter");
		BinaryTreeLinked.printBreadthFirst(root);
		
	
	}
	
	
	
	//@Test
	public void test_switchChildren()
	{
		int[] preorder = {11, 8, 3, 1, 4, 9, 17, 13, 12, 14, 19};
		int[] inorder = {1, 3, 4, 8, 9, 11, 12, 13, 14, 17, 19};
		
		BinaryTreeLinked.Node root = BinaryTreeArray.getBinaryTreeFromPreOrderAndInOrder(preorder,0,inorder,0,inorder.length-1);
		
		System.out.println("\nbefore");
		BinaryTreeLinked.printBreadthFirst(root);
	
		BinaryTreeLinked.switchChildren(root);		
		
		System.out.println("\nafter");
		BinaryTreeLinked.printBreadthFirst(root);
		
		System.out.println(-2/2);
		
	
	}
	//@Test
	public void test_getNearestCommonFather()
	{
		int[] preorder = {11, 8, 3, 1, 4, 9, 17, 13, 12, 14, 19};
		int[] inorder = {1, 3, 4, 8, 9, 11, 12, 13, 14, 17, 19};
		
		BinaryTreeLinked.Node root = BinaryTreeArray.getBinaryTreeFromPreOrderAndInOrder(preorder,0,inorder,0,inorder.length-1);
		
		BinaryTreeLinked.Node node1 = new BinaryTreeLinked.Node(11);
		BinaryTreeLinked.Node node2 = new BinaryTreeLinked.Node(8);
		
		System.out.println("NearestCommonFather is "+BinaryTreeLinked.getNearestCommonFather(root, node1, node2));
	}
	
	//@Test
	public void test_getLastCommonParentFromBSTree()
	{
		//create a BS Tree
		int[] numbers={1,2,3,4,5,6,7,8,9,10};
		BinaryTreeLinked.Node root = BinaryTreeArray.getBSTree(numbers,0,numbers.length-1);
		
		BinaryTreeLinked.printBreadthFirst(root);
		
		BinaryTreeLinked.Node result = BinaryTreeLinked.getLastCommonParentFromBSTree(root,new BinaryTreeLinked.Node(2),new BinaryTreeLinked.Node(7));
		
		System.out.println("\nlast common parent is "+result.value);
		
	}
	
	@Test
	public void test_getPathToRoot()
	{
		int[] preorder = {11, 8, 3, 1, 4, 9, 17, 13, 12, 14, 19};
		int[] inorder = {1, 3, 4, 8, 9, 11, 12, 13, 14, 17, 19};
		
		BinaryTreeLinked.Node root = BinaryTreeArray.getBinaryTreeFromPreOrderAndInOrder(preorder,0,inorder,0,inorder.length-1);
		BinaryTreeLinked.printBreadthFirst(root);
		
		
		LinkedList<BinaryTreeLinked.Node> path = new LinkedList<BinaryTreeLinked.Node>();
		BinaryTreeLinked.getPathToRoot(root, new BinaryTreeLinked.Node(12), path);
		
		
		Iterator<BinaryTreeLinked.Node> iterator = path.iterator();
		while(iterator.hasNext())
		{
			System.out.print(iterator.next().value+" ");
		}
		
		
		
	}
	
	
	
}
