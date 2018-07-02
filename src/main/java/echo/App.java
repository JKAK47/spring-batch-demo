package echo;

import java.io.IOException;

/**
 * @Package: com.vincent.echo <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/26 12:02 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/26. <br/>
 */

public class App {
		public static void main(String[] args){
				System.out.println("begin");
		     new  Thread(new Runnable() {
				     @Override
				     public void run() {
						     try {
						     		RpcExporter.export("localhost",8088);
						     } catch (IOException e) {
								     e.printStackTrace();
						     } finally {

						     }
				     }
		     }).start();
		}
}
