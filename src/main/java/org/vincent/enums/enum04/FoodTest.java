package org.vincent.enums.enum04;

import org.vincent.enums.enum04.Food.Coffee;
import org.vincent.enums.enum04.Food.Dessert;

public class FoodTest {

	public static void main(String[] args) {
		for (Dessert dessertEnum : Dessert.values()) {
			//对于每个枚举实例并调用接口方法
            System.out.println("name "+dessertEnum.name() +". eat invoke "+dessertEnum.eat(dessertEnum.name())+ "  ");
        }
        System.out.println("\n------------------------------");
        //我这地方这么写，是因为我在自己测试的时候，把这个coffee单独到一个文件去实现那个food接口，而不是在那个接口的内部。
        for (Coffee coffee : Coffee.values()) {
            System.out.print(coffee + "  ");
        }
        System.out.println("\n------------------------------");
        //搞个实现接口，来组织枚举，简单讲，就是分类吧。如果大量使用枚举的话，这么干，在写代码的时候，就很方便调用啦。
        //还有就是个“多态”的功能吧，
        //把枚举类当做接口类型来用
        Food food = Dessert.CAKE;
        System.out.println(food);
        food = Coffee.BLACK_COFFEE;
        System.out.println(food);
        System.out.println(food.eat(food.getClass().getName()));
        

	}
}
