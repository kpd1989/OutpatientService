package ru.ambulatory.dto.archive;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ambulatory.dto.RecipeDto;

import java.util.List;

@Data
@AllArgsConstructor
public class ArchiveRecipePageDto {

    private List<RecipeDto> data;
    private int currentPage;
    private int totalPage;
    private boolean hasNext;
    private boolean hasPrevious;
    private boolean admin;
    private boolean chief;
    private boolean doc;
    private boolean pharmacy;
}
