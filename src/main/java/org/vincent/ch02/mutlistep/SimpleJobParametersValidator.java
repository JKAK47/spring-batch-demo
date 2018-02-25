package org.vincent.ch02.mutlistep;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

/**
 * @Package: org.vincent.mutlistep <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 */

public class SimpleJobParametersValidator implements JobParametersValidator {
		/**
		 * Check the parameters meet whatever requirements are appropriate, and
		 * throw an exception if not.
		 *
		 * @param parameters some {@link JobParameters}
		 * @throws JobParametersInvalidException if the parameters are invalid
		 */
		@Override
		public void validate(JobParameters parameters) throws JobParametersInvalidException {
					System.out.println("SimpleJobParametersValidator begin");
					System.out.println(parameters.toString());
		}
}
