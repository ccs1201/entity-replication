package beneficios.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Pessoa {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private String nome;
    private String email;
    private boolean ativo = true;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;
}
