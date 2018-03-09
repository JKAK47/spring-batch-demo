package org.vincent.ch02.mutlistep.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
 * @Package: org.vincent.mutlistep.listener <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 */

public class SystemOutJobExecutionListener extends JobExecutionListenerSupport {

		/* (non-Javadoc)
		 * @see org.springframework.batch.core.JobExecutionListener#beforeJob(org.springframework.batch.core.JobExecution)
		 */
		@Override
		public void beforeJob(JobExecution jobExecution) {
				System.out.println("JobExecution create time:" + jobExecution.getCreateTime()+"\t jobName: "+jobExecution.getJobInstance().getJobName());
//		throw new RuntimeException("listener make error!");
		}

		/* (non-Javadoc)
		 * @see org.springframework.batch.core.JobExecutionListener#afterJob(org.springframework.batch.core.JobExecution)
		 */
		@Override
		public void afterJob(JobExecution jobExecution) {
				System.out.println("Job execute state:" + jobExecution.getStatus().toString());
		}

}