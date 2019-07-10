/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inductivehealth.ndr.schema;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author brightoibe
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Containers")
@XmlSeeAlso({Container.class})
public class Containers {
    @XmlElement(name = "Container")
    protected List<Container> containers;
    public Containers(){
        
    }

    /**
     * @return the containers
     */
    @XmlElement(name = "Container", type = Container.class)
    public List<Container> getContainers() {
        return containers;
    }

    /**
     * @param containers the containers to set
     */
    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
}
