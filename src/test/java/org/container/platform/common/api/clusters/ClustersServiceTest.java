package org.container.platform.common.api.clusters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.container.platform.common.api.common.CommonService;
import org.container.platform.common.api.common.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Cluster Service Test 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2020.11.17
 **/
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yml")
public class ClustersServiceTest {
    private static final String CLUSTER = "cp-cluster";
    private static final String CLUSTER_API_URL = "111.111.111.111:6443";
    private static final String CLUSTER_ADMIN_TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6IktNWmgxVXB3ajgwS0NxZjFWaVZJVGVvTXJoWnZ5dG0tMGExdzNGZjBKX00ifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJwYWFzLWYxMGU3ZTg4LTQ4YTUtNGUyYy04Yjk5LTZhYmIzY2ZjN2Y2Zi1jYWFzIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6InN1cGVyLWFkbWluLXRva2VuLWtzbXo1Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6InN1cGVyLWFkbWluIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiMjMwZWQ1OGQtNzc0MC00MDI4LTk0MTEtYTM1MzVhMWM0NjU4Iiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50OnBhYXMtZjEwZTdlODgtNDhhNS00ZTJjLThiOTktNmFiYjNjZmM3ZjZmLWNhYXM6c3VwZXItYWRtaW4ifQ.nxnIJCOH_XVMK71s0gF8bgzSxA7g6_y7hGdboLvSqIAGf9J9AgG1DouP29uShK19fMsl9IdbGODPvtuiBz4QyGLPARZldmlzEyFG3k08UMNay1xX_oK-Fe7atMlYgvoGzyM_5-Zp5dyvnxE2skk524htMGHqW1ZwnHLVxtBg8AuGfMwLW1xahmktsNZDG7pRMasPsj73E85lfavMobBlcs4hwVcZU82gAg0SK1QVe7-Uc2ip_9doNo6_9rGW3FwHdVgUNAeCvPRGV0W1dKJv0IX5e_7fIPIznj2xXcZoHf3BnKfDayDIKJOCdsEsy_2NGi1tiD3UvzDDzZpz02T2sg";
    private static final String NAMESPACE = "cp-namespace";
    private static final String ALL_NAMESPACES = "all";
    private static final String USER_ID = "kpaas";
    private static final String ROLE = "container-platform-init-role";
    private static final String ADMIN_ROLE = "container-platform-admin-role";
    private static final String SECRET_NAME = "kpaas-token-jqrx4";
    private static final String TOKEN_NAME = "cp_admin";
    @Value("${cp.defaultNamespace}")
    private String defaultNamespace;

    private static Clusters createdCluster = null;
    private static ClustersList finalClustersList = null;
    private static ArrayList<Clusters> clustersList = null;

    private static Clusters finalHostCluster = null;


    @Mock
    ClustersRepository clustersRepository;

    @Mock
    CommonService commonService;

    @InjectMocks
    ClustersService clustersService;

    @Before
    public void setUp() {
        createdCluster = new Clusters();
        createdCluster.setClusterId(CLUSTER);
        createdCluster.setName(CLUSTER);
        createdCluster.setCreated("2020-11-05 13:26:24");
        createdCluster.setLastModified("2020-11-05 13:26:24");

        clustersList = new ArrayList<>();
        clustersList.add(createdCluster);
        finalClustersList = new ClustersList();
        finalClustersList.setItems(clustersList);
        finalClustersList.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        finalHostCluster = new Clusters();
        finalHostCluster.setClusterType(Constants.HOST_CLUSTER_TYPE);

    }

    @Test
    public void createClusters() {
        Clusters clusters = new Clusters();
        clusters.setName(CLUSTER);
        clusters.setClusterId(CLUSTER);

        when(clustersRepository.save(clusters)).thenReturn(createdCluster);
        when(commonService.setResultModel(createdCluster, Constants.RESULT_STATUS_SUCCESS)).thenReturn(createdCluster);

        Clusters finalClusters = clustersService.createClusters(clusters);
    }

    @Test
    public void createClusters_Ex() {
        Clusters clusters = new Clusters();
        clusters.setName(CLUSTER);
        clusters.setClusterId(CLUSTER);

        when(clustersRepository.save(clusters)).thenThrow(new NullPointerException());
        when(commonService.setResultModel(createdCluster, Constants.RESULT_STATUS_FAIL)).thenReturn(createdCluster);

        Clusters finalClusters = clustersService.createClusters(clusters);
    }

    @Test
    public void getClusters() {
        when(clustersRepository.findByClusterId(CLUSTER)).thenReturn(createdCluster);
        when(commonService.setResultModel(createdCluster, Constants.RESULT_STATUS_SUCCESS)).thenReturn(createdCluster);

        Clusters finalCluster = clustersService.getClusters(CLUSTER);
        assertEquals(finalCluster, createdCluster);
    }

//    @Test
//    public void getClusters_Ex() {
//        when(clustersRepository.findByClusterId(CLUSTER)).thenThrow(new NullPointerException());
//        when(commonService.setResultModel(createdCluster, Constants.RESULT_STATUS_FAIL)).thenReturn(createdCluster);
//
//        Clusters finalCluster = clustersService.getClusters(CLUSTER);
//    }

    @Test
    public void getClustersList() {
        when(clustersRepository.findAllByOrderByName()).thenReturn(clustersList);
        ClustersList createdClustersList = new ClustersList();
        createdClustersList.setItems(clustersList);
        when(commonService.setResultModel(createdClustersList, Constants.RESULT_STATUS_SUCCESS)).thenReturn(finalClustersList);

        ClustersList finalClusterList = clustersService.getClustersList();
        assertEquals(finalClusterList, finalClustersList);

    }

    @Test
    public void getClustersList_Ex() {
        when(clustersRepository.findAllByOrderByName()).thenThrow(new NullPointerException());
        ClustersList createdClustersList = new ClustersList();
        createdClustersList.setItems(clustersList);
        when(commonService.setResultModel(createdClustersList, Constants.RESULT_STATUS_FAIL)).thenReturn(finalClustersList);

        ClustersList finalClusterList = clustersService.getClustersList();

    }

    @Test
    public void getClustersListByUser() {

        when(clustersRepository.findClustersUsedByUser(Constants.AUTH_CLUSTER_ADMIN, USER_ID)).thenReturn(clustersList);
        when(commonService.setResultModel(clustersList, Constants.RESULT_STATUS_SUCCESS)).thenReturn(clustersList);

        clustersService.getClustersListByUser(USER_ID);
    }

    @Test
    public void getClustersListByUser_Ex() {

        when(clustersRepository.findClustersUsedByUser(Constants.AUTH_CLUSTER_ADMIN, USER_ID)).thenThrow(new NullPointerException());
        when(commonService.setResultModel(clustersList, Constants.RESULT_STATUS_FAIL)).thenReturn(clustersList);

        clustersService.getClustersListByUser(USER_ID);
    }

    @Test
    public void getHostClusters() {
        when(clustersRepository.findByClusterType(Constants.HOST_CLUSTER_TYPE)).thenReturn(finalHostCluster);
        when(commonService.setResultModel(finalHostCluster, Constants.RESULT_STATUS_SUCCESS));
        clustersService.getHostClusters();
        assertEquals(finalHostCluster.getClusterType(), Constants.HOST_CLUSTER_TYPE);
    }

    @Test
    public void getHostClusters_Ex() {
        when(clustersRepository.findByClusterType(Constants.HOST_CLUSTER_TYPE)).thenThrow(new NullPointerException());
        when(commonService.setResultModel(finalHostCluster, Constants.RESULT_STATUS_FAIL));
        clustersService.getHostClusters();
        assertEquals(finalHostCluster.getClusterType(), Constants.HOST_CLUSTER_TYPE);
    }

    @Test
    public void updateClusters() {
        createdCluster.setDescription("test");
        when(clustersRepository.findByClusterId(createdCluster.getClusterId())).thenReturn(createdCluster);
        when(clustersRepository.save(createdCluster)).thenReturn(createdCluster);
        when(commonService.setResultModel(createdCluster, Constants.RESULT_STATUS_SUCCESS)).thenReturn(createdCluster);

        Clusters finalClusters = clustersService.updateClusters(createdCluster);
    }

    @Test
    public void deleteClusters() {
        clustersService.deleteClusters("test");
    }
}