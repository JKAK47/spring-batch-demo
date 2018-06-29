package org.vincent.ch03.custjob.base.job01.bean;


import org.vincent.ch03.custjob.base.OutItem;

/**
 * @Package: org.vincent.custjob.base.job01.bean <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 */

public class Job01OutItem extends OutItem {
		private String rtnCode;
		private String reason;
		public Job01OutItem(){
		}
		public Job01OutItem(String rtnCode, String reason) {
				this.rtnCode = rtnCode;
				this.reason = reason;
		}

		public String getRtnCode() {
				return rtnCode;
		}

		public void setRtnCode(String rtnCode) {
				this.rtnCode = rtnCode;
		}

		public String getReason() {
				return reason;
		}

		public void setReason(String reason) {
				this.reason = reason;
		}

		@Override
		public String toString() {
				return "Job01OutItem{" +
								"rtnCode='" + rtnCode + '\'' +
								", reason='" + reason + '\'' +
								'}';
		}
}
