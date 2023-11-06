
package org.example;


import org.example.Service.productService;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        productService productServic = new productService();
        Scanner sc= new Scanner(System.in);

        String  menu = """
                Digita la opcion que deseas realizar
                1.Consultar productos
                2.Agregar Producto
                """;
        System.out.println(menu);

        int seleccion = 0;
        while (seleccion!=3) {
            seleccion = sc.nextInt();
            switch (seleccion) {

                case 1-> productServic.cargarProductosDesdeCSV("C:\\Users\\danie\\IdeaProjects\\lectura_csv_java\\resources\\inventory.csv");
                case 2-> productServic.agregarProducto();

            }
        }
    }
}
