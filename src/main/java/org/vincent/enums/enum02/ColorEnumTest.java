package org.vincent.enums.enum02;
/**
 * 对Color 枚举 增加属性覆盖枚举本身方法进行测试。
 * @author pengrong
 *
 */
public class ColorEnumTest {
	public static void main(String[] args) {
		Color color=Color.RED;
		System.err.println(color.toString());//自定义枚举类覆盖的父类方法。
		System.err.println(color.getName());//自定义属性输出
		System.err.println(color.getIndex());//自定义属性输出
		System.out.println(color.name());//定义枚举变量的枚举变量名
		System.out.println(color.ordinal());//定义枚举变量中枚举变量序列序号
	}
}
