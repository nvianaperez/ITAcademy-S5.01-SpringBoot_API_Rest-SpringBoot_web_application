package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.service;

import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.dto.BranchDTO;

import java.util.List;
public interface IBranchService {

    BranchDTO createBranch(BranchDTO branchDTO);
    void updateBranch(Long id, BranchDTO branchDTO);
    void deleteBranch(Long id);
    BranchDTO getOneBranchById(Long id);
    List<BranchDTO> getAllBranches();







}
