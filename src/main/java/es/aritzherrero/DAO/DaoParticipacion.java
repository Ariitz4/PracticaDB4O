package es.aritzherrero.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import es.aritzherrero.MODELO.ModeloDeportista;
import es.aritzherrero.MODELO.ModeloEvento;
import es.aritzherrero.MODELO.ModeloParticipacion;

import java.util.List;

/**
 * The Class DaoParticipacion.
 */
public class DaoParticipacion {

	/**
	 * Insertar.
	 *
	 * @param p the p
	 * @param db the db
	 */
	public static void insertar(ModeloParticipacion p,ObjectContainer db) {
		db.store(p);
	}
	
	/**
	 * Conseguir por deportista y evento.
	 *
	 * @param dep the dep
	 * @param e the e
	 * @param db the db
	 * @return the modelo participacion
	 */
	public static ModeloParticipacion conseguirPorDeportistaEvento(ModeloDeportista dep,ModeloEvento e, ObjectContainer db) {
		ModeloParticipacion par=new ModeloParticipacion();
		par.setDeportista(dep);
		par.setEvento(e);
		ObjectSet<ModeloParticipacion> set=db.queryByExample(par);
		return set.hasNext() ? set.next() : null;
	}
	
	/**
	 * Conseguir por evento.
	 *
	 * @param e the e
	 * @param db the db
	 * @return the list
	 */
	public static List<ModeloParticipacion> conseguirPorEvento(ModeloEvento e,ObjectContainer db){
		List<ModeloParticipacion> participaciones=db.query(new Predicate<ModeloParticipacion>() {

			@Override
			public boolean match(ModeloParticipacion par) {
				return par.getEvento().equals(e);
			}
		});
		return participaciones;
	}
	
	/**
	 * Conseguir por deportista.
	 *
	 * @param d the d
	 * @param db the db
	 * @return the list
	 */
	public static List<ModeloParticipacion> conseguirPorDeportista(ModeloDeportista d,ObjectContainer db){
		List<ModeloParticipacion> participaciones=db.query(new Predicate<ModeloParticipacion>() {

			@Override
			public boolean match(ModeloParticipacion par) {
				return par.getDeportista().equals(d);
			}
		});
		return participaciones;
	}
	
	/**
	 * Actualizar medallas.
	 *
	 * @param medalla the medalla
	 * @param dep the dep
	 * @param e the e
	 * @param db the db
	 */
	public static void actualizarMedallas(String medalla,ModeloDeportista dep,ModeloEvento e, ObjectContainer db) {
		ModeloParticipacion p=conseguirPorDeportistaEvento(dep, e, db);
		p.setMedalla(medalla);
		db.store(p);
	}
	
	/**
	 * Eliminar.
	 *
	 * @param dep the dep
	 * @param e the e
	 * @param db the db
	 */
	public static void eliminar(ModeloDeportista dep, ModeloEvento e, ObjectContainer db) {
		ModeloParticipacion p=conseguirPorDeportistaEvento(dep, e, db);
		db.delete(p);
	}
	
}
