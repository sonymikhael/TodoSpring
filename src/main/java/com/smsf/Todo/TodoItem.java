package com.smsf.Todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Column(columnDefinition = "varchar(255) default ''")
    private String description;

    @Column(columnDefinition = "boolean default false")
    private boolean isDone;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP()")
    @Builder.Default
    private Date createDate = new Date();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
