package array;

public class JudgeEven implements Judge{

	@Override
	public boolean isSatisfied(int number) {

		return (number&1)==0;
	}
	
}
