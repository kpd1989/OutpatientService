package ru.ambulatory.controller.rest.archive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ArchivePatientCardPageController {

    @GetMapping("/archive_cards")
    public String allCards() {
        return "archive/archive_cards";
    }

}
