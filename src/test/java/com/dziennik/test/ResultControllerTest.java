package com.dziennik.test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.dziennik.controllers.ResultsController;
import com.dziennik.domain.Result;
import com.dziennik.services.ResultService;

@RunWith(SpringRunner.class)
@WebMvcTest(ResultsController.class)
public class ResultControllerTest {

    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private ResultService resultService;

    @Test
    public void whenGetResult_thenReturnResults() throws Exception {
    	Result result1 = new Result(4, "markk");
    	
        Mockito.when(resultService.findById(result1.getId())).thenReturn(result1);
        
        String id = ""+ result1.getId();
        
        mvc.perform(get("/result/" + id)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(result1.getId())))
          .andExpect(jsonPath("$.studentLogin", is(result1.getStudentLogin())));
    }
}
