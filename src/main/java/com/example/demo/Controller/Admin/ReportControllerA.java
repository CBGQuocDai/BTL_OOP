package com.example.demo.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DAO.ReportDAO;

@Controller
public class ReportControllerA {
    @Autowired
    ReportDAO reportDAO;

    @RequestMapping("/admin_report")
    public String Report(Model model){
        model.addAttribute("reports" , reportDAO.getAllReport());
        return "admin/Report";
    }

    @RequestMapping("/delete-report")
    public String deleteUser(Model model , @RequestParam("id") String reportId) throws Exception {
        reportDAO.deleteReportById(reportId);
        return "redirect:/admin_report";
    }

    @RequestMapping("/report-view/{id}")
    public String ReportView(Model model, @RequestParam("id") int reportId) throws Exception {
        reportDAO.selectReportById(reportId);
        return "admin/report-view";
    }
}