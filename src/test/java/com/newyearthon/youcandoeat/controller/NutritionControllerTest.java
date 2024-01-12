package com.newyearthon.youcandoeat.controller;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(NutritionController.class)
@AutoConfigureMockMvc
public class NutritionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Open API 통신 테스트")
    public void callOpenApi() throws Exception {
        String descKor = "가자미구이";

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();

        param.add("DESC_KOR", descKor);

        this.mvc.perform(get("/api/nutrition").params(param))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
