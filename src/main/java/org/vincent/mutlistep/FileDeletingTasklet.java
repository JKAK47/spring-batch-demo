package org.vincent.mutlistep;

import java.io.File;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

/**
 * @Package: org.vincent.mutlistep <br/>
 * @Description： 用于删除，clean某一个目录下的所有文件。 <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 *
 */

public class FileDeletingTasklet implements Tasklet,InitializingBean{

			private Resource directory;

		public void setDirectory(Resource directory) {
				this.directory = directory;
		}
		public Resource getDirectory() {
				return directory;
		}

		/**
		 * Given the current context in the form of a step contribution, do whatever
		 * is necessary to process this unit inside a transaction. Implementations
		 * return {@link RepeatStatus#FINISHED} if finished. If not they return
		 * {@link RepeatStatus#CONTINUABLE}. On failure throws an exception.
		 *
		 * @param contribution mutable state to be passed back to update the current
		 *                     step execution
		 * @param chunkContext attributes shared between invocations but not between
		 *                     restarts
		 * @return an {@link RepeatStatus} indicating whether processing is
		 * continuable.
		 */
		@Override
		public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				File dir = directory.getFile();
				Assert.state(dir.isDirectory());

				File[] files = dir.listFiles();
				for (int i = 0; i < files.length; i++) {
						boolean deleted = files[i].delete();
						if (!deleted) {
								throw new UnexpectedJobExecutionException(
												"Could not delete file " + files[i].getPath());
						} else {
								System.out.println(files[i].getPath() + " is deleted!");
						}
				}
				return RepeatStatus.FINISHED;
		}

		/**
		 * Invoked by a BeanFactory after it has set all bean properties supplied
		 * (and satisfied BeanFactoryAware and ApplicationContextAware).
		 * <p>This method allows the bean instance to perform initialization only
		 * possible when all bean properties have been set and to throw an
		 * exception in the event of misconfiguration.
		 *
		 * @throws Exception in the event of misconfiguration (such
		 *                   as failure to set an essential property) or if initialization fails.
		 */
		@Override
		public void afterPropertiesSet() throws Exception {
				Assert.notNull(directory,"directory must be set");
		}
}
