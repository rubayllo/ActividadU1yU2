package org.utadactividades;

import org.utadactividades.model.Empleado;
import org.utadactividades.model.Empleados;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class UseXML {

    private static final String PATH = "./src/main/resources/empleados.xml";

    private static Empleados empleados;

    // Método para cargar los dartos actuales del archivo
    // XML en la lista empleados de la clase Empleados
    // será requerido cada vez que se necesite recibir o
    // mandar datos
    public static void chargeXML(){
        try {
            JAXBContext ctx = JAXBContext.newInstance(Empleados.class);
            Unmarshaller um = ctx.createUnmarshaller();
            File fileToRead = new File(PATH);
            empleados = (Empleados) um.unmarshal(fileToRead);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }


    // Método para leer los datos estructurados del archivo.XML
    public static void readXML() {
        chargeXML();

        for (Empleado empleado : empleados.getEmpleadosList()) {
            System.out.println("Nomb: " + empleado.getNombre());
            System.out.println("Ape: " + empleado.getApellido());
            System.out.println("DNI: " + empleado.getDni());
            System.out.println("Dpto: " + empleado.getDpto());
            System.out.println("-----------------------------");
        }

    }

    public static Empleados getEmpleados() {
        return empleados;
    }


// Método que envía los datos a insertar en la base de datos
    public static void insertInDB() {
        chargeXML();

        int totalInserts = 0; // contador para verificar la cantidad de nuevos usuarios insertados

        for (Empleado empleado : empleados.getEmpleadosList()) {
            String nombre = empleado.getNombre();
            String apellido = empleado.getApellido();
            String dni = empleado.getDni();
            String dpto = empleado.getDpto();

            totalInserts += DBConnect.insertSQL(nombre, apellido, dni, dpto);
        }

        System.out.println("Numero total de empleados nuevos insertados: " + totalInserts);
    }
}
