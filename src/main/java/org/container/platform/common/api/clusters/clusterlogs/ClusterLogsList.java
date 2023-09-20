package org.container.platform.common.api.clusters.clusterlogs;

import lombok.Data;

import java.util.List;

@Data
public class ClusterLogsList {
    private String resultCode;
    private String resultMessage;
    private List<ClusterLogs> items;
}
