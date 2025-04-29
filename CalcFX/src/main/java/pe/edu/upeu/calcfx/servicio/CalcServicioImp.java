package pe.edu.upeu.calcfx.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.calcfx.modelo.CalcTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalcServicioImp implements CalcServicioI{

    List<CalcTO> datos= new ArrayList<>();

    @Override
    public void save(CalcTO calcTO) {
        datos.add(calcTO);
    }

    @Override
    public List<CalcTO> findAll() {
        return datos;
    }

    @Override
    public CalcTO findById(int index) {
        return datos.get(index);
    }

    @Override
    public void update(CalcTO calcTO, int index) {
        datos.set(index, calcTO);
    }

    @Override
    public void delete(CalcTO calcTO) {
    datos.remove(calcTO);
    }

    @Override
    public void deleteById(int index) {
    datos.remove(index);
    }
}

