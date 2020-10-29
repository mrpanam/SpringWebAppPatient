package com.example.yousfi_patient.web;

import com.example.yousfi_patient.dao .PatientRepo;
import com.example.yousfi_patient.entities.Patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class PatientController {
    //injection dependence
    public PatientController(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    private PatientRepo patientRepo;

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping(path ={ "/patients"})
    public String patient(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "10")int size,
                          @RequestParam(name = "keyword", defaultValue = "")String mc){
        Page<Patient> patientPage = patientRepo.findByNomContainsOrderById(mc,PageRequest.of(page,size));
        model.addAttribute("patients",patientPage.getContent());
        model.addAttribute("pages", new int[patientPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",mc);
        model.addAttribute("size",size);
        return "patients";
    }

    @GetMapping(path = "/deletePatient")
    public String delete(Long id,String keyword, int page, int size){
        patientRepo.deleteById(id);
        return "redirect:/patients?page="+page+"&size="+size+"&keyword="+keyword;
    }
    @GetMapping("/formPatient")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        model.addAttribute("mode","new");
        return "formPatient";
    }

    @PostMapping("savePatient")
    public String savePatient(@Valid Patient p, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) return "formPatient";
        model.addAttribute("patient",p);
        patientRepo.save(p);

        return "confirmation";
    }
    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id){
        Patient p = patientRepo.findById(id).get();
        model.addAttribute("patient",p);
        model.addAttribute("mode","edit");
        return "formPatient";
    }
    // methode pour renvoyer au format json la liste des patients
    @GetMapping("/listePatients")
    @ResponseBody
    public List<Patient> liste(){
        return patientRepo.findAll();
    }

    @GetMapping("/patients/{id}")
    @ResponseBody
    public Patient patientUnique(@PathVariable Long id){
        return patientRepo.findById(id).get();
    }

}
