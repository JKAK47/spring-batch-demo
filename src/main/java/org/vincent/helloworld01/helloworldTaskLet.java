package org.vincent.helloworld01;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @Package: org.vincent <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/23 0:07 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/23. <br/>
 */

public class helloworldTaskLet implements Tasklet {
		private String Msg;

		public String getMsg() {
				return Msg;
		}

		public void setMsg(String msg) {
				Msg = msg;
		}

		@Override
		public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
				System.out.println(getMsg());
				return RepeatStatus.FINISHED;
		}
}
