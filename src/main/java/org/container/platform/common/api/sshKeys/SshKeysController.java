package org.container.platform.common.api.sshKeys;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * SshKeys Controller 클래스
 *
 * @author jjy
 * @version 1.0
 * @since 2023.12.26
 **/
@RestController
@RequestMapping(value = "/sshKeys")
public class SshKeysController {

    private final SshKeysService sshKeysService;

    /**
     * Instantiates a new sshKeys controller
     *
     * @param sshKeysService the sshKeys service
     */
    SshKeysController(SshKeysService sshKeysService) { this.sshKeysService = sshKeysService;
    }

    /**
     * SshKeys 목록 조회(Get SshKeys List)
     *
     * @return the sshKeysList
     */
    @GetMapping
    public SshKeysList getSshKeysList() { return
            sshKeysService.getSshKeysList();}

    /**
     * Provider별 SshKeys 목록 조회(Get SshKeys List By Provider)
     *
     * @return the sshKeys
     */
    @ApiOperation(value="SshKeys 타입 별 목록 조회(Get SshKeys List By Provider)", nickname="getSshKeysListByProvider")
    @GetMapping(value = "/provider/{provider:.+}")
    public SshKeysList getSshKeysListByProvider(@PathVariable String provider) {
        return sshKeysService.getSshKeysListByProvider(provider);
    }

    /**
     * SshKeys 정보 조회(Get SshKeys Info)
     *
     * @param id the SshKeys id
     * @return the sshKeys
     */
    @GetMapping(value = "/{id:.+}")
    public SshKeys getSshKeys(@PathVariable Long id) {
        return sshKeysService.getSshKeys(id);
    }

    /**
     * SshKeys 정보 저장(Create SshKeys Info)
     *
     * @param sshKeys the sshKeys
     * @return the sshKeys
     */
    @PostMapping
    public SshKeys createSshKeys(@RequestBody SshKeys sshKeys) {
        return sshKeysService.createSshKeys(sshKeys);
    }

    /**
     * SshKeys 정보 삭제(Delete SshKeys Info)
     *
     * @param id the id
     * @return the sshKeys
     */
    @DeleteMapping(value = "/{id:.+}")
    public SshKeys deleteSshKeys(@PathVariable Long id) {
        return sshKeysService.deleteSshKeys(id);}

    /**
     * SshKeys 정보 수정(Update SshKeys Info)
     *
     * @param sshKeys the SshKeys
     * @return the sshKeys
     */
    @PutMapping
    public SshKeys updateSshKeys(@RequestBody SshKeys sshKeys) {
        return sshKeysService.modifySshKeys(sshKeys);
    }
}

