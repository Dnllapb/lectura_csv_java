package org.example;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.Model.Product;
import org.example.Model.RemoveToString;
import org.example.Service.productService;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainPrueba {

    public static void main(String[] args)
    {
       //1. Instanciar la clase productServicetos para poder llamar la lista
       productService productService = new productService();
       //2.Obtener la lista de productos que se encuentra en el objeto productService
       List <Product> productList = productService.getProductList();

        try {
        //3.Instanciamos las clases de la libreria en la primera pasamaos la ruta donde tenemos el archivo csv
            FileReader fileReader = new FileReader("C:\\Users\\danie\\IdeaProjects\\lectura_csv_java\\resources\\inventory.csv");
            //4. La clase CSV Parser se utiliza para analizar un archivo CSV en filas y columnas de datos. Permite leer y procesar los datos del archivo CSV.
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

            boolean primeraFila = true; // Flag para detectar la primera fila (encabezados)

            for (CSVRecord csvRecord : csvParser) {
                if (primeraFila ) {//NO PERMITE TOMAR LOS ENCABEZADOS DEL EXCEL
                    // Si es la primera fila, omitirla
                    primeraFila  = false;
                    continue;//se utiliza para saltar esa fila y avanzar a la siguiente fila de datos.
                }

                int codigo = Integer.parseInt(csvRecord.get(0));
                String nombre = csvRecord.get(1);
                String descripcion = csvRecord.get(2);
                String categoria = csvRecord.get(3);
                String etiqueta = csvRecord.get(4);
                double precio = Double.parseDouble(csvRecord.get(5));
                String url = csvRecord.get(6);

                Product producto = new Product(codigo, nombre, descripcion, categoria, etiqueta, precio, url);
                productList.add(producto);

                RemoveToString remove = new RemoveToString(codigo, nombre, descripcion, categoria, etiqueta, precio, url);
                System.out.println(remove);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        productService productServic = new productService();
        Scanner sc= new Scanner(System.in);
        // Cargar productos desde un archivo CSV
        System.out.println("Seleciona por favor que va realizar \n1.Ver Productos\n2.Añadir\n3.Consultar ");

        int seleccion = 0;
        while (seleccion!=3) {
            seleccion = sc.nextInt();
            switch (seleccion) {

                case 1-> productServic.cargarProductosDesdeCSV("C:\\Users\\danie\\IdeaProjects\\lectura_csv_java\\resources\\inventory.csv");
                case 2-> productServic.add();
                case 3-> productServic.remove();
            }
        }

    }



}
