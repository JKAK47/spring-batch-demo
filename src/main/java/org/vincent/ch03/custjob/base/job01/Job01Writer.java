package org.vincent.ch03.custjob.base.job01;

import java.util.List;
import org.springframework.stereotype.Service;
import org.vincent.ch03.custjob.base.BaseItemWriter;
import org.vincent.ch03.custjob.base.OutItem;
import org.vincent.ch03.custjob.base.job01.bean.Job01OutItem;

/**
 * @Package: org.vincent.custjob.base.job01 <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 */

@Service("job01Writer")
public class Job01Writer extends BaseItemWriter<OutItem> {

		/**
		 * Process the supplied data element. Will not be called with any null items
		 * in normal operation.
		 *
		 * @param items items to be written
		 * @throws Exception if there are errors. The framework will catch the
		 *                   exception and convert or rethrow it as appropriate.
		 */
		@Override
		public void write(List<? extends OutItem> items) throws Exception {
				for (OutItem outItem : items){
						System.out.println(((Job01OutItem) outItem).toString());
				}

		}
}
