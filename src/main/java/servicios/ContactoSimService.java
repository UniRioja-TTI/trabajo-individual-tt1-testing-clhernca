package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.tt1.trabajo.utilidades.Solicitud;

import interfaces.InterfazContactoSim;
import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;

@Service
public class ContactoSimService implements InterfazContactoSim {

    private List<Entidad> entidades = new ArrayList<>();
    private List<DatosSolicitud> solicitudes = new ArrayList<>();

    public ContactoSimService() {
        rellenarEntidades();
    }

    private void rellenarEntidades() {
        Entidad e1 = new Entidad();
        e1.setId(1);
        e1.setName("Servidor Web");
        e1.setDescripcion("Nodo principal de servicio HTTP");

        Entidad e2 = new Entidad();
        e2.setId(2);
        e2.setName("Router Central");
        e2.setDescripcion("Nodo de enrutamiento principal de la red");

        Entidad e3 = new Entidad();
        e3.setId(3);
        e3.setName("Firewall");
        e3.setDescripcion("Nodo de seguridad y filtrado de tráfico");

        Entidad e4 = new Entidad();
        e4.setId(4);
        e4.setName("Servidor de Base de Datos");
        e4.setDescripcion("Nodo de almacenamiento y consulta de datos");

        Entidad e5 = new Entidad();
        e5.setId(5);
        e5.setName("Balanceador de Carga");
        e5.setDescripcion("Nodo distribuidor de tráfico entre servidores");

        entidades.add(e1);
        entidades.add(e2);
        entidades.add(e3);
        entidades.add(e4);
        entidades.add(e5);
    }

    @Override
    public int solicitarSimulation(DatosSolicitud sol) {
        solicitudes.add(sol);
        try {
            Solicitud solicitud = new Solicitud();

            List<String> nombres = new ArrayList<>();
            List<Integer> cantidades = new ArrayList<>();

            for (Map.Entry<Integer, Integer> entry : sol.getNums().entrySet()) {
                // Buscar la entidad por ID para obtener su nombre
                entidades.stream()
                        .filter(e -> e.getId() == entry.getKey())
                        .findFirst()
                        .ifPresent(e -> nombres.add(e.getName()));
                cantidades.add(entry.getValue());
            }

            solicitud.setNombreEntidades(nombres);
            solicitud.setCantidadesIniciales(cantidades);

            solicitudApi.solicitudSolicitarPost("usuario", solicitud);

            return new Random().nextInt(10000);

        } catch (Exception e) {
            return new Random().nextInt(10000);
        }
    }

    @Override
    public DatosSimulation descargarDatos(int ticket) {
        return new DatosSimulation();
    }

    @Override
    public List<Entidad> getEntities() {
        return entidades;
    }

    @Override
    public boolean isValidEntityId() {
        return true;
    }

}
