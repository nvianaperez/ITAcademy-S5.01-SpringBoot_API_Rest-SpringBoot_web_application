package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.service;

import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.domain.BankBranch;
import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.exception.BankBranchNotFoundException;
import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.repository.IBranchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements IBranchService {
    @Autowired
    private IBranchRepository branchRepository;

    //Other resource: https://www.baeldung.com/java-modelmapper --> ModelMapper library to map data between different structured objects
    public BranchDTO convertToDTO(BankBranch bankBranch) {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setPk_branchId(bankBranch.getPk_branchId());
        branchDTO.setName(bankBranch.getName());
        branchDTO.setCountry(bankBranch.getCountry());
        return branchDTO;
    }

    public BankBranch convertToEntity(BranchDTO branchDTO) {
        BankBranch bankBranch = new BankBranch();
        bankBranch.setPk_branchId(branchDTO.getPk_branchId());
        bankBranch.setName(branchDTO.getName());
        bankBranch.setCountry(branchDTO.getCountry());
        return bankBranch;
    }

    @Override
    @Transactional //save only if transaction finish with exit
    public BranchDTO createBranch(BranchDTO branchDTO) {
        BankBranch branch = convertToEntity(branchDTO);
        return convertToDTO(branchRepository.save(branch));
    }

    @Override
    @Transactional
    public void updateBranch(Long id, BranchDTO branchDTO) {
        BranchDTO branchDTOToUpdate = getOneBranchById(id);
        branchDTOToUpdate.setPk_branchId(branchDTO.getPk_branchId());
        branchDTOToUpdate.setName(branchDTO.getName());
        branchDTOToUpdate.setCountry(branchDTO.getCountry());
        branchRepository.save(convertToEntity(branchDTOToUpdate));
    }

    @Override
    @Transactional
    public void deleteBranch(Long id) {
        BranchDTO branchDTOToDelete = getOneBranchById(id);
        branchRepository.delete(convertToEntity(branchDTOToDelete));
    }

    @Override
    public BranchDTO getOneBranchById(Long id) {
//        Optional<BankBranch> branch = branchRepository.findById(id);
//        return branch.map(this::convertToDTO).get();
        BranchDTO branchDTO = branchRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new BankBranchNotFoundException("The branch with id " + id + " is not found in database"));
        return branchDTO;
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        List<BankBranch> branchList = branchRepository.findAll();
        List<BranchDTO> branchDTOList = branchList.stream()
//                .map(b -> convertToDTO(b))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return branchDTOList;
    }
}
