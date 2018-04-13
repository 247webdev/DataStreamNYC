package com.example.DataStreamNYC.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "SUGGESTIONS")
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "USER_NAME")
    private String userName;

    public Suggestion(String title, String content, String userName) {
        this.title = title;
        this.content = content;
        this.userName = userName;
    }
}
