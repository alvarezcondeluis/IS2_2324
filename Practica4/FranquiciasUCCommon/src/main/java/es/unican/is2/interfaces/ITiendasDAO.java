package es.unican.is2.interfaces;

import java.util.List;

import es.unican.is2.domain.Tienda;
import es.unican.is2.exceptions.DataAccessException;
/**
 * Interfaz DAO para tiendas
 */
public interface ITiendasDAO {

	/**
	 * Persite una nueva tienda
	 * @param t Tienda a anhadir
	 * @return La tienda anhadida 
	 *         null si ya existe una tienda con el mismo  id
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Tienda crearTienda(Tienda t) throws DataAccessException;

	/**
	 * Retorna la tienda cuyo id se pasa por par�metro
	 * @param id Id de la tienda
	 * @return La tienda buscada 
	 *         null si no se encuentra
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Tienda tienda(long id) throws DataAccessException;

	/**
	 * Retorna la tienda cuyo nombre se pasa por par�metro
	 * @param nombre Nombre de la tienda
	 * @return La tienda buscada 
	 *         null si no se encuentra
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Tienda tiendaPorNombre(String nombre) throws DataAccessException;

	/**
	 * Actualiza el estado de la tienda
	 * @param nuevo Tienda actualizada
	 * @return Tienda actualizada 
	 *         null si no existe una tienda con el mismo id
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Tienda modificarTienda(Tienda nuevo) throws DataAccessException;

	/**
	 * Elimina una tienda
	 * @param id Id de la tienda a eliminar
	 * @return Tienda eliminada
	 *         null si no se encuentra la tienda
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Tienda eliminarTienda(long id) throws DataAccessException;

	/**
	 * Retorna la lista completa de tiendas
	 * @return Lista de tiendas 
	 *         Lista vacia si no hay ninguna tienda
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public List<Tienda> tiendas() throws DataAccessException;

}
