package me.jar.scw.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Description controller接口测试
 * @Date 2020/5/10-18:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc4test.xml", "classpath:mybatisconfig.xml"})
@WebAppConfiguration
public class UserControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testUserReg() throws Exception {
       MultiValueMap<String, String>  paraMap = new LinkedMultiValueMap<>();
       paraMap.add("userName", "mockMVC");
       paraMap.add("userPswd", "123456");
       paraMap.add("email", "mock@mvc.com");
       paraMap.add("loginAcct", "会员");
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/reg.do").contentType(MediaType.APPLICATION_FORM_URLENCODED).params(paraMap)).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
    }
}
