package org.vincent.mutlistep;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:multistep/spring/Application.xml"})
public class MultiStepTest extends AbstractJUnit4SpringContextTests {

		@Autowired
		private JobLauncher joblauncher;
		@Autowired
		@Qualifier("mutltistepjob")
		private Job job;

		@Test
		public void testMultiStep() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
				JobParametersBuilder builder = new JobParametersBuilder();
				LocalDate localDate= LocalDate.parse("2018-03-02").plusDays(1);
				builder.addDate("start", Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				joblauncher.run(job, builder.toJobParameters());

		}
}
