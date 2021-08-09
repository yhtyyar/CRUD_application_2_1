package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "writers")
public class Writer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Post> posts;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;


    public Writer() {
    }


    public Writer(Long id) {
        this.id = id;
    }


    public Writer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Writer(String firstName, String lastName, Region region) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
    }

    public Writer(Long id, String firstName, String lastName, Region region) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
    }

    public Writer(Long id, String firstName, String lastName, Region region, List<Post> posts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.posts = posts;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }


    @Override
    public String toString() {

        StringBuilder postBuilder = new StringBuilder();

        if (posts != null) {
            for (Post post : posts) {
                postBuilder.append(post.getContent()).append(" | ");
            }

            return "  " + id + " | " + firstName + " | " + lastName +
                    " | " + region.getRegionName() + " | " + postBuilder;

        } else {

            return "  " + id + " | " + firstName + " | " + lastName +
                    " | " + region.getRegionName() + " | " + "нет записи";

        }





    }
}
