package com.project.library.controllers;

import com.project.library.model.Admin;
import com.project.library.model.Cell;
import com.project.library.services.CellService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CellController {

    private final CellService cellService;

    public CellController(CellService cellService) {
        this.cellService = cellService;
    }

    @GetMapping(path = "/cells")
    public List<Cell> showAvailableCells() {
        return cellService.showAvailableCells();
    }

    @GetMapping(path = "/cells/{cellID}")
    public Cell getCellByID(@PathVariable int cellID) {
        return cellService.getCellByID(cellID);
    }

    @PostMapping(path = "/cells")
    public String addCells(@RequestBody Cell cell) {
        cellService.addCells(cell);
        return "The cell is created";
    }

    @PutMapping(path = "/cells/update/{cellID}")
    public String updateAdminData(@PathVariable int cellID) {
        Cell updatedCell = null;
        try{
            updatedCell = cellService.getCellByID(cellID);
        } catch(Exception e) {
            return e.getMessage();
        }
        if (updatedCell.isCellAvailable()) {
            updatedCell.setCellAvailable(false);
        } else {
            updatedCell.setCellAvailable(true);
        }
        cellService.callCellUpdated(updatedCell);
        return "The cell with ID" + cellID +" has been updated. Now it " + (updatedCell.isCellAvailable() ? "available" : "not available");
    }

    @DeleteMapping(path = "cells/update/{cellID}")
    public String deleteAdmin(@PathVariable int cellID) {
        try {
            cellService.deleteById(cellID);
        } catch (Exception e) {
            return String.valueOf(e);
        }
        return "Admin with ID " + cellID + "deleted";
    }


}
