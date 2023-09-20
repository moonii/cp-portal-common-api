package org.container.platform.common.api.clusters.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cp_metric_node_status")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NodeStatus {

    @Id
    @Column(name = "cluster_id")
    private String clusterId;

}


