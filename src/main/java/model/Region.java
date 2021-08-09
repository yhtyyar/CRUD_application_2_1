package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
public class Region {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "region", cascade = CascadeType.ALL)
    private List<Writer> writerList;

    @Column(name = "region_name")
    private String regionName;


    public Region() {
    }


    public Region(Long id) {
        this.id = id;
    }



    public Region (String regionName) {
        this.regionName = regionName;
    }


    public Region(Long id, String regionName) {
        this.id = id;
        this.regionName = regionName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Writer> getWriterList() {
        return writerList;
    }

    public void setWriterList(List<Writer> writerList) {
        this.writerList = writerList;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }


    @Override
    public String toString() {

        StringBuilder writerBuilder = new StringBuilder();

        for (Writer writer : writerList) {
            writerBuilder.append(writer.getId()).append(" | ");
        }


        return "  " + id + " |  " + regionName + " | " + writerList;
    }

}
