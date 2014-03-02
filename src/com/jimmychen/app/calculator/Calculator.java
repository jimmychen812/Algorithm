package com.jimmychen.app.calculator;

public class Calculator {
	
	private int total;
	private int temp;
	private int index;
	private State state;
	private State idleState = new IdleState(this);
	private State addState = new AddState(this);
	private State subState = new SubState(this);
	private State mulState = new MulState(this);
	private State divState = new DivState(this);
	
	
	public int calculator(String str) throws Exception
	{
		total = 0;
		temp = 0;
		index = 0;
		state = idleState;
		
		for(int index=str.length()-1;index>=0;index--)
		{
			read(str.charAt(index));
		}
		char first = str.charAt(0);
		if(first>='0'&&first<='9')
		{
			read('+');
		}
		
		return total;
	}
	
	private void add(int number)
	{
		total += number;
	}
	
	private void read(char c) throws Exception
	{
		if(c=='+'||c=='-'||c=='*'||c=='/')
		{	
			
			state.process(temp,c);
			temp = 0;
			index = 0;
		}
		else if(c>='0'&&c<='9')
		{
			temp += (c-'0')*Math.pow(10, index++);
		}
		else
		{
			throw new Exception("Invalid character:"+c);
		}
	}
	
	private void setState(State state)
	{
		this.state = state;
	}
	private State getCurrentState()
	{
		return this.state;
	}
	private State getIdleState()
	{
		return idleState;
	}
	private State getAddState()
	{
		return addState;
	}
	private State getSubState()
	{
		return subState;
	}
	
	private State getMulState()
	{
		return mulState;
	}
	private State getDivState()
	{
		return divState;
	}
	
	
	private abstract static class State
	{
		protected Calculator calculator;
		public State(Calculator calculator)
		{
			this.calculator = calculator;
		}
		
		public abstract void process(int number,char operator);
		
		
	}
	
	private static class IdleState extends State
	{

		public IdleState(Calculator calculator) {
			super(calculator);
			
		}

		@Override
		public void process(int number, char operator) {
			if(operator=='+')
			{
				calculator.setState(calculator.getAddState());
				calculator.getCurrentState().process(number, operator);
			}
			else if(operator=='-')
			{
				calculator.setState(calculator.getSubState());
				calculator.getCurrentState().process(number, operator);
			}
			else if(operator=='*')
			{
				calculator.setState(calculator.getMulState());
				
				calculator.getCurrentState().process(number, operator);
			}
			else if(operator=='/')
			{
				calculator.setState(calculator.getDivState());
				
				calculator.getCurrentState().process(number, operator);
			}
			
		}
		
	}
	
	private static class AddState extends State
	{

		public AddState(Calculator calculator) {
			super(calculator);
		}

		@Override
		public void process(int number, char operator) {
			if(operator=='+')
			{
				calculator.add(number);
			}
			else
			{
				
			}
			
			calculator.setState(calculator.getIdleState());
			
		}
		
	}
	
	private static class SubState extends State
	{

		public SubState(Calculator calculator) {
			super(calculator);
		}

		@Override
		public void process(int number, char operator) {
			if(operator=='-')
			{
				calculator.add(-number);
			}
			else
			{
				
			}
			
			calculator.setState(calculator.getIdleState());
			
		}
		
	}
	
	private static class MulState extends State
	{
		private int number2;
		private boolean hasNumber2;
		public MulState(Calculator calculator) {
			super(calculator);
		}

		@Override
		public void process(int number, char operator) {
			
			if(hasNumber2)
			{
				if(operator=='+')
				{
					calculator.add(number*number2);
					
					hasNumber2 = false;
					
					calculator.setState(calculator.getIdleState());
				}
				else if(operator=='-')
				{
					calculator.add(-number*number2);
					
					hasNumber2 = false;
					
					calculator.setState(calculator.getIdleState());
				}
				else if(operator=='*')
				{
					number2 = number*number2;
				}
				else if(operator=='/')
				{
					calculator.setState(calculator.getDivState());
					calculator.getCurrentState().process(number*number2, '/');
					
					hasNumber2 = false;
				}
			}
			else
			{
				if(operator=='*')
				{
					hasNumber2 = true;
					number2 = number;
				}
				
			}
			
			
		}
		
	}
	
	
	
	private static class DivState extends State
	{
		private int number2;
		private boolean hasNumber2;
		
		public DivState(Calculator calculator) {
			super(calculator);
		}

		@Override
		public void process(int number, char operator) {
			if(hasNumber2)
			{
				if(number==0)
				{
					
				}
				
				if(operator=='+')
				{
					calculator.add(number/number2);
					
					hasNumber2 = false;
					
					calculator.setState(calculator.getIdleState());
				}
				else if(operator=='-')
				{
					calculator.add(-number/number2);
					
					hasNumber2 = false;
					
					calculator.setState(calculator.getIdleState());
				}
				else if(operator=='*')
				{					
					calculator.setState(calculator.getMulState());
					calculator.getCurrentState().process(number/number2, '*');
					
					hasNumber2 = false;
					
				}
				else if(operator=='/')
				{
					number2 = number/number2;	
				}
			}
			else
			{
				if(operator=='/')
				{
					hasNumber2 = true;
					number2 = number;
				}
				
			}
			
		}
		
	}
	
	
	
	
	public static void main(String[] args) throws Exception
	{
		Calculator calculator = new Calculator();
		
		System.out.println(calculator.calculator("100*2/2+2-2-100"));

	}
	
}
