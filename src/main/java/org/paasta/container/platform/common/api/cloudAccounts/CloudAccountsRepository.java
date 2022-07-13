package org.paasta.container.platform.common.api.cloudAccounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CloudAccounts Repository 인터페이스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.06.14
 **/

@Repository
@Transactional
public interface CloudAccountsRepository extends JpaRepository<CloudAccounts, Long> {
    List<CloudAccounts> findAllByProvider(String provider);
}
