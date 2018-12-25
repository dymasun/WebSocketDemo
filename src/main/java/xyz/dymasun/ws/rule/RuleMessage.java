package xyz.dymasun.ws.rule;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author sun
 *	消息常数
 */
public final class RuleMessage {
	public final static String RUNTIMEERROR = "运行出错";
	//学生不存在
	public final static int StuNotExist = 0;
	//密码错误
	public final static int InvaildPWD = 1;

	public final static RuleResult NOT_IN_TIME = RuleResult.fail("没到考试时间");

	public final static RuleResult MISS_PARAM = RuleResult.fail("MISS_PARAM");


}
