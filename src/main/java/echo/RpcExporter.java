package echo;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Package: com.vincent.echo <br/>
 * @Description： 将本地的服务发布出去被客户端通过网络调用。 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/25 0:47 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/25. <br/>
 * RpcExporter
 * 首先需要监听网络请求
 * 将客户发送过来的服务访问请求封装成一个Runnable任务类型
 * 然后对客户端发来的请求通过线程池处理
 *
 */

public class RpcExporter {
		public  static Executor executor=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		public  static void  export(String host, int port) throws IOException {
				ServerSocket socket=new ServerSocket();
				socket.bind(new InetSocketAddress(host, port));
				System.out.println(socket.getInetAddress().getHostName()+" bind 成功");
				try {
						while (true){
								executor.execute(new  ExporterTask(socket.accept()));
						}
				}finally {
						if (socket!=null)socket.close();
				}
		}

		private static class ExporterTask implements Runnable {
				private  Socket socket;
				public ExporterTask(Socket accept) {
						System.out.println("hello > "+accept.getInetAddress());
						this.socket=accept;
				}

				@Override
				public void run() {
						ObjectInputStream inputStream=null;
						ObjectOutputStream outputStream=null;
						try {

								/**
								 * 读取字节流安装 通信双方约定的数据字节流格式解析。
								 * （接口名 + 方法名 + 方法入参类型 + 方法入参值 ）的形式组织参数
								 */
								inputStream=new ObjectInputStream(socket.getInputStream());
								String interfaceName=inputStream.readUTF();
								Class<?> service =Class.forName(interfaceName);//接口类
								String methodName=inputStream.readUTF();// 方法名
								Class<?>[] paramTypes= (Class<?>[]) inputStream.readObject();// 接口入参类型
								Object[] arguments= (Object[]) inputStream.readObject();// 接口入参值
								Method method=service.getMethod(methodName,paramTypes);//获取方法签名
								Object result=method.invoke(service.newInstance(),arguments);//通过反射方式调用服务
								outputStream=new ObjectOutputStream(socket.getOutputStream());
								outputStream.writeObject(result);
						}catch (Exception e){
						e.printStackTrace();
						}finally {
							if (inputStream !=null) {
									try {
											inputStream.close();
									} catch (IOException e) {
											e.printStackTrace();
									}
							}

							if (outputStream!=null){
									try {
											outputStream.close();
									} catch (IOException e) {
											e.printStackTrace();
									}
							}
							if (socket!=null) {
									try {
											socket.close();
									} catch (IOException e) {
											e.printStackTrace();
									}
							}
						}
				}
		}
}
