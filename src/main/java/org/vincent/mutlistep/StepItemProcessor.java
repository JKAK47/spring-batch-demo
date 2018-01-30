package org.vincent.mutlistep;

import org.springframework.batch.item.ItemProcessor;

/**
 * @Package: org.vincent.mutlistep <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 */

public class StepItemProcessor implements ItemProcessor<Student,Student>{
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
		public Student process(Student item) throws Exception {
				System.out.println("Processing.... "+ item);
				return item;
		}
}
