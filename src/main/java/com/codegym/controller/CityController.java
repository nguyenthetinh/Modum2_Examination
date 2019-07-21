package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Nation;
import com.codegym.service.CityService;
import com.codegym.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private NationService nationService;

    @ModelAttribute("nations")
    public Iterable<Nation> nations(){
        return nationService.findAll();
    }

    @GetMapping("cities")
    public ModelAndView listCity(@RequestParam("s")Optional<String>s,@PageableDefault(size = 5) Pageable pageable){
        Page<City> cities;

        if (s.isPresent()){
            cities = cityService.findByNameContaining(s.get(),pageable);
        }else {
            cities = cityService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("cities",cities);
        return modelAndView;
    }

    @GetMapping("/create-cities")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("cities",new City());
        return modelAndView;
    }

    @PostMapping("/create-cities")
    public ModelAndView saveCity(@ModelAttribute("cities")City city){
        cityService.save(city);

        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("cities",new City());
        modelAndView.addObject("message","'New City created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-cities/{id}")
    public ModelAndView showEditFrom(@PathVariable Long id){
        City city = cityService.findById(id);
        if (city != null){
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("cities",city);
            return modelAndView;
        }else {
            ModelAndView modelAndView =new  ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-cities")
    public ModelAndView updateCity(@ModelAttribute("cities")City city){
        cityService.save(city);

        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("cities",city);
        modelAndView.addObject("message","City updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-cities/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        City city = cityService.findById(id);
        if (city != null){
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("cities",city);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-cities")
    public String deleteCity(@ModelAttribute("cities")City city){
        cityService.remove(city.getId());
        return "redirect:cities";
    }

    @GetMapping("/view-cities/{id}")
    public ModelAndView viewCity(@PathVariable("id") Long id){
        City city = cityService.findById(id);
        if (city != null){
            ModelAndView modelAndView = new ModelAndView("/view");
            modelAndView.addObject("cities",city);
            return modelAndView;
        }else {
            return new ModelAndView("/error.404");
        }
    }

    @GetMapping("/create-nation")
    public ModelAndView showCreateNationForm(){
        ModelAndView modelAndView = new ModelAndView("/createNation");
        modelAndView.addObject("nations",new Nation());
        return modelAndView;
    }

    @PostMapping("/create-nation")
    public ModelAndView saveNation(@ModelAttribute("nation")Nation nation){
        nationService.save(nation);

        ModelAndView modelAndView = new ModelAndView("/createNation");
        modelAndView.addObject("nations",new Nation());
        modelAndView.addObject("message","'New Nation created successfully");
        return modelAndView;
    }

}
