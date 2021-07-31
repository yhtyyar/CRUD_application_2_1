package model;

import javax.persistence.*;

@Entity
@Table(name = "regions", schema = "public")
public class Region {

    @Id
    @SequenceGenerator(name = "region_seq", sequenceName = "region_region_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_seq")
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "writer_id")
    private Writer writer;

    @Column(name = "region_name")
    private String regionName;


    public Region() {
    }


    public Region(Long id) {
        this.id = id;
    }


    public Region(Writer writer, String regionName) {
        this.writer = writer;
        this.regionName = regionName;
    }


    public Region(Long id, Writer writer, String regionName) {
        this.id = id;
        this.writer = writer;
        this.regionName = regionName;
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

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }


    @Override
    public String toString() {
        return "  " + id + " |  " + regionName + " | " + writer;
    }

}
