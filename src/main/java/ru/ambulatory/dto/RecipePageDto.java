package ru.ambulatory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecipePageDto {

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
