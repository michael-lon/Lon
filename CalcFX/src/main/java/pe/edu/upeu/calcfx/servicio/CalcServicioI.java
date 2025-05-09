package pe.edu.upeu.calcfx.servicio;

import pe.edu.upeu.calcfx.modelo.CalcTO;

import java.util.List;

public interface CalcServicioI {
    //C
    public void save(CalcTO calcTO);
    //R
    public List<CalcTO> findAll();
    public CalcTO findById(Long index);
   //U
   public void update(CalcTO calcTO, Long index);
   //D
    public void delete(CalcTO calcTO);
    public void deleteById(Long index);

}
