package org.paasta.container.platform.common.api.privateRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.paasta.container.platform.common.api.common.CommonService;
import org.paasta.container.platform.common.api.common.Constants;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yml")
public class PrivateRegistryServiceTest {

    private static final String IMAGE_NAME ="images";
    private static PrivateRegistry privateRegistry = null;

    @Mock
    CommonService commonService;
    @Mock
    PrivateRegistryRepository privateRegistryRepository;
    @InjectMocks
    PrivateRegistryService privateRegistryService;

    @Before
    public void setUp() throws Exception {
        privateRegistry = new PrivateRegistry();
    }

    @Test
    public void getPrivateRegistry() {
        when(privateRegistryRepository.findByImageName(IMAGE_NAME)).thenReturn(privateRegistry);
        when(commonService.setResultModel(privateRegistry, Constants.RESULT_STATUS_SUCCESS)).thenReturn(privateRegistry);

        privateRegistryService.getPrivateRegistry(IMAGE_NAME);
    }

    @Test
    public void getPrivateRegistry_Exception() {
        when(privateRegistryRepository.findByImageName(IMAGE_NAME)).thenThrow(new NullPointerException());
        when(commonService.setResultModel(privateRegistry, Constants.RESULT_STATUS_FAIL)).thenReturn(privateRegistry);

        privateRegistryService.getPrivateRegistry(IMAGE_NAME);
    }
}