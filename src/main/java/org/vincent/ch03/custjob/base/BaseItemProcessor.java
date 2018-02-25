package org.vincent.ch03.custjob.base;

import org.springframework.batch.item.ItemProcessor;

/**
 * @Package: org.vincent.custjob.base <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 */

public abstract class BaseItemProcessor<I extends InItem, O extends OutItem> implements ItemProcessor<I, O>{

}
