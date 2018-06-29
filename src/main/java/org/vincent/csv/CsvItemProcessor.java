package org.vincent.csv;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @Package: org.vincent.csv <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/24 1:00 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/24. <br/>
 */
@Component("csvItemProcessor")
public class CsvItemProcessor implements ItemProcessor<Student, Student> {

		/**
		 * 对取到的数据进行简单的处理。
		 *Student :  id,name,age,score
		 * @param student
		 *            处理前的数据。
		 * @return 处理后的数据。
		 * @exception Exception
		 *                处理是发生的任何异常。
		 */
		@Override
		public Student process(Student student) throws Exception {
				System.out.println(student);
        /* 合并ID和名字 */
				student.setName(student.getId() + "-" + student.getName());
        /* 年龄加2 */
				student.setAge(student.getAge()+2);
        /* 分数加10 */
				student.setScore(student.getScore() + 10);
        /* 将处理后的结果传递给writer */
				return student;
		}
}