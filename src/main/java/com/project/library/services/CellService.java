package com.project.library.services;

import com.project.library.model.Cell;
import com.project.library.repositories.CellRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CellService {

    private CellRepository cellRepository;

    public CellService(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

    public List<Cell> showAvailableCells() {
        return cellRepository.showAvailableCellsInRepo();
    }

    public void addCells(Cell cell) {
        cellRepository.insertCells(cell);
    }

    public Cell getCellByID(int cellID) {
        return cellRepository.getCellByID(cellID);
    }

    public void deleteById(int cellID) {
        cellRepository.deleteById(cellID);
    }

    public void callCellUpdated(Cell updatedCell) {
        cellRepository.updateCellData(updatedCell);
    }


    public boolean selectAvailableCells() {
        return cellRepository.selectAvailableCell();
    }
}
