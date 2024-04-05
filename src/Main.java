import controller.AvionController;
import controller.PasajeroController;
import dataBase.ConfigDB;
import entity.Pasajero;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //ConfigDB.openConnection();

        String option = "";

        do {
            option = JOptionPane.showInputDialog("""
                    opciones: 
                    1. Aviones
                    2. Pasajeros
                    3. Vuelos
                    4. Reservas
                    5. Salir
                                        
                    Selecciona una opcion: 
                    """);

            switch (option) {
                case "1":
                    //AVIONES
                    String option1 = "";

                    do {
                        option1 = JOptionPane.showInputDialog("""
                                opciones: 
                                1. Insertar nuevo avion
                                2. Listar todos los aviones
                                3. Buscar por id
                                4. Eliminar 
                                5. Editar
                                6. Salir
                                                        
                                Selecciona una opcion: 
                                """);
                        switch (option1) {
                            case "1":
                                AvionController.create();
                                break;
                            case "2":
                                AvionController.getAll();
                                break;
                        }
                    } while (!option1.equals("6"));
                    break;
                case"2":
                    //PASAJEROS
                    String option2 = "";

                    do {
                        option2 = JOptionPane.showInputDialog("""
                                opciones: 
                                1. Insertar nuevo pasajero
                                2. Listar todos los pasajeros
                                3. Buscar por id
                                4. Buscar por Documento de identidad
                                5. Eliminar 
                                6. Editar
                                7. Salir
                                                        
                                Selecciona una opcion: 
                                """);
                        switch (option2) {
                            case "1":
                                PasajeroController.create();
                                break;
                            case"2":
                                PasajeroController.getAll();
                                break;
                        }
                    }while(!option2.equals("7"));

                    break;
                case"3":
                    //VUELOS
            }
        } while (!option.equals("5"));
    }
}