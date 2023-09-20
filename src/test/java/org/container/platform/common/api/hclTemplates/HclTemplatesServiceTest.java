package org.container.platform.common.api.hclTemplates;

import org.apache.tomcat.util.bcel.Const;
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

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * HclTemplates Service Test 클래스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.06.30
 **/
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yml")
public class HclTemplatesServiceTest {

    private static final long TEST_ID = 1;
    private static final String PROVIDER = "AWS";

    @Mock
    CommonService commonService;
    @Mock
    HclTemplatesRepository hclTemplatesRepository;
    @InjectMocks
    HclTemplatesService hclTemplatesService;

    private static HclTemplates finalHclTemplates = null;
    private static HclTemplatesList finalHclTemplatesList = null;
    private static List<HclTemplates> hclTemplatesList = null;

    @Before
    public void setUp() {
        finalHclTemplates = new HclTemplates();
        finalHclTemplates.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        finalHclTemplatesList = new HclTemplatesList();

        hclTemplatesList = new ArrayList<>();
        finalHclTemplatesList.setItems(hclTemplatesList);
    }

    @Test
    public void createHclTemplates() {
        HclTemplates inHclTemplates = new HclTemplates();
        when(hclTemplatesRepository.save(inHclTemplates)).thenReturn(finalHclTemplates);
        when(commonService.setResultModel(finalHclTemplates, Constants.RESULT_STATUS_SUCCESS)).thenReturn(finalHclTemplates);

        HclTemplates hclTemplates = hclTemplatesService.createHclTemplates(inHclTemplates);
        assertEquals(hclTemplates, finalHclTemplates);

    }

    @Test
    public void createHclTemplates_Exception() {
        HclTemplates inHclTemplates = new HclTemplates();
        when(hclTemplatesRepository.save(inHclTemplates)).thenThrow(new NullPointerException());
        when(commonService.setResultModel(finalHclTemplates, Constants.RESULT_STATUS_FAIL)).thenReturn(finalHclTemplates);

        HclTemplates hclTemplates = hclTemplatesService.createHclTemplates(inHclTemplates);

    }

    @Test
    public void getHclTemplates() {
        when(hclTemplatesRepository.findById(TEST_ID)).thenReturn(Optional.of(finalHclTemplates));
        HclTemplates hclTemplates = hclTemplatesService.getHclTemplates(TEST_ID);

        assertNotNull(hclTemplates);
    }

    @Test
    public void getHclTemplatesList() {
        when(hclTemplatesRepository.findAll()).thenReturn(finalHclTemplatesList.getItems());
        when(commonService.setResultModel(finalHclTemplatesList, Constants.RESULT_STATUS_SUCCESS)).thenReturn(finalHclTemplatesList);

        HclTemplatesList hclTemplatesList = hclTemplatesService.getHclTemplatesList();
        assertNotNull(hclTemplatesList);

    }

    @Test
    public void getHclTemplatesListByProvider() {
        when(hclTemplatesRepository.findAllByProvider(PROVIDER)).thenReturn(hclTemplatesList);
        when(commonService.setResultModel(finalHclTemplatesList, Constants.RESULT_STATUS_SUCCESS)).thenReturn(finalHclTemplatesList);

        hclTemplatesService.getHclTemplatesListByProvider(PROVIDER);

    }

    @Test
    public void modifyHclTemplates() {
        HclTemplates inHclTemplates = new HclTemplates();
        when(hclTemplatesRepository.save(inHclTemplates)).thenReturn(finalHclTemplates);
        when(commonService.setResultModel(finalHclTemplates, Constants.RESULT_STATUS_SUCCESS)).thenReturn(finalHclTemplates);

        HclTemplates hclTemplates = hclTemplatesService.modifyHclTemplates(inHclTemplates);
        assertEquals(hclTemplates, finalHclTemplates);
    }

    @Test
    public void modifyHclTemplates_Exception() {
        HclTemplates inHclTemplates = new HclTemplates();
        when(hclTemplatesRepository.save(inHclTemplates)).thenThrow(new NullPointerException("test"));
        when(commonService.setResultModel(finalHclTemplates, Constants.RESULT_STATUS_FAIL)).thenReturn(finalHclTemplates);

        HclTemplates hclTemplates = hclTemplatesService.modifyHclTemplates(inHclTemplates);
    }

    @Test
    public void deleteHclTemplates() {
        HclTemplates inHclTemplates = new HclTemplates();
        when(commonService.setResultModel(inHclTemplates, Constants.RESULT_STATUS_SUCCESS)).thenReturn(finalHclTemplates);

        HclTemplates hclTemplates = hclTemplatesService.deleteHclTemplates(TEST_ID);
        assertNotNull(hclTemplates);
    }
}