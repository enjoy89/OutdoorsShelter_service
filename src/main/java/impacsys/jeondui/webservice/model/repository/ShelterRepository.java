package impacsys.jeondui.webservice.model.repository;


import impacsys.jeondui.webservice.model.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    @Query(value = "select shelter from Shelter shelter where  shelter.city_name = :city_name")
    List<Shelter> findByCityName(@Param("city_name") String city_name);

}
