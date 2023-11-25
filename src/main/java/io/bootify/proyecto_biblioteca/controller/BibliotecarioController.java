package io.bootify.proyecto_biblioteca.controller;

import io.bootify.proyecto_biblioteca.domain.Lector;
import io.bootify.proyecto_biblioteca.model.BibliotecarioDTO;
import io.bootify.proyecto_biblioteca.repos.LectorRepository;
import io.bootify.proyecto_biblioteca.service.BibliotecarioService;
import io.bootify.proyecto_biblioteca.util.CustomCollectors;
import io.bootify.proyecto_biblioteca.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/bibliotecarios")
public class BibliotecarioController {

    private final BibliotecarioService bibliotecarioService;
    private final LectorRepository lectorRepository;

    public BibliotecarioController(final BibliotecarioService bibliotecarioService,
            final LectorRepository lectorRepository) {
        this.bibliotecarioService = bibliotecarioService;
        this.lectorRepository = lectorRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("esLectorValues", lectorRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Lector::getId, Lector::getNombre)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("bibliotecarios", bibliotecarioService.findAll());
        return "bibliotecario/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("bibliotecario") final BibliotecarioDTO bibliotecarioDTO) {
        return "bibliotecario/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("bibliotecario") @Valid final BibliotecarioDTO bibliotecarioDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("zona") && bibliotecarioService.zonaExists(bibliotecarioDTO.getZona())) {
            bindingResult.rejectValue("zona", "Exists.bibliotecario.zona");
        }
        if (!bindingResult.hasFieldErrors("esLector") && bibliotecarioDTO.getEsLector() != null && bibliotecarioService.esLectorExists(bibliotecarioDTO.getEsLector())) {
            bindingResult.rejectValue("esLector", "Exists.bibliotecario.esLector");
        }
        if (bindingResult.hasErrors()) {
            return "bibliotecario/add";
        }
        bibliotecarioService.create(bibliotecarioDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bibliotecario.create.success"));
        return "redirect:/bibliotecarios";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("bibliotecario", bibliotecarioService.get(id));
        return "bibliotecario/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("bibliotecario") @Valid final BibliotecarioDTO bibliotecarioDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final BibliotecarioDTO currentBibliotecarioDTO = bibliotecarioService.get(id);
        if (!bindingResult.hasFieldErrors("zona") &&
                !bibliotecarioDTO.getZona().equalsIgnoreCase(currentBibliotecarioDTO.getZona()) &&
                bibliotecarioService.zonaExists(bibliotecarioDTO.getZona())) {
            bindingResult.rejectValue("zona", "Exists.bibliotecario.zona");
        }
        if (!bindingResult.hasFieldErrors("esLector") && bibliotecarioDTO.getEsLector() != null &&
                !bibliotecarioDTO.getEsLector().equals(currentBibliotecarioDTO.getEsLector()) &&
                bibliotecarioService.esLectorExists(bibliotecarioDTO.getEsLector())) {
            bindingResult.rejectValue("esLector", "Exists.bibliotecario.esLector");
        }
        if (bindingResult.hasErrors()) {
            return "bibliotecario/edit";
        }
        bibliotecarioService.update(id, bibliotecarioDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bibliotecario.update.success"));
        return "redirect:/bibliotecarios";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = bibliotecarioService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            bibliotecarioService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("bibliotecario.delete.success"));
        }
        return "redirect:/bibliotecarios";
    }

}
