package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="bankbranch")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BankBranch  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="id")
    private Long pk_branchId;

    @Column(name="name")
    private String name;
    @Column(name="country")
    private String country;

    public BankBranch(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
