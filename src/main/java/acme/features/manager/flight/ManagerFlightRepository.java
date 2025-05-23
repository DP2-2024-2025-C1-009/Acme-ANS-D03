
package acme.features.manager.flight;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.flight.Flight;
import acme.entities.legs.Leg;

@Repository
public interface ManagerFlightRepository extends AbstractRepository {

	@Query("select f from Flight f where f.manager.id = :managerId")
	List<Flight> findFlightsByManagerId(int managerId);

	@Query("select f from Flight f where f.id = :masterId")
	Flight findFlightById(int masterId);

	@Query("select l from Leg l")
	Collection<Leg> findAllLegs();

	@Query("select l from Leg l where l.flight.manager.id = :managerId")
	Collection<Leg> fingLegsByManagerId(int managerId);

	@Query("select l from Leg l where l.flight.id = :flightId")
	Collection<Leg> findLegsByFlightId(int flightId);

	@Query("SELECT COUNT(c) FROM Claim c WHERE c.leg.id = :legId")
	int countClaimsByLegId(@Param("legId") int legId);

}
