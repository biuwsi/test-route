package com.modsen.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modsen.controller.api.ErrorResponse;
import com.modsen.controller.api.RouteResponse;
import com.modsen.exception.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.modsen.controller.RouteController.PATH_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestRouteApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void pathFoundTest() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(get(PATH_URI, "CZE", "ITA"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        final RouteResponse result = convert(RouteResponse.class, response.getContentAsString());
        Assertions.assertEquals(result.getRoute(), List.of("CZE", "AUT", "ITA"));
    }

    @Test
    void codeNotFoundTest() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(get(PATH_URI, "invalid-code", "ITA"))
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse();

        final ErrorResponse result = convert(ErrorResponse.class, response.getContentAsString());
        Assertions.assertEquals(result.getText(), ErrorCode.UNKNOWN_COUNTRY_CODE.getText());
    }

    @Test
    void pathNotFound() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(get(PATH_URI, "MDG", "ITA"))
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse();

        final ErrorResponse result = convert(ErrorResponse.class, response.getContentAsString());
        Assertions.assertEquals(result.getText(), ErrorCode.PATH_NOT_FOUND.getText());
    }

    private <T> T convert(Class<T> clazz, String content) throws JsonProcessingException {
        return objectMapper.readValue(content, clazz);
    }

}
