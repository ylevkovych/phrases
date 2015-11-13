package com.levkip.phrases.webapp.pagecontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by levy on 11.10.15.
 */
@Controller
public class Main {

    private Logger logger = LoggerFactory.getLogger(Main.class);

    @RequestMapping(value="/")
    public String index(Model model)
    {
        logger.debug("redirect:/index");
        return "index";
    }

}
