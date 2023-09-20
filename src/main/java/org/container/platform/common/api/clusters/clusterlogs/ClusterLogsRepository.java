package org.container.platform.common.api.clusters.clusterlogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface ClusterLogsRepository extends JpaRepository<ClusterLogs, String> {
    List<ClusterLogs> findClustersLogsByClusterIdOrderByProcessNoDesc(String clusterId);

    void deleteAllByClusterId(String clusterId);
}
