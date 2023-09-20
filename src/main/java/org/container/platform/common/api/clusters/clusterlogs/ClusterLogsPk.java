package org.container.platform.common.api.clusters.clusterlogs;

import java.io.Serializable;

public class ClusterLogsPk implements Serializable {
    private static final long serialVersionUID = 1L;
    private String clusterId;
    private int processNo;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public int getProcessNo() {
        return processNo;
    }

    public void setProcessNo(int processNo) {
        this.processNo = processNo;
    }

}
