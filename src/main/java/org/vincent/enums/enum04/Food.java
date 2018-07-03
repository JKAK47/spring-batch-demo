package org.vincent.enums.enum04;
/**
 * 使用接口组织多个枚举类,
 * 接口可以定义接口方法，然后枚举类实现接口并实现接口方法。
 * @author pengrong
 *
 */
public interface Food {  
	//接口定义方法
	String eat(String name);
    enum Coffee implements Food{  
        BLACK_COFFEE,DECAF_COFFEE,LATTE,CAPPUCCINO;

		@Override
		public String eat(String name) {
			System.out.println("Coffee eat : "+name +" eating...");
			return name;
			
		}  
    }  
    enum Dessert implements Food{  
        FRUIT, CAKE, GELATO;

		@Override
		public String eat(String name) {
			System.out.println("Dessert eat print  : "+name +" eating...");
			return name;
		}  
    }  
}  