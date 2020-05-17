package me.jar.scw.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

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

    @Test
    public void testUserLogin() throws Exception {
        LinkedMultiValueMap<String, String> paraMap = new LinkedMultiValueMap<>();
        paraMap.add("userName", "mockMVC");
        paraMap.add("userPswd", "123456");
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/login.do").contentType(MediaType.APPLICATION_FORM_URLENCODED).params(paraMap)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        System.out.println("response status: " + status);
        Cookie[] cookies = response.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("cookie: " + cookie.getComment());
        }
    }

    @Test
    public void testUserLogin2() throws Exception {
        LinkedMultiValueMap<String, String> paraMap = new LinkedMultiValueMap<>();
        paraMap.add("userName", "mockMVC");
        paraMap.add("userPswd", "123456");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/user/login.do").contentType(MediaType.APPLICATION_FORM_URLENCODED).params(paraMap));
        resultActions.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAddUser() throws Exception {
        LinkedMultiValueMap<String, String> paraMap = new LinkedMultiValueMap<>();
        paraMap.add("userName", "hhhhhsssss");
        paraMap.add("loginAcct", "hhhhhsssss");
        paraMap.add("email", "aaaa@bbb.com");
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/user/adduser.do").contentType(MediaType.APPLICATION_FORM_URLENCODED).params(paraMap));
        perform.andDo(MockMvcResultHandlers.print());
    }
}
