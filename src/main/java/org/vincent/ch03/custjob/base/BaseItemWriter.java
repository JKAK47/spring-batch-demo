package org.vincent.ch03.custjob.base;

import org.springframework.batch.item.ItemWriter;

/**
 * @Package: org.vincent.custjob.base <br/>
 * @Description： Writer写入类简单实现 <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 */

public abstract class BaseItemWriter<T extends OutItem> implements ItemWriter<T>{

}
