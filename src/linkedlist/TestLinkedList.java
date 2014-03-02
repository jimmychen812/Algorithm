package linkedlist;

import org.junit.Assert;
import org.junit.Test;

public class TestLinkedList {
	
	private char[] chars = new char[]{'h','e','l','l','o',',','w','o','r','l','d','!'};
	private char[] chars2 = new char[]{'h','a','h','a','.'};
	private char[] chars3 = new char[]{'z','y','x','n','b','a','c','d','f','e','p','q'};
	private char[] chars4 = new char[]{'h'};
	private char[] chars5 = new char[]{'h','e'};
	
	private LinkedItem root = new LinkedItem();
	private LinkedItem linkedlistwithLoop = new LinkedItem();
	
	private LinkedItem linkedlist1 = new LinkedItem();
	private LinkedItem linkedlist2 = new LinkedItem();
	
	private static class LinkedItem
	{
		private char value;
		private LinkedItem next;
		public LinkedItem(char value)
		{
			this.value = value;
		}
		public LinkedItem()
		{
			
		}
		public boolean hasNext()
		{
			return next!=null;
		}
		public boolean isEmpty()
		{
			return value!=0;
		}
		
		@Override
		public String toString()
		{
			return String.valueOf(value);
		}
	}
	
	//return the root of this linked list
	public LinkedItem initLinkedList(char[] chars)
	{
		if(chars==null||chars.length<0)
		{
			return null;
		}
		
		LinkedItem root = new LinkedItem(chars[0]);
		LinkedItem last = root;
		
		
		int lastIndex = chars.length-1;
		for(int index=1;index<=lastIndex;index++)
		{
			last.next = new LinkedItem(chars[index]);
			
			last = last.next;	
		}
		
		return root;
	}
	//创建一个有环的单链表
	public LinkedItem createLinkedListWithALoop(char[] chars,int loopstart)
	{
		LinkedItem root = initLinkedList(chars);
		
		if(root!=null)
		{
			LinkedItem loopstartItem = null;
			LinkedItem lastItem = null;
			int count = 0;
			LinkedItem node = root;
			do
			{
				count++;
				if(count==loopstart)
				{
					loopstartItem = node;
				}
				//This is the last node.
				if(!node.hasNext())
				{
					lastItem = node;
				}
				
				node = node.next;
				
			}while(node!=null);
			
			lastItem.next = loopstartItem;
		}
		
		
		
		return root;
	}
	//创建两个有交点的链表
	public LinkedItem[] createLinkedListsWithCommonNode(char[] chars,char[] chars2)
	{
		LinkedItem root1 = initLinkedList(chars);
		LinkedItem root2 = initLinkedList(chars2);
		
		int index = 0;
		int commonNodeIndex = chars.length/2;
		LinkedItem commonNodeInList1 = null;
		LinkedItem lastNodeInList2 = null;
		
		LinkedItem temp=root1;
		while(temp!=null)
		{
			if(index==commonNodeIndex)
			{
				commonNodeInList1 = temp;
				break;
			}
			index++;
			temp = temp.next;
		}
		
		
		temp = root2;
		while(temp!=null&&temp.hasNext())
		{
			temp = temp.next;
		}
		
		lastNodeInList2 = temp;
		
		
		lastNodeInList2.next = commonNodeInList1;
		
		return new LinkedItem[]{root1,root2};
	}
	
	//给定单链表，检测是否有环。如果有环，则求出进入环的第一个节点。
	public LinkedItem hasLoop(LinkedItem root)
	{
		LinkedItem slow = root;
		LinkedItem fast = root;
		while(fast!=null&&fast.hasNext())
		{
			slow = slow.next;
			fast = fast.next.next;
			
			if(slow==fast)
			{
				slow = root;
				
				while(true)
				{
					fast = fast.next;
					slow = slow.next;
					if(fast==slow)
					{
						return slow;
					}
				}
			}
		}
		
		
		return null;
	}
	public LinkedItem hasCommon(LinkedItem root1,LinkedItem root2)
	{
		LinkedItem lastNodeInList1 = lastNode(root1);
		LinkedItem lastNodeInList2 = lastNode(root2);
		if(lastNodeInList1==lastNodeInList2)
		{
			lastNodeInList1.next = root2;
			
			return hasLoop(root1);
			
		}
		else
		{
			return null;
		}
		
		
	}
	public LinkedItem get(LinkedItem root,int index)
	{
		LinkedItem temp = root;
		int i = 0;
		while(temp!=null&&temp.hasNext())
		{
			if(i==index)
			{
				return temp;
			}
			temp = temp.next;
			i++;
		}
		
		return null;
	}
	//获取倒数第last个node
	public LinkedItem getLastByIndex(LinkedItem root,int last)
	{
		int index = 0;
		int targetIndex = index+last-1;
		LinkedItem node = root;
		LinkedItem node2 = null;
		while(node!=null&&node.hasNext())
		{
			if(index==targetIndex)
			{
				node2 = root;
			}
			
			if(node2!=null)
			{
				node2 = node2.next;
			}
			node = node.next;
			index++;
			
		}
		
		return node2;
	}
	//删除倒数第last个node
	//找到倒数第last+1个node，再删除这个node的next
	public boolean removeLastByIndex(LinkedItem root,int last)
	{
		int index = 0;
		int targetIndex = index+last+1-1;
		LinkedItem node = root;
		LinkedItem node2 = null;
		
		while(node!=null&&node.hasNext())
		{
			if(index==targetIndex)
			{
				node2 = root;
			}
			
			if(node2!=null)
			{
				node2 = node2.next;
			}
			node = node.next;
			index++;
			
		}
		
		//delete the next node of node2
		if(node2!=null)
		{
			if(node2.next!=null)
			{
				LinkedItem old = node2.next;
				
				node2.next = node2.next.next;
				old.next = null;
				return true;
			}
			
			
			
		}
		return false;
	}
	//删除节点
	//value 的移动
	public boolean remove(LinkedItem p)
	{
		if(p==null||!p.hasNext())
		{
			return false;
		}

		while(p!=null&&p.hasNext())
		{
			p.value = p.next.value;
			if(p.next.next==null)
			{
				p.next = null;
			}
			else
			{
				p = p.next;
			}
			
		}
		
		return true;
	}
	//在P节点前插入一个节点
	public boolean insertBefore(LinkedItem p,char value)
	{
		if(p==null)
		{
			return false;
		}
		
		/*
		char temp = value;
		char temp2 = 0;
		do
		{
			temp2 = p.value;
			p.value = temp;
			
			temp = temp2;
			
			p = p.next;
			
		}while(p!=null&&p.hasNext());
		
		
		
		p.next = new LinkedItem(temp);
		
		
		return false;*/
		
		
		LinkedItem item = new LinkedItem(p.value);
		item.next = p.next;
		
		p.next = item;
		p.value = value;
		
		return true;
	}
	//归并排序
	public LinkedItem mergesort(LinkedItem root)
	{
		if(root==null||!root.hasNext())
		{
			return root;
		}
		LinkedItem[] subs = split(root);
		
		LinkedItem part1 = mergesort(subs[0]);
		LinkedItem part2 = mergesort(subs[1]);
		
		return merge(part1,part2);
	}
	private LinkedItem[] split(LinkedItem root)
	{
		LinkedItem[] subs = new LinkedItem[2];
		
		LinkedItem fast = root;
		LinkedItem slow = root;
		LinkedItem last = null;
		
		if(root==null||!root.hasNext())
		{
			subs[0] = root;
			subs[1] = null;
			return subs;
		}
		
		
		while(fast!=null&&fast.hasNext())
		{
			last = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		last.next = null;
		
		subs[0] = root;
		subs[1] = slow;
		
		return subs;
	}
	public LinkedItem merge(LinkedItem part1,LinkedItem part2)
	{
		LinkedItem root = null;
		if(part1==null)
		{
			root = part2;
		}
		else if(part2==null)
		{
			root = part1;
		}
		else
		{
			if(part1.value<part2.value)
			{
				root = part1;
				root.next = merge(part1.next,part2);
			}
			else
			{
				root = part2;
				root.next = merge(part1,part2.next);
			}
		}

		return root;
	}
	public LinkedItem revert(LinkedItem root)
	{
		LinkedItem prev = null;
		LinkedItem current = root;
		LinkedItem next = null;
		
		if(!root.hasNext())
		{
			return root;
		}
		
		while(current!=null)
		{
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			
		}
		
		return prev;
	}
	public LinkedItem revert2(LinkedItem root)
	{
		if(root.hasNext())
		{
			LinkedItem head = revert2(root.next);
			root.next.next = root;
			root.next = null;
			
			return head;
		}
		else
		{
			return root;
		}
	}
	public LinkedItem lastNode(LinkedItem root)
	{
		LinkedItem temp = root;
		while(temp!=null&&temp.hasNext())
		{
			temp = temp.next;
		}
		return temp;
	}
	public void printRevert(LinkedItem root)
	{
		if(root==null)
		{
			return;
		}
		
		if(root.hasNext())
		{
			printRevert(root.next);
		}
		
		System.out.print(root.value+" ");
	}
	public int print(LinkedItem root)
	{
		int max = 100;
		
		int count = 0;
		LinkedItem node = root;
		do
		{
			System.out.print(node.value);
			count++;
			node = node.next;
			
		}while(node!=null&&count<max);
		
		System.out.println("\n"+"total:"+count+"\n");
		return count;
	}
	
	//@Test
	public void test_hasLoop()
	{
		//a linkedlist with a loop
		char[] chars = new char[]{'h','e','l','l','o',',','w','o','r','l','d','!'};
		LinkedItem root1 = createLinkedListWithALoop(chars,chars.length/2);
		LinkedItem node1 = hasLoop(root1);
		Assert.assertTrue(node1!=null);
		
		System.out.println(node1.value);
		
		//a linkedlist without a loop
		LinkedItem root2 = initLinkedList(chars);
		LinkedItem node2 = hasLoop(root2);
		Assert.assertTrue(node2==null);
		System.out.println("without a loop");
		
	}
	
	@Test
	public void test_hasCommon()
	{
		char[] chars1 = new char[]{'h','e','l','l','o'};
		char[] chars2 = new char[]{'w','o','r','l','d'};
		
		LinkedItem[] roots = createLinkedListsWithCommonNode(chars1,chars2);
		
		LinkedItem common = hasCommon(roots[0],roots[1]);
		Assert.assertTrue(common!=null);
		
		System.out.println("common node "+common.value);
		
	}
	
	
	public static void main(String[] args)
	{
		
		
	}
}
