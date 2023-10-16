package br.com.rocketseat.todolist.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/*
* @Data - Getters, Setters, toString, equals e hashCode
* @Getter - Getters
* @Setter - Setters
* @ToString - toString
* @EqualsAndHashCode - equals e hashCode
* @NoArgsConstructor - Construtor sem argumentos
* @AllArgsConstructor - Construtor com argumentos
* @RequiredArgsConstructor - Construtor com argumentos para os campos marcados com @NonNull
* @Value - Getters, toString, equals e hashCode para campos marcados com @NonNull
* @Builder - Construtor de objetos
* @Singular - Construtor de objetos para coleções
* */

@Data
@Entity(name = "tb_users")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String username;

    private String nome;
    private String senha;

    @CreationTimestamp
    private LocalDateTime criadoEm;
}
