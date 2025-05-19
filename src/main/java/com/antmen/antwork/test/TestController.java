package com.antmen.antwork.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {

    public final TestService testService;

    @PostMapping
    public TestResponseDto postContent(@RequestBody TestRequestDto requestBody) {
        return testService.postContent(requestBody);
    }
}
