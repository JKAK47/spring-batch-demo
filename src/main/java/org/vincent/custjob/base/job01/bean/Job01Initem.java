package org.vincent.custjob.base.job01.bean;

import org.vincent.custjob.base.InItem;

/**
 * @Package: org.vincent.custjob.base.job01.bean <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/2/8 0:18 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/2/8. <br/>
 */

public class Job01Initem extends InItem{
		private  String name;
		private  String jobName;
		public  Job01Initem(){
		}
		public Job01Initem(String name, String jobName) {
				this.name = name;
				this.jobName = jobName;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public String getJobName() {
				return jobName;
		}

		public void setJobName(String jobName) {
				this.jobName = jobName;
		}

		@Override
		public String toString() {
				return "Job01Initem{" +
								"name='" + name + '\'' +
								", jobName='" + jobName + '\'' +
								'}';
		}
}
