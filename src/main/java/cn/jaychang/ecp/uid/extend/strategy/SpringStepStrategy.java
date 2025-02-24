package cn.jaychang.ecp.uid.extend.strategy;

import cn.jaychang.ecp.uid.extend.annotation.UidModelEnum;
import cn.jaychang.ecp.uid.leaf.SegmentIDGenImpl;
import cn.jaychang.ecp.uid.spring.ColumnMaxValueIncrementer;

/**
 * @类名称 SpringStrategy.java
 * @类描述 <pre>spring 分段批量Id策略(可配置asynLoadingSegment-异步标识)</pre>
 * @作者  庄梦蝶殇 linhuaichuan1989@126.com
 * @创建时间 2019年3月15日 下午7:48:58
 * @版本 1.0.0
 *
 * @修改记录
 * <pre>
 *     版本                       修改人 		修改日期 		 修改内容描述
 *     ----------------------------------------------
 *     1.0.0 	       庄梦蝶殇 	2019年3月15日             
 *     ----------------------------------------------
 * </pre>
 */
public class SpringStepStrategy extends MeituanLeafStrategy {
    @Override
    public UidModelEnum getName() {
        return UidModelEnum.SPRING_STEP;
    }
    
    @Override
    public SegmentIDGenImpl getSegmentService(String prefix) {
        SegmentIDGenImpl segmentService = generatorMap.get(prefix);
        if (null == segmentService) {
            synchronized (generatorMap) {
                if (null == segmentService) {
                    segmentService = new ColumnMaxValueIncrementer();
                    segmentService.setBizTag(prefix);
                }
                generatorMap.put(prefix, segmentService);
            }
        }
        return segmentService;
    }
    
}
