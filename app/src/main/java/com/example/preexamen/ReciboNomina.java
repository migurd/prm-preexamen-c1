package com.example.preexamen;
import java.util.Random;

public class ReciboNomina {
    private int numRecibo;
    private String nombre;
    private double horasTrabajoNormal;
    private double horasTrabajoExtra;
    private int puesto;
    private double impuestoPorcentaje;

    public ReciboNomina(int numRecibo, String nombre, double horasTrabajoNormal, double horasTrabajoExtra, int puesto, double impuestoPorcentaje) {
        this.numRecibo = numRecibo;
        this.nombre = nombre;
        this.horasTrabajoNormal = horasTrabajoNormal;
        this.horasTrabajoExtra = horasTrabajoExtra;
        this.puesto = puesto;
        this.impuestoPorcentaje = impuestoPorcentaje;
    }
    public ReciboNomina() {
        this.numRecibo = 0;
        this.nombre = "";
        this.horasTrabajoNormal = 0;
        this.horasTrabajoExtra = 0;
        this.puesto = 0;
        this.impuestoPorcentaje = 0;
    }

    public ReciboNomina(ReciboNomina recibo) {
        this.numRecibo = recibo.numRecibo;
        this.nombre = recibo.nombre;
        this.horasTrabajoNormal = recibo.horasTrabajoNormal;
        this.horasTrabajoExtra = recibo.horasTrabajoExtra;
        this.puesto = recibo.puesto;
        this.impuestoPorcentaje = recibo.impuestoPorcentaje;
    }

    public int getNumRecibo() {
        return numRecibo;
    }

    public void setNumRecibo(int numRecibo) {
        this.numRecibo = numRecibo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getHorasTrabajoNormal() {
        return horasTrabajoNormal;
    }

    public void setHorasTrabajoNormal(double horasTrabajoNormal) {
        this.horasTrabajoNormal = horasTrabajoNormal;
    }

    public double getHorasTrabajoExtra() {
        return horasTrabajoExtra;
    }

    public void setHorasTrabajoExtra(double horasTrabajoExtra) {
        this.horasTrabajoExtra = horasTrabajoExtra;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public double getImpuestoPorcentaje() {
        return impuestoPorcentaje;
    }

    public void setImpuestoPorcentaje(double impuestoPorcentaje) {
        this.impuestoPorcentaje = impuestoPorcentaje;
    }

    public int generateId() {
        Random rand = new Random();
        return rand.nextInt(999) + 1;
    }

    public double calcularSubtotal() {
        final int PAGOBASE = 200;
        int bono = puesto == 1 ? 20 : puesto == 2 ? 50 : puesto == 3 ? 100 : 0;
        int pagoBaseBono = PAGOBASE + (PAGOBASE * (bono / 100));

        return pagoBaseBono * this.horasTrabajoNormal + pagoBaseBono * 2 * horasTrabajoExtra;
    }

    public double calcularImpuesto() {
        return calcularSubtotal() * 0.16;
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularImpuesto();
    }
}
