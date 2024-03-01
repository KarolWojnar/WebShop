package com.webshop.Controller;

import com.webshop.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/allItems")
    public String uploadAllProducts(Model model) throws JSONException {
        adminService.addAllItems(model);
        return "home";
    }
}
