package com.exam.presentation;

import com.exam.application.StatsApplicationService;
import java.util.Scanner;

public class InstructorUI {

    private final StatsApplicationService statsService;
    private final Scanner scanner = new Scanner(System.in);

    public InstructorUI(StatsApplicationService statsService) {
        this.statsService = statsService;
    }

    public void start() {
        System.out.print("Ingrese PIN docente: ");
        String pin = scanner.nextLine();

        if (!pin.equals("1234")) {
            System.out.println("PIN incorrecto.");
            return;
        }

        System.out.println("\n=== PANEL DOCENTE ===");

        int total = statsService.totalEstudiantesQuePresentaron();
        double promedio = statsService.promedioGlobal();

        System.out.println("Estudiantes evaluados: " + total);
        System.out.println("Promedio global: " + promedio);
    }
}