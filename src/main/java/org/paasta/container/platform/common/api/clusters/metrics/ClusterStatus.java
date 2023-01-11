package org.paasta.container.platform.common.api.clusters.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "cp_metric_cluster_status")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClusterStatus {

    @Id
    @Column(name = "cluster_id")
    private String clusterId;

}


