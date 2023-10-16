package br.com.rocketseat.todolist.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(length = 50)
    private String titulo;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    private String descricao;
    private LocalDateTime comecaEm;
    private LocalDateTime terminaEm;
    private String prioridade;
    private UUID idUser;

    public void setTitulo(String titulo) throws Exception {
        if (titulo.length() > 50) {
            throw new Exception("Título não pode ter mais de 50 caracteres");
        }
        this.titulo = titulo;
    }
}
