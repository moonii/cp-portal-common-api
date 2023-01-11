package org.paasta.container.platform.common.api.clusters.metrics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface ClusterStatusRepository extends JpaRepository<ClusterStatus, Long> {

    @Query(value = "DELETE FROM cp_metric_cluster_status WHERE cluster_id = :cluster ;", nativeQuery = true)
    void deleteAllByClusterId(@Param("cluster") String cluster);
}
