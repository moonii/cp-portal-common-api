package org.container.platform.common.api.clusters;

import lombok.Data;

import java.util.List;

/**
 * Clusters List Model 클래스
 *
 * @author kjhoon
 * @version 1.0
 * @since 2022.05.31
 */
@Data
public class ClustersList {
    private String resultCode;
    private String resultMessage;
    private List<Clusters> items;

    public ClustersList() {
    }

    public ClustersList(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public ClustersList(List<Clusters> items) {
        this.items = items;
    }


}
