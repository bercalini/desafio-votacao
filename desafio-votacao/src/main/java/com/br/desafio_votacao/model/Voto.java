package com.br.desafio_votacao.model;

import com.br.desafio_votacao.enums.OpcaoVoto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cpf", "pauta_id"}))
@Data
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    private String cpf;

    @Enumerated(EnumType.STRING)
    private OpcaoVoto opcaoVoto;
}
