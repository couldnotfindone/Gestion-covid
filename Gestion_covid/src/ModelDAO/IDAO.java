package ModelDAO;

import java.util.ArrayList;
import java.util.List;

import MVCModel.ListPatients;
import MVCModel.Patient;

public interface IDAO<T> {
	public abstract T findByID (Object id);
	 public abstract ArrayList<Patient> findAll();
	 public abstract boolean delete (T o);
	 public abstract boolean insert (T o);


}
