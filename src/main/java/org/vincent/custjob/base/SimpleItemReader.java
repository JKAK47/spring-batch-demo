package org.vincent.custjob.base;

import java.util.List;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class SimpleItemReader<T extends InItem> implements ItemReader<T> {

		protected List<? extends InItem> list;

		public void SimpleItemReader() {

		}

		@Override
		public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
				// TODO Auto-generated method stub
				if (list != null && list.size() > 0)
						return (T) list.remove(list.size() - 1);
				else {
						return  null;
				}
		}

}
