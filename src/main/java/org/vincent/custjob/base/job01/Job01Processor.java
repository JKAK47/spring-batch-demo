package org.vincent.custjob.base.job01;

import org.springframework.stereotype.Service;
import org.vincent.custjob.base.BaseItemProcessor;
import org.vincent.custjob.base.job01.bean.Job01Initem;
import org.vincent.custjob.base.job01.bean.Job01OutItem;

/**
 * @Package: org.vincent.custjob.base.job01 <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 */

@Service("job01Processor")
public class Job01Processor extends BaseItemProcessor<Job01Initem,Job01OutItem>{

		/**
		 * Process the provided item, returning a potentially modified or new item for continued
		 * processing.  If the returned result is null, it is assumed that processing of the item
		 * should not continue.
		 *
		 * @param item to be processed
		 * @return potentially modified or new item for continued processing, null if processing of the
		 * provided item should not continue.
		 * @throws Exception
		 */
		@Override
		public Job01OutItem process(Job01Initem item) throws Exception {
				System.out.println(item);
				return new Job01OutItem("job01outItem","----------");
		}
}
