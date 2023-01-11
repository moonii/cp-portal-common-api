package org.paasta.container.platform.common.api.clusters;

import org.paasta.container.platform.common.api.clusters.clusterlogs.ClusterLogsRepository;
import org.paasta.container.platform.common.api.clusters.metrics.ClusterStatusRepository;
import org.paasta.container.platform.common.api.clusters.metrics.NodeStatusRepository;
import org.paasta.container.platform.common.api.common.CommonService;
import org.paasta.container.platform.common.api.common.Constants;
import org.paasta.container.platform.common.api.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clusters Service 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2020.11.04
 **/
@Service
public class ClustersService {

    @Value("${cp.defaultNamespace}")
    private String defaultNamespace;
    private final CommonService commonService;
    private final ClustersRepository clustersRepository;
    private final UsersRepository usersRepository;
    private final ClusterLogsRepository clusterLogsRepository;
    private final ClusterStatusRepository clusterStatusRepository;
    private final NodeStatusRepository nodeStatusRepository;

    /**
     * Instantiates a new Clusters service
     *
     * @param clustersRepository the cluster repository
     */
    @Autowired
    public ClustersService(CommonService commonService, ClustersRepository clustersRepository,
                           UsersRepository usersRepository, ClusterLogsRepository clusterLogsRepository,
                           ClusterStatusRepository clusterStatusRepository, NodeStatusRepository nodeStatusRepository) {
        this.commonService = commonService;
        this.clustersRepository = clustersRepository;
        this.usersRepository = usersRepository;
        this.clusterLogsRepository = clusterLogsRepository;
        this.clusterStatusRepository = clusterStatusRepository;
        this.nodeStatusRepository = nodeStatusRepository;
    }

    /**
     * Clusters 정보 저장(Create Clusters Info)
     *
     * @param clusters the clusters
     * @return the clusters
     */
    @Transactional
    public Clusters createClusters(Clusters clusters) {
        Clusters createdClusters = new Clusters();
        try {
            createdClusters = clustersRepository.save(clusters);
        } catch (Exception e) {
            createdClusters.setResultMessage(e.getMessage());
            return (Clusters) commonService.setResultModel(createdClusters, Constants.RESULT_STATUS_FAIL);
        }
        return (Clusters) commonService.setResultModel(createdClusters, Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Clusters 정보 조회(Get Clusters Info)
     *
     * @param clusterId the cluster id
     * @return the clusters
     */
    public Clusters getClusters(String clusterId) {
        return clustersRepository.findByClusterId(clusterId);
    }

    /**
     * Clusters 목록 조회(Get Clusters List)
     *
     * @return the clustersList
     */
    public ClustersList getClustersList() {
        ClustersList clustersList = new ClustersList();
        try {
            clustersList = new ClustersList(clustersRepository.findAllByOrderByName());
        } catch (Exception e) {
            clustersList.setResultMessage(e.getMessage());
            return (ClustersList) commonService.setResultModel(clustersList, Constants.RESULT_STATUS_FAIL);
        }
        return (ClustersList) commonService.setResultModel(clustersList, Constants.RESULT_STATUS_SUCCESS);
    }

    public ClustersList getClustersListByUser(String userAuthId) {
        ClustersList clustersList = new ClustersList();
        try {
            clustersList = new ClustersList(clustersRepository.findClustersUsedByUser(Constants.AUTH_CLUSTER_ADMIN, userAuthId));
        } catch (Exception e) {
            clustersList.setResultMessage(e.getMessage());
            return (ClustersList) commonService.setResultModel(clustersList, Constants.RESULT_STATUS_FAIL);
        }
        return (ClustersList) commonService.setResultModel(clustersList, Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Host Clusters 정보 조회(Get Host Clusters Info)
     *
     * @return the clusters
     */
    public Clusters getHostClusters() {
        Clusters clusters = new Clusters();
        try {
            clusters = clustersRepository.findByClusterType(Constants.HOST_CLUSTER_TYPE);
        } catch (Exception e) {
            clusters.setResultMessage(e.getMessage());
            return (Clusters) commonService.setResultModel(clusters, Constants.RESULT_STATUS_FAIL);
        }
        return (Clusters) commonService.setResultModel(clusters, Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Clusters 정보 수정(Update Clusters Info)
     *
     * @return the clusters
     */
    @Transactional
    public Clusters updateClusters(Clusters clusters) {
        Clusters target;
        try {
            target = clustersRepository.findByClusterId(clusters.getClusterId());
            if (!clusters.getName().equals(target.getName())) target.setName(clusters.getName());
            if (!clusters.getDescription().equals(target.getDescription()))
                target.setDescription(clusters.getDescription());
            target = clustersRepository.save(target);
        } catch (Exception e) {
            clusters.setResultMessage(e.getMessage());
            return (Clusters) commonService.setResultModel(clusters, Constants.RESULT_STATUS_FAIL);
        }

        return (Clusters) commonService.setResultModel(target, Constants.RESULT_STATUS_SUCCESS);
    }


    public Clusters deleteClusters(String cluster) {
        try {
            // cp-clusters 데이터 삭제
            clustersRepository.deleteByClusterId(cluster);

            // cp_users 데이터 삭제
            usersRepository.deleteAllByClusterId(cluster);

            // cp_cluster_log 데이터 삭제
            clusterLogsRepository.deleteAllByClusterId(cluster);

            // cp_metric_cluster_status 데이터 삭제
            clusterStatusRepository.deleteAllByClusterId(cluster);

            // cp_metric_node_status 삭제
            nodeStatusRepository.deleteAllByClusterId(cluster);
        }
        catch (Exception e) {
            return (Clusters) commonService.setResultModel(new Clusters(), Constants.RESULT_STATUS_FAIL);
        }

        return (Clusters) commonService.setResultModel(new Clusters(), Constants.RESULT_STATUS_SUCCESS);
    }

}
