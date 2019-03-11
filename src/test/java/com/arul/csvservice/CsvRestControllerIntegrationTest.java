package com.arul.csvservice;
import com.arul.csvservice.controller.CsvRestController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.arul.csvservice.CsvserviceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CsvserviceApplication.class)
@AutoConfigureMockMvc
public class CsvRestControllerIntegrationTest {
 
    @Autowired
    private MockMvc mvc;
 
    @Test
    public void givenCsv_whenPostCsv_thenStatus200()
      throws Exception {
 
          StringBuffer buffer = new StringBuffer();
          buffer.append("\"Patient Name\",\"SSN\",\"Age\",\"Phone Number\",\"Status\"\n"+
"\"Prescott, Zeke\",\"542-51-6641\",21,\"801-555-2134\",\"Opratory=2,PCP=1\"\n"+
"\"Goldstein, Bucky\",\"635-45-1254\",42,\"435-555-1541\",\"Opratory=1,PCP=1\"\n"+
"\"Vox, Bono\",\"414-45-1475\",51,\"801-555-2100\",\"Opratory=3,PCP=2\"");

          MockMultipartFile multipartFile = new MockMultipartFile("file", buffer.toString().getBytes());

              RequestBuilder request = MockMvcRequestBuilders
              .multipart("/parse")
              .file(multipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA);
                

             MvcResult result = mvc.perform(request)
                .andExpect(status().isOk())
                // .andExpect(content().string(containsString("[Patient Name]")))
                .andReturn();

                String content = result.getResponse().getContentAsString();

                assertThat(content).isEqualTo("[Patient Name][SSN][Age][Phone Number][Status]"+System.lineSeparator()+
"[Prescott, Zeke][542-51-6641][21][801-555-2134][Opratory=2,PCP=1]"+System.lineSeparator()+
"[Goldstein, Bucky][635-45-1254][42][435-555-1541][Opratory=1,PCP=1]"+System.lineSeparator()+
"[Vox, Bono][414-45-1475][51][801-555-2100][Opratory=3,PCP=2]"+System.lineSeparator());

}
}