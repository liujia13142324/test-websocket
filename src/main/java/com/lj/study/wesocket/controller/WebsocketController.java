package com.lj.study.wesocket.controller;

import com.lj.study.wesocket.wesocket.ChatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/wesocket")
public class WebsocketController {
  
  @Autowired
  ChatHandler chatHandler;
  
  @RequestMapping("/send")
  public Object send( @RequestParam Map params ){
  
    chatHandler.send(params);
    
    return params ;
  }
  
}
