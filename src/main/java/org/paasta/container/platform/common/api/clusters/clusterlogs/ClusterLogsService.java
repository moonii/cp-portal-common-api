package org.paasta.container.platform.common.api.clusters.clusterlogs;

import org.paasta.container.platform.common.api.common.CommonService;
import org.paasta.container.platform.common.api.common.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClusterLogsService {
    private final ClusterLogsRepository clusterLogsRepository;
    private final CommonService commonService;

    ClusterLogsService(ClusterLogsRepository clusterLogsRepository, CommonService commonService) {
        this.clusterLogsRepository = clusterLogsRepository;
        this.commonService = commonService;
    }


    public ClusterLogsList getClusterLogs(String clusterId) {
        List<ClusterLogs> clustersLogsItemList = clusterLogsRepository.findClustersLogsByClusterIdOrderByProcessNoDesc(clusterId);
        if (clustersLogsItemList.size() > 0) {
            ClusterLogsList clustersLogsList = new ClusterLogsList();
            clustersLogsList.setItems(clustersLogsItemList);
            return (ClusterLogsList) commonService.setResultModel(clustersLogsList, Constants.RESULT_STATUS_SUCCESS);
        }

        ClusterLogsList clusterLogsList = (ClusterLogsList) commonService.setResultModel(new ClusterLogsList(), Constants.RESULT_STATUS_FAIL);
        clusterLogsList.setResultMessage(Constants.DATA_NOT_FOUND_MESSAGE);
        return clusterLogsList;
    }
}
