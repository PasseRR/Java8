package base;

/**
 * 断言<BR>
 * 主要用于程序的调试
 * <P>
 * 1、assert boolean表达式<BR>
 * &nbsp;&nbsp;如果boolean表达式为true，则程序继续执行。<BR>
 * &nbsp;&nbsp;如果为false，则程序抛出AssertionError，并终止执行。<BR>
 * </P>
 * <P>
 * 2、assert boolean表达式 : 错误信息表达式<br>
 * &nbsp;&nbsp;如果boolean表达式为true，则程序继续执行。<br>
 * &nbsp;&nbsp;如果为false，则程序抛出java.lang.AssertionError，并输入错误信息表达式。<br>
 * </P>
 * @version 1.0
 * @author xiehai
 * @date 2014年4月2日 下午2:58:01 
 */
public class Assert {
	public static void main(String[] args) {
//		char c = '\u0012'; 
		//若要开启断言
		//在Run Configurations中的Arguments选项卡在 VM arguments 文本框中输入： -ea 
		boolean flg = false;
		flg = true;
		assert flg;
		flg = false;
		assert flg : "断言失败!";
	}
}
