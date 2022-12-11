package com.example.CurrencyExchangeApplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller public class MainView {

    public MainView() {
    }

    @GetMapping("/") public String MainView(ModelMap modelMap) {
        return "mainView";
    }
}
