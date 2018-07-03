package org.vincent.enums.enum03.service;
/**
 * 定义一个枚举实现接口 Behaviour
 * @author pengrong
 *
 */
public enum Print  implements Behaviour {
	//定义枚举变量时候赋值name和index两个属性
	  XIAOMI("XIAOMI-00", 1), HUAWEI("HUAWEI-01", 2), VIVO("VIVO-02", 3), OPPO("OPPO-03", 4);  
    // 定义Print枚举变量 成员变量 name,和index变量  
    private String name;  
    private int index;  
    
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

	// 构造方法  
    private Print(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  

    /**
     * 枚举类实现接口方法print
     */
	@Override
	public void print() {
		System.out.println(this.getName()+" : "+this.getIndex()+" printing...");
	}
	 /**
     * 枚举类实现接口方法 getInfo
     */
	@Override
	public String getInfo() {
		System.out.println(this.getName()+" : "+this.getIndex());
		return this.name()+"-"+this.ordinal();//name(),ordinal()方法是 返回枚举变量名   以及枚举变量顺序号
	}

}
