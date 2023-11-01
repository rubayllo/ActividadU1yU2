package org.utadactividades.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "empleados")
public class Empleados {
    private List<Empleado> empleadosList = new ArrayList<>();

    @XmlElement(name = "empleado")
    public List<Empleado> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleado> empleadosList) {
        this.empleadosList = empleadosList;
    }

}
