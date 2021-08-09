package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "writer_id")
    private Writer writer;

    @Column(name = "content")
    private String content;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    public Post() {
    }


    public Post(String content) {
        this.content = content;
    }

    public Post(Writer writer, String content, LocalDateTime created, LocalDateTime updated) {
        this.writer = writer;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public Post(Long id, Writer writer, String content, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }


    @Override
    public String toString() {
        return "  " + id + " | " + writer + " | " + created + " | " + updated + " | " + content;
    }
}
