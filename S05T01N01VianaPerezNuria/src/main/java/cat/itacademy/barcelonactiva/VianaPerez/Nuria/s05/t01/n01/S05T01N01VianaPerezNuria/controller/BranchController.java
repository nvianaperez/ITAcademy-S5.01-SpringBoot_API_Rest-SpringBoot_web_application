package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.controller;

import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.service.IBranchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bankbranches")
public class BranchController {

    @Autowired
    private IBranchService branchService;

    @GetMapping("/")
    public String showAllBranches(Model model) {
        List<BranchDTO> branchDTOList = branchService.getAllBranches();

        model.addAttribute("title", "Bank branches list");
        model.addAttribute("branchesList", branchDTOList);
        return "home";
    }

    @GetMapping("/showOne/{id}")
    public String showOneBranch(@PathVariable Long id, Model model) {
        BranchDTO selectedBranchDTO = branchService.getOneBranchById(id);
        List<BranchDTO> branchDTOList = new ArrayList<BranchDTO>();
        branchDTOList.add(selectedBranchDTO);
        model.addAttribute("title", "Show one branch:");
        model.addAttribute("branchesList", branchDTOList);
        return "home";
    }

    @GetMapping("/filterEU")
    public String filterEUBranches(Model model) {
        List<BranchDTO> branchDTOListEU = branchService.getAllBranches().stream()
                .filter(b->b.getType().equalsIgnoreCase("EU"))
                .toList();
        model.addAttribute("title", "Bank branches list from 'EU'");
        model.addAttribute("branchesList", branchDTOListEU);
        return "home";
    }

    @GetMapping("/filterNOTEU")
    public String filterNOTEUBranches(Model model) {
        List<BranchDTO> branchDTOListNOTEU = branchService.getAllBranches().stream()
                .filter(b->b.getType().equalsIgnoreCase("NOT EU"))
                .toList();
        model.addAttribute("title", "Bank branches list from 'NOT EU'");
        model.addAttribute("branchesList", branchDTOListNOTEU);
        return "home";
    }

    @GetMapping("/addBranch")
    public String addBranch(Model model) {
        BranchDTO branchDTO = new BranchDTO();

        model.addAttribute("title", "Create a new Bank branch:");
        model.addAttribute("branchDTO", branchDTO);
        return "addBranch";
    }

    @PostMapping("/saveBranch")
    //When Spring sees @Valid, it tries to find the validator for the object being validated. Spring automatically picks up validation annotations if you have “annotation-driven” enabled. Spring then invokes the validator and puts any errors in the BindingResult and adds the BindingResult to the view model.
    public String saveBranch(@Valid @ModelAttribute("branchDTO") BranchDTO branchDTO, BindingResult result) {
        if(result.hasErrors()) {
            return "addBranch";
        } else {
            BranchDTO savedBranchDTO = branchService.createBranch(branchDTO);
            System.out.println(savedBranchDTO);
            return "redirect:/bankbranches/";
        }
    }

    @GetMapping("/update/{id}")
    public String updateBranch(@PathVariable Long id, Model model) {
        model.addAttribute("title", "Update selected Bank branch");
        model.addAttribute("updatedBranchDTO", branchService.getOneBranchById(id));
        return "updateBranch";
    }

    @PostMapping("/update")
    public String updateBranch(@Valid @ModelAttribute("updatedBranchDTO") BranchDTO branchDTO, BindingResult result) {
        if(result.hasErrors()) {
            return "updateBranch";
        } else {
            branchService.updateBranch(branchDTO.getPk_branchId(), branchDTO);
            return "redirect:/bankbranches/showOne/" + branchDTO.getPk_branchId();
        }
    }

    @GetMapping("/delete/{id}")
        public String deleteBranch(@PathVariable Long id) {
            branchService.deleteBranch(id);
            return "redirect:/bankbranches/";
    }

}

//Controller
//model.addAttribute("serverTime", dateFormat.format(new Date()));
//HTML
//Current time is <span th:text="${serverTime}" />

//HTML
//Recorrer una collection
//<tbody>
//<tr th:each="student: ${students}">
//<td th:text="${student.id}" />
//<td th:text="${student.name}" />
//</tr>
//</tbody>

//Conditional
//<td>
//<span th:if="${student.gender} == 'M'" th:text="Male" />
//<span th:unless="${student.gender} == 'M'" th:text="Female" />
//</td>

//Switch
//<td th:switch="${student.gender}">
//<span th:case="'M'" th:text="Male" />
//<span th:case="'F'" th:text="Female" />
//</td>