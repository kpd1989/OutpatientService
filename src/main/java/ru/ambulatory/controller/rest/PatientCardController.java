package ru.ambulatory.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ambulatory.dto.PatientCardPageDto;
import ru.ambulatory.security.Authorities;
import ru.ambulatory.service.PatientCardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards")
public class PatientCardController {

    private final PatientCardService cardService;
    private final Authorities authorities;

    @GetMapping
    public PatientCardPageDto find(@PageableDefault(value = 20) Pageable pageable) {
        final PatientCardPageDto page = cardService.getPage(pageable);
        page.setAdmin(authorities.isAdmin());
        page.setDoc(authorities.isDoc());
        page.setRegister(authorities.isRegister());
        page.setPharmacy(authorities.isPharmacy());
        return page;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        cardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
