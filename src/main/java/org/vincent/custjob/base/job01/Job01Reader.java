package org.vincent.custjob.base.job01;

import java.util.ArrayList;
import java.util.List;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Service;
import org.vincent.custjob.base.SimpleItemReader;
import org.vincent.custjob.base.job01.bean.Job01Initem;

/**
 * @Package: org.vincent.custjob.base.job01 <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 */

@Service("job01Reader")
public class Job01Reader extends SimpleItemReader<Job01Initem> implements StepExecutionListener{
		/**
		 * Initialize the state of the listener with the {@link StepExecution} from
		 * the current scope.
		 *
		 * @param stepExecution
		 */
		@Override
		public void beforeStep(StepExecution stepExecution) {
				System.out.println("job01Reader beforeStep runnig");
				List<Job01Initem> initems=new ArrayList();
				initems.add(new Job01Initem());
				initems.add(new Job01Initem("vincent","Job01"));
				initems.add(new Job01Initem("xx","Job01"));
				initems.add(new Job01Initem("yy","Job01"));
				list=initems;
				System.out.println("job01Reader beforeStep runnig");
		}

		/**
		 * Give a listener a chance to modify the exit status from a step. The value
		 * returned will be combined with the normal exit status using
		 * {@link ExitStatus#and(ExitStatus)}.
		 * <p>
		 * Called after execution of step's processing logic (both successful or
		 * failed). Throwing exception in this method has no effect, it will only be
		 * logged.
		 *
		 * @param stepExecution
		 * @return an {@link ExitStatus} to combine with the normal value. Return
		 * null to leave the old value unchanged.
		 */
		@Override
		public ExitStatus afterStep(StepExecution stepExecution) {
				if (stepExecution.getStatus()== BatchStatus.COMPLETED){
						System.out.println("成功执行Job01");
				}
				return ExitStatus.COMPLETED;
		}
}
