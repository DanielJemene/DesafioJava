package br.com.minhaempresa.models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
public class MembroId implements Serializable {

    private Long projeto;
    private Long pessoa;
}
