package cn.jaychang.ecp.uid.extend.strategy;

import java.util.HashMap;
import java.util.Map;

import cn.jaychang.ecp.uid.extend.annotation.UidModelEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import cn.jaychang.ecp.uid.baidu.UidGenerator;

/**
 * baidu uid生成策略
 * @类名称 BaiduUidStrategy.java
 * @类描述 <pre>baidu uid生成策略</pre>
 * @作者 庄梦蝶殇 linhuaichuan1989@126.com
 * @创建时间 2018年4月27日 下午8:47:27
 * @版本 1.00
 *
 * @修改记录
 * <pre>
 *     版本                       修改人 		修改日期 		 修改内容描述
 *     ----------------------------------------------
 *     1.00 	庄梦蝶殇 	2018年4月27日             
 *     ----------------------------------------------
 * </pre>
 */
public class BaiduUidStrategy implements IUidStrategy, ApplicationContextAware {
    
    private static Map<String, UidGenerator> generatorMap = new HashMap<>();
    
    private UidGenerator uidGenerator;

    private ApplicationContext applicationContext;

    @Override
    public UidModelEnum getName() {
        return UidModelEnum.BAIDU_UID;
    }
    
    /**
     * 获取uid生成器
     * @方法名称 getUidGenerator
     * @功能描述 <pre>获取uid生成器</pre>
     * @param group 组名
     * @return uid生成器
     */
    public UidGenerator getUidGenerator(String group) {
        if (StringUtils.isEmpty(group)) {
            return uidGenerator;
        }
        UidGenerator generator = generatorMap.get(group);
        if (null == generator) {
            synchronized (generatorMap) {
                if (null == generator) {
                    generator = getGenerator();
                }
                generatorMap.put(group, generator);
            }
        }
        return generator;
    }
    
    @Override
    public long getUID(String group) {
        return getUidGenerator(group).getUID();
    }
    
    @Override
    public String parseUID(long uid, String group) {
        return getUidGenerator(group).parseUID(uid);
    }
    
    /**
     * @方法名称 getGenerator
     * @功能描述 <pre>多实例返回uidGenerator(返回值不重要，动态注入)</pre>
     * @return
     */
    public UidGenerator getGenerator() {
        return applicationContext.getBean(UidGenerator.class);
    }

    public UidGenerator getUidGenerator() {
        return uidGenerator;
    }

    public void setUidGenerator(UidGenerator uidGenerator) {
        this.uidGenerator = uidGenerator;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
