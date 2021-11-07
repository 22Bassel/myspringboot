package com.first.project.controllers.queue;

import com.first.project.classes_for_requestbody.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value ="/for_queue")
public class ControlerSendToQueue {


    @Autowired
    RabbitTemplate template;


    @GetMapping(value = "/")
    public String showqueuepage(Model model){
        OrderStatus orderStatus=new OrderStatus();

        model.addAttribute("orderStatus",orderStatus);
        return "/queue/queuepage";
    }

        @PostMapping(value = "/send")
    public String publishToRabbit(@ModelAttribute OrderStatus orderStatus)
    {
        template.convertAndSend(Messagingconfig.Exchange,Messagingconfig.Routing,orderStatus);
        return "done";
    }

}
