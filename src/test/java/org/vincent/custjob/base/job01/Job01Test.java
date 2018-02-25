package org.vincent.custjob.base.job01;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Job01Processor Tester.
 *
 * @author <PengRong>
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:custjob/job01/Application.xml"})
public class Job01Test extends AbstractJUnit4SpringContextTests {

		@Autowired
		private JobLauncher joblauncher;
		@Autowired
		@Qualifier("job01")
		private Job job;

		@Test
		public void testProcess() throws Exception {
				JobParametersBuilder builder = new JobParametersBuilder();
				builder.addString("jobname","custjob");
				JobExecution execution = joblauncher.run(job, builder.toJobParameters());
				System.out.println("Exit Status : " + execution.getStatus());
				Assert.assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		}


} 
