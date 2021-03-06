package org.vincent.csv;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring配置的方式取得JobLauncher和Job对象，然后由JobLauncher的run方法启动job，
 * 参数JobParameters是标志job的一些参数，处理结束后，控制台输出处理结果。
 *http://www.mkyong.com/spring-batch/spring-batch-multiresourceitemreader-example/
 * @author PengRong
 */
public class JobLaunchTest {
		public static void main(String[] args) throws Exception {
				System.out.println("batch start");
				@SuppressWarnings("resource")
				ApplicationContext context = new ClassPathXmlApplicationContext("demo-csv/spring/Application.xml");
				//获取到 jobLauncher
				JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
				// 获取到 Job
				Job job = (Job) context.getBean("csvjob");
				try {
						launcher.run(job, new JobParameters());
				} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
								| JobParametersInvalidException e) {
						e.printStackTrace();
						throw new Exception(e.getMessage(), e);
				}
				System.out.println("batch stop");
		}

}
