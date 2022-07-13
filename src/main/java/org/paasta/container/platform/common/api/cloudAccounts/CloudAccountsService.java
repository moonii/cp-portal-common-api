package org.paasta.container.platform.common.api.cloudAccounts;

import org.paasta.container.platform.common.api.common.CommonService;
import org.paasta.container.platform.common.api.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CloudAccounts Service 클래스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.06.14
 **/
@Service
public class CloudAccountsService {
    private final CommonService commonService;
    private final CloudAccountsRepository cloudAccountsRepository;

    /**
     * Instantiates a new Clusters service
     *
     * @param commonService the common Service
     * @param cloudAccountsRepository the CloudAccounts repository
     */
    @Autowired
    public CloudAccountsService(CommonService commonService, CloudAccountsRepository cloudAccountsRepository) {
        this.commonService = commonService;
        this.cloudAccountsRepository = cloudAccountsRepository;
    }

    /**
     * cloudAccounts 정보 저장(Create cloudAccounts)
     *
     * @param cloudAccounts the cloudAccounts
     * @return the CloudAccounts
     */
    @Transactional
    public CloudAccounts createCloudAccounts(CloudAccounts cloudAccounts) {
        CloudAccounts createdCloudAccounts = new CloudAccounts();
        try {
            createdCloudAccounts = cloudAccountsRepository.save(cloudAccounts);
        } catch (Exception e){
            createdCloudAccounts.setResultMessage(e.getMessage());
            return (CloudAccounts) commonService.setResultModel(createdCloudAccounts, Constants.RESULT_STATUS_FAIL);
        }

        return (CloudAccounts) commonService.setResultModel(createdCloudAccounts, Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * cloudAccounts 정보 조회(Get cloudAccounts)
     *
     * @param id the cloudAccounts id
     * @return the CloudAccounts
     */
    public CloudAccounts getCloudAccounts(Long id) {
        return cloudAccountsRepository.findById(id).orElse(new CloudAccounts(Constants.RESULT_STATUS_FAIL, Constants.DATA_NOT_FOUND_MESSAGE));
    }

    /**
     * cloudAccounts 목록 조회(Get cloudAccounts List)
     *
     * @return the CloudAccountsList
     */
    public CloudAccountsList getCloudAccountsList() {
        List<CloudAccounts> cloudAccountsList = cloudAccountsRepository.findAll();
        CloudAccountsList finalCloudAccountList = new CloudAccountsList();
        finalCloudAccountList.setItems(cloudAccountsList);

        return (CloudAccountsList) commonService.setResultModel(finalCloudAccountList, Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * cloudAccounts Provider 별 목록 조회(Get cloudAccounts List by Provider)
     *
     * @return the CloudAccountsList
     */
    public CloudAccountsList getCloudAccountsListByProvider(String provider) {
        List<CloudAccounts> cloudAccountsList = cloudAccountsRepository.findAllByProvider(provider);
        CloudAccountsList finalCloudAccountList = new CloudAccountsList();
        finalCloudAccountList.setItems(cloudAccountsList);

        return (CloudAccountsList) commonService.setResultModel(finalCloudAccountList, Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * cloudAccounts 정보 수정(Modify cloudAccounts)
     *
     * @return the cloudAccounts
     */
    @Transactional
    public CloudAccounts modifyCloudAccounts(CloudAccounts cloudAccounts) {
        CloudAccounts target = new CloudAccounts();
        try {
            target = cloudAccountsRepository.findById(cloudAccounts.getId()).orElseThrow(NullPointerException::new);
            target = cloudAccountsRepository.save(target);
            target.setName(cloudAccounts.getName());
        } catch (Exception e) {
            target.setResultMessage(e.getMessage());
            return (CloudAccounts) commonService.setResultModel(target, Constants.RESULT_STATUS_FAIL);
        }
        return (CloudAccounts) commonService.setResultModel(target, Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * cloudAccounts 정보 삭제(Delete cloudAccounts)
     *
     * @return the ResultStatus
     */
    @Transactional
    public CloudAccounts deleteCloudAccounts(Long id) {
        cloudAccountsRepository.deleteById(id);
        return (CloudAccounts) commonService.setResultModel(new CloudAccounts(), Constants.RESULT_STATUS_SUCCESS);
    }
}
