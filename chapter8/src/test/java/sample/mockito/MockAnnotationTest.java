package sample.mockito;

import org.testng.annotations.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.smart.domain.User;
import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class MockAnnotationTest {
	//����ע��ģ����
    @Mock User mockUser;
    
    @BeforeMethod
    public void initMocks() {
    	//��ʼ����ǰ����������@Mockע��ģ�����
        MockitoAnnotations.initMocks(this);
    }

	//ģ��User�����
	@Test
	public void testMockUser() {
		when(mockUser.getUserId()).thenReturn(1);
		when(mockUser.getUserName()).thenReturn("tom");
		assertEquals(mockUser.getUserId(),1);
		assertEquals(mockUser.getUserName(), "tom");
	}
}
