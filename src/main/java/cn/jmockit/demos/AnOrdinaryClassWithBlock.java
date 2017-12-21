package cn.jmockit.demos;

//一个包含初始代码块的普通类 
public class AnOrdinaryClassWithBlock {
	private int i;

	public static int j;

	// 初始代码块
	{
		i = 1;
	}
	// 静态初始代码块
	static {
		j = 2;
	}

	// 构造函数
	public AnOrdinaryClassWithBlock(int i) {
		this.i = i;
	}
 
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
