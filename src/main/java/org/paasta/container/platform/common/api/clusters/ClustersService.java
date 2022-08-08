package org.paasta.container.platform.common.api.clusters;

import org.paasta.container.platform.common.api.common.CommonService;
import org.paasta.container.platform.common.api.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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


    /**
     * Instantiates a new Clusters service
     *
     * @param clustersRepository the cluster repository
     */
    @Autowired
    public ClustersService(CommonService commonService, ClustersRepository clustersRepository) {
        this.commonService = commonService;
        this.clustersRepository = clustersRepository;
    }

    /**
     * Clusters 정보 저장(Create Clusters Info)
     *
     * @param clusters the clusters
     * @return the clusters
     */
    public Clusters createClusters(Clusters clusters) {
        return clustersRepository.save(clusters);
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
        ClustersList clustersList = new ClustersList(clustersRepository.findAllByOrderByName());
        return (ClustersList) commonService.setResultModel(clustersList, Constants.RESULT_STATUS_SUCCESS);
    }

    public ClustersList getClustersListByUser(String userAuthId) {
        ClustersList clustersList = new ClustersList(clustersRepository.findClustersUsedByUser(Constants.HOST_CLUSTER_TYPE, defaultNamespace, userAuthId));
        return (ClustersList) commonService.setResultModel(clustersList, Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Host Clusters 정보 조회(Get Host Clusters Info)
     *
     * @return the clusters
     */
    public Clusters getHostClusters() {
        return clustersRepository.findByClusterType(Constants.HOST_CLUSTER_TYPE);
    }

    /**
     * Clusters 정보 수정(Update Clusters Info)
     *
     * @return the clusters
     */
    public Clusters updateClusters(Clusters clusters) {
        Clusters target = clustersRepository.findByClusterId(clusters.getClusterId());
        if(!clusters.getName().equals(target.getName()))target.setName(clusters.getName());
        if(!clusters.getDescription().equals(target.getDescription()))target.setDescription(clusters.getDescription());
        target = clustersRepository.save(target);

        return (Clusters) commonService.setResultModel(target, Constants.RESULT_STATUS_SUCCESS);
    }

}
