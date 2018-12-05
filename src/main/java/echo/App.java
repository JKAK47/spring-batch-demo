package echo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			List<String> list=new ArrayList<>();
			list.add("asdas");
			list.add("asdaasds");
			list.add("asdqwereras");
			list.add("askljkdas");
			list.add("asdsdcvnmas");
			list.add("asdcgasfas");
			list.add("asqazqadas");
			list.add("asbbbbbbbdas");
			list.add("asbbbbffffbbbdas");
			list.add("asbbbbbbdddbdas");
			list.add("asbbbbbbssssbdas");
			System.out.println(list);
			for (int i = list.size()-1; i>=0; i--) {
				if (i%2==0) {
					list.remove(i);
				}
			}
			System.out.println(list);
			/*
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
		*/}
}
