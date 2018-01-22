package org.vincent.helloworld01;

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
 *
 * @author PengRong
 */
public class JobLaunchTest {
		public static void main(String[] args) {
				@SuppressWarnings("resource")
				ApplicationContext context = new ClassPathXmlApplicationContext("demo-1/spring/Application.xml");
				JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
				Job job = (Job) context.getBean("helloWorldJob");
				try {
						System.out.println("start launcher");
						launcher.run(job, new JobParameters());
						System.out.println("stop launcher");
				} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
								| JobParametersInvalidException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				System.out.println("debug");
		}

}
