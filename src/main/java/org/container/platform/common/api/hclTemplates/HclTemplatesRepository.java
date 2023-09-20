package org.container.platform.common.api.hclTemplates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * HclTemplates Repository 인터페이스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.06.30
 **/
@Repository
@Transactional
public interface HclTemplatesRepository extends JpaRepository<HclTemplates, Long> {
    List<HclTemplates> findAllByProvider(String provider);
}
