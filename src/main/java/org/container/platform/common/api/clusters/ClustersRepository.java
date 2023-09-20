package org.container.platform.common.api.clusters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clusters Repository 인터페이스
 *
 * @author hrjin
 * @version 1.0
 * @since 2020.11.04
 **/
@Repository
@Transactional
public interface ClustersRepository extends JpaRepository<Clusters, Long> {
    Clusters findByClusterId(String clusterId);

    void deleteByClusterId(String clusterId);

    List<Clusters> findAllByOrderByName();

    Clusters findByClusterType(String clusterType);

    @Query(value = "SELECT DISTINCT b.*" +
            "FROM cp_users a, cp_clusters b " +
            "WHERE a.cluster_id = b.cluster_id " +
            "AND a.user_type = :authClusterAdmin  " +
            "AND a.user_auth_id = :userAuthId " +
            "ORDER BY b.name; ", nativeQuery = true)
    List<Clusters> findClustersUsedByUser(@Param("authClusterAdmin") String authClusterAdmin, @Param("userAuthId") String userAuthId);

}
