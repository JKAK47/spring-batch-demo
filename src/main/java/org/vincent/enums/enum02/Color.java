package org.vincent.enums.enum02;
/**
 * Color 枚举 增加属性覆盖枚举本身方法 
 * @author pengrong
 *
 */
public enum Color {
	//定义枚举是否赋值新增的两个属性
	 RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);  
    // 成员变量  
    private String name;  
    private int index;  
    // 构造方法,枚举构造方法只能private   
    private Color(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    // 普通方法  
    public static String getName(int index) {  
        for (Color c : Color.values()) {  
            if (c.getIndex() == index) {  
                return c.name;  
            }  
        }  
        return null;  
    }  
    // get set 方法  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }  
    
    //覆盖枚举本身的方法  
    @Override  
    public String toString() {  
        return this.index+"_"+this.name;  
    }  
}
