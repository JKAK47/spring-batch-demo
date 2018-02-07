package org.vincent.mutlistep.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
 * @Package: org.vincent.mutlistep.listener <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/2/5 1:04 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/2/5. <br/>
 */

public class CustomJobExecutionListener extends JobExecutionListenerSupport {
		@Override
		public void afterJob(JobExecution jobExecution) {
				super.afterJob(jobExecution);
				System.out.println("ExitStatus: " + jobExecution.getExitStatus().toString());
		}

		@Override
		public void beforeJob(JobExecution jobExecution) {
				super.beforeJob(jobExecution);
				System.out.println("jobId: " + jobExecution.getJobId() + " jobtoString: " + jobExecution.toString());
		}
}