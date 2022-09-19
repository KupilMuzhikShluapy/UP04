package com.example.demo.controller;

import com.example.demo.model.BaseModel;
import com.example.demo.model.PersonModel;
import com.example.demo.model.RoomModel;
import com.example.demo.rep.BaseModelRep;
import com.example.demo.rep.PersonModelRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonModelRep personModelRep;

    @Autowired
    BaseModelRep baseModelRep;

    @GetMapping("/")
    public String PersonHomeGet(Model model){

        Iterable<PersonModel> AllPerson = personModelRep.findAll();
        model.addAttribute("AllPerson", AllPerson);

        return "person/HomePerson";
    }

    @GetMapping("/PersonAdd")
    public String PersonAddGet(@Valid PersonModel Person, Model model){

        model.addAttribute("AllBase", baseModelRep.findAll());
        model.addAttribute("Person",new PersonModel());
        return "person/PersonAdd";
    }

    @PostMapping("/PersonAdd")
    public String PersonAddPost(
            @Valid @ModelAttribute("Person") PersonModel Person,
            @RequestParam("baseName") String baseName,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()){
            return "person/PersonAdd";
        }

        Person.setOwnerBasePerson(baseModelRep.findByTitle(baseName));

        personModelRep.save(Person);

        return "redirect:/person/";
    }

    @PostMapping("/{id}/DeletePerson")
    public String PersonDelete(@PathVariable("id")Long id, Model model){
        PersonModel Person = personModelRep.findById(id).orElseThrow();
        personModelRep.delete(Person);
        return "redirect:/person/";
    }

}
