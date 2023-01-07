
package com.escom.tt.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    
    @MessageMapping("/hello")

@SendTo("/topic/greetings")

public String greeting(@Payload String message) throws Exception {

Thread.sleep(1000); // simulated delay

return message;

}
    
}
