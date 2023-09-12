package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.repository;

import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.domain.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBranchRepository extends JpaRepository<BankBranch, Long> {
}
