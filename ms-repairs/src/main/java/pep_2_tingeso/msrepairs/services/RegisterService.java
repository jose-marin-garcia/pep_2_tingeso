package pep_2_tingeso.msrepairs.services;

import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pep_2_tingeso.msrepairs.entities.HistoricEntity;
import pep_2_tingeso.msrepairs.entities.HistoryRepairsEntity;
import pep_2_tingeso.msrepairs.model.Price;
import pep_2_tingeso.msrepairs.model.Registry;
import pep_2_tingeso.msrepairs.model.Vehicle;
import pep_2_tingeso.msrepairs.model.VehicleBackend;
import pep_2_tingeso.msrepairs.repositories.BondRepository;
import pep_2_tingeso.msrepairs.repositories.HistoricRepository;
import pep_2_tingeso.msrepairs.repositories.HistoryRepairsRepository;
import pep_2_tingeso.msrepairs.repositories.MarksRepository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RegisterService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BondRepository bondRepository;

    @Autowired
    HistoricRepository historicRepository;

    @Autowired
    HistoryRepairsRepository historyRepairsRepository;

    @Autowired
    MarksRepository markRepository;

    public HistoricEntity saveRegistry(Vehicle vehicle, List<Long> reparations, Long idBond) {

        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        String fechaActual = fechaHora.format(formatoFecha);
        String horaActual = fechaHora.format(formatoHora);

        int sumaReparaciones = calcularSumaReparaciones(vehicle, reparations, horaActual);

        int bono = 0;

        if (idBond != null) {
            bono = bondRepository.findById(idBond).get().getAmount();
            bondRepository.deleteById(idBond);
        }

        int descuentos = ((int) (sumaReparaciones * calcularDescuentos(vehicle.getTypemotor(), reparations.size(), fechaActual, horaActual))) + bono;

        int recargos = (int) (sumaReparaciones * calculoRecargos(vehicle.getKilometers(), vehicle.getType(), fechaHora.getYear()-vehicle.getYear()));

        int montoTotal = sumaReparaciones - descuentos + recargos;

        int iva = montoTotal * 19 / 100;

        HistoricEntity historial = historicRepository.save(new HistoricEntity(null, vehicle.getPatent(), fechaActual, horaActual, montoTotal + iva, sumaReparaciones, descuentos, recargos, iva, null, null, null, null));

        Long idHistorial = historial.getId();

        for (Long idReparacion : reparations) {
            historyRepairsRepository.save(new HistoryRepairsEntity(null, idHistorial, idReparacion));
        }

        VehicleBackend vehicleBackend = new VehicleBackend(vehicle.getPatent(), markRepository.findByMarkName(vehicle.getMark()), vehicle.getModel(), vehicle.getType(), vehicle.getYear(), vehicle.getTypemotor(), vehicle.getNumberseats(), vehicle.getKilometers());

        HttpEntity<VehicleBackend> request = new HttpEntity<>(vehicleBackend);

        restTemplate.postForObject("http://ms-vehicles/vehicles", request, VehicleBackend.class);

        return historial;
    }

    public int calcularSumaReparaciones(Vehicle vehiculo, List<Long> reparaciones, String hora){
        int montoTotal = 0;

        // Obtener el tipo de motor para aplicar los precios base
        String motortype = vehiculo.getTypemotor();

        // Iterar sobre la lista de IDs de reparación para sumar los costos base
        for (Long idReparacion : reparaciones) {
            // Aquí necesitarás una forma de mapear idReparacion a un tipo de reparación y luego a un costo.
            // Esto podría hacerse mediante una consulta a la base de datos o un mapa en memoria, por ejemplo.
            int costoReparacion = restTemplate.getForObject("http://ms-prices/prices/price/get/" + idReparacion + "/" + motortype, Price.class).getPrice();
            montoTotal += costoReparacion;
        }

        return montoTotal;
    }

    public double calcularDescuentos(String motorType, int totalRepairs, String fecha, String hora) {
        double descuento = calcularDescuentoPorNumeroDeReparaciones(motorType, totalRepairs) + aplicarDescuentoSiCorresponde(fecha,hora);
        //imprimir descuento por consola
        System.out.println("Descuento: " + descuento);
        return descuento;
    }

    public double calcularDescuentoPorNumeroDeReparaciones(String motorType, int totalRepairs) {
        double descuento = 0;
        switch (motorType) {
            case "Gasolina":
                descuento = calculateDiscountForGasoline(totalRepairs);
                break;
            case "Diesel":
                descuento = calculateDiscountForDiesel(totalRepairs);
                break;
            case "Hibrido":
                descuento = calculateDiscountForHibrid(totalRepairs);
                break;
            case "Electrico":
                descuento = calculateDiscountForHybridElectric(totalRepairs);
                break;
            default:
                break;
        }
        return descuento;
    }

    public double calculateDiscountForGasoline(int totalRepairs) {
        if (totalRepairs >= 1 && totalRepairs <= 2) {
            return 0.05;
        } else if (totalRepairs >= 3 && totalRepairs <= 5) {
            return 0.10;
        } else if (totalRepairs >= 6 && totalRepairs <= 9) {
            return 0.15;
        } else if (totalRepairs >= 10) {
            return 0.20;
        }
        return 0.0;
    }

    public double calculateDiscountForDiesel(int totalRepairs) {
        if (totalRepairs >= 1 && totalRepairs <= 2) {
            return 0.07;
        } else if (totalRepairs >= 3 && totalRepairs <= 5) {
            return 0.12;
        } else if (totalRepairs >= 6 && totalRepairs <= 9) {
            return 0.17;
        } else if (totalRepairs >= 10) {
            return 0.22;
        }
        return 0.0;
    }

    public double calculateDiscountForHibrid(int totalRepairs) {
        if (totalRepairs >= 1 && totalRepairs <= 2) {
            return 0.10;
        } else if (totalRepairs >= 3 && totalRepairs <= 5) {
            return 0.15;
        } else if (totalRepairs >= 6 && totalRepairs <= 9) {
            return 0.20;
        } else if (totalRepairs >= 10) {
            return 0.25;
        }
        return 0.0;
    }

    public double calculateDiscountForHybridElectric(int totalRepairs) {
        if (totalRepairs >= 1 && totalRepairs <= 2) {
            return 0.08;
        } else if (totalRepairs >= 3 && totalRepairs <= 5) {
            return 0.13;
        } else if (totalRepairs >= 6 && totalRepairs <= 9) {
            return 0.18;
        } else if (totalRepairs >= 10) {
            return 0.23;
        }
        return 0.0;
    }

    public double aplicarDescuentoSiCorresponde(String fecha, String hora) {
        if (esDescuentoAplicable(fecha, hora)) {
            return 0.1;
        }
        return 0.0;
    }

    public boolean esDescuentoAplicable(String fecha, String hora) {

        // Combina fecha y hora en un solo objeto LocalDateTime
        LocalDateTime fechaHora = LocalDateTime.parse(fecha + " " + hora, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        if(fechaHora.getDayOfWeek() == DayOfWeek.MONDAY || fechaHora.getDayOfWeek() == DayOfWeek.THURSDAY){
            LocalDateTime inicioDescuento = fechaHora.withHour(9).withMinute(0);
            LocalDateTime finDescuento = fechaHora.withHour(12).withMinute(0);
            return fechaHora.isAfter(inicioDescuento) && fechaHora.isBefore(finDescuento);
        }else{
            return false;
        }
    }

    public double calculoRecargos(int kilometers, String type, int antiguedad) {
        return calcularRecargoPorKilometraje(kilometers,type) + calcularRecargoPorAntiguedad(antiguedad,type);
    }

    public double calcularRecargoPorKilometraje(int kilometraje, String tipoAuto) {

        return switch (tipoAuto) {
            case "Sedan", "Hatchback" -> calcularRecargoPorTipo(kilometraje, 0.03, 0.07);
            case "SUV", "Pickup", "Furgoneta" -> calcularRecargoPorTipo(kilometraje, 0.05, 0.09);
            default -> throw new IllegalArgumentException("Tipo de auto desconocido: " + tipoAuto);
        };
    }

    public double calcularRecargoPorTipo(int kilometraje, double recargo1, double recargo2) {
        if (kilometraje <= 5000) {
            return 0;
        } else if (kilometraje <= 12000) {
            return recargo1;
        } else if (kilometraje <= 25000) {
            return recargo2;
        } else if (kilometraje <= 40000) {
            return 0.12;
        } else {
            return 0.2;
        }
    }

    public double calcularRecargoPorAntiguedad(int antiguedad, String tipoAuto) {
        return switch (tipoAuto) {
            case "Sedan", "Hatchback" -> calcularRecargoPorTipoAntiguedad(antiguedad, 0.05, 0.09, 0.15);
            case "SUV", "Pickup", "Furgoneta" -> calcularRecargoPorTipoAntiguedad(antiguedad, 0.07, 0.11, 0.20);
            default -> throw new IllegalArgumentException("Tipo de auto desconocido: " + tipoAuto);
        };
    }

    public double calcularRecargoPorTipoAntiguedad(int antiguedad , double recargo1, double recargo2, double recargo3) {
        if (antiguedad <= 5) {
            return 0;
        } else if (antiguedad <= 10) {
            return recargo1;
        } else if (antiguedad <= 15) {
            return recargo2;
        } else {
            return recargo3;
        }
    }

}
