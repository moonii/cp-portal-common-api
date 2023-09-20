package org.container.platform.common.api.hclTemplates;

import org.container.platform.common.api.common.CommonService;
import org.container.platform.common.api.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * HclTemplates Service 클래스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.06.30
 **/
@Service
public class HclTemplatesService {
    private final CommonService commonService;
    private final HclTemplatesRepository hclTemplatesRepository;

    /**
     * Instantiates a new Clusters service
     *
     * @param hclTemplatesRepository the hclTemplates repository
     * @param commonService the common Service
     */
    @Autowired
    HclTemplatesService(CommonService commonService, HclTemplatesRepository hclTemplatesRepository) {
        this.commonService = commonService;
        this.hclTemplatesRepository = hclTemplatesRepository;
    }

    /**
     * HclTemplates 정보 저장(Create HclTemplates Info)
     *
     * @param hclTemplates the hclTemplates
     * @return the hclTemplates
     */
    @Transactional
    public HclTemplates createHclTemplates(HclTemplates hclTemplates) {
        HclTemplates createdHclTemplates = new HclTemplates();
        try {
            createdHclTemplates = hclTemplatesRepository.save(hclTemplates);
        } catch (Exception e) {
            createdHclTemplates.setResultMessage(e.getMessage());
            return (HclTemplates) commonService.setResultModel(createdHclTemplates, Constants.RESULT_STATUS_FAIL);
        }

        return (HclTemplates) commonService.setResultModel(createdHclTemplates, Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * HclTemplates 정보 조회(Get HclTemplates Info)
     *
     * @param id the hclTemplate id
     * @return the hclTemplates
     */
    public HclTemplates getHclTemplates(Long id) {
        return hclTemplatesRepository.findById(id).orElse(new HclTemplates(Constants.RESULT_STATUS_FAIL, Constants.DATA_NOT_FOUND_MESSAGE));
    }

    /**
     * HclTemplates 목록 조회(Get HclTemplates List)
     *
     * @return the hclTemplatesList
     */
    public HclTemplatesList getHclTemplatesList() {
        List<HclTemplates> hclTemplatesList = hclTemplatesRepository.findAll();
        HclTemplatesList finalHclTemplatesList = new HclTemplatesList();
        finalHclTemplatesList.setItems(hclTemplatesList);
        return (HclTemplatesList) commonService.setResultModel(finalHclTemplatesList, Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * HclTemplates 목록 조회(Get HclTemplates List)
     *
     * @return the hclTemplatesList
     */
    public HclTemplatesList getHclTemplatesListByProvider(String provider) {
        List<HclTemplates> hclTemplatesList = hclTemplatesRepository.findAllByProvider(provider);
        HclTemplatesList finalHclTemplatesList = new HclTemplatesList();
        finalHclTemplatesList.setItems(hclTemplatesList);
        return (HclTemplatesList) commonService.setResultModel(finalHclTemplatesList, Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * HclTemplates 정보 수정(Update HclTemplates Info)
     *
     * @param hclTemplates the hclTemplates
     * @return the hclTemplates
     */
    @Transactional
    public HclTemplates modifyHclTemplates(HclTemplates hclTemplates) {
        try {
            hclTemplates = hclTemplatesRepository.save(hclTemplates);
        } catch (Exception e) {
            hclTemplates.setResultMessage(e.getMessage());
            return (HclTemplates) commonService.setResultModel(hclTemplates, Constants.RESULT_STATUS_FAIL);
        }

        return (HclTemplates) commonService.setResultModel(hclTemplates, Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * HclTemplates 정보 삭제(Delete HclTemplates Info)
     *
     * @param id the hclTemplate id
     * @return the hclTemplates
     */
    @Transactional
    public HclTemplates deleteHclTemplates(Long id) {
        hclTemplatesRepository.deleteById(id);
        return (HclTemplates) commonService.setResultModel(new HclTemplates(), Constants.RESULT_STATUS_SUCCESS);

    }

}
