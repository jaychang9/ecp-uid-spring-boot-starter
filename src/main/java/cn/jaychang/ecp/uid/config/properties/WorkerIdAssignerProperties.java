package cn.jaychang.ecp.uid.config.properties;

import cn.jaychang.ecp.uid.worker.AbstractIntervalWorkId;
import cn.jaychang.ecp.uid.worker.enums.WorkerIdAssignerEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * worker id 分配器属性配置类
 *
 * @author jaychang
 */
@Data
@Accessors(chain = true)
public class WorkerIdAssignerProperties implements Serializable {
    private static final long serialVersionUID = -5969365082580407201L;
    /**
     * worker id assigner. allow values: zk,db,redis,simple
     */
    private WorkerIdAssignerEnum workerIdAssigner;

    /**
     * heartbeat interval(unit millisecond). default value is 3000ms  (only use zk or redis work id assigner,this field is effective)
     */
    private Long heartbeatInterval = 3000L;

    /**
     * pidPort:heartbeat port (only use zk or redis work id assigner,this field is effective)
     */
    private Integer pidPort;


    /**
     * zookeeper address, when workerIdAssigner equals zk, it must be set
     */
    private String zkAddr = "localhost:2181";
}
