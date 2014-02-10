package BinaryTree;

import java.util.Arrays;

public class BinaryTreeArray {

	private int[] binarytree = {11,8,17,3,9,13,19,1,4,-1,-1,12,14,-1,-1};
	
	private int[] preorder = {11, 8, 3, 1, 4, 9, 17, 13, 12, 14, 19};
	private int[] inorder = {1, 3, 4, 8, 9, 11, 12, 13, 14, 17, 19};
	private int[] postorder = {1, 4, 3, 9, 8, 12, 14, 13, 19, 17, 11};
	
	/*
		Level 0 11 
		Level 1 8 17 
		Level 2 3 9 13 19 
		Level 3 1 4 # # 12 14 # # 
		Level 4 # # # # # # # # 
	*/
	
	private int rootIndexInPreOrder = 0;

	
	//已知一个二叉树的前序遍历和中序遍历，构造这颗二叉树
	public BinaryTreeLinked.Node getBinaryTreeFromPreOrderAndInOrder(int istart,int iend)
	{
		if(rootIndexInPreOrder>preorder.length)
		{
			return null;
		}
		
		BinaryTreeLinked.Node root = new BinaryTreeLinked.Node();
		root.value = preorder[rootIndexInPreOrder++];

		//find left subtree and right subtree
		
		int rootIndexinInOrder=-1;
		for(int index=istart;istart<=iend;index++)
		{
			if(root.value==inorder[index])
			{
				rootIndexinInOrder = index;
				break;
			}
		}
		if(rootIndexinInOrder!=-1)
		{
			if(istart==iend)
			{
				root.leftchild = null;
				root.rightchild = null;
			}
			else
			{
				root.leftchild = getBinaryTreeFromPreOrderAndInOrder(istart,rootIndexinInOrder-1);
				root.rightchild = getBinaryTreeFromPreOrderAndInOrder(rootIndexinInOrder+1,iend);
			}
			
		}
		else
		{
			System.err.println("can't find the root node in inorder!");
		}

		return root;
	}
	
	
	/*
	 * 根据前序遍历和中序遍历，构造二叉树
	 * 思路：根据前序遍历中的根节点，在中序遍历中定位其左子树和右子数。递归。
	 */
	public static BinaryTreeLinked.Node getBinaryTreeFromPreOrderAndInOrder(int[]preorder,int preorderIndex,int[]inorder,int inorderStart,int inorderEnd)
	{
		BinaryTreeLinked.Node root = null;
		if(preorderIndex<preorder.length)
		{
			root = new BinaryTreeLinked.Node(preorder[preorderIndex]);
			

			int indexInInorder=-1;
			for(int index=inorderStart;index<=inorderEnd;index++)
			{
				if(root.getValue()==inorder[index])
				{
					indexInInorder = index;
					break;
				}
			}
			
			if(indexInInorder!=-1)
			{
				if(indexInInorder>inorderStart)
				{
					root.setLeft(
							getBinaryTreeFromPreOrderAndInOrder(
									preorder,
									preorderIndex+1,
									inorder,
									inorderStart,
									indexInInorder-1));
				}
				else
				{
					root.setLeft(null);
				}
				
				if(indexInInorder<inorderEnd)
				{
					root.setRight(
							getBinaryTreeFromPreOrderAndInOrder(
									preorder,
									1+preorderIndex+indexInInorder-inorderStart,
									inorder,
									1+indexInInorder,
									inorderEnd));
				}
				else
				{
					root.setRight(null);
				}
				
				
				
			}
			else
			{
				System.err.println("can't find the root node in inorder!");
			}

	
		}
		
		
		
		return root;
	}
	
	//已知一个二叉树的中序遍历和后序遍历，构造这颗二叉树
	public BinaryTreeLinked.Node getBinaryTreeFromInOrderAndPostOrder(int pstart,int pend,int instart,int inend)
	{
		BinaryTreeLinked.Node root = new BinaryTreeLinked.Node();
		root.value = postorder[pend];
		if(pstart==pend)
		{
			root.leftchild = null;
			root.rightchild = null;
			return root;
		}
		else
		{
			int indexRootInInOrder = -1;
			for(int index=instart;index<=inend;index++)
			{
				if(root.value==inorder[index])
				{
					indexRootInInOrder = index;
					break;
				}
			}
			if(indexRootInInOrder==-1)
			{
				System.err.println("can't find the root node in inorder!");
			}
			else
			{
				int leftsubtree = indexRootInInOrder-instart;
				int rightsubtree = inend-indexRootInInOrder;
				
				root.leftchild = getBinaryTreeFromInOrderAndPostOrder(pstart,pstart+leftsubtree-1,instart,instart+leftsubtree-1);
				root.rightchild = getBinaryTreeFromInOrderAndPostOrder(pend-rightsubtree,pend-1,inend-rightsubtree+1,inend);
			}
			
			return root;
		}
		
	}
	
	/*
	 * 根据中序遍历和后序遍历，构造二叉树
	 * 思路：根据后序遍历中的根节点，在中序遍历中定位其左子树和右子数。递归。
	 */
	public static BinaryTreeLinked.Node getBinaryTreeFromInOrderAndPostOrder(int postorderIndex,int[]postorder,int[]inorder,int inorderStart,int inorderEnd)
	{
		BinaryTreeLinked.Node root = null;
		if(postorderIndex>=0)
		{
			root = new BinaryTreeLinked.Node(postorder[postorderIndex]);
			
			int indexInInorder = -1;
			for(int index=inorderStart;index<=inorderEnd;index++)
			{
				if(root.getValue()==inorder[index])
				{
					indexInInorder = index;
					break;
				}
			}
			if(indexInInorder!=-1)
			{
				//has left child
				if(indexInInorder>inorderStart)
				{
					root.setLeft(getBinaryTreeFromInOrderAndPostOrder(
							postorderIndex-(inorderEnd-indexInInorder)-1,
							postorder,
							inorder,
							inorderStart,
							indexInInorder-1));
				}
				//doesn't have left child
				else
				{
					root.setLeft(null);
				}
				//has right child
				if(indexInInorder<inorderEnd)
				{
					root.setRight(getBinaryTreeFromInOrderAndPostOrder(
							postorderIndex-1,
							postorder,
							inorder,
							indexInInorder+1,
							inorderEnd));
				}
				////doesn't have right child
				else
				{
					root.setRight(null);
				}
				
				
			}
			else
			{
				System.err.println("can't find the root node in inorder!");
			}
	
		}
		
		
		return root;
	}
	//构造二元查找树
	public static BinaryTreeLinked.Node getBSTree(int[] input,int start,int end)
	{
		int rootIndex = (start+end)/2;
		BinaryTreeLinked.Node root = new BinaryTreeLinked.Node(input[rootIndex]);
		if(rootIndex>start)
		{
			root.setLeft(getBSTree(input,start,rootIndex-1));
		}
		
		if(rootIndex<end)
		{
			root.setRight(getBSTree(input,rootIndex+1,end));
		}
		
		return root;
	}
	
	//return the tail of the double-linked chain
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
	
	public static void main(String[] args) {
		
		
		int[] preorder = {11, 8, 3, 1, 4, 9, 17, 13, 12, 14, 19};
		int[] inorder = {1, 3, 4, 8, 9, 11, 12, 13, 14, 17, 19};
		int[] postorder = {1, 4, 3, 9, 8, 12, 14, 13, 19, 17, 11};
		
		//int[] preorder = {11,12,13};
		//int[] inorder = {11,12,13};
		//int[] postorder = {11,12,13};
		
		//int[] preorder = {11};
		//int[] inorder = {11};
		//int[] postorder = {11};
		
		//int[] preorder = {11,12};
		//int[] inorder = {12,11};
		//int[] postorder = {12,11};
		
		//int[] preorder = {11,13};
		//int[] inorder = {11,13};
		//int[] postorder = {13,11};

		//BinaryTreeLinked.Node root = BinaryTreeArray.getBinaryTreeFromPreOrderAndInOrder(preorder,0,inorder,0,inorder.length-1);
		//BinaryTreeLinked.printBreadthFirst(root);

		
		//System.out.println();
		
		//BinaryTreeLinked.Node root2 = BinaryTreeArray.getBinaryTreeFromInOrderAndPostOrder(postorder.length-1,postorder,inorder,0,inorder.length-1);
		//BinaryTreeLinked.printBreadthFirst(root2);
		
		
		//BinaryTreeLinked.NonRescursionLRD(root);
		
		//System.out.println("leaves "+BinaryTreeLinked.getLeaves(root));
		
		
		Arrays.sort(preorder);
		BinaryTreeLinked.Node BSTreeroot = getBSTree(preorder,0,preorder.length-1);
		BinaryTreeLinked.printBreadthFirst(BSTreeroot);
		
		
		BSTreeroot = convertBSTreeToDoubleLinked(BSTreeroot,null);
		
		while(BSTreeroot!=null)
		{
			System.out.print(BSTreeroot.getValue()+" ");
			BSTreeroot = BSTreeroot.getLeft();
		}
	
	}

}
