package linkedlist;

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
	
	public LinkedItem initLinkedList(char[] chars,LinkedItem root)
	{
		LinkedItem node = root;
		int lastIndex = chars.length-1;
		for(int index=0;index<=lastIndex;index++)
		{
			node.value = chars[index];

			if(index<lastIndex)
			{
				node.next = new LinkedItem();
				node = node.next;
			}
			else
			{
				node.next = null;
			}	
			
		}
		
		return root;
	}
	//创建一个有环的单链表
	public void createLinkedListWithALoop(char[] chars,LinkedItem root)
	{
		initLinkedList(chars,root);
		
		int loopstartIndex = 6;
		LinkedItem loopstartItem = null;
		LinkedItem lastItem = null;
		int count = 0;
		LinkedItem node = root;
		do
		{
			count++;
			if(count==loopstartIndex)
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
	//创建两个有交点的链表
	public void createLinkedListsWithCommonNode(char[] chars, LinkedItem root1,char[] chars2,LinkedItem root2)
	{
		initLinkedList(chars,root1);
		initLinkedList(chars2,root2);
		
		int index = 0;
		int commonNodeIndex = 5;
		LinkedItem commonNodeInList1 = null;
		LinkedItem lastNodeInList2 = null;
		
		LinkedItem temp=root1;
		while(temp!=null)
		{
			if(index==commonNodeIndex)
			{
				commonNodeInList1 = temp;
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
		}
		
		return hasLoop(root1);
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
				node2.next = node2.next.next;
				node2.next.next = null;
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

		while(p.hasNext())
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
	
	
	public static void main(String[] args)
	{
		TestLinkedList test = new TestLinkedList();
		
		LinkedItem root = new LinkedItem();
		test.initLinkedList(test.chars5,root);
		//test.print(test.revert(root));
		test.print(test.revert2(root));
		
		
		LinkedItem root2 = new LinkedItem();
		test.initLinkedList(test.chars,root2);
		test.printRevert(root2);
		
	}
}
