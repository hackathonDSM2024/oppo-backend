package com.example.demo.controller;

import io.github.flashvayne.chatgpt.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatgptService chatgptService;
}
