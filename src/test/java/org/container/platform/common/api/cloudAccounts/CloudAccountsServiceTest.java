package org.container.platform.common.api.cloudAccounts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.container.platform.common.api.common.CommonService;
import org.container.platform.common.api.common.Constants;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * CloudAccounts Service Test 클래스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.06.30
 **/
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yml")
public class CloudAccountsServiceTest {

    private static final long TEST_ID = 1;
    private static final String TEST_NAME = "test";

    @Mock
    CommonService commonService;
    @Mock
    CloudAccountsRepository cloudAccountsRepository;
    @InjectMocks
    CloudAccountsService cloudAccountsService;

    private static CloudAccounts finalCloudAccounts = null;
    private static CloudAccountsList finalCloudAccountsList = null;

    @Before
    public void setup() {
        finalCloudAccounts = new CloudAccounts();
        finalCloudAccounts.setResultCode(Constants.RESULT_STATUS_SUCCESS);
        finalCloudAccounts.setName(TEST_NAME);
        finalCloudAccounts.setId(TEST_ID);

        finalCloudAccountsList = new CloudAccountsList();

    }


    @Test
    public void createCloudAccounts() {
        CloudAccounts inCloudAccounts = new CloudAccounts();
        when(cloudAccountsRepository.save(inCloudAccounts)).thenReturn(finalCloudAccounts);
        when(commonService.setResultModel(finalCloudAccounts, Constants.RESULT_STATUS_SUCCESS)).thenReturn(finalCloudAccounts);

        CloudAccounts cloudAccounts = cloudAccountsService.createCloudAccounts(inCloudAccounts);

        assertEquals(cloudAccounts, finalCloudAccounts);
    }

    @Test
    public void createCloudAccounts_Exception() {
        CloudAccounts inCloudAccounts = new CloudAccounts();
        when(cloudAccountsRepository.save(inCloudAccounts)).thenThrow(new NullPointerException());
        when(commonService.setResultModel(finalCloudAccounts, Constants.RESULT_STATUS_FAIL)).thenReturn(finalCloudAccounts);

        CloudAccounts cloudAccounts = cloudAccountsService.createCloudAccounts(inCloudAccounts);

    }

    @Test
    public void getCloudAccounts() {
        when(cloudAccountsRepository.findById(TEST_ID)).thenReturn(Optional.of(finalCloudAccounts));
        CloudAccounts cloudAccounts = cloudAccountsService.getCloudAccounts(TEST_ID);

        assertNotNull(cloudAccounts);
    }

    @Test
    public void getCloudAccountsListByProvider() {
        List<CloudAccounts> list = new ArrayList<>();
        when(cloudAccountsRepository.findAllByProvider(TEST_NAME)).thenReturn(list);
        CloudAccountsList finalList = new CloudAccountsList();
        finalList.setItems(list);
        when(commonService.setResultModel(finalList, Constants.RESULT_STATUS_SUCCESS)).thenReturn(finalList);

        cloudAccountsService.getCloudAccountsListByProvider(TEST_NAME);
    }

    @Test
    public void getCloudAccountsList() {
        when(cloudAccountsRepository.findAll()).thenReturn(finalCloudAccountsList.getItems());
        when(commonService.setResultModel(finalCloudAccountsList, Constants.RESULT_STATUS_SUCCESS)).thenReturn(finalCloudAccountsList);
        CloudAccountsList cloudAccountsList = cloudAccountsService.getCloudAccountsList();

        assertNotNull(cloudAccountsList);
    }

    @Test
    public void modifyCloudAccounts() {
        CloudAccounts inCloudAccounts = new CloudAccounts();
        inCloudAccounts.setId(TEST_ID);
        inCloudAccounts.setName(TEST_NAME);
        when(cloudAccountsRepository.findById(inCloudAccounts.getId())).thenReturn(Optional.ofNullable(finalCloudAccounts));
        when(cloudAccountsRepository.save(finalCloudAccounts)).thenReturn(finalCloudAccounts);
        when(commonService.setResultModel(finalCloudAccounts, Constants.RESULT_STATUS_SUCCESS)).thenReturn(finalCloudAccounts);

        CloudAccounts cloudAccounts = cloudAccountsService.modifyCloudAccounts(inCloudAccounts);

    }

    @Test
    public void deleteCloudAccounts() {
        CloudAccounts inCloudAccounts = new CloudAccounts();
        when(commonService.setResultModel(inCloudAccounts, Constants.RESULT_STATUS_SUCCESS)).thenReturn(finalCloudAccounts);

        CloudAccounts cloudAccounts = cloudAccountsService.deleteCloudAccounts(TEST_ID);
        assertNotNull(cloudAccounts);
    }
}