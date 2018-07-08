package com.tw.harper;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class HelloControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @MockBean
    private HelloService helloService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void setUp(){
        RestAssured.baseURI="http://localhost";
        RestAssured.port = port;
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void shouldGetMessage() {
        assertThat(this.restTemplate.getForObject("http://localhost:"+port + "/service0/hello?str=world",String.class)).contains("hello world");
        assertThat(this.restTemplate.getForObject("http://localhost:"+port + "/service0/hello1",String.class)).contains("hello world");
        String param = "java";
        Mockito.when(helloService.strToUpcase(param)).thenReturn(param.toUpperCase());
        assertThat(this.restTemplate.getForObject("http://localhost:"+port + "/service0/hello2?str="+param,String.class)).contains(param.toUpperCase());
    }
    @Test
    public void shouldGetMessage1(){
        String param = "java";
        Mockito.when(helloService.strToUpcase(param)).thenReturn(param);
        assertThat(this.restTemplate.getForObject("http://localhost:"+port + "/service0/hello2?str="+param,String.class)).doesNotContain(param.toUpperCase());

    }

    @Test
    public void testGetUser(){
        given().header("Accept", ContentType.JSON.withCharset("UTF-8"))
                .header("Content-Type",ContentType.JSON.withCharset("UTF-8"))
                .contentType("application/json")
                .param("name","whb")
                .when()
                .get("service0/user/87")
                .then()
                .statusCode(200)
                .body("id",is(87))
                .body("name",is("whb"))
                .body("age",is(87));

    }
}