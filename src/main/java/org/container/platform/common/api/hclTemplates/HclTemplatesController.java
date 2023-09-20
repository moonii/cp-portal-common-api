package org.container.platform.common.api.hclTemplates;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * HclTemplates Controller 클래스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.06.30
 **/
@RestController
@RequestMapping(value = "/hclTemplates")
public class HclTemplatesController {
    private final HclTemplatesService hclTemplatesService;

    /**
     * Instantiates a new hclTemplates controller
     *
     * @param hclTemplatesService the hclTemplates service
     */
    HclTemplatesController(HclTemplatesService hclTemplatesService) {
        this.hclTemplatesService = hclTemplatesService;
    }


    /**
     * HclTemplates 목록 조회(Get HclTemplates List)
     *
     * @return the hclTemplatesList
     */
    @GetMapping
    public HclTemplatesList getHclTemplatesList() {
        return hclTemplatesService.getHclTemplatesList();
    }


    /**
     * Provider별 HclTemplates 목록 조회(Get HclTemplates List By Provider)
     *
     * @return the HclTemplatesList
     */
    @ApiOperation(value="HclTemplates 타입 별 목록 조회(Get CloudAccounts List By Provider)", nickname="getHclTemplatesListByProvider")
    @GetMapping(value = "/provider/{provider:.+}")
    public HclTemplatesList getHclTemplatesListByProvider(@PathVariable String provider) {
        return hclTemplatesService.getHclTemplatesListByProvider(provider);}


    /**
     * HclTemplates 정보 조회(Get HclTemplates Info)
     *
     * @param id the hclTemplates id
     * @return the HclTemplates
     */
    @GetMapping(value = "/{id:.+}")
    public HclTemplates getHclTemplates(@PathVariable Long id) {
        return hclTemplatesService.getHclTemplates(id);
    }


    /**
     * HclTemplates 정보 저장(Create HclTemplates Info)
     *
     * @param hclTemplates the hclTemplates
     * @return the hclTemplates
     */
    @PostMapping
    public HclTemplates createHclTemplates(@RequestBody HclTemplates hclTemplates) {
        return hclTemplatesService.createHclTemplates(hclTemplates);
    }


    /**
     * HclTemplates 정보 삭제(Delete HclTemplates Info)
     *
     * @param id the id
     * @return the hclTemplates
     */
    @DeleteMapping(value = "/{id:.+}")
    public HclTemplates deleteHclTemplates(@PathVariable Long id) {
        return hclTemplatesService.deleteHclTemplates(id);
    }


    /**
     * HclTemplates 정보 수정(Update HclTemplates Info)
     *
     * @param hclTemplates the hclTemplates
     * @return the hclTemplates
     */
    @PutMapping
    public HclTemplates updateHclTemplates(@RequestBody HclTemplates hclTemplates) {
        return hclTemplatesService.modifyHclTemplates(hclTemplates);
    }
}
