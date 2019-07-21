package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long acreage;

    private Long population;

    private Long gdp;

    @ManyToOne
    @JoinColumn(name = "nation_id")
    private Nation nation;

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public City(){}

    public City(Long id, String name, Long acreage, Long population, Long gdp) {
        this.id = id;
        this.name = name;
        this.acreage = acreage;
        this.population = population;
        this.gdp = gdp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAcreage() {
        return acreage;
    }

    public void setAcreage(Long acreage) {
        this.acreage = acreage;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Long getGdp() {
        return gdp;
    }

    public void setGdp(Long gdp) {
        this.gdp = gdp;
    }
}
