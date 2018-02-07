package org.vincent.csv;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * csv 数据处理job测试类
 * @author pengrong
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:demo-csv/spring/Application.xml"})
public class CsvJobLaunchTest extends AbstractJUnit4SpringContextTests{

	@Autowired
	private JobLauncher joblauncher;
	@Autowired
	@Qualifier("csvjob")
	private Job job;
	@Test
	public void testCsvJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobExecution execution =joblauncher.run(job, new  JobParametersBuilder().toJobParameters());
		System.out.println("Exit Status : " + execution.getStatus());
		Assert.assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
	}



}
