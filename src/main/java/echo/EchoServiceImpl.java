package echo;

/**
 * @Package: com.vincent.echo <br/>
 * @Description： Rpc 提供的接口服务实现类 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/25 0:46 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/25. <br/>
 */

public class EchoServiceImpl implements  EchoService{
		@Override
		public String echo(String echo) {
				return "I love you . echo >> "+echo;
		}
}
