package org.container.platform.common.api.sshKeys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SshKeys Repository 인터페이스
 *
 * @author jjy
 * @version 1.0
 * @since 2023.12.26
 **/

@Repository
@Transactional
public interface SshKeysRepository extends JpaRepository<SshKeys, Long> {
    List<SshKeys> findAllByProvider(String provider);
    SshKeys findByName(@Param("name") String name);
}