
package org.vincent.enums.enum01;

/**
 * 测试switch 语句使用场景
 * @author liuhy
 *
 */
public class AppEnumSwitch {
	public static void main(String[] args) {
		   for (EnumTest e : EnumTest.values()) {
	            System.out.println(e.toString());
	            System.out.println("枚举名称:枚举序号对  "+e.name()+" : "+e.ordinal());
	        }
	         
	        System.out.println("----------------我是分隔线------------------");
	         
	        EnumTest test = EnumTest.TUE;
	        switch (test) {
	        case MON:
	            System.out.println("今天是星期一");
	            break;
	        case TUE:
	            System.out.println("今天是星期二");
	            break;
	        // ... ...
	        default:
	            System.out.println(test);
	            break;
	        }
	        
	        
	        System.out.println("----------------------------------");
	         test = EnumTest.MON;
	         
	        //compareTo(E o)
	        switch (test.compareTo(EnumTest.MON)) {
	        case -1:
	            System.out.println("TUE 在 MON 之前");
	            break;
	        case 1:
	            System.out.println("TUE 在 MON 之后");
	            break;
	        default:
	            System.out.println("TUE 与 MON 在同一位置");
	            break;
	        }
	         
	        //getDeclaringClass()
	        System.out.println("getDeclaringClass(): " + test.getDeclaringClass().getName());
	         
	        //name() 和  toString()
	        System.out.println("name(): " + test.name());
	        System.out.println("toString(): " + test.toString());
	         
	        //ordinal()， 返回值是从 0 开始,获取到定义枚举的 变量的下标。
	        System.out.println("ordinal(): " + test.ordinal());
	}

}
