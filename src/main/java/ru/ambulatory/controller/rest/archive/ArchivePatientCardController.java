package ru.ambulatory.controller.rest.archive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ambulatory.dto.archive.ArchivePatientCardPageDto;
import ru.ambulatory.security.Authorities;
import ru.ambulatory.service.archive.ArchivePatientCardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/archive_cards")
public class ArchivePatientCardController {

    private final ArchivePatientCardService cardService;
    private final Authorities authorities;

    @GetMapping
    public ArchivePatientCardPageDto find(@PageableDefault(value = 20) Pageable pageable) {
        final ArchivePatientCardPageDto page = cardService.getPage(pageable);
        page.setAdmin(authorities.isAdmin());
        page.setChief(authorities.isChief());
        page.setDoc(authorities.isDoc());
        page.setRegister(authorities.isRegister());
        page.setPharmacy(authorities.isPharmacy());
        return page;
    }
}
