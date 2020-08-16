package com.nhs.healthcosteligibility.controller;

import com.nhs.healthcosteligibility.model.RegularAmount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EligibilityController {
    public static final String REDIRECT = "redirect:";
    public static final String VIEW = "checkEligibility";
    public static final String PATH = "/" + VIEW;

    @GetMapping("/checkEligibility")
    public ModelAndView getForm(@ModelAttribute("regularAmount") RegularAmount regularAmount) {
        ModelAndView mv = new ModelAndView("eligibility");
        mv.addObject("regularAmount", regularAmount);
        return mv;
    }

    @PostMapping("/checkEligibility")
    public String processForm(Model model,
                              @Valid @ModelAttribute("regularAmount") RegularAmount regularAmount,
                              BindingResult errors,
                              RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            String redirectPath = REDIRECT + EligibilityController.PATH;
            redirectAttributes.addFlashAttribute("regularAmountFormErrors", errors);
            redirectAttributes.addFlashAttribute("regularAmount", regularAmount);
            return redirectPath;
        }
        if (StringUtils.isEmpty(regularAmount.getAmount()) || regularAmount.getFrequency() == null) {
            return "failure";
        }
        return "success";
    }
}
