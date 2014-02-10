package TT;

import T.Base;
import T.I1;


public class Child extends Base implements I1{
	public int index = 101;
	
	public Child()
	{
		System.out.println("child");
	}
	
	public Child(int index)
	{
		System.out.println("child2");
	}
}
