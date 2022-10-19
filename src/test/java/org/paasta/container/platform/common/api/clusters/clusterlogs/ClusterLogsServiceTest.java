package org.paasta.container.platform.common.api.clusters.clusterlogs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.paasta.container.platform.common.api.common.CommonService;
import org.paasta.container.platform.common.api.common.Constants;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yml")
public class ClusterLogsServiceTest {

    @Mock
    ClusterLogsRepository clusterLogsRepository;
    @Mock
    CommonService commonService;
    @InjectMocks
    ClusterLogsService clusterLogsService;

    private static final String CLUSTER_ID = "cp-cluster";
    private static List<ClusterLogs> list = null;
    private static ClusterLogsList clusterLogsList = null;
    private static ClusterLogs clusterLogs = null;

    @Before
    public void setUp() throws Exception {
        list = new ArrayList<>();
        clusterLogsList = new ClusterLogsList();
        clusterLogs = new ClusterLogs();
    }

    @Test
    public void getClusterLogs() {
        list.add(clusterLogs);
        clusterLogsList.setItems(list);
        when(clusterLogsRepository.findClustersLogsByClusterIdOrderByProcessNoDesc(CLUSTER_ID)).thenReturn(list);
        when(commonService.setResultModel(clusterLogsList, Constants.RESULT_STATUS_SUCCESS)).thenReturn(clusterLogsList);

        clusterLogsService.getClusterLogs(CLUSTER_ID);
    }

    @Test
    public void getClusterLogs_INVALID() {
        when(clusterLogsRepository.findClustersLogsByClusterIdOrderByProcessNoDesc(CLUSTER_ID)).thenReturn(list);
        when(commonService.setResultModel(clusterLogsList, Constants.RESULT_STATUS_FAIL)).thenReturn(clusterLogsList);

        clusterLogsService.getClusterLogs(CLUSTER_ID);
    }
}