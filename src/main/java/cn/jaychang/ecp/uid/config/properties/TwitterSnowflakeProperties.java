package cn.jaychang.ecp.uid.config.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * twitter snowflake 属性配置类
 *
 * @author jaychang
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = TwitterSnowflakeProperties.PREFIX)
public class TwitterSnowflakeProperties extends WorkerIdAssignerProperties implements Serializable {

    private static final long serialVersionUID = -552051843461413001L;

    /**
     * Prefix for the TwitterSnowflakeProperties.
     */
    public static final String PREFIX = "ecp.uid.twitter-snowflake";

    /**
     * 数据中心ID 可不配
     */
    private Long datacenterId;

    /**
     * 工作节点ID 可不配(如果配了workerIdAssigner，优先使用workerIdAssigner)
     */
    private Long workerId;

}
